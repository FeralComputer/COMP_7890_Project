package com.example.comp7855_project;

import com.example.comp7855_project.Utils.Search_Functions;
import com.example.comp7855_project.Utils.Util_functions;
import com.google.android.gms.common.internal.Asserts;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class ExampleUnitTest {



    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void search_isCorrect()
    {
        double xval = 1;
        double x = 1;

        double yval = 1;
        double y = 1;
        double rad = 1;
        assertEquals(true,Util_functions.check_distance(xval,x,yval,y,rad));

         xval = 10;
         x = 15;

         yval = 10;
         y = 15;
         rad = 25;
        assertEquals(true,Util_functions.check_distance(xval,x,yval,y,rad));

         xval = 8;
         x = 0;

         yval = 10;
         y = 0;
         rad = 5;
        assertEquals(false,Util_functions.check_distance(xval,x,yval,y,rad));
    }

}