package org.sukrupa.bigneeds;

import net.sf.json.JSONObject;

/**
 * Created by IntelliJ IDEA.
 * User: sreerajan
 * Date: 15/7/11
 * Time: 6:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class BigNeedDonationController {

    BigNeedRepository bigNeedRepository;
    public BigNeedDonationController(BigNeedRepository bigNeedRepository){
        this.bigNeedRepository=bigNeedRepository;
    }


    public String getHighPriorityItemName() {
        JSONObject jsonBigNeedDonationInfo=new JSONObject();
        jsonBigNeedDonationInfo.accumulate("highPriorityBigNeedItem",bigNeedRepository.getBigNeed(1).getItemName());
        return jsonBigNeedDonationInfo.toString();
    }
}
