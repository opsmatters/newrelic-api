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

package com.opsmatters.newrelic.api.model.synthetics;

import com.opsmatters.newrelic.api.model.NamedResource;

/**
 * Represents a New Relic script location.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ScriptLocation implements NamedResource
{
    private String name;
    private String hmac;

    /**
     * Default constructor.
     */
    public ScriptLocation()
    {
    }

    /**
     * Sets the name of the location.
     * @param name The name of the location
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Returns the name of the location.
     * @return The name of the location
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the hmac of the location.
     * @param hmac The hmac of the location
     */
    public void setHmac(String hmac)
    {
        this.hmac = hmac;
    }
    
    /**
     * Returns the hmac of the location.
     * @return The hmac of the location
     */
    public String getHmac()
    {
        return hmac;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "ScriptLocation [name="+name
            +", hmac="+hmac
            +"]";
    }

    /**
     * Returns a builder for the script location.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make script location construction easier.
     */
    public static class Builder
    {
        private ScriptLocation scriptLocation = new ScriptLocation();

        /**
         * Sets the location name.
         * @param name The location name
         * @return This object
         */
        public Builder name(String name)
        {
            scriptLocation.setName(name);
            return this;
        }

        /**
         * Sets the location hmac.
         * @param hmac The location hmac
         * @return This object
         */
        public Builder hmac(String hmac)
        {
            scriptLocation.setHmac(hmac);
            return this;
        }

        /**
         * Returns the configured script location instance
         * @return The script location instance
         */
        public ScriptLocation build()
        {
            return scriptLocation;
        }
    }
}