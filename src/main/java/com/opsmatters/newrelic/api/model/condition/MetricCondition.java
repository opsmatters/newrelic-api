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

package com.opsmatters.newrelic.api.model.condition;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a New Relic metric condition.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public abstract class MetricCondition extends TermsCondition
{
    private String metric;
    private List<Long> entities = new ArrayList<Long>();

    /**
     * Default constructor.
     */
    public MetricCondition()
    {
    }
    
    /**
     * Sets the metric of the alert condition.
     * @param metric The metric of the alert condition
     */
    public void setMetric(String metric)
    {
        this.metric = metric;
    }

    /**
     * Returns the metric of the alert condition.
     * @return The metric of the alert condition
     */
    public String getMetric()
    {
        return metric;
    }

    /**
     * Sets the list of entities for the condition.
     * @param entities The list of entities
     */
    public void setEntities(List<Long> entities)
    {
        this.entities = entities;
    }

    /**
     * Adds an entity to the alert condition.
     * @param entity The entity to be added
     */
    public void addEntity(long entity)
    {
        this.entities.add(entity);
    }

    /**
     * Returns the list of entities for the condition.
     * @return The list of entities
     */
    public List<Long> getEntities()
    {
        return entities;
    }

    /**
     * Returns the array of entities for the condition.
     * @return The array of entities
     */
    public long[] getEntitiesArray()
    {
        long[] ret = new long[entities.size()];
        for(int i = 0; i < entities.size(); i++)
            ret[i] = entities.get(i);
        return ret;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return super.toString()
            +", metric="+metric
            +", entities="+entities;
    }
}