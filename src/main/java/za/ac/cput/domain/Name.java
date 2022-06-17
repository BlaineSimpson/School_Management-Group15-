/*
Name.java
Author: Demi Farquhar (220322104)
Date: 11 June 2022
 */
package za.ac.cput.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable
public class Name {
  @NotNull
  private String firstName;
  private String middleName;
  @NotNull
  private String lastName;

    //constructor
    protected Name(){

    }

    private Name(Builder builder ){
        this.firstName=builder.firstName;
        this.middleName=builder.middleName;
        this.lastName=builder.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    //Added setters to fix Getter errors ?
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return firstName.equals(name.firstName) && middleName.equals(name.middleName) && lastName.equals(name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName);
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
    public static class Builder{
        private String firstName;
        private String middleName;
        private String lastName;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder copy(Name name){
            this.firstName=name.firstName;
            this.middleName=name.middleName;
            this.lastName=name.lastName;
            return this;
        }
        public Name build(){
            return new Name(this);
        }
    }
}
