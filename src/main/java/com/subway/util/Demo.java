package com.subway.util;

import java.io.*;
import java.util.*;
import com.subway.core.Subway;
import com.subway.core.SubwayLoader;
import com.subway.gui.SubwayPrinter;

public class Demo
{
    public static void main(String[] args) {
        try {
            SubwayLoader loader = new SubwayLoader();
            Subway objectville = loader.loadFromFile(new File("data/ObjectvilleSubway.txt"));
            
            System.out.println("=== Objectville Subway Route Finder ===");
            System.out.println("Available stations:");
            System.out.println("- DRY Drive");
            System.out.println("- Weather-O-Rama, Inc.");
            System.out.println("- Boards 'R' Us");
            System.out.println("- Head First Theater");
            System.out.println("- LSP Lane");
            System.out.println("- JavaBeans Boulevard");
            System.out.println("- OOA&D Oval");
            System.out.println("- Head First Labs");
            System.out.println("- Infinite Circle");
            System.out.println("- Long Way");
            System.out.println("- Null Object");
            System.out.println("- Prime Numbers");
            System.out.println("- Fibonacci");
            System.out.println();
            
            // Test 1: Direct route
            System.out.println("Test 1: Direct route from DRY Drive to Head First Theater");
            System.out.println("========================================================");
            List route1 = objectville.getDirections("DRY Drive", "Head First Theater");
            SubwayPrinter printer1 = new SubwayPrinter(System.out);
            printer1.printDirections(route1);
            System.out.println();
            
            // Test 2: Route with one transfer
            System.out.println("Test 2: Route with transfer from DRY Drive to Infinite Circle");
            System.out.println("=============================================================");
            List route2 = objectville.getDirections("DRY Drive", "Infinite Circle");
            SubwayPrinter printer2 = new SubwayPrinter(System.out);
            printer2.printDirections(route2);
            System.out.println();
            
            // Test 3: Complex route with multiple transfers
            System.out.println("Test 3: Complex route from Weather-O-Rama, Inc. to Prime Numbers");
            System.out.println("================================================================");
            List route3 = objectville.getDirections("Weather-O-Rama, Inc.", "Prime Numbers");
            SubwayPrinter printer3 = new SubwayPrinter(System.out);
            printer3.printDirections(route3);
            System.out.println();
            
            // Test 4: Route to Fibonacci
            System.out.println("Test 4: Route to Fibonacci station");
            System.out.println("===================================");
            List route4 = objectville.getDirections("Boards 'R' Us", "Fibonacci");
            SubwayPrinter printer4 = new SubwayPrinter(System.out);
            printer4.printDirections(route4);
            System.out.println();
            
            System.out.println("=== All tests completed successfully! ===");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 