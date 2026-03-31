package services;

import models.Patient;
import java.util.ArrayList;

public class PatientService {

    private ArrayList<Patient> patients = new ArrayList<>();

    public void addPatient(Patient p) {
        patients.add(p);
    }

    public ArrayList<Patient> getAllPatients() {
        return patients;
    }

    public Patient getPatientById(int id) {
        for (Patient p : patients) {
            if (p.getId() == id)
                return p;
        }
        return null;
    }

    public boolean isPatientListEmpty() {
        return patients.isEmpty();
    }
}
