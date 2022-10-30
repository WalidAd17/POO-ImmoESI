package TP_KERNEL;
public class Message {
    private String msg;
    private Bien good;
    private Personne prop;

    public Message(String msg, Bien good, Personne prop) {
        this.msg = msg;
        this.good = good;
        this.prop = prop;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Bien getGood() {
        return good;
    }

    public void setGood(Bien good) {
        this.good = good;
    }

    public Personne getProp() {
        return prop;
    }

    public void setProp(Personne prop) {
        this.prop = prop;
    }

    public void AfficheMsg(){
        System.out.println(msg + " " + prop + " " + good);
    }

}
