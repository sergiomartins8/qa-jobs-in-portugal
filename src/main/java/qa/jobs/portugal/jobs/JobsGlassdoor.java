package qa.jobs.portugal.jobs;

import qa.jobs.portugal.utils.model.Job;
import qa.jobs.portugal.pages.GlassdoorPage;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class JobsGlassdoor implements Jobs {
    private static final String URL_TEMPLATE_ON_SITE
            = "https://www.glassdoor.com/Job/jobs.htm?locT=C&locId=%s&jobType=&context=Jobs&sc.keyword=%s";
    private static final Map<String, String> JOBS_ON_SITE_ID = new TreeMap<>(Map
            .of("Aveiro", "3185896",
                    "Braga", "3227540",
                    "Coimbra", "3177365",
                    "Lisboa", "3192045",
                    "Porto", "3183562"));

    private static final String URL_TEMPLATE_REMOTE
            = "https://www.glassdoor.com/Job/jobs.htm?locT=N&locId=195&jobType=&context=Jobs&remoteWorkType=%s&sc.keyword=%s";
    private static final String JOBS_REMOTE_ID = "1";

    private final GlassdoorPage glassdoorPage;
    private final Map<String, Set<Job>> jobs;

    public JobsGlassdoor() {
        glassdoorPage = new GlassdoorPage();
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
            Set<Job> onSiteJobs = getJobsForLocation(URL_TEMPLATE_ON_SITE, locationId, glassdoorPage);
            jobs.put(locationName, onSiteJobs);
        });
    }

    private void setJobsRemote() {
        Set<Job> remoteJobs = getJobsForLocation(URL_TEMPLATE_REMOTE, JOBS_REMOTE_ID, glassdoorPage);
        jobs.put("Remote", remoteJobs);
    }
}
