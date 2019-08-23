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
        if (args.length < 1)
        {
            a.connect("localhost:3306");
        }
        else
        {
            a.connect(args[0]);
        }

        // 1. All the cities in a district by population
        ArrayList ctydistrict = a.getCityDistrict();
        a.displayCityDistrict(ctydistrict);

        //2. All the country in a continent by Population
        ArrayList cty = a.getCityContinent();
        a.displayCityContinent(cty);

        //3. city report requires the following columns:
        //Name.
        //Country.
        //District.
        //Population.
        ArrayList ctrept = a.getCity_report();
        a.displayCityReport(ctrept);

        //4. All the city population in a region
        ArrayList ctyregion = a.getCityRegion();
        a.displayCityRegion(ctyregion);

        //5. All the cities in a country
        // organised by largest population to smallest.
        ArrayList ctycountry = a.getCityCountry();
        a.displayCityCountry(ctycountry);

        //6.
        // All the country in a continent by Population
        ArrayList countrycontinent = a.getCountryContinent();
        a.displayCountryContinent(countrycontinent);

        //7.
        //all the countries in a region by the population
        ArrayList countryregion = a.getCountryRegion();
        a.displayCountryRegion(countryregion);

        //8.
        //all the countries in the world by the population
        ArrayList countryworld = a.getCountryWorld();
        a.displayCountryWorld(countryworld);

        //9.
        //cities in the world by population
        ArrayList ctyworld = a.getCity_world();
        a.displayCityWorld(ctyworld);



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
    public void connect(String location)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
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
    public ArrayList<City> getCityDistrict()
    {
        ArrayList<City> ctydistrict =null;
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
                ctydistrict=new ArrayList<>();
                // Return new city if valid.
                while (rset.next()) {
                    City cities = new City();
                    cities.setName(rset.getString("Name"));
                    cities.setPopulation( rset.getInt("Population"));

                    ctydistrict.add(cities);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city name");
        }
        return ctydistrict;
    }

    public void displayCityDistrict(ArrayList<City> ctydistrict)
    {
        if (ctydistrict== null)
        {
            System.out.println("* There is no null data in CityDistrict!\n");
            return;
        }
        for(City c:ctydistrict)
        {
            if (c == null) {
                System.out.println("* No null data in each CityDistrict!\n");
                continue;
            }
            System.out.println(c.getName()+ "\t" + c.getPopulation() + "\n");
        }
//        System.out.print("\n");
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
            System.out.println("* There is no null data in CityContinent!\n");
            return;
        }
        for(City c:cty)
        {
            if (c == null) {
                System.out.println("* No null data in each CityContinent!\n");
                continue;
            }
            System.out.println(c.getName() + "\t" + c.getPopulation() + "\t" + c.getCountry().getContinent() + "\n");
        }
//        System.out.print("\n");
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
            System.out.println("* There is no null data in City Report!\n");
            return;
        }
        for(City c:ctyrp)
        {
            if (c == null) {
                System.out.println("* No null data in each CityReport!\n");
                continue;
            }
            System.out.println(c.getName() + "\t" + c.getPopulation() + "\t" + c.getCountry().getContinent() + c.getCountry().getName() +"\n");
        }
//        System.out.print("\n");
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
            System.out.println("* There is no null data in CityRegion!\n");
            return;
        }
        for(City c:region)
        {
            if (c == null) {
                System.out.println("* No null data in each CityRegion!\n");
                continue;
            }
            System.out.println(c.getName() + "\t" + c.getPopulation() + "\t" + c.getCountry().getName() + "\n");
        }
//        System.out.print("\n");
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
            System.out.println("* There is no null data in CityCountry!\n");
            return;
        }
        for(City c:cityCountry)
        {
            if (c == null) {
                System.out.println("* No null data in each CityCountry!\n ");
                continue;
            }
            System.out.println(c.getName() + "\t" + c.getCountry().getName() + "\t" + c.getPopulation() + "\t"+c.getCountry().getContinent() + "\n");
        }
//        System.out.print("\n");
    }

    public Country getCountryByCode(String code)throws SQLException
    {   Country c=null;

        // Create string for SQL statement
        String strSelect =
                "SELECT country.Code, country.Name, country.Population, country.Continent  "
                        + "FROM country "
                        + "WHERE country.Code = ?";
        PreparedStatement stmt = con.prepareStatement(strSelect);
        stmt.setString(1,code);



        // Execute SQL statement
        ResultSet rset = stmt.executeQuery();
        if (rset==null) System.out.println("No Country found");
        else{ c=new Country();
            while(rset.next())
            {
                c.setCode(rset.getString(1));
                c.setName(rset.getString(2));
                c.setPopulation(rset.getInt(3));
                c.setContinent(rset.getString(4));

            }


        }
       return c;

    }

    //6.
