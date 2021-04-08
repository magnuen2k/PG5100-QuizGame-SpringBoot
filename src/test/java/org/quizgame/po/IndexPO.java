package org.quizgame.po;

import no.kristiania.quizgame.selenium.PageObject;
import org.openqa.selenium.WebDriver;
import org.quizgame.po.ui.MatchPO;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IndexPO extends PageObject {

    public IndexPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public void toStartingPage(){
        getDriver().get("http://" + host + ":" + port);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Quiz Game");
    }

    public MatchPO startNewMatch(){

        clickAndWait("newMatchBtnId");
        MatchPO po = new MatchPO(this);
        assertTrue(po.isOnPage());

        return po;
    }
}
