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
import com.opsmatters.newrelic.api.model.entity.MobileApplication;
import com.opsmatters.newrelic.api.model.entity.Metric;
import com.opsmatters.newrelic.api.model.entity.MetricData;
import com.opsmatters.newrelic.util.QueryParameterList;

/**
 * The set of operations used for mobile applications.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class MobileApplicationOperations extends BaseFluent
{
    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param apiService The set of API operations
     */
    public MobileApplicationOperations(HttpContext httpContext, NewRelicApiService apiService)
    {
        super(httpContext, apiService);
    }

    /**
     * Returns the set of Mobile applications.
     * @return The set of applications
     */
    public Collection<MobileApplication> list()
    {
        return HTTP.GET("/mobile_applications.json", MOBILE_APPLICATIONS).get();
    }

    /**
     * Returns the Mobile application for the given application id.
     * @param id The id for the application to return
     * @return The application
     */
    public Optional<MobileApplication> show(long id)
    {
        return HTTP.GET(String.format("/mobile_applications/%d.json", id), MOBILE_APPLICATION);
    }

    /**
     * Returns the set of metrics for the given application.
     * @param id The id of the application to return metrics for
     * @param name Filter metrics by name (or part of name)
     * @return The set of metrics
     */
    public Collection<Metric> metricNames(long id, String name)
    {
        QueryParameterList queryParams = new QueryParameterList();
        if(name != null && name.length() > 0)
            queryParams.add("name", name);
        return HTTP.GET(String.format("/mobile_applications/%d/metrics.json", id), null, queryParams, METRICS).get();
    }

    /**
     * Returns the set of metrics for the given application.
     * @param id The id of the application to return metrics for
     * @return The set of metrics
     */
    public Collection<Metric> metricNames(long id)
    {
        return metricNames(id, null);
    }

    /**
     * Returns the set of metric data for the given application.
     * @param id The id of the application to return metric data for
     * @param queryParams The query parameters
     * @return The set of metric data
     */
    public Optional<MetricData> metricData(long id, List<String> queryParams)
    {
        return HTTP.GET(String.format("/mobile_applications/%d/metrics/data.json", id), null, queryParams, METRIC_DATA);
    }

    /**
     * Returns a builder for the metric data parameters.
     * @return The builder instance.
     */
    public static MetricParameterBuilder metrics()
    {
        return new MetricParameterBuilder();
    }
}
