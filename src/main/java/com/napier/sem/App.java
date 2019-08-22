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

//        a.getCity_world();
        ArrayList cty = a.getCityContinent();
        a.displayCityContinent(cty);
//        a.getCityRegion();
//        a.getCityDistrict();

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

    //All the functions related to the Countries Table
//
//All the country in a continent by Population
//
    public ArrayList<City> getCityContinent()
    {
        ArrayList<City> cities = null;
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
                cities=new ArrayList<>();
                // Return new city if valid.
                while (rset.next()) {
                    //System.out.printf("%20s%20s%20s%20d",rset.getString(1),rset.getInt(2),rset.getString(3));
                    //System.out.println("\n");
                    //  System.out.printf(rset.getString(1)+"\t"+rset.getInt(2)+"\t"+rset.getString(3)+"\n");
                    City ct=new City();
                    Country c =new Country();
                    ct.setName(rset.getString(1));
                    ct.setPopulation(rset.getInt(2));
                    c.setContinent(rset.getString(3));//set continent value to country object
                    ct.setCountry(c);
                    cities.add(ct);


                }
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country name by continent");
        }
        //return continent;
        return cities;
    }

    public void displayCityContinent(ArrayList<City> cty)
    {

        if (cty == null)
        {
            System.out.println("No cities data");
            return;
        }
        for(City c:cty)
        {
            if (c == null) {
                System.out.println("Null City");
                continue;
            }
            System.out.println(c.Name + "\t" + c.Population + "\n");
        }
        System.out.print("\n");
    }

} /*This is the end of the public App class */