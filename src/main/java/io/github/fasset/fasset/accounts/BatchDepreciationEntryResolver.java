/**
 * 
 */
package io.github.fasset.fasset.accounts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableSet;

import io.github.fasset.fasset.book.keeper.AccountingEntry;
import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import io.github.fasset.fasset.model.FixedAsset;

/**
 * This resolver generates accounting entries relates to depreciation of a fixed asset where we generally credit the
 * contra account which is the accumulated depreciation account, and credit the respective depreciation account.
 * 
 * @author edwin.njeru
 *
 */
@Component("batchDepreciationEntryResolver")
public class BatchDepreciationEntryResolver implements BatchEntryResolver {

	private AccountResolver accountResolver;
	
	@Autowired
	public BatchDepreciationEntryResolver(@Qualifier("depreciationAccountResolver") AccountResolver accountResolver) {
		this.accountResolver = accountResolver;
	}

	/**
	 * Generates {@code AccountingEntry} items based on {@code FixedAssets} items passed in the parameter.
	 * The method will generate both {@code DEBIT} and {@code CREDIT} side entries and will abstract from
	 * client the logic of obtaining depreciation rates and values from configurations in the application
	 * @param fixedAssets Items to be depreciated
	 * @return {@code AccountingEntries} to post depreciation
	 */
	public List<AccountingEntry> resolveEntries(List<FixedAsset> fixedAssets){

		List<AccountingEntry> entries = 
				fixedAssets.stream()
				.flatMap(asset -> getEntrySet(asset).stream())
				.collect(ImmutableListCollector.toImmutableFastList());

		return entries;
	}
	
	private ImmutableSet<AccountingEntry> getEntrySet(FixedAsset asset){
		
		return ImmutableSet.of(debitEntry(asset), creditEntry(asset));
	}

	private AccountingEntry debitEntry(FixedAsset asset) {
		
		AccountingEntry debit = 
				new AccountingEntry();
		
		return debit;
	}
	
	private AccountingEntry creditEntry(FixedAsset asset) {
		AccountingEntry credit = 
				new AccountingEntry();
		return credit;
	}

}
