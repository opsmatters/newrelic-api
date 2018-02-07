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

package com.opsmatters.newrelic.api.services;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.labels.Label;

/**
 * The set of operations used for labels.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class LabelService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public LabelService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of labels.
     * @return The set of labels
     */
    public Collection<Label> list()
    {
        return HTTP.GET("/v2/labels.json", null, null, LABELS).get();
    }

    /**
     * Returns the set of labels where the key contains the given (partial) name.
     * @param name The name of the labels to return. Can be a partial key. A null value returns all labels.
     * @return The set of labels
     */
    public Collection<Label> list(String name)
    {
        List<Label> ret = new ArrayList<Label>();
        Collection<Label> labels = list();
        for(Label label : labels)
        {
            if(name == null || label.getKey().toLowerCase().indexOf(name) != -1)
                ret.add(label);
        }
        return ret;
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
        return HTTP.PUT("/v2/labels.json", label, LABEL);
    }

    /**
     * Deletes the label with the given key.
     * @param key The key of the label to delete
     * @return This object
     */
    public LabelService delete(String key)
    {
        HTTP.DELETE(String.format("/v2/labels/%s.json", encode(key)));       
        return this;
    }
}
