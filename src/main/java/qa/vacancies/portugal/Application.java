package qa.vacancies.portugal;

import net.steppschuh.markdowngenerator.image.Image;
import net.steppschuh.markdowngenerator.text.emphasis.ItalicText;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import net.steppschuh.markdowngenerator.text.quote.Quote;
import qa.vacancies.portugal.utils.constants.Constants;
import qa.vacancies.portugal.utils.markdown.MarkdownFileWriter;
import qa.vacancies.portugal.utils.markdown.MarkdownStringBuilder;
import qa.vacancies.portugal.vacancies.GlassdoorVacancies;
import qa.vacancies.portugal.vacancies.ItJobsVacancies;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Application {
    private static final String README = "README.md";
    private static final String TITLE = "QA vacancies in Portugal";
    private static final String DESCRIPTION = "An awesome curated list of the most recent QA vacancies in Portugal, updated every day!";
    private static final String VACANCIES = "Vacancies";
    private static final String STAR_IF_USEFUL_SHIELD = "https://img.shields.io/static/v1"
            + "?label=%F0%9F%8C%9F&message=If%20Useful&style=style=flat&color=BC4E99";
    private static final String STARS_SHIELD = "https://img.shields.io/github/stars/sergiomartins8/qa-vacancies-in-portugal";
    private static final String FORKS_SHIELD = "https://img.shields.io/github/forks/sergiomartins8/qa-vacancies-in-portugal";
    private static final String LINKEDIN_SHIELD = "https://img.shields.io/badge/-sergiomartins8-blue"
            + "?logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/sergiomartins8/";
    private static final String RELEASE_BUILD_SHIELD = "https://img.shields.io/github/v/release/sergiomartins8/qa-vacancies-in-portugal";
    private static final String RELEASES_SHIELD = "https://github.com/sergiomartins8/qa-vacancies-in-portugal/workflows/release/badge.svg";
    private static final String ISSUES_SHIELD = "https://img.shields.io/github/issues/sergiomartins8/qa-vacancies-in-portugal";
    private static final String CONTRIBUTORS_SHIELD = "https://img.shields.io/github/contributors/sergiomartins8/qa-vacancies-in-portugal";
    private static final String LICENSE_SHIELD = "https://img.shields.io/github/license/sergiomartins8/qa-vacancies-in-portugal";

    private static final MarkdownStringBuilder IT_JOBS_VACANCIES = new ItJobsVacancies();
    private static final MarkdownStringBuilder GLASSDOOR_VACANCIES = new GlassdoorVacancies();

    /**
     * Magic.
     */
    public static void main(String[] args) {
        MarkdownFileWriter.truncateMarkdown(README);
        MarkdownFileWriter.appendMarkdown(README, fillTop());
        MarkdownFileWriter.appendMarkdown(README, lastUpdated());
        MarkdownFileWriter.appendMarkdown(README, IT_JOBS_VACANCIES.stringBuilder());
        MarkdownFileWriter.appendMarkdown(README, GLASSDOOR_VACANCIES.stringBuilder());
    }

    private static StringBuilder fillTop() {
        StringBuilder sb = new StringBuilder();
        sb.append(new Heading(TITLE, Constants.HEADING_TITLE)).append("\n\n")
                .append(new Image("", STAR_IF_USEFUL_SHIELD)).append("\n")
                .append(new Image("", STARS_SHIELD)).append("\n")
                .append(new Image("", FORKS_SHIELD)).append("\n")
                .append(new Image("", LINKEDIN_SHIELD)).append("\n\n")
                .append(new ItalicText(DESCRIPTION))
                .append("\n\n")
                .append(new Image("", RELEASE_BUILD_SHIELD)).append("\n")
                .append(new Image("", RELEASES_SHIELD)).append("\n")
                .append(new Image("", ISSUES_SHIELD)).append("\n")
                .append(new Image("", CONTRIBUTORS_SHIELD)).append("\n")
                .append(new Image("", LICENSE_SHIELD)).append("\n\n")
                .append(new Heading(VACANCIES, Constants.HEADING_SUBTITLE)).append("\n\n");
        return sb;
    }

    private static StringBuilder lastUpdated() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm", Locale.ENGLISH);
        String lastUpdated = dateTimeFormatter.format(localDateTime);

        StringBuilder sb = new StringBuilder();
        sb.append(new Quote(new ItalicText("Last updated: " + lastUpdated))).append("\n\n");
        return sb;
    }
}
