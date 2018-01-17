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

package com.opsmatters.newrelic.api.model.deployments;

import java.util.Date;
import com.opsmatters.newrelic.api.model.IdResource;

/**
 * Represents a New Relic nrql.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Deployment extends IdResource
{
    private String revision;
    private String changelog;
    private String description;
    private String user;
    private Date timestamp;
    private DeploymentLinks links;
    
    /**
     * Default constructor.
     */
    public Deployment()
    {
    }
    
    /**
     * Sets the revision of the deployment.
     * @param revision The revision of the deployment
     */
    public void setRevision(String revision)
    {
        this.revision = revision;
    }

    /**
     * Returns the revision of the deployment.
     * @return The revision of the deployment
     */
    public String getRevision()
    {
        return revision;
    }

    /**
     * Sets the changelog of the deployment.
     * @param changelog The changelog of the deployment
     */
    public void setChangelog(String changelog)
    {
        this.changelog = changelog;
    }

    /**
     * Returns the changelog of the deployment.
     * @return The changelog of the deployment
     */
    public String getChangelog()
    {
        return changelog;
    }

    /**
     * Sets the description of the deployment.
     * @param description The description of the deployment
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Returns the description of the deployment.
     * @return The description of the deployment
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets the user of the deployment.
     * @param user The user of the deployment
     */
    public void setUser(String user)
    {
        this.user = user;
    }

    /**
     * Returns the user of the deployment.
     * @return The user of the deployment
     */
    public String getUser()
    {
        return user;
    }

    /**
     * Returns the date of the deployment.
     * @return The date of the deployment
     */
    public Date getTimestamp()
    {
        return timestamp;
    }

    /**
     * Returns the links of the deployment.
     * @return The links of the deployment
     */
    public DeploymentLinks getLinks()
    {
        return links;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Deployment [revision="+revision
            +", changelog="+changelog
            +", description="+description
            +", user="+user
            +", timestamp="+timestamp
            +", links="+links
            +"]";
    }

    /**
     * Returns a builder for the deployment.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make deployment construction easier.
     */
    public static class Builder
    {
        private Deployment deployment = new Deployment();

        /**
         * Sets the revision of the deployment.
         * @param revision The revision of the deployment
         * @return This object
         */
        public Builder revision(String revision)
        {
            deployment.setRevision(revision);
            return this;
        }

        /**
         * Sets the changelog of the deployment.
         * @param changelog The changelog of the deployment
         * @return This object
         */
        public Builder changelog(String changelog)
        {
            deployment.setChangelog(changelog);
            return this;
        }

        /**
         * Sets the description of the deployment.
         * @param description The description of the deployment
         * @return This object
         */
        public Builder description(String description)
        {
            deployment.setDescription(description);
            return this;
        }

        /**
         * Sets the user of the deployment.
         * @param user The user of the deployment
         * @return This object
         */
        public Builder user(String user)
        {
            deployment.setUser(user);
            return this;
        }

        /**
         * Returns the configured deployment instance
         * @return The deployment instance
         */
        public Deployment build()
        {
            return deployment;
        }
    }
}