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

package com.opsmatters.newrelic.api.model.insights.widgets;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a New Relic Insights dashboard traffic light.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class TrafficLight
{
    private String id;

    private String title;

    private String subtitle;

    private List<TrafficLightState> states;

    /**
     * Default constructor.
     */
    public TrafficLight()
    {
    }

    /**
     * Sets the id of the traffic light.
     * @param id The id of the traffic light
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * Returns the id of the traffic light.
     * @return The id of the traffic light
     */
    public String getId()
    {
        return id;
    }

    /**
     * Sets the title of the traffic light.
     * @param title The title of the traffic light
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Returns the title of the traffic light.
     * @return The title of the traffic light
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets the subtitle of the traffic light.
     * @param subtitle The subtitle of the traffic light
     */
    public void setSubtitle(String subtitle)
    {
        this.subtitle = subtitle;
    }

    /**
     * Returns the subtitle of the traffic light.
     * @return The subtitle of the traffic light
     */
    public String getSubtitle()
    {
        return subtitle;
    }

    /**
     * Sets the states of the traffic light.
     * @param states The states of the traffic light
     */
    public void setStates(List<TrafficLightState> states)
    {
        this.states = states;
    }

    /**
     * Adds a state to the traffic light.
     * @param state The state to add to the traffic light
     */
    public void addState(TrafficLightState state)
    {
        if(this.states == null)
            this.states = new ArrayList<TrafficLightState>();
        this.states.add(state);
    }

    /**
     * Returns the states of the traffic light.
     * @return The states of the traffic light
     */
    public List<TrafficLightState> getStates()
    {
        return states;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "TrafficLight [id="+id
            +", title="+title
            +", subtitle="+subtitle
            +", states="+states
            +"]";
    }

    /**
     * Returns a builder for the traffic light.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make traffic light construction easier.
     */
    public static class Builder
    {
        private TrafficLight trafficLight = new TrafficLight();

        /**
         * Sets the id of the traffic light.
         * @param id The id of the traffic light
         * @return This object
         */
        public Builder id(String id)
        {
            trafficLight.setId(id);
            return this;
        }

        /**
         * Sets the title of the traffic light.
         * @param title The title of the traffic light
         * @return This object
         */
        public Builder title(String title)
        {
            trafficLight.setTitle(title);
            return this;
        }

        /**
         * Sets the subtitle of the traffic light.
         * @param subtitle The subtitle of the traffic light
         * @return This object
         */
        public Builder subtitle(String subtitle)
        {
            trafficLight.setSubtitle(subtitle);
            return this;
        }

        /**
         * Sets the states of the traffic light.
         * @param states The states of the traffic light
         * @return This object
         */
        public Builder states(List<TrafficLightState> states)
        {
            trafficLight.setStates(states);
            return this;
        }

        /**
         * Adds a state to the traffic light.
         * @param state The state to add to the traffic light
         * @return This object
         */
        public Builder addState(TrafficLightState state)
        {
            trafficLight.addState(state);
            return this;
        }

        /**
         * Returns the configured traffic light instance
         * @return The traffic light instance
         */
        public TrafficLight build()
        {
            return trafficLight;
        }
    }
}