// All the country in a continent by Population
//
    public ArrayList<Country> getCountryContinent()
    {
        ArrayList<Country> countryContinent = null;
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
                countryContinent =new ArrayList<>();
                // Return new city if valid.
                // Check one is returned
                while (rset.next()) {
                    Country country = new Country();
                    country.setName(rset.getString("Name"));
                    country.setPopulation( rset.getInt("Population"));
                    country.setContinent(rset.getString("Continent"));

                    countryContinent.add(country);

                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country name by continent");
        }
        return countryContinent;
    }
    public void displayCountryContinent(ArrayList<Country> countryContinent)
    {

        if (countryContinent == null)
        {
            System.out.println("* There is no null data in CountryContinent!\n");
            return;
        }
        for(Country ct:countryContinent)
        {
            if (ct == null) {
                System.out.println("* No null data in each CountryContinent!\n");
                continue;
            }
            System.out.println(ct.getName() + "\t" + ct.getPopulation() + "\t"+ ct.getContinent() + "\n");
        }
//        System.out.print("\n");
    }

    //7.
//all the countries in a region by the population
//
    public ArrayList<Country> getCountryRegion()
    {
        ArrayList<Country> countryRegion = null;
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
                countryRegion =new ArrayList<>();
                // Return new city if valid.
                // Check one is returned
                while (rset.next()) {
                    Country country = new Country();
                    country.setName(rset.getString("Name"));
                    country.setPopulation( rset.getInt("Population"));
                    country.setRegion(rset.getString("Region"));

                    countryRegion.add(country);


                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country name by region");
        }
        return countryRegion;

    }
    public void displayCountryRegion(ArrayList<Country> countryRegion)
    {

        if (countryRegion == null)
        {
            System.out.println("* There is no null data in CountryRegion!\n");
            return;
        }
        for(Country ct:countryRegion)
        {
            if (ct == null) {
                System.out.println("* No null data in each CountryRegion!\n");
                continue;
            }
            System.out.println(ct.getName() + "\t" + ct.getPopulation() + "\t"+ ct.getRegion() + "\n");
        }
//        System.out.print("\n");
    }

    //8.
//    all the countries in the world by the population
////
    public ArrayList<Country> getCountryWorld()
    {
        ArrayList<Country> countryWorld = null;
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
                countryWorld = new ArrayList<>();
                // Return new city if valid.
                // Check one is returned
                while (rset.next()) {
                    Country country = new Country();
                    country.setName(rset.getString("Name"));
                    country.setPopulation(rset.getInt("Population"));

                    countryWorld.add(country);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country name in the world.");
        }
        return countryWorld;
    }
    public void displayCountryWorld(ArrayList<Country> countryWorld)
    {

        if (countryWorld == null)
        {
            System.out.println("* There is no null data in CountryWorld!\n");
            return;
        }
        for(Country ct:countryWorld)
        {
            if (ct == null) {
                System.out.println("* No null data in each CountryWorld!\n ");
                continue;
            }
            System.out.println(ct.getName() + "\t" + ct.getPopulation() + "\n");
        }
//        System.out.print("\n");
    }
    //
//9.
//cities in the world by population
////
    public ArrayList<City> getCity_world()
    {
        ArrayList<City> ctyworld = null;
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
                ctyworld = new ArrayList<>();
                // Return new city if valid.
                // Check one is returned
                while (rset.next()) {
                    City city = new City();
                    city.setName(rset.getString("Name"));
                    city.setPopulation(rset.getInt("Population"));

                    ctyworld.add(city);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city name in the world.");
        }
        return ctyworld;
    }
    public void displayCityWorld(ArrayList<City> ctyWorld)
    {

        if (ctyWorld == null)
        {
            System.out.println("* There is no null data in CityWorld!\n.");
            return;
        }
        for(City c:ctyWorld)
        {
            if (c == null) {
                System.out.println("* No null data in each CityWorld!\n");
                continue;
            }
            System.out.println(c.getName() + "\t" + c.getPopulation() + "\n");
        }
//        System.out.print("\n");
    }




} /*This is the end of the public App class */