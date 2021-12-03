/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package readcsvfile;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Faris Faiz
 */
public class ReadCSVFile {

    private static Scanner x;

    public static void main(String[] args) {
        String filepath = "src\\Book1.csv";
        String module;
        module = JOptionPane.showInputDialog(null, "Which module? (CASE SENSITIVE!)\n ", "Pick your module!", JOptionPane.INFORMATION_MESSAGE);
        readRecord(module, filepath);
    }

    public static void readRecord(String module, String filePath) {
        boolean found = false;
        String moduleCode = "";
        String creditHour = "";
        String noOcc = "";
        String activityType = "";
        try {
            x = new Scanner(new File(filePath));
            x.useDelimiter("[,\n]");

            while (x.hasNext() && !found) {
                moduleCode = x.next();
                creditHour = x.next();
                noOcc = x.next();
                activityType = x.next();

                if (moduleCode.equals(module)) {
                    found = true;
                }
            }

            if (found) {
                JOptionPane.showMessageDialog(null, "Module Code: " + moduleCode + "\n Credits: " + creditHour + "\n No. of Occ: " + noOcc + "\n Activity Type: " + activityType);
            } else {
                JOptionPane.showMessageDialog(null, "Module does not exist.");
            }
        } catch (Exception e) {

        }
    }

}
