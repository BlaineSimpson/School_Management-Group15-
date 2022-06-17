package za.ac.cput.factory;

/*
Author: Shuaib Allie (217148867)
 */

import za.ac.cput.domain.Employee;
import za.ac.cput.domain.Name;
import za.ac.cput.util.Helper;

public class EmployeeFactory {
    public static Employee createEmployee(String staffId, String email, Name name){
        if(Helper.stringIsNull(staffId) || Helper.nameIsNull(name))
            throw new IllegalArgumentException("ID or Name is empty");
        if(!Helper.emailValid(email))
            throw new IllegalArgumentException("Invalid Email!");

        return new Employee.Builder()
                .setStaffId(staffId)
                .setEmail(email)
                .setName(name)
                .build();
    }
}
