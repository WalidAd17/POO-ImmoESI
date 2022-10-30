package TP_KERNEL;
public class Prix {
    private double pi,pf,pe,pew;
    private boolean negociable;

    public Prix(int pi,boolean negociable) {
        this.negociable = negociable;
        this.pi = pi;
    }

    public double getPi() {
        return pi;
    }

    public void setPi(double pi) {
        this.pi = pi;
    }

    public double getPf() {
        return pf;
    }

    public void setPf(double pf) {
        this.pf = pf;
    }

    public double getPe() {
        return pe;
    }

    public void setPe(double pe) {
        this.pe = pe;
    }

    public double getPew() {
        return pew;
    }

    public void setPew(double pew) {
        this.pew = pew;
    }

    public boolean isNegociable() {
        return negociable;
    }

    public void setNegociable(boolean negociable) {
        this.negociable = negociable;
    }
}
