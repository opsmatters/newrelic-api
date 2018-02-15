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

package com.opsmatters.newrelic.api.model.insights;

import java.util.List;
import java.util.Date;
import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.IdResource;
import com.opsmatters.newrelic.api.model.insights.widgets.Widget;

/**
 * Represents a New Relic Insights dashboard.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Dashboard extends IdResource
{
    // The field names
    public static final String TITLE = "title";
    public static final String ICON = "icon";
    public static final String VISIBILITY = "visibility";
    public static final String EDITABLE = "editable";
    public static final String FILTER = "filter";
    public static final String WIDGETS = "widgets";

    private String title;

    private String icon;

    private String visibility;

    private String editable;

    private Metadata metadata;

    private Filter filter;

    private List<Widget> widgets;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("ui_url")
    private String uiUrl;

    @SerializedName("api_url")
    private String apiUrl;

    @SerializedName("owner_email")
    private String ownerEmail;

    /**
     * Represents the available icons for the dashboard.  
     */
    public enum Icon
    {
        NONE("none"),
        ADJUST("adjust"),
        ARCHIVE("archive"),
        BAR_CHART("bar-chart"),
        BELL("bell"),
        BOLT("bolt"),
        BUG("bug"),
        BULLHORN("bullhorn"),
        BULLSEYE("bullseye"),
        CLOCK_O("clock-o"),
        CLOUD("cloud"),
        COG("cog"),
        COMMENTS_O("comments-o"),
        CROSSHAIRS("crosshairs"),
        DASHBOARD("dashboard"),
        ENVELOPE("envelope"),
        FIRE("fire"),
        FLAG("flag"),
        FLASK("flask"),
        GLOBE("globe"),
        HEART("heart"),
        LEAF("leaf"),
        LEGAL("legal"),
        LIFE_RING("life-ring"),
        LINE_CHART("line-chart"),
        MAGIC("magic"),
        MOBILE("mobile"),
        MONEY("money"),
        PAPER_PLANE("paper-plane"),
        PIE_CHART("pie-chart"),
        PUZZLE_PIECE("puzzle-piece"),
        ROAD("road"),
        ROCKET("rocket"),
        SHOPPING_CART("shopping-cart"),
        SITEMAP("sitemap"),
        SLIDERS("sliders"),
        TABLET("tablet"),
        THUMBS_DOWN("thumbs-down"),
        THUMBS_UP("thumbs-up"),
        TROPHY("trophy"),
        USD("usd"),
        USER("user"),
        USERS("users");

        Icon(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        private String value;
    }

    /**
     * Represents the available options for the dashboard visibility.  
     */
    public enum Visibility
    {
        OWNER("owner"),
        ALL("all");

        Visibility(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        private String value;
    }

    /**
     * Represents the available options for the dashboard editability.  
     */
    public enum Editable
    {
        READ_ONLY("read_only"),
        EDITABLE_BY_OWNER("editable_by_owner"),
        EDITABLE_BY_ALL("editable_by_all");

        Editable(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        private String value;
    }

    /**
     * Default constructor.
     */
    public Dashboard()
    {
    }

    /**
     * Sets the title of the dashboard.
     * @param title The title of the dashboard
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Returns the title of the dashboard.
     * @return The title of the dashboard
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets the icon of the dashboard.
     * @param icon The icon of the dashboard
     */
    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    /**
     * Sets the icon of the dashboard.
     * @param icon The icon of the dashboard
     */
    public void setIcon(Icon icon)
    {
        setIcon(icon.value());
    }

    /**
     * Returns the icon of the dashboard.
     * @return The icon of the dashboard
     */
    public String getIcon()
    {
        return icon;
    }

    /**
     * Sets the visibility of the dashboard.
     * @param visibility The visibility of the dashboard
     */
    public void setVisibility(String visibility)
    {
        this.visibility = visibility;
    }

    /**
     * Sets the visibility of the dashboard.
     * @param visibility The visibility of the dashboard
     */
    public void setVisibility(Visibility visibility)
    {
        setVisibility(visibility.value());
    }

    /**
     * Returns the visibility of the dashboard.
     * @return The visibility of the dashboard
     */
    public String getVisibility()
    {
        return visibility;
    }

    /**
     * Sets the editability of the dashboard.
     * @param editable The editability of the dashboard
     */
    public void setEditable(String editable)
    {
        this.editable = editable;
    }

    /**
     * Sets the editability of the dashboard.
     * @param editable The editability of the dashboard
     */
    public void setEditable(Editable editable)
    {
        setEditable(editable.value());
    }

    /**
     * Returns the editability of the dashboard.
     * @return The editability of the dashboard
     */
    public String getEditable()
    {
        return editable;
    }

    /**
     * Sets the metadata of the dashboard.
     * @param metadata The metadata of the dashboard
     */
    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    /**
     * Returns the metadata of the dashboard.
     * @return The metadata of the dashboard
     */
    public Metadata getMetadata()
    {
        return metadata;
    }

    /**
     * Sets the filter of the dashboard.
     * @param filter The filter of the dashboard
     */
    public void setFilter(Filter filter)
    {
        this.filter = filter;
    }

    /**
     * Returns the filter of the dashboard.
     * @return The filter of the dashboard
     */
    public Filter getFilter()
    {
        return filter;
    }

    /**
     * Sets the list of widgets.
     * @param widgets The list of widgets
     */
    public void setWidgets(List<Widget> widgets)
    {
        this.widgets = widgets;
    }

    /**
     * Adds a widget to the list of widgets.
     * @param widget The widget to add
     */
    public void addWidget(Widget widget)
    {
        this.widgets.add(widget);
    }

    /**
     * Returns the list of widgets.
     * @return The list of widgets
     */
    public List<Widget> getWidgets()
    {
        return widgets;
    }

    /**
     * Returns the date the dashboard was created.
     * @return The date the dashboard was created
     */
    public Date getCreatedAt()
    {
        return createdAt;
    }

    /**
     * Returns the date the dashboard was last updated.
     * @return The date the dashboard was last updated
     */
    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    /**
     * Returns the UI URL of the dashboard.
     * @return The UI URL of the dashboard
     */
    public String getUiUrl()
    {
        return uiUrl;
    }

    /**
     * Returns the API URL of the dashboard.
     * @return The API URL of the dashboard
     */
    public String getApiUrl()
    {
        return apiUrl;
    }

    /**
     * Returns the owner email of the dashboard.
     * @return The owner email URL of the dashboard
     */
    public String getOwnerEmail()
    {
        return ownerEmail;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Dashboard ["+super.toString()
            +", title="+title
            +", icon="+icon
            +", visibility="+visibility
            +", editable="+editable
            +", metadata="+metadata
            +", filter="+filter
            +", widgets="+widgets
            +", createdAt="+createdAt
            +", updatedAt="+updatedAt
            +", uiUrl="+uiUrl
            +", apiUrl="+apiUrl
            +", ownerEmail="+ownerEmail
            +"]";
    }

    /**
     * Returns a builder for the dashboard.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make dashboard construction easier.
     */
    public static class Builder
    {
        private Dashboard dashboard = new Dashboard();
        private Metadata metadata = new Metadata();
        private Filter filter = new Filter();

        /**
         * Default constructor.
         */
        public Builder()
        {
            dashboard.setMetadata(metadata);
            dashboard.setFilter(filter);
        }

        /**
         * Sets the title of the dashboard.
         * @param title The title of the dashboard
         * @return This object
         */
        public Builder title(String title)
        {
            dashboard.setTitle(title);
            return this;
        }

        /**
         * Sets the icon of the dashboard.
         * @param icon The icon of the dashboard
         * @return This object
         */
        public Builder icon(String icon)
        {
            dashboard.setIcon(icon);
            return this;
        }

        /**
         * Sets the icon of the dashboard.
         * @param icon The icon of the dashboard
         * @return This object
         */
        public Builder icon(Icon icon)
        {
            dashboard.setIcon(icon);
            return this;
        }

        /**
         * Sets the version of the dashboard.
         * @param version The version of the dashboard
         * @return This object
         */
        public Builder version(int version)
        {
            metadata.setVersion(version);
            return this;
        }

        /**
         * Sets the visibility of the dashboard.
         * @param visibility The visibility of the dashboard
         * @return This object
         */
        public Builder visibility(String visibility)
        {
            dashboard.setVisibility(visibility);
            return this;
        }

        /**
         * Sets the visibility of the dashboard.
         * @param visibility The visibility of the dashboard
         * @return This object
         */
        public Builder visibility(Visibility visibility)
        {
            dashboard.setVisibility(visibility);
            return this;
        }

        /**
         * Sets the visibility of the dashboard to "owner".
         * @return This object
         */
        public Builder ownerVisibility()
        {
            dashboard.setVisibility(Visibility.OWNER);
            return this;
        }

        /**
         * Sets the visibility of the dashboard to "all".
         * @return This object
         */
        public Builder allVisibility()
        {
            dashboard.setVisibility(Visibility.ALL);
            return this;
        }

        /**
         * Sets the editability of the dashboard.
         * @param editable The editability of the dashboard
         * @return This object
         */
        public Builder editable(String editable)
        {
            dashboard.setEditable(editable);
            return this;
        }

        /**
         * Sets the editability of the dashboard.
         * @param editable The editability of the dashboard
         * @return This object
         */
        public Builder editable(Editable editable)
        {
            dashboard.setEditable(editable);
            return this;
        }

        /**
         * Sets the editability of the dashboard to "read_only".
         * @return This object
         */
        public Builder readOnlyEditable()
        {
            dashboard.setEditable(Editable.READ_ONLY);
            return this;
        }

        /**
         * Sets the editability of the dashboard to "editable_by_owner".
         * @return This object
         */
        public Builder ownerEditable()
        {
            dashboard.setEditable(Editable.EDITABLE_BY_OWNER);
            return this;
        }

        /**
         * Sets the editability of the dashboard to "editable_by_all".
         * @return This object
         */
        public Builder allEditable()
        {
            dashboard.setEditable(Editable.EDITABLE_BY_ALL);
            return this;
        }

        /**
         * Sets the list of widgets.
         * @param widgets The list of widgets
         * @return This object
         */
        public Builder widgets(List<Widget> widgets)
        {
            dashboard.setWidgets(widgets);
            return this;
        }

        /**
         * Adds a widget to the list of widgets.
         * @param widget The widget to add
         * @return This object
         */
        public Builder addWidget(Widget widget)
        {
            dashboard.addWidget(widget);
            return this;
        }

        /**
         * Sets the filter for the dashboard.
         * @param filter The filter for the dashboard
         * @return This object
         */
        public Builder filter(Filter filter)
        {
            dashboard.setFilter(filter);
            return this;
        }

        /**
         * Adds the given filter event type and attribute to the dashboard.
         * @param eventType The event type for the filter
         * @param attribute The attribute for the filter
         * @return This object
         */
        public Builder addFilter(String eventType, String attribute)
        {
            if(eventType != null)
                dashboard.getFilter().addEventType(eventType);
            if(attribute != null)
                dashboard.getFilter().addAttribute(attribute);
            return this;
        }

        /**
         * Sets the given filter event types and attributes on the dashboard.
         * @param eventTypes The list of event types for the filter
         * @param attributes The list of attributes for the filter
         * @return This object
         */
        public Builder setFilter(List<String> eventTypes, List<String> attributes)
        {
            dashboard.getFilter().setEventTypes(eventTypes);
            dashboard.getFilter().setAttributes(attributes);
            return this;
        }

        /**
         * Returns the configured dashboard instance
         * @return The dashboard instance
         */
        public Dashboard build()
        {
            return dashboard;
        }
    }
}