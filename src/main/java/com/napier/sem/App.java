package com.napier.sem;

import com.sun.java_cup.internal.runtime.Scanner;

import java.sql.*;
import java.util.ArrayList;

//This is the class to be accessed from other functions

public class App {
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

//        a.getCity_world();
//        a.getCityContinent();
//        a.getCityRegion();
//        a.getCityDistrict();
//        a.getPopulationRegion();

//        a.getCountryTotalPopulationRegion();

//      get population in the cities people who live and not living in each region
        a.getTotalPopulationCityRegion();
        a.getNotLivingPopulationRegion();

//      get population in the cities people who live and not living in each country
//        a.getTotalPopulationCityCountry();
//        a.getNotLivingPopulationCountry();

//      get population in the cities people who live and not living in each continent
//        a.getTotalPopulationCityContinent();
//        a.getNotLivingPopulationContinent();


//        a.getPopulationCountry();
//        a.getCountryContinent();
//        a.getCountryRegion();
//        a.getCountryWorld();

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
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
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
//cities in the world by population
//
    public void getCity_world()
    {
        ArrayList<City> ctyworld = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name,city.Population "
                            + "FROM city "
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                while (rset.next()) {
                    System.out.printf(rset.getString(1) + "\t" + rset.getInt(2) + "\n");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city name in the world.");
        }
        //return ctyworld;
    }

    //
    // All the cities in a district by population
    //
    public void getCityDistrict()
    {
        ArrayList<City> cty = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name,city.Population "
                            + "FROM city "
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                while (rset.next()) {
                    City cities = new City();
                    cities.Name = rset.getString("Name");
                    cities.Population = rset.getInt("Population");

                    cty.add(cities);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city name");
        }
//        return cty;
    }

    public void displayCityDistrict(ArrayList<City> cty)
    {
        for(City c:cty)
        {
            System.out.println(c.Name + "\t" + c.Population + "\n");
        }
        System.out.print("\n");
    }

//All the functions related to the Countries Table
//
//All the country in a continent by Population
//
    public void getCityContinent()
    {
        // ArrayList<City> continent = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.Population, country.Continent  "
                            + "FROM city, country "
                            + "WHERE city.CountryCode = country.Code AND country.Continent = 'Europe' "
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                while (rset.next()) {
                    //System.out.printf("%20s%20s%20s%20d",rset.getString(1),rset.getInt(2),rset.getString(3));
                    //System.out.println("\n");
                    System.out.printf(rset.getString(1)+"\t"+rset.getInt(2)+"\t"+rset.getString(3)+"\n");

                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country name by continent");
        }
        //return continent;

    }

    //  All the cities in a region by the population
    public void getCityRegion()
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.Population, country.Region  "
                            + "FROM city, country "
                            + "WHERE city.CountryCode = country.Code AND country.Region = 'Caribbean' "
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                // Check one is returned
                while (rset.next()) {
                    //System.out.printf("%20s%20s%20s%20d",rset.getString(1),rset.getInt(2),rset.getString(3));
                    //System.out.println("\n");
                    System.out.printf(rset.getString(1)+"\t"+rset.getInt(2)+"\t"+rset.getString(3)+"\n");

                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country name by region");
        }
        //return continent;

    }

    // All the country in a continent by Population
    //
    public void getCountryContinent()
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Name, country.Population, country.Continent  "
                            + "FROM country "
                            + "WHERE country.Continent = 'Asia' "
                            + "ORDER BY country.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                // Check one is returned
                while (rset.next()) {
                    //System.out.printf("%20s%20s%20s%20d",rset.getString(1),rset.getInt(2),rset.getString(3));
                    //System.out.println("\n");
                    System.out.printf(rset.getString(1)+"\t"+rset.getInt(2)+"\t"+rset.getString(3)+"\n");

                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country name by continent");
        }
        //return continent;

    }

