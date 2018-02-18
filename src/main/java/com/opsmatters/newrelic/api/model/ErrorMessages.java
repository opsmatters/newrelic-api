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

import java.util.List;

/**
 * Used to marshall a set of REST API error messages.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ErrorMessages
{
    private String title;
    private List<String> messages;

    /**
     * Default constructor.
     */
    public ErrorMessages()
    {
    }

    /**
     * Returns the title of the error messages.
     * @return The title of the error messages
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets the title of the error messages.
     * @param title The title of the error messages
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Returns the error messages.
     * @return The error messages
     */
    public List<String> getMessages()
    {
        return messages;
    }

    /**
     * Returns the number of error messages.
     * @return The number of error messages
     */
    public int numMessages()
    {
        return messages != null ? messages.size() : -1;
    }

    /**
     * Returns the error messages.
     * @return The error messages
     */
    public String toString()
    {
        String ret = title;
        if(messages != null)
            ret += ": "+messages;
        return ret;
    }
}
