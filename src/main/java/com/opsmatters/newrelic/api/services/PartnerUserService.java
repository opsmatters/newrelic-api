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
import com.opsmatters.newrelic.api.model.accounts.PartnerUser;

/**
 * The set of operations used for partner users.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PartnerUserService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public PartnerUserService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of users.
     * @param partnerId The id of the partner for the users
     * @param accountId The id of the account for the users
     * @return The set of users
     */
    public Collection<PartnerUser> list(long partnerId, long accountId)
    {
        return HTTP.GET(String.format("/v2/partners/%d/accounts/%d/users", partnerId, accountId), PARTNER_USERS).get();
    }

    /**
     * Creates the given user.
     * @param partnerId The id of the partner the user belongs to
     * @param accountId The id of the account for the user
     * @param user The user to create
     * @return The user that was created
     */
    public Optional<PartnerUser> create(long partnerId, long accountId, PartnerUser user)
    {
        return Optional.of(HTTP.POST(String.format("/v2/partners/%d/accounts/%d/users", partnerId, accountId), user, PARTNER_USERS).get().iterator().next());
    }

    /**
     * Updates the given user.
     * @param partnerId The id of the partner the user belongs to
     * @param accountId The id of the account for the user
     * @param user The user to update
     * @return The user that was updated
     */
    public Optional<PartnerUser> update(long partnerId, long accountId, PartnerUser user)
    {
        return Optional.of(HTTP.PUT(String.format("/v2/partners/%d/accounts/%d/users", partnerId, accountId), user, PARTNER_USERS).get().iterator().next());
    }

    /**
     * Deletes the user with the given id.
     * @param partnerId The id of the partner the user belongs to
     * @param accountId The id of the account for the user
     * @param userId The id of the user to delete
     * @return This object
     */
    public PartnerUserService delete(long partnerId, long accountId, long userId)
    {
        HTTP.DELETE(String.format("/v2/partners/%d/accounts/%d/users/%d", partnerId, accountId, userId));       
        return this;
    }
}
