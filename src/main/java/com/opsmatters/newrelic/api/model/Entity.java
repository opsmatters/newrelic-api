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

/**
 * Represents the base class for all New Relic entities.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public abstract class Entity extends NamedResource
{
    private transient String type;

    /**
     * Default constructor.
     * @param type The type of the entity
     */
    public Entity(String type)
    {
        setType(type);
    }

    /**
     * Sets the entity type.
     * @param type The entity type
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * Returns the entity type.
     * @return The entity type
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return super.toString()
            +", type="+getType();
    }

    /**
     * Builder to make entity construction easier.
     */
    protected abstract static class Builder<T extends Entity, B extends Builder<T,B>>
    {
        private Entity entity;

        /**
         * Sets the entity.
         * @param entity The entity
         * @return This object
         */
        public B entity(Entity entity)
        {
            this.entity = entity;
            return self();
        }

        /**
         * Sets the id of the entity.
         * @param id The id of the entity
         * @return This object
         */
        public B id(long id)
        {
            entity.setId(id);
            return self();
        }

        /**
         * Sets the name of the entity.
         * @param name The name of the entity
         * @return This object
         */
        public B name(String name)
        {
            entity.setName(name);
            return self();
        }

        /**
         * Returns this object.
         * @return This object
         */
        protected abstract B self();

        /**
         * Returns the configured entity instance
         * @return The entity instance
         */
        public abstract T build();
    }
}