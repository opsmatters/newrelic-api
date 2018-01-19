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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Configuration;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.logging.LoggingFeature;
import com.opsmatters.newrelic.httpclient.filters.LicenseKeyFilter;

/**
 * HTTP client provider to attach a License key used for authentication.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class LicenseKeyHttpClientProvider implements HttpClientProvider
{
    private static final Logger logger = Logger.getLogger(LicenseKeyHttpClientProvider.class.getName());

    private String licenseKey;
    
    /**
     * Constructor that takes a License key.
     * @param licenseKey The License key
     */
    public LicenseKeyHttpClientProvider(String licenseKey)
    {    
        this.licenseKey = licenseKey;
    }

    /**
     * Returns <CODE>true</CODE> if the provider should use https.
     * @return <CODE>true</CODE> if the provider should use https
     */
    @Override
    public boolean useSsl()
    {
        return true;
    }

    /**
     * Returns the HTTP client.
     * @return The HTTP client
     */
    @Override
    public Client getClient()
    {
        ClientConfig config = new ClientConfig();
        config.register(GsonMessageBodyHandler.class);   
        Client client = ClientBuilder.newClient(config);
        client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true); // To support PATCH method
        client.register(new LicenseKeyFilter(this.licenseKey));
        if(logger.isLoggable(Level.FINE))
            client.register(new LoggingFeature(logger, Level.FINE, LoggingFeature.Verbosity.PAYLOAD_TEXT, 8192));
        return client;
    }
}