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

package com.opsmatters.newrelic.api.model.metrics;

import java.util.List;
import java.util.ArrayList;
import com.opsmatters.newrelic.api.model.NamedResource;

/**
 * Represents a New Relic metric with a set of values.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Metric implements NamedResource
{
    private String name;
    private String units;
    private String scope;
    private List<String> values = new ArrayList<String>();

    /**
     * Represents the available categories for metrics.  
     */
    public enum Category
    {
        APDEX("Apdex"),
        AGENT("Agent"),
        AGENT_CHECK("AgentCheck"),
        ERRORS("Errors"),
        FILTER("Filter"),
        HTTP_DISPATCHER("HttpDispatcher"),
        JAVA("Java"),
        JMX_BUILTIN("JmxBuiltin"),
        OTHER_TRANSACTION("OtherTransaction"),
        THREADS("Threads"),
        WEB_TRANSACTION("WebTransaction"),
        WEB_TRANSACTION_TOTAL_TIME("WebTransactionTotalTime");

        Category(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        private String value;
    }

    /**
     * Interface for all metric value enums.  
     */
    public interface Value
    {
        public String value();
    }

    /**
     * Represents the available values for Apdex metrics.  
     */
    public enum Apdex implements Value
    {
        SCORE("score"),
        S("s"),
        T("t"),
        F("f"),
        COUNT("count");

        Apdex(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        private String value;
    }

    /**
     * Represents the available values for Errors metrics.  
     */
    public enum Errors implements Value
    {
        ERRORS_PER_MINUTE("errors_per_minute"),
        ERROR_COUNT("error_count");

        Errors(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        private String value;
    }

    /**
     * Represents the available values for JmxBuiltin metrics.  
     */
    public enum JmxBuiltin implements Value
    {
        AVERAGE_VALUE("average_value"),
        TOTAL_VALUE("total_value"),
        MIN_VALUE("min_value"),
        MAX_VALUE("max_value"),
        STANDARD_DEVIATION("standard_deviation"),
        COUNT("count"),
        THROUGHPUT("throughput");

        JmxBuiltin(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        private String value;
    }

    /**
     * Represents the available values for Agent metrics.  
     */
    public enum Agent implements Value
    {
        AVERAGE_RESPONSE_TIME("average_response_time"),
        CALLS_PER_MINUTE("calls_per_minute"),
        CALL_COUNT("call_count"),
        MIN_RESPONSE_TIME("min_response_time"),
        MAX_RESPONSE_TIME("max_response_time"),
        AVERAGE_EXCLUSIVE_TIME("average_exclusive_time"),
        AVERAGE_VALUE("average_value"),
        TOTAL_CALL_TIME_PER_MINUTE("total_call_time_per_minute"),
        REQUESTS_PER_MINUTE("requests_per_minute"),
        STANDARD_DEVIATION("standard_deviation");

        Agent(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        private String value;
    }

    /**
     * Represents the available values for Threads metrics.  
     */
    public enum Threads implements Value
    {
        AVERAGE_RESPONSE_TIME("average_response_time"),
        CALLS_PER_MINUTE("calls_per_minute"),
        CALL_COUNT("call_count"),
        MIN_RESPONSE_TIME("min_response_time"),
        MAX_RESPONSE_TIME("max_response_time"),
        AVERAGE_EXCLUSIVE_TIME("average_exclusive_time"),
        AVERAGE_VALUE("average_value"),
        TOTAL_CALL_TIME_PER_MINUTE("total_call_time_per_minute"),
        REQUESTS_PER_MINUTE("requests_per_minute"),
        STANDARD_DEVIATION("standard_deviation");

        Threads(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        private String value;
    }

    /**
     * Represents the available values for Java metrics.  
     */
    public enum Java implements Value
    {
        AVERAGE_RESPONSE_TIME("average_response_time"),
        CALLS_PER_MINUTE("calls_per_minute"),
        CALL_COUNT("call_count"),
        MIN_RESPONSE_TIME("min_response_time"),
        MAX_RESPONSE_TIME("max_response_time"),
        AVERAGE_EXCLUSIVE_TIME("average_exclusive_time"),
        AVERAGE_VALUE("average_value"),
        TOTAL_CALL_TIME_PER_MINUTE("total_call_time_per_minute"),
        REQUESTS_PER_MINUTE("requests_per_minute"),
        STANDARD_DEVIATION("standard_deviation");

        Java(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        private String value;
    }

    /**
     * Represents the available values for WebTransaction metrics.  
     */
    public enum WebTransaction implements Value
    {
        AVERAGE_CALL_TIME("average_call_time"),
        AVERAGE_RESPONSE_TIME("average_response_time"),
        REQUESTS_PER_MINUTE("requests_per_minute"),
        CALL_COUNT("call_count"),
        MIN_CALL_TIME("min_call_time"),
        MAX_CALL_TIME("max_call_time"),
        TOTAL_CALL_TIME("total_call_time"),
        THROUGHPUT("throughput"),
        STANDARD_DEVIATION("standard_deviation");

        WebTransaction(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        private String value;
    }

    /**
     * Represents the available values for WebTransactionTotalTime metrics.  
     */
    public enum WebTransactionTotalTime implements Value
    {
        AVERAGE_RESPONSE_TIME("average_response_time"),
        CALLS_PER_MINUTE("calls_per_minute"),
        CALL_COUNT("call_count"),
        MIN_RESPONSE_TIME("min_response_time"),
        MAX_RESPONSE_TIME("max_response_time"),
        AVERAGE_EXCLUSIVE_TIME("average_exclusive_time"),
        AVERAGE_VALUE("average_value"),
        TOTAL_CALL_TIME_PER_MINUTE("total_call_time_per_minute"),
        REQUESTS_PER_MINUTE("requests_per_minute"),
        STANDARD_DEVIATION("standard_deviation");

        WebTransactionTotalTime(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        private String value;
    }

    /**
     * Represents the available values for HttpDispatcher metrics.  
     */
    public enum HttpDispatcher implements Value
    {
        AVERAGE_RESPONSE_TIME("average_response_time"),
        CALLS_PER_MINUTE("calls_per_minute"),
        CALL_COUNT("call_count"),
        MIN_RESPONSE_TIME("min_response_time"),
        MAX_RESPONSE_TIME("max_response_time"),
        AVERAGE_EXCLUSIVE_TIME("average_exclusive_time"),
        AVERAGE_VALUE("average_value"),
        TOTAL_CALL_TIME_PER_MINUTE("total_call_time_per_minute"),
        REQUESTS_PER_MINUTE("requests_per_minute"),
        STANDARD_DEVIATION("standard_deviation"),
        AVERAGE_CALL_TIME("average_call_time");

        HttpDispatcher(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        private String value;
    }

    /**
     * Represents the available values for Filter metrics.  
     */
    public enum Filter implements Value
    {
        AVERAGE_RESPONSE_TIME("average_response_time"),
        CALLS_PER_MINUTE("calls_per_minute"),
        CALL_COUNT("call_count"),
        MIN_RESPONSE_TIME("min_response_time"),
        MAX_RESPONSE_TIME("max_response_time"),
        AVERAGE_EXCLUSIVE_TIME("average_exclusive_time"),
        AVERAGE_VALUE("average_value"),
        TOTAL_CALL_TIME_PER_MINUTE("total_call_time_per_minute"),
        REQUESTS_PER_MINUTE("requests_per_minute"),
        STANDARD_DEVIATION("standard_deviation");

        Filter(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        private String value;
    }

    /**
     * Represents the available values for OtherTransaction metrics.  
     */
    public enum OtherTransaction implements Value
    {
        AVERAGE_RESPONSE_TIME("average_response_time"),
        CALLS_PER_MINUTE("calls_per_minute"),
        CALL_COUNT("call_count"),
        MIN_RESPONSE_TIME("min_response_time"),
        MAX_RESPONSE_TIME("max_response_time"),
        AVERAGE_EXCLUSIVE_TIME("average_exclusive_time"),
        AVERAGE_VALUE("average_value"),
        TOTAL_CALL_TIME_PER_MINUTE("total_call_time_per_minute"),
        REQUESTS_PER_MINUTE("requests_per_minute"),
        STANDARD_DEVIATION("standard_deviation");

        OtherTransaction(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        private String value;
    }

    /**
     * Default constructor.
     */
    public Metric()
    {
    }

    /**
     * Sets the name of the metric.
     * @param name The name of the metric
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the name of the metric.
     * @param name The name of the metric
     */
    public void setName(Category name)
    {
        setName(name.value());
    }

    /**
     * Returns the name of the metric.
     * @return The name of the metric
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the units of the metric.
     * @param units The units of the metric
     */
    public void setUnits(String units)
    {
        this.units = units;
    }

    /**
     * Returns the units of the metric.
     * @return The units of the metric
     */
    public String getUnits()
    {
        return units;
    }

    /**
     * Sets the scope of the metric.
     * @param scope The scope of the metric
     */
    public void setScope(String scope)
    {
        this.scope = scope;
    }

    /**
     * Returns the scope of the metric.
     * @return The scope of the metric
     */
    public String getScope()
    {
        return scope;
    }

    /**
     * Sets the list of values.
     * @param values The list of values
     */
    public void setValues(List<String> values)
    {
        this.values.clear();
        if(values != null)
            this.values.addAll(values);
    }

    /**
     * Adds a value to the list of values.
     * @param value The value to add to the list of values
     */
    public void addValue(String value)
    {
        if(this.values == null)
            this.values = new ArrayList<String>();
        this.values.add(value);
    }

    /**
     * Adds an metric value to the list of values.
     * @param value The metric value to add to the list of values
     */
    public void addValue(Value value)
    {
        addValue(value.value());
    }

    /**
     * Returns the list of values.
     * @return The list of values
     */
    public List<String> getValues()
    {
        return values;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Metric [name="+name
            +", units="+units
            +", scope="+scope
            +", values="+values
            +"]";
    }

    /**
     * Returns a builder for the metric.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make metric construction easier.
     */
    public static class Builder
    {
        private Metric metric = new Metric();

        /**
         * Sets the name of the metric.
         * @param name The name of the metric
         * @return This object
         */
        public Builder name(String name)
        {
            metric.setName(name);
            return this;
        }

        /**
         * Sets the name of the metric.
         * @param name The name of the metric
         * @return This object
         */
        public Builder name(Category name)
        {
            metric.setName(name);
            return this;
        }

        /**
         * Sets the scope of the metric.
         * @param scope The scope of the metric
         * @return This object
         */
        public Builder scope(String scope)
        {
            metric.setScope(scope);
            return this;
        }

        /**
         * Sets the units of the metric.
         * @param units The units of the metric
         * @return This object
         */
        public Builder units(String units)
        {
            metric.setUnits(units);
            return this;
        }

        /**
         * Sets the values of the metric.
         * @param values The values of the metric
         * @return This object
         */
        public Builder values(List<String> values)
        {
            metric.setValues(values);
            return this;
        }

        /**
         * Adds the given value to the list of values for the metric.
         * @param value The value to add to the list of values
         * @return This object
         */
        public Builder addValue(String value)
        {
            metric.addValue(value);
            return this;
        }

        /**
         * Adds the given metric value to the list of values for the metric.
         * @param value The metric value to add to the list of values
         * @return This object
         */
        public Builder addValue(Value value)
        {
            metric.addValue(value);
            return this;
        }

        /**
         * Returns the configured widget data instance
         * @return The widget data instance
         */
        public Metric build()
        {
            return metric;
        }
    }
}