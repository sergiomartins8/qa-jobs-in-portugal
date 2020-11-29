package qa.vacancies.portugal;

import net.steppschuh.markdowngenerator.text.emphasis.ItalicText;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import net.steppschuh.markdowngenerator.text.quote.Quote;
import qa.vacancies.portugal.utils.markdown.MarkdownFileReader;
import qa.vacancies.portugal.utils.markdown.MarkdownFileWriter;
import qa.vacancies.portugal.vacancies.GlassdoorVacancies;
import qa.vacancies.portugal.vacancies.IndeedVacancies;
import qa.vacancies.portugal.vacancies.ItJobsVacancies;
import qa.vacancies.portugal.vacancies.Vacancies;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Application {
    private static final String README = "README.md";
    private static final String README_TEMPLATE = "docs/README_TEMPLATE.md";

    private static final Vacancies GLASSDOOR_VACANCIES = new GlassdoorVacancies();
    private static final Vacancies INDEED_VACANCIES = new IndeedVacancies();
    private static final Vacancies IT_JOBS_VACANCIES = new ItJobsVacancies();

    /**
     * Magic.
     */
    public static void main(String[] args) {
        MarkdownFileWriter.truncateMarkdown(README);
        MarkdownFileWriter.appendMarkdown(README, MarkdownFileReader.getMarkdown(README_TEMPLATE));
        MarkdownFileWriter.appendMarkdown(README, lastUpdated());
        MarkdownFileWriter.appendMarkdown(README, setHeader("Glassdoor"));
        MarkdownFileWriter.appendMarkdown(README, GLASSDOOR_VACANCIES.getVacancies());
        MarkdownFileWriter.appendMarkdown(README, setHeader("Indeed"));
        MarkdownFileWriter.appendMarkdown(README, INDEED_VACANCIES.getVacancies());
        MarkdownFileWriter.appendMarkdown(README, setHeader("ItJobs"));
        MarkdownFileWriter.appendMarkdown(README, IT_JOBS_VACANCIES.getVacancies());
    }

    private static StringBuilder lastUpdated() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm", Locale.ENGLISH);
        String lastUpdated = dateTimeFormatter.format(localDateTime);

        StringBuilder sb = new StringBuilder();
        sb.append(new Quote(new ItalicText("Last updated: " + lastUpdated))).append("\n\n");
        return sb;
    }

    private static StringBuilder setHeader(String website) {
        StringBuilder sb = new StringBuilder();
        sb.append(new Heading(website, 3)).append("\n\n");
        return sb;
    }
}
