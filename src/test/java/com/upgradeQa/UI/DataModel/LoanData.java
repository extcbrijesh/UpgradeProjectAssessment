package com.upgradeQa.UI.DataModel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by brijesh on 11/4/21.
 */

@Data
@Getter
@Setter
public class LoanData {
    String loanAmount;
    String term;
    String interestRate;
    String apr;
}
