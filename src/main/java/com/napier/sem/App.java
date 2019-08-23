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

        ArrayList ctrept = a.getCity_report();
        a.displayCityReport(ctrept);

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
// 1. All the cities in a district by population
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


//2. All the country in a continent by Population
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
            System.out.println("No cities data \n");
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

//
//3. city report requires the following columns:
//Name.
//Country.
//District.
//Population.
//
    public ArrayList<City> getCity_report()
    {
        ArrayList<City> cityrept = null;
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population "
                            + "FROM city, country  "
                            + "WHERE city.CountryCode = country.Code "
                            + "ORDER BY city.Name ASC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("City Report Not found!");
            } else {
                cityrept=new ArrayList<>();
                // Return new city if valid.
                while (rset.next()) {
                    City cty=new City();
                    Country c1 =new Country();
                    cty.setName(rset.getString(1));
                    cty.setPopulation(rset.getInt(2));
                    c1.setContinent(rset.getString(3));//set continent value to country object
                    cty.setCountry(c1);
                    cityrept.add(cty);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city report!.");
        }
        return cityrept;
    }
    public void displayCityReport(ArrayList<City> ctyrp)
    {

        if (ctyrp == null)
        {
            System.out.println("No city report.");
            return;
        }
        for(City c:ctyrp)
        {
            if (c == null) {
                System.out.println("Null City report data ");
                continue;
            }
            System.out.println(c.Name + "\t" + c.Population + "\n");
        }
        System.out.print("\n");
    }

//4. All the city population in a region
//
    public ArrayList<City> getCityRegion()
    {
        ArrayList<City> region = new ArrayList<>();
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
                    //System.out.printf(rset.getString(1)+"\t"+rset.getInt(2)+"\t"+rset.getString(3)+"\n");
                    City cty2=new City();
                    Country c2 =new Country();
                    cty2.setName(rset.getString(1));
                    cty2.setPopulation(rset.getInt(2));
                    c2.setContinent(rset.getString(3));//set continent value to country object
                    cty2.setCountry(c2);
                    region.add(cty2);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country name by region");
        }
        return region;

    }
    public void displayCityRegion(ArrayList<City> region)
    {

        if (region == null)
        {
            System.out.println("No region.");
            return;
        }
        for(City c:region)
        {
            if (c == null) {
                System.out.println("Null Region data ");
                continue;
            }
            System.out.println(c.Name + "\t" + c.Population + "\n");
        }
        System.out.print("\n");
    }


//5. All the cities in a country
// organised by largest population to smallest.
//
    public ArrayList<City> getCityCountry()
    {
        ArrayList<City> cityCountry = new ArrayList<>();
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
//                    System.out.printf("\nCity Name: " + rset.getString(1) + "\n" +
//                            "Country Name: " + rset.getString(2) + "\n" +
//                            "Continent: " + rset.getString(3) + "\n" );
                    City cty3=new City();
                    Country c3 =new Country();
                    cty3.setName(rset.getString(1));
                    cty3.setPopulation(rset.getInt(2));
                    c3.setContinent(rset.getString(3));//set continent value to country object
                    cty3.setCountry(c3);
                    cityCountry.add(cty3);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city in a country!.");
        }
        return cityCountry;

    }
    public void displayCityCountry(ArrayList<City> cityCountry)
    {

        if (cityCountry == null)
        {
            System.out.println("No city in a country.");
            return;
        }
        for(City c:cityCountry)
        {
            if (c == null) {
                System.out.println("Null City in a country data ");
                continue;
            }
            System.out.println(c.Name + "\t" + c.Population + "\n");
        }
        System.out.print("\n");
    }

} /*This is the end of the public App class */