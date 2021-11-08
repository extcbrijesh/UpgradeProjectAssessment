package com.upgradeQa.API.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by brijesh on 11/8/21.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class QaBorrowerResumptionInfo  {
    String firstName;
    String maskedEmail;
    Boolean ssnRequired;
    String state;
    String email;
}
