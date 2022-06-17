//Author: Craig Jarvis

package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.City;
import za.ac.cput.util.Helper;

public class AddressFactory {
    public static Address createAddress (String unitNumber, String complexName, String streetNumber, String streetName, int postalCode, City city) {
        Helper.checkStringParam("Unit Number",unitNumber);
        Helper.checkStringParam("Complex Name", complexName);
        Helper.checkStringParam("Street Number",streetNumber);
        Helper.checkStringParam("Street Name",streetName);


        return new Address.Builder()
                .setUnitNumber(unitNumber)
                .setComplexName(complexName)
                .setStreetNumber(streetNumber)
                .setStreetName(streetName)
                .setPostalCode(postalCode)
                .setCity(city)
                .build();
    }
}
