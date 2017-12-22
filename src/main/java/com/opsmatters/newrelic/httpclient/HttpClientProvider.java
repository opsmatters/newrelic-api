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

package com.opsmatters.newrelic.httpclient;

import javax.ws.rs.client.Client;

/**
 * Interface for a HTTP client provider.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public interface HttpClientProvider
{
    /**
     * Returns <CODE>true</CODE> if the provider uses https.
     * @return <CODE>true</CODE> if the provider uses https
     */
    public boolean useSsl();

    /**
     * Returns the http client.
     * @return the http client
     */
    public Client getClient();
}