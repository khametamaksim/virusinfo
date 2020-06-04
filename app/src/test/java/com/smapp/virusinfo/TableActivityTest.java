package com.smapp.virusinfo;

import org.junit.Test;

import static org.junit.Assert.*;

public class TableActivityTest {
    @Test(timeout = 1000)
    public void pageStart() {
        TableActivity t = new TableActivity();
        assertNotNull(t);
    }

    @Test
    public void getTableTest(){
        TableActivity t = new TableActivity();
        assertNotNull(t);

        TableActivity.GetTable g = t.new GetTable();
        assertNotNull(g);

        g.doInBackground();

        assertEquals("1", g.webData.get(0));
    }
}