//Author: Craig Jarvis

package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.City;
import za.ac.cput.domain.Country;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddressFactoryTest {

//    String unitNumber = "5A";
//    String complexName = "Bridges Complex";
//    String streetNumber = "17";
//    String streetName = "Foxglove Street";
//    int postalCode = 1701;
//    City city = "Cape Town";
    Country country = CountryFactory.createCountry("1", "South Africa");
    City city = CityFactory.createCity("1","Cape Town", country);
    Address address = AddressFactory.createAddress("5A", "Bridges Complex", "17", "Foxglove Street", 1701, city );

    @Test
    void notNull(){
        Address address = AddressFactory.createAddress("5A", "Bridges Complex", "17", "Foxglove Street", 1701, city );
        assertNotNull(address);
    }

    @Test
    void unitNumberNullException(){
        assertThrows(IllegalArgumentException.class, () -> AddressFactory.createAddress(null, "Bridges Complex", "17", "Foxglove Street", 1701, city ));
    }

    @Test
    void complexNameNullException(){
        assertThrows(IllegalArgumentException.class, () -> AddressFactory.createAddress("5A", null, "17", "Foxglove Street", 1701, city ));
    }

    @Test
    void streetNumberNullException() {
        assertThrows(IllegalArgumentException.class, () -> AddressFactory.createAddress("5A", "Bridges Complex", null, "Foxglove Street", 1701, city ));
    }

    @Test
    void streetNameNullException(){
        assertThrows(IllegalArgumentException.class, () -> AddressFactory.createAddress("5A", "Bridges Complex", "17", null, 1701, city ));
    }

    @Test
    void postalCodeNullException(){
        assertThrows(IllegalArgumentException.class, () -> AddressFactory.createAddress("5A", "Bridges Complex", "17", "Foxglove Street", 1701, city ));
    }

    @Test
    void cityNullException(){
        assertThrows(IllegalArgumentException.class, () -> AddressFactory.createAddress("5A", "Bridges Complex", "17", "Foxglove Street", 1701, null ));
    }

    }