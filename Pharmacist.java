public class Pharmacist {
    private int id;
    private String name;
    private String licenseNumber;

    public Pharmacist(int id, String name, String licenseNumber) {
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
    }

    public String getName() { return name; }
}
