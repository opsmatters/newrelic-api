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

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic application settings.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class MonitorOptions
{
    private String validationString;

    @SerializedName("verifySSL")
    private Boolean verifySsl;

    @SerializedName("bypassHEADRequest")
    private Boolean bypassHeadRequest;

    private Boolean treatRedirectAsFailure;

    /**
     * Default constructor.
     */
    public MonitorOptions()
    {
    }

    /**
     * Sets the validation string of the monitor.
     * @param validationString The validation string of the monitor
     */
    public void setValidationString(String validationString)
    {
        this.validationString = validationString;
    }
    
    /**
     * Returns the validation string of the monitor.
     * @return The validation string of the monitor
     */
    public String getValidationString()
    {
        return validationString;
    }

    /**
     * Set to <CODE>true</CODE> if the SSL connection should be verified by the monitor.
     * @param verifySsl <CODE>true</CODE> if the SSL connection should be verified by the monitor
     */
    public void setVerifySsl(Boolean verifySsl)
    {
        this.verifySsl = verifySsl;
    }
    
    /**
     * Returns <CODE>true</CODE> if the SSL connection should be verified by the monitor.
     * @return <CODE>true</CODE> if the SSL connection should be verified by the monitor
     */
    public Boolean getVerifySsl()
    {
        return verifySsl;
    }

    /**
     * Set to <CODE>true</CODE> if the monitor should bypass the HEAD request.
     * @param bypassHeadRequest <CODE>true</CODE> if the monitor should bypass the HEAD request
     */
    public void setBypassHeadRequest(Boolean bypassHeadRequest)
    {
        this.bypassHeadRequest = bypassHeadRequest;
    }
    
    /**
     * Returns <CODE>true</CODE> if the monitor should treat a 3xx redirect as a failure.
     * @return <CODE>true</CODE> if the monitor should treat a 3xx redirect as a failure
     */
    public Boolean getBypassHeadRequest()
    {
        return bypassHeadRequest;
    }

    /**
     * Set to <CODE>true</CODE> if the monitor should treat a 3xx redirect as a failure.
     * @param treatRedirectAsFailure <CODE>true</CODE> if the monitor should treat a 3xx redirect as a failure
     */
    public void setTreatRedirectAsFailure(Boolean treatRedirectAsFailure)
    {
        this.treatRedirectAsFailure = treatRedirectAsFailure;
    }
    
    /**
     * Returns <CODE>true</CODE> if the monitor should treat a 3xx redirect as a failure.
     * @return <CODE>true</CODE> if the monitor should treat a 3xx redirect as a failure
     */
    public Boolean getTreatRedirectAsFailure()
    {
        return treatRedirectAsFailure;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "MonitorOptions [validationString="+validationString
            +", verifySsl="+verifySsl
            +", bypassHeadRequest="+bypassHeadRequest
            +", treatRedirectAsFailure="+treatRedirectAsFailure
            +"]";
    }
}