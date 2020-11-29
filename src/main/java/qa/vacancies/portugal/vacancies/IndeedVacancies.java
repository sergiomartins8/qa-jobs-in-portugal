package qa.vacancies.portugal.vacancies;

import qa.vacancies.portugal.pages.IndeedPage;
import qa.vacancies.portugal.utils.constants.Constants;
import qa.vacancies.portugal.utils.model.Vacancy;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;

public class IndeedVacancies implements Vacancies {
    private static final String URL_TEMPLATE_ON_SITE = "https://pt.indeed.com/ofertas?l=%s&q=%s";
    private static final Map<String, String> VACANCIES_ON_SITE_ID = new TreeMap<>(Map
            .of("Aveiro", "Aveiro",
                    "Braga", "Braga",
                    "Coimbra", "Coimbra",
                    "Lisboa", "Lisboa",
                    "Porto", "Porto"));

    private static final String URL_TEMPLATE_REMOTE = "https://pt.indeed.com/ofertas?l=%s&q=%s";
    private static final String VACANCIES_REMOTE_ID = "Remoto";

    private final IndeedPage indeedPage;
    private final Map<String, Set<Vacancy>> vacancies;

    public IndeedVacancies() {
        indeedPage = new IndeedPage();
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
        open(String.format(urlTemplate, locationId, Constants.TEST_AUTOMATION_QUERY));
        List<Vacancy> vacancyListTestAutomation = indeedPage.getVacancies();

        open(String.format(urlTemplate, locationId, Constants.QUALITY_ASSURANCE_QUERY));
        List<Vacancy> vacancyListQualityAssurance = indeedPage.getVacancies();

        return Stream.of(vacancyListTestAutomation, vacancyListQualityAssurance)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
