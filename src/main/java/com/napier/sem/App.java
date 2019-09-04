package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
/*** This is the class to be accessed from other function*/

public class App {
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        if (args.length < 1)
        {
            a.connect("localhost:3306");
            a.displayapp();  // Input Display Method
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
//        ArrayList ctrept = a.getCity_report();
//        a.displayCityReport(ctrept);
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
//        ArrayList cpcty_report = a.getCapitalCityReport();
//        a.displayCapitalCityReport(cpcty_report);
//
//        //12. capital city world
//        ArrayList cpcty_world = a.getCapitalCityWorld();
//        a.displayCapitalCityWorld(cpcty_world);
//
//        //13. capital city continent
//        ArrayList cpcty_continent = a.getCapitalCityContinent();
//        a.displayCapitalCityContinent(cpcty_continent);

        //14. capital city region
//        ArrayList cpcty_region = a.getCapitalCityRegion();
//        a.displayCapitalCityRegion(cpcty_region);

        // 15. total country population in the world
//        ArrayList crtyTotlPopuWorld= a.getCountryTotalPopuWorld();
//        a.displayCountryTotalPopuWorld(crtyTotlPopuWorld);

        //16. total country population of a region
//        ArrayList crtyTotlPopuRegion= a.getCountryTotalPopuRegion();
//        a.displayCountryTotalPopuRegion(crtyTotlPopuRegion);

        // 17. total country population of a continent
//        ArrayList crtyTotlPopuContinent= a.getCountryTotalPopuCont();
//        a.displayCountryTotalPopuCont(crtyTotlPopuContinent);

        // 18. total country population of a country
//        ArrayList crtyTotlPopu= a.getCountryTotalPopu();
//        a.displayCountryTotalPopu(crtyTotlPopu);

        // 19. total city population
//        ArrayList ctyTotlPopu= a.getCityTotalPopu();
//        a.displayCityTotalPopu(ctyTotlPopu);

        //20. total city population in a district
//        ArrayList ctyTotlPopuDistrict= a.getCityTotalPopuDistrict();
//        a.displayCityTotalPopuDistrict(ctyTotlPopuDistrict);
        
//        Cherry Get Function
//      get population in the cities people who live and not living in each region
//        a.getLivePopulationRegion();

//        ArrayList livpopuregion= a.getLivePopulationRegion();
//        a.displayLivePopulationRegion(livpopuregion);
//
//        ArrayList livpopucrty= a.getLivePopulationCountry();
//        a.displayLivePopulationCountry(livpopucrty);
//
//        ArrayList livpopucont= a.getLivePopulationContinent();
//        a.displayLivePopulationContinent(livpopucont);

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

    public void displayapp()
    {
        Scanner Obj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("\n");
        System.out.println("*****************************************");
        System.out.println("*Welcome to WorldFact Book Report System*");
        System.out.println("*****************************************");
        boolean again = false;
        do {
            System.out.println("Choose Your Options for the following Report:");
            System.out.println(
                            "1. About Country Report " + "\n" +
                            "2. About City Report " + "\n" +
                            "3. About Capital City Report "+ "\n" +
                            "4. About Population Report "+ "\n" +
                            "5. About Population According To User-Input "+ "\n" +
                            "6. About Language Report "+ "\n"

            );
            System.out.print("Enter your option: ");
            int reportOp = Obj.nextInt();  // Read user input
            System.out.println("\n");

            // Country Report
            if (reportOp == 1)
            {
                System.out.println("****************");
                System.out.println("*Country Report*");
                System.out.println("****************");
                System.out.println(
                                "1. All the countries in the world organised by largest population to smallest." + "\n" +
                                "2. All the countries in a continent organised by largest population to smallest." + "\n" +
                                "3. All the countries in a region organised by largest population to smallest."+ "\n"
                );
                System.out.print("Enter an option : ");
                int CountryReport = Obj.nextInt();  // Read user input

                if (CountryReport == 1)
                {
                    ArrayList countryworld = getCountryWorld();
                    displayCountryWorld(countryworld);
                    again = Question();
                }
                else if (CountryReport == 2)
                {
                    ArrayList countrycontinent = getCountryContinent();
                    displayCountryContinent(countrycontinent);
                    again = Question();
                }
                else if (CountryReport == 3)
                {
                    ArrayList countryregion = getCountryRegion();
                    displayCountryRegion(countryregion);
                    again = Question();
                }
                else
                {
                    again = true;
                }
            }

            // City_Report
            else if(reportOp == 2)
            {
                System.out.println("*************");
                System.out.println("*City Report*");
                System.out.println("*************");
                System.out.println(
                                "1. All the cities in the world organised by largest population to smallest. " + "\n" +
                                "2. All the cities in a continent organised by largest population to smallest. " + "\n" +
                                "3. All the cities in a region organised by largest population to smallest. " + "\n" +
                                "4. All the cities in a country organised by largest population to smallest. " + "\n" +
                                "5. All the cities in a district organised by largest population to smallest."+ "\n"
                );
                System.out.print("Enter your option: ");
                int CityReport = Obj.nextInt();  // Read user input

                if (CityReport == 1)
                {
                    ArrayList ctyworld = getCity_world();
                    displayCityWorld(ctyworld);
                    again = Question();
                }
                else if (CityReport == 2)
                {
                    ArrayList cty = getCityContinent();
                    displayCityContinent(cty);
                    again = Question();
                }
                else if (CityReport == 3)
                {
                    ArrayList ctyregion = getCityRegion();
                    displayCityRegion(ctyregion);
                    again = Question();
                }
                else if (CityReport == 4)
                {
                    ArrayList ctycountry = getCityCountry();
                    displayCityCountry(ctycountry);
                    again = Question();
                }
                else if (CityReport == 5)
                {
                    ArrayList ctydistrict = getCityDistrict();
                    displayCityDistrict(ctydistrict);
                    again = Question();
                }
                else{
                    again = true;
                }
            }

            // Capital_City_Report
            else if (reportOp == 3)
            {
                System.out.println("*********************");
                System.out.println("*Capital City Report*");
                System.out.println("*********************");
                System.out.println
                                (
                                "1. All the capital cities in the world organised by largest population to smallest. " + "\n" +
                                "2. All the capital cities in a continent organised by largest population to smallest. " + "\n" +
                                "3. All the capital cities in a region organised by largest to smallest. " + "\n" );
                System.out.print("Enter your option: ");
                int CapCityReport = Obj.nextInt();  // Read user input

                if (CapCityReport == 1)
                {
                    ArrayList cpcty_world = getCapitalCityWorld();
                    displayCapitalCityWorld(cpcty_world);
                    again = Question();
                }
                else if (CapCityReport == 2)
                {
                    ArrayList cpcty_continent = getCapitalCityContinent();
                    displayCapitalCityContinent(cpcty_continent);
                    again = Question();
                }
                else if (CapCityReport == 3)
                {
                    ArrayList cpcty_region = getCapitalCityRegion();
                    displayCapitalCityRegion(cpcty_region);
                    again = Question();
                }
                else{
                    again = true;
                }
            }


            // Population Report
            if (reportOp == 4)
            {
                System.out.println("*******************");
                System.out.println("*Population Report*");
                System.out.println("*******************");
                System.out.println(
                        "1. The population of the world." + "\n" +
                        "2. The population of a continent." + "\n" +
                        "3. The population of a region." + "\n" +
                        "4. The population of a country." + "\n" +
                        "5. The population of a district." + "\n" +
                        "6. The population of a city." + "\n" +
                        "7. The population of people, people living in cities, and people not living in cities in each continent." + "\n" +
                        "8. The population of people, people living in cities, and people not living in cities in each region." + "\n" +
                        "9. The population of people, people living in cities, and people not living in cities in each country." + "\n"

                );
                System.out.print("Enter an option : ");
                int PopuReport = Obj.nextInt();  // Read user input

                if (PopuReport == 1)
                {
                    ArrayList crtyTotlPopuWorld= getCountryTotalPopuWorld();
                    displayCountryTotalPopuWorld(crtyTotlPopuWorld);
                    again = Question();
                }
                else if (PopuReport == 2)
                {
                    ArrayList crtyTotlPopuContinent= getCountryTotalPopuCont();
                    displayCountryTotalPopuCont(crtyTotlPopuContinent);
                    again = Question();
                }
                else if (PopuReport == 3)
                {
                    ArrayList crtyTotlPopuRegion= getCountryTotalPopuRegion();
                    displayCountryTotalPopuRegion(crtyTotlPopuRegion);
                    again = Question();
                }
                else if (PopuReport == 4)
                {
//                    ArrayList countryregion = getCountryRegion();
//                    displayCountryRegion(countryregion);
//                    again = Question();
                }
                else if (PopuReport == 5)
                {
//                    ArrayList countryregion = getCountryRegion();
//                    displayCountryRegion(countryregion);
//                    again = Question();
                }
                else if (PopuReport == 6)
                {
                    ArrayList ctyTotlPopu= getCityTotalPopu();
                    displayCityTotalPopu(ctyTotlPopu);
                    again = Question();
                }
                else if (PopuReport == 7)
                {
                    ArrayList popuContinent = getLivePopulationContinent();
                    displayLivePopulationContinent(popuContinent);
                    again = Question();
                }
                else if (PopuReport == 8)
                {
                    ArrayList popuRegion = getLivePopulationRegion();
                    displayLivePopulationRegion(popuRegion);
                    again = Question();
                }
                else if (PopuReport == 9)
                {
                    ArrayList popuCountry = getLivePopulationCountry();
                    displayLivePopulationCountry(popuCountry);
                    again = Question();
                }
                else
                {
                    again = true;
                }
            }

            // Population According to USer_input Report
            if (reportOp == 5)
            {
                System.out.println("************************************");
                System.out.println("*Population According to User_input*");
                System.out.println("************************************");
                System.out.println(
                        "1. The top N populated countries in the world where N is provided by the user." + "\n" +
                                "2. The top N populated countries in a continent where N is provided by the user." + "\n" +
                                "3. The top N populated countries in a region where N is provided by the user." + "\n" +
                                "4. The top N populated cities in the world where N is provided by the user." + "\n" +
                                "5. The top N populated cities in a continent where N is provided by the user." + "\n" +
                                "6. The top N populated cities in a region where N is provided by the user." + "\n" +
                                "7. The top N populated cities in a country where N is provided by the user." + "\n" +
                                "8. The top N populated cities in a district where N is provided by the user." + "\n" +
                                "9. The top N populated capital cities in the world where N is provided by the user." + "\n" +
                                "10. The top N populated capital cities in a continent where N is provided by the user." + "\n" +
                                "11. The top N populated capital cities in a region where N is provided by the user." + "\n"

                );
                System.out.print("Enter an option : ");
                int PopuReport_input = Obj.nextInt();  // Read user input

                if (PopuReport_input == 1)
                {
//                    ArrayList countryworld = getCountryWorld();
//                    displayCountryWorld(countryworld);
//                    again = Question();
                }
                else if (PopuReport_input == 2)
                {
//                    ArrayList countrycontinent = getCountryContinent();
//                    displayCountryContinent(countrycontinent);
//                    again = Question();
                }
                else if (PopuReport_input == 3)
                {
//                    ArrayList countryregion = getCountryRegion();
//                    displayCountryRegion(countryregion);
//                    again = Question();
                }
                else if (PopuReport_input == 4)
                {
//                    ArrayList countryregion = getCountryRegion();
//                    displayCountryRegion(countryregion);
//                    again = Question();
                }
                else if (PopuReport_input == 5)
                {
//                    ArrayList countryregion = getCountryRegion();
//                    displayCountryRegion(countryregion);
//                    again = Question();
                }
                else if (PopuReport_input == 6)
                {
//                    ArrayList countryregion = getCountryRegion();
//                    displayCountryRegion(countryregion);
//                    again = Question();
                }
                else if (PopuReport_input == 7)
                {
//                    ArrayList countryregion = getCountryRegion();
//                    displayCountryRegion(countryregion);
//                    again = Question();
                }
                else if (PopuReport_input == 8)
                {
                    ArrayList popuRegion = getLivePopulationRegion();
                    displayLivePopulationRegion(popuRegion);
                    again = Question();
                }
                else if (PopuReport_input == 9)
                {
//                    ArrayList countryregion = getCountryRegion();
//                    displayCountryRegion(countryregion);
//                    again = Question();
                }
                else if (PopuReport_input == 10)
                {
//                    ArrayList countryregion = getCountryRegion();
//                    displayCountryRegion(countryregion);
//                    again = Question();
                }
                else if (PopuReport_input == 11)
                {
//                    ArrayList countryregion = getCountryRegion();
//                    displayCountryRegion(countryregion);
//                    again = Question();
                }
                else
                {
                    again = true;
                }
            }

            // Capital_City_Report
            else if (reportOp == 6)
            {
                System.out.println("*****************");
                System.out.println("*Language Report*");
                System.out.println("*****************");
                System.out.println
                        (
                                "1. All the capital cities in the world organised by largest population to smallest. " + "\n" +
                                "2. All the capital cities in a continent organised by largest population to smallest. " + "\n" +
                                "3. All the capital cities in a region organised by largest to smallest. " + "\n" );
                System.out.print("Enter your option: ");
                int CapCityReport = Obj.nextInt();  // Read user input

            }
            else
            {
                System.out.println("Invalid Input!");  // Output user input
            }
        }
        while (again == true);

    }

//    public String Continent()
//    {
//        Scanner Obj = new Scanner(System.in);  // Create a Scanner object
//        System.out.println(
//                "Choose one of the following continent " + "\n" +
//                        "('Asia','Oceania','Antarctica','Europe','North America','Africa','South America')");
//        System.out.print("Enter a continent: ");
//        String continent = Obj.nextLine();
//        System.out.println("\n");
//        return continent;
//    }
//
//    String Country()
//    {
//        Scanner Obj = new Scanner(System.in);  // Create a Scanner object
//        System.out.print(
//                "Enter a country : ");
//        String country = Obj.nextLine();
//        System.out.println("\n");
//        return country;
//    }
//
//
//    public String Region()
//    {
//        Scanner Obj = new Scanner(System.in);  // Create a Scanner object
//        System.out.print(
//                "Choose a region of the world " + "\n" +
//                        "('Caribbean','Southern and Central Asia','Central Africa','Southern Europe', 'Eastern Africa', 'Middle East','Polynesia', 'Western Europe', 'Antarctica','South America' etc.)");
//        String region = Obj.nextLine();
//        System.out.println("\n");
//        return region;
//    }

    private boolean Question() {
        Scanner Obj = new Scanner(System.in);
        System.out.print("Do you want to continue or not???(Y/N): ");
        String askQues = Obj.nextLine();  // To read the user input....
        askQues = askQues.toLowerCase();

        if (askQues.equals("y"))
        {
            System.out.println("\n");
            return true;
        }
        else if (askQues.equals("n"))
        {
            System.out.println("\n");
            return false;
        }
        else
        {
            System.out.println("Invalid Input! Please Try Again....");
            return Question();
        }
    }

//8.
//Option 1.   all the countries in the world by the population
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
                
                while (rset.next()) {
                    Country country = new Country();
                    country.setName(rset.getString("Name"));
                    country.setPopulation(rset.getLong("Population"));

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
            System.out.println("* There is null data in Country World!\n");
            return;
        }
        System.out.print("============== All the Countries in the World organised by Largest Population to Smallest ==============\n");
        System.out.printf("%25s%25s","CountryName","Population\n");
        System.out.print("========================================================================================================\n");
        for(Country ct:countryWorld)
        {
            if (ct == null) {
                System.out.println("* No null data in each CountryWorld!\n ");
                continue;
            }
            System.out.printf("%25s%25s",ct.getName(), ct.getPopulation());
            System.out.print("\n");
        }
        System.out.print("========================================================================================================\n");

    }

//6.
//Country Report Option 2. All the country in a continent by Population
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
                    country.setPopulation( rset.getLong("Population"));
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
        System.out.print("============= All the Countries in a Continent organised by Largest Population to Smallest =============\n");
        System.out.printf("%25s%25s%25s","CountryName","Population","Continent\n");
        System.out.print("========================================================================================================\n");
        for(Country ct:countryContinent)
        {
            if (ct == null) {
                System.out.println("* No null data in each CountryContinent!\n");
                continue;
            }
            System.out.printf("%25s%25s%25s",ct.getName(), ct.getPopulation(), ct.getContinent());
            System.out.print("\n");
        }
    }

