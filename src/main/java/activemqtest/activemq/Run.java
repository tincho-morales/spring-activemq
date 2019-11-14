package activemqtest.activemq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Run  implements ApplicationRunner {

    @Autowired
    private Sender sender;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {


        sender.sendEmail(new Email("1","tincho@geocom.com.uy","Vamos tincho",5000));
        sender.sendEmail(new Email("2","martin@geocom.com.uy","Vamos martin",5000));
        sender.sendEmail(new Email("3","morales@geocom.com.uy","Vamos morales",3000));

    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Run.class, args);
    }
}
