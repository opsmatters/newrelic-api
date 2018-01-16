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

package com.opsmatters.newrelic.api.model.alerts.conditions;

import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.alerts.Priority;

/**
 * Represents a New Relic alert condition term.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Term
{
    private String duration;
    private String operator;
    private String priority;
    private String threshold;

    @SerializedName("time_function")
    private String timeFunction;

    /**
     * Represents a term duration (in minutes).  
     */
    public enum Duration
    {
        MINUTES_5(5),
        MINUTES_10(10),
        MINUTES_15(15),
        MINUTES_30(30),
        MINUTES_60(60),
        MINUTES_120(120);

        Duration(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return value;
        }

        private int value;
    }

    /**
     * Default constructor.
     */
    public Term()
    {
    }
    
    /**
     * Sets the duration of the term in minutes.
     * @param duration The duration of the term
     */
    public void setDuration(String duration)
    {
        this.duration = duration;
    }

    /**
     * Sets the duration of the term in minutes.
     * @param duration The duration of the term
     */
    public void setDuration(int duration)
    {
        setDuration(Integer.toString(duration));
    }

    /**
     * Sets the duration of the term in minutes.
     * @param duration The duration of the term
     */
    public void setDuration(Duration duration)
    {
        setDuration(duration.value());
    }

    /**
     * Returns the duration of the term in minutes.
     * @return The duration of the term
     */
    public String getDuration()
    {
        return duration;
    }

    /**
     * Sets the operator of the term.
     * @param operator The operator of the term
     */
    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    /**
     * Sets the operator of the term.
     * @param operator The operator of the term
     */
    public void setOperator(Operator operator)
    {
        setOperator(operator.value());
    }

    /**
     * Returns the operator of the term.
     * @return The operator of the term
     */
    public String getOperator()
    {
        return operator;
    }

    /**
     * Sets the priority of the term.
     * @param priority The priority of the term
     */
    public void setPriority(String priority)
    {
        this.priority = priority;
    }

    /**
     * Sets the priority of the term.
     * @param priority The priority of the term
     */
    public void setPriority(Priority priority)
    {
        setPriority(priority.value());
    }

    /**
     * Returns the priority of the term.
     * @return The priority of the term
     */
    public String getPriority()
    {
        return priority;
    }

    /**
     * Sets the threshold of the term.
     * @param threshold The threshold of the term
     */
    public void setThreshold(String threshold)
    {
        this.threshold = threshold;
    }

    /**
     * Sets the threshold of the term.
     * @param threshold The threshold of the term
     */
    public void setThreshold(int threshold)
    {
        setThreshold(Integer.toString(threshold));
    }

    /**
     * Sets the threshold of the term.
     * @param threshold The threshold of the term
     */
    public void setThreshold(double threshold)
    {
        setThreshold(Double.toString(threshold));
    }

    /**
     * Returns the threshold of the term.
     * @return The threshold of the term
     */
    public String getThreshold()
    {
        return threshold;
    }

    /**
     * Sets the time function of the term.
     * @param timeFunction The time function of the term
     */
    public void setTimeFunction(String timeFunction)
    {
        this.timeFunction = timeFunction;
    }

    /**
     * Sets the time function of the term.
     * @param timeFunction The time function of the term
     */
    public void setTimeFunction(TimeFunction timeFunction)
    {
        setTimeFunction(timeFunction.value());
    }

    /**
     * Returns the time function of the term.
     * @return The time function of the term
     */
    public String getTimeFunction()
    {
        return timeFunction;
    }
    
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Term [duration="+duration
            +", operator="+operator
            +", priority="+priority
            +", threshold="+threshold
            +", timeFunction="+timeFunction
            +"]";
    }

    /**
     * Returns a builder for the term.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make term construction easier.
     */
    public static class Builder
    {
        private Term term = new Term();

        /**
         * Sets the duration of the term in minutes.
         * @param duration The duration of the term
         * @return This object
         */
        public Builder duration(String duration)
        {
            term.setDuration(duration);
            return this;
        }

        /**
         * Sets the duration of the term in minutes.
         * @param duration The duration of the term
         * @return This object
         */
        public Builder duration(int duration)
        {
            term.setDuration(duration);
            return this;
        }

        /**
         * Sets the duration of the term in minutes.
         * @param duration The duration of the term
         * @return This object
         */
        public Builder duration(Duration duration)
        {
            term.setDuration(duration);
            return this;
        }

        /**
         * Sets the operator of the term.
         * @param operator The operator of the term
         * @return This object
         */
        public Builder operator(String operator)
        {
            term.setOperator(operator);
            return this;
        }

        /**
         * Sets the operator of the term to "above".
         * @return This object
         */
        public Builder aboveOperator()
        {
            term.setOperator(Operator.ABOVE);
            return this;
        }

        /**
         * Sets the operator of the term to "below".
         * @return This object
         */
        public Builder belowOperator()
        {
            term.setOperator(Operator.BELOW);
            return this;
        }

        /**
         * Sets the operator of the term to "equal".
         * @return This object
         */
        public Builder equalOperator()
        {
            term.setOperator(Operator.EQUAL);
            return this;
        }

        /**
         * Sets the priority of the term.
         * @param priority The priority of the term
         * @return This object
         */
        public Builder priority(String priority)
        {
            term.setPriority(priority);
            return this;
        }

        /**
         * Sets the priority of the term to "critical".
         * @return This object
         */
        public Builder criticalPriority()
        {
            term.setPriority(Priority.CRITICAL);
            return this;
        }

        /**
         * Sets the priority of the term to "warning".
         * @return This object
         */
        public Builder warningPriority()
        {
            term.setPriority(Priority.WARNING);
            return this;
        }

        /**
         * Sets the threshold of the term.
         * @param threshold The threshold of the term
         * @return This object
         */
        public Builder threshold(String threshold)
        {
            term.setThreshold(threshold);
            return this;
        }

        /**
         * Sets the threshold of the term.
         * @param threshold The threshold of the term
         * @return This object
         */
        public Builder threshold(int threshold)
        {
            term.setThreshold(threshold);
            return this;
        }

        /**
         * Sets the threshold of the term.
         * @param threshold The threshold of the term
         * @return This object
         */
        public Builder threshold(double threshold)
        {
            term.setThreshold(threshold);
            return this;
        }

        /**
         * Sets the time function of the term.
         * @param timeFunction The time function of the term
         * @return This object
         */
        public Builder timeFunction(String timeFunction)
        {
            term.setTimeFunction(timeFunction);
            return this;
        }

        /**
         * Sets the time function of the term to "any".
         * @return This object
         */
        public Builder anyTimeFunction()
        {
            term.setTimeFunction(TimeFunction.ANY);
            return this;
        }

        /**
         * Sets the time function of the term to "all".
         * @return This object
         */
        public Builder allTimeFunction()
        {
            term.setTimeFunction(TimeFunction.ALL);
            return this;
        }

        /**
         * Returns the configured term instance
         * @return The term instance
         */
        public Term build()
        {
            return term;
        }
    }
}