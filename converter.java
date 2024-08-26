//Converts feet to meters and meters to feet based on arrays
//Version 1.4
public class converter {
    /** Convert from feet to meters */
    public static double footToMeter(double foot) {
        // Conversion formula 1
        return 0.305 * foot;
    }

    /** Convert from meters to feet */
    public static double meterToFoot(double meter) {
        // Conversion formula 2
        return 3.279 * meter;
    }

    // The specified arrays needed to produce the desired results
    public static void main(String[] args) {
        double[] feetArray = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0};
        double[] metersArray = {20.0, 25.0, 30.0, 35.0, 40.0, 45.0, 50.0, 55.0, 60.0};

        // Print table headers
        System.out.printf("%-10s %-10s%n", "Feet", "Meters");
        System.out.println("------------------------------");

        // The results generated using previous formulas
        for (double feet : feetArray) {
            double convertedMeters = footToMeter(feet);
            System.out.printf("%-10.3f %-10.3f%n", feet, convertedMeters);
        }

        // Print a dash line to separate headers from data
        System.out.println();
        System.out.printf("%-10s %-10s%n", "Meters", "Feet");
        System.out.println("------------------------------");

        // Prints results in table format
        for (double meters : metersArray) {
            double convertedFeet = meterToFoot(meters);
            System.out.printf("%-10.3f %-10.3f%n", meters, convertedFeet);
        }
    }
}