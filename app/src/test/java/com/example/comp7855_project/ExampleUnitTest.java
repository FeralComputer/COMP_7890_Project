package com.example.comp7855_project;

import com.example.comp7855_project.Utils.Search_Functions;

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
        Date date_now = new Date();
        Date date_future = new GregorianCalendar(2020, Calendar.FEBRUARY,20).getTime();
        System.out.println(date_now.toString());
        System.out.println(date_future.toString());
        ArrayList<String> photoGallery;
        //photoGallery = Search_Functions.populateGallery(this, date_now, date_future);

    }

}