package qa.vacancies.portugal.vacancies;

import qa.vacancies.portugal.pages.GlassdoorPage;
import qa.vacancies.portugal.utils.model.Vacancy;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GlassdoorVacancies implements Vacancies {
    private static final String URL_TEMPLATE_ON_SITE
            = "https://www.glassdoor.com/Job/jobs.htm?locT=C&locId=%s&jobType=&context=Jobs&sc.keyword=%s";
    private static final Map<String, String> VACANCIES_ON_SITE_ID = new TreeMap<>(Map
            .of("Aveiro", "3185896",
                    "Braga", "3227540",
                    "Coimbra", "3177365",
                    "Lisboa", "3192045",
                    "Porto", "3183562"));

    private static final String URL_TEMPLATE_REMOTE
            = "https://www.glassdoor.com/Job/jobs.htm?locT=N&locId=195&jobType=&context=Jobs&remoteWorkType=%s&sc.keyword=%s";
    private static final String VACANCIES_REMOTE_ID = "1";

    private final GlassdoorPage glassdoorPage;
    private final Map<String, Set<Vacancy>> vacancies;

    public GlassdoorVacancies() {
        glassdoorPage = new GlassdoorPage();
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
            Set<Vacancy> onSiteVacancies = getVacanciesForLocation(URL_TEMPLATE_ON_SITE, locationId, glassdoorPage);
            vacancies.put(locationName, onSiteVacancies);
        });
    }

    private void setVacanciesRemote() {
        Set<Vacancy> remoteVacancies = getVacanciesForLocation(URL_TEMPLATE_REMOTE, VACANCIES_REMOTE_ID, glassdoorPage);
        vacancies.put("Remote", remoteVacancies);
    }
}
