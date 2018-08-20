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

package com.opsmatters.newrelic.api.model.insights;

import java.util.List;
import java.util.Map;

/**
 * Represents a New Relic Insights query data.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class QueryData
{
    private List<Map<String,Object>> results;
    
    private List<Map<String,Object>> facets;
    
    private Map<String,Object> totalResult;

    private Map<String,Object> performanceStats;

    private Map<String,Object> metadata;

    /**
     * Default constructor.
     */
    public QueryData()
    {
    }

    /**
     * Returns the list of results. Not applicable for a faceted query.
     * @return The list of results
     */
    public List<Map<String,Object>> getResults()
    {
        return results;
    }
    
    /**
     * Returns the list of facets. Applicable only for a faceted query.
     * @return The list of facets
     */
    public List<Map<String,Object>> getFacets() {
            return facets;
    }

    /**
     * Returns the result for all entities that match within the query timeframe, regardless of the FACET clause. 
     * Applicable only for a faceted query. For instance, the <b>total result</b> of this faceted query:
     * <br></br>
     * <code>SELECT count(*) FROM PageView FACET pageUrl</code> 
     * <br></br>
     * is the same as the <b>result</b> of this non-faceted query:
     * <br></br>
     * <code>SELECT count(*) FROM PageView</code>
     * @return the total results for a faceted query
     */
     public Map<String,Object> getTotalResult() {
    	return totalResult;
    }
    
    /**
     * Returns the list of performance stats.
     * @return The list of performance stats
     */
    public Map<String,Object> getPerformanceStats()
    {
        return performanceStats;
    }

    /**
     * Returns the list of metadata.
     * @return The list of metadata
     */
    public Map<String,Object> getMetadata()
    {
        return metadata;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "QueryData [results="+results
           +", facets="+facets
           +", totalResult="+totalResult
           +", performanceStats="+performanceStats
            +", metadata="+metadata
            +"]";
    }
}