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

package com.opsmatters.newrelic.api.model.synthetics;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.opsmatters.newrelic.api.model.NamedResource;

/**
 * Represents a New Relic Synthetics monitor.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public abstract class Monitor implements NamedResource
{
    private String id;
    private String name;
    private String type;
    private Integer frequency;
    private String status;
    private Double slaThreshold;
    private Date createdAt;
    private Date modifiedAt;
    private Long userId;
    private String apiVersion;
    private List<String> locations = new ArrayList<String>();
    private MonitorOptions options = new MonitorOptions();

    /**
     * Represents the available types for a New Relic Synthetics monitor.  
     */
    public enum MonitorType
    {
        SIMPLE,
        BROWSER,
        SCRIPT_BROWSER,
        SCRIPT_API;

        /**
         * Returns <CODE>true</CODE> if the given value is contained in the list of types.
         * @param value The type value
         * @return <CODE>true</CODE> if the given value is contained in the list of types
         */
        public static boolean contains(String value)
        {
            return valueOf(value) != null;
        }
    }

    /**
     * Represents the available statuses for a monitor.  
     */
    public enum Status
    {
        ENABLED,
        MUTED,
        DISABLED
    }

    /**
     * Represents a monitor frequency.  
     */
    public enum Frequency
    {
        MINUTES_1(1),
        MINUTES_5(5),
        MINUTES_10(10),
        MINUTES_15(15),
        MINUTES_30(30),
        MINUTES_60(60),
        MINUTES_360(360),
        MINUTES_720(720),
        MINUTES_1440(1440);

        Frequency(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return value;
        }

        /**
         * Returns the type for the given value.
         * @param value The type value
         * @return The type for the given value
         */
        public static Frequency fromValue(int value)
        {
            Frequency[] types = values();
            for(Frequency type : types)
            {
                if(type.value() == value)
                    return type;
            }
            return null;
        }

        /**
         * Returns <CODE>true</CODE> if the given value is contained in the list of types.
         * @param value The type value
         * @return <CODE>true</CODE> if the given value is contained in the list of types
         */
        public static boolean contains(int value)
        {
            return fromValue(value) != null;
        }

        private int value;
    }

    /**
     * Default constructor.
     */
    public Monitor()
    {
    }

    /**
     * Sets the id of the monitor.
     * @param id The id of the monitor
     */
    public void setId(String id)
    {
        this.id = id;
    }
   
    /**
     * Returns the id of the monitor.
     * @return The id of the monitor
     */
    public String getId()
    {
        return id;
    }

    /**
     * Sets the name of the monitor.
     * @param name The name of the monitor
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the name of the monitor.
     * @return The name of the monitor
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the type of the monitor.
     * @param type The type of the monitor
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * Sets the type of the monitor.
     * @param type The type of the monitor
     */
    public void setType(MonitorType type)
    {
        setType(type.name());
    }

    /**
     * Returns the type of the monitor.
     * @return The type of the monitor
     */
    public String getType()
    {
        return type;
    }

    /**
     * Sets the frequency of the monitor (in minutes).
     * @param frequency The frequency of the monitor
     */
    public void setFrequency(int frequency)
    {
        this.frequency = frequency;
    }

    /**
     * Sets the frequency of the monitor (in minutes).
     * @param frequency The frequency of the monitor
     */
    public void setFrequency(Frequency frequency)
    {
        setFrequency(frequency.value());
    }

    /**
     * Returns the frequency of the monitor (in minutes).
     * @return The frequency of the monitor
     */
    public int getFrequency()
    {
        return frequency;
    }

    /**
     * Sets the status of the monitor.
     * @param status The status of the monitor
     */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * Sets the status of the monitor.
     * @param status The status of the monitor
     */
    public void setStatus(Status status)
    {
        setStatus(status.name());
    }

    /**
     * Returns the status of the monitor.
     * @return The status of the monitor
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * Sets the SLA threshold of the monitor.
     * @param slaThreshold The SLA threshold of the monitor
     */
    public void setSlaThreshold(double slaThreshold)
    {
        this.slaThreshold = slaThreshold;
    }

    /**
     * Returns the SLA threshold of the monitor.
     * @return The SLA threshold of the monitor
     */
    public double getSlaThreshold()
    {
        return slaThreshold;
    }

    /**
     * Sets the list of locations.
     * @param locations The list of locations
     */
    public void setLocations(List<String> locations)
    {
        this.locations.clear();
        this.locations.addAll(locations);
    }

    /**
     * Returns the list of locations.
     * @return The list of locations
     */
    public List<String> getLocations()
    {
        return locations;
    }

    /**
     * Sets the monitor options.
     * @param options The monitor options
     */
    public void setOptions(MonitorOptions options)
    {
        this.options = options;
    }

    /**
     * Returns the monitor options.
     * @return The monitor options
     */
    public MonitorOptions getOptions()
    {
        return options;
    }

    /**
     * Returns the user id of the monitor.
     * @return The user id of the monitor
     */
    public long getUserId()
    {
        return userId;
    }

    /**
     * Returns the API version of the monitor.
     * @return The API version of the monitor
     */
    public String getApiVersion()
    {
        return apiVersion;
    }

    /**
     * Returns the date the monitor was created.
     * @return The date the monitor was created
     */
    public Date getCreatedAt()
    {
        return createdAt;
    }

    /**
     * Returns the date the monitor was last modified.
     * @return The date the monitor was last modified
     */
    public Date getModifedAt()
    {
        return modifiedAt;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString()
    {
        return "id="+id
            +", name="+name
            +", type="+type
            +", frequency="+frequency
            +", status="+status
            +", slaThreshold="+slaThreshold
            +", userId="+userId
            +", apiVersion="+apiVersion
            +", locations="+locations
            +", options="+options
            +", createdAt="+createdAt
            +", modifiedAt="+modifiedAt;
    }

    /**
     * Builder to make monitor construction easier.
     */
    protected abstract static class Builder<T extends Monitor, B extends Builder<T,B>>
    {
        private Monitor monitor;

        /**
         * Sets the monitor.
         * @param monitor The monitor
         * @return This object
         */
        public B monitor(Monitor monitor)
        {
            this.monitor = monitor;
            return self();
        }

        /**
         * Sets the id of the monitor.
         * @param id The id of the monitor
         * @return This object
         */
        public B id(String id)
        {
            monitor.setId(id);
            return self();
        }

        /**
         * Sets the name of the monitor.
         * @param name The name of the monitor
         * @return This object
         */
        public B name(String name)
        {
            monitor.setName(name);
            return self();
        }

        /**
         * Sets the frequency of the monitor (in minutes).
         * @param frequency The frequency of the monitor
         * @return This object
         */
        public B frequency(int frequency)
        {
            monitor.setFrequency(frequency);
            return self();
        }

        /**
         * Sets the frequency of the monitor (in minutes).
         * @param frequency The frequency of the monitor
         * @return This object
         */
        public B frequency(Frequency frequency)
        {
            monitor.setFrequency(frequency);
            return self();
        }

        /**
         * Sets the status of the monitor.
         * @param status The status of the monitor
         * @return This object
         */
        public B status(String status)
        {
            monitor.setStatus(status);
            return self();
        }

        /**
         * Sets the status of the monitor.
         * @param status The status of the monitor
         * @return This object
         */
        public B status(Status status)
        {
            monitor.setStatus(status);
            return self();
        }

        /**
         * Sets the SLA threshold of the monitor.
         * @param slaThreshold The SLA threshold of the monitor
         * @return This object
         */
        public B slaThreshold(double slaThreshold)
        {
            monitor.setSlaThreshold(slaThreshold);
            return self();
        }

        /**
         * Sets the locations of the monitor.
         * @param locations The locations of the monitor
         * @return This object
         */
        public B locations(List<String> locations)
        {
            monitor.setLocations(locations);
            return self();
        }

       /**
         * Adds the location to the monitor.
         * @param location The location of the monitor
         * @return This object
         */
        public B addLocation(String location)
        {
            monitor.getLocations().add(location);
            return self();
        }

        /**
         * Returns this object.
         * @return This object
         */
        protected abstract B self();

        /**
         * Returns the configured monitor instance
         * @return The monitor instance
         */
        public abstract T build();
    }
}