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

/**
 * Represents a New Relic Insights widget presentation threshold.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Threshold
{
    // The field names
    public static final String RED = "red";
    public static final String YELLOW = "yellow";

    private Integer red;

    private Integer yellow;

    /**
     * Default constructor.
     */
    public Threshold()
    {
    }

    /**
     * Sets the red threshold of the presentation.
     * @param red The red threshold of the presentation
     */
    public void setRed(Integer red)
    {
        this.red = red;
    }

    /**
     * Returns the red threshold of the presentation.
     * @return The red threshold of the presentation
     */
    public Integer getRed()
    {
        return red;
    }

    /**
     * Sets the yellow threshold of the presentation.
     * @param yellow The yellow threshold of the presentation
     */
    public void setYellow(Integer yellow)
    {
        this.yellow = yellow;
    }

    /**
     * Returns the yellow threshold of the presentation.
     * @return The yellow threshold of the presentation
     */
    public Integer getYellow()
    {
        return yellow;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Threshold [red="+red
            +", yellow="+yellow
            +"]";
    }

    /**
     * Returns a builder for the threshold.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make threshold construction easier.
     */
    public static class Builder
    {
        private Threshold threshold = new Threshold();

        /**
         * Sets the red threshold of the presentation.
         * @param red The red threshold of the presentation
         * @return This object
         */
        public Builder red(int red)
        {
            threshold.setRed(red);
            return this;
        }

        /**
         * Sets the yellow threshold of the presentation.
         * @param yellow The yellow threshold of the presentation
         * @return This object
         */
        public Builder yellow(int yellow)
        {
            threshold.setYellow(yellow);
            return this;
        }

        /**
         * Returns the configured threshold instance
         * @return The threshold instance
         */
        public Threshold build()
        {
            return threshold;
        }
    }
}