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
    private static final String BRAGA_ID = "4";
    private static final String PORTO_ID = "18";
    private static final String AVEIRO_ID = "1";
    private static final String COIMBRA_ID = "8";
    private static final String LISBOA_ID = "14";

    private final ItJobsPage itJobsPage;
    private final StringBuilder sb;

    public ItJobsVacancies() {
        itJobsPage = new ItJobsPage();
        sb = new StringBuilder();
    }

    @Override
    public StringBuilder stringBuilder() {
        sb.append(new Heading("ItJobs", Constants.HEADING_WEBSITE)).append("\n\n");
        appendVacancies("Braga", getVacanciesForLocation(BRAGA_ID));
        appendVacancies("Porto", getVacanciesForLocation(PORTO_ID));
        appendVacancies("Aveiro", getVacanciesForLocation(AVEIRO_ID));
        appendVacancies("Coimbra", getVacanciesForLocation(COIMBRA_ID));
        appendVacancies("Lisboa", getVacanciesForLocation(LISBOA_ID));
        return sb;
    }

    /**
     * Searches for "test automation" and "quality assurance" vacancies.
     *
     * @param locationId location identifier
     * @return a unique list of vacancies for the given location
     */
    private Set<Vacancy> getVacanciesForLocation(String locationId) {
        List<Vacancy> testAutomation = itJobsPage
                .openAndSearch(locationId, Constants.TEST_AUTOMATION_QUERY)
                .getVacancies();

        List<Vacancy> qualityAssurance = itJobsPage
                .openAndSearch(locationId, Constants.QUALITY_ASSURANCE_QUERY)
                .getVacancies();

        return mergeListsToSet(testAutomation, qualityAssurance);
    }

    /**
     * Since lists may have overlapped vacancies, this method merges two lists into a set.
     * Thus, ensuring the uniqueness of each vacancy.
     *
     * @param testAutomationList   test automation vacancy list
     * @param qualityAssuranceList quality assurance vacancy list
     * @return a set of unique vacancies
     */
    private Set<Vacancy> mergeListsToSet(List<Vacancy> testAutomationList, List<Vacancy> qualityAssuranceList) {
        return Stream
                .concat(testAutomationList.stream(), qualityAssuranceList.stream())
                .collect(Collectors.toSet());
    }

    @SafeVarargs
    private void appendVacancies(String location, Set<Vacancy>... vacancies) {
        sb.append(new Heading(location, Constants.HEADING_LOCATION)).append("\n\n");
        Arrays.stream(vacancies).forEach(set ->
                set.forEach(vacancy ->
                        sb.append(new BoldText(vacancy.getTitle()))
                                .append(" @")
                                .append(new ItalicText(vacancy.getCompany()))
                                .append(" ")
                                .append(new Link("here", vacancy.getUrl()))
                                .append("\n\n")));
    }
}
