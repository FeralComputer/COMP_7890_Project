package com.example.comp7855_project;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.comp7855_project.Utils.Search_Functions;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.comp7855_project", appContext.getPackageName());

        Date date_now = new Date();
        Date date_future = new GregorianCalendar(2020, Calendar.FEBRUARY,20).getTime();
        System.out.println(date_now.toString());
        System.out.println(date_future.toString());
        Search_Functions.populateGallery(appContext.MainActivity, date_now, date_future);

    }
}
