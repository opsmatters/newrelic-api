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
import com.opsmatters.newrelic.api.NewRelicApiService;
import com.opsmatters.newrelic.api.NewRelicInfraApiService;
import com.opsmatters.newrelic.api.model.AlertPolicy;
import com.opsmatters.newrelic.api.model.condition.InfraAlertCondition;
import com.opsmatters.newrelic.api.model.condition.CriticalThreshold;
import com.opsmatters.newrelic.api.model.condition.NrqlAlertCondition;
import com.opsmatters.newrelic.api.model.condition.Term;
import com.opsmatters.newrelic.api.model.condition.Nrql;
import com.opsmatters.newrelic.api.model.AlertChannel;
import com.opsmatters.newrelic.api.model.channel.EmailConfiguration;
import com.opsmatters.newrelic.api.model.channel.SlackConfiguration;

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
    private String hostConditionName = "test-host-condition";
    private String nrqlConditionName = "test-nrql-condition";
    private String email = "info@opsmatters.com";
    private String channel = "#enquiries";
    private String webhookUrl = "https://hooks.slack.com/services/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";

    @Test
    public void testChannelsAndPolicy()
    {
        String testName = "ChannelsAndPolicy";
        logger.info("Starting test: "+testName);

        // Initialise the services
        logger.info("Initialise the services");

        NewRelicApiService api = getService();
        Assert.assertNotNull(api);

        NewRelicInfraApiService infraapi = getInfraService();
        Assert.assertNotNull(infraapi);

        // Create the email alert channel
        AlertChannel emailChannel = createEmailChannel(api, email);

        // Create the slack alert channel
        AlertChannel slackChannel = createSlackChannel(api, webhookUrl, channel);

        // Get all the alert channels
        Collection<AlertChannel> channels = getAllChannels(api);

        // Create the policy
        AlertPolicy policy = createPolicy(api);

        // Create the infrastructure condition
        InfraAlertCondition infraCondition = createInfraCondition(infraapi, policy);

        // Create the NRQL condition
        NrqlAlertCondition nrqlCondition = createNrqlCondition(api, policy);

        // Delete the infrastructure condition
        deleteInfraCondition(infraapi, infraCondition);

        // Delete the NRQL condition
        deleteNrqlCondition(api, policy, nrqlCondition);

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
        AlertPolicy policy = getPolicy(policyName);
        AlertPolicy testPolicy = api.alertPolicies().create(policy).get();
        Assert.assertNotNull(testPolicy);

        // Get the policy
        {
            logger.info("Get the policy: "+testPolicy.getId()+"-"+testPolicy.getName());
            Optional<AlertPolicy> ret = api.alertPolicies().get(testPolicy.getName(), testPolicy.getId());
            Assert.assertTrue(ret.isPresent());
        }

        return testPolicy;
    }

    public void deletePolicy(NewRelicApiService api, AlertPolicy policy)
    {
        logger.info("Delete the policy: "+policy.getId());
        api.alertPolicies().delete(policy.getId());
        Optional<AlertPolicy> ret = api.alertPolicies().get(policy.getName(), policy.getId());
        Assert.assertFalse(ret.isPresent());
    }

    public InfraAlertCondition getHostNotReportingCondition(long policyId, String name)
    {
        return InfraAlertCondition.builder()
            .policyId(policyId)
            .name(name)
            .hostNotReportingType()
            .whereClause("env=prod")
            .criticalThreshold(new CriticalThreshold(10))
            .enabled(true)
            .build();
    }

    public InfraAlertCondition createInfraCondition(NewRelicInfraApiService api, AlertPolicy policy)
    {
        // Create the host infra condition
        logger.info("Create the host condition: "+hostConditionName);
        InfraAlertCondition condition = getHostNotReportingCondition(policy.getId(), hostConditionName);
        InfraAlertCondition testCondition = api.infraAlertConditions().create(condition).get();
        Assert.assertNotNull(testCondition);

        // Get the infra condition
        {
            logger.info("Get the host condition: "+testCondition.getId());
            Optional<InfraAlertCondition> ret = api.infraAlertConditions().get(testCondition.getId());
            Assert.assertTrue(ret.isPresent());
        }

        // Get all infra conditions for the policy
        {
            logger.info("Get all conditions for policy: "+policy.getId());
            Collection<InfraAlertCondition> ret = api.infraAlertConditions().all(policy.getId());
            Assert.assertTrue(ret.size() > 0);
        }

        return testCondition;
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

    public NrqlAlertCondition getNrqlCondition(long policyId, String name)
    {
        Term term = Term.builder()
            .duration(10)
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
            .name(nrqlConditionName)
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
        NrqlAlertCondition condition = getNrqlCondition(policy.getId(), nrqlConditionName);
        NrqlAlertCondition testCondition = api.nrqlAlertConditions().create(policy.getId(), condition).get();
        Assert.assertNotNull(testCondition);

        // Get the nrql condition
        {
            logger.info("Get the nrql condition: "+testCondition.getId());
            Optional<NrqlAlertCondition> ret = api.nrqlAlertConditions().get(policy.getId(), testCondition.getId());
            Assert.assertTrue(ret.isPresent());
        }

        // Get all nrql conditions for the policy
        {
            logger.info("Get all conditions for policy: "+policy.getId());
            Collection<NrqlAlertCondition> ret = api.nrqlAlertConditions().all(policy.getId());
            Assert.assertTrue(ret.size() > 0);
        }

        return testCondition;
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
        EmailConfiguration configuration = EmailConfiguration.builder()
            .recipients(email)
            .includeJsonAttachment(true)
            .build();
        return AlertChannel.builder()
            .name(email)
            .configuration(configuration)
            .build();
    }

    public AlertChannel getSlackChannel(String url, String channel)
    {
        SlackConfiguration configuration = SlackConfiguration.builder()
            .channel(channel)
            .url(url)
            .build();
        return AlertChannel.builder()
            .name(channel)
            .configuration(configuration)
            .build();
    }

    public AlertChannel createEmailChannel(NewRelicApiService api, String email)
    {
        AlertChannel emailChannel = getEmailChannel(email);
        AlertChannel testEmailChannel = api.alertChannels().create(emailChannel).get();
        logger.info("Create email alert channel: "+testEmailChannel.getId());

        // Get the email alert channel
        {
            logger.info("Get the email alert channel: "+testEmailChannel.getId());
            Optional<AlertChannel> ret = api.alertChannels().get(testEmailChannel.getId());
            Assert.assertTrue(ret.isPresent());
        }

        return testEmailChannel;
    }

    public AlertChannel createSlackChannel(NewRelicApiService api, String url, String channel)
    {
        AlertChannel slackChannel = getSlackChannel(url, channel);
        AlertChannel testSlackChannel = api.alertChannels().create(slackChannel).get();
        logger.info("Create slack alert channel: "+testSlackChannel.getId());

        // Get the slack alert channel
        {
            logger.info("Get the slack alert channel: "+testSlackChannel.getId());
            Optional<AlertChannel> ret = api.alertChannels().get(testSlackChannel.getId());
            Assert.assertTrue(ret.isPresent());
        }

        return testSlackChannel;
    }

    public Collection<AlertChannel> getAllChannels(NewRelicApiService api)
    {
        logger.info("Get all alert channels: ");
        Collection<AlertChannel> ret = api.alertChannels().all();
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
}