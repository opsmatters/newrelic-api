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
 * Represents a New Relic Insights dashboard widget layout.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Layout
{
    // The field names
    public static final String ROW = "row";
    public static final String COLUMN = "column";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";

    private Integer row;

    private Integer column;

    private Integer width;

    private Integer height;

    /**
     * Default constructor.
     */
    public Layout()
    {
    }

    /**
     * Sets the width of the dashboard widget.
     * @param width The width of the dashboard widget
     */
    public void setWidth(Integer width)
    {
        this.width = width;
    }

    /**
     * Returns the width of the dashboard widget.
     * @return The width of the dashboard widget
     */
    public Integer getWidth()
    {
        return width;
    }

    /**
     * Sets the height of the dashboard widget.
     * @param height The height of the dashboard widget
     */
    public void setHeight(Integer height)
    {
        this.height = height;
    }

    /**
     * Returns the height of the dashboard widget.
     * @return The height of the dashboard widget
     */
    public Integer getHeight()
    {
        return height;
    }

    /**
     * Sets the row of the dashboard widget.
     * @param row The row of the dashboard widget
     */
    public void setRow(Integer row)
    {
        this.row = row;
    }

    /**
     * Returns the row of the dashboard widget.
     * @return The row of the dashboard widget
     */
    public Integer getRow()
    {
        return row;
    }

    /**
     * Sets the column of the dashboard widget.
     * @param column The column of the dashboard widget
     */
    public void setColumn(Integer column)
    {
        this.column = column;
    }

    /**
     * Returns the column of the dashboard widget.
     * @return The column of the dashboard widget
     */
    public Integer getColumn()
    {
        return column;
    }

    /**
     * Sets the size of the dashboard widget.
     * @param width The width of the widget
     * @param height The height of the widget
     */
    public void setSize(int width, int height)
    {
        setWidth(width);
        setHeight(height);
    }

    /**
     * Sets the position of the dashboard widget.
     * @param row The row of the widget
     * @param column The column of the widget
     */
    public void setPosition(int row, int column)
    {
        setRow(row);
        setColumn(column);
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Layout [row="+row
            +", column="+column
            +", width="+width
            +", height="+height
            +"]";
    }

    /**
     * Returns a builder for the layout.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make layout construction easier.
     */
    public static class Builder
    {
        private Layout layout = new Layout();

        /**
         * Sets the width of the dashboard widget.
         * @param width The width of the dashboard widget
         * @return This object
         */
        public Builder width(int width)
        {
            layout.setWidth(width);
            return this;
        }

        /**
         * Sets the height of the dashboard widget.
         * @param height The height of the dashboard widget
         * @return This object
         */
        public Builder height(int height)
        {
            layout.setHeight(height);
            return this;
        }

        /**
         * Sets the row of the dashboard widget.
         * @param row The row of the dashboard widget
         * @return This object
         */
        public Builder row(int row)
        {
            layout.setRow(row);
            return this;
        }

        /**
         * Sets the column of the dashboard widget.
         * @param column The column of the dashboard widget
         * @return This object
         */
        public Builder column(int column)
        {
            layout.setColumn(column);
            return this;
        }

        /**
         * Sets the position of the dashboard widget.
         * @param row The row of the dashboard widget
         * @param column The column of the dashboard widget
         * @return This object
         */
        public Builder position(int row, int column)
        {
            layout.setPosition(row, column);
            return this;
        }

        /**
         * Sets the size of the dashboard widget.
         * @param width The width of the dashboard widget
         * @param height The height of the dashboard widget
         * @return This object
         */
        public Builder size(int width, int height)
        {
            layout.setSize(width, height);
            return this;
        }

        /**
         * Returns the configured layout instance
         * @return The layout instance
         */
        public Layout build()
        {
            return layout;
        }
    }
}