package org.sukrupa.smallneeds;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.smallNeeds.SmallNeed;
import org.sukrupa.smallNeeds.SmallNeedRepository;
import org.sukrupa.smallNeeds.SmallNeedsDonationController;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;



public class SmallNeedsDonationControllerTest {

    @Mock private SmallNeedRepository smallNeedRepository;
    @Mock private SmallNeed pencilSmallNeed, uniformSmallNeed;

    @Before
    public  void setup(){
        initMocks(this);
    }

    @Test
    public void shouldReturnTheSmallNeedList() throws JSONException{
        List<SmallNeed> smallNeedList = new ArrayList<SmallNeed>();
        smallNeedList.add(pencilSmallNeed);
        smallNeedList.add(uniformSmallNeed);

        when(smallNeedRepository.getList()).thenReturn(smallNeedList);
        when(pencilSmallNeed.getItemName()).thenReturn("Pencil");
        when(pencilSmallNeed.getCost()).thenReturn(200.0);
        when(uniformSmallNeed.getItemName()).thenReturn("Uniform");
        when(uniformSmallNeed.getCost()).thenReturn(250.0);


        JSONObject jsonSmallNeedDonationInfo = new JSONObject();
        List<String> smallNeeds =new ArrayList<String>();
        smallNeeds.add("Pencil");
        smallNeeds.add("200.0");
        smallNeeds.add("Uniform");
        smallNeeds.add("250.0");
        jsonSmallNeedDonationInfo.accumulate("smallNeedItems", smallNeeds);


        SmallNeedsDonationController smallNeedsDonationController = new SmallNeedsDonationController(smallNeedRepository);
        String smallListLocalOutput = jsonSmallNeedDonationInfo.toString();
        String smallList = smallNeedsDonationController.getSmallNeedList();
        System.out.println(smallList);
        assertThat(smallList, is(smallListLocalOutput));
    }
}

