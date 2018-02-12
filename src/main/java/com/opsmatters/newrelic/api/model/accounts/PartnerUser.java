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

/**
 * Represents a New Relic account partner user.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PartnerUser extends User
{
    private String password;

    private Boolean owner;

    /**
     * Default constructor.
     */
    public PartnerUser()
    {
    }
    
    /**
     * Sets the password of the user.
     * @param password The password of the user
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Returns the password of the user.
     * @return The password of the user
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Set to <CODE>true</CODE> if the user is an account owner.
     * @param owner <CODE>true</CODE> if the user is an account owner
     */
    public void setOwner(Boolean owner)
    {
        this.owner = owner;
    }

    /**
     * Returns <CODE>true</CODE> if the user is an account owner.
     * @return <CODE>true</CODE> if the user is an account owner
     */
    public Boolean getOwner()
    {
        return owner;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "PartnerUser ["+super.toString()
            +", owner="+owner
            +", password="+password
            +"]";
    }

    /**
     * Returns a builder for the user.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make user construction easier.
     */
    public static class Builder
    {
        private PartnerUser user = new PartnerUser();

        /**
         * Sets the first name of the user.
         * @param firstName The first name of the user
         * @return This object
         */
        public Builder firstName(String firstName)
        {
            user.setFirstName(firstName);
            return this;
        }

        /**
         * Sets the last name of the user.
         * @param lastName The last name of the user
         * @return This object
         */
        public Builder lastName(String lastName)
        {
            user.setLastName(lastName);
            return this;
        }

        /**
         * Sets the email of the user.
         * @param email The email of the user
         * @return This object
         */
        public Builder email(String email)
        {
            user.setEmail(email);
            return this;
        }

        /**
         * Sets the role of the user.
         * @param role The role of the user
         * @return This object
         */
        public Builder role(String role)
        {
            user.setRole(role);
            return this;
        }

        /**
         * Sets the role of the user.
         * @param role The role of the user
         * @return This object
         */
        public Builder role(Role role)
        {
            user.setRole(role);
            return this;
        }

        /**
         * Sets the password of the user.
         * @param password The password of the user
         * @return This object
         */
        public Builder password(String password)
        {
            user.setPassword(password);
            return this;
        }

        /**
         * Set to <CODE>true</CODE> if the user is an account owner.
         * @param owner <CODE>true</CODE> if the user is an account owner
         * @return This object
         */
        public Builder owner(boolean owner)
        {
            user.setOwner(owner);
            return this;
        }

        /**
         * Returns the configured user instance
         * @return The user instance
         */
        public PartnerUser build()
        {
            return user;
        }
    }
}