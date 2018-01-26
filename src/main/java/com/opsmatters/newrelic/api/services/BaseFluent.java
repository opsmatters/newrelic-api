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

package com.opsmatters.newrelic.api.services;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericType;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.alerts.AlertIncident;
import com.opsmatters.newrelic.api.model.alerts.AlertViolation;
import com.opsmatters.newrelic.api.model.alerts.AlertEvent;
import com.opsmatters.newrelic.api.model.alerts.policies.AlertPolicy;
import com.opsmatters.newrelic.api.model.alerts.policies.AlertPolicyChannel;
import com.opsmatters.newrelic.api.model.alerts.channels.AlertChannel;
import com.opsmatters.newrelic.api.model.alerts.conditions.AlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.NrqlAlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.ExternalServiceAlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.PluginsAlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.SyntheticsAlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.InfraAlertCondition;
import com.opsmatters.newrelic.api.model.applications.Application;
import com.opsmatters.newrelic.api.model.applications.ApplicationHost;
import com.opsmatters.newrelic.api.model.applications.ApplicationInstance;
import com.opsmatters.newrelic.api.model.applications.BrowserApplication;
import com.opsmatters.newrelic.api.model.applications.MobileApplication;
import com.opsmatters.newrelic.api.model.transactions.KeyTransaction;
import com.opsmatters.newrelic.api.model.plugins.Plugin;
import com.opsmatters.newrelic.api.model.plugins.PluginComponent;
import com.opsmatters.newrelic.api.model.servers.Server;
import com.opsmatters.newrelic.api.model.metrics.Metric;
import com.opsmatters.newrelic.api.model.metrics.MetricData;
import com.opsmatters.newrelic.api.model.deployments.Deployment;
import com.opsmatters.newrelic.api.model.labels.Label;
import com.opsmatters.newrelic.api.model.accounts.PartnerAccount;
import com.opsmatters.newrelic.api.model.accounts.PartnerUser;
import com.opsmatters.newrelic.api.model.accounts.PartnerSubscription;
import com.opsmatters.newrelic.api.model.accounts.User;
import com.opsmatters.newrelic.api.model.accounts.UsageData;
import com.opsmatters.newrelic.api.model.synthetics.Monitor;
import com.opsmatters.newrelic.api.model.synthetics.Script;
import com.opsmatters.newrelic.api.model.synthetics.Location;
import com.opsmatters.newrelic.api.model.insights.QueryData;
import com.opsmatters.newrelic.api.model.Status;
import com.opsmatters.newrelic.api.model.insights.Dashboard;

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

    protected static final GenericType<ApplicationHost> APPLICATION_HOST = new GenericType<ApplicationHost>(){};
    protected static final GenericType<Collection<ApplicationHost>> APPLICATION_HOSTS = new GenericType<Collection<ApplicationHost>>(){};

    protected static final GenericType<ApplicationInstance> APPLICATION_INSTANCE = new GenericType<ApplicationInstance>(){};
    protected static final GenericType<Collection<ApplicationInstance>> APPLICATION_INSTANCES = new GenericType<Collection<ApplicationInstance>>(){};

    protected static final GenericType<BrowserApplication> BROWSER_APPLICATION = new GenericType<BrowserApplication>(){};
    protected static final GenericType<Collection<BrowserApplication>> BROWSER_APPLICATIONS = new GenericType<Collection<BrowserApplication>>(){};

    protected static final GenericType<MobileApplication> MOBILE_APPLICATION = new GenericType<MobileApplication>(){};
    protected static final GenericType<Collection<MobileApplication>> MOBILE_APPLICATIONS = new GenericType<Collection<MobileApplication>>(){};

    protected static final GenericType<KeyTransaction> KEY_TRANSACTION = new GenericType<KeyTransaction>(){};
    protected static final GenericType<Collection<KeyTransaction>> KEY_TRANSACTIONS = new GenericType<Collection<KeyTransaction>>(){};

    protected static final GenericType<Plugin> PLUGIN = new GenericType<Plugin>(){};
    protected static final GenericType<Collection<Plugin>> PLUGINS = new GenericType<Collection<Plugin>>(){};

    protected static final GenericType<PluginComponent> PLUGIN_COMPONENT = new GenericType<PluginComponent>(){};
    protected static final GenericType<Collection<PluginComponent>> PLUGIN_COMPONENTS = new GenericType<Collection<PluginComponent>>(){};

    protected static final GenericType<Server> SERVER = new GenericType<Server>(){};
    protected static final GenericType<Collection<Server>> SERVERS = new GenericType<Collection<Server>>(){};

    protected static final GenericType<Collection<Metric>> METRICS = new GenericType<Collection<Metric>>(){};
    protected static final GenericType<MetricData> METRIC_DATA = new GenericType<MetricData>(){};

    protected static final GenericType<Deployment> DEPLOYMENT = new GenericType<Deployment>(){};
    protected static final GenericType<Collection<Deployment>> DEPLOYMENTS = new GenericType<Collection<Deployment>>(){};

    protected static final GenericType<Label> LABEL = new GenericType<Label>(){};
    protected static final GenericType<Collection<Label>> LABELS = new GenericType<Collection<Label>>(){};

    protected static final GenericType<PartnerAccount> PARTNER_ACCOUNT = new GenericType<PartnerAccount>(){};
    protected static final GenericType<Collection<PartnerAccount>> PARTNER_ACCOUNTS = new GenericType<Collection<PartnerAccount>>(){};

    protected static final GenericType<User> USER = new GenericType<User>(){};
    protected static final GenericType<Collection<User>> USERS = new GenericType<Collection<User>>(){};
    protected static final GenericType<Collection<PartnerUser>> PARTNER_USERS = new GenericType<Collection<PartnerUser>>(){};

    protected static final GenericType<PartnerSubscription> PARTNER_SUBSCRIPTION = new GenericType<PartnerSubscription>(){};
    protected static final GenericType<Collection<PartnerSubscription>> PARTNER_SUBSCRIPTIONS = new GenericType<Collection<PartnerSubscription>>(){};

    protected static final GenericType<UsageData> USAGE_DATA = new GenericType<UsageData>(){};

    protected static final GenericType<Monitor> MONITOR = new GenericType<Monitor>(){};
    protected static final GenericType<Collection<Monitor>> MONITORS = new GenericType<Collection<Monitor>>(){};

    protected static final GenericType<Script> SCRIPT = new GenericType<Script>(){};

    protected static final GenericType<Collection<Location>> LOCATIONS = new GenericType<Collection<Location>>(){};

    protected static final GenericType<QueryData> QUERY_DATA = new GenericType<QueryData>(){};

    protected static final GenericType<Status> STATUS = new GenericType<Status>(){};

    protected static final GenericType<Dashboard> DASHBOARD = new GenericType<Dashboard>(){};
    protected static final GenericType<Collection<Dashboard>> DASHBOARDS = new GenericType<Collection<Dashboard>>(){};

    protected HttpContext HTTP;
    private NewRelicClient client;

    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The set of API operations
     */
    public BaseFluent(HttpContext httpContext, NewRelicClient client)
    {    
        this.HTTP = httpContext;
        this.client = client;
    }
    
    /**
     * Returns the API client.
     * @return The API client
     */
    public NewRelicClient getClient()
    {    
        return this.client;
    }

    /**
     * Encode special character in query string to the URL encoded representation.
     * @param str The input string
     * @return The encoded String
     */
    public static String encode(String str)
    {
        String encodedValue = str;

        try
        {
            encodedValue = URLEncoder.encode(encodedValue, "UTF-8");

            // Spaces in NRQL queries expected to be encoded as "%20" instead of "+".
            encodedValue = encodedValue.replace("+", "%20");
        }
        catch (UnsupportedEncodingException e)
        {
            logger.severe("Failed to encode value: "+str);
        }

        return encodedValue;
    }
}
