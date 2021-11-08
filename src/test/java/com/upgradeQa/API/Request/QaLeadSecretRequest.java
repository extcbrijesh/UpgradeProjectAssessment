package com.upgradeQa.API.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by brijesh on 11/8/21.
 */
@Getter
@Setter
@Data
public class QaLeadSecretRequest {
    String loanAppUUID;
    Boolean skipSideEffects;

    public String getLoanAppUUID() {
        return loanAppUUID;
    }

    public void setLoanAppUUID(String loanAppUUID) {
        this.loanAppUUID = loanAppUUID;
    }

    public Boolean getSkipSideEffects() {
        return skipSideEffects;
    }

    public void setSkipSideEffects(Boolean skipSideEffects) {
        this.skipSideEffects = skipSideEffects;
    }
}
