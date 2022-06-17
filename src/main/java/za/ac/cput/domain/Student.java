/* Student.java
 Domain for the Student class
 Author: Ali Mohamed (219113505)
 Date: 11 June 2022
*/

package za.ac.cput.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Student implements Serializable {
    @NotNull
    @Id
    private String studentId;
    @NotNull
    private String email;
    @NotNull
    @Embedded
    private Name name;

    protected Student(){

    }

    private Student(Builder builder){
        this.studentId = builder.studentId;
        this.email = builder.email;
        this.name = builder.name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getEmail() {
        return email;
    }

    public Name getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", email='" + email + '\'' +
                ", name=" + name +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId.equals(student.studentId) && email.equals(student.email) && name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, email, name);
    }

    public static class Builder{
        private String studentId;
        private String email;
        private Name name;

        public Builder setStudentId(String studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setName(Name name) {
            this.name = name;
            return this;
        }

        public Builder copy(Student student){
            this.studentId = student.studentId;
            this.email = student.email;
            this.name = student.name;
            return this;
        }

        public Student build(){
            return new Student(this);
        }
    }
}
