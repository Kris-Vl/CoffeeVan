package Storage;

import IItem.Find;
import IItem.IItem;
import IItem.Sort;
import Exceptions.CloneItemException;

import java.util.List;

/**
 * Інтерфейс для сховищ
 */
public interface IStorage {
    /**
     * Додавання продукту в сховище
     * @param item продукт
     * @return повертає true, якщо додавання пройшло успішно, і false, якщо ні
     */
    boolean add(IItem item);

    /**
     * Збідьшення параметру сховища
     * @param totalVolume параметр, на величину якого збільшується об'єм
     */
    void upVolume(int totalVolume);

    /**
     * Получить состояние хранилища в виде строки
     * @return возвращает строку которая содержит информацию о состоянии хранилища
     */
    String getState();

    /**
     * Сортування предмета по параметру
     * @param sortBy параметр. по якому буде проводитися сортування
     */
    void sort(Sort sortBy);

    /**
     * Повертає колекцію клонованих елементів по заданому параметру
     * @param findBy параметр, по якому здійснювався пошук
     * @param startValue початкове значення діапазону
     * @param endValue кінцеве значення діапазону
     * @return повертає колекцію клованих елементів
     * @throws CloneItemException виключення при клонуванні
     */
    List<IItem> find(Find findBy, int startValue, int endValue) throws CloneItemException;
}
