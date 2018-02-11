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
 * Represents a New Relic Insights dashboard widget.
 * 
 * @author Gerald Curley (opsmatters)
 */
public abstract class Widget
{
    private String visualization;

    @SerializedName("account_id")
    private Long accountId;

    private List<WidgetData> data = new ArrayList<WidgetData>();

    private Presentation presentation;

    private Layout layout;

    /**
     * Default constructor.
     */
    public Widget()
    {
    }

    /**
     * Sets the visualization of the widget.
     * @param visualization The visualization of the widget
     */
    public void setVisualization(String visualization)
    {
        this.visualization = visualization;
    }

    /**
     * Returns the visualization of the widget.
     * @return The visualization of the widget
     */
    public String getVisualization()
    {
        return visualization;
    }

    /**
     * Sets the account id of the widget.
     * @param accountId The account id of the widget
     */
    public void setAccountId(long accountId)
    {
        this.accountId = accountId;
    }

    /**
     * Returns the account id of the widget.
     * @return The account id of the widget
     */
    public long getAccountId()
    {
        return accountId;
    }

    /**
     * Sets the list of widget data items.
     * @param data The list of widget data items
     */
    public void setData(List<WidgetData> data)
    {
        this.data = data;
    }

    /**
     * Adds an item to the list of widget data items.
     * @param data The data item to be added
     */
    public void addData(WidgetData data)
    {
        this.data.add(data);
    }

    /**
     * Returns the list of widget data items.
     * @return The list of widget data items
     */
    public List<WidgetData> getData()
    {
        return data;
    }

   /**
     * Returns <CODE>true</CODE> if the given data item is contained in the list of widget data items.
     * @param data The data item to be checked
     * @return <CODE>true</CODE> if the given data item is contained in the list of widget data items
     */
    public boolean containsData(WidgetData data)
    {
        return this.data != null && this.data.contains(data);
    }

    /**
     * Sets the presentation of the widget.
     * @param presentation The presentation of the widget
     */
    public void setPresentation(Presentation presentation)
    {
        this.presentation = presentation;
    }

    /**
     * Returns the presentation of the widget.
     * @return The presentation of the widget
     */
    public Presentation getPresentation()
    {
        return presentation;
    }

    /**
     * Sets the layout of the widget.
     * @param layout The layout of the widget
     */
    public void setLayout(Layout layout)
    {
        this.layout = layout;
    }

    /**
     * Returns the layout of the widget.
     * @return The layout of the widget
     */
    public Layout getLayout()
    {
        return layout;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "visualization="+visualization
            +", accountId="+accountId
            +", data="+data
            +", presentation="+presentation
            +", layout="+layout;
    }

    /**
     * Builder to make widget construction easier.
     */
    public abstract static class Builder<T extends Widget, B extends Builder<T,B>>
    {
        private Widget widget;
        private Layout layout = new Layout();

        /**
         * Sets the widget.
         * @param widget The widget
         * @return This object
         */
        public B widget(Widget widget)
        {
            this.widget = widget;
            widget.setLayout(layout);
            return self();
        }

        /**
         * Sets the visualization of the widget.
         * @param visualization The visualization of the widget
         * @return This object
         */
        public B visualization(String visualization)
        {
            widget.setVisualization(visualization);
            return self();
        }

        /**
         * Sets the account id of the widget.
         * @param accountId The account id of the widget
         * @return This object
         */
        public B accountId(long accountId)
        {
            widget.setAccountId(accountId);
            return self();
        }

        /**
         * Sets the layout of the widget.
         * @param layout The layout of the widget
         * @return This object
         */
        public B layout(Layout layout)
        {
            widget.setLayout(layout);
            return self();
        }

        /**
         * Sets the position of the widget.
         * @param row The row of the widget
         * @param column The column of the widget
         * @return This object
         */
        public B position(int row, int column)
        {
            widget.getLayout().setPosition(row, column);
            return self();
        }

        /**
         * Sets the size of the widget.
         * @param width The width of the widget
         * @param height The height of the widget
         * @return This object
         */
        public B size(int width, int height)
        {
            widget.getLayout().setSize(width, height);
            return self();
        }

        /**
         * Sets the title of the presentation.
         * @param title The title of the presentation
         * @return This object
         */
        public B title(String title)
        {
            widget.getPresentation().setTitle(title);
            return self();
        }

        /**
         * Sets the notes of the presentation.
         * @param notes The notes of the presentation
         * @return This object
         */
        public B notes(String notes)
        {
            widget.getPresentation().setNotes(notes);
            return self();
        }

        /**
         * Returns this object.
         * @return This object
         */
        protected abstract B self();

        /**
         * Returns the configured alert condition instance
         * @return The alert condition instance
         */
        public abstract T build();
    }
}