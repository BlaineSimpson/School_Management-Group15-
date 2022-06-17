package za.ac.cput.factory;

/*
Author: Blaine Simpson
Student Nr: 218020171
 */

import za.ac.cput.domain.Address;
import za.ac.cput.domain.EmployeeAddress;
import za.ac.cput.util.Helper;

public class EmployeeAddressFactory {


 public static EmployeeAddress createEmployeeAddress( String staffId, Address address){
        Helper.checkStringParam("EmployeeAddress ", staffId);
        Helper.addressIsNull(address);

    return new EmployeeAddress.Builder().setStaffId(staffId).setAddress(address).build();


 }
}
