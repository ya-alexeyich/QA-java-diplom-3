package base;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationWebDriver {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationWebDriver.class);

    public static final String BROWSER_NAME = System.getProperty("browser", "chrome");

    public static void setDriver(String browserName) {

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().driverVersion("latest").setup();
                initializeChromeDriver();
                break;
            case "yandex":
                WebDriverManager.chromedriver().driverVersion("latest").setup();
                System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");
                initializeChromeDriver();
                break;
            default:
                throw new IllegalStateException("The name of browser is incorrect. Please, use chrome or yandex");
        }

    }

    private static void initializeChromeDriver() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*", "start-maximized");
            WebDriverRunner.setWebDriver(new ChromeDriver(options));
            logger.info("Driver initialized successfully.");
        } catch (Exception e) {
            logger.error("Failed to initialize ChromeDriver", e);
            throw new RuntimeException("Failed to initialize ChromeDriver", e);
        }
    }
}
