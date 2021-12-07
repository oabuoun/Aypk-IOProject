package com.sparta.iomanager.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileFinder {
    //Search the folder/i,e list all the files with their names...
    public String findFile(){
        Scanner scanner = new Scanner(System.in);
        File dir = new File(System.getProperty("user.dir"));
        Boolean foundFile = false;
        System.out.println("Search for the file you want to be read");
        while (!foundFile){
            // Show directories
            System.out.println("Current Directory: " + dir.toString());
            System.out.println(" - type folder name to enter it\n - type 'back' to go back\n - type filename with extension to read file\nPossible Directories: ");
            File[] matchingFiles = dir.listFiles();
            String[] dirs;
            String[] tnames = new String[0];
            ArrayList<String> names = new ArrayList<>();
            for (int i = 0; i < matchingFiles.length; i++){
                tnames = matchingFiles[i].toString().split("\\\\");
                names.add(tnames[tnames.length-1]);
                System.out.println(tnames[tnames.length-1].toString());
            }
            // Get input
            // Check input
            String userInput = scanner.nextLine();
            if (userInput == null || userInput.isEmpty()){
                System.out.println("Please enter a response!");
                continue;
            }

            boolean validInput = false;
            if (userInput.equals("back") && !validInput){
                dir = new File(goBack(dir));
                validInput = true;
                continue;
            }

            else if (!validInput) {
                for (int i = 0; i < matchingFiles.length; i++) {
                    if (names.get(i).equals(userInput)) {
                        if (userInput.contains(".csv")){
                            // is file
                            dir = new File(dir.toString() + "\\" + userInput);
                            foundFile = true;
                            validInput = true;
                        }
                        else if (userInput.contains(".")){
                            validInput = false;
                        }
                        else{
                            // is folder
                            dir = new File(dir.toString() + "\\" + userInput);
                            validInput = true;
                            continue;
                        }
                    }
                }
            }
            if (!validInput){
                System.err.println("Invalid option, pick again!");
                continue;
            }
        }
        return dir.toString();
    }

    private String goBack(File file){
        String[] dirs = file.toString().split("\\\\");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < dirs.length-1; i++){
            stringBuilder.append(dirs[i] + "\\");
        }
        return stringBuilder.toString();
    }
}