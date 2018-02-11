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

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Logger;
import org.junit.Test;
import junit.framework.Assert;
import com.google.common.base.Optional;
import org.apache.commons.lang3.ArrayUtils;
import com.opsmatters.newrelic.api.services.AlertEventService;
import com.opsmatters.newrelic.api.services.ApplicationService;
import com.opsmatters.newrelic.api.services.ApplicationHostService;
import com.opsmatters.newrelic.api.services.ApplicationInstanceService;
import com.opsmatters.newrelic.api.services.MobileApplicationService;
import com.opsmatters.newrelic.api.services.KeyTransactionService;
import com.opsmatters.newrelic.api.services.PluginService;
import com.opsmatters.newrelic.api.services.PluginComponentService;
import com.opsmatters.newrelic.api.services.ServerService;
import com.opsmatters.newrelic.api.services.DashboardService;
import com.opsmatters.newrelic.api.services.MetricParameterBuilder;
import com.opsmatters.newrelic.api.model.Entity;
import com.opsmatters.newrelic.api.model.EntityType;
import com.opsmatters.newrelic.api.model.alerts.AlertEvent;
import com.opsmatters.newrelic.api.model.alerts.AlertViolation;
import com.opsmatters.newrelic.api.model.alerts.AlertIncident;
import com.opsmatters.newrelic.api.model.alerts.policies.AlertPolicy;
import com.opsmatters.newrelic.api.model.alerts.policies.AlertPolicyChannel;
import com.opsmatters.newrelic.api.model.alerts.channels.AlertChannel;
import com.opsmatters.newrelic.api.model.alerts.channels.EmailChannel;
import com.opsmatters.newrelic.api.model.alerts.channels.SlackChannel;
import com.opsmatters.newrelic.api.model.alerts.conditions.AlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.ApmAppAlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.BrowserAlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.NrqlAlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.Term;
import com.opsmatters.newrelic.api.model.alerts.conditions.Nrql;
import com.opsmatters.newrelic.api.model.alerts.conditions.ExternalServiceAlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.ApmExternalServiceAlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.PluginsAlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.PluginId;
import com.opsmatters.newrelic.api.model.alerts.conditions.SyntheticsAlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.InfraAlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.InfraMetricAlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.InfraHostNotReportingAlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.InfraProcessRunningAlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.AlertThreshold;
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
import com.opsmatters.newrelic.api.model.accounts.User;
import com.opsmatters.newrelic.api.model.accounts.UsageData;
import com.opsmatters.newrelic.api.model.accounts.Product;
import com.opsmatters.newrelic.api.model.synthetics.Monitor;
import com.opsmatters.newrelic.api.model.synthetics.SimpleMonitor;
import com.opsmatters.newrelic.api.model.synthetics.ScriptBrowserMonitor;
import com.opsmatters.newrelic.api.model.synthetics.Script;
import com.opsmatters.newrelic.api.model.synthetics.ScriptLocation;
import com.opsmatters.newrelic.api.model.synthetics.Location;
import com.opsmatters.newrelic.api.model.insights.QueryData;
import com.opsmatters.newrelic.api.model.insights.Dashboard;
import com.opsmatters.newrelic.api.model.insights.Filter;
import com.opsmatters.newrelic.api.model.insights.widgets.Widget;
import com.opsmatters.newrelic.api.model.insights.widgets.EventChart;
import com.opsmatters.newrelic.api.model.insights.widgets.ThresholdEventChart;
import com.opsmatters.newrelic.api.model.insights.widgets.FacetChart;
import com.opsmatters.newrelic.api.model.insights.widgets.MetricLineChart;
import com.opsmatters.newrelic.api.model.insights.widgets.BreakdownMetricChart;
import com.opsmatters.newrelic.api.model.insights.widgets.TrafficLightChart;
import com.opsmatters.newrelic.api.model.insights.widgets.InventoryChart;
import com.opsmatters.newrelic.api.model.insights.widgets.Markdown;
import com.opsmatters.newrelic.api.model.insights.widgets.EventsData;
import com.opsmatters.newrelic.api.model.insights.widgets.MetricsData;
import com.opsmatters.newrelic.api.model.insights.widgets.MarkdownData;
import com.opsmatters.newrelic.api.model.insights.widgets.InventoryData;
import com.opsmatters.newrelic.api.model.insights.widgets.Threshold;
import com.opsmatters.newrelic.api.model.insights.widgets.TrafficLight;
import com.opsmatters.newrelic.api.model.insights.widgets.TrafficLightState;
import com.opsmatters.newrelic.api.model.plugins.PluginData;
import com.opsmatters.newrelic.api.model.plugins.Component;
import com.opsmatters.newrelic.api.model.plugins.MetricTimeslice;
import com.opsmatters.newrelic.api.model.Status;
import com.opsmatters.newrelic.util.Utils;

