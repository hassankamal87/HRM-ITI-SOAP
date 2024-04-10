package com.soap_hrm.business.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.soap_hrm.persistence.entities.Address}
 */
public class AddressDto implements Serializable {
    private Integer id;
    @NotNull
    @Size(max = 25)
    private String country;
    @NotNull
    @Size(max = 25)
    private String city;
    @Size(max = 45)
    private String streetName;

    public AddressDto() {
    }

    public AddressDto(Integer id, String country, String city, String streetName) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.streetName = streetName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto entity = (AddressDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.country, entity.country) &&
                Objects.equals(this.city, entity.city) &&
                Objects.equals(this.streetName, entity.streetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, streetName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "country = " + country + ", " +
                "city = " + city + ", " +
                "streetName = " + streetName + ")";
    }
}