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

package com.opsmatters.newrelic.api.model.insights.widgets;

/**
 * Represents a New Relic Insights dashboard presentation.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Presentation
{
    private String title;

    private String notes;

    /**
     * Default constructor.
     */
    public Presentation()
    {
    }

    /**
     * Sets the title of the presentation.
     * @param title The title of the presentation
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Returns the title of the presentation.
     * @return The title of the presentation
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets the notes of the presentation.
     * @param notes The notes of the presentation
     */
    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    /**
     * Returns the notes of the presentation.
     * @return The notes of the presentation
     */
    public String getNotes()
    {
        return notes;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Presentation [title="+title
            +", notes="+notes
            +"]";
    }

    /**
     * Returns a builder for the presentation.
     * @return The builder instance.
     */
    public static PresentationBuilder<?,?> builder()
    {
        return new Builder();
    }

    /**
     * Abstract Builder from which the other presentation Builders derive.
     */
    public abstract static class PresentationBuilder<T extends Presentation, B extends PresentationBuilder<T,B>>
    {
        private Presentation presentation;

        /**
         * Sets the presentation.
         * @param presentation The presentation
         * @return This object
         */
        public B presentation(Presentation presentation)
        {
            this.presentation = presentation;
            return self();
        }

        /**
         * Sets the title of the presentation.
         * @param title The title of the presentation
         * @return This object
         */
        public B title(String title)
        {
            presentation.setTitle(title);
            return self();
        }

        /**
         * Sets the notes of the presentation.
         * @param notes The notes of the presentation
         * @return This object
         */
        public B notes(String notes)
        {
            presentation.setNotes(notes);
            return self();
        }

        /**
         * Returns this object.
         * @return This object
         */
        protected abstract B self();

        /**
         * Returns the configured presentation instance
         * @return The presentation instance
         */
        public abstract T build();
    }

    /**
     * Builder to make presentation construction easier.
     */
    public static class Builder extends PresentationBuilder<Presentation, Builder>
    {
        private Presentation presentation = new Presentation();

        /**
         * Default constructor.
         */
        public Builder()
        {
            presentation(presentation);
        }

        /**
         * Returns this object.
         * @return This object
         */
        @Override
        protected Builder self()
        {
            return this;
        }

        /**
         * Returns the configured presentation instance
         * @return The presentation instance
         */
        @Override
        public Presentation build()
        {
            return presentation;
        }
    }
}