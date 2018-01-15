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

package com.opsmatters.newrelic.api;

import java.util.Collection;
import java.util.List;
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.model.users.User;
import com.opsmatters.newrelic.util.QueryParameterList;

/**
 * The set of operations used for users.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class UserOperations extends BaseFluent
{
    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param apiService The set of API operations
     */
    public UserOperations(HttpContext httpContext, NewRelicApiService apiService)
    {
        super(httpContext, apiService);
    }

    /**
     * Returns the set of users with the given query parameters.
     * @param queryParams The query parameters
     * @return The set of users
     */
    public Collection<User> list(List<String> queryParams)
    {
        return HTTP.GET("/users.json", null, queryParams, USERS).get();
    }

    /**
     * Returns the set of users.
     * @return The set of users
     */
    public Collection<User> list()
    {
        return list(null);
    }

    /**
     * Returns the user for the given user id.
     * @param userId The id for the user to return
     * @return The user
     */
    public Optional<User> show(long userId)
    {
        return HTTP.GET(String.format("/users/%d.json", userId), USER);
    }
    
    /**
     * Resets the password for the given user.
     * @param userId The id of the user to reset the password for
     * @return The user that was reset
     */
    public Optional<User> resetPassword(long userId)
    {
        return HTTP.POST(String.format("/users/%d/reset_password.json", userId), null, USER);
    }

    /**
     * Returns a builder for the user filters.
     * @return The builder instance.
     */
    public static FilterBuilder filters()
    {
        return new FilterBuilder();
    }

    /**
     * Builder to make user filter construction easier.
     */
    public static class FilterBuilder
    {
        private QueryParameterList filters = new QueryParameterList();

        /**
         * Adds the email filter to the filters.
         * @param email The email to filter on
         * @return This object
         */
        public FilterBuilder email(String email)
        {
            filters.add("filter[email]", email);
            return this;
        }

        /**
         * Adds the id filter to the filters.
         * @param ids The comma-separated list of ids to filter on
         * @return This object
         */
        public FilterBuilder ids(String ids)
        {
            filters.add("filter[ids]", ids);
            return this;
        }

        /**
         * Returns the configured filters
         * @return The filters
         */
        public List<String> build()
        {
            return filters;
        }
    }
}
