package efub.ebmt.eeojum.global.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.nio.file.Paths;
import java.time.Duration;

@Component
public class WebDriverUtil {
    private static String WEB_DRIVER_PATH;

    public static WebDriver getChromeDriver() {
        System.out.println("현재 디렉토리: " + Paths.get("").toAbsolutePath().toString());

        if (ObjectUtils.isEmpty(System.getProperty("webdriver.chrome.driver"))) {
            System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
        }

        // webDriver 옵션 설정
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true); //브라우저가 눈에 보이지 않고 내부적으로 돈다.
        //chromeOptions.setBinary("C:/Program Files/Google/Chrome Beta/Application/chrome.exe");
        chromeOptions.addArguments("--lang=ko");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--remote-allow-origins=*"); //connection error
        chromeOptions.setCapability("ignoreProtectedModeSettings", true);
        chromeOptions.addArguments("--disable-popup-blocking"); //팝업 X
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false"); //이미지 다운 X
        chromeOptions.setBrowserVersion("119.0.6045.159");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        return driver;
    }

    @Value("${selenium.chromedriver.path}")
    public void initDriver(String path) {
        WEB_DRIVER_PATH = path;
    }

    public static void quit(WebDriver driver) {
        if (!ObjectUtils.isEmpty(driver)) {
            driver.quit();
        }
    }

    public static void close(WebDriver driver) {
        if (!ObjectUtils.isEmpty(driver)) {
            driver.close();
        }
    }

}