//7.
//Country Report Option 3. all the countries in a region by the population
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
                    country.setPopulation( rset.getLong("Population"));
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
        System.out.print("============= All the Countries in a Region organised by Largest Population to Smallest =============\n");
        System.out.printf("%25s%25s%25s","CountryName","Population","Region\n");
        System.out.print("========================================================================================================\n");
        for(Country ct:countryRegion)
        {
            if (ct == null) {
                System.out.println("* No null data in each CountryRegion!\n");
                continue;
            }
            System.out.printf("%25s%25s%25s",ct.getName(), ct.getPopulation(), ct.getRegion());
            System.out.print("\n");
        }
    }


//9.
//City Report Option 1. cities in the world by population
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
                    city.setPopulation(rset.getLong("Population"));

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
        System.out.print("=============== All the Cities in the World organised by Largest Population to Smallest ==============\n");
        System.out.printf("%25s%25s","CityName","Population\n");
        System.out.print("=======================================================================================================\n");
        for(City c:ctyWorld)
        {
            if (c == null) {
                System.out.println("* No null data in each CityWorld!\n");
                continue;
            }
            System.out.printf("%25s%25s",c.getName(), c.getPopulation());
            System.out.print("\n");
        }
        System.out.print("=======================================================================================================\n");

    }

//2.
// City Report Option 2. All the cities in a continent organised by largest population to smallest
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
                    ct.setPopulation(rset.getLong(2));
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
        System.out.print("========================================================================================================\n");
    }

