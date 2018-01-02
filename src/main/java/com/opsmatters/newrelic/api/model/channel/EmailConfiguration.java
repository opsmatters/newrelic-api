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

package com.opsmatters.newrelic.api.model.channel;

/**
 * Represents a New Relic Email channel configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class EmailConfiguration extends ChannelConfiguration
{
    private String include_json_attachment;
    private String recipients;

    /**
     * Default constructor.
     */
    public EmailConfiguration()
    {
        super("email");
    }
   
    /**
     * Constructor that takes a list of recipients.
     * @param recipients The list of recipients
     */
    public EmailConfiguration(String recipients)
    {
        this();
        setRecipients(recipients);
    }

    /**
     * Set to <CODE>true</CODE> if the alerts should include the data in JSON format.
     * @param include_json_attachment <CODE>true</CODE> if the alerts should include a JSON attachment
     */
    public void setIncludeJsonAttachment(String include_json_attachment)
    {
        this.include_json_attachment = include_json_attachment;
    }

    /**
     * Set to <CODE>true</CODE> if the alerts should include the data in JSON format.
     * @param include_json_attachment <CODE>true</CODE> if the alerts should include a JSON attachment
     */
    public void setIncludeJsonAttachment(boolean include_json_attachment)
    {
        this.include_json_attachment = Boolean.toString(include_json_attachment);
    }

    /**
     * Returns <CODE>true</CODE> if the alerts should include the data in JSON format.
     * @return <CODE>true</CODE> if the alerts should include a JSON attachment
     */
    public String getIncludeJsonAttachment()
    {
        return include_json_attachment;
    }

    /**
     * Sets the recipients of the alerts.
     * @param recipients The recipients of the alerts
     */
    public void setRecipients(String recipients)
    {
        this.recipients = recipients;
    }

    /**
     * Returns the recipients of the alerts.
     * @return The recipients of the alerts
     */
    public String getRecipients()
    {
        return recipients;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "EmailConfiguration ["+super.toString()
            +", recipients="+recipients
            +", include_json_attachment="+include_json_attachment
            +"]";
    }

    /**
     * Returns a builder for the Email configuration.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make Email configuration construction easier.
     */
    public static class Builder
    {
        private EmailConfiguration configuration = new EmailConfiguration();

        /**
         * Sets the recipients of the Email configuration.
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
         * Returns the configured Email configuration instance
         * @return The Email configuration instance
         */
        public EmailConfiguration build()
        {
            return configuration;
        }
    }
}