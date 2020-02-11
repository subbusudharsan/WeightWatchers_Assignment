package stepdefinition;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gherkin.lexer.Fi;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageobjects.FindAStudio;
import pageobjects.HomePage;
import pageobjects.SchedulePage;
import utility.BaseClass;
import utility.GlobalConstants;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

public class WeightWatchersMeetingFinderImplementation extends BaseClass {

    Logger logger = Logger.getLogger("WW_Test_Execution_Logs");
    public Properties properties;
    String firstLocationName = null;
    String firstLocationAddress = null;
    String firstLocationCityZip = null;
    String oldWindow;
    Set<String> newWindow;

    @Given("^User navigates to the WeightWatchers website$")
    public void user_navigates_to_the_WeightWatchers_website() throws Throwable {
        initializeTest();
    }

    @Then("^I close the browser$")
    public void i_close_the_browser() throws Throwable {
        tearDown();
    }

    @Given("^verify we land on page using page title (.*?)$")
    public void verify_we_land_on_page_using_page_title(String pageTitle) throws Throwable {
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.titleIs(pageTitle));
        Assert.assertEquals(driver.getTitle(), pageTitle);
        System.out.println("The given page title and actual screen title matches " + pageTitle);
    }

    @Given("^click on element Find A Studio on Home Page$")
    public void click_on_element_Find_A_Studio_on_Home_Page() throws Throwable {
        PageFactory.initElements(driver, HomePage.class);
        HomePage.findAStudio.click();

    }

    @Given("^click on element Search on Find A Studio Page$")
    public void click_on_element_Search_on_Find_A_Studio_Page() throws Throwable {
        FindAStudio.searchButton.click();
    }

    @Given("^enter zipcode (.*?) in the search field$")
    public void enter_zipcode_in_the_search_field(String zipcode) throws Throwable {
        PageFactory.initElements(driver, FindAStudio.class);
        modalHandler();
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("meetingSearch")));

        FindAStudio.searchInputBoc.click();
        FindAStudio.searchInputBoc.sendKeys(zipcode);
    }

    @Then("^print the result of the first result and distance$")
    public void print_the_result_of_the_first_result_and_distance() throws Throwable {
        Thread.sleep(3000);
        firstLocationName = FindAStudio.searchResult.get(0).findElement(By.className("location__name")).getText();
        String firstLocationDistance = FindAStudio.searchResult.get(0).findElement(By.className("location__distance")).getText();
        firstLocationAddress = FindAStudio.searchResult.get(0).findElement(By.className("location__address")).getText();
        firstLocationCityZip = FindAStudio.searchResult.get(0).findElement(By.className("location__address")).getText();
        System.out.println("Search Results");
        System.out.println("First Location Name: " + firstLocationName);
        System.out.println("First Location Distance: " + firstLocationDistance);
    }

    @Then("^click on the first result$")
    public void click_on_the_first_result() throws Throwable {
        FindAStudio.searchResult.get(0).findElement(By.className("location__name")).click();
    }

    @Then("^verify displayed location with name of the first searched result that was clicked$")
    public void verify_displayed_location_with_name_of_the_first_searched_result_that_was_clicked() throws Throwable {

        PageFactory.initElements(driver, SchedulePage.class);
        Assert.assertTrue(driver.getTitle().contains(firstLocationAddress));
        Assert.assertTrue(driver.getTitle().contains(firstLocationCityZip));
        System.out.println("Expected and Actual page are same " + driver.getTitle());
    }

    @Then("^print hours of operations from the location page$")
    public void print_hours_of_operations_from_the_location_page() throws Throwable {
        for (WebElement webElement : SchedulePage.operationHours) {
            String day = webElement.findElement(By.className("hours-list-item-day")).getText();
            String timings = webElement.findElement(By.className("hours-list-item-hours")).getText();
            System.out.println("Day: " + day);
            System.out.println("Timings:  " + timings);
        }
    }

    @Then("^print the number of meeting the each person has a particular day of the week$")
    public void print_the_number_of_meeting_the_each_person_has_a_particular_day_of_the_week() throws Throwable {
        for (WebElement webElement : SchedulePage.scheduleDetailedDay) {
            HashMap<String, Integer> personSchedule = new HashMap<String, Integer>();
            System.out.println("Number of Schedules for each person on : " + webElement.findElement(By.className("schedule-detailed-day-label")).getText());
            for (WebElement persons : webElement.findElements(By.className("schedule-detailed-day-meetings-item-leader"))) {
                if (!personSchedule.containsKey(persons.getText())) {
                    personSchedule.put(persons.getText(), 1);
                } else {
                    int temp = personSchedule.get(persons.getText());
                    temp++;
                    personSchedule.put(persons.getText(), temp);
                }

            }
            System.out.println(personSchedule);
        }

    }


    public void initializeTest() throws IOException {
        logger.info("Before method is called & Browser details & Application URL is taken & loaded from the property file");
        properties = loadPropertyFile();
        String browser = properties.getProperty("browser");
        GlobalConstants.applicationURL = properties.getProperty("url");
        BaseClass.setDriver(browser);
        logger.info("Driver is instantiated");
        driver = BaseClass.getDriver();
        driver.get(GlobalConstants.applicationURL);
    }

    public void tearDown() {
        //code to quit the application after execution completion
        driver.quit();
        logger.info("Driver Quits");
    }

    public Properties loadPropertyFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/properties/application-default.properties");
        properties = new Properties();
        properties.load(fileInputStream);
        return properties;
    }

    public void modalHandler() throws InterruptedException {
        Thread.sleep(5000);
        if (driver.findElements(By.xpath(("(//button[contains(text(),'No Thanks')])[1]"))).size() != 0)
            driver.findElement(By.xpath(("(//button[contains(text(),'No Thanks')])[1]"))).click();
    }
}