//    all the countries in a region by the population
//
    public void getCountryRegion()
    {
//        ArrayList<Country> region = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Name, country.Population, country.Region  "
                            + "FROM country "
                            + "WHERE country.Region = 'Caribbean' "
                            + "ORDER BY country.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                // Check one is returned
                while (rset.next()) {
                    //System.out.printf("%20s%20s%20s%20d",rset.getString(1),rset.getInt(2),rset.getString(3));
                    //System.out.println("\n");
                    System.out.printf(rset.getString(1)+"\t"+rset.getInt(2)+"\t"+rset.getString(3)+"\n");

                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country name by region");
        }
        //return continent;
    }

    //    all the countries in the world by the population
//
    public void getCountryWorld()
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Name, country.Population  "
                            + "FROM country "
                            + "ORDER BY country.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                // Check one is returned
                while (rset.next()) {
                    //System.out.printf("%20s%20s%20s%20d",rset.getString(1),rset.getInt(2),rset.getString(3));
                    //System.out.println("\n");
                    System.out.printf(rset.getString(1)+"\t"+rset.getInt(2)+"\n");

                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country name by region");
        }
        //return continent;
    }

    public void getPopulationRegion()
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.Population, country.Region "
                    + "FROM city, country "
                    +"WHERE city.CountryCode = country.Code AND country.Region = 'Caribbean' ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                // Check one is returned
                while (rset.next()) {
                    //System.out.printf("%20s%20s%20s%20d",rset.getString(1),rset.getInt(2),rset.getString(3));
                    //System.out.println("\n");
                    System.out.printf(rset.getString(1)+"\t"+rset.getInt(2)+"\t"+rset.getString(3)+"\n");

                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get people who live in the city by region");
        }
        //return continent;
    }

