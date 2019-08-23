package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");
        {
            System.out.println("\n................................\n" +
                    "Integration_Testing For WorldFact\n" +
                    "................................");
        }
    }

    @Test
    void testgetCountryByCode() throws SQLException
    {
        Country ctry = app.getCountryByCode("ABW");
        assertEquals(ctry.getCode(), "ABW");
        assertEquals(ctry.getName(), "Aruba");
        assertEquals(ctry.getPopulation(), 103000);
    }



}