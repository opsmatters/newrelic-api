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

/**
 * Represents a New Relic Browser alert condition.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class BrowserAlertCondition extends AlertCondition
{
    /**
     * The type of the alert condition.
     */
    public static final ConditionType TYPE = ConditionType.BROWSER;

    /**
     * Represents the metric types for this condition type.  
     */
    public enum Metric
    {
        END_USER_APDEX("end_user_apdex"),
        TOTAL_PAGE_LOAD("total_page_load"),
        PAGE_RENDERING("page_rendering"),
        WEB_APPLICATION("web_application"),
        NETWORK("network"),
        DOM_PROCESSING("dom_processing"),
        REQUEST_QUEUING("request_queuing"),
        AJAX_RESPONSE_TIME("ajax_response_time"),
        PAGE_VIEWS_WITH_JS_ERRORS("page_views_with_js_errors"),
        PAGE_VIEW_THROUGHPUT("page_view_throughput"),
        AJAX_THROUGHPUT("ajax_throughput"),
        USER_DEFINED("user_defined");

        Metric(String value)
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
    public BrowserAlertCondition()
    {
        setType(TYPE.value());
    }
    
    /**
     * Sets the metric of the alert condition.
     * @param metric The metric of the alert condition
     */
    public void setMetric(Metric metric)
    {
        setMetric(metric.value());
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "BrowserAlertCondition ["+super.toString()+"]";
    }

    /**
     * Returns a builder for the alert condition.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make alert condition construction easier.
     */
    public static class Builder extends AlertCondition.Builder<BrowserAlertCondition, Builder>
    {
        private BrowserAlertCondition condition = new BrowserAlertCondition();

        /**
         * Default constructor.
         */
        public Builder()
        {
            condition(condition);
        }

        /**
         * Sets the metric of the alert condition.
         * @param metric The metric of the alert condition
         * @return This object
         */
        public Builder metric(Metric metric)
        {
            condition.setMetric(metric);
            return this;
        }

        /**
         * Sets the user defined of the alert condition.
         * @param userDefined The user defined of the alert condition
         * @return This object
         */
        public Builder userDefined(UserDefined userDefined)
        {
            condition.setUserDefined(userDefined);
            return this;
        }

        /**
         * Returns this object.
         * @return This object
         */
        @Override
        protected Builder self()
        {
            return this;
        }

        /**
         * Returns the configured alert condition instance
         * @return The alert condition instance
         */
        @Override
        public BrowserAlertCondition build()
        {
            return condition;
        }
    }
}