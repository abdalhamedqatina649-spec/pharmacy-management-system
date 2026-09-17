import java.util.ArrayList;

public class SaleInvoice {
    private int id;
    private Customer customer;
    private Pharmacist pharmacist;
    private double total = 0.0;
    private ArrayList<String> items = new ArrayList<>();

    public SaleInvoice(int id, Customer customer, Pharmacist pharmacist) {
        this.id = id;
        this.customer = customer;
        this.pharmacist = pharmacist;
    }

    public boolean addMedicine(Medicine medicine, int quantity) {
        if (!medicine.isAvailable(quantity)) return false;

        medicine.decreaseQuantity(quantity);
        double itemTotal = medicine.getPrice() * quantity;
        total += itemTotal;
        items.add(String.format("%-20s Quantity: %-4d Unit Price: %8.2f Item Total: %8.2f",
                medicine.getName(), quantity, medicine.getPrice(), itemTotal));
        return true;
    }

    public void printInvoice() {
        System.out.println("\n==================== SALE INVOICE ====================");
        System.out.println("Invoice ID: " + id);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Pharmacist: " + pharmacist.getName());
        System.out.println("-------------------------------------------------------");
        for (String item : items) System.out.println(item);
        System.out.println("-------------------------------------------------------");
        System.out.printf("Grand Total: %.2f%n", total);
        System.out.println("=======================================================");
    }
}
