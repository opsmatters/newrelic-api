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
 * Used to marshall a REST API error message.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Error
{
    private String error;

    /**
     * Default constructor.
     */
    public Error()
    {
    }

    /**
     * Returns the error message.
     * @return The error message
     */
    public String getError()
    {
        return error;
    }

    /**
     * Sets the error message.
     * @param error The error message
     */
    public void setError(String error)
    {
        this.error = error;
    }

    /**
     * Returns the error message.
     * @return The error message
     */
    public String toString()
    {
        return error;
    }
}
