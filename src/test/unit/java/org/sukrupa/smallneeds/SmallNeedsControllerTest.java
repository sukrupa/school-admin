package org.sukrupa.smallneeds;


import org.junit.Test;
import org.sukrupa.app.needs.SmallNeedsController;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SmallNeedsControllerTest {

     private HashMap<String, Object> studentModel = new HashMap<String, Object>();
    public SmallNeedsController controller ;

    private HashMap<String,Object> model= new HashMap<String, Object>();

    @Test
    public void shouldDisplaySmallNeedsPage(){
      controller= new SmallNeedsController();
      assertEquals(controller.list(), "smallNeeds/list");
    }
}
