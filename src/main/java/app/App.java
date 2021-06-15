package app;

import app.pages.SearchPage;
import app.pages.MainPage;

public class App {

    public MainPage mainPage;
    public SearchPage searchPage;

    public App() {
        mainPage = PageBuilder.buildMainPage();
        searchPage = PageBuilder.buildSearchPage();
    }

}
