package com.mohammedtawhied122.myapplication;

public class Patient {

    String patientId, patientUsername, patientPassword;

    public Patient(String patientId, String patientUsername, String patientPassword) {
        this.patientId = patientId;
        this.patientUsername = patientUsername;
        this.patientPassword = patientPassword;
    }

    public Patient(String patientUsername, String patientPassword) {
        this.patientUsername = patientUsername;
        this.patientPassword = patientPassword;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public void setPatientUsername(String patientUsername) {
        this.patientUsername = patientUsername;
    }

    public String getPatientPassword() {
        return patientPassword;
    }

    public void setPatientPassword(String patientPassword) {
        this.patientPassword = patientPassword;
    }
}
