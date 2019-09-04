package com.napier.sem;


public class Population {

    private City city;
    private Language language;
    private Country country;
    private String name;
    private long totalPopulation;
    private long PopulationinCity;
    private float PercentinCity;
    private long PopulationNotinCity;
    private float PercentNotinCity;
    private float PopulationPercent;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }


    public void setCity(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public long getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(long totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    public long getPopulationinCity() {
        return PopulationinCity;
    }

    public void setPopulationinCity(long populationinCity) {
        PopulationinCity = populationinCity;
    }

    public float getPercentinCity() {
        return PercentinCity;
    }

    public void setPercentinCity(float percentinCity) {
        PercentinCity = percentinCity;
    }

    public long getPopulationNotinCity() {
        return PopulationNotinCity;
    }

    public void setPopulationNotinCity(long populationNotinCity) {
        PopulationNotinCity = populationNotinCity;
    }

    public float getPercentNotinCity() {
        return PercentNotinCity;
    }

    public void setPercentNotinCity(float percentNotinCity) {
        PercentNotinCity = percentNotinCity;
    }

    public float getPopulationPercent() {
        return PopulationPercent;
    }

    public void setPopulationPercent(float populationPercent) {
        PopulationPercent = populationPercent;
    }


//    public static String getPopulationFormat() {
//        return "%-30.25s  %-30.25s  %-30s %-30.25s  %-30.25s  %-30s";
//    }
//
//    public static void printPopulation() {
//        System.out.println("\n");
//        System.out.printf(Population.getPopulationFormat(), "total_population", "population_in_city", "percent_in_city","population_not_in_city","percent_not_in_city","population_percent");
//        System.out.println("\n");
//    }
}
