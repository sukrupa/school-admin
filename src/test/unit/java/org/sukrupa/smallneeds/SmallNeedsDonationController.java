package org.sukrupa.smallneeds;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sukrupa.smallNeeds.SmallNeed;
import org.sukrupa.smallNeeds.SmallNeedRepository;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/getSmallNeedListForDonation")
public class SmallNeedsDonationController {
    private SmallNeedRepository smallNeedRepository;

    @Autowired
    public SmallNeedsDonationController(SmallNeedRepository smallNeedRepository) {
        this.smallNeedRepository =smallNeedRepository;
    }

    @RequestMapping(method = GET)
    @ResponseBody
    public String getSmallNeedList(){
        JSONObject jsonSmallNeedDonationInfo=new JSONObject();
        HashMap<String,Double> smallNeeds =new HashMap<String, Double>();
        List<SmallNeed> smallNeedList = smallNeedRepository.getList();
        for(int i=0;i<smallNeedList.size();i++) {
            smallNeeds.put(smallNeedList.get(i).getItemName(), smallNeedList.get(i).getCost());
        }

        jsonSmallNeedDonationInfo.accumulate("smallNeedItems", smallNeeds);
        return jsonSmallNeedDonationInfo.toString();
    }


}
