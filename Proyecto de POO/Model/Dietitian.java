package Model;

public class Dietitian {
    private int dietitianId;
    private String name;
    private String specialty;

    public Dietitian(int dietitianId, String name, String specialty) {
        this.dietitianId = dietitianId;
        this.name = name;
        this.specialty = specialty;
    }

    public void registerDietitian() {
        System.out.println("nutricionista registrado " + this.name);
    }

    public void manageDietitian() {
        System.out.println("nutricionista encargado: " + this.name);
    }

    public String toCsvString() {
        return dietitianId + "," + name + "," + specialty;
    }

    public int getDietitianId() {
        return dietitianId;
    }
}
