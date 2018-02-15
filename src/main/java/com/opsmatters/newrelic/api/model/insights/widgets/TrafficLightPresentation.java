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
import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic Insights dashboard presentation with traffic lights.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class TrafficLightPresentation extends Presentation
{
    // The field names
    public static final String TRAFFIC_LIGHT = "traffic_light";

    @SerializedName("traffic_lights")
    List<TrafficLight> trafficLights;

    /**
     * Default constructor.
     */
    public TrafficLightPresentation()
    {
    }

    /**
     * Sets the traffic lights of the presentation.
     * @param trafficLights The traffic lights of the presentation
     */
    public void setTrafficLights(List<TrafficLight> trafficLights)
    {
        this.trafficLights = trafficLights;
    }

    /**
     * Adds a traffic light to the traffic lights.
     * @param trafficLight The traffic light to add to the traffic lights
     */
    public void addTrafficLight(TrafficLight trafficLight)
    {
        if(this.trafficLights == null)
            this.trafficLights = new ArrayList<TrafficLight>();
        this.trafficLights.add(trafficLight);
    }

    /**
     * Returns the traffic lights of the presentation.
     * @return The traffic lights of the presentation
     */
    public List<TrafficLight> getTrafficLights()
    {
        return trafficLights;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "TrafficLightPresentation ["+super.toString()
            +", trafficLights="+trafficLights
            +"]";
    }

    /**
     * Returns a builder for the presentation.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make presentation construction easier.
     */
    public static class Builder extends PresentationBuilder<TrafficLightPresentation, Builder>
    {
        private TrafficLightPresentation presentation = new TrafficLightPresentation();

        /**
         * Default constructor.
         */
        public Builder()
        {
            presentation(presentation);
        }

        /**
         * Sets the traffic lights of the presentation.
         * @param trafficLights The traffic lights of the presentation
         * @return This object
         */
        public Builder trafficLights(List<TrafficLight> trafficLights)
        {
            presentation.setTrafficLights(trafficLights);
            return this;
        }

        /**
         * Adds a traffic light to the traffic lights.
         * @param trafficLight The traffic light to add to the traffic lights
         * @return This object
         */
        public Builder addTrafficLight(TrafficLight trafficLight)
        {
            presentation.addTrafficLight(trafficLight);
            return this;
        }

        /**
         * Returns this object.
         * @return This object
         */
        @Override
        protected Builder self()
        {
            return this;
        }

        /**
         * Returns the configured presentation instance
         * @return The presentation instance
         */
        @Override
        public TrafficLightPresentation build()
        {
            return presentation;
        }
    }
}