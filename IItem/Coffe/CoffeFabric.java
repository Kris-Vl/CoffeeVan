package IItem.Coffe;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Генерація кави
 */
public class CoffeFabric {
    private final List<Class<? extends Coffee>> coffeClasses;
    private static final Random random = new Random();
    public CoffeFabric(List<Class<? extends Coffee>> coffeClasses) {
        this.coffeClasses = coffeClasses;
    }
    
    public Coffee getRandomCoffee() {
        Class<? extends Coffee> cCoffee = coffeClasses.get(random.nextInt(coffeClasses.size()));
        Coffee result;
        CoffeCondition coffeCondition = Enums.random(CoffeCondition.class);
        CoffeWrap coffeWrap = Enums.random(CoffeWrap.class);
        if (cCoffee.equals(Arabica.class))
            result = new Arabica(coffeCondition, coffeWrap);
        else if (cCoffee.equals(Robusta.class))
            result = new Robusta(coffeCondition, coffeWrap);
        else
            result = new Liberica(coffeCondition, coffeWrap);
        return result;
    }
    
    public Coffee getCoffee() {
        Scanner scanner = new Scanner(System.in);
        int N;
        Coffee result;
        System.out.println("Введіть Номер сорта потрібної вам кави: \n1 - Арабіка \n2 - Робуста \n3 - Ліберіка");
        N = scanner.nextInt();
        while (N != 1 && N != 2 && N != 3) {
            System.out.println("Такого сорту кави не існує! Введіть коректні значення");
            N = scanner.nextInt();
        }
        CoffeCondition coffeCondition = getCoffeCondition();
        CoffeWrap coffeWrap = getCoffeWrap();
        result = switch (N) {
            case 1 -> new Arabica(coffeCondition, coffeWrap);
            case 2 -> new Robusta(coffeCondition, coffeWrap);
            default -> new Liberica(coffeCondition, coffeWrap);
        };
        return result;
    }
    
    public CoffeCondition getCoffeCondition() {
        Scanner scanner = new Scanner(System.in);
        int N;
        CoffeCondition result;
        System.out.println("Введіть стан потрібної вам кави: \n1 - Розчинна \n2 - Мелена \n3 - Зернова");
        N = scanner.nextInt();
        while (N != 1 && N != 2 && N != 3) {
            System.out.println("Такого стану кави не існує! Введіть коректні значення");
            N = scanner.nextInt();
        }
        result = switch (N) {
            case 1 -> CoffeCondition.РОЗЧИННА;
            case 2 -> CoffeCondition.МЕЛЕНА;
            default -> CoffeCondition.ЗЕРНОВА;
        };
        return result;
    }
    
    public CoffeWrap getCoffeWrap() {
        Scanner scanner = new Scanner(System.in);
        int N;
        CoffeWrap result;
        System.out.println("Введіть потрібне вам пакування: \n1 - Скляне \n2 - Пластикове");
        N = scanner.nextInt();
        while (N != 1 && N != 2) {
            System.out.println("Такого пакування кави не існує! Введіть коректні значення");
            N = scanner.nextInt();
        }
        if (N == 1) {
            result = CoffeWrap.СКЛЯНЕ;
        } else {
            result = CoffeWrap.ПЛАСТИКОВЕ;
        }
        return result;
    }

    public static class Enums {
        public static <T extends Enum<T>> T random(Class<T> ec) {
            return random(ec.getEnumConstants());
        }
        public static <T> T random(T[] values) {
            return values[random.nextInt(values.length)];
        }
    }
}
