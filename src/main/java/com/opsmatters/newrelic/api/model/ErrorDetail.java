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
public class ErrorDetail extends ErrorResponse
{
    private String error;
    private String status;
    private String detail;

    /**
     * Default constructor.
     */
    public ErrorDetail()
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
     * Returns <CODE>true</CODE> if this object contains an error.
     * @return <CODE>true</CODE> if this object contains an derror
     */
    @Override
    public boolean hasError()
    {
        return true;
    }

    /**
     * Returns the error message.
     * @return The error message
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        if(error != null)
            sb.append(error);

        if(detail != null)
        {
            if(sb.length() > 0)
                sb.append(" ");
            sb.append(detail);
        }

        return sb.toString();
    }
}
