package org.sukrupa.bigneeds;

import org.sukrupa.platform.RequiredByFramework;

public class BigNeedFormData {
    private String itemName;
    private String costString;

    @RequiredByFramework
    public BigNeedFormData(){
        
    }

    public BigNeedFormData(String itemName, String costString) {
        this.itemName = itemName;
        this.costString = costString;
    }

    public BigNeed createBigNeed() {
        return new BigNeed(itemName, Integer.parseInt(costString));
    }

    public void setItemName(String itemName){

        this.itemName = itemName;
    }

    public void setCostString(String costString){

        this.costString = costString;
    }
}
