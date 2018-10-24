/*
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.kernel.book.keeper.service;

import io.github.fasset.fasset.kernel.book.keeper.Account;
import io.github.fasset.fasset.kernel.book.keeper.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * <p>AccountServiceImpl class.</p>
 *
 * @author edwin.njeru
 * @version $Id: $Id
 * @see AccountService interface
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    private AccountRepository repository;

    /**
     * <p>Constructor for AccountServiceImpl.</p>
     *
     * @param repository a {@link io.github.fasset.fasset.kernel.book.keeper.repo.AccountRepository} object.
     */
    @Autowired
    public AccountServiceImpl(@Qualifier("accountRepository") AccountRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Saves the item passed to the parameter
     */
    @Override
    public Account saveAccount(Account persistentAccount) {

        return repository.save(persistentAccount);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Saves items in a  {@code Collection} of {@code PersistentAccount} items to the repository
     */
    @Override
    public void saveAccounts(Collection<Account> persistentAccounts) {

        this.repository.saveAll(persistentAccounts);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the Account instance whose nomenclature is given in the parameter
     */
    @Override
    public Account fetchAccountById(int accountId) {

        return repository.getOne(accountId);
    }
}
