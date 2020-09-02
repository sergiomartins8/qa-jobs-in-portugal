package qa.vacancies.portugal.vacancies;

import qa.vacancies.portugal.pages.GlassdoorPage;
import qa.vacancies.portugal.pages.PageObject;
import qa.vacancies.portugal.utils.model.Location;

public class GlassdoorVacancies extends Vacancies {
    private static final String URL_TEMPLATE = "https://www.glassdoor.com/Job/jobs.htm?locT=C&locId=%s&jobType=&context=Jobs&sc.keyword=%s";
    private static final String URL_TEMPLATE_REMOTE
            = "https://www.glassdoor.com/Job/jobs.htm?locT=N&locId=195&jobType=&context=Jobs&remoteWorkType=%s&sc.keyword=%s";

    private static final Location AVEIRO = Location.builder().urlTemplate(URL_TEMPLATE).id("3185896").build();
    private static final Location BRAGA = Location.builder().urlTemplate(URL_TEMPLATE).id("3227540").build();
    private static final Location COIMBRA = Location.builder().urlTemplate(URL_TEMPLATE).id("3177365").build();
    private static final Location LISBOA = Location.builder().urlTemplate(URL_TEMPLATE).id("3192045").build();
    private static final Location PORTO = Location.builder().urlTemplate(URL_TEMPLATE).id("3183562").build();
    private static final Location REMOTE = Location.builder().urlTemplate(URL_TEMPLATE_REMOTE).id("1").build();

    private final PageObject<GlassdoorPage> glassdoorPage;

    public GlassdoorVacancies() {
        super(AVEIRO, BRAGA, COIMBRA, LISBOA, PORTO, REMOTE);
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
