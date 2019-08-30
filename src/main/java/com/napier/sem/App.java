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

//        // 1. All the cities in a district organised by largest population to smallest
//        ArrayList ctydistrict = a.getCityDistrict();
//        a.displayCityDistrict(ctydistrict);

//        //2. All the country in a continent by Population
//        ArrayList cty = a.getCityContinent();
//        a.displayCityContinent(cty);
//
        //3. city report requires the following columns:
        //Name.
        //Country.
        //District.
        //Population.
        ArrayList ctrept = a.getCity_report();
        a.displayCityReport(ctrept);
//
//        //4. All the city population in a region
//        ArrayList ctyregion = a.getCityRegion();
//        a.displayCityRegion(ctyregion);
//
//        //5. All the cities in a country
//        // organised by largest population to smallest.
//        ArrayList ctycountry = a.getCityCountry();
//        a.displayCityCountry(ctycountry);
//
//        //6.
//        // All the country in a continent by Population
//        ArrayList countrycontinent = a.getCountryContinent();
//        a.displayCountryContinent(countrycontinent);
//
//        //7.
//        //all the countries in a region by the population
//        ArrayList countryregion = a.getCountryRegion();
//        a.displayCountryRegion(countryregion);
//
//        //8.
//        //all the countries in the world by the population
//        ArrayList countryworld = a.getCountryWorld();
//        a.displayCountryWorld(countryworld);
//
//        //9.
//        //cities in the world by population
//        ArrayList ctyworld = a.getCity_world();
//        a.displayCityWorld(ctyworld);

//        //10. cities in the world by population
//        ArrayList countryreport = a.getCountry_report();
//        a.displayCountry_report(countryreport);

//        //11. capital city report
//        ArrayList capitalcityreport = a.getCapitalCityReport();
//        a.displayCapitalCityReport(capitalcityreport);
//
//        //12. capital city world
//        ArrayList capitalcityworld = a.getCapitalCityWorld();
//        a.displayCapitalCityWorld(capitalcityworld);
//
//        //13. capital city in a continent
//        ArrayList capitalcitycontinent = a.getCapitalCityContinent();
//        a.displayCapitalCityContinent(capitalcitycontinent);






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
// 1. All the cities in a district organised by largest population to smallest
//
    public ArrayList<City> getCityDistrict()
    {
        ArrayList<City> ctydistrict =null;
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name,city.Population, city.District "
                            + "FROM city WHERE city.District = 'Gelderland' "
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
                    cities.setDistrict(rset.getString("District"));
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

        System.out.print("=============== All the cities in a district organised by largest population to smallest ===============\n");
        System.out.printf("%25s%25s%25s","Name","Population","District\n");
        System.out.print("========================================================================================================\n");
        for(City c:ctydistrict)
        {
            if (c == null) {
                System.out.println("* No null data in each CityDistrict!\n");
                continue;
            }
            System.out.printf("%25s%25s%25s",c.getName(), c.getPopulation(), c.getDistrict());
            System.out.print("\n");
        }
        System.out.print("========================================================================================================\n");
    }


//2. All the cities in a continent organised by largest population to smallest
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
        return cities;
    }

    public void displayCityContinent(ArrayList<City> cty)
    {

        if (cty == null)
        {
            System.out.println("* There is no null data in CityContinent!\n");
            return;
        }
        System.out.print("=============== All the cities in a continent organised by largest population to smallest ===============\n");
        System.out.printf("%25s%25s%25s","Name","Population","Continent\n");
        System.out.print("========================================================================================================\n");
        for(City c:cty)
        {
            if (c == null) {
                System.out.println("* No null data in each CityContinent!\n");
                continue;
            }
            System.out.printf("%25s%25s%25s",c.getName(), c.getPopulation(), c.getCountry().getContinent());
            System.out.print("\n");
        }
//        System.out.print("========================================================================================================\n");
    }


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
        System.out.print("============================================== City Report ==============================================\n");
        System.out.printf("%25s%25s%25s","Name","Population","Continent","Country Name","\n");
        System.out.print("=========================================================================================================\n");
        for(City c:ctyrp)
        {
            if (c == null) {
                System.out.println("* No null data in each CityReport!\n");
                continue;
            }
            System.out.printf("%25s%25s%25s",c.getName(), c.getPopulation(), c.getCountry().getContinent(), c.getCountry().getName());
            System.out.print("\n");
        }
//        System.out.print("=========================================================================================================\n");
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
    }

//9.
//cities in the world by population
//
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
    }

//10. cities in the world by population
//
    public ArrayList<City> getCountry_report()
    {
        ArrayList<City> countryrept = null;
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Name,country.Continent,country.Region,country.Population,city.Name "
                            + "FROM country,city "
                            + "WHERE city.ID=country.Capital "
                            + "ORDER BY country.Name ASC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Country Report Not found!");
            } else {
                countryrept = new ArrayList<>();
                // Return new city if valid.
                // Check one is returned
                while (rset.next()) {
                    Country crty10 = new Country();
                    City ct10 = new City();
                    crty10.setName(rset.getString("Name"));
                    crty10.setPopulation(rset.getInt("Population"));
                    crty10.setContinent(rset.getString("Continent"));
                    crty10.setRegion(rset.getString("Region"));
                    ct10.setName(rset.getString(5));

                    ct10.setCountry(crty10);
                    countryrept.add(ct10);
                }
                }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country report!.");
        }
        return countryrept;
    }
    public void displayCountry_report(ArrayList<City> countryrept)
    {

        if (countryrept == null) {
            System.out.println("* There is no null data in country report !\n.");
            return;
        }
        System.out.print("*********************************************************Country Report*********************************************************\n");
        System.out.printf("%25s%25s%25s%25s%25s","Name","Population", "Continent","Region","Capital\n");
        System.out.print("********************************************************************************************************************************\n");

        for(City cty:countryrept)
        {
            if (cty == null) {
                System.out.println("* No null data in each country report!\n");
                continue;
            }
            System.out.printf("%25s%25s%25s%25s%25s",cty.getCountry().getName(), cty.getCountry().getPopulation(), cty.getCountry().getContinent(),
                    cty.getCountry().getRegion(), cty.getName());
            System.out.print("\n");
        }
        System.out.print("************************************************************************************************************************************\n");
    }


