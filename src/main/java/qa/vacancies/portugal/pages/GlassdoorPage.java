package qa.vacancies.portugal.pages;

import com.codeborne.selenide.SelenideElement;
import qa.vacancies.portugal.utils.constants.Constants;
import qa.vacancies.portugal.utils.model.Vacancy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class GlassdoorPage implements PageObject<GlassdoorPage> {
    private static final String GRID_SELECTOR = ".jlGrid.hover .gdGrid";
    private static final String JOB_TITLE_SELECTOR = ".jobTitle";
    private static final String JOB_HEADER_SELECTOR = ".jobHeader";
    private static final String JOB_INFO_ITEM_SELECTOR = "a.jobInfoItem";

    @Override
    public GlassdoorPage openAndSearch(String locationId, String query) {
        open("https://www.glassdoor.com/Job/jobs.htm?locT=C&locId=" + locationId
                + "&jobType=&context=Jobs&sc.keyword=" + query);
        return this;
    }

    @Override
    public List<Vacancy> getVacancies() {
        return $$(GRID_SELECTOR)
                .stream()
                .filter(this::containsQuery)
                .map(element -> Vacancy.builder().title(getTitle(element)).company(getCompany(element)).url(getUrl(element)).build())
                .collect(Collectors.toList());
    }

    private boolean containsQuery(SelenideElement element) {
        return Stream
                .of(Constants.QUERIES)
                .anyMatch(query -> element.$(JOB_TITLE_SELECTOR).getText().toLowerCase().contains(query));
    }

    private String getTitle(SelenideElement element) {
        return element.$(JOB_TITLE_SELECTOR).getText();
    }

    private String getCompany(SelenideElement element) {
        return element.$(JOB_HEADER_SELECTOR).getText();
    }

    private String getUrl(SelenideElement element) {
        return element.$(JOB_INFO_ITEM_SELECTOR).getAttribute("href");
    }
}
