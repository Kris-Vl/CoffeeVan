package Controller;

import IItem.Find;
import IItem.IItem;
import IItem.Sort;
import IItem.Coffe.*;
import Exceptions.*;
import Storage.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Конроллер програми
 */
public class Controller {
    Scanner scanner = new Scanner(System.in);
    private final double totalMoney;
    private double usedMoney;
    private final IStorage storage;
    private final CoffeFabric coffeeFabric;
    private final List<IItem> find = new ArrayList<>();

    /**
     * Вказуємо об'єм та суму для створення фургону
     */
    public Controller() {
        System.out.print("Введіть суму, на яку хочете заповнити фургон: ");
        int money = scanner.nextInt();
        System.out.print("Введіть об'єм фургона: ");
        int volume = scanner.nextInt();
        if (money <= 0 || volume <= 0) {
            try {
                throw new InputValuesZeroOrNegativeException ("гроші або ж об'єм не можуть бути нульовими або від'ємними");
            } catch (InputValuesZeroOrNegativeException e) {
                throw new RuntimeException(e);
            }
        } else {
            this.totalMoney = money;
            this.storage = new Van(volume);
            List<Class<? extends Coffee>> listOfCoffee = Arrays.asList(Arabica.class, Robusta.class, Liberica.class);
            new CoffePrice(listOfCoffee);
            this.coffeeFabric = new CoffeFabric(listOfCoffee);
        }
    }

    /**
     * Перевірка чи є на даний момент достатьо грашей для того, щоб додати новий товар певної ціни
     * @param cost ціна товару, який добавляється
     * @return true, якщо сума поточного товару та товарів, що були загружені в фургон раніше менша за максимальну
     */
    private boolean hasFreeMoney(double cost) {
        return this.usedMoney + cost <= this.totalMoney;
    }

    private String getState() {
        return "Вмкористана сума: " + this.usedMoney + " з: " + this.totalMoney;
    }

    /**
     * Метод, який повертає інформацію про найдені по певному параметру предмети
     * @param findBy параметр, по якому здійснювався пошук
     * @param startValue початкове значення діапазону
     * @param endValue кінцеве значення діапазону
     * @return відфільтровані дані
     */
    private String findToString(List<IItem> items, Find findBy, int startValue, int endValue) {
        StringBuilder result = new StringBuilder("\nРезультат пісял фільтрації в діапазоні ");
        result.append(findBy).append(" [").append(startValue).append(" - ").append(endValue).append("]\n");
        double sum = 0;
        int volume = 0;
        for (IItem item : items) {
            result.append(item.toString()).append("\n");
            sum += item.getCost();
            volume += item.getVolume();
        }
        result.append("\nВикористаний об'єм: ").append(volume).append("\nВикористана сума: ").append(sum).append("\n");
        return result.toString();
    }

    /**
     * Метод виклику пошуку продуктів по заданому параметру та отримання клонів найдених продуктів в окрему колекцію
     * @param findBy параметр, по якому здійснювався пошук
     * @param startValue початкове значення діапазону
     * @param endValue кінцеве значення діапазону
     * @throws CloneItemException виключення під час клонування продукту
     */
    public void find(Find findBy, int startValue, int endValue) throws CloneItemException {
        find.clear();
        find.addAll(storage.find(findBy, startValue, endValue));
        if (!find.isEmpty()) {
            System.out.println(findToString(find, findBy, startValue, endValue));
        }
    }
    public void Find(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть 0, якщо хочете відфільтрувати замовлення по ціні і 1, якщо по об'єму:");
        int Finding = scanner.nextInt();
        while (Finding != 1 && Finding != 0) {
            System.out.println("Введіть коректні значення!");
            Finding = scanner.nextInt();
        }
        System.out.print("Введіть діапазон значень\n");
        System.out.print("Від: ");
        int startValues = scanner.nextInt();
        System.out.print("До:");
        int endValues = scanner.nextInt();
        if(Finding == 1) {
            try {
                find(Find.BY_VOLUME, startValues, endValues);
            } catch (CloneItemException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                find(Find.BY_COST, startValues, endValues);
            } catch (CloneItemException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * Заповнення сховища
     */
    public void fillStorage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Бажаєте заповнити фургон вручну(1) чи оберете автоматичне заповнення(0)?:");
        int Fill = scanner.nextInt();
        while (Fill != 1 && Fill != 0) {
            System.out.println("Введіть коректні значення!");
            Fill = scanner.nextInt();
        }
        if(Fill == 0) {
            while (true) {
                IItem item = coffeeFabric.getRandomCoffee();
                if (!hasFreeMoney(item.getCost()) || !storage.add(item))
                    break;
                usedMoney += item.getCost();
            }
        }
        else {
            while (true) {
                IItem item = coffeeFabric.getCoffee();
                if (!hasFreeMoney(item.getCost()) || !storage.add(item)) {
                    System.out.println("Останній продукт не вдалося додати у замовлення!");
                    break;
                }
                usedMoney += item.getCost();
            }
        }

    }

    /**
     * Вивід інформації
     */
    public void outputStorage() {
        System.out.println(storage);
        System.out.println(storage.getState());
        System.out.println(this.getState());
    }

    /**
     * Виклик сортування
     */
    public void sort() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть 0, якщо хочете відсортувати замовлення по ціні і 1, якщо по об'єму:");
        int sort = scanner.nextInt();
        while (sort != 1 && sort != 0) {
            System.out.println("Введіть коректні значення!");
            sort = scanner.nextInt();
        }
        if(sort == 1)
            storage.sort(Sort.BY_VOLUME);
        else
            storage.sort(Sort.BY_COST);
        outputStorage();
    }

    public void upVolumeOfStorage() {
        System.out.print("Введіть об'єм, на який хочете збільшити об'єм фургона:");
        int volume = scanner.nextInt();
        if (volume > 0)
            storage.upVolume(volume);
        else
            System.out.println("Volume cant be zero or negative");
    }
}
