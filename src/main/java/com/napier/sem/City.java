package com.napier.sem;

/**
 * Represents an city
 */
public class City
{
    /**
     * City ID
     */
    public int ID;

    /**
     * City's Name
     */
    public String Name;

    /**
     * Country's code
     */
    public Country country;

    /**
     * District
     */
    public String District;

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

    /**
     * City's population
     */
    public int Population;
}