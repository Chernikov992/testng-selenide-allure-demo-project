package app.widgets;

import com.codeborne.selenide.SelenideElement;
import static org.testng.Assert.*;

import static com.codeborne.selenide.Selenide.*;

public class Header {

    public SelenideElement container = $("div.top");
    public SelenideElement selectRegion = container.$("select[id*='Region']");
    public SelenideElement expandIcon = container.$("span[class*='select']");
    public SelenideElement searchInput = $("span[class*='dropdown'] input[class*='search']");


    public void changeRegion(String region) {
        expandIcon.click();
        searchInput.setValue(region).pressEnter();
    }

    public void checkRegionIs(String region) {
        assertEquals(selectRegion.getSelectedText(), region);
    }


}