//    public void getCountryTotalPopulationRegion()
//    {
//        try {
//            // Create an SQL statement
//            Statement stmt = con.createStatement();
//            // Create string for SQL statement
//            String strSelect =
//                    "SELECT SUM(country.Population) "
//                            + "FROM country "
//                            +"WHERE country.Region = 'Caribbean' ";
//            // Execute SQL statement
//            ResultSet rset = stmt.executeQuery(strSelect);
//            if (rset == null) {
//                System.out.print("Not found.");
//            } else {
//                // Return new city if valid.
//                // Check one is
//
//                while (rset.next()) {
//                    //System.out.printf("%20s%20s%20s%20d",rset.getString(1),rset.getInt(2),rset.getString(3));
//                    //System.out.println("\n");
//                    System.out.printf("Total Population Live in Country: "+rset.getInt(1)+"\n");
//
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//            System.out.println("Failed to get people who are not living in the city by region");
//        }
//        //return continent;
//    }

    public void getTotalPopulationCityRegion()
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Region, (SUM(city.Population)) "
                            + "FROM city " + "INNER JOIN country " + "ON city.CountryCode = country.Code ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                // Check one is

                while (rset.next()) {
                    //System.out.printf("%20s%20s%20s%20d",rset.getString(1),rset.getInt(2),rset.getString(3));
                    //System.out.println("\n");
                    System.out.printf("Country Region: "+rset.getString(1)+"\n"+"City population in each region: "+rset.getInt(2)+"\n"+"\n");

                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get people who are not living in the city by region");
        }
        //return continent;
    }

    public void getNotLivingPopulationRegion()

    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Region, (SUM(country.Population) - SUM(city.Population)) "
                            + "FROM country " + "INNER JOIN city " + "ON city.CountryCode = country.Code "
                            +"GROUP BY country.Region";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                // Check one is
                while (rset.next()) {
                    //System.out.printf("%20s%20s%20s%20d",rset.getString(1),rset.getInt(2),rset.getString(3));
                    //System.out.println("\n");
                    System.out.printf("Region Name : "+rset.getString(1)+"\n"+"People not living in cities in each region: "+rset.getLong(2)+"\n"+"\n");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get people who are not living in the city by region");
        }
        //return continent;
    }


    public void getTotalPopulationCityCountry()
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Name, city.Name, city.Population "
                            + "FROM city " + "INNER JOIN country " + "ON city.CountryCode = country.Code ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                // Check one is

                while (rset.next()) {
                    //System.out.printf("%20s%20s%20s%20d",rset.getString(1),rset.getInt(2),rset.getString(3));
                    //System.out.println("\n");
                    System.out.printf("Country Name: "+rset.getString(1)+"\n"+"City Name: "+rset.getString(2)+"\n"+"City population in each country: "+rset.getInt(3)+"\n"+"\n");

                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get people who are not living in the city by region");
        }
        //return continent;
    }

    public void getNotLivingPopulationCountry()

    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Name, (SUM(country.Population) - SUM(city.Population)) "
                            + "FROM country " + "INNER JOIN city " + "ON city.CountryCode = country.Code "
                            +"GROUP BY country.Name";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                // Check one is
                while (rset.next()) {
                    //System.out.printf("%20s%20s%20s%20d",rset.getString(1),rset.getInt(2),rset.getString(3));
                    //System.out.println("\n");
                    System.out.printf("Country Name : "+rset.getString(1)+"\n"+"People not living in cities in each country: "+rset.getLong(2)+"\n"+"\n");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get people who are not living in the city by region");
        }
        //return continent;
    }

    public void getTotalPopulationCityContinent()
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Continent, city.Name, city.Population "
                            + "FROM city " + "INNER JOIN country " + "ON city.CountryCode = country.Code ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                // Check one is

                while (rset.next()) {
                    //System.out.printf("%20s%20s%20s%20d",rset.getString(1),rset.getInt(2),rset.getString(3));
                    //System.out.println("\n");
                    System.out.printf("Country Continent: "+rset.getString(1)+"\n"+"City Name: "+rset.getString(2)+"\n"+"City population in each continent: "+rset.getInt(3)+"\n"+"\n");

                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get people who are not living in the city by region");
        }
        //return continent;
    }

    public void getNotLivingPopulationContinent()

    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Continent, (SUM(country.Population) - SUM(city.Population)) "
                            + "FROM country " + "INNER JOIN city " + "ON city.CountryCode = country.Code "
                            +"GROUP BY country.Continent";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                // Return new city if valid.
                // Check one is
                while (rset.next()) {
                    //System.out.printf("%20s%20s%20s%20d",rset.getString(1),rset.getInt(2),rset.getString(3));
                    //System.out.println("\n");
                    System.out.printf("Continent Name : "+rset.getString(1)+"\n"+"People not living in cities in each continent: "+rset.getLong(2)+"\n"+"\n");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get people who are not living in the city by region");
        }
        //return continent;
    }

//    public void getUserInput()
//    {
//        try {
//            // Create an SQL statement
//            Statement stmt = con.createStatement();
//            // Create string for SQL statement
//            String strSelect =
//                    "SELECT country.Name, country.Population  "
//                            + "FROM country "
//                            + "ORDER BY country.Population DESC";
//            // Execute SQL statement
//            ResultSet rset = stmt.executeQuery(strSelect);
//            if (rset == null) {
//                System.out.print("Not found.");
//            } else {
//                // Return new city if valid.
//                // Check one is
//
//                while (rset.next()) {
//                    //System.out.printf("%20s%20s%20s%20d",rset.getString(1),rset.getInt(2),rset.getString(3));
//                    //System.out.println("\n");
////                    System.out.printf("People not living in Country: "+(rset.getInt(1) - rset.getInt(2))+"\n");
//                    java.util.Scanner scanner = new java.util.Scanner(System.in);
//                    System.out.println("Please enter country in the world");
//                    String id = scanner.nextLine();
//                    System.out.println("User id=" + rset.getString());
//                    System.out.println("Please enter password to get details:");
//                    String pwd = scanner.nextLine();
//                    System.out.println("User password=" + pwd);
//
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//            System.out.println("Failed to get people who are not living in the city by region");
//        }
//        //return continent;
//    }

}