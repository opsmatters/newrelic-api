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

import java.util.List;
import java.util.Collection;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.EntityType;
import com.opsmatters.newrelic.api.model.accounts.Product;
import com.opsmatters.newrelic.api.model.alerts.AlertEvent;
import com.opsmatters.newrelic.api.util.QueryParameterList;

/**
 * The set of operations used for alert events.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertEventService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public AlertEventService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of alert events with the given query parameters.
     * @param queryParams The query parameters
     * @return The set of alert events
     */
    public Collection<AlertEvent> list(List<String> queryParams)
    {
        return HTTP.GET("/v2/alerts_events.json", null, queryParams, ALERT_EVENTS).get();
    }

    /**
     * Returns the set of alert events.
     * @return The set of alert events
     */
    public Collection<AlertEvent> list()
    {
        return list(null);
    }

    /**
     * Returns a builder for the event operation filters.
     * @return The builder instance.
     */
    public static FilterBuilder filters()
    {
        return new FilterBuilder();
    }

    /**
     * Builder to make filter construction easier.
     */
    public static class FilterBuilder
    {
        private QueryParameterList filters = new QueryParameterList();

        /**
         * Adds the product filter to the filters.
         * @param product The product to filter on
         * @return This object
         */
        public FilterBuilder product(String product)
        {
            filters.add("filter[product]", product);
            return this;
        }

        /**
         * Adds the product filter to the filters.
         * @param product The product to filter on
         * @return This object
         */
        public FilterBuilder product(Product product)
        {
            return product(product.name());
        }

        /**
         * Adds the entity type filter to the filters.
         * @param entityType The entity type to filter on
         * @return This object
         */
        public FilterBuilder entityType(String entityType)
        {
            filters.add("filter[entity_type]", entityType);
            return this;
        }

        /**
         * Adds the entity type filter to the filters.
         * @param entityType The entity type to filter on
         * @return This object
         */
        public FilterBuilder entityType(EntityType entityType)
        {
            return entityType(entityType.value());
        }

        /**
         * Adds the entity id filter to the filters.
         * @param entityId The entity id to filter on
         * @return This object
         */
        public FilterBuilder entityId(long entityId)
        {
            filters.add("filter[entity_id]", entityId);
            return this;
        }

        /**
         * Adds the entity group id filter to the filters.
         * @param entityGroupId The entity group id to filter on
         * @return This object
         */
        public FilterBuilder entityGroupId(long entityGroupId)
        {
            filters.add("filter[entity_group_id]", entityGroupId);
            return this;
        }

        /**
         * Adds the event type filter to the filters.
         * @param eventType The event type to filter on
         * @return This object
         */
        public FilterBuilder eventType(String eventType)
        {
            filters.add("filter[event_type]", eventType);
            return this;
        }

        /**
         * Adds the event type filter to the filters.
         * @param eventType The event type to filter on
         * @return This object
         */
        public FilterBuilder eventType(AlertEvent.EventType eventType)
        {
            return eventType(eventType.name());
        }

        /**
         * Adds the incident id filter to the filters.
         * @param incidentId The incident id to filter on
         * @return This object
         */
        public FilterBuilder incidentId(long incidentId)
        {
            filters.add("filter[incident_id]", incidentId);
            return this;
        }

        /**
         * Adds the page filter to the filters.
         * @param page The page to filter on
         * @return This object
         */
        public FilterBuilder page(int page)
        {
            if(page >= 0)
                filters.add("page", page);
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
