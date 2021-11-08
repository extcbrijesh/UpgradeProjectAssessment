package com.upgradeQa.API.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author by brijesh on 11/8/21.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanAppResumptionInfo {
    String loanAppId;
    UUID loanAppUuid;
    String status;
    @JsonProperty(value = "productType")
    String productType;
    String sourceSystem;
    Double desiredAmount;
    String coBorrowerResumptionInfo;
    Boolean turnDown;
    Boolean hasLogin;
    String availableAppImprovements;
    Double cashOutAmount;
    Boolean canAddCollateral;
    Integer rewardProgramId;
    String rewardProgramCode;
    String addon;
    Boolean isMobileDiscountApplied;
    Boolean checkingDiscountAvailable;
}