//4.
//City Report Option 3. All the city population in a region
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
                while (rset.next()) {
                    City cty2=new City();
                    Country c2 =new Country();
                    cty2.setName(rset.getString(1));
                    cty2.setPopulation(rset.getLong(2));
                    c2.setRegion(rset.getString(3));//set continent value to country object
                    cty2.setCountry(c2);
                    region.add(cty2);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city name by region");
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
        System.out.print("=============== All the Cities in a Region organised by Largest Population to Smallest ===============\n");
        System.out.printf("%25s%25s%25s","CityName","Population","Region\n");
        System.out.print("======================================================================================================\n");
        for(City c:region)
        {
            if (c == null) {
                System.out.println("* No null data in each CityRegion!\n");
                continue;
            }
            System.out.printf("%25s%25s%25s",c.getName(), c.getPopulation(), c.getCountry().getRegion());
            System.out.print("\n");
        }
        System.out.print("======================================================================================================\n");
    }

//5.
//City Report Option 4. All the cities in a country organised by largest population to smallest.
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
                System.out.print("City Report Not found!");
            } else {
                // Return city if valid.
                while (rset.next()) {
                    City cty3=new City();
                    Country c3 =new Country();
                    cty3.setName(rset.getString(1));
                    cty3.setPopulation(rset.getLong(3));
                    c3.setName(rset.getString(2));//set continent value to country object
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
        System.out.print("============= All the Cities in a Country organised by Largest Population to Smallest ==============\n");
        System.out.printf("%25s%25s%25s","CityName","Population","Country\n");
        System.out.print("======================================================================================================\n");
        for(City c:cityCountry)
        {
            if (c == null) {
                System.out.println("* No null data in each City Country!\n ");
                continue;
            }
            System.out.printf("%25s%25s%25s",c.getName(), c.getPopulation(), c.getCountry().getName());
            System.out.print("\n");
        }
    }

// 1.
// City Report Option 5. All the cities in a district organised by largest population to smallest
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
                    cities.setPopulation( rset.getLong("Population"));
                    cities.setDistrict(rset.getString("District"));
                    ctydistrict.add(cities);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city name from a district");
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

        System.out.print("=============== All the Cities in a Cistrict organised by Largest Population to Smallest ===============\n");
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

//12.
// Capital City Report Option 1.
// All the capital cities in the world organised by largest population to smallest.
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
                    cityworld.setPopulation(rset.getLong(3));

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
            System.out.println("* There is no null data in capital cities in the world!\n");
            return;
        }
        System.out.print("======= All the Capital Cities in the World organised by Largest Population to Smallest =======\n");
        System.out.printf("%25s%25s%25s","CapitalCity","Country","CityPopulation\n");
        System.out.print("===============================================================================================\n");

        for (City ctyworld:cpcty_world)
        {
            if(ctyworld == null)
            {
                System.out.println("* No null data in each capital city world!\n");
                continue;
            }
            System.out.printf("%25s%25s%25s",ctyworld.getName(), ctyworld.getCountry().getName(), ctyworld.getPopulation());
            System.out.print("\n");
        }
        System.out.print("===============================================================================================\n");
    }

