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
import com.opsmatters.newrelic.api.model.AlertPolicy;
import com.opsmatters.newrelic.api.model.InfraAlertCondition;
import com.opsmatters.newrelic.api.model.CriticalThreshold;
import com.opsmatters.newrelic.api.model.NrqlAlertCondition;
import com.opsmatters.newrelic.api.model.Term;
import com.opsmatters.newrelic.api.model.Nrql;

/**
 * The set of tests used for New Relic API operations.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ApiTest
{
    private static final Logger logger = Logger.getLogger(ApiTest.class.getName());

    private String host = "api.newrelic.com";
    private String infrahost = "infra-api.newrelic.com";
    private int port = 443;
    private String key = System.getProperty("newrelic.apiKey");
    private String policyName = "test-policy";
    private String hostConditionName = "test-host-condition";
    private String nrqlConditionName = "test-nrql-condition";

    @Test
    public void testPolicyAndInfraCondition()
    {
        String testName = "PolicyAndInfraCondition";
        logger.info("Starting test: "+testName);

        // Initialise the services
        logger.info("Initialise the services");
        NewRelicApiService api = getService();
        Assert.assertNotNull(api);

        NewRelicApiService infraapi = getInfraService();
        Assert.assertNotNull(infraapi);

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

        // Test an infrastructure condition
        testInfraCondition(infraapi, testPolicy);

        // Test an NRQL condition
        testNrqlCondition(api, testPolicy);

        // Delete the policy
        {
            logger.info("Delete the policy: "+testPolicy.getId());
            api.alertPolicies().delete(testPolicy.getId());
            Optional<AlertPolicy> ret = api.alertPolicies().get(testPolicy.getName(), testPolicy.getId());
            Assert.assertFalse(ret.isPresent());
        }

        logger.info("Completed test: "+testName);
    }

    public NewRelicApiService getService()
    {
        return NewRelicApiService.builder()
            .apiKey(key)
            .hostname(host)
            .port(port)
            .build();
	}

    public NewRelicApiService getInfraService()
    {
        return NewRelicApiService.builder()
            .apiKey(key)
            .hostname(infrahost)
            .port(port)
            .build();
	}

    public AlertPolicy getPolicy(String name)
    {
        return AlertPolicy.builder()
            .name(name)
            .perPolicyIncidentPreference()
            .build();
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

    public void testInfraCondition(NewRelicApiService api, AlertPolicy policy)
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

        // Delete the infra condition
        {
            logger.info("Delete the infra condition: "+testCondition.getId());
            api.infraAlertConditions().delete(testCondition.getId());

            Optional<InfraAlertCondition> ret = Optional.absent();

            try
            {
                ret = api.infraAlertConditions().get(testCondition.getId());
            }
            catch(RuntimeException e)
            {
            }

            Assert.assertFalse(ret.isPresent());
        }
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

    public void testNrqlCondition(NewRelicApiService api, AlertPolicy policy)
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

        // Delete the nrql condition
        {
            logger.info("Delete the nrql condition: "+testCondition.getId());
            api.nrqlAlertConditions().delete(testCondition.getId());

            Optional<NrqlAlertCondition> ret = Optional.absent();

            try
            {
                ret = api.nrqlAlertConditions().get(policy.getId(), testCondition.getId());
            }
            catch(RuntimeException e)
            {
            }

            Assert.assertFalse(ret.isPresent());
        }
    }
}