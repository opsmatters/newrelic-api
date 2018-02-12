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

package com.opsmatters.newrelic.api.model.accounts;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.NamedIdResource;

/**
 * Represents a New Relic partner account.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PartnerAccount extends NamedIdResource
{
    @SerializedName("application_type")
    private String applicationType;

    @SerializedName("allow_api_access")
    private Boolean allowApiAccess;

    @SerializedName("phone_number")
    private String phoneNumber;

    private Boolean testing;

    private List<PartnerUser> users;

    private List<ProductSubscription> subscriptions;

    private String status;

    @SerializedName("license_key")
    private String licenseKey;

    @SerializedName("api_key")
    private String apiKey;

    @SerializedName("data_access_key")
    private String dataAccessKey;

    @SerializedName("browser_monitoring_key")
    private String browserMonitoringKey;

    @SerializedName("high_security")
    private Boolean highSecurity;

    @SerializedName("partner_external_identifier")
    private String partnerExternalIdentifier;

    private PartnerSubscription subscription;

    @SerializedName("primary admin")
    private AdminUser primaryAdmin;

    /**
     * Default constructor.
     */
    public PartnerAccount()
    {
    }
    
    /**
     * Sets the application type of the account.
     * @param applicationType The application type of the account
     */
    public void setApplicationType(String applicationType)
    {
        this.applicationType = applicationType;
    }

    /**
     * Returns the application type of the account.
     * @return The application type of the account
     */
    public String getApplicationType()
    {
        return applicationType;
    }

    /**
     * Set to <CODE>true</CODE> if API access to application data is allowed.
     * @param allowApiAccess <CODE>true</CODE> if API access to application data is allowed
     */
    public void setAllowApiAccess(Boolean allowApiAccess)
    {
        this.allowApiAccess = allowApiAccess;
    }

    /**
     * Returns <CODE>true</CODE> if API access to application data is allowed.
     * @return <CODE>true</CODE> if API access to application data is allowed
     */
    public Boolean getAllowApiAccess()
    {
        return allowApiAccess;
    }

    /**
     * Sets the phone number of the account.
     * @param phoneNumber The phone number of the account
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the phone number of the account.
     * @return The phone number of the account
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * Set to <CODE>true</CODE> if this account is a test account.
     * @param testing <CODE>true</CODE> if this account is a test account
     */
    public void setTesting(Boolean testing)
    {
        this.testing = testing;
    }

    /**
     * Returns <CODE>true</CODE> if this account is a test account.
     * @return <CODE>true</CODE> if this account is a test account
     */
    public Boolean getTesting()
    {
        return testing;
    }

    /**
     * Sets the list of users.
     * @param users The list of users
     */
    public void setUsers(List<PartnerUser> users)
    {
        this.users = users;
    }

    /**
     * Adds a user to the list of users.
     * @param user The user to be added
     */
    public void addUser(PartnerUser user)
    {
        this.users.add(user);
    }

    /**
     * Returns the list of users.
     * @return The list of users
     */
    public List<PartnerUser> getUsers()
    {
        return users;
    }

    /**
     * Sets the list of subscriptions.
     * @param subscriptions The list of subscriptions
     */
    public void setSubscriptions(List<ProductSubscription> subscriptions)
    {
        this.subscriptions = subscriptions;
    }

    /**
     * Adds a subscription to the list of subscriptions.
     * @param subscription The subscription to be added
     */
    public void addSubscription(ProductSubscription subscription)
    {
        this.subscriptions.add(subscription);
    }

    /**
     * Returns the list of subscriptions.
     * @return The list of subscriptions
     */
    public List<ProductSubscription> getSubscriptions()
    {
        return subscriptions;
    }

    /**
     * Returns the status of the account.
     * @return The status of the account
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * Returns the license key of the account.
     * @return The license key of the account
     */
    public String getLicenseKey()
    {
        return licenseKey;
    }

    /**
     * Returns the API key of the account.
     * @return The API key of the account
     */
    public String getApiKey()
    {
        return apiKey;
    }

    /**
     * Returns the data access key of the account.
     * @return The data access key of the account
     */
    public String getDataAccessKey()
    {
        return dataAccessKey;
    }

    /**
     * Returns the browser monitoring key of the account.
     * @return The browser monitoring key of the account
     */
    public String getBrowserMonitoringKey()
    {
        return browserMonitoringKey;
    }

    /**
     * Returns <CODE>true</CODE> if this account has high security enabled.
     * @return <CODE>true</CODE> if this account has high security enabled
     */
    public Boolean getHighSecurity()
    {
        return highSecurity;
    }

    /**
     * Returns the partner external identifier of the account.
     * @return The partner external identifier of the account
     */
    public String getPartnerExternalIdentifier()
    {
        return partnerExternalIdentifier;
    }

    /**
     * Returns the subscription of the account.
     * @return The subscription of the account
     */
    public PartnerSubscription getSubscription()
    {
        return subscription;
    }

    /**
     * Returns the primary admin of the account.
     * @return The primary admin of the account
     */
    public AdminUser getPrimaryAdmin()
    {
        return primaryAdmin;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "PartnerAccount ["+super.toString()
            +", applicationType="+applicationType
            +", allowApiAccess="+allowApiAccess
            +", phoneNumber="+phoneNumber
            +", testing="+testing
            +", users="+users
            +", subscriptions="+subscriptions
            +", status="+status
            +", licenseKey="+licenseKey
            +", apiKey="+apiKey
            +", dataAccessKey="+dataAccessKey
            +", browserMonitoringKey="+browserMonitoringKey
            +", highSecurity="+highSecurity
            +", partnerExternalIdentifier="+partnerExternalIdentifier
            +", subscription="+subscription
            +", primaryAdmin="+primaryAdmin
            +"]";
    }

    /**
     * Returns a builder for the account.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make account construction easier.
     */
    public static class Builder
    {
        private PartnerAccount account = new PartnerAccount();

        /**
         * Sets the name of the account.
         * @param name The name of the account
         * @return This object
         */
        public Builder name(String name)
        {
            account.setName(name);
            return this;
        }

        /**
         * Sets the application type of the account.
         * @param applicationType The application type of the account
         * @return This object
         */
        public Builder applicationType(String applicationType)
        {
            account.setApplicationType(applicationType);
            return this;
        }

        /**
         * Sets the phone number of the account.
         * @param phoneNumber The phone number of the account
         * @return This object
         */
        public Builder phoneNumber(String phoneNumber)
        {
            account.setPhoneNumber(phoneNumber);
            return this;
        }

        /**
         * Set to <CODE>true</CODE> if API access to application data is allowed.
         * @param allowApiAccess <CODE>true</CODE> if API access to application data is allowed
         * @return This object
         */
        public Builder allowApiAccess(boolean allowApiAccess)
        {
            account.setAllowApiAccess(allowApiAccess);
            return this;
        }

        /**
         * Set to <CODE>true</CODE> if this account is a test account.
         * @param testing <CODE>true</CODE> if this account is a test account
         * @return This object
         */
        public Builder testing(boolean testing)
        {
            account.setTesting(testing);
            return this;
        }

        /**
         * Sets the users of the account.
         * @param users The users of the account
         * @return This object
         */
        public Builder users(List<PartnerUser> users)
        {
            account.setUsers(users);
            return this;
        }

        /**
         * Adds the given user to the list of users for the account.
         * @param user The user to add to the list of users
         * @return This object
         */
        public Builder addUser(PartnerUser user)
        {
            account.addUser(user);
            return this;
        }

        /**
         * Sets the subscriptions of the account.
         * @param subscriptions The subscriptions of the account
         * @return This object
         */
        public Builder subscriptions(List<ProductSubscription> subscriptions)
        {
            account.setSubscriptions(subscriptions);
            return this;
        }

        /**
         * Adds the given subscription to the list of subscriptions for the account.
         * @param subscription The subscription to add to the list of subscriptions
         * @return This object
         */
        public Builder addSubscription(ProductSubscription subscription)
        {
            account.addSubscription(subscription);
            return this;
        }

        /**
         * Returns the configured account instance
         * @return The account instance
         */
        public PartnerAccount build()
        {
            return account;
        }
    }
}