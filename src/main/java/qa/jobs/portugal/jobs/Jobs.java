package qa.jobs.portugal.jobs;

import qa.jobs.portugal.utils.model.Job;
import qa.jobs.portugal.pages.VacancyPage;
import qa.jobs.portugal.utils.constants.SearchFor;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;

public interface Jobs {
    Map<String, Set<Job>> getJobs();

    /**
     * Get the list of jobs in a page, for a given location.
     *
     * @param urlTemplate url template for the given page
     * @param locationId  location id for the url template query parameter
     * @param page        object that maps the current page to be visited
     * @return set of unique jobs for a given location
     */
    default Set<Job> getJobsForLocation(String urlTemplate, String locationId, VacancyPage page) {
        open(String.format(urlTemplate, locationId, SearchFor.TEST_AUTOMATION_QUERY));
        List<Job> jobListTestAutomation = page.getJobs();

        open(String.format(urlTemplate, locationId, SearchFor.QUALITY_ASSURANCE_QUERY));
        List<Job> jobListQualityAssurance = page.getJobs();

        return Stream.of(jobListTestAutomation, jobListQualityAssurance)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
