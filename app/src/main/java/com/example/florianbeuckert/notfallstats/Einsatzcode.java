package com.example.florianbeuckert.notfallstats;

public class Einsatzcode {

    int aa;
    int bb;
    boolean n;

    public Einsatzcode() {
        aa = 99;
        bb = 99;
        n = true;
    }

    public Einsatzcode(int aa, int bb, boolean n) {
        this.aa = aa;
        this.bb = bb;
        this.n = n;
    }

    public int getAA() {
        return aa;
    }

    public int getBB() {
        return bb;
    }

    public boolean getN() {
        return n;
    }

    public String getAAasString() {
        if (aa > 9)
            return "" + aa;
        else
            return "0" + aa;
    }

    public String getBBasString() {
        if (bb > 9)
            return "" + bb;
        else
            return "0" + bb;
    }

    public String getNasString() {
        if (n)
            return "N";
        else
            return "";
    }

    public String toString() {
        return getAAasString() + " " + getBBasString() + " " + getNasString();
    }
}