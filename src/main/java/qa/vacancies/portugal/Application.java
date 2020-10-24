package qa.vacancies.portugal;

import net.steppschuh.markdowngenerator.text.emphasis.ItalicText;
import net.steppschuh.markdowngenerator.text.quote.Quote;
import qa.vacancies.portugal.utils.markdown.MarkdownFileReader;
import qa.vacancies.portugal.utils.markdown.MarkdownFileWriter;
import qa.vacancies.portugal.utils.markdown.MarkdownStringBuilder;
import qa.vacancies.portugal.vacancies.GlassdoorVacancies;
import qa.vacancies.portugal.vacancies.IndeedVacancies;
import qa.vacancies.portugal.vacancies.ItJobsVacancies;
import qa.vacancies.portugal.vacancies.LandingJobsVacancies;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Application {
    private static final String README = "README.md";
    private static final String README_TEMPLATE = "docs/README_TEMPLATE.md";

    private static final MarkdownStringBuilder GLASSDOOR_VACANCIES = new GlassdoorVacancies();
    private static final MarkdownStringBuilder INDEED_VACANCIES = new IndeedVacancies();
    private static final MarkdownStringBuilder IT_JOBS_VACANCIES = new ItJobsVacancies();
    private static final MarkdownStringBuilder LANDING_JOBS_VACANCIES = new LandingJobsVacancies();

    /**
     * Magic.
     */
    public static void main(String[] args) {
        MarkdownFileWriter.truncateMarkdown(README);
        MarkdownFileWriter.appendMarkdown(README, MarkdownFileReader.getMarkdown(README_TEMPLATE));
        MarkdownFileWriter.appendMarkdown(README, lastUpdated());
        MarkdownFileWriter.appendMarkdown(README, GLASSDOOR_VACANCIES.stringBuilder());
        MarkdownFileWriter.appendMarkdown(README, INDEED_VACANCIES.stringBuilder());
        MarkdownFileWriter.appendMarkdown(README, IT_JOBS_VACANCIES.stringBuilder());
        MarkdownFileWriter.appendMarkdown(README, LANDING_JOBS_VACANCIES.stringBuilder());
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
