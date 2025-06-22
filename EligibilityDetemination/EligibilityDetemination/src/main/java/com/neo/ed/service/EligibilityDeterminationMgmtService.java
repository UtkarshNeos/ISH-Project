package com.neo.ed.service;

import com.neo.ed.binding.EligibilityDetailsOutput;

public interface EligibilityDeterminationMgmtService {
    public EligibilityDetailsOutput determineEligibility(int caseNo);
}
