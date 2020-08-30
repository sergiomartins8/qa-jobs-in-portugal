package qa.vacancies.portugal.pages;

import com.codeborne.selenide.SelenideElement;
import qa.vacancies.portugal.utils.constants.Constants;
import qa.vacancies.portugal.utils.model.Vacancy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class ItJobsPage implements PageObject<ItJobsPage> {
    private static final String BLOCK_BORDERLESS_SELECTOR = ".block.borderless";
    private static final String TITLE_SELECTOR = ".title";
    private static final String LIST_NAME_SELECTOR = ".list-name";

    @Override
    public ItJobsPage openAndSearch(String locationId, String query) {
        open("https://www.itjobs.pt/emprego?location=" + locationId + "&q=" + query);
        return this;
    }

    @Override
    public List<Vacancy> getVacancies() {
        return $$(BLOCK_BORDERLESS_SELECTOR)
                .stream()
                .filter(this::containsQuery)
                .map(element -> Vacancy.builder().title(getTitle(element)).company(getCompany(element)).url(getUrl(element)).build())
                .collect(Collectors.toList());
    }

    private boolean containsQuery(SelenideElement element) {
        return Stream
                .of(Constants.QUERIES)
                .anyMatch(query -> element.$(TITLE_SELECTOR).getText().toLowerCase().contains(query));
    }

    private String getTitle(SelenideElement element) {
        return element.$(TITLE_SELECTOR).getText();
    }

    private String getCompany(SelenideElement element) {
        return element.$(LIST_NAME_SELECTOR).getText();
    }

    private String getUrl(SelenideElement element) {
        return element.$(TITLE_SELECTOR).getAttribute("href");
    }
}
