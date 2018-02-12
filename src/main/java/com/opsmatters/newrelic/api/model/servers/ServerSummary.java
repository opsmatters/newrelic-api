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

package com.opsmatters.newrelic.api.model.servers;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic server summary.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ServerSummary
{
    private Float cpu;

    @SerializedName("cpu_stolen")
    private Float cpuStolen;

    @SerializedName("disk_io")
    private Float diskIo;

    private Float memory;

    @SerializedName("memory_used")
    private Integer memoryUsed;

    @SerializedName("memory_total")
    private Integer memoryTotal;

    @SerializedName("fullest_disk")
    private Float fullestDisk;

    @SerializedName("fullest_disk_free")
    private Integer fullestDiskFree;

    /**
     * Default constructor.
     */
    public ServerSummary()
    {
    }
    
    /**
     * Returns the cpu of the server.
     * @return The cpu of the server
     */
    public Float getCpu()
    {
        return cpu;
    }

    /**
     * Returns the cpu steal of the server.
     * @return The cpu steal of the server
     */
    public Float getCpuStolen()
    {
        return cpuStolen;
    }

    /**
     * Returns the disk I/O of the server.
     * @return The disk I/O of the server
     */
    public Float getDiskIo()
    {
        return diskIo;
    }

    /**
     * Returns the memory of the server.
     * @return The memory of the server
     */
    public Float getMemory()
    {
        return memory;
    }

    /**
     * Returns the memory used of the server.
     * @return The memory used of the server
     */
    public Integer getMemoryUsed()
    {
        return memoryUsed;
    }

    /**
     * Returns the total memory of the server.
     * @return The total memory of the server
     */
    public Integer getMemoryTotal()
    {
        return memoryTotal;
    }

    /**
     * Returns the fullest disk of the server.
     * @return The fullest disk of the server
     */
    public Float getFullestDisk()
    {
        return fullestDisk;
    }

    /**
     * Returns the fullest disk free of the server.
     * @return The fullest disk free of the server
     */
    public Integer getFullestDiskFree()
    {
        return fullestDiskFree;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "ServerSummary [cpu="+cpu
            +", cpuStolen="+cpuStolen
            +", diskIo="+diskIo
            +", memory="+memory
            +", memoryUsed="+memoryUsed
            +", memoryTotal="+memoryTotal
            +", fullestDisk="+fullestDisk
            +", fullestDiskFree="+fullestDiskFree
            +"]";
    }
}