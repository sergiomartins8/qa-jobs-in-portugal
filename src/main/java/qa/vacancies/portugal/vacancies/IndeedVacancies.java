package qa.vacancies.portugal.vacancies;

import qa.vacancies.portugal.pages.IndeedPage;
import qa.vacancies.portugal.pages.PageObject;
import qa.vacancies.portugal.utils.model.Location;

public class IndeedVacancies extends Vacancies {
    private static final String URL_TEMPLATE = "https://pt.indeed.com/ofertas?l=%s&q=%s";
    private static final String URL_TEMPLATE_REMOTE = "https://pt.indeed.com/ofertas?l=%s&q=%s";

    private static final Location AVEIRO = Location.builder().urlTemplate(URL_TEMPLATE).id("Aveiro").build();
    private static final Location BRAGA = Location.builder().urlTemplate(URL_TEMPLATE).id("Braga").build();
    private static final Location COIMBRA = Location.builder().urlTemplate(URL_TEMPLATE).id("Coimbra").build();
    private static final Location LISBOA = Location.builder().urlTemplate(URL_TEMPLATE).id("Lisboa").build();
    private static final Location PORTO = Location.builder().urlTemplate(URL_TEMPLATE).id("Porto").build();
    private static final Location REMOTE = Location.builder().urlTemplate(URL_TEMPLATE_REMOTE).id("Remoto").build();

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
