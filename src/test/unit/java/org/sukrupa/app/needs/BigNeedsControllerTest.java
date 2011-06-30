package org.sukrupa.app.needs;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

public class BigNeedsControllerTest {

    private BigNeedsController controller;
      private HashMap<String, Object> model = new HashMap<String, Object>();
    @Before
    public void setUp(){
        controller = new BigNeedsController();
    }
    @Test
    public  void shouldDisplayBigNeedsPage(){
        assertThat(controller.list(model), is("bigneeds/list"));
    }

    @Test
       public  void shouldCheckListIsNotEmpty(){
        controller.list(model);
        assertFalse(model.isEmpty());
       }

}
