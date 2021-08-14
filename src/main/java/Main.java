import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello and welcome to the automation program by Maxim Mirochnik !");
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease enter you AC user name:"); // AC - Ashkelon College
        String userName = scanner.nextLine();
        System.out.println("Please enter you AC password:");
        String password = scanner.nextLine();

        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.aac.ac.il/");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement infoButton = driver.findElement(By.className("info-btn"));
        if (infoButton !=null) {
            WebElement personalInfo = infoButton.findElement(By.tagName("a"));
            driver.get(personalInfo.getAttribute("href"));
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement userNameInput = driver.findElement(By.id("Ecom_User_ID"));
        if (userNameInput == null) {
            System.out.println("Username not found.");
        } else {
            userNameInput.sendKeys(userName);
        }

        WebElement passwordInput = driver.findElement(By.id("Ecom_Password"));
        if (passwordInput == null) {
            System.out.println("password incorrect.");
        } else {
            passwordInput.sendKeys(password);
        }

        driver.findElement(By.id("wp-submit")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> optionButtons = driver.findElements(By.className("row"));
        WebElement moodleRow = optionButtons.get(5);
        WebElement moodleButton = moodleRow.findElement(By.tagName("a"));
        driver.get(moodleButton.getAttribute("href"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> courses = driver.findElements(By.className("course-summaryitem"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String> titles = courses.stream().map(element -> element.findElement(By.tagName("h6")).getText()).collect(Collectors.toList());
        System.out.println("Your courses are:\n");
        int i = 0;
        for (String title : titles) {
            System.out.println(title + " ." + (i + 1));
            i++;
        }

        System.out.print("\nChoose a course number you want to enter in:");
        int courseNumInput = scanner.nextInt();
        courses.get(courseNumInput - 1).findElement(By.tagName("a")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.className("dropdown-toggle")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector(".dropdown-menu .dropdown-item:last-of-type")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("menu-top-header")).findElements(By.tagName("li")).get(1).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();

    }
}


