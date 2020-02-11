package stepdefinition;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.*;
import java.util.*;

public class DictionaryImplementation {

    BufferedReader reader;
    boolean isFileExist;

    @Given("^verify whether the file (.*?) is present$")
    public void verify_whether_the_file_Alpha_is_present(String filePath) throws IOException {
        //call the doesFileExist method to check the presence of the file
        isFileExist = doesFileExist(filePath);
        if(isFileExist)
        System.out.println("The file exists in given path");
    }

    @Then("^print the words and meanings$")
    public void print_the_words_and_meanings() throws IOException {
        //hashtable would be the appropriate data structure to implement dictionary
        if (isFileExist) {
            LinkedHashMap<String, List<String>> hmapDictionary = new LinkedHashMap<String, List<String>>();
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                String[] words = currentLine.split("-|,");
                String key = words[0];
                List<String> values = new ArrayList<String>();
                for (int i = 1; i < words.length; i++) {
                    values.add(words[i]);
                }

                hmapDictionary.put(key, values);
            }

            System.out.println("Reading out each word and its possible meaning from the file:");
            for (Map.Entry<String, List<String>> entry : hmapDictionary.entrySet()) {
                System.out.println(entry.getKey());
                List<String> values = entry.getValue();
                for (String evalue : values) {
                    System.out.println(evalue);
                }
            }
        }
    }

    public boolean doesFileExist(String path) {
        boolean isExist = false;
        try {
            File file = new File(path).getAbsoluteFile();
            FileReader fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);

            if (file.exists()) {
                isExist = true;
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
            System.out.println(e);
        }
        return isExist;
    }
}