//11. capital city report
//
    public ArrayList<Country> getCapitalCityReport()
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
                cpcty_report = new ArrayList<>();
                // Return new city if valid.
                // Check one is returned
                while (rset.next()) {
                    Country cpcty = new Country();
                    cpcty.setName(rset.getString("Name"));
                    cpcty.setPopulation(rset.getInt("Population"));
                    cpcty.setCapital(rset.getInt("Capital"));

                    cpcty_report.add(cpcty);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city report.");
        }
       return cpcty_report;
    }
    public void displayCapitalCityReport(ArrayList<Country> capcityrept)
    {

        if (capcityrept == null)
        {
            System.out.println("* There is no null data in capital city report !\n.");
            return;
        }
        for(Country capcity:capcityrept)
        {
            if (capcity == null) {
                System.out.println("* No null data in each country report!\n");
                continue;
            }
            System.out.println(capcity.getName() + "\t" + capcity.getPopulation() + "\t" + capcity.getCapital() + "\n");
        }
    }


//12. capital city world
    public ArrayList<Country> getCapitalCityWorld()
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
                cpcty_world = new ArrayList<>();
                // Return new city if valid.
                // Check one is returned
                while (rset.next()) {
                    Country cpworld = new Country();
                    cpworld.setCapital(rset.getInt("Capital"));
                    cpworld.setName(rset.getString("Name"));
                    cpworld.setPopulation(rset.getInt("Population"));

                    cpcty_world.add(cpworld);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city world.");
        }
        return cpcty_world;
    }
    public void displayCapitalCityWorld(ArrayList<Country> capworld)
    {

        if (capworld == null)
        {
            System.out.println("* There is no null data in capital city report !\n.");
            return;
        }
        for(Country capw:capworld)
        {
            if (capw == null) {
                System.out.println("* No null data in each country report!\n");
                continue;
            }
            System.out.println(capw.getCapital() + "\t" +capw.getName() + "\t" + capw.getPopulation() + "\n");
        }
    }

//13. All the capital cities in a continent organised by largest population to smallest
    public ArrayList<Country> getCapitalCityContinent()
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
            }  else {
                cpcty_continent = new ArrayList<>();
                // Return new city if valid.
                // Check one is returned
                while (rset.next()) {
                    Country cpcontinent = new Country();
                    cpcontinent.setCapital(rset.getInt("Capital"));
                    cpcontinent.setName(rset.getString("Name"));
                    cpcontinent.setContinent(rset.getString("Continent"));
                    cpcontinent.setPopulation(rset.getInt("Population"));

                    cpcty_continent.add(cpcontinent);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city in a continent.");
        }
        return cpcty_continent;
    }
    public void displayCapitalCityContinent(ArrayList<Country> capcontinent)
    {

        if (capcontinent == null)
        {
            System.out.println("* There is no null data in capital city report !\n.");
            return;
        }

        for(Country capc:capcontinent)
        {
            if (capc == null) {
                System.out.println("* No null data in each continent report!\n");
                continue;
            }
            System.out.println(capc.getCapital() + "\t" +capc.getName() + "\t" +
                    capc.getPopulation() + "\t" + capc.getContinent() + "\n");
        }
    }

//14. All the capital cities in a region organised by largest to smallest
    public ArrayList<Country> getCapitalCityRegion()
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
            }  else {
                cpcty_region = new ArrayList<>();
                // Return new city if valid.
                // Check one is returned
                while (rset.next()) {
                    Country cpregion = new Country();
                    cpregion.setCapital(rset.getInt("Capital"));
                    cpregion.setName(rset.getString("Name"));
                    cpregion.setRegion(rset.getString("Region"));
                    cpregion.setPopulation(rset.getInt("Population"));

                    cpcty_region.add(cpregion);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city in a region.");
        }
        return cpcty_region;
    }
    public void displayCapitalCityRegion(ArrayList<Country> capregion)
    {

        if (capregion == null)
        {
            System.out.println("* There is no null data in capital city report !\n.");
            return;
        }
        System.out.print("***************************** All the capital cities in a region organised by largest to smallest *****************************\n");
        System.out.printf("%25s%25s%25s%25s%25s","CountryName","Capital", "Population","Region\n");
        System.out.print("********************************************************************************************************************************\n");
        for(Country capr:capregion)
        {
            if (capr == null) {
                System.out.println("* No null data in each continent report!\n");
                continue;
            }
            System.out.printf("%25s%25s%25s%25s%25s",capr.getName(),capr.getCapital(),
                    capr.getPopulation(),capr.getRegion());
            System.out.print("\n");
        }
        System.out.print("********************************************************************************************************************************\n");
    }

//15. Total population of the world, a region, a continent, and a country
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

} /*This is the end of the public App class */