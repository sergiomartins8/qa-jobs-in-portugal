package qa.jobs.portugal.utils.markdown;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@UtilityClass
public class MarkdownFileReader {
    @SneakyThrows(value = IOException.class)
    public static byte[] getMarkdown(String filename) {
        return Files.readAllBytes(Paths.get(filename));
    }
}