//13.
// Capital City Report Option 2. All the capital cities in a continent organised by largest population to smallest
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
                    ctycontinent.setPopulation(rset.getLong(4));

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
        System.out.print("================ All the Capital Cities in a Continent organised by Largest Population to Smallest ================\n");
        System.out.printf("%25s%25s%25s%25s","CapitalCity","CountryName","Continent","CityPopulation\n");
        System.out.print("===================================================================================================================\n");

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
        System.out.print("===================================================================================================================\n");
    }

    //14.
//Capital City Report Option 3. All the capital cities in a region organised by largest to smallest
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
                    ctyRegion.setPopulation(rset.getLong(4));

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
            System.out.println("* There is null data in capital city in a region!\n");
            return;
        }
        System.out.print("======================= All the Capital Cities in a Region organised by Largest to Smallest =======================\n");
        System.out.printf("%25s%25s%25s%25s","CapitalCity","Country","Region","CityPopulation\n");
        System.out.print("===================================================================================================================\n");

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
        System.out.print("===================================================================================================================\n");
    }

    //15.
// Population Report Option 1. Total population of the world.
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
                    totlcrtyworld.setPopulation(rset.getLong(1));

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
        System.out.print("================================================================\n\n");

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
        System.out.print("================================================================\n\n");
    }

    //17.
