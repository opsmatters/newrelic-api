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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericType;
import com.opsmatters.newrelic.api.model.*;

/**
 * Provides the types and HTTP operations to be used with the API calls.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class BaseFluent
{
    private static final Logger logger = Logger.getLogger(BaseFluent.class.getName());

    protected static final GenericType<AlertPolicyWrapper> ALERT_POLICY = new GenericType<AlertPolicyWrapper>(){};
    protected static final GenericType<AlertPoliciesWrapper> ALERT_POLICIES = new GenericType<AlertPoliciesWrapper>(){};

    protected static final GenericType<InfraAlertConditionWrapper> INFRA_ALERT_CONDITION = new GenericType<InfraAlertConditionWrapper>(){};
    protected static final GenericType<InfraAlertConditionsWrapper> INFRA_ALERT_CONDITIONS = new GenericType<InfraAlertConditionsWrapper>(){};

    protected HttpContext HTTP;
    private NewRelicApiService apiService;

    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param apiService The set of API operations
     */
    public BaseFluent(HttpContext httpContext, NewRelicApiService apiService)
    {    
        this.HTTP = httpContext;
        this.apiService = apiService;
    }
    
    /**
     * Returns the API service.
     * @return The API service
     */
    public NewRelicApiService getService()
    {    
        return this.apiService;
    }

    /**
     * Encode "/" to its URL encoded representation "%2F".
     * @param str The input string
     * @return The encoded String
     */
    public static String encode(String str)
    {
        String encodedValue = str;

        try
        {
            encodedValue = URLEncoder.encode(str, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            logger.severe("Failed to encode value: "+str);
        }

        return encodedValue;
    }
}
