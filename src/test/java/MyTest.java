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
    }

    @Test
    protected void displayCityDistrict()
    {
        ArrayList<City> cty = new ArrayList<City>();
        cty.add(null);
        app.displayCityDistrict(cty);
    }


    @Test
    protected void  displayCityContinenttest()
    {
        app.displayCityContinent(null);
    }

    @Test
    protected void  displayCityNulltest()
    {   ArrayList<City> cities =new ArrayList<>();
        cities.add(null);
        app.displayCityContinent(cities);
    }

    @Test
    protected void  displayCityReporttest()
    {
        app.displayCityReport(null);
    }

    @Test
    protected void  displayCityReportNulltest()
    {   ArrayList<City> cit =new ArrayList<>();
        cit.add(null);
        app.displayCityReport(cit);
    }

    @Test
    protected void  displayCityRegionttest()
    {
        app.displayCityRegion(null);
    }

    @Test
    protected void  displayCityRegionNulltest()
    {   ArrayList<City> ct =new ArrayList<>();
        ct.add(null);
        app.displayCityRegion(ct);
    }

    @Test
    protected void  displayCityCountrytest()
    {
        app.displayCityCountry(null);
    }

    @Test
    protected void  displayCityCountryNulltest()
    {   ArrayList<City> cty =new ArrayList<>();
        cty.add(null);
        app.displayCityCountry(cty);
    }

}