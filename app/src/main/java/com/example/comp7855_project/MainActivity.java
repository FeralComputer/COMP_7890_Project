package com.example.comp7855_project;

//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.support.v4.content.FileProvider;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.TextView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.example.comp7855_project.Social_Interface.Social_Share;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;

//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;


//import java.io.File;
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Locale;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int SEARCH_ACTIVITY_REQUEST_CODE = 0;
    static final int CAMERA_REQUEST_CODE = 1;
    private String currentPhotoPath = null;
    private int currentPhotoIndex = 0;
    private ArrayList<String> photoGallery;
    public Map<String, List<String>> PictureDatabase = new HashMap<String, List<String>>();
    public FusedLocationProviderClient client;
    public String Latitude;
    public String Longitude;
    public Date minDate = new Date(Long.MIN_VALUE);
    public Date maxDate = new Date(Long.MAX_VALUE);
    DateFormatSymbols dfs = new DateFormatSymbols();
    public String[] months = dfs.getMonths();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText Tag = (EditText) findViewById(R.id.entryCaption);
        Tag.clearFocus();
        Button btnLeft = (Button)findViewById(R.id.btnLeft);
        Button btnRight = (Button)findViewById(R.id.btnRight);
        Button btnFilter = (Button)findViewById(R.id.btnFilter);
        Button btnCamera = (Button)findViewById(R.id.btnCamera);
        Button btnShare = (Button)findViewById(R.id.btnShare);

        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        btnShare.setOnClickListener(this);

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SearchActivity.class);
                startActivityForResult(i, SEARCH_ACTIVITY_REQUEST_CODE);

            }
        });

        Latitude = "N/A";
        Longitude = "N/A";

        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION}, 1);

        client = LocationServices.getFusedLocationProviderClient(this);

        try
        {
            FileInputStream fileInputStream = new FileInputStream(this.getFilesDir()+"/PhotoData.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            PictureDatabase = (Map<String, List<String>>)objectInputStream.readObject();
        }
        catch(ClassNotFoundException | IOException | ClassCastException e) {
            e.printStackTrace();
        }


        photoGallery = populateGallery(minDate, maxDate);
        Log.d("onCreate, size", Integer.toString(photoGallery.size()));
        if (photoGallery.size() > 0)
            currentPhotoPath = photoGallery.get(currentPhotoIndex);
        displayPhoto(currentPhotoPath);

        client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Latitude = String.valueOf((int)location.getLatitude());
                    Longitude = String.valueOf((int)location.getLongitude());
                }
                else
                    Log.d("Location Was Null", Integer.toString(123));
            }
        });
    }


    public ArrayList<String> populateGallery(Date minDate, Date maxDate) {
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "/Android/data/com.example.comp7855_project/files/Pictures");
        photoGallery = new ArrayList<String>();
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
                    photoGallery.add(f.getPath());
                }
            }
        }
        return photoGallery;
    }

    public ArrayList<String> populateGallery(Date minDate, Date maxDate, String caption) {
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "/Android/data/com.example.comp7855_project/files/Pictures");
        photoGallery = new ArrayList<String>();
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
                    List<String> data = PictureDatabase.get(filename);
                    if(data != null) {
                        String name = data.get(3);
                        if (name.equals(caption))
                            photoGallery.add(f.getPath());
                    }
                }
            }
        }
        return photoGallery;
    }

    public ArrayList<String> populateGallery(Date minDate, Date maxDate, double x, double y, double rad) {
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "/Android/data/com.example.comp7855_project/files/Pictures");
        photoGallery = new ArrayList<String>();
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
                    List<String> data = PictureDatabase.get(filename);
                    if(data != null && !data.get(1).equals("N/A")) {
                        Double xval = Double.valueOf(data.get(1));
                        Double yval = Double.valueOf(data.get(2));
                        if (Math.sqrt((x - xval) * (x - xval) + (y - yval) * (y - yval)) < rad) {
                            photoGallery.add(f.getPath());
                        }
                    }
                }
            }
        }
        return photoGallery;
    }

    public ArrayList<String> populateGallery(Date minDate, Date maxDate, String caption, double x, double y, double rad) {
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "/Android/data/com.example.comp7855_project/files/Pictures");
        photoGallery = new ArrayList<String>();
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
                    List<String> data = PictureDatabase.get(filename);
                    if(data != null && !data.get(1).equals("N/A")) {
                        Double xval = Double.valueOf(data.get(1));
                        Double yval = Double.valueOf(data.get(2));
                        if (Math.sqrt((x - xval)*(x - xval) + (y - yval)*(y - yval)) < rad)
                        {
                                String name = data.get(3);
                                if (name.equals(caption))
                                    photoGallery.add(f.getPath());
                            }
                    }
                }
            }
        }
        return photoGallery;
    }

    public void displayPhoto(String path) {
        try {
            File f = new File(path);
            String filename = f.getName();
            List<String> data = PictureDatabase.get(filename);
            if (data != null) {
                TextView Date_Num = (TextView) findViewById(R.id.lblDate_Num);
                Date_Num.setText(months[Integer.valueOf(data.get(0).substring(4,6))] + " " + data.get(0).substring(6,8) + ", " + data.get(0).substring(0,4));

                TextView Time_Num = (TextView) findViewById(R.id.lblTime_Num);
                if (Integer.valueOf(data.get(0).substring(9,11)) >= 13)
                    Time_Num.setText(String.valueOf(Integer.valueOf(data.get(0).substring(9,11)) - 12) + " : " + data.get(0).substring(11,13) + " pm");
                else if (Integer.valueOf(data.get(0).substring(9,11)) == 12)
                    Time_Num.setText(data.get(0).substring(9,11) + " : " + data.get(0).substring(11,13) + " pm");
                else
                    Time_Num.setText(data.get(0).substring(9,11) + " : " + data.get(0).substring(11,13) + " am");

                TextView Coord = (TextView) findViewById(R.id.lblCoord);
                Coord.setText(data.get(1) + ", " + data.get(2));

                EditText Tag = (EditText) findViewById(R.id.entryCaption);
                Tag.setText(data.get(3));
            }
        }
        catch(NullPointerException e) {
            Log.d("Garbage Language", "Cant handle empty data");
        }

        ImageView iv = (ImageView) findViewById(R.id.ivMain);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        iv.setImageBitmap(bitmap);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onClick( View v) {
        // update Tag
        try {
            if (photoGallery.size() != 0) {
                File f = new File(photoGallery.get(currentPhotoIndex));
                String filename = f.getName();
                List<String> data = PictureDatabase.get(filename);
                if (data != null) {
                    EditText Tag = (EditText) findViewById(R.id.entryCaption);
                    data.set(3, Tag.getText().toString());
                    PictureDatabase.put(filename, data);

                    Tag.clearFocus();

                    // save to file
                    try {
                        FileOutputStream fos = this.openFileOutput("PhotoData.ser", this.MODE_PRIVATE);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(PictureDatabase);
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        catch(NullPointerException e) {
            Log.d("Garbage Language", "Cant handle empty data");
        }

        client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Latitude = String.valueOf((int)location.getLatitude());
                    Longitude = String.valueOf((int)location.getLongitude());
                }
                else
                    Log.d("Location Was Null", Integer.toString(123));
            }
        });

        switch (v.getId()) {
            case R.id.btnLeft:
                --currentPhotoIndex;
                break;
            case R.id.btnRight:
                ++currentPhotoIndex;
                break;
            case R.id.btnCamera:
                takePicture();
                break;
            case R.id.btnShare:
                String extra_text=((EditText)findViewById(R.id.entryCaption)).getText().toString();
                Social_Share.share(extra_text,currentPhotoPath,this);
                break;
            default:
                break;
        }
        if (currentPhotoIndex >= photoGallery.size())
            currentPhotoIndex = photoGallery.size() - 1;
        if (currentPhotoIndex < 0)
            currentPhotoIndex = 0;

        if (photoGallery.size() != 0) {
            currentPhotoPath = photoGallery.get(currentPhotoIndex);
            Log.d("photoleft, size", Integer.toString(photoGallery.size()));
            Log.d("photoleft, index", Integer.toString(currentPhotoIndex));
            displayPhoto(currentPhotoPath);
        }
    }

    public void goToSettings(View v) {
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }

    public void goToDisplay(String x) {
        Intent i = new Intent(this, DisplayActivity.class);
        i.putExtra("DISPLAY_TEXT", x);
        startActivity(i);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.d("createImageFile", data.getStringExtra("STARTDATE"));
                Log.d("createImageFile", data.getStringExtra("ENDDATE"));
                Log.d("createImageFile", data.getStringExtra("TAG"));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                Date d1 = new Date(Long.MIN_VALUE);
                Date d2 = new Date(Long.MAX_VALUE);
                try{
                    d1 = dateFormat.parse(data.getStringExtra("STARTDATE"));
                }catch(ParseException ex){
                    // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
                }
                try{
                    d2 = dateFormat.parse(data.getStringExtra("ENDDATE"));
                }catch(ParseException ex){
                    // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
                }

                if (!data.getStringExtra("TAG").isEmpty() && !data.getStringExtra("RAD").isEmpty()) {
                    String Tag = data.getStringExtra("TAG");
                    Double x = Double.valueOf(data.getStringExtra("LOCX"));
                    Double y = Double.valueOf(data.getStringExtra("LOCY"));
                    Double rad = Double.valueOf(data.getStringExtra("RAD"));
                    photoGallery = populateGallery(d1, d2, Tag, x, y, rad);
                }
                else if (!data.getStringExtra("TAG").isEmpty() && data.getStringExtra("RAD").isEmpty()) {
                    String Tag = data.getStringExtra("TAG");
                    photoGallery = populateGallery(d1, d2, Tag);
                }
                else if (data.getStringExtra("TAG").isEmpty() && !data.getStringExtra("RAD").isEmpty()) {
                    Double x = Double.valueOf(data.getStringExtra("LOCX"));
                    Double y = Double.valueOf(data.getStringExtra("LOCY"));
                    Double rad = Double.valueOf(data.getStringExtra("RAD"));
                    photoGallery = populateGallery(d1, d2, x, y, rad);
                }
                else
                    photoGallery = populateGallery(d1, d2);

                Log.d("onCreate, size", Integer.toString(photoGallery.size()));
                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.activity_main), "No Results Found", Snackbar.LENGTH_SHORT);
                if (photoGallery.size() != 0) {
                    currentPhotoIndex = 0;
                    currentPhotoPath = photoGallery.get(currentPhotoIndex);
                    displayPhoto(currentPhotoPath);

                    mySnackbar = Snackbar.make(findViewById(R.id.activity_main), String.valueOf(photoGallery.size()) + " Result(s) Found", Snackbar.LENGTH_SHORT);
                }
                else
                {
                    photoGallery = populateGallery(minDate, maxDate);
                    currentPhotoIndex = 0;
                    currentPhotoPath = photoGallery.get(currentPhotoIndex);
                    displayPhoto(currentPhotoPath);
                }
                mySnackbar.show();

            }
        }
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.d("createImageFile", "Picture Taken");
                photoGallery = populateGallery(minDate, maxDate);
                currentPhotoIndex = photoGallery.size() - 1;
                currentPhotoPath = photoGallery.get(currentPhotoIndex);
                displayPhoto(currentPhotoPath);
            }
        }
    }

    public void takePicture() {
        EditText Tag = (EditText) findViewById(R.id.entryCaption);
        Tag.clearFocus();

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.d("FileCreation", "Failed");
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.comp7855_project.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }

    public File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", dir );
        currentPhotoPath = image.getAbsolutePath();
        Log.d("createImageFile", currentPhotoPath);

        //LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //Criteria criteria = new Criteria();
        //String bestProvider = locationManager.getBestProvider(criteria, false);
        //Location location = locationManager.getLastKnownLocation(bestProvider);

        List<String> data = new ArrayList<String>();
        data.add(timeStamp);
        data.add(Latitude);
        data.add(Longitude);
        data.add("CLICK TO EDIT");
        PictureDatabase.put(image.getName(), data);

        try
        {
            FileOutputStream fos = this.openFileOutput("PhotoData.ser", this.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(PictureDatabase);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

}

//        Tag.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//@Override
//public void onFocusChange(View v, boolean hasFocus) {
//        if (!hasFocus) {
//        try {
//        File f = new File(photoGallery.get(currentPhotoIndex));
//        String filename = f.getName();
//        List<String> data = PictureDatabase.get(filename);
//        if (data != null) {
//        EditText Tag = (EditText) findViewById(R.id.entryCaption);
//        data.set(3,Tag.getText().toString());
//        }
//        PictureDatabase.put(filename, data);
//        }
//        catch(NullPointerException e) {
//        Log.d("Garbage Language", "Cant handle empty data");
//        }
//        }
//        }
//        });


//Map<String, List<String>> hm = new HashMap<String, List<String>>();
//List<String> values = new ArrayList<String>();
//values.add("Value 1");
//values.add("Value 2");
//hm.put("Key1", values);
//
//// to get the arraylist
//System.out.println(hm.get("key1"));

//    public Map Load_Map() {
//        try
//        {
//            FileInputStream fileInputStream = new FileInputStream(this.getFilesDir()+"/PhotoData.ser");
//            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//            Map myHashMap = (Map)objectInputStream.readObject();
//
//            return myHashMap;
//        }
//        catch(ClassNotFoundException | IOException | ClassCastException e) {
//            e.printStackTrace();
//        }
//    }

//    public void Save_Map(Map<String, List<String>> PictureDatabase) {
//        try
//        {
//            FileOutputStream fos = this.openFileOutput("PhotoData.ser", this.MODE_PRIVATE);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(PictureDatabase);
//            oos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }