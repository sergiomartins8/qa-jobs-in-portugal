package qa.jobs.portugal;

import net.steppschuh.markdowngenerator.text.emphasis.ItalicText;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import net.steppschuh.markdowngenerator.text.quote.Quote;
import qa.jobs.portugal.jobs.Jobs;
import qa.jobs.portugal.jobs.JobsGlassdoor;
import qa.jobs.portugal.jobs.JobsIndeed;
import qa.jobs.portugal.jobs.JobsItJobs;
import qa.jobs.portugal.utils.markdown.MarkdownFileReader;
import qa.jobs.portugal.utils.markdown.MarkdownFileWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Application {
    private static final String README = "README.md";
    private static final String README_TEMPLATE = "docs/README_TEMPLATE.md";

    private static final Jobs GLASSDOOR = new JobsGlassdoor();
    private static final Jobs INDEED = new JobsIndeed();
    private static final Jobs IT_JOBS = new JobsItJobs();

    /**
     * Magic.
     */
    public static void main(String[] args) {
        MarkdownFileWriter.truncateMarkdown(README);
        MarkdownFileWriter.appendMarkdown(README, MarkdownFileReader.getMarkdown(README_TEMPLATE));
        MarkdownFileWriter.appendMarkdown(README, lastUpdated());
        MarkdownFileWriter.appendMarkdown(README, setHeader("Glassdoor"));
        MarkdownFileWriter.appendMarkdown(README, GLASSDOOR.getJobs());
        MarkdownFileWriter.appendMarkdown(README, setHeader("Indeed"));
        MarkdownFileWriter.appendMarkdown(README, INDEED.getJobs());
        MarkdownFileWriter.appendMarkdown(README, setHeader("ItJobs"));
        MarkdownFileWriter.appendMarkdown(README, IT_JOBS.getJobs());
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
