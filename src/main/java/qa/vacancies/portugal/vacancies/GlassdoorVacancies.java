package qa.vacancies.portugal.vacancies;

import qa.vacancies.portugal.pages.GlassdoorPage;
import qa.vacancies.portugal.pages.PageObject;

public class GlassdoorVacancies extends Vacancies {
    private static final String AVEIRO_ID = "3185896";
    private static final String BRAGA_ID = "3227540";
    private static final String COIMBRA_ID = "3177365";
    private static final String LISBOA_ID = "3192045";
    private static final String PORTO_ID = "3183562";

    private final PageObject<GlassdoorPage> glassdoorPage;

    public GlassdoorVacancies() {
        super(AVEIRO_ID, BRAGA_ID, COIMBRA_ID, LISBOA_ID, PORTO_ID);
        glassdoorPage = new GlassdoorPage();
    }

    @Override
    public StringBuilder stringBuilder() {
        StringBuilder sb = new StringBuilder();
        appendWebsite(sb, "Glassdoor");
        appendVacancies(sb, glassdoorPage);
        return sb;
    }
}