// Population Report Option 2. Total population of a continent.
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
                    totlcrtyCont.setPopulation(rset.getLong(1));

                    crtyTotlPopuCont.add(totlcrtyCont);
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
        System.out.print("===========================================================\n");

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
        System.out.print("===========================================================\n");
    }

    // 16.
// Population Report Option 3. Total country population of a region.
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
                    totlcrtyRegion.setPopulation(rset.getLong(1));

                    crtyTotlPopuRegion.add(totlcrtyRegion);
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
        System.out.print("========================================================\n");

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
        System.out.print("========================================================\n");
    }

    //19.
// Population Report Option 6. Total population of a city
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
                    totlcty.setPopulation(rset.getLong(1));

                    ctyTotlPopu.add(totlcty);
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
        System.out.print("=========================================================\n");

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
        System.out.print("=========================================================\n");
    }

// Population Report Option 7. The population of people, people living in cities, and people not living in cities in each continent
//
    public ArrayList<Population> getLivePopulationContinent()

    {
        ArrayList<Population> livpopucontinent=null;
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Continent, SUM(country.Population) as CountryTotalPopulation, SUM((select SUM(population) from city where countrycode = country.code)) as PeopleLiveInCity, "
                            + "(SUM((select SUM(population) from city where countrycode = country.code)) / SUM(country.Population))*100 as PercentagePeopleLiveInCity, "
                            + " (sum(country.Population)-SUM((select SUM(population) from city where countrycode = country.code))) as PeopleNotLiveInCity, "
                            + " ((sum(country.Population)-SUM((select SUM(population) from city where countrycode = country.code))) / SUM(country.Population))*100 as PercentagePeopleNotInCity"
                            + " FROM country GROUP BY country.Continent;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                livpopucontinent=new ArrayList<>();
                // Return new city if valid.
                // Check one is
                while (rset.next()) {
                    Country c=new Country();
                    Population popu = new Population();
                    c.setContinent(rset.getString(1));

                    popu.setCountry(c);
                    popu.setTotalPopulation(rset.getLong(2));
                    popu.setPopulationinCity(rset.getLong(3));
                    popu.setPercentinCity(rset.getFloat(4));
                    popu.setPopulationNotinCity(rset.getLong(5));
                    popu.setPercentNotinCity(rset.getFloat(6));

                    livpopucontinent.add(popu);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get people who are not living in the city by region");
        }
        return livpopucontinent;
    }
    public void displayLivePopulationContinent(ArrayList<Population> livepopucontinent)
    {
        if (livepopucontinent == null)
        {
            System.out.println("* There is no null data in total population of people living in a country of a continent!\n");
            return;
        }
        System.out.print("=============================================== People Living and Not Living in the Country of a Continent ================================================\n");
        System.out.printf("%25s%25s%25s%25s%25s%25s","Continent","CountryPopulation","LivingPopulation","LivingPercentage","NotLivingPopulation","NotLivingPercentage\n");
        System.out.print("=========================================================================================================================================================\n");

        for (Population pop:livepopucontinent)
        {
            if(pop == null)
            {
                System.out.println("* No null data in each total population of people living in a country of a continent!\n");
                continue;
            }
            System.out.printf("%25s%25s%25s%25s%25s%25s",pop.getCountry().getContinent(),pop.getTotalPopulation(), pop.getPopulationinCity(),
                    pop.getPercentinCity(), pop.getPopulationNotinCity(), pop.getPercentNotinCity());
            System.out.print("\n");
        }
        System.out.print("=========================================================================================================================================================\n");
    }

