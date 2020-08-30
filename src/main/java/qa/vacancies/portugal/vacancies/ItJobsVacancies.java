package qa.vacancies.portugal.vacancies;

import qa.vacancies.portugal.pages.ItJobsPage;
import qa.vacancies.portugal.pages.PageObject;

public class ItJobsVacancies extends Vacancies {
    private static final String AVEIRO_ID = "1";
    private static final String BRAGA_ID = "4";
    private static final String COIMBRA_ID = "8";
    private static final String LISBOA_ID = "14";
    private static final String PORTO_ID = "18";

    private final PageObject<ItJobsPage> itJobsPage;

    public ItJobsVacancies() {
        super(AVEIRO_ID, BRAGA_ID, COIMBRA_ID, LISBOA_ID, PORTO_ID);
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
