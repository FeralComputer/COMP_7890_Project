package com.example.comp7855_project.Utils;

public class Util_functions {


    public static boolean check_distance(double x_var, double x, double y_var, double y, double RAD)
    {
        if((Math.sqrt((x - x_var) * (x - x_var) + (y - y_var) * (y - y_var)) < RAD))
        {
            return true;
        }
        else{
            return false;
        }

    }

}
