package qa.vacancies.portugal.utils.config;

import qa.vacancies.portugal.utils.logging.Loggable;

public class CustomConfigurationHolder implements Loggable {
    private final String customConfiguration = System.getProperty("custom.configuration");

    public String exampleConfiguration() {
        logger().info("Custom config: " + customConfiguration);
        return customConfiguration;
    }
}
