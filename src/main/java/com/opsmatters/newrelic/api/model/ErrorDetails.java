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
 * Used to marshall a REST API errors response.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ErrorDetails extends ErrorResponse
{
    private List<ErrorDetail> errors;

    /**
     * Default constructor.
     */
    public ErrorDetails()
    {
    }

    /**
     * Returns the error messages.
     * @return The error messages
     */
    public List<ErrorDetail> getErrors()
    {
        return errors;
    }

    /**
     * Sets the error messages.
     * @param errors The error messages
     */
    public void setErrors(List<ErrorDetail> errors)
    {
        this.errors = errors;
    }

    /**
     * Returns the number of error messages.
     * @return The number of error messages
     */
    public int numErrors()
    {
        return errors != null ? errors.size() : -1;
    }

    /**
     * Returns <CODE>true</CODE> if this object contains errors.
     * @return <CODE>true</CODE> if this object contains errors
     */
    @Override
    public boolean hasError()
    {
        return numErrors() > 0;
    }

    /**
     * Returns the formatted error messages.
     * @return The formatted error messages
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        if(errors != null)
        {
            for(ErrorDetail error : errors)
            {
                if(sb.length() > 0)
                    sb.append(" / ");
                sb.append(error);
            }
        }

        return sb.toString();
    }
}
