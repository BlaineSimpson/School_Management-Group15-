package za.ac.cput.factory;
/*
This is term two group assignment
Author: Sinovuyo Mlanjeni (219220387)
 */

import za.ac.cput.domain.Address;
import za.ac.cput.domain.StudentAddress;
import za.ac.cput.util.Helper;

public class StudentAddressFactory {
    public static StudentAddress createStudentAddress(String studentId, Address address){

        Helper.checkStringParam("StudentAddress StudentId", studentId);
        Helper.addressIsNull(address);

        return new StudentAddress.Builder().setStudentId(studentId).setAddress(address)
                .build();

    }


}
