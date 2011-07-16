package org.sukrupa.bigneeds;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.bigneeds.BigNeed;
import org.sukrupa.bigneeds.BigNeedRepository;

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
    @Mock private BigNeed bigNeed;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldReturnTheHighPriorityBigNeedItemAsPowerGenerator() throws JSONException {
        when(bigNeed.getItemName()).thenReturn("Power Generator");
        when(bigNeedRepository.getBigNeed(1)).thenReturn(bigNeed);
        JSONObject jsonBigNeedDonationInfo = new JSONObject();
        jsonBigNeedDonationInfo.accumulate("highPriorityBigNeedItem","Power Generator");
        BigNeedDonationController bigNeedDonationController = new BigNeedDonationController(bigNeedRepository);
        String highPriorityItemName=bigNeedDonationController.getHighPriorityItemName();
        assertThat(highPriorityItemName,is(jsonBigNeedDonationInfo.toString()));




    }


}
