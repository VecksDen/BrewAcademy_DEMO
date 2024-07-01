import java.io.*;
import java.util.*;
import javax.swing.*;

/*
 * Author:
 * Go, Mardelito Tutor
 * Joshua Famor
 * BSCS - A121
 * BrewAcademy
 */

public class Database {
    private File file;
    private FileWriter fWrite;
    private FileReader fRead;
    private Scanner scan;

    public Database(String filename) {
        file = new File(filename);
    }

    public void errorMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void storeToFile(String record) {
        try {
            fWrite = new FileWriter(file, true); //Append mode
            fWrite.write(record);
            fWrite.write("\n"); //Add new line after each record
            fWrite.flush();
            fWrite.close();
        } catch (Exception e) {
            errorMessage("Error 101: storeToFile\n" + e.getMessage());
        }
    }

    public ArrayList<String> loadFromFile() {
        ArrayList<String> records = new ArrayList<>();
        try {
            fRead = new FileReader(file);
            scan = new Scanner(fRead);
            while (scan.hasNext()) {
                records.add(scan.nextLine());
            }
            scan.close();
        } catch (Exception e) {
            errorMessage("Error 102: loadFromFile\n" + e.getMessage());
        }
        return records;
    }

    public void saveAllRecords(List<String> records) {
        try {
            fWrite = new FileWriter(file, false); //Overwrite mode
            for (String record : records) {
                fWrite.write(record + "\n");
            }
            fWrite.flush();
            fWrite.close();
        } catch (Exception e) {
            errorMessage("Error 104: saveAllRecords\n" + e.getMessage());
        }
    }
}
