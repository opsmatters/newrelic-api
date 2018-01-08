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

package com.opsmatters.newrelic.api.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic Browser application.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class BrowserApplication extends Entity
{
    /**
     * The type of the entity.
     */
    public static final EntityType TYPE = EntityType.BROWSER_APPLICATION;

    @SerializedName("browser_monitoring_key")
    private String browserMonitoringKey;

    @SerializedName("loader_script")
    private String loaderScript;

    /**
     * Default constructor.
     */
    public BrowserApplication()
    {
        super(TYPE.value());
    }
    
    /**
     * Sets the browser monitoring key of the application.
     * @param browserMonitoringKey The browser monitoring key of the application
     */
    public void setBrowserMonitoringKey(String browserMonitoringKey)
    {
        this.browserMonitoringKey = browserMonitoringKey;
    }

    /**
     * Returns the browser monitoring key of the application.
     * @return The browser monitoring key of the application
     */
    public String getBrowserMonitoringKey()
    {
        return browserMonitoringKey;
    }

    /**
     * Sets the loader script of the application.
     * @param loaderScript The loader script of the application
     */
    public void setLoaderScript(String loaderScript)
    {
        this.loaderScript = loaderScript;
    }

    /**
     * Returns the loader script of the application.
     * @return The loader script of the application
     */
    public String getLoaderScript()
    {
        return loaderScript;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "BrowserApplication ["+super.toString()
            +", browserMonitoringKey="+browserMonitoringKey
            +", loaderScript="+loaderScript
            +"]";
    }

    /**
     * Returns a builder for the Browser application.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make Browser application construction easier.
     */
    public static class Builder extends Entity.Builder<BrowserApplication, Builder>
    {
        private BrowserApplication application = new BrowserApplication();

        /**
         * Default constructor.
         */
        public Builder()
        {
            entity(application);
        }

        /**
         * Sets the browser monitoring key of the application.
         * @param browserMonitoringKey The browser monitoring key of the application
         * @return This object
         */
        public Builder browserMonitoringKey(String browserMonitoringKey)
        {
            application.setBrowserMonitoringKey(browserMonitoringKey);
            return this;
        }

        /**
         * Sets the loader script of the application.
         * @param loaderScript The loader script of the application
         * @return This object
         */
        public Builder loaderScript(String loaderScript)
        {
            application.setLoaderScript(loaderScript);
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
         * Returns the configured application instance
         * @return The application instance
         */
        @Override
        public BrowserApplication build()
        {
            return application;
        }
    }
}