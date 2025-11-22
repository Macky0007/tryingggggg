import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
public class Gt2_C1B_Dimaculangan_Guce {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int upperbound;
        int numOfIterations;
        int randomNum;
        int smallestOddNum;
        int smallestNum;
        int largestOddNum;
        int largestNum;
        int[] organizedNum;
        String input1;
        String input2;

        do {
            do {
                System.out.print("Enter a number to randomize from 100 - 10,000: ");
                upperbound = validateInput(sc, "Enter a number to randomize from 100 - 10,000: ");
                if (upperbound < 100 || upperbound > 10000) {
                    System.out.println("Invalid. Please enter a number between 100 - 10000");
                    sc.nextLine();
                }
            }
            while(upperbound < 100 || upperbound > 10000);

            do {
                System.out.print("Number of iterations: ");
                numOfIterations = validateInput(sc, "Number of iterations: ");
                if (numOfIterations <= 0) {
                    System.out.println("Invalid number. Please enter a positive integer.");
                    sc.nextLine();
                }
            }
            while(numOfIterations <= 0);

            System.out.println();

            organizedNum = new int[numOfIterations];

            for(int i = 0; i < numOfIterations; i++) {
                randomNum = generateRandomNumber(upperbound);
                organizedNum[i] = randomNum;
            }

            Arrays.sort(organizedNum);
            System.out.println("Sort numbers: " + Arrays.toString(organizedNum));

            smallestOddNum = findSmallestOdd(organizedNum);
            smallestNum = organizedNum[0];
            largestOddNum = findLargestOdd(organizedNum);
            largestNum = organizedNum[numOfIterations - 1];

            sc.nextLine();

            boolean choiceNotD = false;
            while(!choiceNotD) {
                System.out.println("\nWhat do you want to do? ");
                System.out.println("A. Find the smallest and largest odd number");
                System.out.println("B. Find the difference between the largest and smallest value");
                System.out.println("C. Display all the magic numbers");
                System.out.println("D. Exit");
                System.out.print("Answer: ");
                input1 = sc.nextLine().trim();

                if (input1.equalsIgnoreCase("A")) {
                    calculationsA(smallestOddNum, largestOddNum);
                } else if (input1.equalsIgnoreCase("B")) {
                    calculationsB(smallestNum, largestNum);
                } else if (input1.equalsIgnoreCase("C")) {
                    calculationsC(organizedNum);
                } else if (input1.equalsIgnoreCase("D")) {
                    choiceNotD = true;
                } else {
                    System.out.println("Error: Please select a proper choice from A, B, C, or D");
                }
            }

            System.out.print("\nTry again? Enter Y for yes and N for no: ");
            input2 = sc.nextLine().trim().toLowerCase();
            boolean retry = true;
            while(retry) {
                if (input2.equals("y") || input2.equals("yes")) {
                    retry = false;
                }
                else if (input2.equals("n") || input2.equals("no")) {
                    retry = false;
                    System.out.println("Thank you for using this program.");
                }
                else {
                    System.out.println("Invalid input. Please enter 'Y'/'Yes' for Yes or 'N'/'No' for No: ");
                    System.out.print("\nTry again? Enter Y for yes and N for no: ");
                    input2 = sc.nextLine().trim().toLowerCase();
                }
            }
        }
        while (input2.equalsIgnoreCase("Y"));
        //Validations
    }
    public static int generateRandomNumber(int upperBound){
        Random rand = new Random();

        int randomNum = rand.nextInt(1, upperBound);
        return randomNum;
    }

    //Function for the answers for A
    static int findSmallestOdd(int[] organizedNum){
        for(int num : organizedNum){
            if(num % 2 != 0){
                return num;
            }
        }
        return 0;
    }

    static int findLargestOdd(int[] organizedNum){
        for(int i = organizedNum.length - 1; i >= 0; i--){
            if(organizedNum[i] % 2 != 0){
                return organizedNum[i];
            }
        }
        return 0;
    }

    static void calculationsA(int smallestOddNum, int largestOddNum) {
        if(smallestOddNum == 0 || largestOddNum == 0){
            System.out.println("No odd numbers found");
        }
        else {
            System.out.println("The smallest Odd number is: " + smallestOddNum);
            System.out.println("The largest Odd number is: " + largestOddNum);
        }
    }

    //Function for the answers for B
    static void calculationsB(int smallestNum, int largestNum) {
        int difference = largestNum - smallestNum;
        System.out.println("The difference between the largest number and smallest number is: " + difference);
    }

    //Function for the answers for C
    // Function for the answers for C (Magic Numbers: sum of digits)
    static void calculationsC(int[] organizedNum) {
        System.out.println("Magic numbers (sum of digits):");
        for (int num : organizedNum) {
            int sumOfDigits = sumDigits(num);
            if(sumOfDigits == 1){
                System.out.println("The sum of the digits of " + num + " is " + sumOfDigits);
                System.out.println("it is a magic number");
            }
        }
    }

    static int sumDigits(int num) {
        int sum = 0;
        int lastDigit;
        while (num > 9) {
            while(num > 0) {
                lastDigit = num % 10;
                sum = sum + lastDigit;
                num = num / 10;
            }
            num = sum;
            sum = 0;
        }
        return num;
    }
    //Function for the checking of correct user inputs
    static int validateInput(Scanner sc, String prompt) {
        while(!sc.hasNextInt()){
            System.out.println("Error: Please enter a valid input");
            System.out.print(prompt);
            sc.nextLine();
        }
        return sc.nextInt();
    }
}