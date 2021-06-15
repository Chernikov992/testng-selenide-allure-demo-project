package app;

import app.pages.MainPage;
import app.pages.SearchPage;
import app.widgets.Header;
import app.widgets.SearchWidget;

public class PageBuilder {

    public static MainPage buildMainPage() {
        Header header = new Header();
        SearchWidget searchWidget = new SearchWidget();
        return new MainPage(header, searchWidget);
    }

    public static SearchPage buildSearchPage() {
        return new SearchPage();
    }


}
