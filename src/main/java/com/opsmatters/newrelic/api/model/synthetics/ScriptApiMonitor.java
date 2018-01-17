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

package com.opsmatters.newrelic.api.model.synthetics;

/**
 * Represents a New Relic Synthetics script API monitor.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ScriptApiMonitor extends Monitor
{
    /**
     * The type of the monitor.
     */
    public static final MonitorType TYPE = MonitorType.SCRIPT_API;

    /**
     * Default constructor.
     */
    public ScriptApiMonitor()
    {
        setType(TYPE.name());
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "ScriptApiMonitor ["+super.toString()+"]";
    }

    /**
     * Returns a builder for the monitor.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make monitor construction easier.
     */
    public static class Builder extends Monitor.Builder<ScriptApiMonitor, Builder>
    {
        private ScriptApiMonitor monitor = new ScriptApiMonitor();

        /**
         * Default constructor.
         */
        public Builder()
        {
            monitor(monitor);
        }

        /**
         * Returns this object.
         * @return This object
         */
        @Override
        protected Builder self()
        {
            return this;
        }

        /**
         * Returns the configured monitor instance
         * @return The monitor instance
         */
        @Override
        public ScriptApiMonitor build()
        {
            return monitor;
        }
    }
}