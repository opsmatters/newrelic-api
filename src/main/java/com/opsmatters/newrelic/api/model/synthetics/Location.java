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

import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.NamedResource;

/**
 * Represents a New Relic script location.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Location implements NamedResource
{
    private String name;
    private String label;
    @SerializedName("private")
    private Boolean _private;

    /**
     * Default constructor.
     */
    public Location()
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
     * Sets the label of the location.
     * @param label The label of the location
     */
    public void setLabel(String label)
    {
        this.label = label;
    }
    
    /**
     * Returns the label of the location.
     * @return The label of the location
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * Set to <CODE>true</CODE> if the location is private.
     * @param _private <CODE>true</CODE> if the location is private
     */
    public void setPrivate(boolean _private)
    {
        this._private = _private;
    }

    /**
     * Returns <CODE>true</CODE> if the location is private.
     * @return <CODE>true</CODE> if the location is private
     */
    public boolean isPrivate()
    {
        return _private;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Location [name="+name
            +", private="+_private
            +", label="+label
            +"]";
    }
}