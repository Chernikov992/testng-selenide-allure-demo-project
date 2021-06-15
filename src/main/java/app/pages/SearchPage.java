package app.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static org.testng.Assert.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SearchPage {

    public static final int EXPECTED_RESULTS_COUNT = 10;

    public SelenideElement hSearch = $x("//h1[text()='Поиск']");
    public SelenideElement resultsContainer = $("ol[id*='searchResultList']");
    public ElementsCollection resultsItems = resultsContainer.$$("*.results__item");
    public ElementsCollection resultsItemsLinks = resultsContainer.$$("*.results__item a[class*='results__title']");
    public SelenideElement buttonApplyFilters = $("input[onclick='SendFiltersSearch()']");
    public SelenideElement paginatorContainer = $x("//*[contains(@id,'SearchResultPager')]");
    public String filterLocator = "//label[text()='%s']/preceding-sibling::*//input";
    public String filterLabelLocator = "//label[text()='%s']/preceding-sibling::*//input/following-sibling::*";
    public String selectedRegionLocator = "//*[text()='%s'][contains(@class, 'selection__choice')]";
    public String resultPageByNumberLocator = ".//a[text()='%s']";

    @Step("Открыта страница поиска")
    public SearchPage isOpen() {
        return this;
    }

    @Step("Проверили, что количество результатов поиска на одной странице соответствует ожидаемому")
    public SearchPage checkResultsCount() {
        // Скроллим, чтобы в случае падения на attachment-скрине было видно последний номер результата на странице поиска
        paginatorContainer.$x(String.format(resultPageByNumberLocator, "1")).scrollIntoView(false);
        assertEquals(resultsItems.size(), EXPECTED_RESULTS_COUNT, "Количество результатов поиска на одной странице не соответствует ожидаемому\n");
        return this;
    }

    @Step("Проверили, что активны фильтры: {0}")
    public SearchPage checkFiltersAreApplied(String ... filters) {
        for (String filter : filters) {
            $x(String.format(filterLocator, filter)).shouldBe(checked);
        }
        return this;
    }

    @Step("Убедились, что в фильтре Регион выбрано значение {0}")
    public SearchPage checkRegionIs(String region) {
        $x(String.format(selectedRegionLocator, region)).shouldBe(visible);
        return this;
    }

    @Step("Переключили фильтры {0}")
    public SearchPage clickFilters(String ... filters) {
        hSearch.scrollIntoView(true);
        for (String filter : filters) {
            $x(String.format(filterLabelLocator, filter)).click();
        }
        return this;
    }

    @Step("Осуществили поиск по выставленным фильтрам")
    public SearchPage applyFilters() {
        buttonApplyFilters.click();
        return this;
    }

    @Step("Перешли на страницу поиска под номером {0}")
    public SearchPage goToResultsPageByNumber(int number) {
        paginatorContainer.$x(String.format(resultPageByNumberLocator, Integer.toString(number))).click();
        return this;
    }

    @Step("Сохранили список тем с открытой страницы поиска в файл")
    public void saveResultsToFile() {
        String filename = "src\\test\\java\\fns\\tests\\reports\\SearchTestResults_" + Long.toString(System.currentTimeMillis()) + ".txt";
        List<String> titles = new ArrayList<String>();
        for (SelenideElement link: resultsItemsLinks) {
            titles.add(link.text());
        }
        try {
            Path path = Paths.get(filename);
            Files.write(path, titles, StandardOpenOption.CREATE);
            System.out.println("Результаты сохранены в файл " + filename);
            InputStream is = Files.newInputStream(path);
            Allure.addAttachment("Список заголовков второй страницы", is);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Запись в файл не удалась");
        }
    }



}
