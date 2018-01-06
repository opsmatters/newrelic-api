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

        // Create the APM condition
        AlertCondition apmCondition = createApmCondition(api, policy);

        // Create the NRQL condition
        NrqlAlertCondition nrqlCondition = createNrqlCondition(api, policy);

        // Create the infrastructure conditions
        InfraAlertCondition infraMetricCondition = createInfraCondition(infraApi, policy,
            getMetricCondition(policy.getId(), metricConditionName, whereClause));
        InfraAlertCondition infraHostCondition = createInfraCondition(infraApi, policy,
            getHostNotReportingCondition(policy.getId(), hostConditionName, whereClause));
        InfraAlertCondition infraProcessCondition = createInfraCondition(infraApi, policy,
            getProcessRunningCondition(policy.getId(), processConditionName, whereClause));

        // Add the channels to the policy
        addPolicyChannel(api, policy, emailChannel);
        addPolicyChannel(api, policy, slackChannel);

        // Delete the channels from the policy
        deletePolicyChannel(api, policy, emailChannel);
        deletePolicyChannel(api, policy, slackChannel);

        // Delete the APM condition
        deleteApmCondition(api, policy, apmCondition);

        // Delete the NRQL condition
        deleteNrqlCondition(api, policy, nrqlCondition);

        // Delete the infrastructure conditions
        deleteInfraCondition(infraApi, infraMetricCondition);
        deleteInfraCondition(infraApi, infraHostCondition);
        deleteInfraCondition(infraApi, infraProcessCondition);

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

        // Get the infra condition
        {
            logger.info("Get infra condition: "+condition.getId());
            Optional<InfraAlertCondition> ret = api.infraAlertConditions().get(condition.getId());
            Assert.assertTrue(ret.isPresent());
        }

        // Get all infra conditions for the policy
        {
            logger.info("Get all conditions for policy: "+policy.getId());
            Collection<InfraAlertCondition> ret = api.infraAlertConditions().list(policy.getId());
            Assert.assertTrue(ret.size() > 0);
        }

        return condition;
    }

    public void deleteInfraCondition(NewRelicInfraApiService api, InfraAlertCondition condition)
    {
        logger.info("Delete infra condition: "+condition.getId());
        api.infraAlertConditions().delete(condition.getId());

        Optional<InfraAlertCondition> ret = Optional.absent();

        try
        {
            ret = api.infraAlertConditions().get(condition.getId());
        }
        catch(RuntimeException e)
        {
        }

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

    public AlertCondition createApmCondition(NewRelicApiService api, AlertPolicy policy)
    {
        // Create the APM condition
        logger.info("Create APM condition: "+apmConditionName);
        AlertCondition input = getApmAppCondition(policy.getId(), apmConditionName);
        AlertCondition condition = api.alertConditions().create(policy.getId(), input).get();
        Assert.assertNotNull(condition);

        // Get the APM condition
        {
            logger.info("Get APM condition: "+condition.getId());
            Optional<AlertCondition> ret = api.alertConditions().get(policy.getId(), condition.getId());
            Assert.assertTrue(ret.isPresent());
        }

        // Get all APM conditions for the policy
        {
            logger.info("Get all conditions for policy: "+policy.getId());
            Collection<AlertCondition> ret = api.alertConditions().list(policy.getId());
            Assert.assertTrue(ret.size() > 0);
        }

        return condition;
    }

    public void deleteApmCondition(NewRelicApiService api, AlertPolicy policy, AlertCondition condition)
    {
        logger.info("Delete APM condition: "+condition.getId());
        api.alertConditions().delete(condition.getId());

        Optional<AlertCondition> ret = Optional.absent();

        try
        {
            ret = api.alertConditions().get(policy.getId(), condition.getId());
        }
        catch(RuntimeException e)
        {
        }

        Assert.assertFalse(ret.isPresent());
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

    public NrqlAlertCondition createNrqlCondition(NewRelicApiService api, AlertPolicy policy)
    {
        // Create the nrql condition
        logger.info("Create nrql condition: "+nrqlConditionName);
        NrqlAlertCondition input = getNrqlCondition(policy.getId(), nrqlConditionName);
        NrqlAlertCondition condition = api.nrqlAlertConditions().create(policy.getId(), input).get();
        Assert.assertNotNull(condition);

        // Get the nrql condition
        {
            logger.info("Get nrql condition: "+condition.getId());
            Optional<NrqlAlertCondition> ret = api.nrqlAlertConditions().get(policy.getId(), condition.getId());
            Assert.assertTrue(ret.isPresent());
        }

        // Get all nrql conditions for the policy
        {
            logger.info("Get all conditions for policy: "+policy.getId());
            Collection<NrqlAlertCondition> ret = api.nrqlAlertConditions().list(policy.getId());
            Assert.assertTrue(ret.size() > 0);
        }

        return condition;
    }

    public void deleteNrqlCondition(NewRelicApiService api, AlertPolicy policy, NrqlAlertCondition condition)
    {
        logger.info("Delete nrql condition: "+condition.getId());
        api.nrqlAlertConditions().delete(condition.getId());

        Optional<NrqlAlertCondition> ret = Optional.absent();

        try
        {
            ret = api.nrqlAlertConditions().get(policy.getId(), condition.getId());
        }
        catch(RuntimeException e)
        {
        }

        Assert.assertFalse(ret.isPresent());
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