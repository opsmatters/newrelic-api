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

package com.opsmatters.newrelic.api.model.condition;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a New Relic alert condition with terms.
 * 
 * @author Gerald Curley (opsmatters)
 */
public abstract class TermsCondition extends BaseCondition
{
    private List<Term> terms = new ArrayList<Term>();
    
    /**
     * Default constructor.
     */
    public TermsCondition()
    {
    }
    
    /**
     * Sets the terms of the alert condition.
     * @param terms The terms of the alert condition
     */
    public void setTerms(List<Term> terms)
    {
        this.terms = terms;
    }

    /**
     * Adds a term to the alert condition.
     * @param term The term to be added
     */
    public void addTerm(Term term)
    {
        this.terms.add(term);
    }

    /**
     * Returns the terms of the alert condition.
     * @return The terms of the alert condition
     */
    public List<Term> getTerms()
    {
        return terms;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return super.toString()
            +", terms="+terms;
    }
}