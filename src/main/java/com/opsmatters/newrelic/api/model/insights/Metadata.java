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

package com.opsmatters.newrelic.api.model.insights;

/**
 * Represents a New Relic Insights dashboard metadata.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Metadata
{
    private Integer version;

    /**
     * Default constructor.
     */
    public Metadata()
    {
    }

    /**
     * Sets the version of the dashboard schema.
     * @param version The version of the dashboard schema
     */
    public void setVersion(Integer version)
    {
        this.version = version;
    }

    /**
     * Returns the version of the dashboard schema.
     * @return The version of the dashboard schema
     */
    public Integer getVersion()
    {
        return version;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Metadata [version="+version+"]";
    }

    /**
     * Returns a builder for the metadata.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make metadata construction easier.
     */
    public static class Builder
    {
        private Metadata metadata = new Metadata();

        /**
         * Sets the version of the dashboard schema.
         * @param version The version of the dashboard schema
         * @return This object
         */
        public Builder version(int version)
        {
            metadata.setVersion(version);
            return this;
        }

        /**
         * Returns the configured metadata instance
         * @return The metadata instance
         */
        public Metadata build()
        {
            return metadata;
        }
    }
}