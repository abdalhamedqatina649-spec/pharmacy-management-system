import java.util.ArrayList;

public class PharmacySystem {
    private Inventory inventory = new Inventory();
    private ArrayList<MedicineCategory> categories = new ArrayList<>();

    public boolean addCategory(MedicineCategory category) {
        if (findCategory(category.getId()) != null) return false;
        categories.add(category);
        return true;
    }

    public MedicineCategory findCategory(int id) {
        for (MedicineCategory category : categories) {
            if (category.getId() == id) return category;
        }
        return null;
    }

    public boolean hasCategories() {
        return !categories.isEmpty();
    }

    public boolean hasMedicines() {
        return !inventory.isEmpty();
    }

    public void displayCategories() {
        if (categories.isEmpty()) {
            System.out.println("No categories found.");
            return;
        }
        System.out.println("\n========== MEDICINE CATEGORIES ==========");
        for (MedicineCategory category : categories) {
            System.out.println("ID: " + category.getId() + " | Name: " + category.getName());
        }
    }

    public boolean addMedicine(Medicine medicine) {
        if (inventory.findMedicine(medicine.getId()) != null) return false;
        inventory.addMedicine(medicine);
        return true;
    }

    public Medicine findMedicine(int id) {
        return inventory.findMedicine(id);
    }

    public void addStock(int medicineId, int quantity) {
        Medicine medicine = findMedicine(medicineId);
        if (medicine != null && quantity > 0) medicine.increaseQuantity(quantity);
    }

    public void displayInventory() {
        inventory.displayInventory();
    }
}
