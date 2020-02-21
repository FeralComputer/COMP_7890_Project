package com.example.comp7855_project.Social_Interface;

import android.os.AsyncTask;
import android.content.Intent;
import android.net.Uri;
import android.widget.EditText;

import com.example.comp7855_project.MainActivity;
import com.example.comp7855_project.R;

import java.io.File;

import androidx.core.content.FileProvider;

import java.io.File;

// FEELING CUTE, MIGHT DELETE LATER

public class UploadPhotoTask extends AsyncTask<Void, Void, Void> {

    MainActivity main_activity;
    String extra_info;
    String photopath;

    public UploadPhotoTask(String _extra_info, String _photopath, MainActivity a)
    {
        main_activity = a;
        extra_info = _extra_info;
        photopath = _photopath;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Intent share=new Intent();
        share.setAction(Intent.ACTION_SEND);
        share.setType("image/*");
        share.putExtra(Intent.EXTRA_TEXT,extra_info);
        share.putExtra(Intent.EXTRA_SUBJECT,""+extra_info);
        Uri photoURI = FileProvider.getUriForFile(main_activity,
                "com.example.comp7855_project.fileprovider",
                new File(photopath));
        share.setData(photoURI);
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        share.putExtra(Intent.EXTRA_STREAM,photoURI);
        Intent shareActivity=Intent.createChooser(share,null);
        main_activity.startActivity(shareActivity);
        return null;
    }
}
