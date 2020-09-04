package qa.vacancies.portugal.vacancies;

import net.steppschuh.markdowngenerator.link.Link;
import net.steppschuh.markdowngenerator.list.UnorderedList;
import net.steppschuh.markdowngenerator.text.emphasis.BoldText;
import net.steppschuh.markdowngenerator.text.emphasis.ItalicText;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import qa.vacancies.portugal.pages.PageObject;
import qa.vacancies.portugal.utils.constants.Constants;
import qa.vacancies.portugal.utils.markdown.MarkdownStringBuilder;
import qa.vacancies.portugal.utils.model.Location;
import qa.vacancies.portugal.utils.model.Vacancy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Vacancies implements MarkdownStringBuilder {
    private final Map<String, Location> locations;

    /**
     * Vacations constructor.
     *
     * @param aveiro  Aveiro location
     * @param braga   Braga location
     * @param coimbra Coimbra location
     * @param lisboa  Lisboa location
     * @param porto   Porto location
     * @param remote  Porto location
     */
    public Vacancies(Location aveiro, Location braga, Location coimbra, Location lisboa, Location porto, Location remote) {
        locations = new TreeMap<>(Map
                .of("Aveiro", aveiro,
                        "Braga", braga,
                        "Coimbra", coimbra,
                        "Lisboa", lisboa,
                        "Porto", porto,
                        "Remote", remote));
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
                .forEach((locationName, location) ->
                        appendVacanciesForLocation(sb, locationName, getVacanciesForLocation(po, location)));
    }

    private void appendVacanciesForLocation(StringBuilder sb, String location, Set<Vacancy> vacancies) {
        List<String> vacancyDescriptionsList = new ArrayList<>();

        sb.append(new Heading(location, Constants.HEADING_LOCATION)).append("\n\n");

        vacancies
                .forEach(vacancy -> {
                    String vacancyDescription = new BoldText(vacancy.getTitle())
                            + " @" + new ItalicText(vacancy.getCompany()) + " "
                            + new Link("here", vacancy.getUrl()) + "\n\n";

                    vacancyDescriptionsList.add(vacancyDescription);
                });

        sb.append(new UnorderedList<>(vacancyDescriptionsList));
    }

    private <T extends PageObject<T>> Set<Vacancy> getVacanciesForLocation(PageObject<T> po, Location location) {
        return Stream
                .of(Constants.TEST_AUTOMATION_QUERY, Constants.QUALITY_ASSURANCE_QUERY)
                .flatMap(query -> po
                        .openAndSearch(location.getUrlTemplate(), location.getId(), query)
                        .getVacancies()
                        .stream())
                .collect(Collectors.toSet());
    }
}
