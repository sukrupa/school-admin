package org.sukrupa.bigneeds;

import org.apache.jasper.tagplugins.jstl.When;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.app.needs.BigNeedsController;
import org.sukrupa.bigneeds.BigNeed;
import org.sukrupa.bigneeds.BigNeedRepository;
import org.sukrupa.platform.config.SpringContextLoaderForTesting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class BigNeedsControllerTest {

    private BigNeedsController controller;

    private HashMap<String, Object> model = new HashMap<String, Object>();

    @Mock
    private BigNeedRepository bigNeedRepository;

    @Before
    public void setUp(){
        initMocks(this);
        controller = new BigNeedsController(bigNeedRepository);
    }

    @Test
    public  void shouldDisplayBigNeedsPage(){
        assertThat(controller.list(model), is("bigneeds/list"));
    }

    @Test
    public void shouldRetrieveBigNeedListToModel(){
        BigNeed schoolBusBigNeed = new BigNeed("School Bus", 200000);
        BigNeed waterPurifierBigNeed = new BigNeed("Water Purifier", 5000);
        List<BigNeed> ourList = new ArrayList<BigNeed>() ;
        ourList.add(schoolBusBigNeed);
        ourList.add(waterPurifierBigNeed);
        when(bigNeedRepository.getList()).thenReturn(ourList);
        controller.list(model);
        List<BigNeed> bigNeedList = (List<BigNeed>) model.get("bigNeedList");
        assertThat(bigNeedList.contains(schoolBusBigNeed), is(true));
        assertThat(bigNeedList.contains(waterPurifierBigNeed), is(true));
    }

    @Test
    public void shouldCreateABigNeed(){
        BigNeedFormData sampleBigNeed = new BigNeedFormData("Sample", "60000");
        controller.create(sampleBigNeed, model);
        assertThat((String)model.get("message"),is("Added Successfully"));
    }

}
