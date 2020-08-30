package qa.vacancies.portugal.vacancies;

import qa.vacancies.portugal.pages.IndeedPage;
import qa.vacancies.portugal.pages.PageObject;

public class IndeedVacancies extends Vacancies {
    private static final String AVEIRO_ID = "Aveiro";
    private static final String BRAGA_ID = "Braga";
    private static final String COIMBRA_ID = "Coimbra";
    private static final String LISBOA_ID = "Lisboa";
    private static final String PORTO_ID = "Porto";

    private final PageObject<IndeedPage> indeedPage;

    public IndeedVacancies() {
        super(AVEIRO_ID, BRAGA_ID, COIMBRA_ID, LISBOA_ID, PORTO_ID);
        indeedPage = new IndeedPage();
    }

    @Override
    public StringBuilder stringBuilder() {
        StringBuilder sb = new StringBuilder();
        appendWebsite(sb, "Indeed");
        appendVacancies(sb, indeedPage);
        return sb;
    }
}
