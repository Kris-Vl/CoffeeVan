package IItem;

import java.util.Comparator;

/**
 * Сортування по ціні
 */
public class SortByCost implements Comparator<IItem> {
    public int compare(IItem o1, IItem o2) {
        return Double.compare(o1.getCost(), o2.getCost());
    }
}
