/*
CountryFactoryTest.java
Author: Demi Farquhar (220322104)
Date: 13 June 2022
 */
package za.ac.cput.factory;


import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Country;

import static org.junit.jupiter.api.Assertions.*;


class CountryFactoryTest {
    @Test
    //build a country
    public void CreateCountryWithAllValues() {
        Country country = CountryFactory.createCountry("01A", "Sweden");
        System.out.println("Create Country With All Values");
        System.out.println(country);
        assertNotNull(country);
        System.out.println();
    }
    @Test
    //Test when mandatory attribute is empty
    public void CountryError(){
        Exception exc=assertThrows(IllegalArgumentException.class, ()-> CountryFactory
                .createCountry("","Sweden"));
        String exceptionMessage= exc.getMessage();
        System.out.println(exceptionMessage);
        assertNotSame("Country Id required",exceptionMessage);
        System.out.println();
    }
}