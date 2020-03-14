package schoperation.RandomSchop.HowOldAreTheyAnyway;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class HowOldAreTheyAnyway
{
    /*
        This class/program can take in a birthdate or age (plus an optional, but recommended birthday) and output their age or birthdate (range) respectively,
     */

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        boolean validInput = false;
        String pick;
        System.out.println("How Old Are They Anyway? by Schoperation");

        while (!validInput)
        {
            System.out.println("Would you like to figure out someone's age (type age) or date of birth (type dob)?");
            pick = scan.nextLine().toLowerCase();

            // Figure out pick
            if (pick.equals("age"))
            {
                determineAge(scan);
                validInput = true;

            }
            else if (pick.equals("dob"))
            {
                determineDOB(scan);
                validInput = true;
            }
            else
            {
                System.out.println("Invalid input. Type, without quotations, either \"age\" or \"dob\".");
                scan.reset();
            }
        }

        scan.close();
    }

    // Asks for a date of birth, returns their age in years, weeks, etc....
    private static void determineAge(Scanner scan)
    {
        // Different formats
        DateTimeFormatter inputDTF = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter outputDTF = DateTimeFormatter.ofPattern("E, MMM dd yyyy"); // use for other method
        String inputDate = "";

        // Grab the DOB
        System.out.println("Enter in the date of birth... MM/dd/yyyy");
        inputDate = scan.nextLine();
        LocalDate dob = LocalDate.parse(inputDate, inputDTF);

        // Subtract the DOB from the current date
        LocalDate currentDate = LocalDate.now();

        // Start with years, easy
        int years = currentDate.getYear() - dob.getYear();

        // If negative, subtract one from years
        int months = currentDate.getMonthValue() - dob.getMonthValue();
        if (months < 0)
        {
            years -= 1;
            months += 12;
        }

        // Dumb string formatting, check if only one year/month.
        String yearOrYears = years == 1 ? " year, ": " years, ";
        String monthOrMonths = months == 1 ? " month ": " months ";

        System.out.println("They are " + years + yearOrYears + months + monthOrMonths + "old.");
        /*
        System.out.println(currentDate.getYear() - dob.getYear() + " years,");
        System.out.println(currentDate.getMonthValue() - dob.getMonthValue() + " months,");
        System.out.println(currentDate.getDayOfMonth() - dob.getDayOfMonth() + " days old.");
         */

    }

    /* Asks for an age, then asks for an optional birthday,
       then returns either a range of DOBs (if no birthday provided) or the exact birthdate.
     */
    private static void determineDOB(Scanner scan)
    {
        System.out.println("Dat oughta hold em in hehehehehehheheh");
    }
}
