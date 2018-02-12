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

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic Insights dashboard presentation with drilldown.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class DrilldownPresentation extends Presentation
{
    @SerializedName("drilldown_dashboard_id")
    private Long drilldownDashboardId;

    /**
     * Default constructor.
     */
    public DrilldownPresentation()
    {
    }

    /**
     * Sets the drilldown dashboard id of the presentation.
     * @param drilldownDashboardId The drilldown dashboard id of the presentation
     */
    public void setDrilldownDashboardId(Long drilldownDashboardId)
    {
        this.drilldownDashboardId = drilldownDashboardId;
    }

    /**
     * Returns the drilldown dashboard id of the presentation.
     * @return The drilldown dashboard id of the presentation
     */
    public Long getDrilldownDashboardId()
    {
        return drilldownDashboardId;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "DrilldownPresentation ["+super.toString()
            +", drilldownDashboardId="+drilldownDashboardId
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
    public static class Builder extends PresentationBuilder<DrilldownPresentation, Builder>
    {
        private DrilldownPresentation presentation = new DrilldownPresentation();

        /**
         * Default constructor.
         */
        public Builder()
        {
            presentation(presentation);
        }

        /**
         * Sets the drilldown dashboard id of the presentation.
         * @param drilldownDashboardId The drilldown dashboard id of the presentation
         * @return This object
         */
        public Builder drilldownDashboardId(long drilldownDashboardId)
        {
            presentation.setDrilldownDashboardId(drilldownDashboardId);
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
        public DrilldownPresentation build()
        {
            return presentation;
        }
    }
}