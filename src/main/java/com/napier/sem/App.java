package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

/**
 * This is the class to be accessed from other functions
 */
public class App {
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();


        //a.getCityCountry();
        a.getFiveLanguagePopulation();


        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

//
//All the cities in a country
// organised by largest population to smallest.
//
    public void getCityCountry()
    {
        //ArrayList<City> cityCountry = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            + "FROM country, city "
                            + "WHERE city.CountryCode = country.Code AND country.Name = 'Belgium' "
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Country Report Not found!");
            } else {
                // Return city if valid.
                while (rset.next()) {
                    System.out.printf("\nCity Name: " + rset.getString(1) + "\n" +
                                    "Country Name: " + rset.getString(2) + "\n" +
                                      "Continent: " + rset.getString(3) + "\n" );
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city in a country!.");
        }

    }

//
//the following languages from greatest number to smallest, including the percentage of the world population
//
    public void getFiveLanguagePopulation()
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT countrylanguage.Language, sum(country.Population * countrylanguage.Percentage) AS Population "
                            + "FROM country, countrylanguage "
                            + "WHERE country.Code = countrylanguage.CountryCode AND countrylanguage.Language IN ('English','Chinese','Hindi','Arabic','Spanish') "
                            + "GROUP BY countrylanguage.Language";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Country Report Not found!");
            } else {
                // Return city if valid.
                while (rset.next()) {
                    System.out.printf("\nLanguage: " + rset.getString(1) + "\n" +
                            "Population: " + rset.getString(2) + "\n" );
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city in a country!.");
        }

    }



} /*This is the end of the public App class */