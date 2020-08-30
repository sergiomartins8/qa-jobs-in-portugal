package qa.vacancies.portugal.pages;

import qa.vacancies.portugal.utils.constants.Constants;
import qa.vacancies.portugal.utils.model.Vacancy;

import java.util.List;

/**
 * Contract that represents the definition of a page object.
 *
 * @param <T> represents the type of the concrete page object
 */
public interface PageObject<T extends PageObject<T>> {

    /**
     * Opens the browser to facilitate a query search for a given location.
     *
     * @param locationId location identifier
     * @param query      search query
     * @return the current page
     */
    T openAndSearch(String locationId, String query);

    /**
     * Extracts vacancy details from a single webpage to build a {@link Vacancy} list.
     * <br>
     * The list is built by filtering for the desired queries available on {@link Constants#QUERIES}
     *
     * @return list of vacancies.
     */
    List<Vacancy> getVacancies();
}
