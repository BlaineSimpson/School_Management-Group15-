/*
NameFactoryTest.java
Author: Demi Farquhar (220322104)
Date: 13 June 2022
 */
package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Name;

import static org.junit.jupiter.api.Assertions.*;

class NameFactoryTest {

    @Test
    public void createNameWithAllValues(){
    Name name= NameFactory.createName("Demi ","Alexis","Farquhar");
    System.out.println("Create Name With All Values");
    System.out.println(name);
    assertNotNull(name);
    System.out.println();
}
@Test
//Test to create middleName with empty string
public void createNameWithMissingValues(){
  Name name= NameFactory.createName("Demi","","Farquhar");
  assertNotNull(name);
  System.out.println(name);
    System.out.println();
}
@Test
//When middleName is null then it is replaced with empty string
    public void createNameWithNull(){
    Name name= NameFactory.createName("Demi",null,"Farquhar");
    assertAll(
            () ->assertTrue(name.getMiddleName().isEmpty()),
            () ->assertNotNull(name)
    );
    System.out.println("Replace Null with empty string ");
    System.out.println(name);
    System.out.println();
}
@Test
//Test for when a mandatory attribute is empty
    public void nameError(){
    Exception exc=assertThrows(IllegalArgumentException.class,()->NameFactory
            .createName("","Alexis","Farquhar"));
    String exceptionMessage= exc.getMessage();
    System.out.println(exceptionMessage);
    assertNotSame("Invalid for param", exceptionMessage);
    System.out.println();
}


}