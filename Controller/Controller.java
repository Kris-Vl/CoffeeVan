package Controller;

import Exceptions.*;
import IItem.Coffe.*;
import IItem.*;
import Storage.*;
import Email.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    public final IStorage storage;
    public final CoffeFabric coffeeFabric;
    private final List<IItem> find = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger();
    private static final Email email = new Email(new EmailService());

    /**
     *Вказуємо об'єм та суму для створення фургону
     */
    public Controller(int money, int volume) {
        if (money <= 0 || volume <= 0) {
            logger.error("IllegalArgumentException(Ви ввели некоректний аргумент!)");
            email.Run();
            throw new IllegalArgumentException("Ви ввели некоректний аргумент!");
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
        return "Використана сума: " + this.usedMoney + " з: " + this.totalMoney;
    }

    private String findToString(List<IItem> items, Find findBy, int startValue, int endValue) {
        StringBuilder result = new StringBuilder("\nРезультат після фільтрації в діапазоні ");
        result.append(findBy).append(" [").append(startValue).append(" - ").append(endValue).append("]\n");
        double money = 0;
        int volume = 0;
        for (IItem item : items) {
            result.append(item.toString()).append("\n");
            money += item.getCost();
            volume += item.getVolume();
        }
        result.append("\nВикористаний об'єм: ").append(volume).append("\nВикористана сума: ").append(money).append("\n");
        return result.toString();
    }

    public List<Coffee> find(Find findBy, int startValue, int endValue) throws CloneItemException {
        find.clear();
        find.addAll(storage.find(findBy, startValue, endValue));
        if (!find.isEmpty())
            System.out.println(findToString(find, findBy, startValue, endValue));
        return null;
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
        Finds(Finding, startValues, endValues);
    }

    public List<Coffee> Finds(int Finding, int startValues, int endValues){
        if(Finding == 1) {
            try {
                find(Find.BY_VOLUME, startValues, endValues);
            } catch (CloneItemException e) {
                throw new RuntimeException(e);
            }
        }
        if(Finding == 0) {
            try {
                find(Find.BY_COST, startValues, endValues);
            } catch (CloneItemException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void fillStorage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Бажаєте заповнити фургон вручну(1) чи оберете автоматичне заповнення(0)?:");
        int Fill = scanner.nextInt();
        while (Fill != 1 && Fill != 0) {
            System.out.println("Введіть коректні значення!");
            Fill = scanner.nextInt();
        }
        fillStorages(Fill);
    }

    public void fillStorages(int Fill){
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
                IItem item;
                item = coffeeFabric.getCoffee(IntroduceCoffee(), IntroduceCoffeeCondition(), IntroduceCoffeeWrap());
                if (!hasFreeMoney(item.getCost()) || !storage.add(item)) {
                    System.out.println("Останній продукт не вдалося додати у замовлення!");
                    break;
                }
                usedMoney += item.getCost();
            }
        }
    }

    public int IntroduceCoffee(){
        System.out.println("Введіть Номер сорта потрібної вам кави: \n1 - Арабіка \n2 - Робуста \n3 - Ліберіка");
        int N = scanner.nextInt();
        while (N != 1 && N != 2 && N != 3) {
            System.out.println("Такого сорту кави не існує! Введіть коректні значення");
            N = scanner.nextInt();
        }
        return N;
    }

    private int IntroduceCoffeeCondition(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть стан потрібної вам кави: \n1 - Розчинна \n2 - Мелена \n3 - Зернова");
        int N = scanner.nextInt();
        while (N != 1 && N != 2 && N != 3) {
            System.out.println("Такого стану кави не існує! Введіть коректні значення");
            N = scanner.nextInt();
        }
        return N;
    }

    private int IntroduceCoffeeWrap(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть потрібне вам пакування: \n1 - Скляне \n2 - Пластикове");
        int N = scanner.nextInt();
        while (N != 1 && N != 2) {
            System.out.println("Такого пакування кави не існує! Введіть коректні значення");
            N = scanner.nextInt();
        }
        return N;
    }

    public void outputStorage() {
        System.out.println(storage);
        System.out.println(storage.getState());
        System.out.println(this.getState());
    }

    public void sort() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть 0, якщо хочете відсортувати замовлення по ціні і 1, якщо по об'єму:");
        int sort = scanner.nextInt();
        while (sort != 1 && sort != 0) {
            System.out.println("Введіть коректні значення!");
            sort = scanner.nextInt();
        }
        sorts(sort);
    }
    public void sorts(int sort){
        if(sort == 1)
            storage.sort(Sort.BY_VOLUME);
        else
            storage.sort(Sort.BY_COST);
        outputStorage();
    }
    public void upVolumeOfStorage() {
        System.out.println("Введіть об'єм, на який хочете збільшити об'єм фургона:");
        int volume = scanner.nextInt();
        upVolumeOfStorages(volume);
    }
    public void upVolumeOfStorages(int volume) {
        if (volume > 0) storage.upVolume(volume);
        else {
            logger.error("IllegalArgumentException(Ви ввели некоректний аргумент!)");
            email.Run();
            throw new IllegalArgumentException("Ви ввели некоректний аргумент!");
        }
    }
}
