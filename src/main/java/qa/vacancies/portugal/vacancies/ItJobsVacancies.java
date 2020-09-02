package qa.vacancies.portugal.vacancies;

import qa.vacancies.portugal.pages.ItJobsPage;
import qa.vacancies.portugal.pages.PageObject;
import qa.vacancies.portugal.utils.model.Location;

public class ItJobsVacancies extends Vacancies {
    private static final String URL_TEMPLATE = "https://www.itjobs.pt/emprego?location=%s&q=%s";
    private static final String URL_TEMPLATE_REMOTE = "https://www.itjobs.pt/emprego?remote=%s&q=%s";

    private static final Location AVEIRO = Location.builder().urlTemplate(URL_TEMPLATE).id("1").build();
    private static final Location BRAGA = Location.builder().urlTemplate(URL_TEMPLATE).id("4").build();
    private static final Location COIMBRA = Location.builder().urlTemplate(URL_TEMPLATE).id("8").build();
    private static final Location LISBOA = Location.builder().urlTemplate(URL_TEMPLATE).id("14").build();
    private static final Location PORTO = Location.builder().urlTemplate(URL_TEMPLATE).id("18").build();
    private static final Location REMOTE = Location.builder().urlTemplate(URL_TEMPLATE_REMOTE).id("1").build();

    private final PageObject<ItJobsPage> itJobsPage;

    public ItJobsVacancies() {
        super(AVEIRO, BRAGA, COIMBRA, LISBOA, PORTO, REMOTE);
        itJobsPage = new ItJobsPage();
    }

    @Override
    public StringBuilder stringBuilder() {
        StringBuilder sb = new StringBuilder();
        appendWebsite(sb, "ItJobs");
        appendVacancies(sb, itJobsPage);
        return sb;
    }
}
