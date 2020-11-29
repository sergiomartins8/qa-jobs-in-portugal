package qa.vacancies.portugal.vacancies;

import qa.vacancies.portugal.pages.IndeedPage;
import qa.vacancies.portugal.utils.model.Vacancy;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
            Set<Vacancy> onSiteVacancies = getVacanciesForLocation(URL_TEMPLATE_ON_SITE, locationId, indeedPage);
            vacancies.put(locationName, onSiteVacancies);
        });
    }

    private void setVacanciesRemote() {
        Set<Vacancy> remoteVacancies = getVacanciesForLocation(URL_TEMPLATE_REMOTE, VACANCIES_REMOTE_ID, indeedPage);
        vacancies.put("Remote", remoteVacancies);
    }
}
