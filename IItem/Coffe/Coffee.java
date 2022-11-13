package IItem.Coffe;

import Exceptions.CloneItemException;
import IItem.IItem;

/**
 * Абстрактний клас, предок різних сортів кави
 */
public abstract class Coffee implements IItem, Cloneable {
    private final String name;
    private final double cost;
    private final int volume;
    private final int volumeOfCoffee;
    private final CoffeCondition coffeCondition;
    protected CoffeWrap coffeWrap;

    public Coffee(String name, CoffeCondition coffeCondition, CoffeWrap coffeWrap) {
        this.name = name;
        this.coffeCondition = coffeCondition;
        this.coffeWrap = coffeWrap;
        this.cost = CoffePrice.getPrice(this.getClass()) + coffeWrap.getWrapPrice() + coffeCondition.getConditionPrice();
        this.volume = coffeWrap.getVolume() + coffeWrap.getWrapVolume();
        this.volumeOfCoffee = coffeWrap.getVolume();
    }

    public double getCost() {
        return this.cost;
    }

    public int getVolume() {
        return this.volume;
    }

    public String toString() {
        return String.format("( %8s, %8s, %10s: %4s %4s %6s)",
                this.name,
                this.coffeCondition, this.coffeWrap,
                this.volumeOfCoffee, this.volume, this.cost);
    }
    public IItem getClone() throws CloneItemException {
        try {
            return this.clone();
        } catch (CloneNotSupportedException e) {
            throw new CloneItemException("Неможливо клонувати товари");
        }
    }

    public IItem clone() throws CloneNotSupportedException {
        return (IItem) super.clone();
    }
}
