package qa.vacancies.portugal.vacancies;

import qa.vacancies.portugal.pages.LandingJobsPage;
import qa.vacancies.portugal.pages.PageObject;
import qa.vacancies.portugal.utils.model.Location;

public class LandingJobsVacancies extends Vacancies {
    private static final String URL_TEMPLATE = "https://landing.jobs/jobs?page=1&location=%s&lr=0&match=all&country=PT&q=%s";
    private static final String URL_TEMPLATE_REMOTE = "https://landing.jobs/jobs?page=1&lr=0&fr=true&match=all&q=%s";

    private static final Location AVEIRO = Location.builder().urlTemplate(URL_TEMPLATE).id("Aveiro, Portugal&city=Aveiro").build();
    private static final Location BRAGA = Location.builder().urlTemplate(URL_TEMPLATE).id("Braga, Portugal&city=Braga").build();
    private static final Location COIMBRA = Location.builder().urlTemplate(URL_TEMPLATE).id("Coimbra, Portugal&city=Coimbra").build();
    private static final Location LISBOA = Location.builder().urlTemplate(URL_TEMPLATE).id("Lisbon, Portugal&city=Lisbon").build();
    private static final Location PORTO = Location.builder().urlTemplate(URL_TEMPLATE).id("Porto, Portugal&city=Porto").build();
    private static final Location REMOTE = Location.builder().urlTemplate(URL_TEMPLATE_REMOTE).build();

    private final PageObject<LandingJobsPage> landingJobsPage;

    public LandingJobsVacancies() {
        super(AVEIRO, BRAGA, COIMBRA, LISBOA, PORTO, REMOTE);
        landingJobsPage = new LandingJobsPage();
    }

    @Override
    public StringBuilder stringBuilder() {
        StringBuilder sb = new StringBuilder();
        appendWebsite(sb, "LandingJobs");
        appendVacancies(sb, landingJobsPage);
        return sb;
    }
}
