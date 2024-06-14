package Model;

public class DietPlan {
    private int planId;
    private double dailyCalories;
    private String macronutrientDistribution;
    private String specificRecommendations;
    private Patient patient;
    private Dietitian dietitian;

    public DietPlan(int planId, double dailyCalories, String macronutrientDistribution, String specificRecommendations) {
        this.planId = planId;
        this.dailyCalories = dailyCalories;
        this.macronutrientDistribution = macronutrientDistribution;
        this.specificRecommendations = specificRecommendations;
    }

    public void createDietPlan() {
        System.out.println("Plan de dieta creado: " + this.planId);
    }

    public void adjustDietPlan() {
        System.out.println("Ajustar el plan de dieta: " + this.planId);
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDietitian(Dietitian dietitian) {
        this.dietitian = dietitian;
    }

    public Patient getPatient() {
        return patient;
    }

    public Dietitian getDietitian() {
        return dietitian;
    }

    public String toCsvString() {
        return planId + "," + dailyCalories + "," + macronutrientDistribution + "," + specificRecommendations +
                "," + patient.getPatientId() + "," + dietitian.getDietitianId();
    }
}
