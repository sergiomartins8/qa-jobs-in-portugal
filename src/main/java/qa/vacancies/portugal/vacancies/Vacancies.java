package qa.vacancies.portugal.vacancies;

import qa.vacancies.portugal.pages.VacancyPage;
import qa.vacancies.portugal.utils.constants.SearchFor;
import qa.vacancies.portugal.utils.model.Vacancy;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;

public interface Vacancies {
    Map<String, Set<Vacancy>> getVacancies();

    /**
     * Get the list of vacancies in a page, for a given location.
     *
     * @param urlTemplate url template for the given page
     * @param locationId  location id for the url template query parameter
     * @param page        object that maps the current page to be visited
     * @return set of unique vacancies for a given location
     */
    default Set<Vacancy> getVacanciesForLocation(String urlTemplate, String locationId, VacancyPage page) {
        open(String.format(urlTemplate, locationId, SearchFor.TEST_AUTOMATION_QUERY));
        List<Vacancy> vacancyListTestAutomation = page.getVacancies();

        open(String.format(urlTemplate, locationId, SearchFor.QUALITY_ASSURANCE_QUERY));
        List<Vacancy> vacancyListQualityAssurance = page.getVacancies();

        return Stream.of(vacancyListTestAutomation, vacancyListQualityAssurance)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
