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
import com.opsmatters.newrelic.api.model.condition.InfraHostNotReportingAlertCondition;
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

    private String key = System.getProperty("newrelic.apiKey");
    private String policyName = "test-policy";
    private String apmConditionName = "test-apm-condition";
    private String nrqlConditionName = "test-nrql-condition";
    private String hostConditionName = "test-host-condition";
    private String whereClause = "env=prod";
    private String email = "alerts@test.com";
    private String channel = "#enquiries";
    private String webhookUrl = "https://hooks.slack.com/services/T8LVC2SGM/B8M02K7yy/k7SrSQGoluE2olG3LpmH4sxx";

    @Test
    public void testChannelsAndPolicy()
    {
        String testName = "ChannelsAndPolicy";
        logger.info("Starting test: "+testName);

        // Initialise the services
        logger.info("Initialise the services");

        NewRelicApiService api = getService();
        Assert.assertNotNull(api);

        NewRelicInfraApiService infraApi = getInfraService();
        Assert.assertNotNull(infraApi);

        // Create the email alert channel
        AlertChannel emailChannel = createEmailChannel(api, email);

        // Create the slack alert channel
        AlertChannel slackChannel = createSlackChannel(api, webhookUrl, channel);

        // Get all the alert channels
        Collection<AlertChannel> channels = getAllChannels(api);

        // Create the policy
        AlertPolicy policy = createPolicy(api);

        // Create the APM condition
        AlertCondition apmCondition = createApmCondition(api, policy);

        // Create the NRQL condition
        NrqlAlertCondition nrqlCondition = createNrqlCondition(api, policy);

        // Create the infrastructure condition
        InfraAlertCondition infraCondition = createInfraCondition(infraApi, policy);

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

        // Delete the infrastructure condition
        deleteInfraCondition(infraApi, infraCondition);

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
        logger.info("Create the policy: "+policyName);
        AlertPolicy input = getPolicy(policyName);
        AlertPolicy policy = api.alertPolicies().create(input).get();
        Assert.assertNotNull(policy);

        // Get the policy
        {
            logger.info("Get the policy: "+policy.getId()+"-"+policy.getName());
            Optional<AlertPolicy> ret = api.alertPolicies().get(policy.getName(), policy.getId());
            Assert.assertTrue(ret.isPresent());
        }

        return policy;
    }

    public void deletePolicy(NewRelicApiService api, AlertPolicy policy)
    {
        logger.info("Delete the policy: "+policy.getId());
        api.alertPolicies().delete(policy.getId());
        Optional<AlertPolicy> ret = api.alertPolicies().get(policy.getName(), policy.getId());
        Assert.assertFalse(ret.isPresent());
    }

    public InfraAlertCondition getHostNotReportingCondition(long policyId, String name, String whereClause)
    {
        return InfraHostNotReportingAlertCondition.builder()
            .policyId(policyId)
            .name(name)
            .whereClause(whereClause)
            .criticalThreshold(new Threshold(10))
            .enabled(true)
            .build();
    }

    public InfraAlertCondition createInfraCondition(NewRelicInfraApiService api, AlertPolicy policy)
    {
        // Create the host infra condition
        logger.info("Create the host condition: "+hostConditionName);
        InfraAlertCondition input = getHostNotReportingCondition(policy.getId(), hostConditionName, whereClause);
        InfraAlertCondition condition = api.infraAlertConditions().create(input).get();
        Assert.assertNotNull(condition);

        // Get the infra condition
        {
            logger.info("Get the host condition: "+condition.getId());
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
        logger.info("Delete the infra condition: "+condition.getId());
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
        logger.info("Create the APM condition: "+apmConditionName);
        AlertCondition input = getApmAppCondition(policy.getId(), apmConditionName);
        AlertCondition condition = api.alertConditions().create(policy.getId(), input).get();
        Assert.assertNotNull(condition);

        // Get the APM condition
        {
            logger.info("Get the APM condition: "+condition.getId());
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
        logger.info("Delete the APM condition: "+condition.getId());
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
        logger.info("Create the nrql condition: "+nrqlConditionName);
        NrqlAlertCondition input = getNrqlCondition(policy.getId(), nrqlConditionName);
        NrqlAlertCondition condition = api.nrqlAlertConditions().create(policy.getId(), input).get();
        Assert.assertNotNull(condition);

        // Get the nrql condition
        {
            logger.info("Get the nrql condition: "+condition.getId());
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
        logger.info("Delete the nrql condition: "+condition.getId());
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

    public AlertChannel createEmailChannel(NewRelicApiService api, String email)
    {
        logger.info("Create email alert channel: "+email);
        AlertChannel input = getEmailChannel(email);
        AlertChannel channel = api.alertChannels().create(input).get();

        // Get the email alert channel
        {
            logger.info("Get the email alert channel: "+channel.getId());
            Optional<AlertChannel> ret = api.alertChannels().get(channel.getId());
            Assert.assertTrue(ret.isPresent());
        }

        return channel;
    }

    public AlertChannel createSlackChannel(NewRelicApiService api, String url, String channelName)
    {
        logger.info("Create slack alert channel: "+channelName);
        AlertChannel input = getSlackChannel(url, channelName);
        AlertChannel channel = api.alertChannels().create(input).get();

        // Get the slack alert channel
        {
            logger.info("Get the slack alert channel: "+channel.getId());
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
        logger.info("Delete the alert channel: "+channel.getId());
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