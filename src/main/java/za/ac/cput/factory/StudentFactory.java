/* StudentFactory.java
 Factory for the Student class
 Author: Ali Mohamed (219113505)
 Date: 11 June 2022
*/
package za.ac.cput.factory;

import za.ac.cput.domain.Name;
import za.ac.cput.domain.Student;
import za.ac.cput.util.Helper;

public class StudentFactory {

    public static Student createStudent(String studentId, String email, Name name){
       if(Helper.stringIsNull(studentId) || Helper.stringIsNull(email) || Helper.nameIsNull(name)) //check if string and name is null
           throw new IllegalArgumentException("ID, Email and Name cannot be empty or null");
       if(!Helper.emailValid(email)) //if email is not valid, throw exception
           throw new IllegalArgumentException("Invalid Email!");

       return new Student.Builder()
               .setStudentId(studentId)
               .setEmail(email)
               .setName(name)
               .build();
    }
}
