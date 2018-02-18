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

package com.opsmatters.newrelic.api.model.alerts.channels;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic Email channel configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class EmailConfiguration extends ChannelConfiguration
{
    // The field names
    public static final String RECIPIENTS = "recipients";
    public static final String INCLUDE_JSON_ATTACHMENT = "include_json_attachment";

    /**
     * The type of the channel configuration.
     */
    public static final ChannelType TYPE = ChannelType.EMAIL;

    private String recipients;

    @SerializedName("include_json_attachment")
    private String includeJsonAttachment;

    /**
     * Default constructor.
     */
    public EmailConfiguration()
    {
        super(TYPE.value());
    }
   
    /**
     * Set to <CODE>true</CODE> if the alerts should include the data in JSON format.
     * @param includeJsonAttachment <CODE>true</CODE> if the alerts should include a JSON attachment
     */
    public void setIncludeJsonAttachment(String includeJsonAttachment)
    {
        this.includeJsonAttachment = includeJsonAttachment;
    }

    /**
     * Set to <CODE>true</CODE> if the alerts should include the data in JSON format.
     * @param includeJsonAttachment <CODE>true</CODE> if the alerts should include a JSON attachment
     */
    public void setIncludeJsonAttachment(boolean includeJsonAttachment)
    {
        this.includeJsonAttachment = Boolean.toString(includeJsonAttachment);
    }

    /**
     * Returns <CODE>true</CODE> if the alerts should include the data in JSON format.
     * @return <CODE>true</CODE> if the alerts should include a JSON attachment
     */
    public String getIncludeJsonAttachment()
    {
        return includeJsonAttachment;
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
            +", includeJsonAttachment="+includeJsonAttachment
            +"]";
    }
}