package com.example.florianbeuckert.notfallstats.Data;

public class Datensatz {

    private int id;
    private String datum;
    private Einsatzcode codeGemeldet;
    private Einsatzcode codeKorrekt;
    private String bemerkung;
    private String kommentar;

    public Datensatz() {
    }

    public Datensatz(int id, String datum, Einsatzcode codeGemeldet, Einsatzcode codeKorrekt, String bemerkung, String kommentar) {
        this.id = id;
        this.datum = datum;
        this.codeGemeldet = codeGemeldet;
        this.codeKorrekt = codeKorrekt;
        this.bemerkung = bemerkung;
        this.kommentar = kommentar;
    }

    public static final int STAT_KORREKT = 0;
    public static final int STAT_PRIO_KORREKT = 1;
    public static final int STAT_PRIO_ZU_HOCH = 2;
    public static final int STAT_PRIO_ZU_NIEDRIG = 3;
    public static final int STAT_NICHT_BESTIMMBAR = 4;

    public int evaluateStat() {
        if (codeKorrekt.getAA() == -1 && bemerkung.equals("(keine Bemerkungen)"))
            return STAT_KORREKT;
        if (codeKorrekt.getAA() == -1)
            return STAT_NICHT_BESTIMMBAR;
        if (codeGemeldet.getAA() == codeKorrekt.getAA())
            return STAT_PRIO_KORREKT;
        if (codeGemeldet.getAA() < codeKorrekt.getAA())
            return STAT_PRIO_ZU_HOCH;
        if (codeGemeldet.getAA() > codeKorrekt.getAA())
            return STAT_PRIO_ZU_NIEDRIG;
        return -1;
    }

    public static Einsatzcode stringToEinsatzcode(String s) {
        try {
            int aa = Integer.parseInt(s.substring(0, 2));
            int bb = Integer.parseInt(s.substring(3, 5));

            boolean n = false;
            if (s.length() == 7)
                n = true;

            return new Einsatzcode(aa, bb, n);
        } catch(Exception e) {
            return new Einsatzcode();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Einsatzcode getCodeGemeldet() {
        return codeGemeldet;
    }

    public void setCodeGemeldet(Einsatzcode codeGemeldet) {
        this.codeGemeldet = codeGemeldet;
    }

    public Einsatzcode getCodeKorrekt() {
        return codeKorrekt;
    }

    public void setCodeKorrekt(Einsatzcode codeKorrekt) {
        this.codeKorrekt = codeKorrekt;
    }

    public String getBemerkung() {
        return bemerkung;
    }

    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }
}
