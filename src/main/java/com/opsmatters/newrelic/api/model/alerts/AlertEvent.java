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

package com.opsmatters.newrelic.api.model.alerts;

import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.IdResource;
import com.opsmatters.newrelic.api.model.EntityType;
import com.opsmatters.newrelic.api.model.accounts.Product;

/**
 * Represents a New Relic alert event.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertEvent extends IdResource
{
    @SerializedName("event_type")
    private String eventType;

    private String product;

    @SerializedName("entity_id")
    private Long entityId;

    @SerializedName("entity_type")
    private String entityType;

    @SerializedName("entity_group_id")
    private Long entityGroupId;

    private String priority;

    private String description;

    private Long timestamp;

    @SerializedName("incident_id")
    private Long incidentId;

    /**
     * Represents the types of alert event.  
     */
    public enum EventType
    {
        NOTIFICATION, 
        DEPLOYMENT, 
        VIOLATION_OPEN, 
        VIOLATION_CLOSE, 
        VIOLATION,
        INSTRUMENTATION;
    }

    /**
     * Default constructor.
     */
    public AlertEvent()
    {
    }

    /**
     * Sets the event type of the event.
     * @param eventType The event type of the event
     */
    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    /**
     * Sets the event type of the event.
     * @param eventType The event type of the event
     */
    public void setEventType(EventType eventType)
    {
        setEventType(eventType.name());
    }

    /**
     * Returns the event type of the event.
     * @return The event type of the event
     */
    public String getEventType()
    {
        return eventType;
    }

    /**
     * Sets the product of the event.
     * @param product The product of the event
     */
    public void setProduct(String product)
    {
        this.product = product;
    }

    /**
     * Sets the product of the event.
     * @param product The product of the event
     */
    public void setProduct(Product product)
    {
        setProduct(product.name());
    }

    /**
     * Returns the product of the event.
     * @return The product of the event
     */
    public String getProduct()
    {
        return product;
    }

    /**
     * Sets the entity id of the event.
     * @param entityId The entity id of the event
     */
    public void setEntityId(long entityId)
    {
        this.entityId = entityId;
    }

    /**
     * Returns the entity id of the event.
     * @return The entity id of the event
     */
    public long getEntityId()
    {
        return entityId;
    }

    /**
     * Sets the entity type of the event.
     * @param entityType The entity type of the event
     */
    public void setEntityType(String entityType)
    {
        this.entityType = entityType;
    }

    /**
     * Sets the entity type of the event.
     * @param entityType The entity type of the event
     */
    public void setEntityType(EntityType entityType)
    {
        setEntityType(entityType.value());
    }

    /**
     * Returns the entity type of the event.
     * @return The entity type of the event
     */
    public String getEntityType()
    {
        return entityType;
    }

    /**
     * Sets the entity group id of the event.
     * @param entityGroupId The entity group id of the event
     */
    public void setEntityGroupId(long entityGroupId)
    {
        this.entityGroupId = entityGroupId;
    }

    /**
     * Returns the entity group id of the event.
     * @return The entity group id of the event
     */
    public long getEntityGroupId()
    {
        return entityGroupId;
    }

    /**
     * Sets the priority of the event.
     * @param priority The priority of the event
     */
    public void setPriority(String priority)
    {
        this.priority = priority;
    }

    /**
     * Sets the priority of the event.
     * @param priority The priority of the event
     */
    public void setPriority(Priority priority)
    {
        setPriority(priority.value());
    }

    /**
     * Returns the priority of the event.
     * @return The priority of the event
     */
    public String getPriority()
    {
        return priority;
    }

    /**
     * Sets the description of the event.
     * @param description The description of the event
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Returns the description of the event.
     * @return The description of the event
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets the timestamp of the event.
     * @param timestamp The timestamp of the event
     */
    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

    /**
     * Returns the timestamp of the event.
     * @return The timestamp of the event
     */
    public long getTimestamp()
    {
        return timestamp;
    }

    /**
     * Sets the incident id of the event.
     * @param incidentId The incident id of the event
     */
    public void setIncidentId(long incidentId)
    {
        this.incidentId = incidentId;
    }

    /**
     * Returns the incident id of the event.
     * @return The incident id of the event
     */
    public long getIncidentId()
    {
        return incidentId;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "AlertEvent ["+super.toString()
            +", eventType="+eventType
            +", product="+product
            +", entityId="+entityId
            +", entityType="+entityType
            +", entityGroupId="+entityGroupId
            +", priority="+priority
            +", description="+description
            +", timestamp="+timestamp
            +", incidentId="+incidentId
            +"]";
    }
}