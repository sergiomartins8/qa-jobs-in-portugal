package qa.vacancies.portugal.vacancies;

import qa.vacancies.portugal.pages.ItJobsPage;
import qa.vacancies.portugal.utils.constants.SearchFor;
import qa.vacancies.portugal.utils.model.Vacancy;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;

public class ItJobsVacancies implements Vacancies {
    private static final String URL_TEMPLATE_ON_SITE = "https://www.itjobs.pt/emprego?location=%s&q=%s";
    private static final Map<String, String> VACANCIES_ON_SITE_ID = new TreeMap<>(Map
            .of("Aveiro", "1",
                    "Braga", "4",
                    "Coimbra", "8",
                    "Lisboa", "14",
                    "Porto", "18"));

    private static final String URL_TEMPLATE_REMOTE = "https://www.itjobs.pt/emprego?remote=%s&q=%s";
    private static final String VACANCIES_REMOTE_ID = "1";

    private final ItJobsPage itJobsPage;
    private final Map<String, Set<Vacancy>> vacancies;

    public ItJobsVacancies() {
        itJobsPage = new ItJobsPage();
        vacancies = new TreeMap<>();
    }

    @Override
    public Map<String, Set<Vacancy>> getVacancies() {
        setVacanciesOnSite();
        setVacanciesRemote();
        return vacancies;
    }

    private void setVacanciesOnSite() {
        VACANCIES_ON_SITE_ID.forEach((locationName, locationId) -> {
            Set<Vacancy> onSiteVacancies = getVacanciesForLocation(URL_TEMPLATE_ON_SITE, locationId);
            vacancies.put(locationName, onSiteVacancies);
        });
    }

    private void setVacanciesRemote() {
        Set<Vacancy> remoteVacancies = getVacanciesForLocation(URL_TEMPLATE_REMOTE, VACANCIES_REMOTE_ID);
        vacancies.put("Remote", remoteVacancies);
    }

    private Set<Vacancy> getVacanciesForLocation(String urlTemplate, String locationId) {
        open(String.format(urlTemplate, locationId, SearchFor.TEST_AUTOMATION_QUERY));
        List<Vacancy> vacancyListTestAutomation = itJobsPage.getVacancies();

        open(String.format(urlTemplate, locationId, SearchFor.QUALITY_ASSURANCE_QUERY));
        List<Vacancy> vacancyListQualityAssurance = itJobsPage.getVacancies();

        return Stream.of(vacancyListTestAutomation, vacancyListQualityAssurance)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
