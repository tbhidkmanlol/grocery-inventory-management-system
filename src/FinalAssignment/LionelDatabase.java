package FinalAssignment;

import java.io.*;
import java.util.*;

public abstract class LionelDatabase {
    protected String filePath;
    protected Scanner scanner = new Scanner(System.in);

    // Constructor to set the file path for each subclass
    public LionelDatabase(String filePath) {
        this.filePath = filePath;
    }

    // Method to read data from the file
public List<String> readFile() {
    List<String> lines = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line); // Store each line
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return lines; // Return the list of lines
}

    // Method to create or append data to the file
    public void createData(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Abstract method for generating IDs (to be implemented by subclasses)
    protected abstract String generateNextId();

    // Delete data based on ID
    public void deleteData(String id) {
        File inputFile = new File(filePath);
        File tempFile = new File("temp_" + inputFile.getName());

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            boolean deleted = false;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (!data[0].trim().equals(id)) {
                    writer.write(currentLine);
                    writer.newLine();
                } else {
                    deleted = true;
                }
            }

            if (deleted) {
                System.out.println("Data with ID " + id + " was deleted successfully.");
            } else {
                System.out.println("No data found with ID " + id + ".");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            System.out.println("Error updating the file.");
        }
    }

    // Method to update data based on ID
    public void updateData(String id, String updatedData) {
        File inputFile = new File(filePath);
        File tempFile = new File("temp_" + inputFile.getName());

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean updated = false;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(id)) {
                    writer.write(updatedData);
                    writer.newLine();
                    updated = true;
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (updated) {
                System.out.println("Data updated successfully.");
            } else {
                System.out.println("No data found with ID " + id + ".");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            System.out.println("Error updating the file.");
        }
    }
}
