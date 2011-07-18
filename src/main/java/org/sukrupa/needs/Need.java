package org.sukrupa.needs;

public abstract class Need {

    public abstract String getItemName();

    public abstract double getCost();

    public abstract long getId();

    public abstract int getPriority();

    public abstract void setItemName(String itemName);

    public abstract void setCost(double cost);

    public abstract void setPriority(int priority);
}
