package com.napier.sem;
//this is integration_testing ...........
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:3306");
        {
            System.out.println("\n................................\n" +
                    "Integration_Testing For WorldFact\n" +
                    "................................");
        }
    }

    @Test
    void testgetCountryByCode() throws SQLException
    {
        Country ctry = app.getCountryByCode("ABW");
        assertEquals(ctry.getCode(), "ABW");
        assertEquals(ctry.getName(), "Aruba");
        assertEquals(ctry.getPopulation(), 103000);
    }

    @Test
    void testgetCountryContinent() throws SQLException
    {
        ArrayList<Country> ctries= app.getCountryContinent();
        for (Country c: ctries) {
            if (c.getName().equals("Canada")) {
                assertEquals(c.getCode(), "CAN");
                assertEquals(c.getContinent(), "North America");
            }
        }
    }

    @Test
    void testgetCityDistrict() throws SQLException
    {
        ArrayList<City> cities= app.getCityDistrict();
        for (City c: cities) {
            if (c.getName().equals("Kabul")) {
                assertEquals(c.getDistrict(), "Kabol");
                assertEquals(c.getPopulation(), 1780000);
            }
        }
    }

    //2. All the country in a continent by Population
    @Test
    void testgetCityContinent() throws SQLException
    {
        ArrayList<City> cities= app.getCityContinent();
        for (City c: cities) {
            if (c.getName().equals("Dubai")) {
                assertEquals(c.getCountry().getContinent(), "Asia");
                assertEquals(c.getPopulation(), 669181);
            }
        }
    }
//3. city report requires the following columns:
//Name.
//Country.
//District.
//Population.
//
//    @Test
//    void testgetCity_report() throws SQLException
//    {
//        ArrayList<City> cities= app.getCity_report();
//        for (City c: cities) {
//            if (c.getName().equals("Dubai")) {
//                assertEquals(c.getCountry().getName(), "United Arab Emirates");
//                assertEquals(c.getDistrict(), "Dubai");
//                assertEquals(c.getPopulation(), 669181);
//            }
//        }
//    }




    //7.
//all the countries in a region by the population
//
    @Test
    void testgetCountryRegion() throws SQLException
    {
        ArrayList<Country> ctries= app.getCountryRegion();
        for (Country c: ctries) {
            if (c.getName().equals("Canada")) {
                assertEquals(c.getCode(), "CAN");
                assertEquals(c.getPopulation(), 31147000);
            }
        }
    }
}