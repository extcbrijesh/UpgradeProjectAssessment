package com.upgradeQa.API.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author by brijesh on 11/8/21.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class QaBrFunnelLeadSecretResponse {
    LoanAppResumptionInfo loanAppResumptionInfo;
    List offers;
    String selectedOffer;
    List requiredAgreements;
    List<String> resetOptions;
    String originalLoanApp;
}
