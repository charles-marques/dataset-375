/* Copyright (C) 2009, 2010 QuietlyCoding <mike@quietlycoding.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.quietlycoding.android.reader.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.quietlycoding.android.reader.R;
import com.quietlycoding.android.reader.provider.Reader;

public class PostView extends Activity {
    private static final String TAG = "Reader.PostView";

    private static final String[] PROJECTION = new String[] { BaseColumns._ID,
            Reader.Posts.CHANNEL_ID, Reader.Posts.TITLE, Reader.Posts.BODY, Reader.Posts.READ,
            Reader.Posts.URL, Reader.Posts.STARRED };

    private ViewFlipper mFlip;
    private Cursor mCursor;
    private long mChannelId;
    private long mPostId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.post_view);

        mFlip = (ViewFlipper) findViewById(R.id.post_flip);

        // we use an AsyncTask to keep pulling the next three items in the list
        // for easy scroll.
        final Uri uri = getIntent().getData();

        mCursor = managedQuery(uri, PROJECTION, null, null, null);

        mCursor.moveToNext();
        mChannelId = mCursor.getLong(mCursor.getColumnIndex(Reader.Posts.CHANNEL_ID));
        mPostId = Long.parseLong(uri.getPathSegments().get(1));

        final ContentValues values = new ContentValues();
        values.put(Reader.Posts.READ, 1);
        getContentResolver().update(getIntent().getData(), values, null, null);
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        final LinearLayout v = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.post_view_item, null);
        final TextView postTitle = (TextView) v.findViewById(R.id.post_view_title);

        final String title = mCursor.getString(mCursor.getColumnIndex(Reader.Posts.TITLE));
        postTitle.setText(title);

        final WebView postText = (WebView) v.findViewById(R.id.post_view_text);

        final String html = "<html><head><style type=\"text/css\">body { background-color: #201c19; color: white; } a { color: #ddf; }</style></head><body>"
                + getBody() + "</body></html>";

        postText.loadData(html, "text/html", "utf-8");

        // add view to the flipper.
        mFlip.addView(v);
    }

    private String getBody() {
        String body = mCursor.getString(mCursor.getColumnIndex(Reader.Posts.BODY));

        Log.d(TAG, "Contents of the database: " + body);

        final String url = mCursor.getString(mCursor.getColumnIndex(Reader.Posts.URL));

        if (hasMoreLink(body, url) == false) {
            body += "<p><a href=\"" + url + "\">Read more...</a></p>";
        }

        /*
         * TODO: We should add a check for "posted by", "written by",
         * "posted on", etc, and optionally add our own tagline if the
         * information is in the feed.
         */
        return body;
    }

    private boolean hasMoreLink(String body, String url) {
        int urlpos;

        /*
         * Check if the body contains an anchor reference with the destination
         * of the read more URL we got from the feed.
         */
        if ((urlpos = body.indexOf(url)) <= 0) {
            return false;
        }

        try {
            /* TODO: Improve this check with a full look-behind parse. */
            if (body.charAt(urlpos - 1) != '>') {
                return false;
            }

            if (body.charAt(urlpos + url.length() + 1) != '<') {
                return false;
            }
        } catch (final IndexOutOfBoundsException e) {
            return false;
        }

        return true;
    }
}
