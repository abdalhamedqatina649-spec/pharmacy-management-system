import java.util.ArrayList;

public class Inventory {
    private ArrayList<Medicine> medicines = new ArrayList<>();

    public void addMedicine(Medicine medicine) {
        medicines.add(medicine);
    }

    public boolean isEmpty() {
        return medicines.isEmpty();
    }

    public Medicine findMedicine(int id) {
        for (Medicine medicine : medicines) {
            if (medicine.getId() == id) return medicine;
        }
        return null;
    }

    public void displayInventory() {
        if (medicines.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n========== MEDICINE INVENTORY ==========");
        for (Medicine medicine : medicines) medicine.displayInfo();
    }
}
