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

import java.util.logging.Logger;
import com.opsmatters.newrelic.httpclient.ApiKeyHttpClientProvider;
import com.opsmatters.newrelic.httpclient.HttpClientProvider;
import com.opsmatters.newrelic.api.services.AlertChannelService;
import com.opsmatters.newrelic.api.services.AlertConditionService;
import com.opsmatters.newrelic.api.services.AlertEntityConditionService;
import com.opsmatters.newrelic.api.services.AlertEventService;
import com.opsmatters.newrelic.api.services.AlertIncidentService;
import com.opsmatters.newrelic.api.services.AlertPolicyService;
import com.opsmatters.newrelic.api.services.AlertPolicyChannelService;
import com.opsmatters.newrelic.api.services.AlertViolationService;
import com.opsmatters.newrelic.api.services.ApplicationService;
import com.opsmatters.newrelic.api.services.ApplicationHostService;
import com.opsmatters.newrelic.api.services.ApplicationInstanceService;
import com.opsmatters.newrelic.api.services.BrowserApplicationService;
import com.opsmatters.newrelic.api.services.DeploymentService;
import com.opsmatters.newrelic.api.services.ExternalServiceAlertConditionService;
import com.opsmatters.newrelic.api.services.KeyTransactionService;
import com.opsmatters.newrelic.api.services.LabelService;
import com.opsmatters.newrelic.api.services.MobileApplicationService;
import com.opsmatters.newrelic.api.services.NrqlAlertConditionService;
import com.opsmatters.newrelic.api.services.PluginService;
import com.opsmatters.newrelic.api.services.PluginComponentService;
import com.opsmatters.newrelic.api.services.PluginsAlertConditionService;
import com.opsmatters.newrelic.api.services.ServerService;
import com.opsmatters.newrelic.api.services.SyntheticsAlertConditionService;
import com.opsmatters.newrelic.api.services.UsageService;
import com.opsmatters.newrelic.api.services.UserService;

