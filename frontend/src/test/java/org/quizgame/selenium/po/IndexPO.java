package org.quizgame.selenium.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IndexPO {
    private final WebDriver driver;

    public IndexPO(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(By.id("linkToLoginId"));
        loginButton.click();
    }
}
