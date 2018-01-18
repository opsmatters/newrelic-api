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

package com.opsmatters.newrelic.api.operations;

import java.util.Collection;
import java.util.List;
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.services.NewRelicService;
import com.opsmatters.newrelic.api.model.accounts.UsageData;
import com.opsmatters.newrelic.util.QueryParameterList;
import com.opsmatters.newrelic.util.Utils;

/**
 * The set of operations used for usages.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class UsageOperations extends BaseFluent
{
    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param service The set of API operations
     */
    public UsageOperations(HttpContext httpContext, NewRelicService service)
    {
        super(httpContext, service);
    }

    /**
     * Returns the set of usages with the given parameters.
     * @param product The product for the usages
     * @param startDate The start date for the usages (in milliseconds)
     * @param endDate The start date for the usages (in milliseconds)
     * @param includeSubaccounts <CODE>true</CODE> if sub-accounts should be included in the usages
     * @return The set of usages
     */
    public Optional<UsageData> list(String product, long startDate, long endDate, boolean includeSubaccounts)
    {
        return list(product, 
            startDate > 0L ? Utils.getFormattedDate(startDate) : null, 
            endDate > 0L ? Utils.getFormattedDate(endDate) : null, 
            includeSubaccounts);
    }

    /**
     * Returns the set of usages with the given parameters.
     * @param product The product for the usages
     * @param startDate The start date for the usages (YYYY-MM-DD)
     * @param endDate The start date for the usages (YYYY-MM-DD)
     * @param includeSubaccounts <CODE>true</CODE> if sub-accounts should be included in the usages
     * @return The set of usages
     */
    public Optional<UsageData> list(String product, String startDate, String endDate, boolean includeSubaccounts)
    {
        QueryParameterList queryParams = new QueryParameterList();
        if(startDate != null)
            queryParams.add("start_date", startDate);
        if(endDate != null)
            queryParams.add("end_date", endDate);
        queryParams.add("include_subaccounts", includeSubaccounts);
        return HTTP.GET(String.format("/v2/usages/%s.json", product), null, queryParams, USAGE_DATA);
    }
}
