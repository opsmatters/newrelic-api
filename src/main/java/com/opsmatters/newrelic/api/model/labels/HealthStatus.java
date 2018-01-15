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

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a set of New Relic health statuses.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class HealthStatus
{
    private List<Long> green = new ArrayList<Long>();
    private List<Long> orange = new ArrayList<Long>();
    private List<Long> red = new ArrayList<Long>();
    private List<Long> gray = new ArrayList<Long>();
   
    /**
     * Default constructor.
     */
    public HealthStatus()
    {
    }

    /**
     * Returns the list of green applications.
     * @return The list of green applications
     */
    public List<Long> getGreen()
    {
        return green;
    }

    /**
     * Returns the list of orange applications.
     * @return The list of orange applications
     */
    public List<Long> getOrange()
    {
        return orange;
    }

    /**
     * Returns the list of red applications.
     * @return The list of red applications
     */
    public List<Long> getRed()
    {
        return red;
    }

    /**
     * Returns the list of gray applications.
     * @return The list of gray applications
     */
    public List<Long> getGray()
    {
        return gray;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "HealthStatus [green="+green
            +", orange="+orange
            +", red="+red
            +", gray="+gray
            +"]";
    }
}