// Population Report Option 8.The population of people, people living in cities, and people not living in cities in each region
//
    public ArrayList<Population> getLivePopulationRegion()

    {
        ArrayList<Population>livepopuregion=null;
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statements
            String strSelect =
                    "SELECT country.Region, SUM(country.Population) as CountryTotalPopulation, SUM((select SUM(population) from city where countrycode = country.code)) as PeopleLiveInCity,"
                            +" (SUM((select SUM(population) from city where countrycode = country.code)) / SUM(country.Population))*100 as PercentagePeopleLiveInCity , (SUM(country.Population)-SUM((select SUM(population) from city where countrycode = country.code))) as PeopleNotInCity,"
                            +" ((SUM(country.Population)-SUM((select SUM(population) from city where countrycode = country.code))) / SUM(country.Population))*100 as PercentagePeopleNotInCity"
                            +" FROM country"
                            +" GROUP BY country.Region;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                livepopuregion=new ArrayList<>();

                while (rset.next()) {
                    Country c = new Country();
                    Population popu = new Population();
                    c.setRegion(rset.getString(1));

                    popu.setCountry(c);
                    popu.setTotalPopulation(rset.getLong(2));
                    popu.setPopulationinCity(rset.getLong(3));
                    popu.setPercentinCity(rset.getFloat(4));
                    popu.setPopulationNotinCity(rset.getLong(5));
                    popu.setPercentNotinCity(rset.getFloat(6));
                    livepopuregion.add(popu);

                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get people who are not living in the country by region");
        }
        return livepopuregion;
        //return population calculation
    }
    public void displayLivePopulationRegion(ArrayList<Population> livepopuregion)
    {
        if (livepopuregion == null)
        {
            System.out.println("* There is no null data in total population of people living in a country!\n");
            return;
        }
        System.out.print("=============================================== People Living and Not Living in the Country of a Region ================================================\n");
        System.out.printf("%25s%25s%25s%25s%25s%25s","Region","CountryPopulation","LivingInCity","PercentageOfLivinginCity","NotLivingInCity","PercentageOfNotLivingInCity\n");
        System.out.print("=========================================================================================================================================================\n");

        for (Population pop:livepopuregion)
        {
            if(pop == null)
            {
                System.out.println("* No null data in each total population of people living in a country!\n");
                continue;
            }
            System.out.printf("%25s%25s%25s%25s%25s%25s",pop.getCountry().getRegion(), pop.getTotalPopulation(),pop.getPopulationinCity(),
                    pop.getPercentinCity(), pop.getPopulationNotinCity(), pop.getPercentNotinCity());
            System.out.print("\n");
        }
        System.out.print("=========================================================================================================================================================\n");
    }

