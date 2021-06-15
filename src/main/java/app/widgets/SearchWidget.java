package app.widgets;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class SearchWidget {

    public SelenideElement inputSearch = $("input[id*='main_search']");
    public SelenideElement buttonSearch = $("button[id*='main_search']");

    public void search(String text) {
        inputSearch.setValue(text);
        buttonSearch.click();
    }

}
