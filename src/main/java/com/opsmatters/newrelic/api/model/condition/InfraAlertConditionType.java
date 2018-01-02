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

package com.opsmatters.newrelic.api.model.condition;

/**
 * Represents a New Relic infrastructure alert condition type.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public enum InfraAlertConditionType
{
    HOST_NOT_REPORTING("infra_host_not_reporting"),
    PROCESS_RUNNING("infra_process_running"),
    METRIC("infra_metric");

    InfraAlertConditionType(String value)
    {
        this.value = value;
    }

    public String value()
    {
        return value;
    }

    private String value;
}