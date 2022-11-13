package Storage;

import IItem.Find;
import IItem.IItem;
import IItem.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Описание Фургона
 */
public class Van implements IStorage {
    private int totalVolume;
    private int usedVolume;
    private final List<IItem> items;

    private boolean isStorageHasFreeVolume(double volume) {
        return usedVolume + volume <= this.totalVolume;
    }

    public Van(int totalVolume) {
        this.totalVolume = totalVolume;
        items = new ArrayList<>();
    }

    public boolean add(IItem item) {
        if (isStorageHasFreeVolume(item.getVolume())) {
            items.add(item);
            usedVolume += item.getVolume();
            return true;
        }
        else {
            return false;
        }
    }

    public void upVolume(int volume) {
        this.totalVolume = this.totalVolume + volume;
    }


    public String getState() {
        return "Використаний об'єм: " + this.usedVolume + " з: " + this.totalVolume;
    }

    public void sort(Sort sortBy) {
        sortBy.sort(this.items);
    }

    public List<IItem> find(Find findBy, int startValue, int endValue){
        return findBy.find(this.items, startValue, endValue);
    }

    public String toString() {
        StringBuilder result = new StringBuilder("\nРезультат:\n");
        for (IItem item : this.items) {
            result.append(item.toString()).append("\n");
        }
        return result.toString();
    }

}
