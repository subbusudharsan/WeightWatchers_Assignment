package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SchedulePage {

    @FindBy(className = "hours-list-item")
    public static List<WebElement> operationHours ;

    @FindBy(className = "schedule-detailed-day")
    public static List<WebElement> scheduleDetailedDay ;
}
