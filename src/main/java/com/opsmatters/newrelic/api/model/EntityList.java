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
import com.opsmatters.newrelic.api.util.ResourceList;

/**
 * Adds lookup functions to a list of New Relic entities.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class EntityList extends ResourceList<Entity>
{
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
}