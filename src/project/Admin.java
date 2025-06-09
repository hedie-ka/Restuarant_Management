package project;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private static List<Admin> admins = new ArrayList<Admin>();

    static {
        Admin admin1 = new Admin("Jack", "22004", "J004", "09109738597");
        admins.add(admin1);
        Admin admin2 = new Admin("Ted", "22006", "T006", "09188625307");
        admins.add(admin2);
        Admin admin3 = new Admin("Marry", "22008", "M008", "09184360736");
        admins.add(admin3);
    }

    public Admin(String name, String ID, String password, String phone) {
        super(name, ID, password, phone);
    }

    public static Admin getAdminByID(String ID) {
        for (Admin admin : admins) {
            if (admin.ID.equals(ID))
                return admin;
        }
        return null;
    }

    public static boolean isExistAdmin(String ID) {
        for (Admin admin : admins) {
            if (admin.ID.equals(ID))
                return true;
        }
        return false;
    }

}
