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

package com.opsmatters.newrelic.util;

import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

/**
 * The set of utility methods used by the API classes.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Utils
{
    /**
     * The date format used by ISO8601.
     */
    public static final String ISO8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * The date format without time.
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * The date format used by NRQL queries.
     */
    public static final String NRQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Private constructor.
     */
    private Utils()
    {
    }

    /**
     * Returns the given date time formatted using the given format and timezone.
     * @param dt The date to format (in milliseconds)
     * @param tz The timezone for the date (or null)
     * @param format The format to use for the date
     * @return The formatted date
     */
    static public String getFormattedDateTime(long dt, TimeZone tz, String format)
    {
        SimpleDateFormat df = new SimpleDateFormat(format);
        if(tz != null)
            df.setTimeZone(tz);
        return df.format(new Date(dt));
    }

    /**
     * Returns the given date time formatted using the given format.
     * @param dt The date to format (in milliseconds)
     * @param format The format to use for the date
     * @return The formatted date
     */
    static public String getFormattedDateTime(long dt, String format)
    {
        return getFormattedDateTime(dt, null, format);
    }

    /**
     * Returns the given date time formatted using the ISO8601 format.
     * @param dt The date to format (in milliseconds)
     * @return The date in ISO8601 format
     */
    static public String getUtcDateTime(long dt)
    {
        return getFormattedDateTime(dt, TimeZone.getTimeZone("GMT"), ISO8601_FORMAT);
    }

    /**
     * Returns the given date time formatted using the given format.
     * @param dt The date to format (in milliseconds)
     * @param format The format to use for the date
     * @return The formatted date
     */
    static public String getFormattedDate(long dt, String format)
    {
        return getFormattedDateTime(dt, null, format);
    }

    /**
     * Returns the given date time formatted using the YYYY-MM-DD format.
     * @param dt The date to format (in milliseconds)
     * @return The date in YYYY-MM-DD format
     */
    static public String getFormattedDate(long dt)
    {
        return getFormattedDate(dt, DATE_FORMAT);
    }

    /**
     * Returns the given date time formatted using the NRQL format.
     * @param dt The date to format (in milliseconds)
     * @return The date in NRQL format
     */
    static public String getNrqlDateTime(long dt)
    {
        return getFormattedDateTime(dt, null, NRQL_DATE_FORMAT);
    }
}
