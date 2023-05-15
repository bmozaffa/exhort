package com.redhat.ecosystemappeng.crda.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record CvssVector(
        String attackVector,
        String attackComplexity,
        String privilegesRequired,
        String userInteraction,
        String scope,
        String confidentialityImpact,
        String integrityImpact,
        String availabilityImpact,
        String exploitCodeMaturity,
        String remediationLevel,
        String reportConfidence,
        String cvss) {

    public static class Builder {
        String attackVector;
        String attackComplexity;
        String privilegesRequired;
        String userInteraction;
        String scope;
        String confidentialityImpact;
        String integrityImpact;
        String availabilityImpact;
        String exploitCodeMaturity;
        String remediationLevel;
        String reportConfidence;
        String cvss;

        public Builder attackVector(String attackVector) {
            this.attackVector = attackVector;
            return this;
        }

        public Builder attackComplexity(String attackComplexity) {
            this.attackComplexity = attackComplexity;
            return this;
        }

        public Builder privilegesRequired(String privilegesRequired) {
            this.privilegesRequired = privilegesRequired;
            return this;
        }

        public Builder userInteraction(String userInteraction) {
            this.userInteraction = userInteraction;
            return this;
        }

        public Builder scope(String scope) {
            this.scope = scope;
            return this;
        }

        public Builder confidentialityImpact(String confidentialityImpact) {
            this.confidentialityImpact = confidentialityImpact;
            return this;
        }

        public Builder integrityImpact(String integrityImpact) {
            this.integrityImpact = integrityImpact;
            return this;
        }

        public Builder availabilityImpact(String availabilityImpact) {
            this.availabilityImpact = availabilityImpact;
            return this;
        }

        public Builder exploitCodeMaturity(String exploitCodeMaturity) {
            this.exploitCodeMaturity = exploitCodeMaturity;
            return this;
        }

        public Builder remediationLevel(String remediationLevel) {
            this.remediationLevel = remediationLevel;
            return this;
        }

        public Builder reportConfidence(String reportConfidence) {
            this.reportConfidence = reportConfidence;
            return this;
        }

        public Builder cvss(String cvss) {
            this.cvss = cvss;
            return this;
        }

        public CvssVector build() {
            return new CvssVector(attackVector, attackComplexity, privilegesRequired,
                    userInteraction, scope, confidentialityImpact, integrityImpact,
                    availabilityImpact, exploitCodeMaturity, remediationLevel, reportConfidence,
                    cvss);
        }
    }

}