// Population Report Option 9. The population of people, people living in cities, and people not living in cities in each Country
//
    public ArrayList<Population> getLivePopulationCountry()

    {
        ArrayList<Population>livepopucrty=null;
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.name, country.Population, country.Population-sum(city.Population) as 'PeopleNotInCity', "
                            + "((country.Population-sum(city.Population))/country.Population)*100 as 'PercentagePeopleNotInCity', country.Population - (country.Population-sum(city.Population)) as 'PeopleLiveInCity', "
                            + "((country.Population - (country.Population-sum(city.Population)))/country.Population)*100 as 'PercentagePeopleLiveInCity' "
                            + "FROM country country join city city on country.code = city.countrycode "
                            + "WHERE city.CountryCode = country.Code GROUP BY country.Name,"
                            + "country.Population ORDER BY country.Name;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.print("Not found.");
            } else {
                livepopucrty = new ArrayList<>();
                // Return new city if valid.
                // Check one is
                while (rset.next()) {
                    Country c=new Country();
                    Population popu = new Population();

                    c.setName(rset.getString(1));
                    c.setPopulation(rset.getLong(2));

                    popu.setCountry(c);
                    popu.setPopulationinCity(rset.getLong(5));
                    popu.setPercentinCity(rset.getFloat(6));
                    popu.setPopulationNotinCity(rset.getLong(3));
                    popu.setPercentNotinCity(rset.getFloat(4));
                    livepopucrty.add(popu);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get people who are not living in the city.");
        }
        return livepopucrty;
        //return country;
    }
    public void displayLivePopulationCountry(ArrayList<Population>livepopucrty)
    {
        if (livepopucrty == null)
        {
            System.out.println("* There is no null data in total population of people living in a city!\n");
            return;
        }
        System.out.print("=========================== The Population of People, People living in Cities, and People not living in Cities in each Country ==========================\n");
        System.out.printf("%25s%25s%25s%25s%25s%25s","Country","CountryPopulation","PeopleLivingInCity","LivingPercentage","PeopleNotLivingInCity","NotLivingPercentage\n");
        System.out.print("=========================================================================================================================================================\n");

        for (Population pop:livepopucrty)
        {
            if(pop == null)
            {
                System.out.println("* null data in each total population of people living in a city!\n");
                continue;
            }
            System.out.printf("%25s%25s%25s%25s%25s%25s",pop.getCountry().getName(), pop.getCountry().getPopulation(),pop.getPopulationinCity(), pop.getPercentinCity(),pop.getPopulationNotinCity(),pop.getPercentNotinCity());
            System.out.print("\n");
        }
        System.out.print("=========================================================================================================================================================\n");
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
                    cty.setPopulation(rset.getLong(2));
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
        System.out.print("=============== All the cities in a Region organised by largest population to smallest ===============\n");
        System.out.printf("%25s%25s%25s","Name","Population","Continent","Country Name\n");
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
                c.setPopulation(rset.getLong(3));
                c.setContinent(rset.getString(4));

            }


        }
       return c;
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
                    crty10.setPopulation(rset.getLong("Population"));
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
                    Country countryreport=new Country();
                    City cityreport=new City();
                    cityreport.setName(rset.getString(1));
                    countryreport.setName(rset.getString(2));
                    cityreport.setPopulation(rset.getLong(3));

                    cityreport.setCountry(countryreport);
                    cpcty_report.add(cityreport);
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




//    ******Cherry Coding (People live and not living in each region, country, continent)********



//    End of Cherry Coding



} /*This is the end of the public App class */
