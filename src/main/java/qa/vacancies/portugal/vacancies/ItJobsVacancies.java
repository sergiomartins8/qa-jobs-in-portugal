package qa.vacancies.portugal.vacancies;

import qa.vacancies.portugal.pages.ItJobsPage;
import qa.vacancies.portugal.utils.model.Vacancy;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
            Set<Vacancy> onSiteVacancies = getVacanciesForLocation(URL_TEMPLATE_ON_SITE, locationId, itJobsPage);
            vacancies.put(locationName, onSiteVacancies);
        });
    }

    private void setVacanciesRemote() {
        Set<Vacancy> remoteVacancies = getVacanciesForLocation(URL_TEMPLATE_REMOTE, VACANCIES_REMOTE_ID, itJobsPage);
        vacancies.put("Remote", remoteVacancies);
    }
}
