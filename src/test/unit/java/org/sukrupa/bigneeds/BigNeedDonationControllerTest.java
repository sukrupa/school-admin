package org.sukrupa.bigneeds;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.bigneeds.BigNeed;
import org.sukrupa.bigneeds.BigNeedRepository;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by IntelliJ IDEA.
 * User: sreerajan
 * Date: 15/7/11
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class BigNeedDonationControllerTest {

    @Mock private BigNeedRepository bigNeedRepository;
    @Mock private List<BigNeed> bigNeeds;
    @Mock private BigNeed bigNeed;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldReturnTheHighPriorityBigNeedItemAsPowerGenerator() throws JSONException {
        when(bigNeedRepository.getList()).thenReturn(bigNeeds);
        when(bigNeeds.get(0)).thenReturn(bigNeed);
        when(bigNeed.getItemName()).thenReturn("Power Generator");
        JSONObject jsonBigNeedDonationInfo = new JSONObject();
        jsonBigNeedDonationInfo.accumulate("highPriorityBigNeedItem","Power Generator");
        BigNeedDonationController bigNeedDonationController = new BigNeedDonationController(bigNeedRepository);
        String highPriorityItemName=bigNeedDonationController.getHighPriorityItemName();
        assertThat(highPriorityItemName,is(jsonBigNeedDonationInfo.toString()));

    }

    @Test
    public void shouldReturnTheTotalCostForBigNeedItemAs50000() throws JSONException{
        when(bigNeedRepository.getList()).thenReturn(bigNeeds);
        when(bigNeeds.get(0)).thenReturn(bigNeed);
        when(bigNeed.getCost()).thenReturn(50000.0);
        JSONObject jsonBigNeedDonationInfo = new JSONObject();
        jsonBigNeedDonationInfo.accumulate("bigNeedItemTotalCost",50000.0);
        BigNeedDonationController bigNeedDonationController = new BigNeedDonationController(bigNeedRepository);
        String bigNeedItemTotalCost=bigNeedDonationController.getBigNeedItemTotalCost();
        assertThat(bigNeedItemTotalCost,is(jsonBigNeedDonationInfo.toString()));



    }

    @Test
    public void shouldReturnTheAmountDonatedtoBigNeedItemAs20000() throws JSONException{
        when(bigNeedRepository.getList()).thenReturn(bigNeeds);
        when(bigNeeds.get(0)).thenReturn(bigNeed);
        when(bigNeed.getDonatedAmount()).thenReturn(20000.0);
        JSONObject jsonBigNeedDonationInfo = new JSONObject();
        jsonBigNeedDonationInfo.accumulate("bigNeedItemAmountDonated",20000.0);
        BigNeedDonationController bigNeedDonationController = new BigNeedDonationController(bigNeedRepository);
        String bigNeedItemAmountDonated=bigNeedDonationController.getBigNeedItemAmountDonated();
        assertThat(bigNeedItemAmountDonated,is(jsonBigNeedDonationInfo.toString()));



    }


}
