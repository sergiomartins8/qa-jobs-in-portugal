package qa.vacancies.portugal.vacancies;

import qa.vacancies.portugal.pages.IndeedPage;
import qa.vacancies.portugal.pages.PageObject;
import qa.vacancies.portugal.utils.model.Search;

public class IndeedVacancies extends Vacancies {
    private static final String URL_TEMPLATE = "https://pt.indeed.com/ofertas?l=%s&q=%s";
    private static final String URL_TEMPLATE_REMOTE = "https://pt.indeed.com/ofertas?l=%s&q=%s";

    private static final Search AVEIRO = Search.builder().urlTemplate(URL_TEMPLATE).locationId("Aveiro").build();
    private static final Search BRAGA = Search.builder().urlTemplate(URL_TEMPLATE).locationId("Braga").build();
    private static final Search COIMBRA = Search.builder().urlTemplate(URL_TEMPLATE).locationId("Coimbra").build();
    private static final Search LISBOA = Search.builder().urlTemplate(URL_TEMPLATE).locationId("Lisboa").build();
    private static final Search PORTO = Search.builder().urlTemplate(URL_TEMPLATE).locationId("Porto").build();
    private static final Search REMOTE = Search.builder().urlTemplate(URL_TEMPLATE_REMOTE).locationId("Remoto").build();

    private final PageObject<IndeedPage> indeedPage;

    public IndeedVacancies() {
        super(AVEIRO, BRAGA, COIMBRA, LISBOA, PORTO, REMOTE);
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
