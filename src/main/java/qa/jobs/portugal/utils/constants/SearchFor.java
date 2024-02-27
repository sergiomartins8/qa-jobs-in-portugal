package qa.jobs.portugal.utils.constants;

import com.codeborne.selenide.WebElementCondition;
import lombok.experimental.UtilityClass;

import static com.codeborne.selenide.Condition.anyOf;
import static com.codeborne.selenide.Condition.text;

@UtilityClass
public class SearchFor {
    public static final WebElementCondition KEYWORDS = anyOf(
            text("qa"),
            text("test"),
            text("quality"),
            text("assurance")
    );

    public static final String TEST_AUTOMATION_QUERY = "test automation";
    public static final String QUALITY_ASSURANCE_QUERY = "quality assurance";
}
