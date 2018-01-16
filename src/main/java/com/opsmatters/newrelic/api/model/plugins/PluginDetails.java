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

package com.opsmatters.newrelic.api.model.plugins;

import java.util.Date;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic plugin details.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PluginDetails
{
    @SerializedName("short_name")
    private String shortName;

    private String description;

    @SerializedName("published_version")
    private String publishedVersion;

    @SerializedName("is_public")
    private Boolean isPublic;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("upgraded_at")
    private Date upgradedAt;

    @SerializedName("first_published_at")
    private Date firstPublishedAt;

    @SerializedName("last_published_at")
    private Date lastPublishedAt;

    @SerializedName("first_edited_at")
    private Date firstEditedAt;

    @SerializedName("last_edited_at")
    private Date lastEditedAt;

    @SerializedName("has_unpublished_changes")
    private Boolean hasUnpublishedChanges;

    @SerializedName("branding_image_url")
    private String brandingImageUrl;

    @SerializedName("publisher_about_url")
    private String publisherAboutUrl;

    @SerializedName("publisher_support_url")
    private String publisherSupportUrl;

    @SerializedName("download_url")
    private String downloadUrl;

    /**
     * Default constructor.
     */
    public PluginDetails()
    {
    }

    /**
     * Returns the short name of the plugin.
     * @return The short name of the plugin
     */
    public String getShortName()
    {
        return shortName;
    }
    
    /**
     * Returns the description of the plugin.
     * @return The description of the plugin
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Returns the published version of the plugin.
     * @return The published version of the plugin
     */
    public String getPublishedVersion()
    {
        return publishedVersion;
    }

    /**
     * Returns <CODE>true</CODE> if the plugin is public.
     * @return <CODE>true</CODE> if the plugin is public
     */
    public boolean isPublic()
    {
        return isPublic;
    }

    /**
     * Returns the date the plugin was created.
     * @return The date the plugin was created
     */
    public Date getCreatedAt()
    {
        return createdAt;
    }

    /**
     * Returns the date the plugin was last updated.
     * @return The date the plugin was last updated
     */
    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    /**
     * Returns the date the plugin was last upgraded.
     * @return The date the plugin was last upgraded
     */
    public Date getUpgradedAt()
    {
        return upgradedAt;
    }

    /**
     * Returns the date the plugin was first published.
     * @return The date the plugin was first published
     */
    public Date getFirstPublishedAt()
    {
        return firstPublishedAt;
    }

    /**
     * Returns the date the plugin was last published.
     * @return The date the plugin was last published
     */
    public Date getLastPublishedAt()
    {
        return lastPublishedAt;
    }

    /**
     * Returns the date the plugin was first edited.
     * @return The date the plugin was first edited
     */
    public Date getFirstEditedAt()
    {
        return firstEditedAt;
    }

    /**
     * Returns the date the plugin was last edited.
     * @return The date the plugin was last edited
     */
    public Date getLastEditedAt()
    {
        return lastEditedAt;
    }

    /**
     * Returns <CODE>true</CODE> if the plugin has unpublished changes.
     * @return <CODE>true</CODE> if the plugin has unpublished changes
     */
    public boolean hasUnpublishedChanges()
    {
        return hasUnpublishedChanges;
    }

    /**
     * Returns the branding image url of the plugin.
     * @return The branding image url of the plugin
     */
    public String getBrandingImageUrl()
    {
        return brandingImageUrl;
    }

    /**
     * Returns the publisher about url of the plugin.
     * @return The publisher about url of the plugin
     */
    public String getPublisherAboutUrl()
    {
        return publisherAboutUrl;
    }

    /**
     * Returns the publisher support url of the plugin.
     * @return The publisher support url of the plugin
     */
    public String getPublisherSupportUrl()
    {
        return publisherSupportUrl;
    }

    /**
     * Returns the download url of the plugin.
     * @return The download url of the plugin
     */
    public String getDownloadUrl()
    {
        return downloadUrl;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "PluginDetails [shortName="+shortName
            +", description="+description
            +", publishedVersion="+publishedVersion
            +", isPublic="+isPublic
            +", createdAt="+createdAt
            +", updatedAt="+updatedAt
            +", upgradedAt="+upgradedAt
            +", firstPublishedAt="+firstPublishedAt
            +", lastPublishedAt="+lastPublishedAt
            +", firstEditedAt="+firstEditedAt
            +", lastEditedAt="+lastEditedAt
            +", hasUnpublishedChanges="+hasUnpublishedChanges
            +", brandingImageUrl="+brandingImageUrl
            +", publisherAboutUrl="+publisherAboutUrl
            +", publisherSupportUrl="+publisherSupportUrl
            +", downloadUrl="+downloadUrl
            +"]";
    }
}