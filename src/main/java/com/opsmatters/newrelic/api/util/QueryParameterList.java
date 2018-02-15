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

package com.opsmatters.newrelic.api.util;

import java.util.List;
import java.util.ArrayList;

/**
 * Implementation of a list of URL query parameters.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class QueryParameterList extends ArrayList<String>
{
    /**
     * Adds the given query parameter name and value.
     * @param name The name of the parameter
     * @param value The value of the parameter
     * @return <CODE>true</CODE> if the add was successful
     */
    public boolean add(String name, Object value)
    {
        if(name == null)
            throw new NullPointerException("name == null");
        add(name);
        add(value.toString());
        return true;
    }
}
