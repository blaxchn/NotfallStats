package com.example.florianbeuckert.notfallstats;

import java.util.Date;

public class Datensatz {

    private int id;
    private Date datum;
    private Einsatzcode codeGemeldet;
    private Einsatzcode codeRichtig;
    private String infoBeiFalschmeldung;
    private String bemerkungen;

    public Datensatz() {

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

    public String getInfoBeiFalschmeldung() {
        return infoBeiFalschmeldung;
    }

    public void setInfoBeiFalschmeldung(String infoBeiFalschmeldung) {
        this.infoBeiFalschmeldung = infoBeiFalschmeldung;
    }

    public String getBemerkungen() {
        return bemerkungen;
    }

    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }
}
