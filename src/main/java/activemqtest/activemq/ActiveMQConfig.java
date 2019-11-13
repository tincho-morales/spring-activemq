package activemqtest.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.jmx.ManagementContext;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.store.kahadb.KahaDBPersistenceAdapter;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import java.io.File;

@EnableJms
@Configuration
public class ActiveMQConfig {



    @Bean
    public ActiveMQConnectionFactory senderActiveMQConnectionFactory() {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616?jms.dispatchAsync=true");
        activeMQConnectionFactory.setDispatchAsync(true);

        return activeMQConnectionFactory;
    }

    public CachingConnectionFactory cachingConnectionFactory(){

        return new CachingConnectionFactory(senderActiveMQConnectionFactory());
    }


    @Bean
    public JmsTemplate jmsTemplate(MessageConverter jacksonJmsMessageConverter) {

        JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory());
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter);
        return jmsTemplate;
    }

    @Bean
    public JmsListenerContainerFactory<?> myFactory(DefaultJmsListenerContainerFactoryConfigurer configurer) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setMessageConverter(jacksonJmsMessageConverter());

        configurer.configure(factory, cachingConnectionFactory());
        return factory;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public BrokerService broker() throws Exception {

        final BrokerService broker = new BrokerService();

        broker.addConnector("tcp://localhost:61616?jms.dispatchAsync=true");

        KahaDBPersistenceAdapter kahaDBPersistenceAdapter = new KahaDBPersistenceAdapter();
        File file  = new File("data");
        kahaDBPersistenceAdapter.setDirectory(file);
        broker.setPersistenceAdapter(kahaDBPersistenceAdapter);

        final ManagementContext managementContext = new ManagementContext();
        broker.setManagementContext(managementContext);

        return broker;
    }
}