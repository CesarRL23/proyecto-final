package Model;


public class Patient {
    private int patientId;
    private String name;
    private int age;
    private double weight;
    private double height;
    private String preexistingConditions;

    public Patient(int patientId, String name, int age, double weight, double height, String preexistingConditions) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.preexistingConditions = preexistingConditions;
    }

    public void registerPatient() {
        System.out.println("Patient registered: " + this.name);
    }

    public void updatePatientInfo() {
        System.out.println("Patient information updated: " + this.name);
    }

    public void deletePatient() {
        System.out.println("Patient deleted: " + this.name);
    }

    public int getPatientId() {
        return patientId;
    }

    public String toCsvString() {
        return patientId + "," + name + "," + age + "," + weight + "," + height + "," + preexistingConditions;
    }

}
