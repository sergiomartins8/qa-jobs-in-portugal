package qa.vacancies.portugal.vacancies;

import net.steppschuh.markdowngenerator.link.Link;
import net.steppschuh.markdowngenerator.text.emphasis.BoldText;
import net.steppschuh.markdowngenerator.text.emphasis.ItalicText;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import qa.vacancies.portugal.pages.ItJobsPage;
import qa.vacancies.portugal.utils.constants.Constants;
import qa.vacancies.portugal.utils.markdown.MarkdownStringBuilder;
import qa.vacancies.portugal.utils.model.Vacancy;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ItJobsVacancies implements MarkdownStringBuilder {
    private static final String AVEIRO_ID = "1";

    private final ItJobsPage itJobsPage;
    private final StringBuilder sb;

    public ItJobsVacancies() {
        itJobsPage = new ItJobsPage();
        sb = new StringBuilder();
    }

    @Override
    public StringBuilder getSb() {
        sb.append(new Heading("ItJobs", 3)).append("\n\n");

        appendVacancies("Aveiro", getVacanciesAveiro());
        // all of them

        return sb;
    }

    private Set<Vacancy> getVacanciesAveiro() {
        List<Vacancy> testAutomationAveiro = getVacanciesForLocation(AVEIRO_ID, Constants.TEST_AUTOMATION_QUERY);
        List<Vacancy> qualityAssuranceAveiro = getVacanciesForLocation(AVEIRO_ID, Constants.QUALITY_ASSURANCE_QUERY);
        return mergeLists(testAutomationAveiro, qualityAssuranceAveiro);
    }

    private List<Vacancy> getVacanciesForLocation(String locationId, String query) {
        return itJobsPage
                .openAndSearch(locationId, query)
                .getVacancies();
    }

    private Set<Vacancy> mergeLists(List<Vacancy> testAutomationList, List<Vacancy> qualityAssuranceList) {
        return Stream
                .concat(testAutomationList.stream(), qualityAssuranceList.stream())
                .collect(Collectors.toSet());
    }

    @SafeVarargs
    private void appendVacancies(String location, Set<Vacancy>... vacancies) {
        sb.append(new Heading(location, 5)).append("\n\n");
        Arrays.stream(vacancies).forEach(set -> {
            set.forEach(vacancy -> {
                sb.append(new BoldText(vacancy.getTitle()))
                        .append(" @")
                        .append(new ItalicText(vacancy.getCompany()))
                        .append(" ")
                        .append(new Link("here", vacancy.getUrl()))
                        .append("\n\n");
            });
        });
    }
}
