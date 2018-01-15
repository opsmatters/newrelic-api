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

package com.opsmatters.newrelic.api;

import java.util.Collection;
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.model.labels.Label;

/**
 * The set of operations used for labels.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class LabelOperations extends BaseFluent
{
    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param service The set of API operations
     */
    public LabelOperations(HttpContext httpContext, NewRelicService service)
    {
        super(httpContext, service);
    }

    /**
     * Returns the set of labels.
     * @return The set of labels
     */
    public Collection<Label> list()
    {
        return HTTP.GET("/labels.json", null, null, LABELS).get();
    }

    /**
     * Returns the label with the given key.
     * <P>
     * This is needed because the API does not contain an operation to get a label using the key directly.
     * @param key The key of the label to return
     * @return The label
     */
    public Optional<Label> show(String key)
    {
        Optional<Label> ret = Optional.absent();
        Collection<Label> labels = list();
        for(Label label : labels)
        {
            if(label.getKey().equals(key))
                ret = Optional.of(label);
        }
        return ret;
    }
    
    /**
     * Creates the given label.
     * @param label The label to create
     * @return The label that was created
     */
    public Optional<Label> create(Label label)
    {
        return HTTP.PUT("/labels.json", label, LABEL);
    }

    /**
     * Deletes the label with the given key.
     * @param key The key of the label to delete
     * @return This object
     */
    public LabelOperations delete(String key)
    {
        HTTP.DELETE(String.format("/labels/%s.json", key));       
        return this;
    }
}
