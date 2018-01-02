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

package com.opsmatters.newrelic.api.model.channel;

/**
 * Represents a New Relic PagerDuty channel configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PagerDutyConfiguration extends ChannelConfiguration
{
    private String service_key;

    /**
     * Default constructor.
     */
    public PagerDutyConfiguration()
    {
        super("pagerduty");
    }
   
    /**
     * Constructor that takes a service key.
     * @param service_key The service key
     */
    public PagerDutyConfiguration(String service_key)
    {
        this();
        setServiceKey(service_key);
    }

    /**
     * Sets the service key for the alerts.
     * @param service_key The service key for the alerts
     */
    public void setServiceKey(String service_key)
    {
        this.service_key = service_key;
    }

    /**
     * Returns the service key for the alerts.
     * @return The service key for the alerts
     */
    public String getServiceKey()
    {
        return service_key;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "PagerDutyConfiguration ["+super.toString()
            +", service_key="+service_key
            +"]";
    }

    /**
     * Returns a builder for the PagerDuty configuration.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make PagerDuty configuration construction easier.
     */
    public static class Builder
    {
        private PagerDutyConfiguration configuration = new PagerDutyConfiguration();

        /**
         * Sets the service key of the PagerDuty configuration.
         * @param service_key The service key of the alerts
         * @return This object
         */
        public Builder serviceKey(String service_key)
        {
            configuration.setServiceKey(service_key);
            return this;
        }

        /**
         * Returns the configured PagerDuty configuration instance
         * @return The PagerDuty configuration instance
         */
        public PagerDutyConfiguration build()
        {
            return configuration;
        }
    }
}