/**
 * Client used to invoke New Relic operations using the REST API.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class NewRelicApi extends NewRelicClient
{
    private static final Logger logger = Logger.getLogger(NewRelicApi.class.getName());
 
    /**
     * The default hostname for New Relic RPM.
     */
    public static final String DEFAULT_HOST = "api.newrelic.com";

    /**
     * Default constructor.
     */
    public NewRelicApi()
    {
        setHostname(DEFAULT_HOST);
    }
   
    /**
     * Constructor that takes a hostname, port and provider.
     * @param hostname The hostname of the server
     * @param port The port of the server
     * @param provider The HTTP client provider
     */
    public NewRelicApi(String hostname, int port, HttpClientProvider provider)
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
     * @return The alert policy service
     */
    public AlertPolicyService alertPolicies()
    {
        checkInitialize();
        return new AlertPolicyService(httpContext, this);
    }

    /**
     * Returns the operations related to APM alert conditions.
     * @return The APM alert condition service
     */
    public AlertConditionService alertConditions()
    {
        checkInitialize();
        return new AlertConditionService(httpContext, this);
    }

    /**
     * Returns the operations related to APM alert entity conditions.
     * @return The APM alert entity condition service
     */
    public AlertEntityConditionService alertEntityConditions()
    {
        checkInitialize();
        return new AlertEntityConditionService(httpContext, this);
    }

    /**
     * Returns the operations related to NRQL alert conditions.
     * @return The NRQL alert condition service
     */
    public NrqlAlertConditionService nrqlAlertConditions()
    {
        checkInitialize();
        return new NrqlAlertConditionService(httpContext, this);
    }

    /**
     * Returns the operations related to external service alert conditions.
     * @return The external service alert condition service
     */
    public ExternalServiceAlertConditionService externalServiceAlertConditions()
    {
        checkInitialize();
        return new ExternalServiceAlertConditionService(httpContext, this);
    }

    /**
     * Returns the operations related to Plugins alert conditions.
     * @return The Plugins alert condition service
     */
    public PluginsAlertConditionService pluginsAlertConditions()
    {
        checkInitialize();
        return new PluginsAlertConditionService(httpContext, this);
    }

    /**
     * Returns the operations related to Synthetics alert conditions.
     * @return The Synthetics alert condition service
     */
    public SyntheticsAlertConditionService syntheticsAlertConditions()
    {
        checkInitialize();
        return new SyntheticsAlertConditionService(httpContext, this);
    }

    /**
     * Returns the operations related to alert channels.
     * @return The alert channel service
     */
    public AlertChannelService alertChannels()
    {
        checkInitialize();
        return new AlertChannelService(httpContext, this);
    }

    /**
     * Returns the operations related to alert policy channels.
     * @return The alert policy channel service
     */
    public AlertPolicyChannelService alertPolicyChannels()
    {
        checkInitialize();
        return new AlertPolicyChannelService(httpContext, this);
    }

    /**
     * Returns the operations related to alert incidents.
     * @return The alert incident service
     */
    public AlertIncidentService alertIncidents()
    {
        checkInitialize();
        return new AlertIncidentService(httpContext, this);
    }

    /**
     * Returns the operations related to alert violations.
     * @return The alert violation service
     */
    public AlertViolationService alertViolations()
    {
        checkInitialize();
        return new AlertViolationService(httpContext, this);
    }

    /**
     * Returns the operations related to alert events.
     * @return The alert event service
     */
    public AlertEventService alertEvents()
    {
        checkInitialize();
        return new AlertEventService(httpContext, this);
    }

    /**
     * Returns the operations related to applications.
     * @return The application service
     */
    public ApplicationService applications()
    {
        checkInitialize();
        return new ApplicationService(httpContext, this);
    }

    /**
     * Returns the operations related to application hosts.
     * @return The application host service
     */
    public ApplicationHostService applicationHosts()
    {
        checkInitialize();
        return new ApplicationHostService(httpContext, this);
    }

    /**
     * Returns the operations related to application instances.
     * @return The application instance service
     */
    public ApplicationInstanceService applicationInstances()
    {
        checkInitialize();
        return new ApplicationInstanceService(httpContext, this);
    }

    /**
     * Returns the operations related to Browser applications.
     * @return The Browser application service
     */
    public BrowserApplicationService browserApplications()
    {
        checkInitialize();
        return new BrowserApplicationService(httpContext, this);
    }

    /**
     * Returns the operations related to Mobile applications.
     * @return The Mobile application service
     */
    public MobileApplicationService mobileApplications()
    {
        checkInitialize();
        return new MobileApplicationService(httpContext, this);
    }

    /**
     * Returns the operations related to key transactions.
     * @return The key transaction service
     */
    public KeyTransactionService keyTransactions()
    {
        checkInitialize();
        return new KeyTransactionService(httpContext, this);
    }

    /**
     * Returns the operations related to plugins.
     * @return The plugin service
     */
    public PluginService plugins()
    {
        checkInitialize();
        return new PluginService(httpContext, this);
    }

    /**
     * Returns the operations related to plugin components.
     * @return The plugin component service
     */
    public PluginComponentService pluginComponents()
    {
        checkInitialize();
        return new PluginComponentService(httpContext, this);
    }

    /**
     * Returns the operations related to servers.
     * @return The server service
     */
    public ServerService servers()
    {
        checkInitialize();
        return new ServerService(httpContext, this);
    }

    /**
     * Returns the operations related to deployments.
     * @return The deployment component service
     */
    public DeploymentService deployments()
    {
        checkInitialize();
        return new DeploymentService(httpContext, this);
    }

    /**
     * Returns the operations related to labels.
     * @return The label service
     */
    public LabelService labels()
    {
        checkInitialize();
        return new LabelService(httpContext, this);
    }

    /**
     * Returns the operations related to users.
     * @return The user service
     */
    public UserService users()
    {
        checkInitialize();
        return new UserService(httpContext, this);
    }

    /**
     * Returns the operations related to usages.
     * @return The usage service
     */
    public UsageService usages()
    {
        checkInitialize();
        return new UsageService(httpContext, this);
    }

    /**
     * Returns a builder for the NewRelicApi.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make NewRelicApi construction easier.
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
         * Returns the configured API client instance
         * @return The API client instance
         */
        public NewRelicApi build()
        {
            return new NewRelicApi(hostname, port, provider);
        }
    }
}