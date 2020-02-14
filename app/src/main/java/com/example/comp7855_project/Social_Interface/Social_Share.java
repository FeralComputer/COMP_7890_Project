package com.example.comp7855_project.Social_Interface;

import android.content.Intent;
import android.net.Uri;
import android.widget.EditText;

import com.example.comp7855_project.MainActivity;
import com.example.comp7855_project.R;

import java.io.File;

import androidx.core.content.FileProvider;

public class Social_Share {
    public static void share(String extra_info, String photopath, MainActivity main_activity)
    {
        Intent share=new Intent();
        share.setAction(Intent.ACTION_SEND);
        share.setType("image/*");
        share.putExtra(Intent.EXTRA_TEXT,extra_info);
        share.putExtra(Intent.EXTRA_SUBJECT,""+extra_info);
        Uri photoURI = FileProvider.getUriForFile(main_activity,
                "com.example.comp7855_project.fileprovider",
                new File(photopath));
        share.putExtra(Intent.EXTRA_STREAM,photoURI);
        Intent shareActivity=Intent.createChooser(share,null);
        main_activity.startActivity(shareActivity);
    }
}
