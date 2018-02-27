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

package com.opsmatters.newrelic.api.util;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;
import com.opsmatters.newrelic.api.model.IdResource;
import com.opsmatters.newrelic.api.model.NamedResource;

/**
 * Adds lookup functions to a list of New Relic resources.
 * 
 * @author Gerald Curley (opsmatters)
 */
public abstract class ResourceList<T extends IdResource>
{
    private Map<String,NamedResource> names = new LinkedHashMap<String,NamedResource>();
    private Map<Long,T> ids = new LinkedHashMap<Long,T>();

    /**
     * Default constructor.
     */
    public ResourceList()
    {
    }

    /**
     * Constructor that takes a list of resources.
     * @param resources The resources for the list
     */
    public ResourceList(List<T> resources)
    {
        add(resources);
    }

    /**
     * Adds a list of resources.
     * @param resources The resources to add
     */
    public void add(List<T> resources)
    {
        for(T resource : resources)
        {
            if(resource.getId() != null)
                ids.put(resource.getId(), resource);
            if(resource instanceof NamedResource)
                addName((NamedResource)resource);
        }
    }

    /**
     * Adds a named resource.
     * @param resource The resource to add
     */
    private void addName(NamedResource resource)
    {
        names.put(resource.getName(), resource);
    }

    /**
     * Returns the first resource for the given name.
     * @param name The name of the resource
     * @return The first resource for the given name
     */
    public T get(String name)
    {
        return (T)names.get(name);
    }

    /**
     * Returns the resource for the given id.
     * @param id The id of the resource
     * @return The resource for the given id
     */
    public T get(long id)
    {
        return ids.get(id);
    }

    /**
     * Returns the number of resources.
     * @return The number of resources
     */
    public int size()
    {
        return ids.size();
    }

    /**
     * Returns the resources that match the given comma-separated list of names.
     * @param str The comma-separated list of names (including wildcards)
     * @return The resources that match the given list
     */
    public List<T> list(String str)
    {
        Map<String,T> map = new LinkedHashMap<String,T>();

        if(str == null || str.length() == 0) // Select all resources by default
            str = "%";
        String[] tokens = str.split(",");
        for(String token : tokens)
        {
            token = token.trim();
            if(token.length() > 0)
            {
                Pattern pattern = Pattern.compile(token.replace("?", ".?").replace("%", ".*?"));
                for(NamedResource resource : names.values())
                {
                   if(pattern.matcher(resource.getName()).matches())
                        map.put(resource.getName(), (T)resource);
                }
            }
        }

        List<T> ret = new ArrayList<T>();
        ret.addAll(map.values());
        return ret;
    }

    /**
     * Returns the resources that match the given ids.
     * @param ids The list of the resource ids
     * @return The resources that match the given ids
     */
    public List<T> list(List<Long> ids)
    {
        List<T> ret = new ArrayList<T>();

        if(ids != null)
        {
            for(Long id : ids)
            {
                T resource = get(id);
                if(resource != null)
                    ret.add(resource);
            }
        }

        return ret;
    }
}