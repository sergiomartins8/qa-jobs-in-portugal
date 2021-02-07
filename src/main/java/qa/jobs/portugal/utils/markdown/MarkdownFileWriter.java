package qa.jobs.portugal.utils.markdown;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import net.steppschuh.markdowngenerator.link.Link;
import net.steppschuh.markdowngenerator.list.UnorderedList;
import net.steppschuh.markdowngenerator.text.emphasis.BoldText;
import net.steppschuh.markdowngenerator.text.emphasis.ItalicText;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import qa.jobs.portugal.utils.model.Job;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@UtilityClass
public class MarkdownFileWriter {
    @SneakyThrows(value = IOException.class)
    public static void truncateMarkdown(String filename) {
        Files.write(Paths.get(filename), "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }

    @SneakyThrows(value = IOException.class)
    public static void appendMarkdown(String filename, byte[] bytes) {
        Files.write(Paths.get(filename), bytes, StandardOpenOption.APPEND);
    }

    @SneakyThrows(value = IOException.class)
    public static void appendMarkdown(String filename, StringBuilder sb) {
        Files.write(Paths.get(filename), sb.toString().getBytes(), StandardOpenOption.APPEND);
    }

    /**
     * Append all the jobs list to a given markdown file.
     *
     * @param filename name of the markdown file
     * @param jobs     map of jobs
     */
    @SneakyThrows(value = IOException.class)
    public static void appendMarkdown(String filename, Map<String, Set<Job>> jobs) {
        StringBuilder sb = new StringBuilder();

        jobs.forEach((locationName, vacancySet) -> {
            List<String> vacancyDescriptionsList = new ArrayList<>();
            sb.append(new Heading(locationName, 4)).append("\n\n");
            vacancySet.forEach(vacancy -> {
                String vacancyDescription = new BoldText(vacancy.getTitle())
                        + " @" + new ItalicText(vacancy.getCompany()) + " "
                        + new Link("here", vacancy.getUrl()) + "\n\n";
                vacancyDescriptionsList.add(vacancyDescription);
            });
            sb.append(new UnorderedList<>(vacancyDescriptionsList));
        });

        Files.write(Paths.get(filename), sb.toString().getBytes(), StandardOpenOption.APPEND);
    }
}
