package com.example.comp7855_project.Utils;

import android.os.Environment;

import com.example.comp7855_project.MainActivity;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Search_Functions {


    public static ArrayList<String> populateGallery(MainActivity mainActivity, Date minDate, Date maxDate) {
        File dir = mainActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "/Android/data/com.example.comp7855_project/files/Pictures");
        mainActivity.photoGallery = new ArrayList<String>();
        File[] fList = file.listFiles();
        if (fList != null) {
            for (File f : file.listFiles()) {
                String fileDate = f.getName().split("_")[1];
                Date d = new Date(Long.MIN_VALUE);
                try{
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    d = dateFormat.parse(fileDate);
                }catch(ParseException ex){
                    // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
                }
                if(d.after(minDate) && d.before(maxDate))
                {
                    mainActivity.photoGallery.add(f.getPath());
                }
            }
        }
        return mainActivity.photoGallery;
    }

    public static ArrayList<String> populateGallery(MainActivity mainActivity, Date minDate, Date maxDate, String caption) {
        File dir = mainActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "/Android/data/com.example.comp7855_project/files/Pictures");
        mainActivity.photoGallery = new ArrayList<String>();
        File[] fList = file.listFiles();
        if (fList != null) {
            for (File f : file.listFiles()) {
                String fileDate = f.getName().split("_")[1];
                Date d = new Date(Long.MIN_VALUE);
                try{
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    d = dateFormat.parse(fileDate);
                }catch(ParseException ex){
                    // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
                }
                if(d.after(minDate) && d.before(maxDate))
                {
                    String filename = f.getName();
                    List<String> data = mainActivity.PictureDatabase.get(filename);
                    if(data != null) {
                        String name = data.get(3);
                        if (name.equals(caption))
                            mainActivity.photoGallery.add(f.getPath());
                    }
                }
            }
        }
        return mainActivity.photoGallery;
    }

    public static ArrayList<String> populateGallery(MainActivity mainActivity, Date minDate, Date maxDate, double x, double y, double rad) {
        File dir = mainActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "/Android/data/com.example.comp7855_project/files/Pictures");
        mainActivity.photoGallery = new ArrayList<String>();
        File[] fList = file.listFiles();
        if (fList != null) {
            for (File f : file.listFiles()) {
                String fileDate = f.getName().split("_")[1];
                Date d = new Date(Long.MIN_VALUE);
                try{
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    d = dateFormat.parse(fileDate);
                }catch(ParseException ex){
                    // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
                }
                if(d.after(minDate) && d.before(maxDate))
                {
                    String filename = f.getName();
                    List<String> data = mainActivity.PictureDatabase.get(filename);
                    if(data != null && !data.get(1).equals("N/A")) {
                        Double xval = Double.valueOf(data.get(1));
                        Double yval = Double.valueOf(data.get(2));
                        if (Math.sqrt((x - xval) * (x - xval) + (y - yval) * (y - yval)) < rad) {
                            mainActivity.photoGallery.add(f.getPath());
                        }
                    }
                }
            }
        }
        return mainActivity.photoGallery;
    }

    public static ArrayList<String> populateGallery(MainActivity mainActivity, Date minDate, Date maxDate, String caption, double x, double y, double rad) {
        File dir = mainActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "/Android/data/com.example.comp7855_project/files/Pictures");
        mainActivity.photoGallery = new ArrayList<String>();
        File[] fList = file.listFiles();
        if (fList != null) {
            for (File f : file.listFiles()) {
                String fileDate = f.getName().split("_")[1];
                Date d = new Date(Long.MIN_VALUE);
                try{
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    d = dateFormat.parse(fileDate);
                }catch(ParseException ex){
                    // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
                }
                if(d.after(minDate) && d.before(maxDate))
                {
                    String filename = f.getName();
                    List<String> data = mainActivity.PictureDatabase.get(filename);
                    if(data != null && !data.get(1).equals("N/A")) {
                        Double xval = Double.valueOf(data.get(1));
                        Double yval = Double.valueOf(data.get(2));
                        if (Math.sqrt((x - xval)*(x - xval) + (y - yval)*(y - yval)) < rad)
                        {
                                String name = data.get(3);
                                if (name.equals(caption))
                                    mainActivity.photoGallery.add(f.getPath());
                            }
                    }
                }
            }
        }
        return mainActivity.photoGallery;
    }
}
