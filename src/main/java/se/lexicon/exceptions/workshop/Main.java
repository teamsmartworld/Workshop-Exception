package se.lexicon.exceptions.workshop;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import se.lexicon.exceptions.workshop.data_access.NameService;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

public class Main {

    public static void main(String[] args) {
        // Lists to store names
        List<String> maleFirstNames = new ArrayList<>();
        List<String> femaleFirstNames = new ArrayList<>();
        List<String> lastNames = new ArrayList<>();

        // Create an instance of CSVReader_Writer
        CSVReader_Writer csvHandler = new CSVReader_Writer();

        // Reading files with proper exception handling
        try {
            maleFirstNames = csvHandler.getMaleFirstNames();
            femaleFirstNames = csvHandler.getFemaleFirstNames();
            lastNames = csvHandler.getLastNames();

            // Create NameService instance
            NameService nameService = new NameService(maleFirstNames, femaleFirstNames, lastNames);

            // Test creating random person
            Person test = nameService.getNewRandomPerson();
            System.out.println("Random person generated: " + test);

            // Test saving data
            csvHandler.saveMaleNames(maleFirstNames);
            csvHandler.saveFemaleNames(femaleFirstNames);
            csvHandler.saveLastNames(lastNames);

            // Additional testing: Print the size of each list
            System.out.println("Male names loaded: " + maleFirstNames.size());
            System.out.println("Female names loaded: " + femaleFirstNames.size());
            System.out.println("Last names loaded: " + lastNames.size());

            // Generate multiple random persons for testing
            System.out.println("\nGenerating 5 random persons:");
            for (int i = 0; i < 5; i++) {
                Person randomPerson = nameService.getNewRandomPerson();
                System.out.println((i + 1) + ": " + randomPerson);
            }

        } catch (IOException e) {
            System.err.println("Error handling files: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

