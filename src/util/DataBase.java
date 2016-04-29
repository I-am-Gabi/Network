package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @version 29/04/16.
 */
public class DataBase {
    private List<String> ideas_database;


    public DataBase() {
        ideas_database = new ArrayList<>();
    }

    public String getRegister(int id) {
        readDataBase();
        return ideas_database.get(id);
    }

    public List<String> getRegisters() {
        readDataBase();
        return ideas_database;
    }

    public void addRegister(String data) {
        FileWriter fout = null;
        try {
            fout = new FileWriter("resource/ideas.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter out = new PrintWriter(fout);
        out.println("\n" + data);
        out.flush();
        out.close();
        try {
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readDataBase() {
        ideas_database.clear();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("resource/ideas.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String line = br.readLine();

            while (line != null && !line.equals("")) {
                ideas_database.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
