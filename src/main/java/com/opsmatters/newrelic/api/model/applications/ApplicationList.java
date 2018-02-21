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

package com.opsmatters.newrelic.api.model.applications;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * Adds lookup functions to a list of New Relic applications.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ApplicationList
{
    private Map<String,Application> names = new LinkedHashMap<String,Application>();
    private Map<Long,Application> ids = new LinkedHashMap<Long,Application>();

    /**
     * Constructor that takes a list of applications.
     * @param applications The applications for the list
     */
    public ApplicationList(List<Application> applications)
    {
        for(Application application : applications)
        {
            names.put(application.getName(), application);
            if(application.getId() != null)
                ids.put(application.getId(), application);
        }
    }

    /**
     * Returns the first application for the given name.
     * @param name The name of the application
     * @return The first application for the given name
     */
    public Application get(String name)
    {
        return names.get(name);
    }

    /**
     * Returns the application for the given id.
     * @param id The id of the application
     * @return The application for the given name
     */
    public Application get(long id)
    {
        return ids.get(id);
    }

    /**
     * Returns the applications that match the given name.
     * @param name The name of the applications (including wildcards)
     * @return The applications that match the given name
     */
    public List<Application> list(String name)
    {
        List<Application> ret = new ArrayList<Application>();

        if(name == null || name.length() == 0) // Select all applications by default
            name = "%";
        Pattern pattern = Pattern.compile(name.replace("?", ".?").replace("%", ".*?"));
        for(Application application : names.values())
        {
            if(pattern.matcher(application.getName()).matches())
                ret.add(application);
        }

        return ret;
    }
}