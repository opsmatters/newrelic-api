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
import java.util.logging.Logger;
import org.junit.Test;
import junit.framework.Assert;
import com.google.common.base.Optional;
import org.apache.commons.lang3.ArrayUtils;
import com.opsmatters.newrelic.api.NewRelicApiService;
import com.opsmatters.newrelic.api.NewRelicInfraApiService;
import com.opsmatters.newrelic.api.model.AlertPolicy;
import com.opsmatters.newrelic.api.model.AlertPolicyChannel;
import com.opsmatters.newrelic.api.model.condition.AlertCondition;
import com.opsmatters.newrelic.api.model.condition.ApmAppAlertCondition;
import com.opsmatters.newrelic.api.model.condition.NrqlAlertCondition;
import com.opsmatters.newrelic.api.model.condition.Term;
import com.opsmatters.newrelic.api.model.condition.Nrql;
import com.opsmatters.newrelic.api.model.condition.ExternalServiceAlertCondition;
import com.opsmatters.newrelic.api.model.condition.ApmExternalServiceAlertCondition;
import com.opsmatters.newrelic.api.model.condition.PluginsAlertCondition;
import com.opsmatters.newrelic.api.model.condition.Plugin;
import com.opsmatters.newrelic.api.model.condition.InfraAlertCondition;
import com.opsmatters.newrelic.api.model.condition.InfraMetricAlertCondition;
import com.opsmatters.newrelic.api.model.condition.InfraHostNotReportingAlertCondition;
import com.opsmatters.newrelic.api.model.condition.InfraProcessRunningAlertCondition;
import com.opsmatters.newrelic.api.model.condition.Threshold;
import com.opsmatters.newrelic.api.model.channel.AlertChannel;
import com.opsmatters.newrelic.api.model.channel.EmailChannel;
import com.opsmatters.newrelic.api.model.channel.SlackChannel;

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
    private String nrqlConditionName = "test-nrql-condition";
    private String metricConditionName = "test-metric-condition";
    private String hostConditionName = "test-host-condition";
    private String processConditionName = "test-process-condition";
    private String externalServiceConditionName = "test-external-service-condition";
    private String pluginsConditionName = "test-plugins-condition";
    private String whereClause = "env=prod";
    private String email = "alerts@test.com";
    private String channel = "#slack";
    private String webhookUrl = "https://hooks.slack.com/services/T8LVC2SGM/B8M02K7yy/k7SrSQGoluE2olG3LpmH4sxx";

    @Test
    public void testChannelAndPolicyOperations()
    {
        String testName = "ChannelAndPolicyOperations";
        logger.info("Starting test: "+testName);

        // Initialise the services
        logger.info("Initialise the services");

        NewRelicApiService api = getService();
        Assert.assertNotNull(api);

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
        NrqlAlertCondition nrqlCondition = createNrqlCondition(api, policy, 
            getNrqlCondition(policy.getId(), nrqlConditionName));
        ExternalServiceAlertCondition externalServiceCondition = createExternalServiceCondition(api, policy,
            getExternalServiceCondition(policy.getId(), externalServiceConditionName));
        PluginsAlertCondition pluginsCondition = createPluginsCondition(api, policy, 
            getPluginsCondition(policy.getId(), pluginsConditionName));
        InfraAlertCondition infraMetricCondition = createInfraCondition(infraApi, policy,
            getMetricCondition(policy.getId(), metricConditionName, whereClause));
        InfraAlertCondition infraHostCondition = createInfraCondition(infraApi, policy,
            getHostNotReportingCondition(policy.getId(), hostConditionName, whereClause));
        InfraAlertCondition infraProcessCondition = createInfraCondition(infraApi, policy,
            getProcessRunningCondition(policy.getId(), processConditionName, whereClause));

        // Add the channels to the policy
        addPolicyChannel(api, policy, emailChannel);
        addPolicyChannel(api, policy, slackChannel);

        // Get all the conditions
        getAllApmConditions(api, policy);
        getAllNrqlConditions(api, policy);
        getAllExternalServiceConditions(api, policy);
        getAllPluginsConditions(api, policy);
        getAllInfraConditions(infraApi, policy);

        // Delete the channels from the policy
        deletePolicyChannel(api, policy, emailChannel);
        deletePolicyChannel(api, policy, slackChannel);

        // Delete the conditions
        deleteApmCondition(api, policy, apmCondition);
        deleteNrqlCondition(api, policy, nrqlCondition);
        deleteExternalServiceCondition(api, policy, externalServiceCondition);
        deletePluginsCondition(api, policy, pluginsCondition);
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
            Optional<AlertPolicy> ret = api.alertPolicies().get(policy.getName(), policy.getId());
            Assert.assertTrue(ret.isPresent());
        }

        return policy;
    }

    public void deletePolicy(NewRelicApiService api, AlertPolicy policy)
    {
        logger.info("Delete policy: "+policy.getId());
        api.alertPolicies().delete(policy.getId());
        Optional<AlertPolicy> ret = api.alertPolicies().get(policy.getName(), policy.getId());
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
            ret = api.alertConditions().get(policy.getId(), condition.getId());
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
            ret = api.nrqlAlertConditions().get(policy.getId(), condition.getId());
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
            ret = api.externalServiceAlertConditions().get(policy.getId(), condition.getId());
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
        logger.info("Create plugins condition: "+input.getName());
        PluginsAlertCondition condition = api.pluginsAlertConditions().create(policy.getId(), input).get();
        Assert.assertNotNull(condition);
        Assert.assertTrue(getPluginsCondition(api, policy, condition).isPresent());
        return condition;
    }

    public void deletePluginsCondition(NewRelicApiService api, AlertPolicy policy, PluginsAlertCondition condition)
    {
        logger.info("Delete plugins condition: "+condition.getId());
        api.pluginsAlertConditions().delete(condition.getId());
        Assert.assertFalse(getPluginsCondition(api, policy, condition).isPresent());
    }

    public Optional<PluginsAlertCondition> getPluginsCondition(NewRelicApiService api, AlertPolicy policy, PluginsAlertCondition condition)
    {
        Optional<PluginsAlertCondition> ret = Optional.absent();

        try
        {
            logger.info("Get plugins condition: "+condition.getId());
            ret = api.pluginsAlertConditions().get(policy.getId(), condition.getId());
        }
        catch(RuntimeException e)
        {
        }

        return ret;
    }

    public Collection<PluginsAlertCondition> getAllPluginsConditions(NewRelicApiService api, AlertPolicy policy)
    {
        logger.info("Get all plugins conditions for policy: "+policy.getId());
        Collection<PluginsAlertCondition> ret = api.pluginsAlertConditions().list(policy.getId());
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
            ret = api.infraAlertConditions().get(condition.getId());
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
            Optional<AlertChannel> ret = api.alertChannels().get(channel.getId());
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
        Optional<AlertChannel> ret = api.alertChannels().get(channel.getId());
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
}