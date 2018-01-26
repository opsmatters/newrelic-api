/*
 * Copyright 2018 Gerald Curley
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.opsmatters.newrelic.api.services;

import java.util.Collection;
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.accounts.PartnerAccount;

/**
 * The set of operations used for partner accounts.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PartnerAccountService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public PartnerAccountService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of accounts.
     * @param partnerId The id of the partner for the accounts
     * @return The set of accounts
     */
    public Collection<PartnerAccount> list(long partnerId)
    {
        return HTTP.GET(String.format("/v2/partners/%d/accounts", partnerId), PARTNER_ACCOUNTS).get();
    }

    /**
     * Returns the account with the given id.
     * @param partnerId The id of the partner the account belongs to
     * @param accountId The id of the account to return
     * @return The account
     */
    public Optional<PartnerAccount> show(long partnerId, long accountId)
    {
        return HTTP.GET(String.format("/v2/partners/%d/accounts/%d", partnerId, accountId), PARTNER_ACCOUNT);
    }
    
    /**
     * Creates the given account.
     * @param partnerId The id of the partner the account belongs to
     * @param account The account to create
     * @return The account that was created
     */
    public Optional<PartnerAccount> create(long partnerId, PartnerAccount account)
    {
        return HTTP.POST(String.format("/v2/partners/%d/accounts", partnerId), account, PARTNER_ACCOUNT);
    }

    /**
     * Updates the given account.
     * @param partnerId The id of the partner the account belongs to
     * @param account The account to update
     * @return The account that was updated
     */
    public Optional<PartnerAccount> update(long partnerId, PartnerAccount account)
    {
        return HTTP.PUT(String.format("/v2/partners/%d/accounts/%d", partnerId, account.getId()), account, PARTNER_ACCOUNT);
    }

    /**
     * Deletes the account with the given id.
     * @param partnerId The id of the partner the account belongs to
     * @param accountId The id of the account to delete
     * @return This object
     */
    public PartnerAccountService delete(long partnerId, long accountId)
    {
        HTTP.DELETE(String.format("/v2/partners/%d/accounts/%d", partnerId, accountId));       
        return this;
    }
}
