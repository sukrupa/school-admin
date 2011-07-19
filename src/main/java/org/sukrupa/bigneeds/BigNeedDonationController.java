package org.sukrupa.bigneeds;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/getHighPriorityBigNeedItem")
public class BigNeedDonationController {

    BigNeedRepository bigNeedRepository;

    @Autowired
    public BigNeedDonationController(BigNeedRepository bigNeedRepository){
        this.bigNeedRepository=bigNeedRepository;
    }

    @RequestMapping(method = GET)
    @ResponseBody
    public String getHighPriorityItemName() {
        JSONObject jsonBigNeedDonationInfo=new JSONObject();
        jsonBigNeedDonationInfo.accumulate("highPriorityBigNeedItem",bigNeedRepository.getList().get(0).getItemName());
        return jsonBigNeedDonationInfo.toString();
    }

    @RequestMapping(value = "totalCost", method = GET)
    @ResponseBody
    public String getBigNeedItemTotalCost() {

       JSONObject jsonBigNeedDonationInfo=new JSONObject();
       jsonBigNeedDonationInfo.accumulate("bigNeedItemTotalCost",bigNeedRepository.getList().get(0).getCost());
       return jsonBigNeedDonationInfo.toString();

    }

    @RequestMapping(value = "amountDonated", method = GET)
    @ResponseBody
    public String getBigNeedItemAmountDonated() {

       JSONObject jsonBigNeedDonationInfo=new JSONObject();
       jsonBigNeedDonationInfo.accumulate("bigNeedItemAmountDonated",bigNeedRepository.getList().get(0).getDonatedAmount());
       return jsonBigNeedDonationInfo.toString();

    }
}
