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

package com.opsmatters.newrelic.api.httpclient.deserializers;

import java.lang.reflect.Type;
import com.google.gson.*;
import com.opsmatters.newrelic.api.model.ErrorResponse;
import com.opsmatters.newrelic.api.model.ErrorDetail;
import com.opsmatters.newrelic.api.model.ErrorDetails;
import com.opsmatters.newrelic.api.model.ErrorMessages;

/**
 * Deserializer class for response errors.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ErrorResponseDeserializer implements JsonDeserializer<ErrorResponse>
{
    private static Gson gson = new Gson();

    /**
     * Gson invokes this call-back method during deserialization when it encounters a field of the specified type.
     * @param element The Json data being deserialized
     * @param type The type of the Object to deserialize to 
     * @param context The JSON deserialization context
     * @return The response errors
     */
    @Override
    public ErrorResponse deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        throws JsonParseException
    {
        JsonObject obj = element.getAsJsonObject();
        JsonElement error = obj.get("error");
        JsonElement errors = obj.get("errors");

        if(errors != null)
            return gson.fromJson(element, ErrorDetails.class);
        else if(error != null && error.isJsonObject())
            return gson.fromJson(error, ErrorMessages.class);
        else
            return gson.fromJson(element, ErrorDetail.class);
    }
}