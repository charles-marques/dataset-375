/* Copyright 2012 Smartling, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this work except in compliance with the License.
 * You may obtain a copy of the License in the LICENSE file, or at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.smartling.api.sdk.file;

import java.util.Date;
import java.util.List;

/**
 * Contains the search parameters that can be set when executing a file/list query through the Smartling Translation API.
 */
public class FileListSearchParams
{
    private String       locale;
    private String       uriMask;
    private List<String> fileTypes;
    private Date         lastUploadedAfter;
    private Date         lastUploadedBefore;
    private Integer      offset;
    private Integer      limit;
    private List<String> conditions;
    private List<String> orderBy;

    public String getLocale()
    {
        return locale;
    }

    public void setLocale(String locale)
    {
        this.locale = locale;
    }

    public String getUriMask()
    {
        return uriMask;
    }

    public void setUriMask(String uriMask)
    {
        this.uriMask = uriMask;
    }

    public List<String> getFileTypes()
    {
        return fileTypes;
    }

    public void setFileTypes(List<String> fileTypes)
    {
        this.fileTypes = fileTypes;
    }

    public Date getLastUploadedAfter()
    {
        return lastUploadedAfter;
    }

    public void setLastUploadedAfter(Date lastUploadedAfter)
    {
        this.lastUploadedAfter = lastUploadedAfter;
    }

    public Date getLastUploadedBefore()
    {
        return lastUploadedBefore;
    }

    public void setLastUploadedBefore(Date lastUploadedBefore)
    {
        this.lastUploadedBefore = lastUploadedBefore;
    }

    public Integer getOffset()
    {
        return offset;
    }

    public void setOffset(Integer offset)
    {
        this.offset = offset;
    }

    public Integer getLimit()
    {
        return limit;
    }

    public void setLimit(Integer limit)
    {
        this.limit = limit;
    }

    public List<String> getConditions()
    {
        return conditions;
    }

    public void setConditions(List<String> conditions)
    {
        this.conditions = conditions;
    }

    public List<String> getOrderBy()
    {
        return orderBy;
    }

    public void setOrderBy(List<String> orderBy)
    {
        this.orderBy = orderBy;
    }
}
