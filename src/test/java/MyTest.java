package com.napier.sem;
//this is unit_testing ...........
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class MyTest
{

    static App app;
    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:3306");
        {
            System.out.println("\n...........................\n" +
                    "Unit_Testing For WorldFact\n" +
                    "...........................");
        }
    }

    //Country Report Option 1. All the countries in the world organised by largest population to smallest.
    @Test
    protected void  displayCountryWorldtest()
    {
        app.displayCountryWorld(null);
    }

    @Test
    protected void  displayCountryWorldNulltest()
    {   ArrayList<Country> countries =new ArrayList<>();
        countries.add(null);
        app.displayCountryWorld(countries);
    }

    //Country Report Option 2. All the countries in a continent organised by largest population to smallest.
    @Test
    protected void  displayCountryContinenttest()
    {
        app.displayCountryContinent(null);
    }

    @Test
    protected void  displayCountryContinentNulltest()
    {   ArrayList<Country> countries =new ArrayList<>();
        countries.add(null);
        app.displayCountryContinent(countries);
    }

    //Country Report Option 3. All the countries in a region organised by largest population to smallest
    @Test
    protected void  displayCountryRegiontest()
    {
        app.displayCountryRegion(null);
    }

    @Test
    protected void  displayCountryRegionNulltest()
    {   ArrayList<Country> countries =new ArrayList<>();
        countries.add(null);
        app.displayCountryRegion(countries);
    }

    //City Report Option 1. All the cities in the world organised by largest population to smallest.
    @Test
    protected void  displayCityWorldtest()
    {
        app.displayCityWorld(null);
    }

    @Test
    protected void  displayCityWorldNulltest()
    {   ArrayList<City> cities =new ArrayList<>();
        cities.add(null);
        app.displayCityWorld(cities);
    }

    //City Report Option 2. All the cities in a continent organised by largest population to smallest
    @Test
    protected void  displayCityContinenttest()
    {
        app.displayCityContinent(null);
    }

    @Test
    protected void  displayCityContinentNulltest()
    {   ArrayList<City> cities =new ArrayList<>();
        cities.add(null);
        app.displayCityContinent(cities);
    }

    //City Report Option 3. All the cities in a region organised by largest population to smallest

    @Test
    protected void  displayCityRegiontest()
    {
        app.displayCityRegion(null);
    }

    @Test
    protected void  displayCityRegionNulltest()
    {   ArrayList<City> cities =new ArrayList<>();
        cities.add(null);
        app.displayCityRegion(cities);
    }

    //City Report Option 4. All the cities in a country organised by largest population to smallest
    @Test
    protected void  displayCityCountrytest()
    {
        app.displayCityCountry(null);
    }

    @Test
    protected void  displayCityCountryNulltest()
    {   ArrayList<City> cities =new ArrayList<>();
        cities.add(null);
        app.displayCityCountry(cities);
    }

    //City Report Option 5. All the cities in a district organised by largest population to smallest
    @Test
    protected void  displayCityDistricttest()
    {
        app.displayCityDistrict(null);
    }

    @Test
    protected void  displayCityDistrictNulltest()
    {   ArrayList<City> cities =new ArrayList<>();
        cities.add(null);
        app.displayCityDistrict(cities);
    }


    //Capital City Report Option 1. All the capital cities in the world organised by largest population to smallest
    @Test
    protected void  displayCapitalCityWorldtest()
    {
        app.displayCapitalCityWorld(null);
    }

//    @Test
//    protected void  displayCapitalCityWorldNulltest()
//    {   ArrayList<City> capitalcityreport =new ArrayList<>();
//        capitalcityreport.add(null);
//        app.displayCapitalCityWorld(capitalcityreport);
//    }

    //Capital City Report Option 2. All the capital cities in a continent organised by largest population to smallest
    @Test
    protected void  displayCapitalCityContinenttest()
    {
        app.displayCapitalCityContinent(null);
    }


    //Capital City Report Option 3. All the capital cities in a region organised by largest to smallest.
    @Test
    protected void displayCapitalCityRegiontest() { app.displayCapitalCityRegion(null);}


    //Population Report Option 1. The population of the world
    @Test
    protected void  displayCountryTotalPopuWorldtest()
    {
        app.displayCountryTotalPopuWorld(null);
    }

    @Test
    protected void  displayCountryTotalPopuWorldNulltest()
    {   ArrayList<Country> countries =new ArrayList<>();
        countries.add(null);
        app.displayCountryTotalPopuWorld(countries);
    }

    //Population Report Option 7. The population of people, people living in cities, and people not living in cities in each continent
    @Test
    protected void  displayLivePopulationContinenttest()
    {
        app.displayLivePopulationContinent(null);
    }

    //Population Report Option 8. The population of people, people living in cities, and people not living in cities in each region
    @Test
    protected void  displayLivePopulationRegiontest()
    {
        app.displayLivePopulationRegion(null);
    }

    //9. The population of people, people living in cities, and people not living in cities in each country
    @Test
    protected void  displayLivePopulationCountrytest()
    {
        app.displayLivePopulationCountry(null);
    }


