package za.ac.cput.domain;

/*
Author: Shuaib Allie (217148867)
 */

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class Employee

{
    @NotNull
    @Id
    private String staffId;

    @NotNull
    private String email;

    @Embedded
    @NotNull
    private Name name;


    protected Employee(){
    }




    private Employee(Builder builder){
        this.staffId=builder.staffId;
        this.email=builder.email;
        this.name=builder.name;


    }

    public String getStaffId(){
        return staffId;
    }

    public String getEmail(){
        return email;
    }

    public Name getName(){
        return name;
    }




    @Override
    public String toString(){
        return "Employee{" +
                "Staff ID='" + staffId + '\'' +
                ", Email ='" + email + '\'' +
                ", Name ='" + name + '\'' +
                '}';
    }

    public static class Builder{
        private String staffId;

        private String email;

        private Name name;



        public Builder setStaffId(String staffId){
            this.staffId = staffId;
            return this;
        }

        public Builder setEmail(String email){
            this.email = email;
            return this;
        }

        public Builder setName(Name name){
            this.name = name;
            return this;
        }





        public Builder copy (Employee employee){
            this.staffId = employee.staffId;
            this.email = employee.email;
            this.name = employee.name;
            return this;
        }
        public Employee build(){
            return new Employee(this);
        }
    }

}
