package io.github.fasset.fasset.accounts.definition;

/**
 * The account is not just an arbitrary assignment but a representation of its various features. Each
 * part of the account number string is generated in unique process logic and this Enum represents the
 * identity of each part
 */
public enum AccountNumberSegment {

    /**
     * Defines a group of closely related accounts
     */
    GENERAL_LEDGER_CODE {
        /**
         * Returns the name of this enum constant, as contained in the
         * declaration.  This method may be overridden, though it typically
         * isn't necessary or desirable.  An enum type should override this
         * method when a more "programmer-friendly" string form exists.
         *
         * @return the name of this enum constant
         */
        @Override
        public String toString() {
            return "general-ledger-code";
        }
    },

    /**
     * Unique to the accounts in a given general ledger
     */
    PLACE_HOLDER {
        /**
         * Returns the name of this enum constant, as contained in the
         * declaration.  This method may be overridden, though it typically
         * isn't necessary or desirable.  An enum type should override this
         * method when a more "programmer-friendly" string form exists.
         *
         * @return the name of this enum constant
         */
        @Override
        public String toString() {
            return "place-holder";
        }
    },
}
