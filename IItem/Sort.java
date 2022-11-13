package IItem;

import java.util.List;

/**
 * Клас сортуввання
 */
public enum Sort {
    BY_COST,
    BY_VOLUME;

    public void sort(List<IItem> items) {
        if (this == BY_COST)
            items.sort(new SortByCost());
        else if (this == BY_VOLUME)
            items.sort(new SortByVolume());
    }
}
