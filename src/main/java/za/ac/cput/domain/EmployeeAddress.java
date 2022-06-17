package za.ac.cput.domain;

/*
Author: Blaine Simpson
Student Nr: 218020171
 */


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Entity
public class EmployeeAddress {

    @NotNull
    @Id
    private String staffId;
    private Address address;



    private EmployeeAddress(Builder builder){
      this.staffId= builder.staffId;
      this.address= builder.address;

    }

    protected EmployeeAddress() {

    }

    public String getStaffId(){return staffId;}

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o){

        if (this == o) return true;

        if (o == null || getClass() !=  o.getClass()) return false;
        EmployeeAddress employeeAddress = (EmployeeAddress)  o;
        return staffId.equals(employeeAddress.staffId) && Objects.equals(address, employeeAddress.address);

    }
    @Override
    public int hashCode() {return  Objects.hash(staffId,address);
    }
    @Override
    public String toString() {
        return "EmployeeAddress{" +
                "staffId='" + staffId + '\'' +
                ", address=" + address +
                '}';
    }



public static class Builder {
    private  String staffId;
    private  Address address;

    public Builder setStaffId(String staffId) {

        this.staffId = staffId;
        return this;
    }
    public Builder setAddress(Address address) {

        this.address = address;
        return this;
    }

    public Builder copy (EmployeeAddress employeeAddress){

        this.staffId = employeeAddress.staffId;
        this.address = employeeAddress.address;
        return  this;

    }
    public EmployeeAddress build() {

        return new EmployeeAddress(this);}
}
    }
