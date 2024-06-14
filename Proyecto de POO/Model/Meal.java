package Model;

public class Meal {
    private int codePatient;
    private String name;
    private String macronutrients;
    private double calories;
    private String timeOfDay;

    public Meal(int codePatient, String name, String macronutrients, double calories, String timeOfDay) {
        this.codePatient= codePatient;
        this.name = name;
        this.macronutrients = macronutrients;
        this.calories = calories;
        this.timeOfDay = timeOfDay;
    }

    
    public String toCsvString() {
        return codePatient + "," + name + "," + macronutrients + "," + calories + "," + timeOfDay;
    }
}
