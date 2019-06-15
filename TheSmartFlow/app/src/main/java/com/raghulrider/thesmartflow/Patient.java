package com.raghulrider.thesmartflow;

public class Patient {
    private String PatientID;
    private String Pin;
    private String Email;
    private String Username;
    private String Basal;
    private String Bolus;


    public Patient() {
    }

    public Patient(String patientID, String pin, String email, String username, String basal, String bolus) {
        PatientID = patientID;
        Pin = pin;
        Email = email;
        Username = username;
        Basal = basal;
        Bolus = bolus;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }

    public String getPin() {
        return Pin;
    }

    public void setPin(String pin) {
        Pin = pin;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getBasal() {
        return Basal;
    }

    public void setBasal(String basal) {
        Basal = basal;
    }

    public String getBolus() {
        return Bolus;
    }

    public void setBolus(String bolus) {
        Bolus = bolus;
    }
}