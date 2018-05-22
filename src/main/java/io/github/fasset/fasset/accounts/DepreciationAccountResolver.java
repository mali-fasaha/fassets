package io.github.fasset.fasset.accounts;

import org.springframework.stereotype.Component;

import io.github.fasset.fasset.book.keeper.Account;
import io.github.fasset.fasset.model.FixedAsset;

/**
 * This resolver generates the appropriate accounts required to post depreciation {@code DEBIT} and {@code CREDIT} items
 * as would be typical where the fixed asset is maintained at cost. Although this could be configured to maintain the asset
 * in the register at revaluation.
 * 
 * @author edwin.njeru
 *
 */
@Component("depreciationAccountResolver")
public class DepreciationAccountResolver implements AccountResolver {

	/* (non-Javadoc)
	 * @see io.github.fasset.fasset.accounts.AccountResolver#resolveDebitAccount(io.github.fasset.fasset.model.FixedAsset)
	 */
	@Override
	public Account resolveDebitAccount(FixedAsset fixedAsset) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see io.github.fasset.fasset.accounts.AccountResolver#resolveCreditAccount(io.github.fasset.fasset.model.FixedAsset)
	 */
	@Override
	public Account resolveCreditAccount(FixedAsset fixedAsset) {
		// TODO Auto-generated method stub
		return null;
	}

}
