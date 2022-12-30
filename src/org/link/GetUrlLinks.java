package org.link;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.management.openmbean.OpenDataException;
import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetUrlLinks {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				"H:\\Selenium\\Greens\\Workspace\\Project\\UrlLink\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");

		// To get size of the links present in web page

		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		int size = allLinks.size();
		System.out.println("Num of links: " + size);

		// To get all the link
		for (int i = 0; i < size; i++) {

			WebElement webElement = allLinks.get(i);
			String urlLinks = webElement.getAttribute("href");
			//System.out.println(urlLinks);

			
			try {
				URL link = new URL(urlLinks); // Throw malformed
				int count = 0;
				URLConnection openConnection = link.openConnection(); // throw IO Exception
				HttpsURLConnection htp = (HttpsURLConnection) openConnection;

				// Implicit waits

				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

				// To Establish the connection

				htp.connect();

				// To get response code from Url connection

				int responseCode = htp.getResponseCode();

				if (responseCode >= 400) {

					System.out.println(urlLinks + " ====" + " is a Broken link");
						
					count++;

				}

				else {
					System.out.println(urlLinks + " ====" + " is a valid link");

				}
			}

			catch (IOException e) {

			}
		}

	}

}
