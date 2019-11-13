package activemqtest.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Email email) throws Exception{

        System.out.println("Received <" + email + ">");
        Thread.sleep(email.getSeconds());
    }

}