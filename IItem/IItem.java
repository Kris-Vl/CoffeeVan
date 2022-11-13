package IItem;

import Exceptions.CloneItemException;

public interface IItem {

    double getCost();

    int getVolume();

    IItem getClone() throws CloneItemException;
}
