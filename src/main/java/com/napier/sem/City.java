
//CITY.java

package com.napier.sem;

/**
 * Represents an city
 */
public class City
{
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private String CountryCode;

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    private String District;

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    private int Population;

    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int population) {
        Population = population;
    }

}