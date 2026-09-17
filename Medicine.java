public class Medicine {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private MedicineCategory category;

    public Medicine(int id, String name, double price, int quantity,
                    MedicineCategory category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public boolean isAvailable(int amount) {
        return amount > 0 && quantity >= amount;
    }

    public void decreaseQuantity(int amount) {
        quantity -= amount;
    }

    public void increaseQuantity(int amount) {
        quantity += amount;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void displayInfo() {
        System.out.printf("ID: %d%nName: %s%nPrice: %.2f%nQuantity: %d%nCategory: %s%n-------------------------%n",
                id, name, price, quantity, category.getName());
    }
}
