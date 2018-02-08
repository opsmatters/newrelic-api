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
 * Used to marshall a REST API error response.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Errors
{
    private List<Error> errors;

    /**
     * Default constructor.
     */
    public Errors()
    {
    }

    /**
     * Returns the error messages.
     * @return The error messages
     */
    public List<Error> getErrors()
    {
        return errors;
    }

    /**
     * Sets the error messages.
     * @param errors The error messages
     */
    public void setErrors(List<Error> errors)
    {
        this.errors = errors;
    }

    /**
     * Returns the formatted error messages.
     * @return The formatted error messages
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(Error error : errors)
        {
            if(sb.length() > 0)
                sb.append(" / ");
            sb.append(error);
        }
        return sb.toString();
    }
}
