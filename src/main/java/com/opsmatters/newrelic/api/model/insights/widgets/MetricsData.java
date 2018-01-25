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

package com.opsmatters.newrelic.api.model.insights.widgets;

import java.util.List;
import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.metrics.Metric;
/**
 * Represents a New Relic Insights dashboard metric data source.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class MetricsData extends WidgetData
{
    private Integer duration;

    @SerializedName("end_time")
    private Long endTime;

    @SerializedName("entity_ids")
    private List<Long> entityIds;

    private List<Metric> metrics;

    @SerializedName("order_by")
    private String orderBy;

    private Integer limit;

    /**
     * Default constructor.
     */
    public MetricsData()
    {
    }

    /**
     * Sets the duration of the metrics.
     * @param duration The duration of the metrics
     */
    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    /**
     * Returns the duration of the metrics.
     * @return The duration of the metrics
     */
    public int getDuration()
    {
        return duration;
    }

    /**
     * Sets the end time of the metrics.
     * @param endTime The end time of the metrics
     */
    public void setEndTime(long endTime)
    {
        this.endTime = endTime;
    }

    /**
     * Returns the end time of the metrics.
     * @return The end time of the metrics
     */
    public long getEndTime()
    {
        return endTime;
    }

    /**
     * Sets the list of entities for the widget.
     * @param entityIds The list of entities for the widget
     */
    public void setEntityIds(List<Long> entityIds)
    {
        this.entityIds = entityIds;
    }

    /**
     * Adds the given entity id to the list of entities for the widget.
     * @param entityId The entity id to add to the list of entities
     */
    public void addEntityId(long entityId)
    {
        if(this.entityIds == null)
            this.entityIds = new ArrayList<Long>();
        this.entityIds.add(entityId);
    }

    /**
     * Returns the list of entities for the widget.
     * @return The list of entities for the widget
     */
    public List<Long> getEntityIds()
    {
        return entityIds;
    }

    /**
     * Sets the list of metrics for the widget.
     * @param metrics The list of metrics for the widget
     */
    public void setMetrics(List<Metric> metrics)
    {
        this.metrics = metrics;
    }

    /**
     * Adds the given metric to the list of metrics for the widget.
     * @param metric The metric to add to the list of metrics
     */
    public void addMetric(Metric metric)
    {
        if(this.metrics == null)
            this.metrics = new ArrayList<Metric>();
        this.metrics.add(metric);
    }

    /**
     * Returns the list of metrics for the widget.
     * @return The list of metrics for the widget
     */
    public List<Metric> getMetrics()
    {
        return metrics;
    }

    /**
     * Sets the order by of the metrics.
     * @param orderBy The order by of the metrics
     */
    public void setOrderBy(String orderBy)
    {
        this.orderBy = orderBy;
    }

    /**
     * Sets the order by of the metrics.
     * @param orderBy The order by of the metrics
     */
    public void setOrderBy(Metric.Value orderBy)
    {
        setOrderBy(orderBy.value());
    }

    /**
     * Returns the order by of the metrics.
     * @return The order by of the metrics
     */
    public String getOrderBy()
    {
        return orderBy;
    }

    /**
     * Sets the limit of the metrics.
     * @param limit The limit of the metrics
     */
    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    /**
     * Returns the limit of the metrics.
     * @return The limit of the metrics
     */
    public int getLimit()
    {
        return limit;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "MetricsData [duration="+duration
            +", endTime="+endTime
            +", entityIds="+entityIds
            +", metrics="+metrics
            +", orderBy="+orderBy
            +", limit="+limit
            +"]";
    }

    /**
     * Returns a builder for the widget data.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make widget data construction easier.
     */
    public static class Builder
    {
        private MetricsData data = new MetricsData();

        /**
         * Sets the duration of the widget data.
         * @param duration The duration of the widget data
         * @return This object
         */
        public Builder duration(int duration)
        {
            data.setDuration(duration);
            return this;
        }

        /**
         * Sets the end time of the widget data.
         * @param endTime The end time of the widget data
         * @return This object
         */
        public Builder endTime(long endTime)
        {
            data.setEndTime(endTime);
            return this;
        }

        /**
         * Sets the entity ids of the widget data.
         * @param entityIds The entity ids of the widget data
         * @return This object
         */
        public Builder entityIds(List<Long> entityIds)
        {
            data.setEntityIds(entityIds);
            return this;
        }

        /**
         * Adds the given entity id to the list of entities for the widget.
         * @param entityId The entity id to add to the list of entities
         * @return This object
         */
        public Builder addEntityId(long entityId)
        {
            data.addEntityId(entityId);
            return this;
        }

        /**
         * Sets the metrics of the widget data.
         * @param metrics The metrics of the widget data
         * @return This object
         */
        public Builder metrics(List<Metric> metrics)
        {
            data.setMetrics(metrics);
            return this;
        }

        /**
         * Adds the given metric to the list of metrics for the widget.
         * @param metric The metric to add to the list of metrics
         * @return This object
         */
        public Builder addMetric(Metric metric)
        {
            data.addMetric(metric);
            return this;
        }

        /**
         * Sets the order by of the widget data.
         * @param orderBy The order by of the widget data
         * @return This object
         */
        public Builder orderBy(String orderBy)
        {
            data.setOrderBy(orderBy);
            return this;
        }

        /**
         * Sets the order by of the widget data.
         * @param orderBy The order by of the widget data
         * @return This object
         */
        public Builder orderBy(Metric.Value orderBy)
        {
            data.setOrderBy(orderBy);
            return this;
        }

        /**
         * Sets the limit of the widget data.
         * @param limit The limit of the widget data
         * @return This object
         */
        public Builder limit(int limit)
        {
            data.setLimit(limit);
            return this;
        }

        /**
         * Returns the configured widget data instance
         * @return The widget data instance
         */
        public MetricsData build()
        {
            return data;
        }
    }
}