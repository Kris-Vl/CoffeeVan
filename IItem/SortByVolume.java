package IItem;

import java.util.Comparator;

/**
 * Сортування по об'єму
 */
public class SortByVolume implements Comparator<IItem> {
    public int compare(IItem o1, IItem o2) {
        return Integer.compare(o1.getVolume(), o2.getVolume());
    }
}
