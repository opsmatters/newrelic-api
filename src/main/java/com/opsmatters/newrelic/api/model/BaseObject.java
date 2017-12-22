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

package com.opsmatters.newrelic.api.model;

/**
 * Represents the base class for all top-level model objects.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class BaseObject
{
    private Long id;
    private String name;
    
    /**
     * Default constructor.
     */
    public BaseObject()
    {
    }
   
    /**
     * Returns the id of the object.
     * @return The id of the object
     */
    public long getId()
    {
        return id;
    }

    /**
     * Sets the name of the object.
     * @param name The name of the object
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the name of the object.
     * @return The name of the object
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "id="+id
            +", name="+name;
    }
}