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

import java.util.Map;

/**
 * Used to marshall a HTTP error aresponse of each type.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ResponseErrors
{
    /**
     * Used to marshall a HTTP error response containing a string.
     */
    public static class StringResponseError extends ResponseError<String>
    {
    }

    /**
     * Used to marshall a HTTP error response containing an object.
     */
    public static class ObjectResponseError extends ResponseError<Object>
    {
    }

    /**
     * Used to marshall a HTTP error response containing a map.
     */
    public static class MapResponseError extends ResponseError<Map<String,Object>>
    {
    }
}
