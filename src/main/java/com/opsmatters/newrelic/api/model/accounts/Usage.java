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

package com.opsmatters.newrelic.api.model.accounts;

import java.util.Date;

/**
 * Represents a set of New Relic account usage.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Usage
{
    private Date from;
    private Date to;
    private Long usage;
   
    /**
     * Default constructor.
     */
    public Usage()
    {
    }

    /**
     * Returns the from date of the usage.
     * @return The from date of the usage
     */
    public Date getFrom()
    {
        return from;
    }

    /**
     * Returns the to date of the usage.
     * @return The to date of the usage
     */
    public Date getTo()
    {
        return to;
    }

    /**
     * Returns the usage.
     * @return The usage
     */
    public Long getUsage()
    {
        return usage;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Usage [from="+from
            +", to="+to
            +", usage="+usage
            +"]";
    }
}