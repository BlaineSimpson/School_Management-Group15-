package za.ac.cput.factory;

/*
Author: Shuaib Allie (217148867)
 */

import za.ac.cput.domain.City;
import za.ac.cput.domain.Country;
import za.ac.cput.util.Helper;

public class CityFactory {

    public static City createCity (String id, String name, Country country){
        Helper.checkStringParam(" City Id",id);
        Helper.checkStringParam("City Name", name);
        Helper.countryIsNull(country);

        return new City.Builder().setId(id).setName(name).setCountry(country)
                .build();
    }
}
