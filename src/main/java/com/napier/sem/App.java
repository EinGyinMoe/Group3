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

        a.getCapitalCityReport();
        ArrayList cpcty_report = a.getCapitalCityReport();
        a.displayCapitalCityReport(cpcty_report);
        // a.getCapitalCityWorld();
        // a.getCapitalCityContinent();
        // a.getCapitalCityRegion();
        // a.getTotalPopulationCountry();
//        a.getTotalPopulationCity();

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
//Capital City report
//
    public ArrayList<City> getCapitalCityReport()
    {
        ArrayList<City> cpcty_report = null;
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name,country.Name, city.Population "
                            + "FROM city,country "
                            + "WHERE city.ID=country.Capital "
                            + "ORDER BY city.Name ASC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Capital City Report Not found.");
            } else {
                cpcty_report=new ArrayList<>();
                // Return new capital city if valid.
                // Check one is returned.
                while (rset.next()) {
                    Country country=new Country();
                    City city=new City();
                    city.setName(rset.getString(1));
                    country.setName(rset.getString(2));
                    city.setPopulation(rset.getInt(3));

                    city.setCountry(country);
                    cpcty_report.add(city);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city report.");
        }
        return cpcty_report;
    }
    public void displayCapitalCityReport(ArrayList<City>cpcty_report)
    {
        if (cpcty_report==null)
        {
            System.out.println("* There is no null data in capital city report!\n");
            return;
        }
        System.out.print("\n**************************************Capital City Report**************************************\n\n");
        System.out.printf("%25s%25s%25s","Capital City","Country","Population\n");
        System.out.print("\n***********************************************************************************************\n\n");

        for (City cty:cpcty_report)
        {
            if(cty == null)
            {
                System.out.println("* No null data in each capital city report!\n");
                continue;
            }
            System.out.printf("%25s%25s%25s",cty.getName(), cty.getCountry().getName(), cty.getPopulation());
            System.out.print("\n");
        }
        System.out.print("\n***********************************************************************************************\n");
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
                // Return new capital city world if valid.
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
        ArrayList<Country> cpcty_continent = new ArrayList<>();
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
                // Return new capital city continent if valid.
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
        ArrayList<Country> cpcty_region = new ArrayList<>();
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
                // Return new capital city region if valid.
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
    // Total population of the world, a region, a continent, and a country
    public void getTotalPopulationCountry()
    {
        ArrayList<Country> totl_popu_country = new ArrayList<>();
        try {
            // Create SQL statements for the total population of the world, a region, a continent, and a country.
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            Statement stmt3 = con.createStatement();

            // Create string for total population of a region and a continent SQL statement
            String strSelect =
                    "SELECT SUM(CASE WHEN country.Region='Caribbean' THEN country.Population END ) as totalregion, SUM(CASE WHEN country.Continent='Asia' THEN country.Population END ) as totalcontinent " +
                            "FROM country " +
                            "WHERE country.Continent='Asia' OR country.Region='Caribbean'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new total population of a region and a continent if valid.
                while (rset.next()) {
                    System.out.printf("Total Population of a Region: " + rset.getLong(1) + "\n" + "Total Population of a Continent: " + rset.getLong(2) + "\n");
                }
                // Create string for total population of the world SQL statement
                String strSelect2 =
                        "SELECT SUM(country.Population) " +
                                "FROM country ";
                // Execute SQL statement
                ResultSet rset2 = stmt2.executeQuery(strSelect2);
                // Return new total population of the world if valid.
                while(rset2.next())
                {
                    System.out.printf("Total Population of the world: " + rset2.getLong(1) + "\n");
                }
                // Create string for total population of a country SQL statement
                String strSelect3 =
                        "SELECT country.Population " +
                                "FROM country " +
                                "WHERE country.Name='Argentina'";
                // Execute SQL statement
                ResultSet rset3 = stmt3.executeQuery(strSelect3);
                // Return new total population of a country if valid.
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
    // Total population of a city and a city district
    public void getTotalPopulationCity()
    {
        ArrayList<City> totl_popu_city = new ArrayList<>();
        try {
            // Create SQL statements for the total population of a city district and a city.
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();

            // Create string for the total population of a city district SQL statement
            String strSelect =
                    "SELECT SUM(CASE WHEN city.District='Kabol' THEN city.Population END ) as totaldistrict " +
                            "FROM city " +
                            "WHERE city.District='Kabol'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new total population of a city district if valid.
                while (rset.next()) {
                    System.out.printf("Total Population of a City District: " + rset.getLong(1) + "\n");
                }
                // Create string for total population of a city SQL statement
                String strSelect2 =
                        "SELECT city.Population " +
                                "FROM city " +
                                "WHERE city.Name='Herat'";
                // Execute SQL statement
                ResultSet rset2 = stmt2.executeQuery(strSelect2);
                // Return new total population of a city if valid.
                while(rset2.next())
                {
                    System.out.printf("Total Population of a city: " + rset2.getInt(1) + "\n");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the total population of a city district, and a city.");
        }
    }
} /*This is the end of the public App class */