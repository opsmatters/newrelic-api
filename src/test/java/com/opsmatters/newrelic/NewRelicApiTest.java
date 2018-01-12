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

package com.opsmatters.newrelic;

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
import com.opsmatters.newrelic.api.NewRelicApiService;
import com.opsmatters.newrelic.api.NewRelicInfraApiService;
import com.opsmatters.newrelic.api.AlertEventOperations;
import com.opsmatters.newrelic.api.ApplicationOperations;
import com.opsmatters.newrelic.api.model.AlertEvent;
import com.opsmatters.newrelic.api.model.AlertViolation;
import com.opsmatters.newrelic.api.model.AlertIncident;
import com.opsmatters.newrelic.api.model.Product;
import com.opsmatters.newrelic.api.model.policy.AlertPolicy;
import com.opsmatters.newrelic.api.model.policy.AlertPolicyChannel;
import com.opsmatters.newrelic.api.model.condition.AlertCondition;
import com.opsmatters.newrelic.api.model.condition.ApmAppAlertCondition;
import com.opsmatters.newrelic.api.model.condition.BrowserAlertCondition;
import com.opsmatters.newrelic.api.model.condition.NrqlAlertCondition;
import com.opsmatters.newrelic.api.model.condition.Term;
import com.opsmatters.newrelic.api.model.condition.Nrql;
import com.opsmatters.newrelic.api.model.condition.ExternalServiceAlertCondition;
import com.opsmatters.newrelic.api.model.condition.ApmExternalServiceAlertCondition;
import com.opsmatters.newrelic.api.model.condition.PluginsAlertCondition;
import com.opsmatters.newrelic.api.model.condition.Plugin;
import com.opsmatters.newrelic.api.model.condition.SyntheticsAlertCondition;
import com.opsmatters.newrelic.api.model.condition.InfraAlertCondition;
import com.opsmatters.newrelic.api.model.condition.InfraMetricAlertCondition;
import com.opsmatters.newrelic.api.model.condition.InfraHostNotReportingAlertCondition;
import com.opsmatters.newrelic.api.model.condition.InfraProcessRunningAlertCondition;
import com.opsmatters.newrelic.api.model.condition.Threshold;
import com.opsmatters.newrelic.api.model.channel.AlertChannel;
import com.opsmatters.newrelic.api.model.channel.EmailChannel;
import com.opsmatters.newrelic.api.model.channel.SlackChannel;
import com.opsmatters.newrelic.api.model.entity.Entity;
import com.opsmatters.newrelic.api.model.entity.EntityType;
import com.opsmatters.newrelic.api.model.entity.Application;
import com.opsmatters.newrelic.api.model.entity.Metric;
import com.opsmatters.newrelic.api.model.entity.MetricData;
import com.opsmatters.newrelic.api.model.entity.BrowserApplication;

