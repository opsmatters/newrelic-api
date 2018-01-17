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
 * Represents the base class for all resources with a numeric id.
 * 
 * @author Gerald Curley (opsmatters)
 */
public abstract class IdResource
{
    private Long id;
    
    /**
     * Default constructor.
     */
    public IdResource()
    {
    }

    /**
     * Sets the id of the resource.
     * @param id The id of the resource
     */
    public void setId(long id)
    {
        this.id = id;
    }
   
    /**
     * Returns the id of the resource.
     * @return The id of the resource
     */
    public long getId()
    {
        return id;
    }

    /**
     * Returns a string representation of the resource.
     */
    @Override
    public String toString()
    {
        return "id="+id;
    }
}