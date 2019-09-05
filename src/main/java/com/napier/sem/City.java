package com.napier.sem;

/**
 * Represents an city
 */
public class City
{
    private int ID;

    private String Name;

    private Country country;

    private String District;

    private Long Population;

    private String CountryCode;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public void setPopulation(Long population) {
        Population = population;
    }

    public String getName() {
        return Name;
    }

    public Country getCountry() {
        return country;
    }

    public String getDistrict() {
        return District;
    }

    public Long getPopulation() {
        return Population;
    }


    public void setCountryCode(String test) {
    }

    public void getCountryCode() {
    }
}