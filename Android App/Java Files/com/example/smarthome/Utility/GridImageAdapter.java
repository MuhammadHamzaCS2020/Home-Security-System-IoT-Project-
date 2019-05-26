package com.example.smarthome.Utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.example.instagramcloneapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GridImageAdapter extends ArrayAdapter<String>
{
    private Context mContext;
    private LayoutInflater layoutInflater;
    private int layoutResource;
    private String mappend;
    private ArrayList<String> imgURLs;

    public GridImageAdapter(Context mContext, int layoutResource, String mappend, ArrayList<String> imgURLs) {
        super(mContext,layoutResource,imgURLs);
        this.mContext = mContext;
        this.layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        this.layoutResource = layoutResource;
        this.mappend = mappend;
        this.imgURLs = imgURLs;
    }


    private class ViewHolder
    {
        private ProgressBar mProgressBar;
        private SquareImageView image;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(layoutResource,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.image= convertView.findViewById(R.id.gridViewImage);
            viewHolder.mProgressBar = convertView.findViewById(R.id.gridViewProgressBar);

            convertView.setTag(viewHolder); // setTag saves viewHolder as Widget in memory.
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String imageURLs = getItem(position);
        ImageLoader.getInstance().displayImage(mappend + imageURLs, viewHolder.image, new ImageLoadingListener()
        {

            @Override
            public void onLoadingStarted(String imageUri, View view)
            {
                if(viewHolder.mProgressBar !=null)
                {
                    viewHolder.mProgressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason)
            {
                if(viewHolder.mProgressBar !=null)
                {
                    viewHolder.mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
            {
                if(viewHolder.mProgressBar !=null)
                {
                    viewHolder.mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view)
            {
                if(viewHolder.mProgressBar !=null)
                {
                    viewHolder.mProgressBar.setVisibility(View.GONE);
                }
            }
        });
        return convertView;
    }
}
