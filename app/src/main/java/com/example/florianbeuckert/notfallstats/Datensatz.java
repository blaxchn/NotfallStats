package com.example.florianbeuckert.notfallstats;

import java.util.Date;

public class Datensatz {

    private int id;
    private Date datum;
    private Einsatzcode codeGemeldet;
    private Einsatzcode codeRichtig;
    private String bemerkung;
    private String kommentar;

    public Datensatz() {
    }

    public Datensatz(int id, Date datum, Einsatzcode codeGemeldet, Einsatzcode codeRichtig, String bemerkung, String kommentar) {
        this.id = id;
        this.datum = datum;
        this.codeGemeldet = codeGemeldet;
        this.codeRichtig = codeRichtig;
        this.bemerkung = bemerkung;
        this.kommentar = kommentar;
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

    public Einsatzcode getCodeRichtig() {
        return codeRichtig;
    }

    public void setCodeRichtig(Einsatzcode codeRichtig) {
        this.codeRichtig = codeRichtig;
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
