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

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.opsmatters.newrelic.api.model.AlertIncident;
import com.opsmatters.newrelic.api.model.AlertViolation;
import com.opsmatters.newrelic.api.model.AlertEvent;
import com.opsmatters.newrelic.api.model.ResponseError;
import com.opsmatters.newrelic.api.model.policies.AlertPolicy;
import com.opsmatters.newrelic.api.model.policies.AlertPolicyChannel;
import com.opsmatters.newrelic.api.model.channels.AlertChannel;
import com.opsmatters.newrelic.api.model.conditions.AlertCondition;
import com.opsmatters.newrelic.api.model.conditions.NrqlAlertCondition;
import com.opsmatters.newrelic.api.model.conditions.ExternalServiceAlertCondition;
import com.opsmatters.newrelic.api.model.conditions.PluginsAlertCondition;
import com.opsmatters.newrelic.api.model.conditions.SyntheticsAlertCondition;
import com.opsmatters.newrelic.api.model.conditions.InfraAlertCondition;
import com.opsmatters.newrelic.api.model.entities.Application;
import com.opsmatters.newrelic.api.model.entities.ApplicationHost;
import com.opsmatters.newrelic.api.model.entities.ApplicationInstance;
import com.opsmatters.newrelic.api.model.entities.BrowserApplication;
import com.opsmatters.newrelic.api.model.entities.MobileApplication;
import com.opsmatters.newrelic.api.model.entities.KeyTransaction;
import com.opsmatters.newrelic.api.model.entities.Metric;
import com.opsmatters.newrelic.api.model.entities.MetricData;
import com.opsmatters.newrelic.httpclient.serializers.policies.AlertPolicySerializer;
import com.opsmatters.newrelic.httpclient.serializers.channels.AlertChannelSerializer;
import com.opsmatters.newrelic.httpclient.serializers.conditions.AlertConditionSerializer;
import com.opsmatters.newrelic.httpclient.serializers.conditions.NrqlAlertConditionSerializer;
import com.opsmatters.newrelic.httpclient.serializers.conditions.ExternalServiceAlertConditionSerializer;
import com.opsmatters.newrelic.httpclient.serializers.conditions.PluginsAlertConditionSerializer;
import com.opsmatters.newrelic.httpclient.serializers.conditions.SyntheticsAlertConditionSerializer;
import com.opsmatters.newrelic.httpclient.serializers.conditions.InfraAlertConditionSerializer;
import com.opsmatters.newrelic.httpclient.serializers.entities.ApplicationSerializer;
import com.opsmatters.newrelic.httpclient.serializers.entities.BrowserApplicationSerializer;
import com.opsmatters.newrelic.httpclient.deserializers.AlertIncidentsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.AlertViolationsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.AlertEventsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.ResponseErrorDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.policies.AlertPolicyDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.policies.AlertPoliciesDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.policies.AlertPolicyChannelDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.channels.AlertChannelsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.conditions.AlertConditionDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.conditions.AlertConditionsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.conditions.NrqlAlertConditionDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.conditions.NrqlAlertConditionsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.conditions.ExternalServiceAlertConditionDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.conditions.ExternalServiceAlertConditionsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.conditions.PluginsAlertConditionDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.conditions.PluginsAlertConditionsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.conditions.SyntheticsAlertConditionDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.conditions.SyntheticsAlertConditionsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.conditions.InfraAlertConditionDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.conditions.InfraAlertConditionsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.entities.ApplicationDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.entities.ApplicationsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.entities.ApplicationHostDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.entities.ApplicationHostsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.entities.ApplicationInstanceDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.entities.ApplicationInstancesDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.entities.BrowserApplicationDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.entities.BrowserApplicationsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.entities.MobileApplicationDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.entities.MobileApplicationsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.entities.KeyTransactionDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.entities.KeyTransactionsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.entities.MetricsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.entities.MetricDataDeserializer;

