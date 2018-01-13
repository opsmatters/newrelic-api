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

package com.opsmatters.newrelic.api;

import java.util.Collection;
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.model.conditions.AlertCondition;
import com.opsmatters.newrelic.api.model.entities.Entity;
import com.opsmatters.newrelic.util.QueryParameterList;

/**
 * The set of operations used for alert entity conditions.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertEntityConditionOperations extends BaseFluent
{
    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param apiService The set of API operations
     */
    public AlertEntityConditionOperations(HttpContext httpContext, NewRelicApiService apiService)
    {
        super(httpContext, apiService);
    }

    /**
     * Returns the alert conditions for the given entity id.
     * @param entityId The id of the entity
     * @param entityType The type of the entity
     * @return The alert conditions for the entity
     */
    public Collection<AlertCondition> list(long entityId, String entityType)
    {
        QueryParameterList queryParams = new QueryParameterList();
        queryParams.add("entity_type", entityType);
        return HTTP.GET(String.format("/alerts_entity_conditions/%d.json", entityId), null, queryParams, ALERT_CONDITIONS).get();
    }

    /**
     * Returns the alert conditions for the given entity.
     * @param entity The entity to look up
     * @return The alert conditions for the entity
     */
    public Collection<AlertCondition> list(Entity entity)
    {
        return list(entity.getId(), entity.getType());
    }

    /**
     * Adds the given entity to the alert condition with the given id.
     * @param entityId The id of the entity to add
     * @param entityType The type of the entity to add
     * @param conditionId The id of the alert condition to add the entity to
     * @return The alert condition that was updated
     */
    public Optional<AlertCondition> add(long entityId, String entityType, long conditionId)
    {
        QueryParameterList queryParams = new QueryParameterList();
        queryParams.add("entity_type", entityType);
        queryParams.add("condition_id", conditionId);
        return HTTP.PUT(String.format("/alerts_entity_conditions/%d.json", entityId), null, null, queryParams, ALERT_CONDITION);
    }

    /**
     * Adds the given entity to the alert condition with the given id.
     * @param entity The entity to add
     * @param conditionId The id of the alert condition to add the entity to
     * @return The alert condition that was updated
     */
    public Optional<AlertCondition> add(Entity entity, long conditionId)
    {
        return add(entity.getId(), entity.getType(), conditionId);
    }

    /**
     * Removes the given entity from the alert condition with the given id.
     * @param entityId The id of the entity to remove
     * @param entityType The type of the entity to remove
     * @param conditionId The id of the alert condition from which to delete the entity
     * @return This object
     */
    public AlertEntityConditionOperations remove(long entityId, String entityType, long conditionId)
    {
        QueryParameterList queryParams = new QueryParameterList();
        queryParams.add("entity_type", entityType);
        queryParams.add("condition_id", conditionId);
        HTTP.DELETE(String.format("/alerts_entity_conditions/%d.json", entityId), null, queryParams);       
        return this;
    }

    /**
     * Removes the given entity from the alert condition with the given id.
     * @param entity The entity to remove
     * @param conditionId The id of the alert condition from which to delete the entity
     * @return This object
     */
    public AlertEntityConditionOperations remove(Entity entity, long conditionId)
    {
        return remove(entity.getId(), entity.getType(), conditionId);
    }
}
