import java.util.ArrayList;

public class Prescription {
    private int id;
    private Customer customer;
    private String doctorName;
    private ArrayList<Medicine> medicines = new ArrayList<>();

    public Prescription(int id, Customer customer, String doctorName) {
        this.id = id;
        this.customer = customer;
        this.doctorName = doctorName;
    }

    public void addMedicine(Medicine medicine) {
        medicines.add(medicine);
    }

    public void displayPrescription() {
        System.out.println("Prescription ID: " + id);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Doctor: " + doctorName);
        for (Medicine medicine : medicines) {
            System.out.println("Medicine: " + medicine.getName());
        }
    }
}
