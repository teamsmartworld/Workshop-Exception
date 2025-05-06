package se.lexicon.exceptions.workshop.fileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader_Writer {
	 /**
     * This method getMaleFirstNames should use a try-catch-finally without resources
     * Should catch FileNotFoundException and IOException
     * You should also close the Buffered reader in the finally block
     * @return List<String>of male firstnames
     */
     public List<String> getMaleFirstNames() {
         BufferedReader reader = null;
         List<String> names = new ArrayList<>();

         try {
             reader = new BufferedReader(new FileReader("male_first_names.txt"));
             String line;
             while ((line = reader.readLine()) != null) {
                 if (!line.isEmpty()) {
                     names.add(line);
                 }
             }
         } catch (IOException e) {
             // Handle the exception appropriately
             System.out.println("Error reading file: " + e.getMessage());
             throw new RuntimeException(e);
         } finally {
             if (reader != null) {
                 try {
                     reader.close();
                 } catch (IOException e) {
                     System.out.println("Error closing reader: " + e.getMessage());
                 }
             }
         }

         return names;
     }


    /**
     * This method getFemaleFirstNames should make use of a try-catch with resources
     * @return
     */
    public List<String> getFemaleFirstNames() {
        List<String> names = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("female_first_names.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    names.add(line);
                }
            }
        } catch (IOException e) {
            // Handle the exception appropriately
            System.out.println("Error reading file: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return names;
    }



    /**
     * This method fetches strings from a file and put them into a list
     * This method might throw IOException which due to the throws clause need to
     * be handled by the caller.
     * @return List <String> of last names
     * @throws IOException
     */
    public List<String> getLastNames() throws IOException {
        List<String> lastNames = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("lastnames.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    lastNames.add(line);
                }
            }
        }

        return lastNames;
    }


    /**
     * Saves a list of last names to a file.
     *
     * @param lastNames the list of last names to save
     * @throws IOException if there's an error writing to the file
     */
    public static void saveLastNames(List<String> lastNames) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("lastnames.txt"))) {
            for (String toWrite : lastNames) {
                writer.append(toWrite);
                writer.append(",");
            }
            writer.flush();
        }
    }


    /**
     * Saves a list of female names to a file.
     *
     * @param femaleNames the list of female names to save
     * @throws IOException if there's an error writing to the file
     */
    public static void saveFemaleNames(List<String> femaleNames) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("firstname_female.txt"))) {
            for (String toWrite : femaleNames) {
                writer.append(toWrite);
                writer.append(",");
            }
            writer.flush();
        }
    }


    /**
     * Saves a list of male names to a file.
     *
     * @param maleNames the list of male names to save
     * @throws IOException if there's an error writing to the file
     */
    public static void saveMaleNames(List<String> maleNames) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("firstname_males.txt"))) {
            for (String toWrite : maleNames) {
                writer.append(toWrite);
                writer.append(",");
            }
            writer.flush();
        }
    }
}
