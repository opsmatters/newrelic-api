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
import com.opsmatters.newrelic.api.model.AlertIncident;
import com.opsmatters.newrelic.api.model.AlertViolation;
import com.opsmatters.newrelic.api.model.AlertEvent;
import com.opsmatters.newrelic.api.model.policy.AlertPolicy;
import com.opsmatters.newrelic.api.model.policy.AlertPolicyChannel;
import com.opsmatters.newrelic.api.model.channel.AlertChannel;
import com.opsmatters.newrelic.api.model.condition.AlertCondition;
import com.opsmatters.newrelic.api.model.condition.NrqlAlertCondition;
import com.opsmatters.newrelic.api.model.condition.ExternalServiceAlertCondition;
import com.opsmatters.newrelic.api.model.condition.PluginsAlertCondition;
import com.opsmatters.newrelic.api.model.condition.SyntheticsAlertCondition;
import com.opsmatters.newrelic.api.model.condition.InfraAlertCondition;
import com.opsmatters.newrelic.api.model.entity.Application;
import com.opsmatters.newrelic.api.model.entity.BrowserApplication;
import com.opsmatters.newrelic.api.model.entity.Metric;
import com.opsmatters.newrelic.api.model.entity.MetricData;

/**
 * Provides the types and HTTP operations to be used with the API calls.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class BaseFluent
{
    private static final Logger logger = Logger.getLogger(BaseFluent.class.getName());

    protected static final GenericType<AlertPolicy> ALERT_POLICY = new GenericType<AlertPolicy>(){};
    protected static final GenericType<Collection<AlertPolicy>> ALERT_POLICIES = new GenericType<Collection<AlertPolicy>>(){};

    protected static final GenericType<AlertChannel> ALERT_CHANNEL = new GenericType<AlertChannel>(){};
    protected static final GenericType<Collection<AlertChannel>> ALERT_CHANNELS = new GenericType<Collection<AlertChannel>>(){};

    protected static final GenericType<AlertPolicyChannel> ALERT_POLICY_CHANNEL = new GenericType<AlertPolicyChannel>(){};

    protected static final GenericType<AlertCondition> ALERT_CONDITION = new GenericType<AlertCondition>(){};
    protected static final GenericType<Collection<AlertCondition>> ALERT_CONDITIONS = new GenericType<Collection<AlertCondition>>(){};

    protected static final GenericType<NrqlAlertCondition> NRQL_ALERT_CONDITION = new GenericType<NrqlAlertCondition>(){};
    protected static final GenericType<Collection<NrqlAlertCondition>> NRQL_ALERT_CONDITIONS = new GenericType<Collection<NrqlAlertCondition>>(){};

    protected static final GenericType<ExternalServiceAlertCondition> EXTERNAL_SERVICE_ALERT_CONDITION = new GenericType<ExternalServiceAlertCondition>(){};
    protected static final GenericType<Collection<ExternalServiceAlertCondition>> EXTERNAL_SERVICE_ALERT_CONDITIONS = new GenericType<Collection<ExternalServiceAlertCondition>>(){};

    protected static final GenericType<PluginsAlertCondition> PLUGINS_ALERT_CONDITION = new GenericType<PluginsAlertCondition>(){};
    protected static final GenericType<Collection<PluginsAlertCondition>> PLUGINS_ALERT_CONDITIONS = new GenericType<Collection<PluginsAlertCondition>>(){};

    protected static final GenericType<SyntheticsAlertCondition> SYNTHETICS_ALERT_CONDITION = new GenericType<SyntheticsAlertCondition>(){};
    protected static final GenericType<Collection<SyntheticsAlertCondition>> SYNTHETICS_ALERT_CONDITIONS = new GenericType<Collection<SyntheticsAlertCondition>>(){};

    protected static final GenericType<InfraAlertCondition> INFRA_ALERT_CONDITION = new GenericType<InfraAlertCondition>(){};
    protected static final GenericType<Collection<InfraAlertCondition>> INFRA_ALERT_CONDITIONS = new GenericType<Collection<InfraAlertCondition>>(){};

    protected static final GenericType<AlertIncident> ALERT_INCIDENT = new GenericType<AlertIncident>(){};
    protected static final GenericType<Collection<AlertIncident>> ALERT_INCIDENTS = new GenericType<Collection<AlertIncident>>(){};

    protected static final GenericType<AlertViolation> ALERT_VIOLATION = new GenericType<AlertViolation>(){};
    protected static final GenericType<Collection<AlertViolation>> ALERT_VIOLATIONS = new GenericType<Collection<AlertViolation>>(){};

    protected static final GenericType<AlertEvent> ALERT_EVENT = new GenericType<AlertEvent>(){};
    protected static final GenericType<Collection<AlertEvent>> ALERT_EVENTS = new GenericType<Collection<AlertEvent>>(){};

    protected static final GenericType<Application> APPLICATION = new GenericType<Application>(){};
    protected static final GenericType<Collection<Application>> APPLICATIONS = new GenericType<Collection<Application>>(){};

    protected static final GenericType<BrowserApplication> BROWSER_APPLICATION = new GenericType<BrowserApplication>(){};
    protected static final GenericType<Collection<BrowserApplication>> BROWSER_APPLICATIONS = new GenericType<Collection<BrowserApplication>>(){};

    protected static final GenericType<Collection<Metric>> METRICS = new GenericType<Collection<Metric>>(){};
    protected static final GenericType<MetricData> METRIC_DATA = new GenericType<MetricData>(){};

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
