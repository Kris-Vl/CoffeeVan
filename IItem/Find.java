package IItem;

import Exceptions.CloneItemException;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас пошуку по параметру
 */
public enum Find {
    BY_COST,
    BY_VOLUME;

    private final List<IItem> findItems = new ArrayList<>();

    public List<IItem> find(List<IItem> items, double startValue, double endValue){
        findItems.clear();
        if (this == BY_COST) {
            findByCost(items, startValue, endValue);
        }
        else if (this == BY_VOLUME) {
            findByVolume(items, startValue, endValue);
        }
        return findItems;
    }

    public void findByCost(List<IItem> items, double startCost, double endCost) {
        for (IItem item : items) {
            if (startCost <= item.getCost() && endCost >= item.getCost()) {
                try {
                    findItems.add(item.getClone());
                } catch (CloneItemException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void findByVolume(List<IItem> items, double startVolume, double endVolume) {
        for (IItem item : items) {
            if (startVolume <= item.getVolume() && endVolume >= item.getVolume()) {
                try {
                    findItems.add(item.getClone());
                } catch (CloneItemException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public String toString() {
        return name().substring(3).toLowerCase();
    }
}
