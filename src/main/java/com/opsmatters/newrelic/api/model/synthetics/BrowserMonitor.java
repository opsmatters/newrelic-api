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
 * Represents a New Relic Synthetics browser monitor.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class BrowserMonitor extends Monitor
{
    /**
     * The type of the monitor.
     */
    public static final MonitorType TYPE = MonitorType.BROWSER;

    private String uri;

    /**
     * Default constructor.
     */
    public BrowserMonitor()
    {
        setType(TYPE.name());
    }

    /**
     * Sets the uri of the monitor.
     * @param uri The uri of the monitor
     */
    public void setUri(String uri)
    {
        this.uri = uri;
    }

    /**
     * Returns the uri of the monitor.
     * @return The uri of the monitor
     */
    public String getUri()
    {
        return uri;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "BrowserMonitor ["+super.toString()
            +", uri="+uri
            +"]";

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
    public static class Builder extends Monitor.Builder<BrowserMonitor, Builder>
    {
        private BrowserMonitor monitor = new BrowserMonitor();

        /**
         * Default constructor.
         */
        public Builder()
        {
            monitor(monitor);
        }

        /**
         * Sets the uri of the monitor.
         * @param uri The uri of the monitor
         * @return This object
         */
        public Builder uri(String uri)
        {
            monitor.setUri(uri);
            return this;
        }

        /**
         * Sets the validation string option of the monitor.
         * @param validationString The validation string of the monitor
         * @return This object
         */
        public Builder validationStringOption(String validationString)
        {
            monitor.getOptions().setValidationString(validationString);
            return this;
        }

        /**
         * Set to <CODE>true</CODE> if the SSL connection should be verified by the monitor.
         * @param verifySsl <CODE>true</CODE> if the SSL connection should be verified by the monitor
         * @return This object
         */
        public Builder verifySslOption(boolean verifySsl)
        {
            monitor.getOptions().setVerifySsl(verifySsl);
            return this;
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
        public BrowserMonitor build()
        {
            return monitor;
        }
    }
}