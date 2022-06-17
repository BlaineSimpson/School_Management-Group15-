package za.ac.cput.impl;
/*
Author: Blaine Simpson
Student Nr: 218020171
 */
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.City;
import za.ac.cput.domain.Country;
import za.ac.cput.domain.EmployeeAddress;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.CityFactory;
import za.ac.cput.factory.CountryFactory;
import za.ac.cput.factory.EmployeeAddressFactory;
import za.ac.cput.service.EmployeeAddressService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeAddressServiceImplTest {
    Country country = CountryFactory.createCountry("BDS", "Sout Africa");
    City city = CityFactory.createCity( "A", "Lee",country);
    Address address = AddressFactory.createAddress("4", "skyway","5","AfricaSTR",6850,city);
    EmployeeAddress employeeAddress = EmployeeAddressFactory.createEmployeeAddress("1", address );

    private EmployeeAddressService service;

    @Test
    @Order(1)
    void save() {
        EmployeeAddress saved = this.service.save(this.employeeAddress);
        assertNotNull(saved);
        System.out.println(saved);

    }
    @Test
    @Order(2)
    void findById() {
        Optional<EmployeeAddress> read = this.service.findById(this.employeeAddress.getStaffId());
        assertAll(
                () -> assertTrue(read.isPresent()),
                () -> assertEquals(this.employeeAddress, read.get())
        );
    }
      @Test
      @Order(3)
      void findAll() {
        List<EmployeeAddress> list =this.service.findAll();
          System.out.println(list);
    }



        @Test
        @Order(4)
        void delete() {
            service.delete(employeeAddress);
            List<EmployeeAddress> list= this.service.findAll();
            System.out.println(list);

        }

    }





