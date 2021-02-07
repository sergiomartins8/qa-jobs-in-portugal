package qa.jobs.portugal.pages;

import com.codeborne.selenide.SelenideElement;
import qa.jobs.portugal.utils.constants.SearchFor;
import qa.jobs.portugal.utils.model.Job;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$$;

public class ItJobsPage implements VacancyPage {
    private static final String BLOCK_BORDERLESS_SELECTOR = ".block.borderless";
    private static final String TITLE_SELECTOR = ".title";
    private static final String LIST_NAME_SELECTOR = ".list-name";

    @Override
    public List<Job> getJobs() {
        return $$(BLOCK_BORDERLESS_SELECTOR)
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
        return element.$(TITLE_SELECTOR).getText().trim();
    }

    private String getCompany(SelenideElement element) {
        return element.$(LIST_NAME_SELECTOR).getText();
    }

    private String getUrl(SelenideElement element) {
        return element.$(TITLE_SELECTOR).getAttribute("href");
    }
}
