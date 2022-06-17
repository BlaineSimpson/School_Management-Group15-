/*
CountryFactory.java
Author: Demi Farquhar (220322104)
Date: 13 June 2022
 */
package za.ac.cput.factory;

import za.ac.cput.domain.Country;
import za.ac.cput.util.Helper;

public class CountryFactory {

    public static Country createCountry (String id, String name){
        Helper.checkStringParam(" Country Id",id);
        Helper.checkStringParam("Country Name", name);

        return new Country.Builder().setId(id).SetName(name)
                .build();
    }
}
