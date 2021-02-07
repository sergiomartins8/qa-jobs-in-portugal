package qa.jobs.portugal.pages;

import com.codeborne.selenide.SelenideElement;
import qa.jobs.portugal.utils.constants.SearchFor;
import qa.jobs.portugal.utils.model.Job;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$$;

public class IndeedPage implements VacancyPage {
    private static final String CLICK_CARD_SELECTOR = ".clickcard";
    private static final String TITLE_SELECTOR = ".jobtitle";
    private static final String COMPANY_SELECTOR = ".company";
    private static final String TITLE_URL_SELECTOR = ".title a";

    @Override
    public List<Job> getJobs() {
        return $$(CLICK_CARD_SELECTOR)
                .stream()
                .filter(this::containsQuery)
                .map(element -> Job.builder().title(getTitle(element)).company(getCompany(element)).url(getUrl(element)).build())
                .collect(Collectors.toList());
    }

    private boolean containsQuery(SelenideElement element) {
        return Stream.of(SearchFor.KEYWORDS)
                .anyMatch(keyword -> element.$(TITLE_SELECTOR).getText().toLowerCase().contains(keyword));
    }

    private String getTitle(SelenideElement element) {
        return element.$(TITLE_SELECTOR).getText();
    }

    private String getCompany(SelenideElement element) {
        return element.$(COMPANY_SELECTOR).getText();
    }

    private String getUrl(SelenideElement element) {
        return element.$(TITLE_URL_SELECTOR).getAttribute("href");
    }
}
