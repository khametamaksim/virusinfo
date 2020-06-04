package com.smapp.virusinfo;

import android.view.View;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculationActivityTest {

    @Test(timeout = 1000)
    public void pageStart(){
        CalculationActivity c = new CalculationActivity();
        assertNotNull(c);
    }

    @Test
    public void getCodeTest() {
        CalculationActivity ca = new CalculationActivity();
        assertNotNull(ca);

        CalculationActivity.User user = ca.new User();
        assertNotNull(user);

        String actual = user.getCode("Poland");
        String required = "PL";

        assertEquals(required, actual);
    }

    @Test
    public void countryTest() {
        CalculationActivity ca = new CalculationActivity();
        assertNotNull(ca);

        CalculationActivity.User user = ca.new User();
        assertNotNull(user);
        user.doInBackground();

        String actual = ca.country;
        String required = "Belarus";

        assertEquals(required, actual);
    }
}