/**
 * The set of tests used for New Relic API operations.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class NewRelicApiTest
{
    private static final Logger logger = Logger.getLogger(NewRelicApiTest.class.getName());

    private String key = System.getProperty(NewRelicApiService.API_KEY_PROPERTY);
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
    private String applicationName = "test-application-";
    private String browserApplicationName = "test-browser-application-"+System.currentTimeMillis();
    private String whereClause = "env=prod";
    private String email = "alerts@test.com";
    private String channel = "#slack";
    private String webhookUrl = "https://hooks.slack.com/services/T8LVC2SGM/B8M02K7yy/k7SrSQGoluE2olG3LpmH4sxx";

    @Test
    public void testAlertOperations()
    {
        String testName = "AlertOperations";
        logger.info("Starting test: "+testName);

        // Initialise the services
        logger.info("Initialise the services");

        NewRelicApiService api = getService();
        Assert.assertNotNull(api);

        // Create the browser application
        BrowserApplication browserApplication = createBrowserApplication(api, 
            getBrowserApplication(browserApplicationName));

        Collection<Application>  applications = getApplications(api);
        if(applications.size() > 0)
        {
            Iterator<Application> it = applications.iterator();
            Application application = it.next();
            getApplication(api, application.getId());
            updateApplication(api, getApplication(application.getId(), application.getName()));
            getMetricNames(api, application.getId());
            getMetricData(api, application.getId());
        }

        NewRelicInfraApiService infraApi = getInfraService();
        Assert.assertNotNull(infraApi);

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

        // Get all the applications
        getBrowserApplications(api);

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

    public NewRelicApiService getService()
    {
        return NewRelicApiService.builder()
            .apiKey(key)
            .build();
	}

    public NewRelicInfraApiService getInfraService()
    {
        return NewRelicInfraApiService.builder()
            .apiKey(key)
            .build();
	}

    public AlertPolicy getPolicy(String name)
    {
        return AlertPolicy.builder()
            .name(name)
            .perPolicyIncidentPreference()
            .build();
    }

    public AlertPolicy createPolicy(NewRelicApiService api)
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

    public void deletePolicy(NewRelicApiService api, AlertPolicy policy)
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

    public AlertCondition createApmCondition(NewRelicApiService api, AlertPolicy policy, AlertCondition input)
    {
        logger.info("Create APM condition: "+apmConditionName);
        AlertCondition condition = api.alertConditions().create(policy.getId(), input).get();
        Assert.assertNotNull(condition);
        Assert.assertTrue(getApmCondition(api, policy, condition).isPresent());
        return condition;
    }

    public void deleteApmCondition(NewRelicApiService api, AlertPolicy policy, AlertCondition condition)
    {
        logger.info("Delete APM condition: "+condition.getId());
        api.alertConditions().delete(condition.getId());
        Assert.assertFalse(getApmCondition(api, policy, condition).isPresent());
    }

    public Optional<AlertCondition> getApmCondition(NewRelicApiService api, AlertPolicy policy, AlertCondition condition)
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

    public Collection<AlertCondition> getAllApmConditions(NewRelicApiService api, AlertPolicy policy)
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

    public NrqlAlertCondition createNrqlCondition(NewRelicApiService api, AlertPolicy policy, NrqlAlertCondition input)
    {
        logger.info("Create NRQL condition: "+input.getName());
        NrqlAlertCondition condition = api.nrqlAlertConditions().create(policy.getId(), input).get();
        Assert.assertNotNull(condition);
        Assert.assertTrue(getNrqlCondition(api, policy, condition).isPresent());
        return condition;
    }

    public void deleteNrqlCondition(NewRelicApiService api, AlertPolicy policy, NrqlAlertCondition condition)
    {
        logger.info("Delete NRQL condition: "+condition.getId());
        api.nrqlAlertConditions().delete(condition.getId());
        Assert.assertFalse(getNrqlCondition(api, policy, condition).isPresent());
    }

    public Optional<NrqlAlertCondition> getNrqlCondition(NewRelicApiService api, AlertPolicy policy, NrqlAlertCondition condition)
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

    public Collection<NrqlAlertCondition> getAllNrqlConditions(NewRelicApiService api, AlertPolicy policy)
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

    public ExternalServiceAlertCondition createExternalServiceCondition(NewRelicApiService api, AlertPolicy policy, ExternalServiceAlertCondition input)
    {
        logger.info("Create external service condition: "+input.getName());
        ExternalServiceAlertCondition condition = api.externalServiceAlertConditions().create(policy.getId(), input).get();
        Assert.assertNotNull(condition);
        Assert.assertTrue(getExternalServiceCondition(api, policy, condition).isPresent());
        return condition;
    }

    public void deleteExternalServiceCondition(NewRelicApiService api, AlertPolicy policy, ExternalServiceAlertCondition condition)
    {
        logger.info("Delete external service condition: "+condition.getId());
        api.externalServiceAlertConditions().delete(condition.getId());
        Assert.assertFalse(getExternalServiceCondition(api, policy, condition).isPresent());
    }

    public Optional<ExternalServiceAlertCondition> getExternalServiceCondition(NewRelicApiService api, AlertPolicy policy, ExternalServiceAlertCondition condition)
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

    public Collection<ExternalServiceAlertCondition> getAllExternalServiceConditions(NewRelicApiService api, AlertPolicy policy)
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

        Plugin plugin = Plugin.builder()
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

    public PluginsAlertCondition createPluginsCondition(NewRelicApiService api, AlertPolicy policy, PluginsAlertCondition input)
    {
        logger.info("Create Plugins condition: "+input.getName());
        PluginsAlertCondition condition = api.pluginsAlertConditions().create(policy.getId(), input).get();
        Assert.assertNotNull(condition);
        Assert.assertTrue(getPluginsCondition(api, policy, condition).isPresent());
        return condition;
    }

    public void deletePluginsCondition(NewRelicApiService api, AlertPolicy policy, PluginsAlertCondition condition)
    {
        logger.info("Delete Plugins condition: "+condition.getId());
        api.pluginsAlertConditions().delete(condition.getId());
        Assert.assertFalse(getPluginsCondition(api, policy, condition).isPresent());
    }

    public Optional<PluginsAlertCondition> getPluginsCondition(NewRelicApiService api, AlertPolicy policy, PluginsAlertCondition condition)
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

    public Collection<PluginsAlertCondition> getAllPluginsConditions(NewRelicApiService api, AlertPolicy policy)
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

    public SyntheticsAlertCondition createSyntheticsCondition(NewRelicApiService api, AlertPolicy policy, SyntheticsAlertCondition input)
    {
        logger.info("Create Synthetics condition: "+input.getName());
        SyntheticsAlertCondition condition = api.syntheticsAlertConditions().create(policy.getId(), input).get();
        Assert.assertNotNull(condition);
        Assert.assertTrue(getSyntheticsCondition(api, policy, condition).isPresent());
        return condition;
    }

    public void deleteSyntheticsCondition(NewRelicApiService api, AlertPolicy policy, SyntheticsAlertCondition condition)
    {
        logger.info("Delete Synthetics condition: "+condition.getId());
        api.syntheticsAlertConditions().delete(condition.getId());
        Assert.assertFalse(getSyntheticsCondition(api, policy, condition).isPresent());
    }

    public Optional<SyntheticsAlertCondition> getSyntheticsCondition(NewRelicApiService api, AlertPolicy policy, SyntheticsAlertCondition condition)
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

    public Collection<SyntheticsAlertCondition> getAllSyntheticsConditions(NewRelicApiService api, AlertPolicy policy)
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
            .criticalThreshold(new Threshold().builder().durationMinutes(10).value(80).allTimeFunction().build())
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
            .criticalThreshold(new Threshold(10))
            .whereClause(whereClause)
            .enabled(true)
            .build();
    }

    public InfraAlertCondition getProcessRunningCondition(long policyId, String name, String whereClause)
    {
        return InfraProcessRunningAlertCondition.builder()
            .policyId(policyId)
            .name(name)
            .criticalThreshold(new Threshold().builder().durationMinutes(10).value(0).build())
            .equalComparison()
            .processWhereClause("(commandName = 'java')")
            .whereClause(whereClause)
            .enabled(true)
            .build();
    }

    public InfraAlertCondition createInfraCondition(NewRelicInfraApiService api, AlertPolicy policy, InfraAlertCondition input)
    {
        // Create the infra condition
        logger.info("Create infra condition: "+input.getName());
        InfraAlertCondition condition = api.infraAlertConditions().create(input).get();
        Assert.assertNotNull(condition);
        Assert.assertTrue(getInfraCondition(api, policy, condition).isPresent());
        return condition;
    }

    public void deleteInfraCondition(NewRelicInfraApiService api, AlertPolicy policy, InfraAlertCondition condition)
    {
        logger.info("Delete infra condition: "+condition.getId());
        api.infraAlertConditions().delete(condition.getId());
        Assert.assertFalse(getInfraCondition(api, policy, condition).isPresent());
    }

    public Optional<InfraAlertCondition> getInfraCondition(NewRelicInfraApiService api, AlertPolicy policy, InfraAlertCondition condition)
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

    public Collection<InfraAlertCondition> getAllInfraConditions(NewRelicInfraApiService api, AlertPolicy policy)
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

    public AlertChannel createChannel(NewRelicApiService api, AlertChannel input)
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

    public Collection<AlertChannel> getAllChannels(NewRelicApiService api)
    {
        logger.info("Get all alert channels: ");
        Collection<AlertChannel> ret = api.alertChannels().list();
        Assert.assertTrue(ret.size() > 0);
        return ret;
    }

    public void deleteChannel(NewRelicApiService api, AlertChannel channel)
    {
        logger.info("Delete alert channel: "+channel.getId());
        api.alertChannels().delete(channel.getId());
        Optional<AlertChannel> ret = api.alertChannels().show(channel.getId());
        Assert.assertTrue(!ret.isPresent());
    }

    public void addPolicyChannel(NewRelicApiService api, AlertPolicy policy, AlertChannel channel)
    {
        logger.info("Adding alert channel: "+channel.getId()+" to policy: "+policy.getId());
        Optional<AlertPolicyChannel> ret = api.alertPolicyChannels().update(policy.getId(), channel.getId());
        Assert.assertTrue(ret.isPresent() && ArrayUtils.contains(ret.get().getChannelIdArray(), channel.getId()));
    }

    public void deletePolicyChannel(NewRelicApiService api, AlertPolicy policy, AlertChannel channel)
    {
        logger.info("Deleting alert channel: "+channel.getId()+" from policy: "+policy.getId());
        api.alertPolicyChannels().delete(policy.getId(), channel.getId());
    }

    public void addEntityCondition(NewRelicApiService api, Entity entity, AlertCondition condition)
    {
        logger.info("Adding entity condition: "+condition.getId()+" to entity: "+entity.getId());
        Optional<AlertCondition> ret = api.alertEntityConditions().add(entity, condition.getId());
        Assert.assertTrue(ret.isPresent() && ArrayUtils.contains(ret.get().getEntitiesArray(), entity.getId()));
    }

    public Collection<AlertCondition> getEntityConditions(NewRelicApiService api, Entity entity)
    {
        logger.info("Get conditions for entity: "+entity.getId());
        Collection<AlertCondition> ret = api.alertEntityConditions().list(entity);
        Assert.assertTrue(ret.size() > 0);
        return ret;
    }

    public void removeEntityCondition(NewRelicApiService api, Entity entity, AlertCondition condition)
    {
        logger.info("Removing entity condition: "+condition.getId()+" from entity: "+entity.getId());
        api.alertEntityConditions().remove(entity, condition.getId());
    }

    public Collection<AlertEvent> getAlertEvents(NewRelicApiService api)
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

    public Collection<AlertEvent> getAlertEventsWithFilters(NewRelicApiService api)
    {

        Collection<AlertEvent> ret = null;

        try
        {
            List<String> filters = AlertEventOperations.filters()
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

    public Collection<AlertViolation> getAlertViolations(NewRelicApiService api)
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

    public Collection<AlertIncident> getAlertIncidents(NewRelicApiService api)
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

    public Application updateApplication(NewRelicApiService api, Application input)
    {
        logger.info("Update application: "+input.getName());
        Application application = api.applications().update(input).get();
        Assert.assertNotNull(application);
        return application;
    }

    public Collection<Application> getApplications(NewRelicApiService api)
    {
        Collection<Application> ret = null;

        try
        {
            logger.info("Get applications: ");
            Map<String,Object> filters = ApplicationOperations.filters()
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

    public Application getApplication(NewRelicApiService api, long id)
    {
        logger.info("Get application: "+id);
        Application ret = api.applications().show(id).get();
        Assert.assertNotNull(ret);
        return ret;
    }

    public Collection<Metric> getMetricNames(NewRelicApiService api, long id)
    {
        logger.info("Get metric names: "+id);
        Collection<Metric> metrics = api.applications().metricNames(id, "Threads/SummaryState/");
        Assert.assertTrue(metrics.size() > 0);
        return metrics;
    }

    public MetricData getMetricData(NewRelicApiService api, long id)
    {
        logger.info("Get metric data: "+id);
        List<String> parameters = ApplicationOperations.metrics()
            //.names("EndUser")
            //.names("EndUser/Apdex")
            .names("Threads/SummaryState/RUNNABLE/Count")
            .names("Threads/SummaryState/BLOCKED/Count")
            .values("average_response_time")
            .values("calls_per_minute")
            .from(System.currentTimeMillis()-(3600*1000L)) // last 1 hour
            .to(System.currentTimeMillis())
            .summarize(true)
            .build();

        MetricData metrics = api.applications().metricData(id, parameters).get();
        Assert.assertTrue(metrics.getMetrics().size() > 0);
        return metrics;
    }

    public BrowserApplication getBrowserApplication(String name)
    {
        return BrowserApplication.builder()
            .name(name)
            .build();
    }

    public BrowserApplication createBrowserApplication(NewRelicApiService api, BrowserApplication input)
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

    public Collection<BrowserApplication> getBrowserApplications(NewRelicApiService api)
    {
        logger.info("Get all browser applications: ");
        Collection<BrowserApplication> ret = api.browserApplications().list();
        Assert.assertTrue(ret.size() > 0);
        return ret;
    }
}