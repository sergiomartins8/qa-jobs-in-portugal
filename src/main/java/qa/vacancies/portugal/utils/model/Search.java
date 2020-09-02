package qa.vacancies.portugal.utils.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class Search {
    private String locationId;
    private String urlTemplate;
}
