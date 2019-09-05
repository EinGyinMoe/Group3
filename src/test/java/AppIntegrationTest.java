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
        app.connect("localhost:33060");
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

// 1. All the cities in a district organised by largest population to smallest
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
    @Test
    void testgetCity_report() throws SQLException
    {
        ArrayList<City> cities= app.getCity_report();
        for (City c: cities) {
            if (c.getName().equals("Dubai")) {
                assertEquals(c.getCountry().getName(), "United Arab Emirates");
                assertEquals(c.getDistrict(), "Dubai");
                assertEquals(c.getPopulation(), 669181);
            }
        }
    }

//4. All the city population in a region
//
    @Test
    void testCityRegion() throws SQLException
    {
        ArrayList<City> cities= app.getCityRegion();
        for (City c: cities) {
            if (c.getName().equals("La Habana")) {
                assertEquals(c.getCountry().getRegion(), "Caribbean");
                assertEquals(c.getPopulation(), 2256000);

            }
        }
    }

//5. All the cities in a country
// organised by largest population to smallest.
//
    @Test
    void testgetCityCountry() throws SQLException
    {
        ArrayList<City> cityCountry= app.getCityCountry();

        for (City c: cityCountry) {
            if (c.getName().equals("Namur")) {
                assertEquals(c.getCountry().getName(),"Belgium");
                assertEquals(c.getPopulation(), 105419);

            }
        }
    }

//6.
// All the country in a continent by Population
    @Test
    void testgetCountryContinent() throws SQLException
    {
        ArrayList<Country> ctries= app.getCountryContinent();

        for (Country c: ctries) {
            if (c.getName().equals("Brunei")) {
                assertEquals(c.getCode(),null);
                assertEquals(c.getContinent(), "Asia");
            }
        }
    }


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

//8.
//    all the countries in the world by the population
    @Test
    void testgetCountryWorld() throws SQLException
    {
        ArrayList<Country> countryWorld = app.getCountryWorld();
        for (Country cr: countryWorld) {
            if (cr.getName().equals("Brazil")) {
                assertEquals(cr.getPopulation(), 170115000);
            }
        }
    }

//9.
//cities in the world by population
    @Test
    void testgetCity_world() throws SQLException
    {
        ArrayList<City> ctyworld = app.getCity_world();
        for (City c: ctyworld) {
            if (c.getName().equals("Seoul")) {
                assertEquals(c.getPopulation(), 9981619);
            }
        }
    }


//10. cities in the world by population
    @Test
    void testgetCountry_report() throws SQLException
    {
        ArrayList<City> countryrept = app.getCountry_report();
        for (City c: countryrept) {
            if (c.getName().equals("Seoul")) {
                assertEquals(c.getCountry().getName(),"South Korea" );
                assertEquals(c.getCountry().getContinent(), "Asia");
                assertEquals(c.getCountry().getRegion(), "Eastern Asia");
                assertEquals(c.getPopulation(), 0);
            }
        }
    }


    //To improve code coverage
    //Test the correct Country by Continent

    @Test
    void test_getCountryContinent() throws SQLException
    {
        ArrayList<Country> ctries = app.getCountryContinent();

        for (Country c: ctries) {
            if (c.getName().equals("Myanmar"))
            {
                assertEquals(c.getCode(),null);
                assertEquals(c.getContinent(), "Asia");
            }
        }
    }

    @Test
    void test_getCountryTotalPopuWorld() throws SQLException
    {
        ArrayList<Country> crtyTotlPopuWorld= app.getCountryTotalPopuWorld();

        for (Country c: crtyTotlPopuWorld) {
            if (c.getName().equals("Afghanistan"))
            {
                assertEquals(c.getContinent(), "Asia");
                assertEquals(c.getRegion(),"Southern and Central Asia");
                assertEquals(c.getSurfaceArea(),652090.00);
                assertEquals(c.getPopulation(),45.9,5976.00);
                assertEquals(c.getCode(),null);


            }
        }
    }

    //17.
    // Population Report Option 2. Total population of a continent.
    @Test
    void test_getCountryTotalPopuCont() throws SQLException
    {
        ArrayList<Country> crtyTotlPopuCont= app.getCountryTotalPopuCont();

        for (Country c: crtyTotlPopuCont) {
            if (c.getName().equals("Afghanistan"))
            {
                assertEquals(c.getContinent(), "Asia");
                assertEquals(c.getRegion(),"Southern and Central Asia");
                assertEquals(c.getSurfaceArea(),652090.00);
                assertEquals(c.getPopulation(),45.9,5976.00);
                assertEquals(c.getCode(),null);


            }
        }
    }


}