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

//GERALD: check
//import java.util.List;
//import java.util.Map;
import java.util.logging.Logger;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.core.GenericType;
import com.opsmatters.newrelic.httpclient.ApiKeyHttpClientProvider;
import com.opsmatters.newrelic.httpclient.HttpClientProvider;

/**
 * The set of Java bindings for the New Relic REST API Service.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class NewRelicApiService extends NewRelicService
{
    private static final Logger logger = Logger.getLogger(NewRelicApiService.class.getName());
 
    /**
     * The default hostname for New Relic RPM.
     */
    public static final String DEFAULT_HOST = "api.newrelic.com";

    /**
     * Default constructor.
     */
    public NewRelicApiService()
    {
        setHostname(DEFAULT_HOST);
    }
   
    /**
     * Constructor that takes a hostname, port and provider.
     * @param hostname The hostname of the server
     * @param port The port of the server
     * @param provider The HTTP client provider
     */
    public NewRelicApiService(String hostname, int port, HttpClientProvider provider)
    {    
        super(hostname, port, provider);
    }

    /**
     * Sets the name of the host to connect to.
     * <P>
     * The default hostname is "api.newrelic.com".
     * @param hostname The name of the host
     */
    public void setHostname(String hostname)
    {
        super.setHostname(hostname);
    }

    /**
     * Returns the operations related to alert policies.
     * @return The alert policy operations
     */
    public AlertPolicyOperations alertPolicies()
    {
        checkInitialize();
        return new AlertPolicyOperations(httpContext, this);
    }

    /**
     * Returns the operations related to APM alert conditions.
     * @return The APM alert condition operations
     */
    public AlertConditionOperations alertConditions()
    {
        checkInitialize();
        return new AlertConditionOperations(httpContext, this);
    }

    /**
     * Returns the operations related to APM alert entity conditions.
     * @return The APM alert entity condition operations
     */
    public AlertEntityConditionOperations alertEntityConditions()
    {
        checkInitialize();
        return new AlertEntityConditionOperations(httpContext, this);
    }

    /**
     * Returns the operations related to NRQL alert conditions.
     * @return The NRQL alert condition operations
     */
    public NrqlAlertConditionOperations nrqlAlertConditions()
    {
        checkInitialize();
        return new NrqlAlertConditionOperations(httpContext, this);
    }

    /**
     * Returns the operations related to external service alert conditions.
     * @return The external service alert condition operations
     */
    public ExternalServiceAlertConditionOperations externalServiceAlertConditions()
    {
        checkInitialize();
        return new ExternalServiceAlertConditionOperations(httpContext, this);
    }

    /**
     * Returns the operations related to Plugins alert conditions.
     * @return The Plugins alert condition operations
     */
    public PluginsAlertConditionOperations pluginsAlertConditions()
    {
        checkInitialize();
        return new PluginsAlertConditionOperations(httpContext, this);
    }

    /**
     * Returns the operations related to Synthetics alert conditions.
     * @return The Synthetics alert condition operations
     */
    public SyntheticsAlertConditionOperations syntheticsAlertConditions()
    {
        checkInitialize();
        return new SyntheticsAlertConditionOperations(httpContext, this);
    }

    /**
     * Returns the operations related to alert channels.
     * @return The alert channel operations
     */
    public AlertChannelOperations alertChannels()
    {
        checkInitialize();
        return new AlertChannelOperations(httpContext, this);
    }

    /**
     * Returns the operations related to alert policy channels.
     * @return The alert policy channel operations
     */
    public AlertPolicyChannelOperations alertPolicyChannels()
    {
        checkInitialize();
        return new AlertPolicyChannelOperations(httpContext, this);
    }

    /**
     * Returns the operations related to alert incidents.
     * @return The alert incident operations
     */
    public AlertIncidentOperations alertIncidents()
    {
        checkInitialize();
        return new AlertIncidentOperations(httpContext, this);
    }

    /**
     * Returns the operations related to alert violations.
     * @return The alert violation operations
     */
    public AlertViolationOperations alertViolations()
    {
        checkInitialize();
        return new AlertViolationOperations(httpContext, this);
    }

    /**
     * Returns the operations related to alert events.
     * @return The alert event operations
     */
    public AlertEventOperations alertEvents()
    {
        checkInitialize();
        return new AlertEventOperations(httpContext, this);
    }

    /**
     * Returns the operations related to applications.
     * @return The application operations
     */
    public ApplicationOperations applications()
    {
        checkInitialize();
        return new ApplicationOperations(httpContext, this);
    }

    /**
     * Returns the operations related to application hosts.
     * @return The application host operations
     */
    public ApplicationHostOperations applicationHosts()
    {
        checkInitialize();
        return new ApplicationHostOperations(httpContext, this);
    }

    /**
     * Returns the operations related to application instances.
     * @return The application instance operations
     */
    public ApplicationInstanceOperations applicationInstances()
    {
        checkInitialize();
        return new ApplicationInstanceOperations(httpContext, this);
    }

    /**
     * Returns the operations related to Browser applications.
     * @return The Browser application operations
     */
    public BrowserApplicationOperations browserApplications()
    {
        checkInitialize();
        return new BrowserApplicationOperations(httpContext, this);
    }

    /**
     * Returns the operations related to Mobile applications.
     * @return The Mobile application operations
     */
    public MobileApplicationOperations mobileApplications()
    {
        checkInitialize();
        return new MobileApplicationOperations(httpContext, this);
    }

    /**
     * Returns the operations related to key transactions.
     * @return The key transaction operations
     */
    public KeyTransactionOperations keyTransactions()
    {
        checkInitialize();
        return new KeyTransactionOperations(httpContext, this);
    }

    /**
     * Returns the operations related to plugins.
     * @return The plugin operations
     */
    public PluginOperations plugins()
    {
        checkInitialize();
        return new PluginOperations(httpContext, this);
    }

    /**
     * Returns the operations related to plugin components.
     * @return The plugin component operations
     */
    public PluginComponentOperations pluginComponents()
    {
        checkInitialize();
        return new PluginComponentOperations(httpContext, this);
    }

    /**
     * Returns the operations related to servers.
     * @return The server operations
     */
    public ServerOperations servers()
    {
        checkInitialize();
        return new ServerOperations(httpContext, this);
    }

    /**
     * Returns the operations related to deployments.
     * @return The deployment component operations
     */
    public DeploymentOperations deployments()
    {
        checkInitialize();
        return new DeploymentOperations(httpContext, this);
    }

    /**
     * Returns the operations related to labels.
     * @return The label operations
     */
    public LabelOperations labels()
    {
        checkInitialize();
        return new LabelOperations(httpContext, this);
    }

    /**
     * Returns the operations related to users.
     * @return The user operations
     */
    public UserOperations users()
    {
        checkInitialize();
        return new UserOperations(httpContext, this);
    }

    /**
     * Returns the operations related to usages.
     * @return The usage operations
     */
    public UsageOperations usages()
    {
        checkInitialize();
        return new UsageOperations(httpContext, this);
    }

    /**
     * Returns a builder for the NewRelicApiService.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make NewRelicApiService construction easier.
     */
    public static class Builder
    {
        private String hostname = DEFAULT_HOST;
        private int port = DEFAULT_PORT;
        private HttpClientProvider provider = new ApiKeyHttpClientProvider("");

        /**
         * Default constructor.
         */
        public Builder()
        {
            hostname(DEFAULT_HOST);
        }

        /**
         * Sets the name of the host to connect to.
         * <P>
         * The default hostname is "api.newrelic.com".
         * @param hostname The name of the host
         * @return This object
         */
        public Builder hostname(String hostname)
        {
            this.hostname = hostname;
            return this;
        }

        /**
         * Sets the port of the host to connect to.
         * <P>
         * The default port is 443.
         * @param port The port of the host
         * @return This object
         */
        public Builder port(int port)
        {
            this.port = port;
            return this;
        }

        /**
         * Sets the API key used to authenticate the connection.
         * @param key The API key
         * @return This object
         */
        public Builder apiKey(String key)
        {
            this.provider = new ApiKeyHttpClientProvider(key);
            return this;
        }

        /**
         * Returns the configured API service instance
         * @return The API service instance
         */
        public NewRelicApiService build()
        {
            return new NewRelicApiService(hostname, port, provider);
        }
    }
}