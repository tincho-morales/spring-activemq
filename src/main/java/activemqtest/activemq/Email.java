package activemqtest.activemq;

public class Email {

    private String to;
    private String body;
    private int seconds;

    public Email() {
    }

    public Email(String to, String body,int seconds) {
        this.to = to;
        this.body = body;
        this.seconds = seconds;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
