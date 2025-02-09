package base;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationWebDriver {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationWebDriver.class);
    private static final String YANDEX_DRIVER = "src/main/resources/yandexdriver.exe";

    public static final String BROWSER_NAME = System.getProperty("browser", "chrome");
//    mvn clean test -Dbrowser=yandex

    public static void setDriver(String browserName) {
        Configuration.baseUrl = "https://stellarburgers.nomoreparties.site";
        Configuration.holdBrowserOpen = false;

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                initializeChromeDriver();
                break;
            case "yandex":
                WebDriverManager.chromedriver().driverVersion("latest").setup();
                System.setProperty("webdriver.chrome.driver", YANDEX_DRIVER);
                initializeChromeDriver();
                break;
            default:
                throw new IllegalStateException("The name of browser is incorrect. Please, use chrome or yandex");
        }

    }

    private static void initializeChromeDriver() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            WebDriverRunner.setWebDriver(new ChromeDriver(options));
            logger.info("Driver initialized successfully.");
        } catch (Exception e) {
            logger.error("Failed to initialize ChromeDriver", e);
            throw new RuntimeException("Failed to initialize ChromeDriver", e);
        }
    }
}
