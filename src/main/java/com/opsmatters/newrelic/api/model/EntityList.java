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

package com.opsmatters.newrelic.api.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * Adds lookup functions to a list of New Relic entities.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class EntityList
{
    private Map<String,Entity> names = new LinkedHashMap<String,Entity>();
    private Map<Long,Entity> ids = new LinkedHashMap<Long,Entity>();

    /**
     * Default constructor.
     */
    public EntityList()
    {
    }

    /**
     * Constructor that takes a list of entities.
     * @param entities The entities for the list
     */
    public EntityList(List<Entity> entities)
    {
        add(entities);
    }

    /**
     * Adds a list of entities.
     * @param entities The entities to add
     */
    public void add(List<Entity> entities)
    {
        for(Entity entity : entities)
        {
            names.put(entity.getName(), entity);
            if(entity.getId() != null)
                ids.put(entity.getId(), entity);
        }
    }

    /**
     * Returns the first entity for the given name.
     * @param name The name of the entity
     * @return The first entity for the given name
     */
    public Entity get(String name)
    {
        return names.get(name);
    }

    /**
     * Returns the entity for the given id.
     * @param id The id of the entity
     * @return The entity for the given id
     */
    public Entity get(long id)
    {
        return ids.get(id);
    }

    /**
     * Returns the number of entities.
     * @return The number of entities
     */
    public int size()
    {
        return ids.size();
    }

    /**
     * Returns the entities that match the given comma-separated list of entities.
     * @param str The comma-separated list of entities (including wildcards)
     * @return The entities that match the given list
     */
    public List<Entity> list(String str)
    {
        Map<String,Entity> map = new LinkedHashMap<String,Entity>();

        if(str == null || str.length() == 0) // Select all entities by default
            str = "%";
        String[] tokens = str.split(",");
        for(String token : tokens)
        {
            token = token.trim();
            if(token.length() > 0)
            {
                Pattern pattern = Pattern.compile(token.replace("?", ".?").replace("%", ".*?"));
                for(Entity entity : names.values())
                {
                   if(pattern.matcher(entity.getName()).matches())
                        map.put(entity.getName(), entity);
                }
            }
        }

        List<Entity> ret = new ArrayList<Entity>();
        ret.addAll(map.values());
        return ret;
    }

    /**
     * Returns the entities that match the given ids.
     * @param ids The list of the entity ids
     * @return The entities that match the given ids
     */
    public List<Entity> list(List<Long> ids)
    {
        List<Entity> ret = new ArrayList<Entity>();

        if(ids != null)
        {
            for(Long id : ids)
            {
                Entity entity = get(id);
                if(entity != null)
                    ret.add(entity);
            }
        }

        return ret;
    }
}