/**
 * The set of tests used for New Relic API services.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class NewRelicApiTest
{
    private static final Logger logger = Logger.getLogger(NewRelicApiTest.class.getName());

    // Get the properties
    private long accountId = Long.parseLong(System.getProperty(Constants.ACCOUNT_ID_PROPERTY, "0"));
    private String apiKey = System.getProperty(Constants.API_KEY_PROPERTY);
    private String queryKey = System.getProperty(Constants.QUERY_KEY_PROPERTY);
    private String licenseKey = System.getProperty(Constants.LICENSE_KEY_PROPERTY);

    private String policyName = "test-policy";
    private String apmConditionName = "test-apm-condition";
    private String browserConditionName = "test-browser-condition";
    private String nrqlConditionName = "test-nrql-condition";
    private String metricConditionName = "test-metric-condition";
    private String hostConditionName = "test-host-condition";
    private String processConditionName = "test-process-condition";
    private String externalServiceConditionName = "test-external-service-condition";
    private String pluginsConditionName = "test-plugins-condition";
    private String syntheticsConditionName = "test-synthetics-condition";
    private String applicationName = "test-application";
    private String browserApplicationName = "test-browser-application-"+System.currentTimeMillis();
    private String deploymentDescription = "deployment-"+System.currentTimeMillis();
    private String labelCategory = "production";
    private String labelName = "label-"+System.currentTimeMillis();
    private String simpleMonitorName = "test-simple-monitor";
    private String scriptMonitorName = "test-script-monitor";
    private String whereClause = "env=prod";
    private String email = "alerts@test.com";
    private String channel = "#slack";
    private String webhookUrl = "https://hooks.slack.com/services/T8LVC2SGM/B8M02K7yy/k7SrSQGoluE2olG3LpmH4sxx";
    private String monitorUrl = "http://google.com";
    private String monitorCategory = "Monitors";
    private String monitorLabel = "Test";
    private String insightsQuery = "SELECT average(duration) FROM PageView";
    private String dashboardName = "test-dashboard";
    private String pluginHost = "my-host";

    @Test
    public void testAlertServices()
    {
        String testName = "AlertServicesTest";
        logger.info("Starting test: "+testName);

        // Initialise the clients
        logger.info("Initialise the clients");
        NewRelicApi api = getApiClient();
        Assert.assertNotNull(api);
        NewRelicInfraApi infraApi = getInfraApiClient();
        Assert.assertNotNull(infraApi);

        // Create the browser application
        BrowserApplication browserApplication = createBrowserApplication(api, 
            getBrowserApplication(browserApplicationName));

        // Create the alert channels
        AlertChannel emailChannel = createChannel(api, getEmailChannel(email));
        AlertChannel slackChannel = createChannel(api, getSlackChannel(webhookUrl, channel));

        // Get all the alert channels
        Collection<AlertChannel> channels = getAllChannels(api);

        // Create the policy
        AlertPolicy policy = createPolicy(api);

        // Create the conditions
        AlertCondition apmCondition = createApmCondition(api, policy, 
            getApmAppCondition(policy.getId(), apmConditionName));
        AlertCondition browserCondition = createApmCondition(api, policy, 
            getBrowserCondition(policy.getId(), browserConditionName));
        NrqlAlertCondition nrqlCondition = createNrqlCondition(api, policy, 
            getNrqlCondition(policy.getId(), nrqlConditionName));
        ExternalServiceAlertCondition externalServiceCondition = createExternalServiceCondition(api, policy,
            getExternalServiceCondition(policy.getId(), externalServiceConditionName));
        PluginsAlertCondition pluginsCondition = createPluginsCondition(api, policy, 
            getPluginsCondition(policy.getId(), pluginsConditionName));
        SyntheticsAlertCondition syntheticsCondition = createSyntheticsCondition(api, policy, 
            getSyntheticsCondition(policy.getId(), syntheticsConditionName));
        InfraAlertCondition infraMetricCondition = createInfraCondition(infraApi, policy,
            getMetricCondition(policy.getId(), metricConditionName, whereClause));
        InfraAlertCondition infraHostCondition = createInfraCondition(infraApi, policy,
            getHostNotReportingCondition(policy.getId(), hostConditionName, whereClause));
        InfraAlertCondition infraProcessCondition = createInfraCondition(infraApi, policy,
            getProcessRunningCondition(policy.getId(), processConditionName, whereClause));

        // Add the channels to the policy
        addPolicyChannel(api, policy, emailChannel);
        addPolicyChannel(api, policy, slackChannel);

        // Add the entity to the condition
        addEntityCondition(api, browserApplication, browserCondition);

        // Get all the conditions
        getAllApmConditions(api, policy);
        getAllNrqlConditions(api, policy);
        getAllExternalServiceConditions(api, policy);
        getAllPluginsConditions(api, policy);
        getAllSyntheticsConditions(api, policy);
        getAllInfraConditions(infraApi, policy);

        // Get all the entity conditions
        getEntityConditions(api, browserApplication);

        // Get all the alert data
        getAlertEvents(api);
        getAlertEventsWithFilters(api);
        getAlertViolations(api);
        getAlertIncidents(api);

        // Delete the channels from the policy
        deletePolicyChannel(api, policy, emailChannel);
        deletePolicyChannel(api, policy, slackChannel);

        // Remove the entity from the condition
        removeEntityCondition(api, browserApplication, browserCondition);

        // Delete the conditions
        deleteApmCondition(api, policy, apmCondition);
        deleteApmCondition(api, policy, browserCondition);
        deleteNrqlCondition(api, policy, nrqlCondition);
        deleteExternalServiceCondition(api, policy, externalServiceCondition);
        deletePluginsCondition(api, policy, pluginsCondition);
        deleteSyntheticsCondition(api, policy, syntheticsCondition);
        deleteInfraCondition(infraApi, policy, infraMetricCondition);
        deleteInfraCondition(infraApi, policy, infraHostCondition);
        deleteInfraCondition(infraApi, policy, infraProcessCondition);

        // Delete the policy
        deletePolicy(api, policy);

        // Delete the alert channels
        deleteChannel(api, emailChannel);
        deleteChannel(api, slackChannel);

        logger.info("Completed test: "+testName);
    }

    @Test
    public void testApplicationServices()
    {
        String testName = "ApplicationServicesTest";
        logger.info("Starting test: "+testName);

        // Initialise the client
        logger.info("Initialise the client");
        NewRelicApi api = getApiClient();
        Assert.assertNotNull(api);

        // Create the browser application
        //BrowserApplication browserApplication = createBrowserApplication(api, 
        //    getBrowserApplication(browserApplicationName));
        //Collection<BrowserApplication> browserApplications = getBrowserApplications(api);

        // Get all the applications
        Collection<Application> applications = getApplications(api);
        Collection<MobileApplication> mobileApplications = getMobileApplications(api);
        Collection<KeyTransaction> keyTransactions = getKeyTransactions(api);
        Collection<Plugin> plugins = getPlugins(api);
        Collection<PluginComponent> pluginComponents = getPluginComponents(api);
        Collection<Server> servers = getServers(api);

        // Get the application metrics
        Application application = null;
        if(applications.size() > 0)
        {
            Iterator<Application> it = applications.iterator();
            //application = it.next();
            //application = it.next();
            application = it.next();
            getApplication(api, application.getId());
            updateApplication(api, getApplication(application.getId(), application.getName()));
            getApplicationMetricNames(api, application.getId());
            getApplicationMetricData(api, application.getId());

            // Create a deployment and then delete it
            Deployment deployment = createDeployment(api, application.getId(),
                getDeployment(deploymentDescription));
            getDeployments(api, application.getId());
            deleteDeployment(api, application, deployment);
        }

        Collection<ApplicationHost> applicationHosts = null;
        Collection<ApplicationInstance> applicationInstances = null;
        if(application != null)
        {
            applicationHosts = getApplicationHosts(api, application.getId());
            applicationInstances = getApplicationInstances(api, application.getId());
        }

        // Get the mobile application metrics
        if(mobileApplications.size() > 0)
        {
            Iterator<MobileApplication> it = mobileApplications.iterator();
            MobileApplication mobileApplication = it.next();
            getMobileApplication(api, mobileApplication.getId());
            getMobileApplicationMetricNames(api, mobileApplication.getId());
            getMobileApplicationMetricData(api, mobileApplication.getId());
        }

        // Get the key transactions
        if(keyTransactions != null && keyTransactions.size() > 0)
        {
            Iterator<KeyTransaction> it = keyTransactions.iterator();
            KeyTransaction keyTransaction = it.next();
            getKeyTransaction(api, keyTransaction.getId());
        }

        // Get the application host metrics
        if(application != null && applicationHosts != null && applicationHosts.size() > 0)
        {
            Iterator<ApplicationHost> it = applicationHosts.iterator();
            ApplicationHost applicationHost = it.next();
            getApplicationHost(api, application.getId(), applicationHost.getId());
            getApplicationHostMetricNames(api, application.getId(), applicationHost.getId());
            getApplicationHostMetricData(api, application.getId(), applicationHost.getId());
        }

        // Get the application instance metrics
        if(application != null && applicationInstances != null && applicationInstances.size() > 0)
        {
            Iterator<ApplicationInstance> it = applicationInstances.iterator();
            ApplicationInstance applicationInstance = it.next();
            getApplicationInstance(api, application.getId(), applicationInstance.getId());
            getApplicationInstanceMetricNames(api, application.getId(), applicationInstance.getId());
            getApplicationInstanceMetricData(api, application.getId(), applicationInstance.getId());
        }

        // Get the plugins
        if(plugins != null && plugins.size() > 0)
        {
            Iterator<Plugin> it = plugins.iterator();
            Plugin plugin = it.next();
            getPlugin(api, plugin.getId());
        }

        // Get the plugin components
        if(pluginComponents != null && pluginComponents.size() > 0)
        {
            Iterator<PluginComponent> it = pluginComponents.iterator();
            PluginComponent pluginComponent = it.next();
            getPluginComponent(api, pluginComponent.getId());
            getPluginComponentMetricNames(api, pluginComponent.getId());
            getPluginComponentMetricData(api, pluginComponent.getId());
        }

        // Get the server metrics
        if(servers.size() > 0)
        {
            Iterator<Server> it = servers.iterator();
            Server server = it.next();
            getServer(api, server.getId());
            updateServer(api, getServer(server.getId(), server.getName()));
            getServerMetricNames(api, server.getId());
            getServerMetricData(api, server.getId());
        }

        // Create the label
        //Label label = createLabel(api, getLabel(labelCategory, labelName)); // gives 500 Internal Server Error
        Collection<Label> labels = getLabels(api);
        if(labels.size() > 0)
        {
            Iterator<Label> it = labels.iterator();
            Label label = it.next();
            label = getLabel(api, label.getKey());
            deleteLabel(api, label);
        }

        logger.info("Completed test: "+testName);
    }

    @Test
    public void testUserServices()
    {
        String testName = "UserServicesTest";
        logger.info("Starting test: "+testName);

        // Initialise the client
        logger.info("Initialise the client");
        NewRelicApi api = getApiClient();
        Assert.assertNotNull(api);

        // Get all the users
        Collection<User> users = getUsers(api);

        // Get the first user
        if(users.size() > 0)
        {
            Iterator<User> it = users.iterator();
            User user = it.next();
            getUser(api, user.getId());
            //resetUser(api, user.getId());
        }

        logger.info("Completed test: "+testName);
    }

    @Test
    public void testUsageServices()
    {
        String testName = "UsageServicesTest";
        logger.info("Starting test: "+testName);

        // Initialise the clients
        logger.info("Initialise the client");
        NewRelicApi api = getApiClient();
        Assert.assertNotNull(api);

        // Get the usage
        UsageData usage = getUsage(api, "apm");

        logger.info("Completed test: "+testName);
    }

    @Test
    public void testSyntheticsServices()
    {
        String testName = "SyntheticsServicesTest";
        logger.info("Starting test: "+testName);

        // Initialise the client
        logger.info("Initialise the client");
        NewRelicSyntheticsApi api = getSyntheticsApiClient();
        Assert.assertNotNull(api);

        // Get all the locations
        Collection<Location> locations = getLocations(api);
        Location location = locations.iterator().next();

        // Create a label for the monitors
        Label label = getMonitorLabel(monitorCategory, monitorLabel);

        // Create the simple monitor
        Monitor simpleMonitor = createMonitor(api, 
            getSimpleMonitor(simpleMonitorName, monitorUrl, location));

        // Update the simple monitor
        simpleMonitor.setName(simpleMonitor.getName()+"-updated");
        simpleMonitor.setFrequency(Monitor.Frequency.MINUTES_15);
        updateMonitor(api, simpleMonitor);

        // Patch the simple monitor
        SimpleMonitor patchSimpleMonitor = new SimpleMonitor();
        patchSimpleMonitor.setId(simpleMonitor.getId());
        patchSimpleMonitor.setType((String)null); // "type" causes error in PATCH
        patchSimpleMonitor.setName(simpleMonitor.getName()+"-patched");
        patchSimpleMonitor.setFrequency(Monitor.Frequency.MINUTES_30);
        patchSimpleMonitor.setLocations(simpleMonitor.getLocations());
        patchMonitor(api, patchSimpleMonitor);

        // Add the label to the simple monitor
        addMonitorLabel(api, simpleMonitor, label);

        // Create the scripted monitor
        Monitor scriptMonitor = createMonitor(api, 
            getScriptMonitor(scriptMonitorName, monitorUrl, location));
        Script script = updateMonitorScript(api, scriptMonitor, getScript());

        // Update the scripted monitor
        scriptMonitor.setName(scriptMonitor.getName()+"-updated");
        scriptMonitor.setFrequency(Monitor.Frequency.MINUTES_15);
        updateMonitor(api, scriptMonitor);

        // Patch the scripted monitor
        ScriptBrowserMonitor patchScriptMonitor = new ScriptBrowserMonitor();
        patchScriptMonitor.setId(scriptMonitor.getId());
        patchScriptMonitor.setType((String)null); // "type" causes error in PATCH
        patchScriptMonitor.setName(scriptMonitor.getName()+"-patched");
        patchScriptMonitor.setFrequency(Monitor.Frequency.MINUTES_30);
        patchScriptMonitor.setLocations(scriptMonitor.getLocations());
        patchMonitor(api, patchScriptMonitor);

        // Add the label to the scripted monitor
        addMonitorLabel(api, scriptMonitor, label);

        // Get all the monitors
        Collection<Monitor> monitors = getMonitors(api);

        // Get all the monitors for the label
        Collection<Monitor> labelMonitors = getMonitorsForLabel(api, label);

        // Delete the label from the monitors
        deleteMonitorLabel(api, simpleMonitor, label);
        deleteMonitorLabel(api, scriptMonitor, label);

        // Delete all the monitors
        deleteMonitor(api, simpleMonitor);
        deleteMonitor(api, scriptMonitor);

        logger.info("Completed test: "+testName);
    }

    @Test
    public void testInsightsServices()
    {
        String testName = "InsightsServicesTest";
        logger.info("Starting test: "+testName);

        // Initialise the client
        logger.info("Initialise the client");
        NewRelicInsightsApi api = getInsightsApiClient();
        Assert.assertNotNull(api);

        // Get the results of the query
        QueryData data = getQueryResult(api, accountId, insightsQuery);
        logger.info("Query data: "+data.getResults());
        Assert.assertTrue(data.getResults().size() > 0);

        logger.info("Completed test: "+testName);
    }

    @Test
    public void testPluginsServices()
    {
        String testName = "PluginsServicesTest";
        logger.info("Starting test: "+testName);

        // Initialise the client
        logger.info("Initialise the client");
        NewRelicPluginsApi api = getPluginsApiClient();
        Assert.assertNotNull(api);

        // Send the metric data
        sendPluginData(api, getPluginData(pluginHost));

        logger.info("Completed test: "+testName);
    }

    @Test
    public void testDashboardsServices()
    {
        String testName = "DashboardsServicesTest";
        logger.info("Starting test: "+testName);

        // Initialise the client
        logger.info("Initialise the client");
        NewRelicApi api = getApiClient();
        Assert.assertNotNull(api);

        // Get all the applications
        Collection<Application> applications = getApplications(api);
        Application application = applications.iterator().next();

        // Create the dashboards
        Dashboard dashboard = createDashboard(api, 
            getDashboard(dashboardName));

        // Add widgets to the chart
        dashboard.addWidget(getMarkdown());
        dashboard.addWidget(getEventChart());
        dashboard.addWidget(getThresholdEventChart());
        dashboard.addWidget(getFacetPieChart(dashboard.getId()));
        dashboard.addWidget(getFacetBarChart(dashboard.getId()));
        dashboard.addWidget(getMetricLineChart(application.getId()));
        dashboard.addWidget(getBreakdownMetricChart(application.getId()));
        dashboard.addWidget(getTrafficLightChart());
        dashboard.addWidget(getInventoryChart());
        updateDashboard(api, dashboard);

        // Get all the dashboards
        Collection<Dashboard> dashboards = getDashboards(api);

        Collection<Dashboard> filteredDashboards = getDashboardsWithFilter(api, dashboardName);

        // Delete the dashboards
        deleteDashboard(api, dashboard);

        logger.info("Completed test: "+testName);
    }

    public NewRelicApi getApiClient()
    {
        return NewRelicApi.builder()
            .apiKey(apiKey)
            .build();
	}

    public NewRelicInfraApi getInfraApiClient()
    {
        return NewRelicInfraApi.builder()
            .apiKey(apiKey)
            .build();
	}

    public NewRelicSyntheticsApi getSyntheticsApiClient()
    {
        return NewRelicSyntheticsApi.builder()
            .apiKey(apiKey)
            .build();
	}

    public NewRelicInsightsApi getInsightsApiClient()
    {
        return NewRelicInsightsApi.builder()
            .queryKey(queryKey)
            .build();
	}

    public NewRelicPluginsApi getPluginsApiClient()
    {
        return NewRelicPluginsApi.builder()
            .licenseKey(licenseKey)
            .build();
	}

    public AlertPolicy getPolicy(String name)
    {
        return AlertPolicy.builder()
            .name(name)
            .perPolicyIncidentPreference()
            .build();
    }

    public AlertPolicy createPolicy(NewRelicApi api)
    {
        // Create the policy
        logger.info("Create policy: "+policyName);
        AlertPolicy input = getPolicy(policyName);
        AlertPolicy policy = api.alertPolicies().create(input).get();
        Assert.assertNotNull(policy);

        // Get the policy
        {
            logger.info("Get policy: "+policy.getId()+"-"+policy.getName());
            Optional<AlertPolicy> ret = api.alertPolicies().show(policy.getName(), policy.getId());
            Assert.assertTrue(ret.isPresent());
        }

        return policy;
    }

    public void deletePolicy(NewRelicApi api, AlertPolicy policy)
    {
        logger.info("Delete policy: "+policy.getId());
        api.alertPolicies().delete(policy.getId());
        Optional<AlertPolicy> ret = api.alertPolicies().show(policy.getName(), policy.getId());
        Assert.assertFalse(ret.isPresent());
    }

    public AlertCondition getApmAppCondition(long policyId, String name)
    {
        Term term = Term.builder()
            .duration(Term.Duration.MINUTES_10)
            .criticalPriority()
            .aboveOperator()
            .allTimeFunction()
            .threshold(1)
            .build();

        return ApmAppAlertCondition.builder()
            .name(name)
            .metric(ApmAppAlertCondition.Metric.APDEX)
            .applicationConditionScope()
            .addTerm(term)
            .enabled(true)
            .build();
    }

    public AlertCondition getBrowserCondition(long policyId, String name)
    {
        Term term = Term.builder()
            .duration(Term.Duration.MINUTES_10)
            .criticalPriority()
            .aboveOperator()
            .allTimeFunction()
            .threshold(1)
            .build();

        return BrowserAlertCondition.builder()
            .name(name)
            .metric(BrowserAlertCondition.Metric.END_USER_APDEX)
            .applicationConditionScope()
            .addTerm(term)
            .enabled(true)
            .build();
    }

    public AlertCondition createApmCondition(NewRelicApi api, AlertPolicy policy, AlertCondition input)
    {
        logger.info("Create APM condition: "+apmConditionName);
        AlertCondition condition = api.alertConditions().create(policy.getId(), input).get();
        Assert.assertNotNull(condition);
        Assert.assertTrue(getApmCondition(api, policy, condition).isPresent());
        return condition;
    }

    public void deleteApmCondition(NewRelicApi api, AlertPolicy policy, AlertCondition condition)
    {
        logger.info("Delete APM condition: "+condition.getId());
        api.alertConditions().delete(condition.getId());
        Assert.assertFalse(getApmCondition(api, policy, condition).isPresent());
    }

    public Optional<AlertCondition> getApmCondition(NewRelicApi api, AlertPolicy policy, AlertCondition condition)
    {
        Optional<AlertCondition> ret = Optional.absent();

        try
        {
            logger.info("Get APM condition: "+condition.getId());
            ret = api.alertConditions().show(policy.getId(), condition.getId());
        }
        catch(RuntimeException e)
        {
        }

        return ret;
    }

    public Collection<AlertCondition> getAllApmConditions(NewRelicApi api, AlertPolicy policy)
    {
        logger.info("Get all APM conditions for policy: "+policy.getId());
        Collection<AlertCondition> ret = api.alertConditions().list(policy.getId());
        Assert.assertTrue(ret.size() > 0);
        return ret;
    }

    public NrqlAlertCondition getNrqlCondition(long policyId, String name)
    {
        Term term = Term.builder()
            .duration(Term.Duration.MINUTES_10)
            .criticalPriority()
            .aboveOperator()
            .allTimeFunction()
            .threshold(1)
            .build();

        Nrql nrql = Nrql.builder()
            .query("SELECT average(cpuPercent) from ProcessSample WHERE hostname like 'ip-%'")
            .sinceValue(3)
            .build();

        return NrqlAlertCondition.builder()
            .name(name)
            .singleValueFunction()
            .addTerm(term)
            .nrql(nrql)
            .enabled(true)
            .build();
    }

    public NrqlAlertCondition createNrqlCondition(NewRelicApi api, AlertPolicy policy, NrqlAlertCondition input)
    {
        logger.info("Create NRQL condition: "+input.getName());
        NrqlAlertCondition condition = api.nrqlAlertConditions().create(policy.getId(), input).get();
        Assert.assertNotNull(condition);
        Assert.assertTrue(getNrqlCondition(api, policy, condition).isPresent());
        return condition;
    }

    public void deleteNrqlCondition(NewRelicApi api, AlertPolicy policy, NrqlAlertCondition condition)
    {
        logger.info("Delete NRQL condition: "+condition.getId());
        api.nrqlAlertConditions().delete(condition.getId());
        Assert.assertFalse(getNrqlCondition(api, policy, condition).isPresent());
    }

    public Optional<NrqlAlertCondition> getNrqlCondition(NewRelicApi api, AlertPolicy policy, NrqlAlertCondition condition)
    {
        Optional<NrqlAlertCondition> ret = Optional.absent();

        try
        {
            logger.info("Get NRQL condition: "+condition.getId());
            ret = api.nrqlAlertConditions().show(policy.getId(), condition.getId());
        }
        catch(RuntimeException e)
        {
        }

        return ret;
    }

    public Collection<NrqlAlertCondition> getAllNrqlConditions(NewRelicApi api, AlertPolicy policy)
    {
        logger.info("Get all NRQL conditions for policy: "+policy.getId());
        Collection<NrqlAlertCondition> ret = api.nrqlAlertConditions().list(policy.getId());
        Assert.assertTrue(ret.size() > 0);
        return ret;
    }

    public ExternalServiceAlertCondition getExternalServiceCondition(long policyId, String name)
    {
        Term term = Term.builder()
            .duration(Term.Duration.MINUTES_10)
            .criticalPriority()
            .aboveOperator()
            .allTimeFunction()
            .threshold(5)
            .build();

        return ApmExternalServiceAlertCondition.builder()
            .name(name)
            .metric(ApmExternalServiceAlertCondition.Metric.RESPONSE_TIME_AVERAGE)
            .externalServiceUrl("example.com")
            .addTerm(term)
            .enabled(true)
            .build();
    }

    public ExternalServiceAlertCondition createExternalServiceCondition(NewRelicApi api, AlertPolicy policy, ExternalServiceAlertCondition input)
    {
        logger.info("Create external service condition: "+input.getName());
        ExternalServiceAlertCondition condition = api.externalServiceAlertConditions().create(policy.getId(), input).get();
        Assert.assertNotNull(condition);
        Assert.assertTrue(getExternalServiceCondition(api, policy, condition).isPresent());
        return condition;
    }

    public void deleteExternalServiceCondition(NewRelicApi api, AlertPolicy policy, ExternalServiceAlertCondition condition)
    {
        logger.info("Delete external service condition: "+condition.getId());
        api.externalServiceAlertConditions().delete(condition.getId());
        Assert.assertFalse(getExternalServiceCondition(api, policy, condition).isPresent());
    }

    public Optional<ExternalServiceAlertCondition> getExternalServiceCondition(NewRelicApi api, AlertPolicy policy, ExternalServiceAlertCondition condition)
    {
        Optional<ExternalServiceAlertCondition> ret = Optional.absent();

        try
        {
            logger.info("Get external service condition: "+condition.getId());
            ret = api.externalServiceAlertConditions().show(policy.getId(), condition.getId());
        }
        catch(RuntimeException e)
        {
        }

        return ret;
    }

    public Collection<ExternalServiceAlertCondition> getAllExternalServiceConditions(NewRelicApi api, AlertPolicy policy)
    {
        logger.info("Get all external service conditions for policy: "+policy.getId());
        Collection<ExternalServiceAlertCondition> ret = api.externalServiceAlertConditions().list(policy.getId());
        Assert.assertTrue(ret.size() > 0);
        return ret;
    }

    public PluginsAlertCondition getPluginsCondition(long policyId, String name)
    {
        Term term = Term.builder()
            .duration(Term.Duration.MINUTES_10)
            .criticalPriority()
            .aboveOperator()
            .allTimeFunction()
            .threshold(5)
            .build();

        PluginId plugin = PluginId.builder()
            .id("12345")
            .guid("12345-12345")
            .build();

        return PluginsAlertCondition.builder()
            .name(name)
            .metric("test-metric")
            .metricDescription("test-metric-description")
            .averageValueFunction()
            .addTerm(term)
            .plugin(plugin)
            .addEntity(54321)
            .enabled(true)
            .build();
    }

    public PluginsAlertCondition createPluginsCondition(NewRelicApi api, AlertPolicy policy, PluginsAlertCondition input)
    {
        logger.info("Create Plugins condition: "+input.getName());
        PluginsAlertCondition condition = api.pluginsAlertConditions().create(policy.getId(), input).get();
        Assert.assertNotNull(condition);
        Assert.assertTrue(getPluginsCondition(api, policy, condition).isPresent());
        return condition;
    }

    public void deletePluginsCondition(NewRelicApi api, AlertPolicy policy, PluginsAlertCondition condition)
    {
        logger.info("Delete Plugins condition: "+condition.getId());
        api.pluginsAlertConditions().delete(condition.getId());
        Assert.assertFalse(getPluginsCondition(api, policy, condition).isPresent());
    }

    public Optional<PluginsAlertCondition> getPluginsCondition(NewRelicApi api, AlertPolicy policy, PluginsAlertCondition condition)
    {
        Optional<PluginsAlertCondition> ret = Optional.absent();

        try
        {
            logger.info("Get Plugins condition: "+condition.getId());
            ret = api.pluginsAlertConditions().show(policy.getId(), condition.getId());
        }
        catch(RuntimeException e)
        {
        }

        return ret;
    }

    public Collection<PluginsAlertCondition> getAllPluginsConditions(NewRelicApi api, AlertPolicy policy)
    {
        logger.info("Get all Plugins conditions for policy: "+policy.getId());
        Collection<PluginsAlertCondition> ret = api.pluginsAlertConditions().list(policy.getId());
        Assert.assertTrue(ret.size() > 0);
        return ret;
    }

    public SyntheticsAlertCondition getSyntheticsCondition(long policyId, String name)
    {
        return SyntheticsAlertCondition.builder()
            .name(name)
            .monitorId("b308d1cb-9cbc-4d21-a93f-d8652392f1c6")
            .enabled(true)
            .build();
    }

    public SyntheticsAlertCondition createSyntheticsCondition(NewRelicApi api, AlertPolicy policy, SyntheticsAlertCondition input)
    {
        logger.info("Create Synthetics condition: "+input.getName());
        SyntheticsAlertCondition condition = api.syntheticsAlertConditions().create(policy.getId(), input).get();
        Assert.assertNotNull(condition);
        Assert.assertTrue(getSyntheticsCondition(api, policy, condition).isPresent());
        return condition;
    }

    public void deleteSyntheticsCondition(NewRelicApi api, AlertPolicy policy, SyntheticsAlertCondition condition)
    {
        logger.info("Delete Synthetics condition: "+condition.getId());
        api.syntheticsAlertConditions().delete(condition.getId());
        Assert.assertFalse(getSyntheticsCondition(api, policy, condition).isPresent());
    }

    public Optional<SyntheticsAlertCondition> getSyntheticsCondition(NewRelicApi api, AlertPolicy policy, SyntheticsAlertCondition condition)
    {
        Optional<SyntheticsAlertCondition> ret = Optional.absent();

        try
        {
            logger.info("Get Synthetics condition: "+condition.getId());
            ret = api.syntheticsAlertConditions().show(policy.getId(), condition.getId());
        }
        catch(RuntimeException e)
        {
        }

        return ret;
    }

    public Collection<SyntheticsAlertCondition> getAllSyntheticsConditions(NewRelicApi api, AlertPolicy policy)
    {
        logger.info("Get all Synthetics conditions for policy: "+policy.getId());
        Collection<SyntheticsAlertCondition> ret = api.syntheticsAlertConditions().list(policy.getId());
        Assert.assertTrue(ret.size() > 0);
        return ret;
    }

    public InfraAlertCondition getMetricCondition(long policyId, String name, String whereClause)
    {
        return InfraMetricAlertCondition.builder()
            .policyId(policyId)
            .name(name)
            .storageEventType()
            .selectValue("diskUsedPercent")
            .criticalThreshold(AlertThreshold.builder().durationMinutes(10).value(80).allTimeFunction().build())
            .aboveComparison()
            .whereClause(whereClause)
            .enabled(true)
            .build();
    }

    public InfraAlertCondition getHostNotReportingCondition(long policyId, String name, String whereClause)
    {
        return InfraHostNotReportingAlertCondition.builder()
            .policyId(policyId)
            .name(name)
            .criticalThreshold(new AlertThreshold(10))
            .whereClause(whereClause)
            .enabled(true)
            .build();
    }

    public InfraAlertCondition getProcessRunningCondition(long policyId, String name, String whereClause)
    {
        return InfraProcessRunningAlertCondition.builder()
            .policyId(policyId)
            .name(name)
            .criticalThreshold(AlertThreshold.builder().durationMinutes(10).value(0).build())
            .equalComparison()
            .processWhereClause("(commandName = 'java')")
            .whereClause(whereClause)
            .enabled(true)
            .build();
    }

    public InfraAlertCondition createInfraCondition(NewRelicInfraApi api, AlertPolicy policy, InfraAlertCondition input)
    {
        // Create the infra condition
        logger.info("Create infra condition: "+input.getName());
        InfraAlertCondition condition = api.infraAlertConditions().create(input).get();
        Assert.assertNotNull(condition);
        Assert.assertTrue(getInfraCondition(api, policy, condition).isPresent());
        return condition;
    }

    public void deleteInfraCondition(NewRelicInfraApi api, AlertPolicy policy, InfraAlertCondition condition)
    {
        logger.info("Delete infra condition: "+condition.getId());
        api.infraAlertConditions().delete(condition.getId());
        Assert.assertFalse(getInfraCondition(api, policy, condition).isPresent());
    }

    public Optional<InfraAlertCondition> getInfraCondition(NewRelicInfraApi api, AlertPolicy policy, InfraAlertCondition condition)
    {
        Optional<InfraAlertCondition> ret = Optional.absent();

        try
        {
            logger.info("Get infra condition: "+condition.getId());
            ret = api.infraAlertConditions().show(condition.getId());
        }
        catch(RuntimeException e)
        {
        }

        return ret;
    }

    public Collection<InfraAlertCondition> getAllInfraConditions(NewRelicInfraApi api, AlertPolicy policy)
    {
        logger.info("Get all infra conditions for policy: "+policy.getId());
        Collection<InfraAlertCondition> ret = api.infraAlertConditions().list(policy.getId());
        Assert.assertTrue(ret.size() > 0);
        return ret;
    }

    public AlertChannel getEmailChannel(String email)
    {
        return EmailChannel.builder()
            .name(email)
            .recipients(email)
            .includeJsonAttachment(true)
            .build();
    }

    public AlertChannel getSlackChannel(String url, String channel)
    {
        return SlackChannel.builder()
            .name(channel)
            .channel(channel)
            .url(url)
            .build();
    }

    public AlertChannel createChannel(NewRelicApi api, AlertChannel input)
    {
        logger.info("Create alert channel: "+input.getName());
        AlertChannel channel = api.alertChannels().create(input).get();

        // Get the alert channel
        {
            logger.info("Get alert channel: "+channel.getId());
            Optional<AlertChannel> ret = api.alertChannels().show(channel.getId());
            Assert.assertTrue(ret.isPresent());
        }

        return channel;
    }

    public Collection<AlertChannel> getAllChannels(NewRelicApi api)
    {
        logger.info("Get all alert channels: ");
        Collection<AlertChannel> ret = api.alertChannels().list();
        Assert.assertTrue(ret.size() > 0);
        return ret;
    }

    public void deleteChannel(NewRelicApi api, AlertChannel channel)
    {
        logger.info("Delete alert channel: "+channel.getId());
        api.alertChannels().delete(channel.getId());
        Optional<AlertChannel> ret = api.alertChannels().show(channel.getId());
        Assert.assertTrue(!ret.isPresent());
    }

    public void addPolicyChannel(NewRelicApi api, AlertPolicy policy, AlertChannel channel)
    {
        logger.info("Adding alert channel: "+channel.getId()+" to policy: "+policy.getId());
        Optional<AlertPolicyChannel> ret = api.alertPolicyChannels().update(policy.getId(), channel.getId());
        Assert.assertTrue(ret.isPresent() && ArrayUtils.contains(ret.get().getChannelIdArray(), channel.getId()));
    }

    public void deletePolicyChannel(NewRelicApi api, AlertPolicy policy, AlertChannel channel)
    {
        logger.info("Deleting alert channel: "+channel.getId()+" from policy: "+policy.getId());
        api.alertPolicyChannels().delete(policy.getId(), channel.getId());
    }

    public void addEntityCondition(NewRelicApi api, Entity entity, AlertCondition condition)
    {
        logger.info("Adding entity condition: "+condition.getId()+" to entity: "+entity.getId());
        Optional<AlertCondition> ret = api.alertEntityConditions().add(entity, condition.getId());
        Assert.assertTrue(ret.isPresent() && ArrayUtils.contains(ret.get().getEntitiesArray(), entity.getId()));
    }

    public Collection<AlertCondition> getEntityConditions(NewRelicApi api, Entity entity)
    {
        logger.info("Get conditions for entity: "+entity.getId());
        Collection<AlertCondition> ret = api.alertEntityConditions().list(entity);
        Assert.assertTrue(ret.size() > 0);
        return ret;
    }

    public void removeEntityCondition(NewRelicApi api, Entity entity, AlertCondition condition)
    {
        logger.info("Removing entity condition: "+condition.getId()+" from entity: "+entity.getId());
        api.alertEntityConditions().remove(entity, condition.getId());
    }

    public Collection<AlertEvent> getAlertEvents(NewRelicApi api)
    {

        Collection<AlertEvent> ret = null;

        try
        {
            logger.info("Get alert events: ");
            ret = api.alertEvents().list();
        }
        catch(RuntimeException e)
        {
            // Throws 404 error if no events found
            logger.warning("Error in get alert events: "+e.getMessage());
        }
        catch(Exception e)
        {
            Assert.fail("Error in get alert events: "+e.getMessage());
        }

        return ret;
    }

    public Collection<AlertEvent> getAlertEventsWithFilters(NewRelicApi api)
    {

        Collection<AlertEvent> ret = null;

        try
        {
            List<String> filters = AlertEventService.filters()
                //.product(Product.INFRASTRUCTURE)
                .entityType(EntityType.HOST)
                //.eventType(AlertEvent.EventType.NOTIFICATION)
                .eventType(AlertEvent.EventType.VIOLATION_OPEN)
                .build();
            logger.info("Get alert events with filters: "+filters);
            ret = api.alertEvents().list(filters);
        }
        catch(RuntimeException e)
        {
            // Throws 404 error if no events found
            logger.warning("Error in alert events: "+e.getMessage());
        }
        catch(Exception e)
        {
            Assert.fail("Error in get alert events with filters: "+e.getMessage());
        }

        return ret;
    }

    public Collection<AlertViolation> getAlertViolations(NewRelicApi api)
    {
        Collection<AlertViolation> ret = null;

        try
        {
            logger.info("Get alert violations: ");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -7); // violations for the last week
            long startDate = c.getTimeInMillis();
            long endDate = System.currentTimeMillis();
            ret = api.alertViolations().list(startDate, endDate, false);
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get alert violations: "+e.getMessage());
        }

        return ret;
    }

    public Collection<AlertIncident> getAlertIncidents(NewRelicApi api)
    {
        Collection<AlertIncident> ret = null;

        try
        {
            logger.info("Get alert incidents: ");
            ret = api.alertIncidents().list(false);
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get alert incidents: "+e.getMessage());
        }

        return ret;
    }

    public Application getApplication(long id, String name)
    {
        return Application.builder()
            .id(id)
            .name(name)
            .appApdexThreshold(0.5f)
            .endUserApdexThreshold(7.0f)
            .enableRealUserMonitoring(true)
            .build();
    }

    public Application updateApplication(NewRelicApi api, Application input)
    {
        logger.info("Update application: "+input.getName());
        Application application = api.applications().update(input).get();
        Assert.assertNotNull(application);
        return application;
    }

    public Application getApplication(NewRelicApi api, long id)
    {
        logger.info("Get application: "+id);
        Application ret = api.applications().show(id).get();
        Assert.assertNotNull(ret);
        return ret;
    }

    public Collection<Application> getApplications(NewRelicApi api)
    {
        Collection<Application> ret = null;

        try
        {
            logger.info("Get applications: ");
            List<String> filters = ApplicationService.filters()
                .language("java")
                .build();
            ret = api.applications().list(filters);
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get applications: "+e.getMessage());
        }

        return ret;
    }

    public Collection<Metric> getApplicationMetricNames(NewRelicApi api, long id)
    {
        logger.info("Get application metric names: "+id);
        Collection<Metric> metrics = api.applications().metricNames(id, "Threads/SummaryState/");
        Assert.assertTrue(metrics.size() > 0);
        return metrics;
    }

    public MetricData getApplicationMetricData(NewRelicApi api, long id)
    {
        logger.info("Get application metric data: "+id);
        List<String> parameters = MetricParameterBuilder.builder()
            .names("Threads/SummaryState/RUNNABLE/Count")
            .names("Threads/SummaryState/BLOCKED/Count")
            .values("average_response_time")
            .values("calls_per_minute")
            .from(System.currentTimeMillis()-(3600*1000L)) // last 1 hour
            .to(System.currentTimeMillis())
            .summarize(true)
            .build();

        MetricData metrics = api.applications().metricData(id, parameters).get();
        //Assert.assertTrue(metrics.getMetrics().size() > 0);
        return metrics;
    }

    public BrowserApplication getBrowserApplication(String name)
    {
        return BrowserApplication.builder()
            .name(name)
            .build();
    }

    public BrowserApplication createBrowserApplication(NewRelicApi api, BrowserApplication input)
    {
        logger.info("Create browser application: "+input.getName());
        BrowserApplication application = api.browserApplications().create(input).get();

        // Get the browser application
        {
            logger.info("Get browser applications: "+application.getId());
            Optional<BrowserApplication> ret = api.browserApplications().show(application.getId());
            Assert.assertTrue(ret.isPresent());
        }

        return application;
    }

    public Collection<BrowserApplication> getBrowserApplications(NewRelicApi api)
    {
        logger.info("Get all browser applications: ");
        Collection<BrowserApplication> ret = api.browserApplications().list();
        Assert.assertTrue(ret.size() > 0);
        return ret;
    }

    public MobileApplication getMobileApplication(NewRelicApi api, long id)
    {
        logger.info("Get mobile application: "+id);
        MobileApplication ret = api.mobileApplications().show(id).get();
        Assert.assertNotNull(ret);
        return ret;
    }

    public Collection<MobileApplication> getMobileApplications(NewRelicApi api)
    {
        Collection<MobileApplication> ret = null;

        try
        {
            logger.info("Get mobile applications: ");
            ret = api.mobileApplications().list();
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get mobile applications: "+e.getMessage());
        }

        return ret;
    }

    public Collection<Metric> getMobileApplicationMetricNames(NewRelicApi api, long id)
    {
        logger.info("Get mobile application metric names: "+id);
        Collection<Metric> metrics = api.mobileApplications().metricNames(id, "Threads/SummaryState/");
        Assert.assertTrue(metrics.size() > 0);
        return metrics;
    }

    public MetricData getMobileApplicationMetricData(NewRelicApi api, long id)
    {
        logger.info("Get mobile application metric data: "+id);
        List<String> parameters = MetricParameterBuilder.builder()
            .names("Mobile/Crash/All")
            .names("Session/Start")
            .values("call_count")
            .from(System.currentTimeMillis()-(3600*1000L)) // last 1 hour
            .to(System.currentTimeMillis())
            .summarize(true)
            .build();

        MetricData metrics = api.mobileApplications().metricData(id, parameters).get();
        Assert.assertTrue(metrics.getMetrics().size() > 0);
        return metrics;
    }

    public KeyTransaction getKeyTransaction(NewRelicApi api, long id)
    {
        logger.info("Get key transaction: "+id);
        KeyTransaction ret = api.keyTransactions().show(id).get();
        Assert.assertNotNull(ret);
        return ret;
    }

    public Collection<KeyTransaction> getKeyTransactions(NewRelicApi api)
    {
        Collection<KeyTransaction> ret = null;

        try
        {
            logger.info("Get key transactions: ");
            List<String> filters = KeyTransactionService.filters()
                .name("/agent")
                .build();
            ret = api.keyTransactions().list(filters);
        }
        catch(RuntimeException e)
        {
            if(e.getMessage().indexOf("403 Forbidden") == -1) // requires full access
                Assert.fail("Error in get key transactions: "+e.getMessage());
        }

        return ret;
    }

    public ApplicationHost getApplicationHost(NewRelicApi api, long applicationId, long id)
    {
        logger.info("Get application host: "+id+" for application: "+applicationId);
        ApplicationHost ret = api.applicationHosts().show(applicationId, id).get();
        Assert.assertNotNull(ret);
        return ret;
    }

    public Collection<ApplicationHost> getApplicationHosts(NewRelicApi api, long applicationId)
    {
        Collection<ApplicationHost> ret = null;

        try
        {
            logger.info("Get application hosts: ");
            List<String> filters = ApplicationHostService.filters()
                .hostname("host")
                .build();
            ret = api.applicationHosts().list(applicationId, filters);
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get application hosts: "+e.getMessage());
        }

        return ret;
    }

    public Collection<Metric> getApplicationHostMetricNames(NewRelicApi api, long applicationId, long id)
    {
        logger.info("Get application host metric names: "+id);
        Collection<Metric> metrics = api.applicationHosts().metricNames(applicationId, id, "Threads/SummaryState/");
        Assert.assertTrue(metrics.size() > 0);
        return metrics;
    }

    public MetricData getApplicationHostMetricData(NewRelicApi api, long applicationId, long id)
    {
        logger.info("Get application host metric data: "+id);
        List<String> parameters = MetricParameterBuilder.builder()
            .names("Threads/SummaryState/RUNNABLE/Count")
            .values("call_count")
            .from(System.currentTimeMillis()-(3600*1000L)) // last 1 hour
            .to(System.currentTimeMillis())
            .summarize(true)
            .build();

        MetricData metrics = api.applicationHosts().metricData(applicationId, id, parameters).get();
        Assert.assertTrue(metrics.getMetrics().size() > 0);
        return metrics;
    }

    public ApplicationInstance getApplicationInstance(NewRelicApi api, long applicationId, long id)
    {
        logger.info("Get application instance: "+id+" for application: "+applicationId);
        ApplicationInstance ret = api.applicationInstances().show(applicationId, id).get();
        Assert.assertNotNull(ret);
        return ret;
    }

    public Collection<ApplicationInstance> getApplicationInstances(NewRelicApi api, long applicationId)
    {
        Collection<ApplicationInstance> ret = null;

        try
        {
            logger.info("Get application instances: ");
            List<String> filters = ApplicationInstanceService.filters()
                .hostname("host")
                .build();
            ret = api.applicationInstances().list(applicationId, filters);
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get application instances: "+e.getMessage());
        }

        return ret;
    }

    public Collection<Metric> getApplicationInstanceMetricNames(NewRelicApi api, long applicationId, long id)
    {
        logger.info("Get application instance metric names: "+id);
        Collection<Metric> metrics = api.applicationInstances().metricNames(applicationId, id, "Threads/SummaryState/");
        Assert.assertTrue(metrics.size() > 0);
        return metrics;
    }

    public MetricData getApplicationInstanceMetricData(NewRelicApi api, long applicationId, long id)
    {
        logger.info("Get application instance metric data: "+id);
        List<String> parameters = MetricParameterBuilder.builder()
            .names("Threads/SummaryState/RUNNABLE/Count")
            .values("call_count")
            .from(System.currentTimeMillis()-(3600*1000L)) // last 1 hour
            .to(System.currentTimeMillis())
            .summarize(true)
            .build();

        MetricData metrics = api.applicationInstances().metricData(applicationId, id, parameters).get();
        Assert.assertTrue(metrics.getMetrics().size() > 0);
        return metrics;
    }

    public Plugin getPlugin(NewRelicApi api, long id)
    {
        logger.info("Get plugin: "+id);
        Plugin ret = api.plugins().show(id, true).get();
        Assert.assertNotNull(ret);
        return ret;
    }

    public Collection<Plugin> getPlugins(NewRelicApi api)
    {
        Collection<Plugin> ret = null;

        try
        {
            logger.info("Get plugins: ");
            List<String> filters = PluginService.filters()
                //.guid("guid")
                .detailed(true)
                .build();
            ret = api.plugins().list(filters);
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get plugins: "+e.getMessage());
        }

        return ret;
    }

    public PluginComponent getPluginComponent(NewRelicApi api, long componentId)
    {
        logger.info("Get plugin component: "+componentId);
        PluginComponent ret = api.pluginComponents().show(componentId).get();
        Assert.assertNotNull(ret);
        return ret;
    }

    public Collection<PluginComponent> getPluginComponents(NewRelicApi api)
    {
        Collection<PluginComponent> ret = null;

        try
        {
            logger.info("Get plugin components: ");
            List<String> filters = PluginComponentService.filters()
                //.pluginId("12345")
                .healthStatus(true)
                .build();
            ret = api.pluginComponents().list(filters);
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get plugin components: "+e.getMessage());
        }

        return ret;
    }

    public Collection<Metric> getPluginComponentMetricNames(NewRelicApi api, long id)
    {
        Collection<Metric> metrics = null;

        try
        {
            logger.info("Get plugin component metric names: "+id);
            metrics = api.pluginComponents().metricNames(id, "Threads/SummaryState/");
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get plugin component metric names: "+e.getMessage());
        }

        return metrics;
    }

    public MetricData getPluginComponentMetricData(NewRelicApi api, long id)
    {
        logger.info("Get plugin component metric data: "+id);
        List<String> parameters = MetricParameterBuilder.builder()
            .names("Threads/SummaryState/RUNNABLE/Count")
            .values("call_count")
            .from(System.currentTimeMillis()-(3600*1000L)) // last 1 hour
            .to(System.currentTimeMillis())
            .summarize(true)
            .build();

        MetricData metrics = null;

        try
        {
            metrics = api.pluginComponents().metricData(id, parameters).get();
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get plugin component metric data: "+e.getMessage());
        }

        return metrics;
    }

    public Deployment getDeployment(String desc)
    {
        return Deployment.builder()
            .description(desc)
            .revision("1.0")
            .changelog("some changes")
            .user("me")
            .build();
    }

    public Deployment createDeployment(NewRelicApi api, long applicationId, Deployment input)
    {
        logger.info("Create deployment: "+input.getDescription());
        Deployment deployment = api.deployments().create(applicationId, input).get();

        // Get the deployment
        {
            logger.info("Get deployment: "+deployment.getId());
            Optional<Deployment> ret = api.deployments().show(applicationId, deployment.getId());
            Assert.assertTrue(ret.isPresent());
        }

        return deployment;
    }

    public Collection<Deployment> getDeployments(NewRelicApi api, long applicationId)
    {
        Collection<Deployment> ret = null;

        try
        {
            logger.info("Get deployments: ");
            ret = api.deployments().list(applicationId);
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get deployments: "+e.getMessage());
        }

        return ret;
    }

    public void deleteDeployment(NewRelicApi api, Application application, Deployment deployment)
    {
        logger.info("Delete deployment: "+deployment.getId());
        api.deployments().delete(application.getId(), deployment.getId());
        Optional<Deployment> ret = api.deployments().show(application.getId(), deployment.getId());
        Assert.assertFalse(ret.isPresent());
    }

    public Server getServer(long id, String name)
    {
        return Server.builder()
            .id(id)
            .name(name)
            .build();
    }

    public Server getServer(NewRelicApi api, long id)
    {
        logger.info("Get server: "+id);
        Server ret = api.servers().show(id).get();
        Assert.assertNotNull(ret);
        return ret;
    }

    public Collection<Server> getServers(NewRelicApi api)
    {
        Collection<Server> ret = null;

        try
        {
            logger.info("Get servers: ");
            List<String> filters = ServerService.filters()
                .reported(true)
                .build();
            ret = api.servers().list(filters);
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get servers: "+e.getMessage());
        }

        return ret;
    }

    public Server updateServer(NewRelicApi api, Server input)
    {
        logger.info("Update server: "+input.getName());
        Server server = api.servers().update(input).get();
        Assert.assertNotNull(server);
        return server;
    }

    public Collection<Metric> getServerMetricNames(NewRelicApi api, long id)
    {
        logger.info("Get server metric names: "+id);
        Collection<Metric> metrics = api.servers().metricNames(id, "System/Memory/");
        Assert.assertTrue(metrics.size() > 0);
        return metrics;
    }

    public MetricData getServerMetricData(NewRelicApi api, long id)
    {
        logger.info("Get server metric data: "+id);
        List<String> parameters = MetricParameterBuilder.builder()
            .names("System/Memory/Used/bytes")
            .values("average_response_time")
            .from(System.currentTimeMillis()-(3600*1000L)) // last 1 hour
            .to(System.currentTimeMillis())
            .summarize(true)
            .build();

        MetricData metrics = api.servers().metricData(id, parameters).get();
        Assert.assertTrue(metrics.getMetrics().size() > 0);
        return metrics;
    }

    public Label getLabel(String category, String name)
    {
        return Label.builder()
            .category(category)
            .name(name)
            .build();
    }

    public Label createLabel(NewRelicApi api, Label input)
    {
        logger.info("Create label: "+input.getName());
        Label label = api.labels().create(input).get();

        // Get the label
        {
            logger.info("Get label: "+label.getKey());
            Optional<Label> ret = api.labels().show(label.getKey());
            Assert.assertTrue(ret.isPresent());
        }

        return label;
    }

    public Label getLabel(NewRelicApi api, String key)
    {
        logger.info("Get label: "+key);
        Label ret = api.labels().show(key).get();
        Assert.assertNotNull(ret);
        return ret;
    }

    public Collection<Label> getLabels(NewRelicApi api)
    {
        Collection<Label> ret = null;

        try
        {
            logger.info("Get labels: ");
            ret = api.labels().list();
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get labels: "+e.getMessage());
        }

        return ret;
    }

    public void deleteLabel(NewRelicApi api, Label label)
    {
        try
        {
            logger.info("Delete label: "+label.getKey());
            api.labels().delete(label.getKey());
            Optional<Label> ret = api.labels().show(label.getKey());
            Assert.assertFalse(ret.isPresent());
        }
        catch(RuntimeException e)
        {
            if(e.getMessage().indexOf("404 Not Found") == -1) // throws 404 if label contains illegal characters
                Assert.fail("Error in delete label: "+e.getMessage());
            else
                logger.warning("Error in delete label: "+e.getMessage());
        }
    }

    public User getUser(NewRelicApi api, long id)
    {
        logger.info("Get user: "+id);
        User ret = api.users().show(id).get();
        Assert.assertNotNull(ret);
        return ret;
    }

    public Collection<User> getUsers(NewRelicApi api)
    {
        Collection<User> ret = null;

        try
        {
            logger.info("Get users: ");
            ret = api.users().list();
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get users: "+e.getMessage());
        }

        return ret;
    }

    public void resetUser(NewRelicApi api, long id)
    {
        try
        {
            logger.info("Reset user: "+id);
            api.users().resetPassword(id);
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in reset user: "+e.getMessage());
        }
    }

    public UsageData getUsage(NewRelicApi api, String product)
    {
        logger.info("Get usage: "+product);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1); // usages from yesterday to today
        long startDate = c.getTimeInMillis();
        long endDate = System.currentTimeMillis();
        UsageData ret = api.usages().list(product, startDate, endDate, true).get();
        Assert.assertNotNull(ret);
        return ret;
    }

    public Monitor getSimpleMonitor(String name, String url, Location location)
    {
        return SimpleMonitor.builder()
            .name(name)
            .frequency(Monitor.Frequency.MINUTES_60)
            .uri(url)
            .slaThreshold(1.0)
            .status(Monitor.Status.ENABLED)
            .addLocation(location.getName())
            .validationStringOption("html")
            .verifySslOption(true)
            .build();
    }

    public Monitor getScriptMonitor(String name, String url, Location location)
    {
        return ScriptBrowserMonitor.builder()
            .name(name)
            .frequency(Monitor.Frequency.MINUTES_60)
            .slaThreshold(1.0)
            .status(Monitor.Status.ENABLED)
            .addLocation(location.getName())
            .build();
    }

    public Script getScript()
    {
        ScriptLocation location = ScriptLocation.builder()
            .name("my_vse_enabled_location")
            .hmac("MjhiNGE4MjVlMDE1N2M4NDQ4MjNjNDFkZDEyYTRjMmUzZDE3NGJlNjU0MWFmOTJlMzNiODExOGU2ZjhkZTY4")
            .build();

        return Script.builder()
            .scriptText("dmFyIGFzc2VydCA9IHJlcXVpcmUoJ2Fzc2VydCcpOw0KYXNzZXJ0LmVxdWFsKCcxJywgJzEnKTs=")
            //.addScriptLocation(location) // Only for private locations
            .build();
    }

    public Monitor getMonitor(NewRelicSyntheticsApi api, String id)
    {
        logger.info("Get monitor: "+id);
        Monitor ret = api.monitors().show(id).get();
        Assert.assertNotNull(ret);
        return ret;
    }

    public Collection<Monitor> getMonitors(NewRelicSyntheticsApi api)
    {
        Collection<Monitor> ret = null;

        try
        {
            logger.info("Get monitors: ");
            ret = api.monitors().list();
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get monitors: "+e.getMessage());
        }

        return ret;
    }

    public Monitor createMonitor(NewRelicSyntheticsApi api, Monitor input)
    {
        logger.info("Create monitor: "+input.getName());
        Monitor monitor = api.monitors().create(input).get();

        // Get the monitor
        {
            logger.info("Get monitor: "+monitor.getId());
            Optional<Monitor> ret = api.monitors().show(monitor.getId());
            Assert.assertTrue(ret.isPresent());
        }

        return monitor;
    }

    public void updateMonitor(NewRelicSyntheticsApi api, Monitor input)
    {
        try
        {
            logger.info("Update monitor: "+input.getName());
            Optional<Monitor> ret = api.monitors().update(input);
            Assert.assertTrue(ret.isPresent());
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in update monitor: "+e.getMessage());
        }
    }

    public Script updateMonitorScript(NewRelicSyntheticsApi api, Monitor monitor, Script input)
    {
        logger.info("Update monitor script: "+monitor.getName());
        Script script = api.monitors().updateScript(monitor.getId(), input).get();

        // Get the script
        {
            logger.info("Get monitor script: "+monitor.getId());
            Optional<Script> ret = api.monitors().showScript(monitor.getId());
            Assert.assertTrue(ret.isPresent());
        }

        return script;
    }

    public void patchMonitor(NewRelicSyntheticsApi api, Monitor input)
    {
        try
        {
            logger.info("Patch monitor: "+input.getName());
            Optional<Monitor> ret = api.monitors().patch(input);
            Assert.assertTrue(ret.isPresent());
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in patch monitor: "+e.getMessage());
        }
    }

    public void deleteMonitor(NewRelicSyntheticsApi api, Monitor monitor)
    {
        logger.info("Delete monitor: "+monitor.getId());
        api.monitors().delete(monitor.getId());

        try
        {
            Optional<Monitor> ret = api.monitors().show(monitor.getId());
            Assert.assertFalse(ret.isPresent());
        }
        catch(RuntimeException e)
        {
             if(e.getMessage().indexOf("404 Not Found") == -1) // throws 404
                 Assert.fail("Error in get monitors: "+e.getMessage());
        }
    }

    public Label getMonitorLabel(String category, String label)
    {
        return Label.builder()
            .name(label)
            .category(category)
            .build();
    }

    public void addMonitorLabel(NewRelicSyntheticsApi api, Monitor monitor, Label label)
    {
        logger.info("Add monitor label: "+monitor.getId());
        api.monitors().createLabel(monitor.getId(), label);
    }

    public Collection<Monitor> getMonitorsForLabel(NewRelicSyntheticsApi api, Label label)
    {
        Collection<Monitor> ret = null;

        try
        {
            logger.info("Get monitors for label: "+label.getCategory()+":"+label.getName());
            ret = api.monitors().list(label);
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get monitors for label: "+e.getMessage());
        }

        return ret;
    }

    public void deleteMonitorLabel(NewRelicSyntheticsApi api, Monitor monitor, Label label)
    {
        logger.info("Delete monitor label: "+monitor.getId());
        api.monitors().deleteLabel(monitor.getId(), label);
    }

    public Collection<Location> getLocations(NewRelicSyntheticsApi api)
    {
        Collection<Location> ret = null;

        try
        {
            logger.info("Get locations: ");
            ret = api.locations().list();
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get locations: "+e.getMessage());
        }

        return ret;
    }

    public QueryData getQueryResult(NewRelicInsightsApi api, long accountId, String query)
    {
        QueryData ret = null;

        try
        {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -1); // data for the last day
            query += " SINCE '"+Utils.getNrqlDateTime(c.getTimeInMillis())+"'";

            logger.info("Get query data: "+query);
            ret = api.queries().list(accountId, query).get();
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get query data: "+e.getMessage());
        }

        return ret;
    }

    public PluginData getPluginData(String host)
    {
        MetricTimeslice<Integer> timeslice1 = MetricTimeslice.<Integer> builder()
            .total(50)
            .count(4)
            .min(10)
            .max(15)
            .sumOfSquares(325)
            .build();

        MetricTimeslice<Double> timeslice2 = MetricTimeslice.<Double> builder()
            .total(50.1)
            .count(4.1)
            .min(10.1)
            .max(15.1)
            .sumOfSquares(325.1)
            .build();

        Component component = Component.builder()
            .name("my-component")
            .guid("com.test.my-plugin")
            .duration(60)
            .addMetric("Component/Database[Queries/First]", 100) // scalar metric
            .addMetric("Component/Database[Queries/Second]", new int[] {25, 2, 10, 15, 325}) // array metric
            .addMetric("Component/Database[Queries/Third]", timeslice1) // int timeslice
            .addMetric("Component/Database[Queries/Fourth]", timeslice2) // float timeslice
            .build();

        return PluginData.builder()
            .host(host)
            .pid(12345)
            .version("1.0")
            .addComponent(component)
            .build();
    }

    public void sendPluginData(NewRelicPluginsApi api, PluginData data)
    {
        logger.info("Send plugin data: "+data);
        Status status = api.metrics().metricData(data).get();
        Assert.assertTrue(status.getStatus().equals("ok"));
    }

    public Dashboard getDashboard(String title)
    {
        return Dashboard.builder()
            .title(title)
            .version(1)
            .icon(Dashboard.Icon.BAR_CHART)
            .allVisibility()
            .ownerEditable()
            .addFilter("ProcessSample", "commandName")
            .build();
    }

    public Widget getMarkdown()
    {
        return Markdown.builder()
            .accountId(accountId)
            .title("markdown-title")
            .notes("markdown notes")
            .position(1,2)
            .size(2,1)
            .addSourceData("# Dashboard Notes\n\nHere are some notes")
            .build();
    }

    public Widget getEventChart()
    {
        return EventChart.builder()
            .visualization(EventChart.Visualization.HISTOGRAM)
            .accountId(accountId)
            .title("event-title")
            .notes("event notes")
            .position(1,1)
            .size(1,1)
            .addNrqlData("SELECT histogram(threadCount,10,20) from ProcessSample SINCE yesterday")
            .build();
    }

    public Widget getThresholdEventChart()
    {
        return ThresholdEventChart.builder()
            .visualization(ThresholdEventChart.Visualization.GAUGE)
            .title("threshold-title")
            .notes("threshold notes")
            .accountId(accountId)
            .position(2,1)
            .size(1,1)
            .addNrqlData("SELECT average(cpuPercent) from ProcessSample SINCE 10 minutes ago")
            .threshold(Threshold.builder().red(10).yellow(5).build())
            .build();
    }

    public Widget getFacetPieChart(long dashboardId)
    {
        return FacetChart.builder()
            .visualization(FacetChart.Visualization.FACET_PIE_CHART)
            .title("facet-pie-title")
            .notes("facet pie notes")
            .accountId(accountId)
            .position(2,2)
            .size(2,1)
            .addNrqlData("SELECT count(*) FROM ProcessSample SINCE 1 DAY AGO FACET commandName")
            .drilldownDashboardId(dashboardId)
            .build();
    }

    public Widget getFacetBarChart(long dashboardId)
    {
        return FacetChart.builder()
            .visualization(FacetChart.Visualization.FACET_BAR_CHART)
            .title("facet-bar-title")
            .notes("facet bar notes")
            .accountId(accountId)
            .position(3,1)
            .size(1,1)
            .addNrqlData("SELECT count(*) FROM ProcessSample SINCE 1 DAY AGO FACET commandName")
            .drilldownDashboardId(dashboardId)
            .build();
    }

    public Widget getBreakdownMetricChart(long entityId)
    {
        MetricsData data = MetricsData.builder()
            .duration(86400000)
            .addEntityId(entityId)
            .build();

        return BreakdownMetricChart.builder()
            .visualization(BreakdownMetricChart.Visualization.APPLICATION_BREAKDOWN)
            .title("breakdown-title")
            .notes("breakdown notes")
            .accountId(accountId)
            .position(3,2)
            .size(1,1)
            .addData(data)
            .build();
    }

    public Widget getMetricLineChart(long entityId)
    {
        MetricsData data = MetricsData.builder()
            .duration(86400000)
            .addEntityId(entityId)
            .addMetric(Metric.builder().name(Metric.Category.APDEX).addValue(Metric.Apdex.SCORE).build())
            .orderBy(Metric.Apdex.SCORE)
            .limit(10)
            .build();

        return MetricLineChart.builder()
            .title("metric-line-title")
            .notes("metric line notes")
            .accountId(accountId)
            .position(3,3)
            .size(1,1)
            .addData(data)
            .build();
    }

    public Widget getTrafficLightChart()
    {
        TrafficLight trafficLight = TrafficLight.builder()
            .id("12345")
            .title("cpu-percent")
            .subtitle("maximum")
            .addState(TrafficLightState.builder().wrongType().min(0).max(3).build())
            .addState(TrafficLightState.builder().warningType().min(3).max(7).build())
            .addState(TrafficLightState.builder().okType().min(7).max(10).build())
            .build();

        return TrafficLightChart.builder()
            .title("traffic-light-title")
            .notes("traffic light notes")
            .accountId(accountId)
            .position(4,1)
            .size(1,1)
            .addNrqlData("SELECT max(cpuPercent) from ProcessSample SINCE 10 minutes ago")
            .addTrafficLight(trafficLight)
            .build();
    }

    public Widget getInventoryChart()
    {
        return InventoryChart.builder()
            .title("inventory-title")
            .notes("inventory notes")
            .accountId(accountId)
            .position(4,2)
            .size(1,1)
            .addSourceData("metadata/system")
            .addFilter("operatingSystem", "linux")
            .build();
    }

    public Dashboard createDashboard(NewRelicApi api, Dashboard input)
    {
        logger.info("Create dashboard: "+input.getTitle());
        Dashboard dashboard = api.dashboards().create(input).get();

        // Get the dashboard
        {
            logger.info("Get dashboard: "+dashboard.getId());
            Optional<Dashboard> ret = api.dashboards().show(dashboard.getId());
            Assert.assertTrue(ret.isPresent());
        }

        return dashboard;
    }

    public Collection<Dashboard> getDashboards(NewRelicApi api)
    {
        Collection<Dashboard> ret = null;

        try
        {
            logger.info("Get dashboards: ");
            ret = api.dashboards().list();
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get dashboards: "+e.getMessage());
        }

        return ret;
    }

    public Collection<Dashboard> getDashboardsWithFilter(NewRelicApi api, String title)
    {
        Collection<Dashboard> ret = null;

        try
        {
            logger.info("Get dashboards for title: "+title);
            List<String> filters = DashboardService.filters()
                .title(title)
                .build();
            ret = api.dashboards().list(filters);
            Assert.assertTrue(ret.iterator().next() != null);
        }
        catch(RuntimeException e)
        {
            Assert.fail("Error in get dashboards: "+e.getMessage());
        }

        return ret;
    }

    public Dashboard updateDashboard(NewRelicApi api, Dashboard input)
    {
        logger.info("Update dashboard: "+input.getTitle());
        Dashboard dashboard = api.dashboards().update(input).get();
        Assert.assertNotNull(dashboard);
        return dashboard;
    }

    public void deleteDashboard(NewRelicApi api, Dashboard dashboard)
    {
        logger.info("Delete dashboard: "+dashboard.getId());
        api.dashboards().delete(dashboard.getId());

        try
        {
            Optional<Dashboard> ret = api.dashboards().show(dashboard.getId());
            Assert.assertFalse(ret.isPresent());
        }
        catch(RuntimeException e)
        {
             if(e.getMessage().indexOf("404 Not Found") == -1) // throws 404
                 Assert.fail("Error in get dashboards: "+e.getMessage());
        }
    }
}