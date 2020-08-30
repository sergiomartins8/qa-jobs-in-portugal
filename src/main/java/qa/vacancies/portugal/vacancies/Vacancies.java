package qa.vacancies.portugal.vacancies;

import net.steppschuh.markdowngenerator.link.Link;
import net.steppschuh.markdowngenerator.text.emphasis.BoldText;
import net.steppschuh.markdowngenerator.text.emphasis.ItalicText;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import qa.vacancies.portugal.pages.PageObject;
import qa.vacancies.portugal.utils.constants.Constants;
import qa.vacancies.portugal.utils.markdown.MarkdownStringBuilder;
import qa.vacancies.portugal.utils.model.Vacancy;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Vacancies implements MarkdownStringBuilder {
    private final Map<String, String> locations;

    /**
     * Vacations constructor.
     *
     * @param aveiroId  Aveiro identifier
     * @param bragaId   Braga identifier
     * @param coimbraId Coimbra identifier
     * @param lisboaId  Lisboa identifier
     * @param portoId   Porto identifier
     */
    public Vacancies(String aveiroId, String bragaId, String coimbraId, String lisboaId, String portoId) {
        locations = new TreeMap<>(Map
                .of("Aveiro", aveiroId,
                        "Braga", bragaId,
                        "Coimbra", coimbraId,
                        "Lisboa", lisboaId,
                        "Porto", portoId));
    }

    /**
     * Append the website name to a string builder.
     *
     * @param sb      string builder
     * @param website website name
     */
    protected void appendWebsite(StringBuilder sb, String website) {
        sb.append(new Heading(website, Constants.HEADING_WEBSITE)).append("\n\n");
    }

    /**
     * Append a set of unique vacancies to a string builder.
     * <br>
     * For that, these vacancies are extracted using a page object; for every given location.
     *
     * @param sb  string builder
     * @param po  page object abstraction
     * @param <T> concrete page implementation
     */
    protected <T extends PageObject<T>> void appendVacancies(StringBuilder sb, PageObject<T> po) {
        locations
                .forEach((locationName, locationId) ->
                        appendVacanciesForLocation(sb, locationName, getVacanciesForLocation(po, locationId)));
    }

    private void appendVacanciesForLocation(StringBuilder sb, String location, Set<Vacancy> vacancies) {
        sb.append(new Heading(location, Constants.HEADING_LOCATION)).append("\n\n");

        vacancies
                .forEach(vacancy -> sb.append(new BoldText(vacancy.getTitle()))
                        .append(" @")
                        .append(new ItalicText(vacancy.getCompany()))
                        .append(" ")
                        .append(new Link("here", vacancy.getUrl()))
                        .append("\n\n"));
    }

    private <T extends PageObject<T>> Set<Vacancy> getVacanciesForLocation(PageObject<T> po, String locationId) {
        return Stream
                .of(Constants.TEST_AUTOMATION_QUERY, Constants.QUALITY_ASSURANCE_QUERY)
                .flatMap(query -> po
                        .openAndSearch(locationId, query)
                        .getVacancies()
                        .stream())
                .collect(Collectors.toSet());
    }
}
