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
import com.opsmatters.newrelic.api.model.AlertPolicy;
import com.opsmatters.newrelic.api.model.AlertPolicyChannel;
import com.opsmatters.newrelic.api.model.ResponseError;
import com.opsmatters.newrelic.api.model.channel.AlertChannel;
import com.opsmatters.newrelic.api.model.condition.AlertCondition;
import com.opsmatters.newrelic.api.model.condition.NrqlAlertCondition;
import com.opsmatters.newrelic.api.model.condition.ExternalServiceAlertCondition;
import com.opsmatters.newrelic.api.model.condition.PluginsAlertCondition;
import com.opsmatters.newrelic.api.model.condition.SyntheticsAlertCondition;
import com.opsmatters.newrelic.api.model.condition.InfraAlertCondition;
import com.opsmatters.newrelic.httpclient.serializers.AlertPolicySerializer;
import com.opsmatters.newrelic.httpclient.serializers.channel.AlertChannelSerializer;
import com.opsmatters.newrelic.httpclient.serializers.condition.AlertConditionSerializer;
import com.opsmatters.newrelic.httpclient.serializers.condition.NrqlAlertConditionSerializer;
import com.opsmatters.newrelic.httpclient.serializers.condition.ExternalServiceAlertConditionSerializer;
import com.opsmatters.newrelic.httpclient.serializers.condition.PluginsAlertConditionSerializer;
import com.opsmatters.newrelic.httpclient.serializers.condition.SyntheticsAlertConditionSerializer;
import com.opsmatters.newrelic.httpclient.serializers.condition.InfraAlertConditionSerializer;
import com.opsmatters.newrelic.httpclient.deserializers.AlertPolicyDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.AlertPoliciesDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.AlertPolicyChannelDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.ResponseErrorDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.channel.AlertChannelsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.condition.AlertConditionDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.condition.AlertConditionsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.condition.NrqlAlertConditionDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.condition.NrqlAlertConditionsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.condition.ExternalServiceAlertConditionDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.condition.ExternalServiceAlertConditionsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.condition.PluginsAlertConditionDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.condition.PluginsAlertConditionsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.condition.SyntheticsAlertConditionDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.condition.SyntheticsAlertConditionsDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.condition.InfraAlertConditionDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.condition.InfraAlertConditionsDeserializer;

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