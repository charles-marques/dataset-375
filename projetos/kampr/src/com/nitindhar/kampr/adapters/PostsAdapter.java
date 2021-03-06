package com.nitindhar.kampr.adapters;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nitindhar.kampr.R;
import com.nitindhar.kampr.models.PostDecorator;
import com.nitindhar.kampr.util.TimeUtils;

public class PostsAdapter<T> extends AbstractListAdapter<T> {
    
    public PostsAdapter(Context context, List<T> posts) {
        super(context, posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = getConvertView(convertView, R.layout.post_item);

        PostDecorator pd = (PostDecorator) _objects.get(position);

        ImageView postTypeIcon = (ImageView) getViewHandle(convertView, R.id.post_item_type_icon);
        
        if (pd.getPost().getPostType().equals("link")) {
            postTypeIcon.setImageDrawable(_context.getResources().getDrawable(R.drawable.list_link));
        }
        else if (pd.getPost().getPostType().equals("snap")) {
            postTypeIcon.setImageDrawable(_context.getResources().getDrawable(R.drawable.list_snap));
        }
        else if (pd.getPost().getPostType().equals("code")) {
            postTypeIcon.setImageDrawable(_context.getResources().getDrawable(R.drawable.list_code));
        }
        else {
            postTypeIcon.setImageDrawable(_context.getResources().getDrawable(R.drawable.list_question));
        }

        ImageView postUserIcon = (ImageView) getViewHandle(convertView, R.id.user_icon_thumbnail);
        postUserIcon.setImageBitmap(pd.getUserIcon());

        TextView postUsername = (TextView) getViewHandle(convertView, R.id.post_item_username);
        postUsername.setText(pd.getPost().getUser().getName());

        TextView postDate = (TextView) getViewHandle(convertView, R.id.post_item_date);
        postDate.setText(TimeUtils.getPostDate(pd.getPost().getCreatedAt()));
        
        TextView postLikes = (TextView) getViewHandle(convertView, R.id.post_likes_count);
        postLikes.setText(Integer.toString(pd.getPost().getLikeCount()));
        
        TextView postViews = (TextView) getViewHandle(convertView, R.id.post_views_count);
        postViews.setText(Integer.toString(pd.getPost().getViewCount()));
        
        TextView postComments = (TextView) getViewHandle(convertView, R.id.post_comments_count);
        postComments.setText(Integer.toString(pd.getPost().getCommentCount()));

        TextView postTitle = (TextView) getViewHandle(convertView, R.id.post_item_content);
        postTitle.setText(pd.getPost().getTitle());

        convertView.setId(pd.getPost().getId());

        return convertView;
    }
    
    public PostDecorator getViewObject(int position) {
        return (PostDecorator) _objects.get(position);
    }

}