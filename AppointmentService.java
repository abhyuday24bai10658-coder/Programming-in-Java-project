package services;

import models.Appointment;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AppointmentService {

    private ArrayList<Appointment> appointments = new ArrayList<>();

    public boolean bookAppointment(int doctorId, int patientId, LocalDateTime dateTime) {

        for (Appointment a : appointments) {
            if (a.getDoctorId() == doctorId && a.getDateTime().equals(dateTime)) {
                return false; // Conflict
            }
        }

        appointments.add(new Appointment(doctorId, patientId, dateTime));
        return true;
    }

    public ArrayList<Appointment> getAppointmentsForDoctor(int doctorId) {
        ArrayList<Appointment> list = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getDoctorId() == doctorId)
                list.add(a);
        }
        return list;
    }

    public ArrayList<Appointment> getAppointmentsForPatient(int patientId) {
        ArrayList<Appointment> list = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getPatientId() == patientId)
                list.add(a);
        }
        return list;
    }

    public boolean cancelAppointment(int doctorId, int patientId, LocalDateTime dateTime) {
        for (Appointment a : appointments) {
            if (a.getDoctorId() == doctorId &&
                a.getPatientId() == patientId &&
                a.getDateTime().equals(dateTime)) {

                appointments.remove(a);
                return true;
            }
        }
        return false;
    }
}
