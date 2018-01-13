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

package com.opsmatters.newrelic.api.model.channels;

import java.util.List;

/**
 * Represents a New Relic Email alert channel.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class EmailChannel extends AlertChannel
{
    /**
     * Default constructor.
     */
    public EmailChannel()
    {
        this(new EmailConfiguration());
    }

    /**
     * Constructor that takes a configuration.
     * @param configuration The alert channel configuration
     */
    public EmailChannel(EmailConfiguration configuration)
    {
        setConfiguration(configuration);
        setType(configuration.getType());
    }
   
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "EmailChannel ["+super.toString()+"]";
    }

    /**
     * Returns a builder for the Email channel.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make Email channel construction easier.
     */
    public static class Builder extends AlertChannel.Builder<EmailChannel, Builder>
    {
        private EmailConfiguration configuration = new EmailConfiguration();
        private EmailChannel channel = new EmailChannel(configuration);

        /**
         * Default constructor.
         */
        public Builder()
        {
            channel(channel);
        }

        /**
         * Sets the recipients of the Email alerts.
         * @param recipients The recipients of the alerts
         * @return This object
         */
        public Builder recipients(String recipients)
        {
            configuration.setRecipients(recipients);
            return this;
        }

        /**
         * Set to <CODE>true</CODE> if the alerts should include the data in JSON format.
         * @param include_json_attachment <CODE>true</CODE> if the alerts should include a JSON attachment
         * @return This object
         */
        public Builder includeJsonAttachment(boolean include_json_attachment)
        {
            configuration.setIncludeJsonAttachment(include_json_attachment);
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
         * Returns the configured Email channel instance
         * @return The Email channel instance
         */
        @Override
        public EmailChannel build()
        {
            return channel;
        }
    }
}