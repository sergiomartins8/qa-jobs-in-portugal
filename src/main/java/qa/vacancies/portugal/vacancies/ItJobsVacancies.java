package qa.vacancies.portugal.vacancies;

import qa.vacancies.portugal.pages.ItJobsPage;
import qa.vacancies.portugal.pages.PageObject;
import qa.vacancies.portugal.utils.model.Search;

public class ItJobsVacancies extends Vacancies {
    private static final String URL_TEMPLATE = "https://www.itjobs.pt/emprego?location=%s&q=%s";
    private static final String URL_TEMPLATE_REMOTE = "https://www.itjobs.pt/emprego?remote=%s&q=%s";

    private static final Search AVEIRO = Search.builder().urlTemplate(URL_TEMPLATE).locationId("1").build();
    private static final Search BRAGA = Search.builder().urlTemplate(URL_TEMPLATE).locationId("4").build();
    private static final Search COIMBRA = Search.builder().urlTemplate(URL_TEMPLATE).locationId("8").build();
    private static final Search LISBOA = Search.builder().urlTemplate(URL_TEMPLATE).locationId("14").build();
    private static final Search PORTO = Search.builder().urlTemplate(URL_TEMPLATE).locationId("18").build();
    private static final Search REMOTE = Search.builder().urlTemplate(URL_TEMPLATE_REMOTE).locationId("1").build();

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
