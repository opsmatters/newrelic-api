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

package com.opsmatters.newrelic.api.model.entities;

/**
 * Represents the available New Relic entity types.
 * 
 * @author Gerald Curley (opsmatters)
 */
public enum EntityType
{
    APPLICATION("Application"),
    BROWSER_APPLICATION("BrowserApplication"),
    MOBILE_APPLICATION("MobileApplication"),
    SERVER("Server"),
    KEY_TRANSACTION("KeyTransaction"),
    PLUGIN("Plugin"),
    MONITOR("Monitor"),
    HOST("Host"),
    INSTANCE("Host");

    EntityType(String value)
    {
        this.value = value;
    }

    public String value()
    {
        return value;
    }

    private String value;
}