package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * This is the class to be accessed from other functions
 */
public class App {
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

//        ArrayList cpcty_report = a.getCapitalCityReport();
//        a.displayCapitalCityReport(cpcty_report);

//        ArrayList cpcty_world = a.getCapitalCityWorld();
//        a.displayCapitalCityWorld(cpcty_world);

//        ArrayList cpcty_continent = a.getCapitalCityContinent();
//        a.displayCapitalCityContinent(cpcty_continent);

//        ArrayList cpcty_region = a.getCapitalCityRegion();
//        a.displayCapitalCityRegion(cpcty_region);

//        ArrayList crtyTotlPopuWorld = a.getCountryTotalPopuWorld();
//        a.displayCountryTotalPopuWorld(crtyTotlPopuWorld);

//        ArrayList crtyTotlPopuRegion = a.getCountryTotalPopuRegion();
//        a.displayCountryTotalPopuRegion(crtyTotlPopuRegion);

//        ArrayList crtyTotlPopuCont = a.getCountryTotalPopuCont();
//        a.displayCountryTotalPopuCont(crtyTotlPopuCont);

//        ArrayList crtyTotlPopu = a.getCountryTotalPopu();
//        a.displayCountryTotalPopu(crtyTotlPopu);

        ArrayList ctyTotlPopu = a.getCityTotalPopu();
        a.displayCityTotalPopu(ctyTotlPopu);

//        ArrayList topcont = a.getTopContinent();
//        a.displayTopContinent(topcont);

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
public ArrayList<City> getCapitalCityWorld()
{
    ArrayList<City> cpcty_world = null;
    try {
        // Create an SQL statement
        Statement stmt = con.createStatement();

        // Create string for SQL statement
        String strSelect =
                "SELECT city.Name,country.Name, city.Population "
                        + "FROM city,country "
                        + "WHERE city.ID=country.Capital "
                        + "ORDER BY city.Population DESC";
        // Execute SQL statement
        ResultSet rset = stmt.executeQuery(strSelect);
        if (rset == null) {
            System.out.print("Capital Cities in the world Not found.");
        } else {
            cpcty_world=new ArrayList<>();
            // Return new capital city world if valid.
            // Check one is returned.
            while (rset.next()) {
                Country countryworld=new Country();
                City cityworld=new City();
                cityworld.setName(rset.getString(1));
                countryworld.setName(rset.getString(2));
                cityworld.setPopulation(rset.getInt(3));

                cityworld.setCountry(countryworld);
                cpcty_world.add(cityworld);
            }
        }
    }
    catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Failed to get capital city world data.");
    }
    return cpcty_world;
}
    public void displayCapitalCityWorld(ArrayList<City>cpcty_world)
    {
        if (cpcty_world==null)
        {
            System.out.println("* There is null data in capital cities in the world!\n");
            return;
        }
        System.out.print("\n**************************************Capital Cities in World**************************************\n\n");
        System.out.printf("%25s%25s%25s","Capital City","Country","Population\n");
        System.out.print("\n*************************************************************************************************\n\n");

        for (City ctyworld:cpcty_world)
        {
            if(ctyworld == null)
            {
                System.out.println("* Null data in each capital city world!\n");
                continue;
            }
            System.out.printf("%25s%25s%25s",ctyworld.getName(), ctyworld.getCountry().getName(), ctyworld.getPopulation());
            System.out.print("\n");
        }
        System.out.print("\n*************************************************************************************************\n\n");
    }

