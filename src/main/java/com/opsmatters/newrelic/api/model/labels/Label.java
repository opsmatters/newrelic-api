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

package com.opsmatters.newrelic.api.model.labels;

import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.NamedResource;

/**
 * Represents a New Relic label.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Label extends NamedResource
{
    private String key;
    private String category;

    @SerializedName("application_health_status")
    private HealthStatus applicationHealthStatus;

    @SerializedName("server_health_status")
    private HealthStatus serverHealthStatus;

    private LabelLinks links;

    /**
     * Default constructor.
     */
    public Label()
    {
    }
    
    /**
     * Sets the key of the label.
     * @param key The key of the label
     */
    public void setKey(String key)
    {
        this.key = key;
    }

    /**
     * Returns the key of the key.
     * @return The key of the key
     */
    public String getKey()
    {
        return key;
    }

    /**
     * Sets the category of the label.
     * @param category The category of the label
     */
    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * Returns the category of the key.
     * @return The category of the key
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * Returns the application health status for the label.
     * @return The application health status for the label
     */
    public HealthStatus getApplicationHealthStatus()
    {
        return applicationHealthStatus;
    }

    /**
     * Returns the server health status for the label.
     * @return The server health status for the label
     */
    public HealthStatus getServerHealthStatus()
    {
        return serverHealthStatus;
    }

    /**
     * Sets the links of the label.
     * @param links The links of the label
     */
    public void setLinks(LabelLinks links)
    {
        this.links = links;
    }

    /**
     * Returns the links of the label.
     * @return The links of the label
     */
    public LabelLinks getLinks()
    {
        return links;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Label ["+super.toString()
            +", key="+key
            +", category="+category
            +", applicationHealthStatus="+applicationHealthStatus
            +", serverHealthStatus="+serverHealthStatus
            +", links="+links
            +"]";
    }

    /**
     * Returns a builder for the label.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make label construction easier.
     */
    public static class Builder
    {
        private Label label = new Label();
        private LabelLinks links = new LabelLinks();

        /**
         * Default constructor.
         */
        Builder()
        {
            label.setLinks(links);
        }

        /**
         * Sets the name of the label.
         * @param name The name of the label
         * @return This object
         */
        public Builder name(String name)
        {
            label.setName(name);
            return this;
        }

        /**
         * Sets the category of the label.
         * @param category The category of the label
         * @return This object
         */
        public Builder category(String category)
        {
            label.setCategory(category);
            return this;
        }

        /**
         * Sets the links of the label.
         * @param links The links of the label
         * @return This object
         */
        public Builder links(LabelLinks links)
        {
            label.setLinks(links);
            return this;
        }

        /**
         * Adds an application to the links of the label.
         * @param applicationId The application to add to the label
         * @return This object
         */
        public Builder addApplicationLink(long applicationId)
        {
            links.getApplications().add(applicationId);
            return this;
        }

        /**
         * Adds a server to the links of the label.
         * @param serverId The server to add to the label
         * @return This object
         */
        public Builder addServerLink(long serverId)
        {
            links.getServers().add(serverId);
            return this;
        }

        /**
         * Returns the configured label instance
         * @return The label instance
         */
        public Label build()
        {
            return label;
        }
    }
}