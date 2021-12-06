/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package readcsvfile;

import java.awt.HeadlessException;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Faris Faiz
 * 
 * ikhmal is the source of our problems
 *
 * Tasneem
 */
public class ReadCSVFile {

    private static Scanner x;

    public static void main(String[] args) {
        String filepath = "src\\Book1.csv";
        String[] courses = {
            "Bachelor of Computer Science (Computer Systems and Networking)",
            "Bachelor of Computer Science (Artificial Intelligence)",
            "Bachelor of Computer Science (Information Systems)",
            "Bachelor of Computer Science (Software Engineering)",
            "Bachelor of Information Technology (Multimedia)",
            "Bachelor of Information Technology (Data Science)"
        }; //storage of all courses
        String module, course;
        int checker = 1;
        course = (String) JOptionPane.showInputDialog(
                null,
                "Which module?\n ",
                "Pick your module!",
                JOptionPane.PLAIN_MESSAGE,
                null,
                courses,
                courses[0]);

        int courseSimple = simplifiedCourse(course); //to convert the chosen course of study into a simpler form. This is because IT students have  WIB2001 whereas CS students have WIA2001, but theyre the same subject.

        while(checker!=0){ //so that the user doesn't have to always run the program again
            if (courseSimple == 1) { //for CS Students
            String moduleInput1 = (String) JOptionPane.showInputDialog(
                    null,
                    "Which module?\nNOTE: YOU ARE A COMPUTER SCIENCE STUDENT, YOUR COURSE IS WIA2001, THE REST ARE THE SAME",
                    "Inputting course code",
                    JOptionPane.INFORMATION_MESSAGE
            );
            module = moduleInput1.toUpperCase(); //to ensure everything the user inputs is capitalized
            if(module.equals("WIB2001")){ //to ensure the student wrote the correct course based on their respective departments.
                JOptionPane.showMessageDialog(null, "I lit'rally told you to not put that.");
            } else{ //if they typed correctly, the checker becomes 0 , and it proceeds to show the course code and details.
                checker = readRecord(module, filepath); //display the course details
            }
        } else { //for IT Students
            String moduleInput2 = (String) JOptionPane.showInputDialog(
                    null,
                    "Which module?\nNOTE: YOU ARE AN INFORMATION TECHNOLOGY STUDENT, YOUR COURSE IS WIB2001, THE REST ARE THE SAME",
                    "Inputting course code",
                    JOptionPane.INFORMATION_MESSAGE
            );
            module = moduleInput2.toUpperCase();
            if(module.equals("WIA2001")){
                JOptionPane.showMessageDialog(null, "I lit'rally told you to not put that.");
            } else{
                checker = readRecord(module, filepath); //display the course details
            }
        }
        }
        

    }

    public static int readRecord(String module, String filePath) { //to read the csv file
        boolean found = false; //to check if the desired keyword is found within the csv file
        String moduleCode = "";
        String creditHour = "";
        String noOcc = "";
        String activityType = "";
        try {
            x = new Scanner(new File(filePath)); //the file path is parsed into the Scanner constructor, meaining the Scanner class is scanning the file specified in the filepath, in this case, the CSV file
            x.useDelimiter("[,\n]"); //to distinguish between each element in the csv. \n is so that at the end of the column, it sees a newline, so it knows its the end of the column already

            while (x.hasNext() && !found) { //.hasNext() means there's something existing after the element being scanned at the moment. !found means as long as its not found yet lah
                moduleCode = x.next(); //reads the first column of CSV file and stores in moduleCode.
                creditHour = x.next(); //reads the second column of CSV file and stores in creditHour.
                noOcc = x.next(); //reads the third column of CSV file and stores in noOcc
                activityType = x.next(); //reads the contents of the fourth column of the CSV file and stores in the activityType variable

                if (moduleCode.equals(module)) { //to see if the desired module is found in the csv file in any of the rows
                    found = true;
                }
            }

            if (found) {
                JOptionPane.showMessageDialog(null, "Module Code: " + moduleCode + "\nCredits: " + creditHour + "\nNo. of Occ: " + noOcc + "\nActivity Type: " + activityType);
                return 0;
            } else {
                JOptionPane.showMessageDialog(null, "Module does not exist.");
                return 1;
            }

        } catch (HeadlessException | FileNotFoundException e) {
            System.out.println(e);
            return 1;
        }
    }

    public static int simplifiedCourse(String course) { //to simplify distinguishability between CS students and IT students. 1 for CS students, 0 for IT students.

        int simplifiedCourse = switch (course) {
            case "Bachelor of Computer Science (Computer Systems and Networking)" ->
                1;
            case "Bachelor of Computer Science (Artificial Intelligence)" ->
                1;
            case "Bachelor of Computer Science (Information Systems)" ->
                1;
            case "Bachelor of Computer Science (Software Engineering)" ->
                1;
            default ->
                0;
        };
        return simplifiedCourse;
    }
}
