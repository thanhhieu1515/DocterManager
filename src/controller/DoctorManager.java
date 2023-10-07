
package controller;

import common.Library;
import view.Menu;


public class DoctorManager extends Menu<String>{

    static String[] mc = {"Add Doctor", "Update Doctor", "Delete Doctor", "Search Doctor","Exit"};
    Library library = new Library();
    
    public DoctorManager(){
        super("Doctor management", mc);
    }
    @Override
    public void executed(int n) {
        switch (n) {
            case 1:
                library.addDoctor();
                break;
            case 2:
                library.updateDoctor();
                break;
            case 3:
                library.deleteDoctor();
                break;
            case 4:
                library.searchDoctor();
                break;
            case 5:
                System.out.println("End program");
                System.exit(0);
            default:
                throw new AssertionError();
        }
    }
    
}
