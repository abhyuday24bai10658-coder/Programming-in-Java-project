package main;

import models.Doctor;
import models.Patient;
import models.Appointment;
import services.DoctorService;
import services.PatientService;
import services.AppointmentService;
import utils.InputHelper;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Main {

    private static DoctorService doctorService = new DoctorService();
    private static PatientService patientService = new PatientService();
    private static AppointmentService appointmentService = new AppointmentService();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            printMenu();
            choice = InputHelper.readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1 -> addDoctor(sc);
                case 2 -> addPatient(sc);
                case 3 -> listDoctors();
                case 4 -> listPatients();
                case 5 -> bookAppointment(sc);
                case 6 -> viewDoctorSchedule(sc);
                case 7 -> viewPatientAppointments(sc);
                case 8 -> cancelAppointment(sc);
                case 9 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid option. Try again.");
            }

        } while (choice != 9);

        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n===== Appointment Scheduling System =====");
        System.out.println("1. Add Doctor");
        System.out.println("2. Add Patient");
        System.out.println("3. List All Doctors");
        System.out.println("4. List All Patients");
        System.out.println("5. Book Appointment");
        System.out.println("6. View Doctor Schedule");
        System.out.println("7. View Patient Appointments");
        System.out.println("8. Cancel Appointment");
        System.out.println("9. Exit");
    }

    private static void addDoctor(Scanner sc) {
        int id = InputHelper.readInt(sc, "Enter Doctor ID: ");
        String name = InputHelper.readString(sc, "Enter Doctor Name: ");
        String specialization = InputHelper.readString(sc, "Enter Specialization: ");

        doctorService.addDoctor(new Doctor(id, name, specialization));
    }

    private static void addPatient(Scanner sc) {
        int id = InputHelper.readInt(sc, "Enter Patient ID: ");
        String name = InputHelper.readString(sc, "Enter Patient Name: ");
        int age = InputHelper.readInt(sc, "Enter Age: ");

        patientService.addPatient(new Patient(id, name, age));
    }

    private static void listDoctors() {
        if (doctorService.isDoctorListEmpty()) {
            System.out.println("No doctors available.");
            return;
        }
        for (Doctor d : doctorService.getAllDoctors()) {
            System.out.println(d);
        }
    }

    private static void listPatients() {
        if (patientService.isPatientListEmpty()) {
            System.out.println("No patients available.");
            return;
        }
        for (Patient p : patientService.getAllPatients()) {
            System.out.println(p);
        }
    }

    private static void bookAppointment(Scanner sc) {
        if (doctorService.isDoctorListEmpty() || patientService.isPatientListEmpty()) {
            System.out.println("Add doctors and patients before booking.");
            return;
        }

        int docId = InputHelper.readInt(sc, "Enter Doctor ID: ");
        Doctor d = doctorService.getDoctorById(docId);
        if (d == null) {
            System.out.println("Doctor not found.");
            return;
        }

        int patId = InputHelper.readInt(sc, "Enter Patient ID: ");
        Patient p = patientService.getPatientById(patId);
        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }

        LocalDateTime dt = InputHelper.readDateTime(sc, "Enter appointment date/time");
        boolean ok = appointmentService.bookAppointment(docId, patId, dt);

        if (ok) System.out.println("Appointment booked!");
        else System.out.println("Doctor is already booked at that time.");
    }

    private static void viewDoctorSchedule(Scanner sc) {
        int id = InputHelper.readInt(sc, "Doctor ID: ");
        ArrayList<Appointment> list = appointmentService.getAppointmentsForDoctor(id);
        if (list.isEmpty()) System.out.println("No appointments.");
        else list.forEach(System.out::println);
    }

    private static void viewPatientAppointments(Scanner sc) {
        int id = InputHelper.readInt(sc, "Patient ID: ");
        ArrayList<Appointment> list = appointmentService.getAppointmentsForPatient(id);
        if (list.isEmpty()) System.out.println("No appointments.");
        else list.forEach(System.out::println);
    }

    private static void cancelAppointment(Scanner sc) {
        int docId = InputHelper.readInt(sc, "Doctor ID: ");
        int patId = InputHelper.readInt(sc, "Patient ID: ");
        LocalDateTime dt = InputHelper.readDateTime(sc, "Enter date/time to cancel");

        if (appointmentService.cancelAppointment(docId, patId, dt))
            System.out.println("Cancelled.");
        else
            System.out.println("Appointment not found.");
    }
}