/**
 * Provides GSON support for serializing and deserializing objects.
 * 
 * @author Gerald Curley (opsmatters)
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public final class GsonMessageBodyHandler implements MessageBodyWriter<Object>, MessageBodyReader<Object>
{
    private static final Logger logger = Logger.getLogger(GsonMessageBodyHandler.class.getName());
    private static final String CHARSET = "UTF-8";

    private static final Type ALERT_POLICIES_TYPE = new TypeToken<Collection<AlertPolicy>>(){}.getType();
    private static final Type ALERT_CHANNELS_TYPE = new TypeToken<Collection<AlertChannel>>(){}.getType();
    private static final Type ALERT_CONDITIONS_TYPE = new TypeToken<Collection<AlertCondition>>(){}.getType();
    private static final Type NRQL_ALERT_CONDITIONS_TYPE = new TypeToken<Collection<NrqlAlertCondition>>(){}.getType();
    private static final Type EXTERNAL_SERVICE_ALERT_CONDITIONS_TYPE = new TypeToken<Collection<ExternalServiceAlertCondition>>(){}.getType();
    private static final Type PLUGINS_ALERT_CONDITIONS_TYPE = new TypeToken<Collection<PluginsAlertCondition>>(){}.getType();
    private static final Type SYNTHETICS_ALERT_CONDITIONS_TYPE = new TypeToken<Collection<SyntheticsAlertCondition>>(){}.getType();
    private static final Type INFRA_ALERT_CONDITIONS_TYPE = new TypeToken<Collection<InfraAlertCondition>>(){}.getType();
    private static final Type ALERT_INCIDENTS_TYPE = new TypeToken<Collection<AlertIncident>>(){}.getType();
    private static final Type ALERT_VIOLATIONS_TYPE = new TypeToken<Collection<AlertViolation>>(){}.getType();
    private static final Type ALERT_EVENTS_TYPE = new TypeToken<Collection<AlertEvent>>(){}.getType();
    private static final Type APPLICATIONS_TYPE = new TypeToken<Collection<Application>>(){}.getType();
    private static final Type APPLICATION_HOSTS_TYPE = new TypeToken<Collection<ApplicationHost>>(){}.getType();
    private static final Type APPLICATION_INSTANCES_TYPE = new TypeToken<Collection<ApplicationInstance>>(){}.getType();
    private static final Type BROWSER_APPLICATIONS_TYPE = new TypeToken<Collection<BrowserApplication>>(){}.getType();
    private static final Type MOBILE_APPLICATIONS_TYPE = new TypeToken<Collection<MobileApplication>>(){}.getType();
    private static final Type KEY_TRANSACTIONS_TYPE = new TypeToken<Collection<KeyTransaction>>(){}.getType();
    private static final Type METRICS_TYPE = new TypeToken<Collection<Metric>>(){}.getType();

    private Gson gson;

    /**
     * Returns the Gson object with all custom deserializers.
     * @return The Gson object
     */
    private Gson getGson()
    {
        if (gson == null)
        {
            GsonBuilder builder = new GsonBuilder();

            builder.registerTypeAdapter(AlertPolicy.class, new AlertPolicySerializer());
            builder.registerTypeAdapter(AlertPolicy.class, new AlertPolicyDeserializer());
            builder.registerTypeAdapter(ALERT_POLICIES_TYPE, new AlertPoliciesDeserializer());
            builder.registerTypeHierarchyAdapter(AlertChannel.class, new AlertChannelSerializer());
            builder.registerTypeAdapter(ALERT_CHANNELS_TYPE, new AlertChannelsDeserializer());
            builder.registerTypeAdapter(AlertPolicyChannel.class, new AlertPolicyChannelDeserializer());
            builder.registerTypeHierarchyAdapter(AlertCondition.class, new AlertConditionSerializer());
            builder.registerTypeAdapter(AlertCondition.class, new AlertConditionDeserializer());
            builder.registerTypeAdapter(ALERT_CONDITIONS_TYPE, new AlertConditionsDeserializer());
            builder.registerTypeAdapter(NrqlAlertCondition.class, new NrqlAlertConditionSerializer());
            builder.registerTypeAdapter(NrqlAlertCondition.class, new NrqlAlertConditionDeserializer());
            builder.registerTypeAdapter(NRQL_ALERT_CONDITIONS_TYPE, new NrqlAlertConditionsDeserializer());
            builder.registerTypeHierarchyAdapter(ExternalServiceAlertCondition.class, new ExternalServiceAlertConditionSerializer());
            builder.registerTypeAdapter(ExternalServiceAlertCondition.class, new ExternalServiceAlertConditionDeserializer());
            builder.registerTypeAdapter(EXTERNAL_SERVICE_ALERT_CONDITIONS_TYPE, new ExternalServiceAlertConditionsDeserializer());
            builder.registerTypeHierarchyAdapter(PluginsAlertCondition.class, new PluginsAlertConditionSerializer());
            builder.registerTypeAdapter(PluginsAlertCondition.class, new PluginsAlertConditionDeserializer());
            builder.registerTypeAdapter(PLUGINS_ALERT_CONDITIONS_TYPE, new PluginsAlertConditionsDeserializer());
            builder.registerTypeHierarchyAdapter(SyntheticsAlertCondition.class, new SyntheticsAlertConditionSerializer());
            builder.registerTypeAdapter(SyntheticsAlertCondition.class, new SyntheticsAlertConditionDeserializer());
            builder.registerTypeAdapter(SYNTHETICS_ALERT_CONDITIONS_TYPE, new SyntheticsAlertConditionsDeserializer());
            builder.registerTypeHierarchyAdapter(InfraAlertCondition.class, new InfraAlertConditionSerializer());
            builder.registerTypeAdapter(InfraAlertCondition.class, new InfraAlertConditionDeserializer());
            builder.registerTypeAdapter(INFRA_ALERT_CONDITIONS_TYPE, new InfraAlertConditionsDeserializer());
            builder.registerTypeAdapter(ALERT_INCIDENTS_TYPE, new AlertIncidentsDeserializer());
            builder.registerTypeAdapter(ALERT_VIOLATIONS_TYPE, new AlertViolationsDeserializer());
            builder.registerTypeAdapter(ALERT_EVENTS_TYPE, new AlertEventsDeserializer());
            builder.registerTypeAdapter(Application.class, new ApplicationSerializer());
            builder.registerTypeAdapter(Application.class, new ApplicationDeserializer());
            builder.registerTypeAdapter(APPLICATIONS_TYPE, new ApplicationsDeserializer());
            builder.registerTypeAdapter(ApplicationHost.class, new ApplicationHostDeserializer());
            builder.registerTypeAdapter(APPLICATION_HOSTS_TYPE, new ApplicationHostsDeserializer());
            builder.registerTypeAdapter(ApplicationInstance.class, new ApplicationInstanceDeserializer());
            builder.registerTypeAdapter(APPLICATION_INSTANCES_TYPE, new ApplicationInstancesDeserializer());
            builder.registerTypeAdapter(BrowserApplication.class, new BrowserApplicationSerializer());
            builder.registerTypeAdapter(BrowserApplication.class, new BrowserApplicationDeserializer());
            builder.registerTypeAdapter(BROWSER_APPLICATIONS_TYPE, new BrowserApplicationsDeserializer());
            builder.registerTypeAdapter(MobileApplication.class, new MobileApplicationDeserializer());
            builder.registerTypeAdapter(MOBILE_APPLICATIONS_TYPE, new MobileApplicationsDeserializer());
            builder.registerTypeAdapter(KeyTransaction.class, new KeyTransactionDeserializer());
            builder.registerTypeAdapter(KEY_TRANSACTIONS_TYPE, new KeyTransactionsDeserializer());
            builder.registerTypeAdapter(METRICS_TYPE, new MetricsDeserializer());
            builder.registerTypeAdapter(MetricData.class, new MetricDataDeserializer());
            builder.registerTypeAdapter(ResponseError.class, new ResponseErrorDeserializer());
            gson = builder.create();
        }

        return gson;
    }

    /**
     * Ascertain if the MessageBodyReader can produce an instance of a particular type.
     * @param type The class of instance to be produced
     * @param genericType The type of instance to be produced
     * @param annotations An array of the annotations on the declaration of the artifact that will be initialized with the produced instance
     * @param mediaType The media type of the HTTP entity, if one is not specified in the request then application/octet-stream is used
     * @return <CODE>true</CODE> if the type is supported
     */
    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
    {    
        return true;
    }
    
    /**
     * Ascertain if the MessageBodyWriter supports a particular type.
     * @param type The class of instance to be written
     * @param genericType The type of instance to be written
     * @param annotations An array of the annotations attached to the message entity instance
     * @param mediaType The media type of the HTTP entity
     * @return <CODE>true</CODE> if the type is supported
     */
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
    {    
        return true;
    }

    /**
     * Deprecated, not implemented.
     */
    @Override
    public long getSize(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
    {    
        return -1;
    }
    
    /**
     * Write a type to a HTTP message.
     * @param object The instance to write
     * @param type The class of instance that is to be written
     * @param genericType The type of instance to be written
     * @param annotations An array of the annotations attached to the message entity instance
     * @param mediaType The media type of the HTTP entity
     * @param httpHeaders A mutable map of the HTTP message headers
     * @param entityStream the OutputStream for the HTTP entity 
     */
    @Override
    public void writeTo(Object object, Class<?> type, Type genericType, Annotation[] annotations,
        MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
        throws IOException, WebApplicationException
    {
        OutputStreamWriter outputStreamWriter = null;
        
        try
        {    
            outputStreamWriter = new OutputStreamWriter(entityStream, CHARSET);   
            Type jsonType = getAppropriateType(type, genericType);
            String json = getGson().toJson(object, jsonType);
            if(logger.isLoggable(Level.FINE))
                logger.fine("Outgoing JSON Entity: "+json);
            getGson().toJson(object, jsonType, outputStreamWriter);
        }
        finally
        {    
            if(outputStreamWriter != null)
                outputStreamWriter.close();
        }
    }

    /**
     * Read a type from the InputStream.
     * @param type The type that is to be read from the entity stream
     * @param genericType The type of instance to be produced
     * @param annotations An array of the annotations on the declaration of the artifact that will be initialized with the produced instance
     * @param mediaType The media type of the HTTP entity, if one is not specified in the request then application/octet-stream is used
     * @param httpHeaders The read-only HTTP headers associated with HTTP entity
     * @param inputStream The InputStream of the HTTP entity
     * @return The type that was read from the stream
     */
    @Override
    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations,
        MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream inputStream)
        throws IOException, WebApplicationException
    {
        String json = null;
        Object result = null;
        InputStreamReader inputStreamReader = null;
        
        try
        {
            inputStreamReader = new InputStreamReader(inputStream, CHARSET);   
            Type jsonType = getAppropriateType(type, genericType);
            json = getStringFromInputStream(inputStream);
            if(logger.isLoggable(Level.FINE))
                logger.fine("Incoming JSON Entity: "+json);
            result = getGson().fromJson(json, jsonType);
        }
        catch(JsonSyntaxException e)
        {
            logger.severe("Error in Incoming JSON Entity: "+json);
        }
        finally
        {
            if(inputStreamReader != null)
                inputStreamReader.close();
        }
        
        return result;
    }
    
    /**
     * Returns the type of the given class.
     * @param type The class to get the type for
     * @param genericType The type to use if the input class is a generic type
     * @return The type of the given class
     */
    private Type getAppropriateType(Class<?> type, Type genericType)
    {    
        return type.equals(genericType) ? type : genericType;
    }

    /**
     * Extracts the string from the given input stream.
     * @param is The input stream
     * @return The string extracted
     */
    private static String getStringFromInputStream(InputStream is)
    {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try
        {
            br = new BufferedReader(new InputStreamReader(is));
            while((line = br.readLine()) != null)
            {
                sb.append(line);
            }

        }
        catch(IOException e)
        {
            logger.severe("Unable to get string from stream: "+e.getClass().getName()+": "+e.getMessage());
        }
        finally
        {
            try
            {
                if(br != null)
                    br.close();
            }
            catch(IOException e)
            {
            }
        }

        return sb.toString();
    }

}