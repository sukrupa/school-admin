package org.sukrupa.app.needs;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.bigneeds.BigNeedRepository;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class BigNeedsControllerTest {

    @Mock
    private BigNeedRepository bigNeedRepository;

    private BigNeedsController controller;
    private HashMap<String, Object> model = new HashMap<String, Object>();

    @Before
    public void setUp(){
        initMocks(this);
        controller = new BigNeedsController(bigNeedRepository);
    }
    @Test
    public  void shouldDisplayBigNeedsPage(){
        assertThat(controller.list(model), is("bigneeds/list"));
    }


}
