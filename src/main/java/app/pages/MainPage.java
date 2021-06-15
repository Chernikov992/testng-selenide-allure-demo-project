package app.pages;
import app.widgets.Header;
import app.widgets.SearchWidget;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static org.testng.Assert.*;

public class MainPage {

    public Header header;
    public SearchWidget searchWidget;

    public MainPage(Header header, SearchWidget searchWidget) {
        this.header = header;
        this.searchWidget = searchWidget;
    }

    @Step("Открыта главная страница")
    public MainPage open() {
        Selenide.open("/");
        return this;
    }

    @Step("Находимся на главной странице")
    public MainPage isOpen() {
        return this;
    }

    @Step("Выбрали регион - {0}")
    public MainPage changeRegion(String region) {
        header.changeRegion(region);
        return this;
    }

    @Step("Убедились, что выбран регион {0}")
    public MainPage checkRegionIs(String region) {
        header.checkRegionIs(region);
        assertTrue(Selenide.title().contains(region), String.format("Неверный title - %s, не содержит - %s\n", Selenide.title(), region));
        return this;
    }

    @Step("Осуществили поиск по значению - {0}")
    public MainPage search(String text) {
        searchWidget.search(text);
        return this;
    }


}
