package qa.vacancies.portugal.utils.markdown;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MarkdownFileReader {

    /**
     * Private constructor to avoid instantiation.
     */
    private MarkdownFileReader() {
    }

    @SneakyThrows(value = IOException.class)
    public static byte[] getMarkdown(String filename) {
        return Files.readAllBytes(Paths.get(filename));
    }
}
