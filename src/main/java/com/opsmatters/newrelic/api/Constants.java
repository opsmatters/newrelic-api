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

package com.opsmatters.newrelic.api;

/**
 * Represents constants used by the New Relic API.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Constants
{
    /**
     * Private constructor.
     */
    private Constants()
    {
    }

    /**
     * The name of the standard property used for the Account ID.
     */
    public static final String ACCOUNT_ID_PROPERTY = "newrelic.account_id";

    /**
     * The name of the standard property used for the API key.
     */
    public static final String API_KEY_PROPERTY = "newrelic.api_key";

    /**
     * The name of the standard property used for the Query key.
     */
    public static final String QUERY_KEY_PROPERTY = "newrelic.query_key";

    /**
     * The name of the standard property used for the License key.
     */
    public static final String LICENSE_KEY_PROPERTY = "newrelic.license_key";
}