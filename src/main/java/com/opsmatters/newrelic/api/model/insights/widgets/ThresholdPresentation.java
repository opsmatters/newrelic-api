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
 * Represents a New Relic Insights dashboard presentation with thresholds.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ThresholdPresentation extends Presentation
{
    // The field names
    public static final String THRESHOLD = "threshold";

    private Threshold threshold;

    /**
     * Default constructor.
     */
    public ThresholdPresentation()
    {
    }

    /**
     * Sets the threshold of the presentation.
     * @param threshold The threshold of the presentation
     */
    public void setThreshold(Threshold threshold)
    {
        this.threshold = threshold;
    }

    /**
     * Returns the threshold of the presentation.
     * @return The threshold of the presentation
     */
    public Threshold getThreshold()
    {
        return threshold;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "ThresholdPresentation ["+super.toString()
            +", threshold="+threshold
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
    public static class Builder extends PresentationBuilder<ThresholdPresentation, Builder>
    {
        private ThresholdPresentation presentation = new ThresholdPresentation();

        /**
         * Default constructor.
         */
        public Builder()
        {
            presentation(presentation);
        }

        /**
         * Sets the threshold of the presentation.
         * @param threshold The threshold of the presentation
         * @return This object
         */
        public Builder threshold(Threshold threshold)
        {
            presentation.setThreshold(threshold);
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
        public ThresholdPresentation build()
        {
            return presentation;
        }
    }
}