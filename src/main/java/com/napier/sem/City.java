package com.napier.sem;

/**
 * Represents an city
 */

public class City
{
    private int ID;

    public String Name;

    private Country country;

    private String District;

    public int Population;

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

    public void setPopulation(int population) {
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

    public int getPopulation() {
        return Population;
    }
}