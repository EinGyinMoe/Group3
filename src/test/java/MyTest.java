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

//    //Unit_testing for CapitalCity_Report
//    @Test
//    protected void  displayCapitalCity_Reporttest()
//    {
//        app.displayCapitalCityReport(null);
//    }

//    @Test
//    protected void  displayCapitalCity_ReportNulltest()
//    {   ArrayList<City> cities =new ArrayList<>();
//        cities.add(null);
//        app.displayCapitalCityReport(cities);
//    }

//    //Unit_testing for CapitalCity_World
//    @Test
//    protected void  displayCapitalCityWorldtest()
//    {
//        app.displayCapitalCityWorld(null);
//    }

//    @Test
//    protected void  displayCapitalCityWorldNulltest()
//    {   ArrayList<City> capitalcityreport =new ArrayList<>();
//        capitalcityreport.add(null);
//        app.displayCapitalCityWorld(capitalcityreport);
//    }

//    //Unit_testing for CapitalCity_World
//    @Test
//    protected void  displayCapitalCityContinenttest()
//    {
//        app.displayCapitalCityContinent(null);
//    }

//    @Test
//    protected void  displayCapitalCityContinentNulltest()
//    {   ArrayList<City> cap =new ArrayList<>();
//        cap.add(null);
//        app.displayCapitalCityContinent(cap);
//    }



}