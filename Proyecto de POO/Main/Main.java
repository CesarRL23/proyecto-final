
import Model.*;
import ArchivoCSV.*;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Dietitian dietitian = null;
        byte cons = 0;
        while (cons == 0) {
            System.out.println("\nMenu:");
            System.out.println("1. iniciar sesion");
            System.out.println("2. Registrar nuevo nutricionista");
            System.out.println("3. salir");
            System.out.print("ingrese la opcion: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    dietitian = logIn(scanner); //metodo para inciar sesion al nutricionista
                    if (dietitian != null) {
                        System.out.println("\nse inicio secion como nutricionista:");
                        System.out.println(dietitian);
                        cons++;

                        dietitianMenu(scanner, dietitian);
                    } else {
                        System.out.println("no se encontro el nutricionista.");
                    }
                    break;
                case 2:
                    dietitian = registerNewDietitian(scanner);
                    if (dietitian != null) {
                        System.out.println("\nse regidtro como nuevo nutricionista:");
                        System.out.println(dietitian);
                        DemoCsv.writeCsv("dietitians.csv", List.of(dietitian.toCsvString()));
                    } else {
                        System.out.println("el registro fallo, co se pudo registrar como nutricionista.");
                    }
                    break;
                case 3:
                    System.out.println("Saliendo del programa. ¡Adiós!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Elección no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void dietitianMenu(Scanner scanner, Dietitian dietitian) {
        while (true) {
            System.out.println("\nmenu del Nutriciolista :");
            System.out.println("1. registar pasiente");
            System.out.println("2. consultar pasientes");
            System.out.println("3. cerrar sesion");
            System.out.print("ingrese la opcion: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerNewPatient(scanner, dietitian);
                    break;
                case 2:
                    System.out.print("Ingrese el ID del paciente que quieres consultar: ");
                    int consultPatientId = scanner.nextInt();
                    scanner.nextLine();

                    List<String[]> patientData = DemoCsv.readCsv("patients.csv");

                    boolean patientFound = false;
                    for (String[] data : patientData) {
                        if (Integer.parseInt(data[0]) == consultPatientId) {

                            System.out.println("\nDetalles del paciente:");
                            System.out.println("id del pasiente: " + data[0]);
                            System.out.println("nombre: " + data[1]);
                            System.out.println("edad: " + data[2]);
                            System.out.println("peso: " + data[3]);
                            System.out.println("altura: " + data[4]);
                            System.out.println("condiciones perxistentes: " + data[5]);

                            List<String[]> dietPlanData = DemoCsv.readCsv("dietPlans.csv");
                            for (String[] dietPlan : dietPlanData) {
                                if (Integer.parseInt(dietPlan[4]) == consultPatientId) {
                                    System.out.println("\nDetalles del plan de dieta:");
                                    System.out.println("id del plan: " + dietPlan[0]);
                                    System.out.println("Calorías Diarias: " + dietPlan[1]);
                                    System.out.println("Distribución de macronutrientes: " + dietPlan[2]);
                                    System.out.println("Recomendaciones específicas:" + dietPlan[3]);
                                    break;
                                }
                            }

                            List<String[]> mealData = DemoCsv.readCsv("meals.csv");
                            System.out.println("\nComidas:");
                            for (String[] meal : mealData) {
                                if (Integer.parseInt(meal[0]) == consultPatientId) {
                                    System.out.println("Nombre de la comida: " + meal[1]);
                                    System.out.println("Macronutrientes de las comidas: " + meal[2]);
                                    System.out.println("Calorías de las comidas: " + meal[3]);
                                    System.out.println("Hora de la comida del día: " + meal[4]);
                                    System.out.println("--------------");
                                }
                            }
                            patientFound = true;
                            break;
                        }
                    }

                    if (!patientFound) {
                        System.out.println("Paciente no encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("Saliendo de tu cuenta. ¡Adiós!");
                    return;
                default:
                    System.out.println("Elección no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void registerNewPatient(Scanner scanner, Dietitian dietitian) {
        System.out.println("\nRegistrar un nuevo paciente:");

        System.out.print("ingrese el Id del pasiente ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ingrese el nombre de pasiente ");
        String name = scanner.nextLine();

        System.out.print("ingrese la edad del pasiente ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ingrese el peso del pasiente ");
        double weight = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("ingrese la altura del pasiente");
        double height = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("ingrese las condiciones perxistentes del pasiente");
        String preexistingConditions = scanner.nextLine();

        Patient newPatient = new Patient(patientId, name, age, weight, height, preexistingConditions);

        System.out.println("\nnuevo pasiente registrado");
        System.out.println(newPatient);

        System.out.print("\nIngrese las calorías diarias para el plan de dieta: ");
        double dailyCalories = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Ingrese la distribución de macronutrientes para el plan de dieta: ");
        String macronutrientDistribution = scanner.nextLine();

        System.out.print("ingrese la recomendaciones especificas para el plan de dieta ");
        String specificRecommendations = scanner.nextLine();

        int nextPlanId = patientId;
        DietPlan newDietPlan = new DietPlan(nextPlanId, dailyCalories, macronutrientDistribution,
                specificRecommendations);

        System.out.println("\nnuevo plan de dieta creado");
        System.out.println(newDietPlan);

        System.out.println("Ingrese los detalles de la comida");

        System.out.print("Nombre de la comida: ");
        String mealName = scanner.nextLine();

        System.out.print("Macronutrientes de las comidas: ");
        String mealMacronutrients = scanner.nextLine();

        System.out.print("introduzaca las calorias de la comida ");
        double mealCalories = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Ingrese la hora de la comida del día: ");
        String mealTimeOfDay = scanner.nextLine();
        int nexMealId = patientId;

        Meal newMeal = new Meal(nexMealId, mealName, mealMacronutrients, mealCalories, mealTimeOfDay);

        System.out.println("\nNueva comida registrada");
        System.out.println(newMeal);

        newDietPlan.setPatient(newPatient);
        newDietPlan.setDietitian(dietitian);

        DemoCsv.writeCsv("patients.csv", List.of(newPatient.toCsvString()));
        DemoCsv.writeCsv("dietPlans.csv", List.of(newDietPlan.toCsvString()));
        DemoCsv.writeCsv("meals.csv", List.of(newMeal.toCsvString()));
    }

    private static Dietitian logIn(Scanner scanner) {
        System.out.println("\nInicie sesión como nutricionista:");

        System.out.print("Ingrese el ID del dietista: ");
        int dietitianId = scanner.nextInt();
        scanner.nextLine();

        List<String[]> dietitianData = DemoCsv.readCsv("dietitians.csv");
        for (String[] data : dietitianData) {
            if (Integer.parseInt(data[0]) == dietitianId) {
                return new Dietitian(Integer.parseInt(data[0]), data[1], data[2]);
            }
        }
        return null;
    }

    private static Dietitian registerNewDietitian(Scanner scanner) {
        System.out.println("\nregistrar un nuevo nutriv¡cionista");

        System.out.print("ingrese el id del nutricionista: ");
        int dietitianId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ingrese el nombre del nutricionista: ");
        String name = scanner.nextLine();

        System.out.print("ingrese la especialidad del nutricionista: ");
        String specialty = scanner.nextLine();
        return new Dietitian(dietitianId, name, specialty);

    }
}