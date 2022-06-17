package za.ac.cput.domain;

/*
Author: Shuaib Allie (217148867)
 */

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Entity
public class City {
    @Id
    private String id;
    @NotNull
    private String name;

    @NotNull
    @ManyToOne(targetEntity = Country.class, cascade = CascadeType.ALL)
    private Country country;


    protected City(){

    }
    private City(Builder builder){
        this.id=builder.id;
        this.name=builder.name;
        this.country=builder.Country;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country getCountry(){
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id.equals(city.id) && name.equals(city.name) && Objects.equals(country, city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
    public static class Builder{
        private String id;
        private String name;

        private Country Country;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCountry(Country country){
            this.Country = country;
            return this;
        }
        public Builder copy(City city ){
            this.id= city.id;
            this.name= city.name;
            this.Country = city.country;
            return this;
        }
        public City build(){
            return new City(this);
        }
    }

}
