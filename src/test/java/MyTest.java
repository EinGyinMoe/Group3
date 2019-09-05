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

    //Unit_testing for CityDistrict
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

    //Unit_testing for CityContinent
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

    //Unit_testing for CityRegion
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
    //Unit_testing for CityWorld
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


    //Unit_testing for CityCountry
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

    //Unit_testing for CountryContinent
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

    //Unit_testing for CountryRegion
    @Test
    protected void  displayCountryRegiontest()
    {
        app.displayCountryContinent(null);
    }

    @Test
    protected void  displayCountryRegionNulltest()
    {   ArrayList<Country> countries =new ArrayList<>();
        countries.add(null);
        app.displayCountryRegion(countries);
    }

    //Unit_testing for CountryWorld
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

    //Unit_testing for CapitalCity_World
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

    //Unit_testing for CapitalCity_World
    @Test
    protected void  displayCapitalCityContinenttest()
    {
        app.displayCapitalCityContinent(null);
    }


//For code review 4

    //Unit_testing for total country population in the world
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

    //Unit_testing for Cherry_PArt
    @Test
    protected void  displayLivePopulationRegiontest()
    {
        app.displayLivePopulationRegion(null);
    }

    //Unit_testing for Cherry_PArt
    @Test
    protected void  displayLivePopulationCountrytest()
    {
        app.displayLivePopulationCountry(null);
    }

    //Unit_testing for Cherry_PArt
    @Test
    protected void  displayLivePopulationContinenttest()
    {
        app.displayLivePopulationContinent(null);
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
        language.getLanguage();
        language.getPercentage();
    }

    //Country Testing
    @Test
    protected void country_testing(){
        Country country = new Country();
        country.setName("MyTest");
        country.setContinent("MyTest");
        country.setCapital (10);
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
    }

    //City Testing
    @Test
    protected void city_testing(){
        City   city = new City();
        city.setName("MyTest");
        city.setDistrict("MyTest");
        city.getID();
        city.getName();
        city.getCountry();
        city.getDistrict();
        city.getPopulation();
    }

    //Population Testing
    @Test
    protected void testingPopulation(){
        Population population = new Population();
        population.setPercentinCity(100);
        population.setPopulationinCity(200);
        population.setPopulationNotinCity(300);
        population.setTotalPopulation(400);
        population.setPopulationPercent(500);
    }

    //TO improve our code coverage
    @Test
    void testingCityReport(){
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
    void testCountryWorld()
    {
        ArrayList<Country> countryworld = app.getCountryWorld();
        app.displayCountryWorld(countryworld);
    }

    @Test
    void testCountryReg()
    {
        ArrayList<Country> countryInReg = app.getCountryRegion();
        app.displayCountryRegion(countryInReg);
    }

    @Test
    void testCountryContinent()
    {
        ArrayList<Country> countrycontinent = app.getCountryContinent();
        app.displayCountryContinent(countrycontinent);
    }

    @Test
    void testCityWorld()
    {
        ArrayList<City> ctyworld = app.getCity_world();
        app.displayCityWorld(ctyworld);
    }

    @Test
    void testCityContinent()
    {
        ArrayList<City> cty = app.getCityContinent();
        app.displayCityContinent(cty);
    }

    @Test
    void testCityRegion()
    {
        ArrayList<City> ctyregion = app.getCityRegion();
        app.displayCityRegion(ctyregion);
    }

    @Test
    void testCityCountry()
    {
        ArrayList<City> ctycountry = app.getCityCountry();
        app.displayCityCountry(ctycountry);
    }

    @Test
    void testCityDistrict()
    {
        ArrayList<City> ctydistrict = app.getCityDistrict();
        app.displayCityDistrict(ctydistrict);
    }

    @Test
    void testCapitalCityWorld()
    {
        ArrayList<City> cpcty_world = app.getCapitalCityWorld();
        app.displayCapitalCityWorld(cpcty_world);
    }

    @Test
    void testCapitalCityContinent()
    {
        ArrayList<City> cpcty_continent = app.getCapitalCityContinent();
        app.displayCapitalCityContinent(cpcty_continent);
    }

    @Test
    void testCapitalCityRegion()
    {
        ArrayList<City> cpcty_region = app.getCapitalCityRegion();
        app.displayCapitalCityRegion(cpcty_region);
    }

    @Test
    void testCountryTotalPopuWorld()
    {
        ArrayList<Country> crtyTotlPopuWorld= app.getCountryTotalPopuWorld();
        app.displayCountryTotalPopuWorld(crtyTotlPopuWorld);
    }

    @Test
    void testCountryTotalPopuCont()
    {
        ArrayList<Country> crtyTotlPopuContinent= app.getCountryTotalPopuCont();
        app.displayCountryTotalPopuCont(crtyTotlPopuContinent);
    }





}