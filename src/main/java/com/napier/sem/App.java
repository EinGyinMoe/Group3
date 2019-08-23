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

        // a.getCapitalCityReport();
        // a.getCapitalCityWorld();
        // a.getCapitalCityContinent();
        // a.getCapitalCityRegion();
        a.getTotalPopulation();

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

        int retries = 20;
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
//All the functions related to the city table
//
//capital city report
//
    public void getCapitalCityReport()
    {
        ArrayList<Country> cpcty_report = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Capital,country.Name, country.Population "
                            + "FROM country "
                            + "ORDER BY country.Capital ASC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                while (rset.next()) {
                    System.out.printf("\nCapital City: " + rset.getInt(1) + "\n" + "Country: " + rset.getString(2) + "\n" + "Population: " + rset.getInt(3) + "\n");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city report.");
        }
    }
// capital city world
    public void getCapitalCityWorld()
    {
        ArrayList<Country> cpcty_world = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Capital,country.Name, country.Population "
                            + "FROM country "
                            + "ORDER BY country.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                while (rset.next()) {
                    System.out.printf("\nCapital City: " + rset.getInt(1) + "\n" + "Country: " + rset.getString(2) + "\n" + "Population: " + rset.getInt(3) + "\n");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city world.");
        }
    }

// capital city in a continent
    public void getCapitalCityContinent()
    {
        ArrayList<Country> cpcty_world = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Capital,country.Name, country.Continent, country.Population "
                            + "FROM country "
                            + "WHERE country.Continent='Asia' "
                            + "ORDER BY country.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                while (rset.next()) {
                    System.out.printf("\nCapital City: " + rset.getInt(1) + "\n" + "Country: " + rset.getString(2) + "\n" + "Continent: " + rset.getString(3) + "\n" + "Population: " + rset.getInt(4) + "\n");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city in a continent.");
        }
    }
    // capital city in a region
    public void getCapitalCityRegion()
    {
        ArrayList<Country> cpcty_world = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Capital,country.Name, country.Region, country.Population "
                            + "FROM country "
                            + "WHERE country.Region='Caribbean' "
                            + "ORDER BY country.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                while (rset.next()) {
                    System.out.printf("\nCapital City: " + rset.getInt(1) + "\n" + "Country: " + rset.getString(2) + "\n" + "Region: " + rset.getString(3) + "\n" + "Population: " + rset.getInt(4) + "\n");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city in a region.");
        }
    }
    // All the functions related to total population.
    // Total population of the world, a region, a continent and a country
    public void getTotalPopulation()
    {
        ArrayList<Country> totl_population = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            Statement stmt3 = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT SUM(CASE WHEN country.Region='Caribbean' THEN country.Population END ) as totalregion, SUM(CASE WHEN country.Continent='Asia' THEN country.Population END ) as totalcontinent " +
                            "FROM country " +
                            "WHERE country.Continent='Asia' OR country.Region='Caribbean'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                while (rset.next()) {
                    System.out.printf("Total Population of a Region: " + rset.getLong(1) + "\n" + "Total Population of a Continent: " + rset.getLong(2) + "\n");
                }
                String strSelect2 =
                        "SELECT SUM(country.Population) " +
                                "FROM country ";
                ResultSet rset2 = stmt2.executeQuery(strSelect2);
                while(rset2.next())
                {
                    System.out.printf("Total Population of the world: " + rset2.getLong(1) + "\n");
                }
                String strSelect3 =
                        "SELECT country.Population " +
                                "FROM country " +
                                "WHERE country.Name='Argentina'";
                ResultSet rset3 = stmt3.executeQuery(strSelect3);
                while(rset3.next())
                {
                    System.out.printf("Total Population of a country: " + rset3.getLong(1) + "\n");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the total population of the world, a region, a continent and a country.");
        }
    }
} /*This is the end of the public App class */