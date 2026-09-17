import java.util.Scanner;

public class Main {
    private static int nextInvoiceId = 1001;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PharmacySystem pharmacy = new PharmacySystem();

        System.out.println("===== Pharmacy Management System =====");
        System.out.println("First, create your medicine categories and medicines.");

        initialSetup(input, pharmacy);
        runMainMenu(input, pharmacy);

        input.close();
        System.out.println("Application closed.");
    }

    private static void initialSetup(Scanner input, PharmacySystem pharmacy) {
        while (true) {
            System.out.println("\n========== INITIAL SETUP ==========");
            System.out.println("1. Add medicine category");
            System.out.println("2. Add medicine");
            System.out.println("3. Continue to pharmacy menu");
            System.out.print("Choose an option: ");
            int choice = readInt(input);

            switch (choice) {
                case 1:
                    addCategory(input, pharmacy);
                    break;
                case 2:
                    addMedicine(input, pharmacy);
                    break;
                case 3:
                    if (hasCategoriesAndMedicines(pharmacy)) return;
                    System.out.println("Add at least one category and one medicine first.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void runMainMenu(Scanner input, PharmacySystem pharmacy) {
        boolean running = true;
        while (running) {
            System.out.println("\n========== PHARMACY MENU ==========");
            System.out.println("1. Add medicine category");
            System.out.println("2. Add medicine");
            System.out.println("3. Add stock");
            System.out.println("4. Sell medicine");
            System.out.println("5. Display all medicines");
            System.out.println("6. Display categories");
            System.out.println("7. Search for medicine");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = readInt(input);
            switch (choice) {
                case 1:
                    addCategory(input, pharmacy);
                    break;
                case 2:
                    addMedicine(input, pharmacy);
                    break;
                case 3:
                    addStock(input, pharmacy);
                    break;
                case 4:
                    sellMedicine(input, pharmacy);
                    break;
                case 5:
                    pharmacy.displayInventory();
                    break;
                case 6:
                    pharmacy.displayCategories();
                    break;
                case 7:
                    searchMedicine(input, pharmacy);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addCategory(Scanner input, PharmacySystem pharmacy) {
        System.out.print("Enter category ID: ");
        int id = readInt(input);
        System.out.print("Enter category name: ");
        String name = input.nextLine();

        if (pharmacy.addCategory(new MedicineCategory(id, name))) {
            System.out.println("Category added successfully.");
        } else {
            System.out.println("A category with this ID already exists.");
        }
    }

    private static void addMedicine(Scanner input, PharmacySystem pharmacy) {
        if (!hasCategories(pharmacy)) {
            System.out.println("Add a category before adding a medicine.");
            return;
        }

        pharmacy.displayCategories();
        System.out.print("Enter category ID for this medicine: ");
        int categoryId = readInt(input);
        MedicineCategory category = pharmacy.findCategory(categoryId);
        if (category == null) {
            System.out.println("Category not found.");
            return;
        }

        System.out.print("Enter medicine ID: ");
        int id = readInt(input);
        input.nextLine();
        System.out.print("Enter medicine name: ");
        String name = input.nextLine();
        System.out.print("Enter medicine unit price: ");
        double price = readDouble(input);

        Medicine medicine = new Medicine(id, name, price, 0, category);
        if (pharmacy.addMedicine(medicine)) {
            System.out.println("Medicine added with zero stock. Use Add stock to enter quantity.");
        } else {
            System.out.println("A medicine with this ID already exists.");
        }
    }

    private static void addStock(Scanner input, PharmacySystem pharmacy) {
        pharmacy.displayInventory();
        System.out.print("Enter medicine ID: ");
        int medicineId = readInt(input);
        Medicine medicine = pharmacy.findMedicine(medicineId);
        if (medicine == null) {
            System.out.println("Medicine not found.");
            return;
        }

        System.out.print("Enter quantity to add: ");
        int quantity = readInt(input);
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }
        pharmacy.addStock(medicineId, quantity);
        System.out.println("Stock updated successfully.");
    }

    private static void sellMedicine(Scanner input, PharmacySystem pharmacy) {
        pharmacy.displayInventory();
        System.out.print("Enter medicine ID: ");
        int medicineId = readInt(input);
        Medicine medicine = pharmacy.findMedicine(medicineId);
        if (medicine == null) {
            System.out.println("Medicine not found.");
            return;
        }

        System.out.print("Enter customer ID: ");
        int customerId = readInt(input);
        input.nextLine();
        System.out.print("Enter customer name: ");
        String customerName = input.nextLine();
        System.out.print("Enter customer phone: ");
        String customerPhone = input.nextLine();
        Customer customer = new Customer(customerId, customerName, customerPhone);

        System.out.print("Enter pharmacist ID: ");
        int pharmacistId = readInt(input);
        input.nextLine();
        System.out.print("Enter pharmacist name: ");
        String pharmacistName = input.nextLine();
        System.out.print("Enter license number: ");
        String licenseNumber = input.nextLine();
        Pharmacist pharmacist = new Pharmacist(pharmacistId, pharmacistName, licenseNumber);

        System.out.print("Enter quantity to sell: ");
        int quantity = readInt(input);
        SaleInvoice invoice = new SaleInvoice(nextInvoiceId++, customer, pharmacist);

        if (invoice.addMedicine(medicine, quantity)) {
            invoice.printInvoice();
        } else {
            System.out.println("Sale failed: insufficient stock or invalid quantity.");
        }
    }

    private static void searchMedicine(Scanner input, PharmacySystem pharmacy) {
        System.out.print("Enter medicine ID: ");
        int id = readInt(input);
        Medicine medicine = pharmacy.findMedicine(id);
        if (medicine == null) {
            System.out.println("Medicine not found.");
        } else {
            medicine.displayInfo();
        }
    }

    private static boolean hasCategories(PharmacySystem pharmacy) {
        return pharmacy.hasCategories();
    }

    private static boolean hasCategoriesAndMedicines(PharmacySystem pharmacy) {
        return pharmacy.hasCategories() && pharmacy.hasMedicines();
    }

    private static int readInt(Scanner input) {
        while (!input.hasNextInt()) {
            System.out.print("Enter a valid integer: ");
            input.nextLine();
        }
        int value = input.nextInt();
        input.nextLine();
        return value;
    }

    private static double readDouble(Scanner input) {
        while (!input.hasNextDouble()) {
            System.out.print("Enter a valid number: ");
            input.nextLine();
        }
        double value = input.nextDouble();
        input.nextLine();
        return value;
    }
}