// capital city in a continent
    public ArrayList<City> getCapitalCityContinent()
    {
        ArrayList<City> cpcty_continent = null;
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name,country.Name, country.Continent, city.Population "
                            + "FROM country,city "
                            + "WHERE city.ID=country.Capital AND country.Continent='Asia' "
                            + "ORDER BY city.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Capital City in a Continent Not Found.");
            } else {
                cpcty_continent=new ArrayList<>();
                // Return new capital city continent if valid.
                // Check one is returned.
                while (rset.next()) {
                    Country crtycontinent=new Country();
                    City ctycontinent=new City();
                    ctycontinent.setName(rset.getString(1));
                    crtycontinent.setName(rset.getString(2));
                    crtycontinent.setContinent(rset.getString(3));
                    ctycontinent.setPopulation(rset.getInt(4));

                    ctycontinent.setCountry(crtycontinent);
                    cpcty_continent.add(ctycontinent);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city in a continent.");
        }
        return cpcty_continent;
    }
    public void displayCapitalCityContinent(ArrayList<City>cpcty_continent)
    {
        if (cpcty_continent == null)
        {
            System.out.println("* There is no null data in capital city in a continent!\n");
            return;
        }
        System.out.print("\n*****************************************Capital Cities in Asia Continent*****************************************\n\n");
        System.out.printf("%25s%25s%25s%25s","Capital City","Country","Continent","Population\n");
        System.out.print("\n******************************************************************************************************************\n\n");

        for (City ctycont:cpcty_continent)
        {
            if(ctycont == null)
            {
                System.out.println("* No null data in each capital city in a continent!\n");
                continue;
            }
            System.out.printf("%25s%25s%25s%25s",ctycont.getName(), ctycont.getCountry().getName(),ctycont.getCountry().getContinent(), ctycont.getPopulation());
            System.out.print("\n");
        }
        System.out.print("\n******************************************************************************************************************\n\n");
    }
    // capital city in a region
    public ArrayList<City> getCapitalCityRegion()
    {
        ArrayList<City> cpcty_region = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name,country.Name, country.Region, city.Population "
                            + "FROM country, city "
                            + "WHERE city.ID=country.Capital AND country.Region='Caribbean' "
                            + "ORDER BY city.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Capital City in Caribbean Not Found.");
            } else {
                cpcty_region=new ArrayList<>();
                // Return new capital city region if valid.
                // Check one is returned.
                while (rset.next()) {
                    Country crtyRegion=new Country();
                    City ctyRegion = new City();
                    ctyRegion.setName(rset.getString(1));
                    crtyRegion.setName(rset.getString(2));
                    crtyRegion.setRegion(rset.getString(3));
                    ctyRegion.setPopulation(rset.getInt(4));

                    ctyRegion.setCountry(crtyRegion);
                    cpcty_region.add(ctyRegion);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city in Caribbean region.");
        }
        return cpcty_region;
    }
    public void displayCapitalCityRegion(ArrayList<City>cpcty_region)
    {
        if (cpcty_region == null)
        {
            System.out.println("* There is no null data in capital city in a regiono!\n");
            return;
        }
        System.out.print("\n*****************************************Capital Cities in Caribbean Region*****************************************\n\n");
        System.out.printf("%25s%25s%25s%25s","Capital City","Country","Region","Population\n");
        System.out.print("\n******************************************************************************************************************\n\n");

        for (City ctyreg:cpcty_region)
        {
            if(ctyreg == null)
            {
                System.out.println("* No null data in each capital city in a region!\n");
                continue;
            }
            System.out.printf("%25s%25s%25s%25s",ctyreg.getName(), ctyreg.getCountry().getName(),ctyreg.getCountry().getRegion(), ctyreg.getPopulation());
            System.out.print("\n");
        }
        System.out.print("\n******************************************************************************************************************\n\n");
    }
    // All the functions related to total population.
    // Total population of the world.
    public ArrayList<Country> getCountryTotalPopuWorld()
    {
        ArrayList<Country> crtyTotlPopuWorld = null;
        try {
            // Create SQL statements for the total population of the world, a region, a continent, and a country.
            Statement stmt = con.createStatement();

            // Create string for total population of a region and a continent SQL statement
            String strSelect =
                    "SELECT SUM(country.Population) as crtytotlworld " +
                            "FROM country ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                crtyTotlPopuWorld=new ArrayList<>();
                // Return new total population of a region and a continent if valid.
                while (rset.next()) {
                    Country totlcrtyworld=new Country();
                    totlcrtyworld.setPopulation(rset.getInt(1));

                    crtyTotlPopuWorld.add(totlcrtyworld);
                    //System.out.printf("Total Population of a Region: " + rset.getLong(1) + "\n" + "Total Population of a Continent: " + rset.getLong(2) + "\n");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the total country population of the world.");
        }
        return crtyTotlPopuWorld;
    }
    public void displayCountryTotalPopuWorld(ArrayList<Country>crtyTotlPopuWorld)
    {
        if (crtyTotlPopuWorld == null)
        {
            System.out.println("* There is null data in total country population of the world!\n");
            return;
        }
        System.out.print("\n**************************************************************\n\n");

        for (Country totlcrtypopuworld:crtyTotlPopuWorld)
        {
            if(totlcrtypopuworld == null)
            {
                System.out.println("* Null data in each total population!\n");
                continue;
            }
            System.out.println("Total Country Population of the world: " + totlcrtypopuworld.getPopulation());
            System.out.print("\n");
        }
        System.out.print("\n**************************************************************\n\n");
    }

    // Total population of a region.
    public ArrayList<Country> getCountryTotalPopuRegion()
    {
        ArrayList<Country> crtyTotlPopuRegion = null;
        try {
            // Create SQL statements for the total population of the world, a region, a continent, and a country.
            Statement stmt = con.createStatement();

            // Create string for total population of a region and a continent SQL statement
            String strSelect =
                    "SELECT SUM(country.Population) as crtytotlworld " +
                            "FROM country " +
                            "WHERE country.Region='Central Africa'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                crtyTotlPopuRegion=new ArrayList<>();
                // Return new total population of a region and a continent if valid.
                while (rset.next()) {
                    Country totlcrtyRegion=new Country();
                    totlcrtyRegion.setPopulation(rset.getInt(1));

                    crtyTotlPopuRegion.add(totlcrtyRegion);
                    //System.out.printf("Total Population of a Region: " + rset.getLong(1) + "\n" + "Total Population of a Continent: " + rset.getLong(2) + "\n");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the total country population of the world.");
        }
        return crtyTotlPopuRegion;
    }
    public void displayCountryTotalPopuRegion(ArrayList<Country>crtyTotlPopuRegion)
    {
        if (crtyTotlPopuRegion == null)
        {
            System.out.println("* There is null data in total country population of Central Africa Region!\n");
            return;
        }
        System.out.print("\n*********************************************************\n\n");

        for (Country totlcrtypopuregion:crtyTotlPopuRegion)
        {
            if(totlcrtypopuregion == null)
            {
                System.out.println("* Null data in each total country population of Central Africa Region!\n");
                continue;
            }
            System.out.println("Total Country Population of Central Africa Region: " + totlcrtypopuregion.getPopulation());
            System.out.print("\n");
        }
        System.out.print("\n*********************************************************\n\n");
    }

    // Total population of a continent.
    public ArrayList<Country> getCountryTotalPopuCont()
    {
        ArrayList<Country> crtyTotlPopuCont = null;
        try {
            // Create SQL statements for the total population of the world, a region, a continent, and a country.
            Statement stmt = con.createStatement();

            // Create string for total population of a region and a continent SQL statement
            String strSelect =
                    "SELECT SUM(country.Population) as crtytotlcont " +
                            "FROM country " +
                            "WHERE country.Continent='Asia'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                crtyTotlPopuCont=new ArrayList<>();
                // Return new total population of a region and a continent if valid.
                while (rset.next()) {
                    Country totlcrtyCont=new Country();
                    totlcrtyCont.setPopulation(rset.getInt(1));

                    crtyTotlPopuCont.add(totlcrtyCont);
                    //System.out.printf("Total Population of a Region: " + rset.getLong(1) + "\n" + "Total Population of a Continent: " + rset.getLong(2) + "\n");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the total country population of Asia Continent.");
        }
        return crtyTotlPopuCont;
    }
    public void displayCountryTotalPopuCont(ArrayList<Country>crtyTotlPopuCont)
    {
        if (crtyTotlPopuCont == null)
        {
            System.out.println("* There is null data in total country population of Asia Continent!\n");
            return;
        }
        System.out.print("\n*********************************************************\n\n");

        for (Country totlcrtypopucont:crtyTotlPopuCont)
        {
            if(totlcrtypopucont == null)
            {
                System.out.println("* Null data in each total country population of Asia Continent!\n");
                continue;
            }
            System.out.println("Total Country Population of Asia Continent: " + totlcrtypopucont.getPopulation());
            System.out.print("\n");
        }
        System.out.print("\n*********************************************************\n\n");
    }

    // Total population of a country
    public ArrayList<Country> getCountryTotalPopu()
    {
        ArrayList<Country> crtyTotlPopu = null;
        try {
            // Create SQL statements for the total population of the world, a region, a continent, and a country.
            Statement stmt = con.createStatement();

            // Create string for total population of a region and a continent SQL statement
            String strSelect =
                    "SELECT country.Population " +
                            "FROM country " +
                            "WHERE country.Name='Angola'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                crtyTotlPopu=new ArrayList<>();
                // Return new total population of a region and a continent if valid.
                while (rset.next()) {
                    Country totlcrty=new Country();
                    totlcrty.setPopulation(rset.getInt(1));

                    crtyTotlPopu.add(totlcrty);
                    //System.out.printf("Total Population of a Region: " + rset.getLong(1) + "\n" + "Total Population of a Continent: " + rset.getLong(2) + "\n");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the total population of Angola country.");
        }
        return crtyTotlPopu;
    }
    public void displayCountryTotalPopu(ArrayList<Country>crtyTotlPopu)
    {
        if (crtyTotlPopu == null)
        {
            System.out.println("* There is null data in total population of Angola country!\n");
            return;
        }
        System.out.print("\n*********************************************************\n\n");

        for (Country totlcrtypopu:crtyTotlPopu)
        {
            if(totlcrtypopu == null)
            {
                System.out.println("* Null data in each total country population of Angola country!\n");
                continue;
            }
            System.out.println("Total Country Population of Angola Country: " + totlcrtypopu.getPopulation());
            System.out.print("\n");
        }
        System.out.print("\n*********************************************************\n\n");
    }

    // Total population of a city
    public ArrayList<City> getCityTotalPopu()
    {
        ArrayList<City> ctyTotlPopu = null;
        try {
            // Create SQL statements for the total population of the world, a region, a continent, and a country.
            Statement stmt = con.createStatement();

            // Create string for total population of a region and a continent SQL statement
            String strSelect =
                    "SELECT city.Population " +
                            "FROM city " +
                            "WHERE city.Name='Herat'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                ctyTotlPopu=new ArrayList<>();
                // Return new total population of a region and a continent if valid.
                while (rset.next()) {
                    City totlcty=new City();
                    totlcty.setPopulation(rset.getInt(1));

                    ctyTotlPopu.add(totlcty);
                    //System.out.printf("Total Population of a Region: " + rset.getLong(1) + "\n" + "Total Population of a Continent: " + rset.getLong(2) + "\n");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the total population of Herat City.");
        }
        return ctyTotlPopu;
    }
    public void displayCityTotalPopu(ArrayList<City>ctyTotlPopu)
    {
        if (ctyTotlPopu == null)
        {
            System.out.println("* There is null data in total population of Herat City!\n");
            return;
        }
        System.out.print("\n*********************************************************\n\n");

        for (City totlctypopu:ctyTotlPopu)
        {
            if(totlctypopu == null)
            {
                System.out.println("* Null data in each total population of Herat city!\n");
                continue;
            }
            System.out.println("Total Country Population of Herat City: " + totlctypopu.getPopulation());
            System.out.print("\n");
        }
        System.out.print("\n*********************************************************\n\n");
    }

    // Total city population in a district.
    public ArrayList<City> getCityTotalPopuDistrict()
    {
        ArrayList<City> ctyTotlPopuDistrict = null;
        try {
            // Create SQL statements for the total population of the world, a region, a continent, and a country.
            Statement stmt = con.createStatement();

            // Create string for total population of a region and a continent SQL statement
            String strSelect =
                    "SELECT SUM(city.Population) as totaldistrict " +
                            "FROM city " +
                            "WHERE city.District='Kabol'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                ctyTotlPopuDistrict=new ArrayList<>();
                // Return new total population of a district if valid.
                while (rset.next()) {
                    City totlctyDistrict=new City();
                    totlctyDistrict.setPopulation(rset.getInt(1));

                    ctyTotlPopuDistrict.add(totlctyDistrict);
                    //System.out.printf("Total Population of a Region: " + rset.getLong(1) + "\n" + "Total Population of a Continent: " + rset.getLong(2) + "\n");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the total country population of the world.");
        }
        return ctyTotlPopuDistrict;
    }
    public void displayCityTotalPopuDistrict(ArrayList<City>ctyTotlPopuDistrict)
    {
        if (ctyTotlPopuDistrict == null)
        {
            System.out.println("* There is null data in total city population in Kabol District!\n");
            return;
        }
        System.out.print("\n*********************************************************\n\n");

        for (City totlctypopudistrict:ctyTotlPopuDistrict)
        {
            if(totlctypopudistrict == null)
            {
                System.out.println("* Null data in each total city population of Kabol district!\n");
                continue;
            }
            System.out.println("Total City Population in Kabol District: " + totlctypopudistrict.getPopulation());
            System.out.print("\n");
        }
        System.out.print("\n*********************************************************\n\n");
    }

    //The top N populated cities in the continent where N is provided by the user.
    public ArrayList<City> getTopContinent()
    {
        ArrayList<City> topcont = null;
        try {
            // Create an SQL statement
            //System.out.println("You entered " + num);
            Statement stmt = con.createStatement();
//            Scanner input = new Scanner(System.in);
//            System.out.print("Enter text: ");
//            String cont = input.next();

            DataInputStream KB=new DataInputStream(System.in);

            //input a particular employee id of which we want to display record
            System.out.print("Enter Continent:");
            String cont=KB.readLine();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Continent, city.Population "
                            + "FROM city, country "
                            + "WHERE country.Continent='\"+eid+\"' "
                            + "ORDER BY city.Population DESC";

//            Scanner input = new Scanner(System.in);
//            System.out.print("Enter Continent: ");
//            String cont = input.nextLine();


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Capital City Report Not found.");
            } else {
                topcont=new ArrayList<>();
                // Return new capital city if valid.
                // Check one is returned.
                while (rset.next()) {
                    City city=new City();
                    Country country=new Country();
                    city.setName(rset.getString(1));
                    country.setName(rset.getString(2));
                    city.setPopulation(rset.getInt(3));

                    city.setCountry(country);
                    topcont.add(city);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get N Continent.");
        }
        return topcont;
    }
    public void displayTopContinent(ArrayList<City>topcont)
    {
        if (topcont==null)
        {
            System.out.println("* There is no null data in top continent!\n");
            return;
        }
        System.out.print("\n**************************************Top Continent**************************************\n\n");
        System.out.printf("%25s%25s%25s","City","Continent","Population\n");
        System.out.print("\n***********************************************************************************************\n\n");

        for (City cty:topcont)
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
} /*This is the end of the public App class */