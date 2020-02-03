package swiftaproject.swifta.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Kwerenachi Utosu
 * @date 2/2/2020
 */
@Getter
@Setter
public class Response {

    private Boolean status;
    private String message;
    private Object respBody;
}
