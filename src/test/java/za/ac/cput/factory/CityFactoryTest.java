package za.ac.cput.factory;

/*
Author: Shuaib Allie (217148867)
 */

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.City;
import za.ac.cput.domain.Country;

import static org.junit.jupiter.api.Assertions.*;

class CityFactoryTest {
    Country country = CountryFactory.createCountry("5A", "South Africa");
    @Test
    public void CreateCityWithAllValues() {
        City city = CityFactory.createCity("5A", "Cape Town", country);
        System.out.println("Create City With All Values");
        System.out.println(city);
        assertNotNull(city);
        System.out.println();
    }
    @Test
    //Test when mandatory attribute is empty
    public void CityError(){
        Exception exc=assertThrows(IllegalArgumentException.class, ()-> CityFactory
                .createCity("","Cape Town", country));
        String exceptionMessage= exc.getMessage();
        System.out.println(exceptionMessage);
        assertNotSame("City Id required",exceptionMessage);
        System.out.println();
    }
}