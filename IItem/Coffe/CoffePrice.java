package IItem.Coffe;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Ціни на каву, підгружаються з файлу
 */
public class CoffePrice {
    private final static String SOURCE_PATH = "price.txt";
    private static Map<Class<? extends Coffee>, Double> price;
    private final List<Class <? extends Coffee>> coffeeClasses;



    public CoffePrice(List<Class<? extends Coffee>> coffeeClasses) {
        price = new HashMap<>();
        this.coffeeClasses = coffeeClasses;
        getPriceFromFile();
    }

    /**
     * Ціни на каву загружаються з файлу, якщо в процесі виникає виключення, то використовуються дані за замовчуванням
     */
    private void getPriceFromFile() {
        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(SOURCE_PATH);
            properties.load(fis);
            for (Class<? extends Coffee> cClass : coffeeClasses) {
                price.put(cClass, Double.valueOf(properties.getProperty(cClass.getSimpleName())));
            }
        } catch (IOException e) {
            getDefaultPrice();
        }
    }

    private void getDefaultPrice() {
        for (int i = 0; i < coffeeClasses.size(); i++) {
            price.put(coffeeClasses.get(i), (double) (i + 2));
        }
    }

    public static double getPrice(Class<? extends Coffee> coffeeClass) {
        return price.get(coffeeClass);
    }
}
