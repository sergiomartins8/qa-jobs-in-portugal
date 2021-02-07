package qa.jobs.portugal.jobs;

import qa.jobs.portugal.pages.IndeedPage;
import qa.jobs.portugal.utils.model.Job;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class JobsIndeed implements Jobs {
    private static final String URL_TEMPLATE_ON_SITE = "https://pt.indeed.com/ofertas?l=%s&q=%s";
    private static final Map<String, String> JOBS_ON_SITE_ID = new TreeMap<>(Map
            .of("Aveiro", "Aveiro",
                    "Braga", "Braga",
                    "Coimbra", "Coimbra",
                    "Lisboa", "Lisboa",
                    "Porto", "Porto"));

    private static final String URL_TEMPLATE_REMOTE = "https://pt.indeed.com/ofertas?l=%s&q=%s";
    private static final String JOBS_REMOTE_ID = "Remoto";

    private final IndeedPage indeedPage;
    private final Map<String, Set<Job>> jobs;

    public JobsIndeed() {
        indeedPage = new IndeedPage();
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
            Set<Job> onSiteJobs = getJobsForLocation(URL_TEMPLATE_ON_SITE, locationId, indeedPage);
            jobs.put(locationName, onSiteJobs);
        });
    }

    private void setJobsRemote() {
        Set<Job> remoteJobs = getJobsForLocation(URL_TEMPLATE_REMOTE, JOBS_REMOTE_ID, indeedPage);
        jobs.put("Remote", remoteJobs);
    }
}
