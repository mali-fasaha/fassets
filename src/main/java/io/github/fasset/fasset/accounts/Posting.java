package io.github.fasset.fasset.accounts;

public enum Posting {

    /**
     * When a transaction is posted on the DEBIT side of an account
     */
    DEBIT {
        @Override
        public String toString() {
            return "DEBIT";
        }
    },

    /**
     * When a transaction is posted on the Credit side of the account
     */
    CREDIT {
        @Override
        public String toString() {
            return "CREDIT";
        }
    },
}
