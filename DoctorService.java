package services;

import models.Doctor;
import java.util.ArrayList;

public class DoctorService {

    private ArrayList<Doctor> doctors = new ArrayList<>();

    public void addDoctor(Doctor d) {
        doctors.add(d);
    }

    public ArrayList<Doctor> getAllDoctors() {
        return doctors;
    }

    public Doctor getDoctorById(int id) {
        for (Doctor d : doctors) {
            if (d.getId() == id)
                return d;
        }
        return null;
    }

    public boolean isDoctorListEmpty() {
        return doctors.isEmpty();
    }
}
