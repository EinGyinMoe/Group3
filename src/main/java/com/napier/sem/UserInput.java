package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInput {
    public static void main(String[] args) {

        UserInput ui = new UserInput();
//
//        Scanner input = new Scanner(System.in);
//
//        System.out.print("Enter an integer: ");
//        int number = input.nextInt();
//        System.out.println("You entered " + number);

        ArrayList topcont = ui.getTopContinent();
        ui.displayTopContinent(topcont);

        ui.disconnect();
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

    //The top N populated cities in the continent where N is provided by the user.
    public ArrayList<City> getTopContinent()
    {
        ArrayList<City> topcont = null;
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            Scanner input = new Scanner(System.in);

            System.out.print("Enter Continent: ");
            String cont = input.nextLine();
            //System.out.println("You entered " + num);

            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Continent, city.Population "
                            + "FROM city, country "
                            + "WHERE country.Continent=cont "
                            + "ORDER BY city.Population DESC";
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
}