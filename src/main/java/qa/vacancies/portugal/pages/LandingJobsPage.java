package qa.vacancies.portugal.pages;

import com.codeborne.selenide.SelenideElement;
import qa.vacancies.portugal.utils.constants.Constants;
import qa.vacancies.portugal.utils.model.Vacancy;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class LandingJobsPage implements PageObject<LandingJobsPage> {
    private static final String SEARCH_SPINNER_SELECTOR = "#search_spinner.stopped";
    private static final String CARD_SELECTOR = ".lj-jobcard ";
    private static final String TITLE_SELECTOR = ".lj-jobcard-name";
    private static final String LIST_NAME_SELECTOR = ".lj-jobcard-company";

    @Override
    public LandingJobsPage openAndSearch(String urlTemplate, Object... params) {
        Object[] nonNullParams = Arrays.stream(params).filter(Objects::nonNull).toArray();
        open(String.format(urlTemplate, nonNullParams));
        return this;
    }

    @Override
    public List<Vacancy> getVacancies() {
        $(SEARCH_SPINNER_SELECTOR).waitUntil(exist, 10000L);

        return $$(CARD_SELECTOR)
                .stream()
                .filter(this::containsQuery)
                .map(element -> Vacancy.builder().title(getTitle(element)).company(getCompany(element)).url(getUrl(element)).build())
                .collect(Collectors.toList());
    }

    private boolean containsQuery(SelenideElement element) {
        return Stream
                .of(Constants.KEYWORDS)
                .anyMatch(keyword -> element.$(TITLE_SELECTOR).innerText().toLowerCase().contains(keyword));
    }

    private String getTitle(SelenideElement element) {
        return element.$(TITLE_SELECTOR).innerText().trim();
    }

    private String getCompany(SelenideElement element) {
        return element.$(LIST_NAME_SELECTOR).innerText();
    }

    private String getUrl(SelenideElement element) {
        return element.$(TITLE_SELECTOR).getAttribute("href");
    }
}
