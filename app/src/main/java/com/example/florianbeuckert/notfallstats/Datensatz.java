package com.example.florianbeuckert.notfallstats;

import java.util.Date;

public class Datensatz {

    private int id;
    private Date datum;
    private Einsatzcode codeGemeldet;
    private Einsatzcode codeKorrekt;
    private String bemerkung;
    private String kommentar;

    public Datensatz() {
    }

    public Datensatz(int id, Date datum, Einsatzcode codeGemeldet, Einsatzcode codeKorrekt, String bemerkung, String kommentar) {
        this.id = id;
        this.datum = datum;
        this.codeGemeldet = codeGemeldet;
        this.codeKorrekt = codeKorrekt;
        this.bemerkung = bemerkung;
        this.kommentar = kommentar;
    }

    private static final int STAT_KORREKT = 0;
    private static final int STAT_PRIO_KORREKT = 1;
    private static final int STAT_PRIO_ZU_HOCH = 2;
    private static final int STAT_PRIO_ZU_NIEDRIG = 3;

    public int evaluateStat() {
        if (codeKorrekt == null)
            return STAT_KORREKT;
        if (codeGemeldet.getAA() == codeKorrekt.getAA())
            return STAT_PRIO_KORREKT;
        if (codeGemeldet.getAA() > codeKorrekt.getAA())
            return STAT_PRIO_ZU_HOCH;
        if (codeGemeldet.getAA() < codeKorrekt.getAA())
            return STAT_PRIO_ZU_NIEDRIG;
        return -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
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
