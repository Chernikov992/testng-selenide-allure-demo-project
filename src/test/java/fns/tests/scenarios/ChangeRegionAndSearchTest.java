package fns.tests.scenarios;

import fns.tests.configuration.BaseTest;
import org.testng.annotations.Test;

public class ChangeRegionAndSearchTest extends BaseTest {

    @Test(priority = 1, description = "Тест смены региона")
    public void changeRegionTest() {
        app.mainPage.open()
                    .changeRegion("18 Удмуртская Республика")
                    .checkRegionIs("18 Удмуртская Республика");
    }

    @Test(priority = 2, description = "Тест проверки количества записей на одной странице результатов")
    public void resultsCountTest() {
        app.mainPage.isOpen()
                .search("Налоговый вычет");
        app.searchPage.isOpen()
                .checkResultsCount();
    }

    @Test(priority = 3, description = "Тест с сохранением отфильтрованных результатов поиска")
    public void filtersTest() {
        app.searchPage.isOpen()
                .checkFiltersAreApplied("Выбрать все", "Мой регион")
                .checkRegionIs("18 Удмуртская Республика")
                .clickFilters("Выбрать все", "Документы", "Новости")
                .applyFilters()
                .goToResultsPageByNumber(2)
                .saveResultsToFile();
    }


}
