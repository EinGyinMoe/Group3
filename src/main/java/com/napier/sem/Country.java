package com.napier.sem;

/**
 * Represents an country
 */
public class Country {
    /**
     * Code
     */
    public String Code;

    public void setCode(String code) {
        Code = code;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setContinent(String continent) {
        Continent = continent;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public void setSurfaceArea(float surfaceArea) {
        SurfaceArea = surfaceArea;
    }

    public void setIndepYear(int indepYear) {
        IndepYear = indepYear;
    }

    public void setPopulation(int population) {
        Population = population;
    }

    public void setLifeExpectancy(float lifeExpectancy) {
        LifeExpectancy = lifeExpectancy;
    }

    public void setGNP(float GNP) {
        this.GNP = GNP;
    }

    public void setGNPOld(float GNPOld) {
        this.GNPOld = GNPOld;
    }

    public void setLocalName(String localName) {
        LocalName = localName;
    }

    public void setGovernmentForm(String governmentForm) {
        GovernmentForm = governmentForm;
    }

    public void setHeadOfState(String headOfState) {
        HeadOfState = headOfState;
    }

    public void setCapital(int capital) {
        Capital = capital;
    }

    public void setCode2(String code2) {
        Code2 = code2;
    }

    /**
     * Country's Name
     */
    public String Name;

    /**
     * Country's Continent
     */
    public String Continent;

    /**
     * Region
     */
    public String Region;

    /**
     * City's surface area
     */
    public float SurfaceArea;

    /**
     * City's IndepYear
     */
    public int IndepYear;

    public int Population;

    public float LifeExpectancy;

    public float GNP;

    public float GNPOld;

    public String LocalName;

    public String GovernmentForm;

    public String HeadOfState;

    public int Capital;

    public String Code2;
}