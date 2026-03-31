package models;

import java.time.LocalDateTime;

public class Appointment {
    private int doctorId;
    private int patientId;
    private LocalDateTime dateTime;

    public Appointment(int doctorId, int patientId, LocalDateTime dateTime) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.dateTime = dateTime;
    }

    public int getDoctorId() {
        return doctorId;
    }
    public int getPatientId() {
        return patientId;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Doctor ID: " + doctorId + ", Patient ID: " + patientId + ", Date: " + dateTime;
    }
}
