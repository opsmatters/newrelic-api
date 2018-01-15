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

package com.opsmatters.newrelic.api.model.users;

import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.NamedResource;

/**
 * Represents a New Relic user.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class User extends NamedResource
{
    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    private String email;

    private String role;

    /**
     * Default constructor.
     */
    public User()
    {
    }
    
    /**
     * Sets the first name of the user.
     * @param firstName The first name of the user
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Returns the first name of the user.
     * @return The first name of the user
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Sets the last name of the user.
     * @param lastName The last name of the user
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Returns the last name of the user.
     * @return The last name of the user
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Sets the email address of the user.
     * @param email The email address of the user
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Returns the email address of the user.
     * @return The email address of the user
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Sets the role of the user.
     * @param role The role of the user
     */
    public void setRole(String role)
    {
        this.role = role;
    }

    /**
     * Returns the role of the user.
     * @return The role of the user
     */
    public String getRole()
    {
        return role;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "User ["+super.toString()
            +", firstName="+firstName
            +", lastName="+lastName
            +", email="+email
            +", role="+role
            +"]";
    }
}