//    //Language Report
//    @Test
//    void testFiveLanguagePopulation()
//    {
//        ArrayList<Language> language = app.getFiveLanguagePopulation();
//        app.displayFiveLanguagePopulation(language);
//    }


    //Unit_testing for Cityreport
    @Test
    protected void  displayCityReporttest()
    {
        app.displayCityReport(null);
    }

    @Test
    protected void  displayCityReportNulltest()
    {   ArrayList<City> cities =new ArrayList<>();
        cities.add(null);
        app.displayCityReport(cities);
    }

    //Unit_testing for CountryReport
    @Test
    protected void  displayCountryReporttest()
    {
        app.displayCountry_report(null);
    }

//    @Test
//    protected void  displayCountryReportNulltest()
//    {   ArrayList<Country> countries =new ArrayList<>();
//        countries.add(null);
//        app.displayCountry_report(countries);
//    }

    //Unit_testing for CapitalCity_Report
    @Test
    protected void  displayCapitalCity_Reporttest()
    {
        app.displayCapitalCityReport(null);
    }

//    @Test
//    protected void  displayCapitalCity_ReportNulltest()
//    {   ArrayList<City> cities =new ArrayList<>();
//        cities.add(null);
//        app.displayCapitalCityReport(cities);
//    }


//For code review 4

    //Unit_testing for total country population of a region
    @Test
    protected void  displayCountryTotalPopuRegiontest()
    {
        app.displayCountryTotalPopuRegion(null);
    }

    @Test
    protected void  displayCountryTotalPopuRegionNulltest()
    {   ArrayList<Country> countries =new ArrayList<>();
        countries.add(null);
        app.displayCountryTotalPopuRegion(countries);
    }

    //Unit_testing for total country population of a region
    @Test
    protected void  displayCountryTotalPopuConttest()
    {
        app.displayCountryTotalPopuCont(null);
    }

    @Test
    protected void  displayCountryTotalPopuNulltest()
    {   ArrayList<Country> countries =new ArrayList<>();
        countries.add(null);
        app.displayCountryTotalPopuCont(countries);
    }


    //Unit_testing for total city population
    @Test
    protected void  displayCityTotalPoputest()
    {
        app.displayCityTotalPopu(null);
    }

    @Test
    protected void  displayCityTotalPopuNulltest()
    {   ArrayList<City> cities =new ArrayList<>();
        cities.add(null);
        app.displayCityTotalPopu(cities);
    }


    //    Additional Testing
    @Test
    protected void getCountryContinentNull(){
        app.getCountryContinent();
    }

    @Test
    protected void getCountryRegionNull(){
        app.getCountryRegion();
    }

    @Test
    protected void getCountryTotalPopuCont(){
        app.getCountryTotalPopuCont();
    }

    @Test
    protected void getCountryWorldNull(){
        app.getCountryWorld();
    }

    @Test
    protected void getCityContinentNull(){
        app.getCityContinent();
    }

    @Test
    protected void getCapitalCityContinentNull(){
        app.getCapitalCityContinent();
    }

    @Test
    protected void getCityTotalPopuNull(){
        app.getCityTotalPopu();
    }

    //Language Testing
    @Test
    protected void language_testing(){
        Language language =new Language();
        language.setLanguage("Unit_Testing");
        language.setPercentage((float) 0.20);
        language.getLanguage();
        language.getPercentage();
        language.toString();
        language.getIsOfficial();
        language.getCountryCode();
        language.setCountry(null);
    }

    //Country Testing
    @Test
    protected void country_testing(){
        Country country = new Country();
        country.setName("MyTest");
        country.setContinent("MyTest");
        country.setCapital (10);
        country.setRegion("Test");
        country.setPopulation((long) 1000);
        country.getCode();
        country.getName();
        country.getContinent();
        country.getRegion();
        country.getSurfaceArea();
        country.getIndepYear();
        country.getPopulation();
        country.getLifeExpectancy();
        country.getGNP();
        country.getGNPOld();
        country.getLocalName();
        country.getGovernmentForm();
        country.getHeadOfState();
        country.getCapital();
        country.getCode2();
        country.toString();
    }

    //City Testing
    @Test
    protected void city_testing(){
        Country c = new Country();
        City   city = new City();
        city.setID(1);
        city.setCountry(c);
        city.setName("Test");
        city.setCountryCode("TEST");
        city.setDistrict("Test");
        city.setPopulation((long) 1000);
        city.getID();
        city.getCountry();
        city.getName();
        city.getPopulation();
        city.getDistrict();
        city.getCountryCode();
        city.toString();
    }

    //Population Testing
    @Test
    protected void testingPopulation(){
        Country c = new Country();
        City cty = new City();
        Language lan = new Language();
        Population population = new Population();

        population.setName("test");
        population.setCountry(c);
        population.setCity(cty);
        population.setLanguage(lan);
        population.setPercentinCity(20);
        population.setPopulationinCity(200);
        population.setPopulationNotinCity(300);
        population.setPercentNotinCity(30);
        population.setTotalPopulation(400);
        population.setPopulationPercent(500);
    }

    //TO improve our code coverage
    @Test
    protected void testingCityReport(){
        displayCapitalCity_Reporttest();
        displayCapitalCityContinenttest();
        displayCapitalCityWorldtest();
        displayCityContinentNulltest();
        displayCityContinenttest();
        displayCityCountryNulltest();
        displayCityDistrictNulltest();
        displayCapitalCity_Reporttest();
        displayCityRegiontest();
    }

    @Test
    protected void testCountryWorld()
    {
        ArrayList<Country> countryworld = app.getCountryWorld();
        app.displayCountryWorld(countryworld);
    }

    @Test
    protected void testCountryReg()
    {
        ArrayList<Country> countryInReg = app.getCountryRegion();
        app.displayCountryRegion(countryInReg);
    }

    @Test
    protected void testCountryContinent()
    {
        ArrayList<Country> countrycontinent = app.getCountryContinent();
        app.displayCountryContinent(countrycontinent);
    }

    @Test
    protected void testCityWorld()
    {
        ArrayList<City> ctyworld = app.getCity_world();
        app.displayCityWorld(ctyworld);
    }

    @Test
    protected void testCityContinent()
    {
        ArrayList<City> cty = app.getCityContinent();
        app.displayCityContinent(cty);
    }

    @Test
    protected void testCityRegion()
    {
        ArrayList<City> ctyregion = app.getCityRegion();
        app.displayCityRegion(ctyregion);
    }

    @Test
    protected void testCityCountry()
    {
        ArrayList<City> ctycountry = app.getCityCountry();
        app.displayCityCountry(ctycountry);
    }

    @Test
    protected void testCityDistrict()
    {
        ArrayList<City> ctydistrict = app.getCityDistrict();
        app.displayCityDistrict(ctydistrict);
    }

    @Test
    protected void testCapitalCityWorld()
    {
        ArrayList<City> cpcty_world = app.getCapitalCityWorld();
        app.displayCapitalCityWorld(cpcty_world);
    }

    @Test
    protected void testCapitalCityContinent()
    {
        ArrayList<City> cpcty_continent = app.getCapitalCityContinent();
        app.displayCapitalCityContinent(cpcty_continent);
    }

    @Test
    protected void testCapitalCityRegion()
    {
        ArrayList<City> cpcty_region = app.getCapitalCityRegion();
        app.displayCapitalCityRegion(cpcty_region);
    }

    @Test
    protected void testCountryTotalPopuWorld()
    {
        ArrayList<Country> crtyTotlPopuWorld= app.getCountryTotalPopuWorld();
        app.displayCountryTotalPopuWorld(crtyTotlPopuWorld);
    }

    @Test
    protected void testCountryTotalPopuCont()
    {
        ArrayList<Country> crtyTotlPopuContinent= app.getCountryTotalPopuCont();
        app.displayCountryTotalPopuCont(crtyTotlPopuContinent);
    }

    @Test
    protected void displayCountryTotalPopuRegion()
    {
        ArrayList crtyTotlPopuRegion= app.getCountryTotalPopuRegion();
        app.displayCountryTotalPopuRegion(crtyTotlPopuRegion);
    }

    @Test
    protected void displayCityTotalPopu()
    {
        ArrayList ctyTotlPopu= app.getCityTotalPopu();
        app.displayCityTotalPopu(ctyTotlPopu);
    }

    @Test
    protected void displayLivePopulationContinent()
    {
        ArrayList popuContinent = app.getLivePopulationContinent();
        app.displayLivePopulationContinent(popuContinent);
    }

    @Test
    protected void displayLivePopulationCountry()
    {
        ArrayList popuCountry = app.getLivePopulationCountry();
        app.displayLivePopulationCountry(popuCountry);
    }
    

    @Test
    protected void displayLivePopulationRegion()
    {
        ArrayList popuRegion = app.getLivePopulationRegion();
        app.displayLivePopulationRegion(popuRegion);
    }

//    @Test
//    protected void displayFiveLanguagePopulationn()
//    {
//        ArrayList language = app.getFiveLanguagePopulation();
//        app.displayFiveLanguagePopulation(language);
//    }




}