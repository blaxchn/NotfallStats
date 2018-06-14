package com.example.florianbeuckert.notfallstats.Data;

import java.util.List;

public class Dataset {

    private int id;
    private String date;
    private EmergencyCode codeReported;
    private EmergencyCode codeActual;
    private String annotation;
    private String comment;

    public Dataset() {
    }

    public Dataset(int id, String date, EmergencyCode codeReported, EmergencyCode codeActual, String annotation, String comment) {
        this.id = id;
        this.date = date;
        this.codeReported = codeReported;
        this.codeActual = codeActual;
        this.annotation = annotation;
        this.comment = comment;
    }

    public static final int STAT_CORRECT = 0;
    public static final int STAT_PRIORITY_ACCURATE = 1;
    public static final int STAT_PRIORITY_TOO_HIGH = 2;
    public static final int STAT_PRIORITY_TOO_LOW = 3;
    public static final int STAT_N_A = 4;

    public int evaluateStat() {
        if (codeActual.getAA() == -1 && annotation.equals("(keine Bemerkungen)"))
            return STAT_CORRECT;
        if (codeActual.getAA() == -1)
            return STAT_N_A;
        if (codeReported.getAA() == codeActual.getAA())
            return STAT_PRIORITY_ACCURATE;
        if (codeReported.getAA() < codeActual.getAA())
            return STAT_PRIORITY_TOO_HIGH;
        if (codeReported.getAA() > codeActual.getAA())
            return STAT_PRIORITY_TOO_LOW;
        return -1;
    }

    public static int[] getStatArray(List<Dataset> data) {
        int[] statCounter = new int[]{0, 0, 0, 0, 0};

        for (Dataset d : data) {
            statCounter[d.evaluateStat()]++;
        }

        int[] stats = new int[]{0, 0, 0, 0};
        int totalSize = data.size();
        if (totalSize > 0) {
            for (int i = 0; i < stats.length; i++) {
                stats[i] = (100 * statCounter[i]) / totalSize;
            }
        }

        return stats;
    }

    public static EmergencyCode stringToEmergencyCode(String codeAsString) {
        try {
            int aa = Integer.parseInt(codeAsString.substring(0, 2));
            int bb = Integer.parseInt(codeAsString.substring(3, 5));

            boolean n = false;
            if (codeAsString.length() == 7)
                n = true;

            return new EmergencyCode(aa, bb, n);
        } catch(Exception e) {
            return new EmergencyCode();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public EmergencyCode getCodeReported() {
        return codeReported;
    }

    public void setCodeReported(EmergencyCode codeReported) {
        this.codeReported = codeReported;
    }

    public EmergencyCode getCodeActual() {
        return codeActual;
    }

    public void setCodeActual(EmergencyCode codeActual) {
        this.codeActual = codeActual;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
