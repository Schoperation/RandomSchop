package schoperation.RandomSchop.HowOldAreTheyAnyway;

import schoperation.RandomSchop.core.RSThing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HowOldAreTheyAnyway extends RSThing
{
    /**
     *   This class/program can take in a birthdate or age (plus an optional, but recommended birthday) and output their age or birthdate (range) respectively,
     */
    public HowOldAreTheyAnyway(String name, String displayName)
    {
        super(name, displayName);
    }

    @Override
    public void main()
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
    private void determineAge(Scanner scan)
    {
        // Different formats
        DateTimeFormatter inputDTF = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter outputDTF = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
        String inputDate = "";

        // Grab the DOB
        System.out.println("Enter in the date of birth... MM/dd/yyyy");
        inputDate = scan.nextLine();
        LocalDate dob = LocalDate.parse(inputDate, inputDTF);

        // Subtract the DOB from the current date
        LocalDate currentDate = LocalDate.now();
        int years = currentDate.getYear() - dob.getYear();

        int daysUntilBirthday = LocalDate.of(currentDate.getYear(), dob.getMonth(), dob.getDayOfMonth()).getDayOfYear() - currentDate.getDayOfYear();

        // If negative or zero, subtract one from years. Also, see if we are approaching their birthday (or it just passed)
        int months = currentDate.getMonthValue() - dob.getMonthValue();
        if (months < 0 && currentDate.getDayOfMonth() < dob.getDayOfMonth()) // Approaching the same day of their birth month (eg the same day for 08/21 in march would be 03/21)
        {
            years -= 1;
            months += 11;
        }
        else if (months < 0) // Just passed the same day of their birth month
        {
            years -= 1;
            months += 12;
        }
        else if (months == 0 && daysUntilBirthday <= 31 && daysUntilBirthday > 0) // Approaching birthday
        {
            years -= 1;
            months += 11;
        }
        else // Just passed their birthday
        {
            ;
        }

        // Calculate how many days old they are
        int iterateYear = dob.getYear();
        int daysOld = 0;
        while (iterateYear <= currentDate.getYear())
        {
            if (iterateYear == dob.getYear())
                daysOld += dob.lengthOfYear() - dob.getDayOfYear();
            else if (iterateYear == currentDate.getYear())
                daysOld += currentDate.getDayOfYear();
            else
                daysOld += LocalDate.of(iterateYear, 1, 1).lengthOfYear();

            iterateYear++;
        }

        // Editing days, if passed their birthday
        if (daysUntilBirthday < 0)
            daysUntilBirthday += currentDate.plusYears(1).lengthOfYear(); // Add 365 or 366, depending on if leap year

        // Dumb string formatting, check if only one year/month.
        String yearOrYears = years == 1 ? " year, ": " years, ";
        String monthOrMonths = months == 1 ? " month ": " months ";
        String dayOrDays = daysUntilBirthday == 1 ? " day ": " days ";
        String dayOrDays2 = daysOld == 1 ? " day ": " days ";

        System.out.println("They are " + years + yearOrYears + months + monthOrMonths + "old.");
        System.out.println("That would be " + daysOld + dayOrDays2 + "old.");
        System.out.println(daysUntilBirthday + dayOrDays + "until their next birthday, on " + LocalDate.of(currentDate.getYear(), dob.getMonth(), dob.getDayOfMonth()).format(outputDTF) + ".");

    }

    /* Asks for an age, then asks for an optional birthday,
       then returns either a range of DOBs (if no birthday provided) or the exact birthdate.
     */
    private void determineDOB(Scanner scan)
    {
        // Get the info
        System.out.println("Enter in the age...");
        int age = scan.nextInt();
        scan.nextLine();

        System.out.println("Enter in birthday... MM/dd (type zero if unavailable)");
        StringBuilder birthday = new StringBuilder();
        birthday.append(scan.nextLine());

        DateTimeFormatter outputDTF = DateTimeFormatter.ofPattern("E, MMM dd yyyy");

        // Check if we'll use the birthday.. first if yes
        if (!birthday.equals("0"))
        {
            // Add the current year to the string
            birthday.append("/");
            birthday.append(LocalDate.now().getYear());
            DateTimeFormatter inputDTF = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate birthdayThisYear = LocalDate.parse(birthday.toString(), inputDTF);
            System.out.println(birthdayThisYear);


        }
    }
}
