package qa.vacancies.portugal;

import net.steppschuh.markdowngenerator.image.Image;
import net.steppschuh.markdowngenerator.text.Text;
import net.steppschuh.markdowngenerator.text.emphasis.ItalicText;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import qa.vacancies.portugal.utils.markdown.MarkdownFileWriter;
import qa.vacancies.portugal.utils.markdown.MarkdownStringBuilder;
import qa.vacancies.portugal.vacancies.ItJobsVacancies;

public class Application {
    private static final String README = "README.md";
    private static final String TITLE = "QA Vacancies in Portugal :portugal:";
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
    private static final String UNICORN_IMAGE = "docs/img/my_unicorn.png";

    private static final MarkdownStringBuilder IT_JOBS_VACANCIES = new ItJobsVacancies();

    /**
     * Magic.
     */
    public static void main(String[] args) {
        MarkdownFileWriter.truncateMarkdown(README);
        MarkdownFileWriter.appendMarkdown(README, fillTop());
//        MarkdownFileWriter.appendMarkdown(README, IT_JOBS_VACANCIES.getSb());
    }

    private static StringBuilder fillTop() {
        StringBuilder sb = new StringBuilder();
        sb.append(new Heading(TITLE, 1));
        sb.append("\n\n");
        sb.append(new Image("", UNICORN_IMAGE));
        sb.append("\n\n");
        sb.append(new Image("", STAR_IF_USEFUL_SHIELD));
        sb.append("\n");
        sb.append(new Image("", STARS_SHIELD));
        sb.append("\n");
        sb.append(new Image("", FORKS_SHIELD));
        sb.append("\n");
        sb.append(new Image("", LINKEDIN_SHIELD));
        sb.append("\n\n");
        sb.append("\n\n");
        sb.append(new ItalicText("An awesome curated list of the most recent QA vacancies in Portugal :portugal:"));
        sb.append("\n\n");
        sb.append(new ItalicText("Updates every day!"));
        sb.append(new Text(" \uD83C\uDD95"));
        sb.append("\n\n");
        sb.append(new Image("", RELEASE_BUILD_SHIELD));
        sb.append("\n");
        sb.append(new Image("", RELEASES_SHIELD));
        sb.append("\n");
        sb.append(new Image("", ISSUES_SHIELD));
        sb.append("\n");
        sb.append(new Image("", CONTRIBUTORS_SHIELD));
        sb.append("\n");
        sb.append(new Image("", LICENSE_SHIELD));
        return sb;
    }
}
