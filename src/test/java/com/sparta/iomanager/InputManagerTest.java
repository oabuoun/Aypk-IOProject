package com.sparta.iomanager;

import com.sparta.iomanager.controller.InputManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InputManagerTest {
    InputManager inputManager = new InputManager();
    BufferedReader file;

    {
        try {
            file = new BufferedReader(new FileReader("EmployeeRecords.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @ParameterizedTest
    @DisplayName("Check if the file exists")
    @ValueSource(strings = {"EmployeeRecords.csv"})
    public void checkFileExists(String strings) {
        File file = new File(strings);
        assertTrue(file.exists());
    }

    @ParameterizedTest
    @DisplayName("Check if the file is the correct extension")
    @ValueSource(strings = {"EmployeeRecords.csv"})
    public void checkFileIsCorrectExtension(String strings) {
        String name = strings.substring(strings.length() - 3);
        assertEquals("csv", name);
    }

    @ParameterizedTest
    @DisplayName("Check if first line is read")
    @ValueSource(strings = {"EmployeeRecords.csv"})
    public void checkCSVTitles(String filename) throws IOException {
        String expected = "Emp ID,Name Prefix,First Name,Middle Initial,Last Name," +
                "Gender,E Mail,Date of Birth,Date of Joining,Salary";
        String firstLine = file.readLine();
        assertEquals(expected, firstLine);
    }

    @Test
    @DisplayName("Checks if 10 values are read")
    public void CheckNumberOfFields() throws IOException {
        String[] firstLine = file.readLine().split(",");
        assertEquals(10, firstLine.length);
    }

    @Test
    @DisplayName("Checks the first id")
    public void checkIdMatch() throws IOException {
        file.readLine();
        String[] firstLine = file.readLine().split(",");
        String x = firstLine[0];
        int n = Integer.parseInt(x);
        assertEquals(Integer.parseInt(x), n);
    }

    @Test
    @DisplayName("Checks if the id stored is the correct format")
    public void checkIdFormat() throws IOException {
        file.readLine();
        String[] firstLine = file.readLine().split(",");
        assertEquals(6, firstLine[0].length());
    }

//    @Test
//    @DisplayName("Checks the title of courtesy") // String array of titles and check
//    public void checkTocFormat() throws IOException {
//        List<String> expectedList = new ArrayList<>(List.of("Mr.", "Miss.", "Mrs.", "Dr.", "Hon.", "Drs.", "Prof."));
//        List<String> actualList = new ArrayList<>();
//        file.readLine();
//        String[] firstLine;
//        String line;
//        for (int i = 1; i < 100; i += 10) {
//            line = file.readLine();
//            firstLine = line.split(",");
//            actualList.add(firstLine[i]);
//            System.out.println(firstLine[i]);
//        }
//
////        assertTrue(myList.containsAll(actualList));
//        assertTrue(expectedList.containsAll(actualList));
//    }
//
//    @Test
//    @DisplayName("") // checks if only 1 char middle name
//    public void checkMiddleNameChar(){
//
//    }
//
//    @Test
//    @DisplayName("") // check email is valid
//    public void checkNameFormat(){
//
//    }
//
//    @Test
//    @DisplayName("") // check date from the file
//    public void checkDateFormat(){
//

}