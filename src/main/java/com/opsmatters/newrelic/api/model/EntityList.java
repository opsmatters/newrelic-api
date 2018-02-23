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
     * @return The entity for the given name
     */
    public Entity get(long id)
    {
        return ids.get(id);
    }

    /**
     * Returns the entities that match the given name.
     * @param name The name of the entities (including wildcards)
     * @return The entities that match the given name
     */
    public List<Entity> list(String name)
    {
        List<Entity> ret = new ArrayList<Entity>();

        if(name == null || name.length() == 0) // Select all entities by default
            name = "%";
        Pattern pattern = Pattern.compile(name.replace("?", ".?").replace("%", ".*?"));
        for(Entity entity : names.values())
        {
            if(pattern.matcher(entity.getName()).matches())
                ret.add(entity);
        }

        return ret;
    }
}