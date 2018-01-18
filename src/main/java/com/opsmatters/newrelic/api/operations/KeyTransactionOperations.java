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

package com.opsmatters.newrelic.api.operations;

import java.util.Collection;
import java.util.List;
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.services.NewRelicService;
import com.opsmatters.newrelic.api.model.transactions.KeyTransaction;
import com.opsmatters.newrelic.util.QueryParameterList;

/**
 * The set of operations used for key transactions.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class KeyTransactionOperations extends BaseFluent
{
    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param service The set of API operations
     */
    public KeyTransactionOperations(HttpContext httpContext, NewRelicService service)
    {
        super(httpContext, service);
    }

    /**
     * Returns the set of key transactions with the given query parameters.
     * @param queryParams The query parameters
     * @return The set of key transactions
     */
    public Collection<KeyTransaction> list(List<String> queryParams)
    {
        return HTTP.GET("/v2/key_transactions.json", null, queryParams, KEY_TRANSACTIONS).get();
    }

    /**
     * Returns the set of key transactions.
     * @return The set of key transactions
     */
    public Collection<KeyTransaction> list()
    {
        return list(null);
    }

    /**
     * Returns the key transaction for the given transaction id.
     * @param transactionId The id for the key transaction to return
     * @return The key transaction
     */
    public Optional<KeyTransaction> show(long transactionId)
    {
        return HTTP.GET(String.format("/v2/key_transactions/%d.json", transactionId), KEY_TRANSACTION);
    }

    /**
     * Returns a builder for the key transaction filters.
     * @return The builder instance.
     */
    public static FilterBuilder filters()
    {
        return new FilterBuilder();
    }

    /**
     * Builder to make key transaction filter construction easier.
     */
    public static class FilterBuilder
    {
        private QueryParameterList filters = new QueryParameterList();

        /**
         * Adds the name filter to the filters.
         * @param name The name to filter on
         * @return This object
         */
        public FilterBuilder name(String name)
        {
            filters.add("filter[name]", name);
            return this;
        }

        /**
         * Adds the id filter to the filters.
         * @param ids The comma-separated list of ids to filter on
         * @return This object
         */
        public FilterBuilder ids(String ids)
        {
            filters.add("filter[ids]", ids);
            return this;
        }

        /**
         * Returns the configured filters
         * @return The filters
         */
        public List<String> build()
        {
            return filters;
        }
    }
}
