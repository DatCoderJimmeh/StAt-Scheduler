package com.stat.scheduler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{   
    public static void main( String[] args ) throws Exception
    {
        MyCSVReader reader = new MyCSVReader();
        List<String[]> groups = reader.read(new File("C:\\Users\\James McKinney\\OneDrive\\Documents\\GitHub\\StAt-Scheduler\\test.csv"));
        for (String[] group : groups){
            for (String g : group){
                System.out.print(g + " ");
            }
            System.out.println("");
        }
    }
}