package Menu;
import Command.Receiver;
import Controller.Controller;

import java.util.Scanner;
public class Menu {

    public void showMenu(){
        String value;
        System.out.print("--------------------------Фургон з кавою--------------------------\n");
        Controller van = new Controller();
        Receiver receiver = new Receiver(van);
        Scanner scanner = new Scanner(System.in);
        do {
            menu();
            value = scanner.nextLine();
            switch (value) {
                case "1" -> receiver.run(0);
                case "2" -> receiver.run(1);
                case "3" -> receiver.run(2);
                case "4" -> receiver.run(3);
                case "5" -> receiver.run(4);
                case "6" -> receiver.run(5);
            }

        } while (true);
    }

    static void menu(){
        System.out.println("\n1. Запонити фургон кавою на певну суму");
        System.out.println("2. Відсортувати замовлення");
        System.out.println("3. Відфільтрувати замовлення");
        System.out.println("4. Збільшити об'єм фургона");
        System.out.println("5. Вивести інформацію про замовлення");
        System.out.println("6. Завершити програму");
        System.out.print("Ваш вибір: ");
    }
}
