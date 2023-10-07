
package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import model.Doctor;


public class Library {

    HashMap<String, Doctor> hs = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public String getValue(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    public int getIntForMenu(String msg, int min, int max) {
        int a = -1;
        while (true) {
            System.out.print(msg);
            try {
                a = Integer.parseInt(sc.nextLine());
                if (a >= min && a <= max) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please enter a number between " + min + " and " + max);
            }
        }
        return a;
    }

    static public int getInteger(String msg) {
        int a = -1;
        while (true) {
            System.out.println(msg);
            try {
                a = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Only input number");
                continue;
            }
            break;
        }
        return a;
    }
    static Scanner in = new Scanner(System.in);
    //check user input string

    public String checkInputString() {
        //loop until user input correct
        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public boolean checkInputYN() {
        //loop until user input correct
        while (true) {
            String result = checkInputString();
            //return true if user input y/Y
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            //return false if user input n/N
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }

    public boolean checkDuplicate(String code,
            String name, String specialization, int availability) {
        //check from first to last list doctor
        for (Doctor doctor : hs.values()) {
            if (code.equalsIgnoreCase(doctor.getCode())
                    && name.equalsIgnoreCase(doctor.getName())
                    && specialization.equalsIgnoreCase(doctor.getSpecialization())
                    && availability == doctor.getAvailability()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkChangeInfo(Doctor doctor, String code,
            String name, String specialization, int availability) {
        if (doctor.getCode().equalsIgnoreCase(code)
                && doctor.getName().equalsIgnoreCase(name)
                && doctor.getSpecialization().equalsIgnoreCase(specialization)
                && doctor.getAvailability() == availability) {
            return false;
        }
        return true;
    }

    public void addDoctor() {
        System.out.print("Enter code: ");
        String code = checkInputString();
        //check code exist or not

        System.out.print("Enter name: ");
        String name = checkInputString();
        System.out.print("Enter specialization: ");
        String specialization = checkInputString();
        int availability = getInteger("Enter availabillity");
        //check worker duplicate
        Doctor dr = new Doctor(code, name, specialization, availability);
        if (!checkDuplicate(code, name, specialization, availability)) {
            System.err.println("Duplicate.");
            return;
        } else {

            hs.put(code, dr);
            System.err.println("Add successful.");

        }

    }

    public Doctor getDoctorByCode(String code) {
        for (Doctor doctor : hs.values()) {
            if (doctor.getCode().equalsIgnoreCase(code)) {
                return doctor;
            }
        }
        return null;
    }

    public void updateDoctor() {
        System.out.print("Enter code: ");
        String code = checkInputString();
        //check code exist or not

        System.out.print("Enter code to Update: ");
        String codeUpdate = checkInputString();
        Doctor doctor = getDoctorByCode(code);
        System.out.print("Enter name: ");
        String name = checkInputString();
        System.out.print("Enter specialization: ");
        String specialization = checkInputString();
        int availability = getInteger("Enter avaiability: ");
        //check user change infomation or not
        if (!checkChangeInfo(doctor, code, name, specialization, availability)) {
            System.err.println("No change");
            return;
        }
        doctor.setCode(codeUpdate);
        doctor.setName(name);
        doctor.setSpecialization(specialization);
        doctor.setAvailability(availability);
        System.err.println("Update successful");
    }

    //get list found by name
    public ArrayList<Doctor> listFoundByName(String name) {
        ArrayList<Doctor> listFoundByName = new ArrayList<>();

        for (Doctor doctor : hs.values()) {
            if (doctor.getName().contains(name)) {
                listFoundByName.add(doctor);
            }
        }
        return listFoundByName;
    }

    public void searchDoctor() {
        System.out.print("Enter name: ");
        String nameSearch = checkInputString();
        ArrayList<Doctor> listFoundByName = listFoundByName(nameSearch);
        if (listFoundByName.isEmpty()) {
            System.err.println("List empty.");
        } else {
            System.out.printf("%-10s%-15s%-25s%-20s\n", "Code", "Name",
                    "Specialization", "Availability");
            for (Doctor doctor : listFoundByName) {
                System.out.printf("%-10s%-15s%-25s%-20d\n", doctor.getCode(),
                        doctor.getName(), doctor.getSpecialization(),
                        doctor.getAvailability());
            }
        }
    }

    public void deleteDoctor() {
        System.out.print("Enter code: ");
        String code = checkInputString();
        Doctor doctor = getDoctorByCode(code);
        if (doctor == null) {
            System.err.println("Not found doctor.");
            return;
        } else {
            hs.remove(code);
        }
        System.err.println("Delete successful.");
    }
}
