package qa.vacancies.portugal.vacancies;

import qa.vacancies.portugal.utils.model.Vacancy;

import java.util.Map;
import java.util.Set;

public interface Vacancies {
    Map<String, Set<Vacancy>> getVacancies();
}
