package za.ac.cput.factory;
/*ADP 3 Assignment 2
Author : Sinovuyo Mlanjeni(219220387)
 */

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.City;
import za.ac.cput.domain.Country;
import za.ac.cput.domain.StudentAddress;

import static org.junit.jupiter.api.Assertions.*;

class StudentAddressFactoryTest {

    Country country = CountryFactory.createCountry("5A", "James");
    City city = CityFactory.createCity("5A", "James", country);
    Address address = AddressFactory.createAddress("2", "ATB", "5", "John", 542,  city);

    @Test
    public void createStudentAddress() {

        StudentAddress sAddress = StudentAddressFactory.createStudentAddress("B214", address);
        System.out.println(sAddress);
        assertNotNull(sAddress);
        System.out.println();
    }

    @Test
        //this is the test when mandatory is empty
    void StudentAddressError() {
        Exception ex = assertThrows(IllegalArgumentException.class, ()-> StudentAddressFactory.createStudentAddress("B009", address));
        String exceptionMessage = ex.getMessage();
        System.out.println(exceptionMessage);
        assertNotSame("Address is needed",exceptionMessage);
        System.out.println();

    }


}