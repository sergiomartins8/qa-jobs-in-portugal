package qa.vacancies.portugal.utils.markdown;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MarkdownFileWriter {

    /**
     * Private constructor to avoid instantiation.
     */
    private MarkdownFileWriter() {
    }

    @SneakyThrows(value = IOException.class)
    public static void appendMarkdown(String filename, StringBuilder sb) {
        Files.write(Paths.get(filename), sb.toString().getBytes(), StandardOpenOption.APPEND);
    }

    @SneakyThrows(value = IOException.class)
    public static void appendMarkdown(String filename, byte[] bytes) {
        Files.write(Paths.get(filename), bytes, StandardOpenOption.APPEND);
    }

    @SneakyThrows(value = IOException.class)
    public static void truncateMarkdown(String filename) {
        Files.write(Paths.get(filename), "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }
}
