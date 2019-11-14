package activemqtest.activemq;

public class Email {

    private String id;
    private String to;
    private String body;
    private int seconds;

    public Email() {
    }

    public Email(String id, String to, String body,int seconds) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
