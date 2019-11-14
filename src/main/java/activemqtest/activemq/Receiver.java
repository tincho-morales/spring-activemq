package activemqtest.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Email email) throws Exception{


        System.out.println("PROCESADO MAIL:"+ email.getId());
    }


    @JmsListener(destination = "ActiveMQ.DLQ", containerFactory = "myFactory")
    public void receiveError(Email email) throws Exception{

        System.out.println("-------------- ERROR! --------------");
        System.out.println("Email en la cola de errores: "+email.getId());
    }

}