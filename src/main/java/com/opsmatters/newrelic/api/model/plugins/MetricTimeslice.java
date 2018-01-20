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

package com.opsmatters.newrelic.api.model.plugins;

import java.util.Map;
import java.util.HashMap;
import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.NamedResource;

/**
 * Represents a New Relic plugin metric timeslice.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class MetricTimeslice<T>
{
    private T total;

    private T count;

    private T min;

    private T max;

    @SerializedName("sum_of_squares")
    private T sumOfSquares;

    /**
     * Default constructor.
     */
    public MetricTimeslice()
    {
    }

    /**
     * Sets the total of the metric.
     * @param total The total of the metric
     */
    public void setTotal(T total)
    {
        this.total = total;
    }

    /**
     * Returns the total of the metric.
     * @return The total of the metric
     */
    public T getTotal()
    {
        return total;
    }

    /**
     * Sets the count of the metric.
     * @param count The count of the metric
     */
    public void setCount(T count)
    {
        this.count = count;
    }

    /**
     * Returns the count of the metric.
     * @return The count of the metric
     */
    public T getCount()
    {
        return count;
    }

    /**
     * Sets the minimum of the metric.
     * @param min The minimum of the metric
     */
    public void setMin(T min)
    {
        this.min = min;
    }

    /**
     * Returns the minimum of the metric.
     * @return The minimum of the metric
     */
    public T getMin()
    {
        return min;
    }

    /**
     * Sets the maximum of the metric.
     * @param max The maximum of the metric
     */
    public void setMax(T max)
    {
        this.max = max;
    }

    /**
     * Returns the maximum of the metric.
     * @return The maximum of the metric
     */
    public T getMax()
    {
        return max;
    }

    /**
     * Sets the sum of squares of the metric.
     * @param sumOfSquares The sum of squares of the metric
     */
    public void setSumOfSquares(T sumOfSquares)
    {
        this.sumOfSquares = sumOfSquares;
    }

    /**
     * Returns the sum of squares of the metric.
     * @return The sum of squares of the metric
     */
    public T getSumOfSquares()
    {
        return sumOfSquares;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "MetricTimeslice [total="+total
            +", count="+count
            +", min="+min
            +", max="+max
            +", sumOfSquares="+sumOfSquares
            +"]";
    }

    /**
     * Returns a builder for the integer metric timeslice.
     * @param <T> The type parameter used for the builder
     * @return The builder instance.
     */
    public static <T> Builder<T> builder()
    {
        return new Builder<T>();
    }

    /**
     * Builder to make metric timeslice construction easier.
     */
    public static class Builder<T>
    {
        private MetricTimeslice<T> timeslice = new MetricTimeslice<T>();

        /**
         * Sets the total of the metric.
         * @param total The total of the metric
         * @return This object
         */
        public Builder total(T total)
        {
            timeslice.setTotal(total);
            return this;
        }

        /**
         * Sets the count of the metric.
         * @param count The count of the metric
         * @return This object
         */
        public Builder count(T count)
        {
            timeslice.setCount(count);
            return this;
        }

        /**
         * Sets the minimum of the metric.
         * @param min The minimum of the metric
         * @return This object
         */
        public Builder min(T min)
        {
            timeslice.setMin(min);
            return this;
        }

        /**
         * Sets the maximum of the metric.
         * @param max The maximum of the metric
         * @return This object
         */
        public Builder max(T max)
        {
            timeslice.setMax(max);
            return this;
        }

        /**
         * Sets the sum of squares of the metric.
         * @param sumOfSquares The sum of squares of the metric
         * @return This object
         */
        public Builder sumOfSquares(T sumOfSquares)
        {
            timeslice.setSumOfSquares(sumOfSquares);
            return this;
        }

        /**
         * Returns the configured timeslice instance
         * @return The timeslice instance
         */
        public MetricTimeslice<T> build()
        {
            return timeslice;
        }
    }
}