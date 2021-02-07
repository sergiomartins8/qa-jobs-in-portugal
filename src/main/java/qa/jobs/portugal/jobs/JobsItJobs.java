package qa.jobs.portugal.jobs;

import qa.jobs.portugal.utils.model.Job;
import qa.jobs.portugal.pages.ItJobsPage;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class JobsItJobs implements Jobs {
    private static final String URL_TEMPLATE_ON_SITE = "https://www.itjobs.pt/emprego?location=%s&q=%s";
    private static final Map<String, String> JOBS_ON_SITE_ID = new TreeMap<>(Map
            .of("Aveiro", "1",
                    "Braga", "4",
                    "Coimbra", "8",
                    "Lisboa", "14",
                    "Porto", "18"));

    private static final String URL_TEMPLATE_REMOTE = "https://www.itjobs.pt/emprego?remote=%s&q=%s";
    private static final String JOBS_REMOTE_ID = "1";

    private final ItJobsPage itJobsPage;
    private final Map<String, Set<Job>> jobs;

    public JobsItJobs() {
        itJobsPage = new ItJobsPage();
        jobs = new TreeMap<>();
    }

    @Override
    public Map<String, Set<Job>> getJobs() {
        setJobsOnSite();
        setJobsRemote();
        return jobs;
    }

    private void setJobsOnSite() {
        JOBS_ON_SITE_ID.forEach((locationName, locationId) -> {
            Set<Job> onSiteJobs = getJobsForLocation(URL_TEMPLATE_ON_SITE, locationId, itJobsPage);
            jobs.put(locationName, onSiteJobs);
        });
    }

    private void setJobsRemote() {
        Set<Job> remoteJobs = getJobsForLocation(URL_TEMPLATE_REMOTE, JOBS_REMOTE_ID, itJobsPage);
        jobs.put("Remote", remoteJobs);
    }
}
