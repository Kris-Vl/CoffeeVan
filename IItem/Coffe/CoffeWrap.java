package IItem.Coffe;

/**
 * Тип пакування
 */
public enum CoffeWrap {
    СКЛЯНЕ (20, 5, 15),
    ПЛАСТИКОВЕ (8, 2, 10);

    private final int volume;
    private final int wrapVolume;
    private final int wrapPrice;

    CoffeWrap(int volume, int wrapVolume, int wrapPrice) {
        this.volume = volume;
        this.wrapVolume = wrapVolume;
        this.wrapPrice = wrapPrice;
    }

    public int getWrapVolume() {
        return this.wrapVolume;
    }

    public int getVolume() {
        return this.volume;
    }

    public int getWrapPrice() {
        return this.wrapPrice;
    }

    public String toString() {
        String id = name();
        String lower = id.substring(1).toLowerCase();
        return id.charAt(0) + lower;
    }
}

