package org.quizgame.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.quizgame.selenium.po.IndexPO;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SeleniumLocalIT {

    @LocalServerPort
    private int port;

    @Test
    public void testPort() throws InterruptedException {
        WebDriver driver = SeleniumDriverHandler.getChromeDriver();
        IndexPO pageObj = new IndexPO(driver);

        assertThat(port, is(greaterThan(0)));

        try {
            driver.get("http://localhost:" + port);
            Thread.sleep(5_000);
        } finally {
            driver.close();
        }
    }
}
