package com.stat.scheduler;

import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class MyCSVReader{
    public List<String[]> read(File file) throws Exception {
        String[] record;
        CSVReader csvReader = null;
        List<String[]> groups = new ArrayList<String[]>();
        try {
            csvReader = new CSVReaderBuilder(new FileReader(file)).withCSVParser(new CSVParserBuilder().build()).build();
            
            //Skip first 2 columns, then song name, artist, 6 performer columns, conflicts, and then prefered area.
            record = csvReader.readNext();
            while ((record = csvReader.readNext()) != null){
                List<String> performers = new ArrayList<>();
                
                for (int i=4; i<10; i++){
                    if (!record[i].isEmpty()){
                        if (record[i].contains("/")){
                            String[] subperformers = record[i].split("/");
                            for (String sub : subperformers){
                                performers.add(sub.replaceAll("[\\s.]","").toLowerCase());
                            }
                        }
                        else
                            performers.add(record[i].replaceAll("[\\s.]","").toLowerCase());
                    }
                }
                String[] newGroup = Stream.concat(Stream.of(record[2],record[3]), performers.stream()).toArray(String[]::new);
                groups.add(newGroup);
            }
        } finally {
            if (csvReader != null){
                csvReader.close();
            }
        }
        return groups;
    }
}
