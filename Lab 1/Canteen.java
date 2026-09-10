import java.util.Scanner;

public class Canteen {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalQuantity = 0;
        double totalAmountBeforeDeductions = 0.0;
        boolean isStudent = false;

        System.out.println("==========================================");
        System.out.println("          CANTEEN MENU SELECTION          ");
        System.out.println("==========================================");
        System.out.println("[1] Fried Chicken Meal  - $120.00");
        System.out.println("[2] Spaghetti Plate     - $80.00");
        System.out.println("[3] Burger Special      - $150.00");
        System.out.println("[4] Club Sandwich       - $90.00");
        System.out.println("[5] Iced Tea Drinks     - $30.00");
        System.out.println("==========================================\n");

        boolean keepOrdering = true;

        while (keepOrdering) {
            System.out.print("Enter Item Number (1-5): ");
            int itemChoice = scanner.nextInt();

            System.out.print("Enter Quantity (1-10): ");
            int quantity = scanner.nextInt();

            System.out.print("Are you a student? (Y/N): ");
            char studentInput = scanner.next().toUpperCase().charAt(0);

            if (itemChoice < 1 || itemChoice > 5 || quantity < 1 || quantity > 10) {
                System.out.println("\n[ERROR] Invalid entry! Choice must be 1-5 and quantity must be 1-10.");
                System.out.println("Order was skipped. Please try again.\n");
                
                continue; 
            }

            double itemPrice = 0.0;
            switch (itemChoice) {
                case 1: itemPrice = 120.00; break;
                case 2: itemPrice = 80.00; break;
                case 3: itemPrice = 150.00; break;
                case 4: itemPrice = 90.00; break;
                case 5: itemPrice = 30.00; break;
            }

            if (studentInput == 'Y') {
                isStudent = true;
            }

            double orderSubtotal = itemPrice * quantity;
            totalAmountBeforeDeductions += orderSubtotal;
            totalQuantity += quantity;

            // Katulad ng print(f"") sa python
            System.out.printf("[SUCCESS] Added %d item(s) to order. Subtotal: $%.2f\n", quantity, orderSubtotal);

            System.out.print("\nDo you want to order again? (Y/N): ");
            char again = scanner.next().toUpperCase().charAt(0);

            if (again != 'Y') {
                keepOrdering = false;
            }
            System.out.println();
        }

        double deductionPercentage = 0.0;

        if (isStudent && totalAmountBeforeDeductions >= 500) {
            deductionPercentage = 0.15;
        } else if (isStudent) {
            deductionPercentage = 0.10;
        } else if (totalAmountBeforeDeductions >= 500) {
            deductionPercentage = 0.05;
        } else {
            deductionPercentage = 0.00;
        }

        double totalDeduction = totalAmountBeforeDeductions * deductionPercentage;
        double finalAmountToPay = totalAmountBeforeDeductions - totalDeduction;

        System.out.println("==========================================");
        System.out.println("             FINAL SUMMARY                ");
        System.out.println("==========================================");
        System.out.printf("Total Quantity Purchased : %d\n", totalQuantity);
        System.out.printf("Total Before Deductions  : $%.2f\n", totalAmountBeforeDeductions);
        System.out.printf("Total Deduction (%d%%)     : -$%.2f\n", (int)(deductionPercentage * 100), totalDeduction);
        System.out.println("------------------------------------------");
        System.out.printf("Final Amount to Pay      : $%.2f\n", finalAmountToPay);
        System.out.println("==========================================");

        scanner.close();
    }
}