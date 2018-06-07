package com.example.florianbeuckert.notfallstats.Data;

public class EmergencyCode {

    int aa;
    int bb;
    boolean n;

    public EmergencyCode() {
        aa = -1;
        bb = -1;
        n = false;
    }

    public EmergencyCode(int aa, int bb, boolean n) {
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
        if (aa == -1)
            return "";
        else
            return getAAasString() + " " + getBBasString() + " " + getNasString();
    }
}