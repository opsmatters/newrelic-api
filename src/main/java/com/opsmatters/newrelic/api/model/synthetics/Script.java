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

package com.opsmatters.newrelic.api.model.synthetics;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a New Relic Synthetics script.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Script
{
    private String scriptText;
    private List<ScriptLocation> scriptLocations = new ArrayList<ScriptLocation>();

    /**
     * Default constructor.
     */
    public Script()
    {
    }

    /**
     * Sets the script text.
     * @param scriptText The script text
     */
    public void setScriptText(String scriptText)
    {
        this.scriptText = scriptText;
    }
    
    /**
     * Returns the script text.
     * @return The script text
     */
    public String getScriptText()
    {
        return scriptText;
    }

    /**
     * Sets the list of locations for the script.
     * @param scriptLocations The list of locations for the script
     */
    public void setScriptLocations(List<ScriptLocation> scriptLocations)
    {
        this.scriptLocations = scriptLocations;
    }

    /**
     * Returns the list of locations for the script.
     * @return The list of locations for the script
     */
    public List<ScriptLocation> getScriptLocations()
    {
        return scriptLocations;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Script [scriptText="+scriptText
            +", scriptLocations="+scriptLocations
            +"]";
    }

    /**
     * Returns a builder for the script.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make script construction easier.
     */
    public static class Builder
    {
        private Script script = new Script();

        /**
         * Sets the script text.
         * @param scriptText The script text
         * @return This object
         */
        public Builder scriptText(String scriptText)
        {
            script.setScriptText(scriptText);
            return this;
        }

        /**
         * Sets the script locations.
         * @param scriptLocations The script locations
         * @return This object
         */
        public Builder scriptLocations(List<ScriptLocation> scriptLocations)
        {
            script.setScriptLocations(scriptLocations);
            return this;
        }

        /**
         * Adds a script location.
         * @param scriptLocation The script location
         * @return This object
         */
        public Builder addScriptLocation(ScriptLocation scriptLocation)
        {
            script.getScriptLocations().add(scriptLocation);
            return this;
        }

        /**
         * Returns the configured script instance
         * @return The script instance
         */
        public Script build()
        {
            return script;
        }
    }
}