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
 * Used to marshall a HTTP error response.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ResponseError<T>
{
    private String error;
    private T reason;

    /**
     * Default constructor.
     */
    public ResponseError()
    {
    }

    /**
     * Returns the HTTP error message.
     * @return The HTTP error message
     */
    public String getError()
    {
        return error;
    }

    /**
     * Sets the HTTP error message.
     * @param error The HTTP error message
     */
    public void setError(String error)
    {
        this.error = error;
    }

    /**
     * Returns the HTTP reason.
     * @return The HTTP reason
     */
    public T getReason()
    {
        return reason;
    }

    /**
     * Sets the HTTP reason.
     * @param reason The HTTP reason
     */
    public void setReason(T reason)
    {
        this.reason = reason;
    }
}
