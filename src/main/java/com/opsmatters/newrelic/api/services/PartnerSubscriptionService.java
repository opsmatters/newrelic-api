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
import java.util.List;
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.accounts.PartnerSubscription;
import com.opsmatters.newrelic.api.model.accounts.ProductSubscription;

/**
 * The set of operations used for partner subscriptions.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PartnerSubscriptionService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public PartnerSubscriptionService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of subscriptions.
     * @param partnerId The id of the partner for the subscriptions
     * @param accountId The id of the account for the subscriptions
     * @return The set of subscriptions
     */
    public Collection<PartnerSubscription> list(long partnerId, long accountId)
    {
        return HTTP.GET(String.format("/v2/partners/%d/accounts/%d/subscriptions", partnerId, accountId), PARTNER_SUBSCRIPTIONS).get();
    }

    /**
     * Returns the subscription with the given id.
     * @param partnerId The id of the partner the subscription belongs to
     * @param accountId The id of the account for the subscription
     * @param subscriptionId The id of the subscription to return
     * @return The subscription
     */
    public Optional<PartnerSubscription> show(long partnerId, long accountId, long subscriptionId)
    {
        return HTTP.GET(String.format("/v2/partners/%d/accounts/%d/subscriptions/%d", partnerId, accountId, subscriptionId), PARTNER_SUBSCRIPTION);
    }

    /**
     * Raplaces the subscriptions on the account with the given subscriptions.
     * @param partnerId The id of the partner the subscriptions belongs to
     * @param accountId The id of the account for the subscriptions
     * @param subscriptions The subscriptions to create
     * @return The subscription that was created
     */
    public Optional<PartnerSubscription> create(long partnerId, long accountId, List<ProductSubscription> subscriptions)
    {
        return HTTP.POST(String.format("/v2/partners/%d/accounts/%d/subscriptions", partnerId, accountId), subscriptions, PARTNER_SUBSCRIPTION);
    }
}
