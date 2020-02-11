package stepdefinition;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.Arrays;
import java.util.Random;

public class SmallestNumberImplementation {

    int[] randomArray = new int[500];
    Random random = new Random();

    @Given("^generate (\\d+) random numbers$")
    public void generate_random_numbers(int arg1) throws Throwable {

        for (int i = 0; i < 500; i++) {
            randomArray[i] = random.nextInt(999) + 1;
        }

        for (int in : randomArray)
            System.out.print(in + ",");

    }

    @Then("^return (\\d+) smallest number$")
    public void return_smallest_number(int n) throws Throwable {

        Arrays.sort(randomArray);

        System.out.println("The "+n+"th smallest number from the 500 randomly generated numbers: " + randomArray[n-1]);
    }


}
