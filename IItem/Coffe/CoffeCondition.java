package IItem.Coffe;

/**
 * Стан кави
 */
public enum CoffeCondition {
        ЗЕРНОВА(15),
        МЕЛЕНА(10),
        РОЗЧИННА(5);

        private final int ConditionPrice;

        CoffeCondition(int conditionPrice) {
                this.ConditionPrice = conditionPrice;
        }

        public int getConditionPrice(){
                return this.ConditionPrice;
        }
        public String toString() {
                String id = name();
                String lower = id.substring(1).toLowerCase();
                return id.charAt(0) + lower;
        }
}
