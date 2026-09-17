public class PurchaseInvoice {
    private int id;
    private Supplier supplier;
    private double total;

    public PurchaseInvoice(int id, Supplier supplier) {
        this.id = id;
        this.supplier = supplier;
    }

    public void addMedicine(Medicine medicine, int quantity, double purchasePrice) {
        medicine.increaseQuantity(quantity);
        total += quantity * purchasePrice;
    }

    public void printInvoice() {
        System.out.println("Purchase Invoice ID: " + id);
        System.out.println("Supplier: " + supplier.getName());
        System.out.printf("Total: %.2f%n", total);
    }
}
