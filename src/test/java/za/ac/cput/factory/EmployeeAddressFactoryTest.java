package za.ac.cput.factory;
/*
Author: Blaine Simpson
Student Nr: 218020171
 */

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.City;
import za.ac.cput.domain.Country;
import za.ac.cput.domain.EmployeeAddress;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeAddressFactoryTest {
    Country country = CountryFactory.createCountry("ABC", "Japan");
    City city = CityFactory.createCity( "A", "Lee",country);

   Address address = AddressFactory.createAddress("1", "skyway","2","AfricaSTR",6850,city);


    @Test
  public  void createEmployeeAddress() {

        EmployeeAddress employeeAddress = EmployeeAddressFactory.createEmployeeAddress("2", address );
        System.out.println(employeeAddress);
        assertNotNull(employeeAddress);
        System.out.println();    }


}