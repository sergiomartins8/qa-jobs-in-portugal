package qa.vacancies.portugal;

import net.steppschuh.markdowngenerator.text.heading.Heading;
import qa.vacancies.portugal.utils.markdown.MarkdownFileWriter;
import qa.vacancies.portugal.utils.markdown.MarkdownStringBuilder;
import qa.vacancies.portugal.vacancies.ItJobsVacancies;

public class Application {
    private static final String FILE_NAME = "README.md";
    private static final MarkdownStringBuilder IT_JOBS_VACANCIES = new ItJobsVacancies();

    /**
     * Magic.
     */
    public static void main(String[] args) {
        MarkdownFileWriter.truncateMarkdown(FILE_NAME);
        MarkdownFileWriter.appendMarkdown(FILE_NAME, fillTop());
        MarkdownFileWriter.appendMarkdown(FILE_NAME, IT_JOBS_VACANCIES.getSb());
    }

    private static StringBuilder fillTop() {
        StringBuilder sb = new StringBuilder();
        sb.append(new Heading("QA Vacancies in Portugal", 1));
        sb.append("\n\n");
        return sb;
    }
}
