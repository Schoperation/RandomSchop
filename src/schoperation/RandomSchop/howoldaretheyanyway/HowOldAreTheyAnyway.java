package schoperation.RandomSchop.howoldaretheyanyway;

import schoperation.RandomSchop.core.Main;
import schoperation.RandomSchop.core.RSThing;
import schoperation.RandomSchop.panel.Panels;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HowOldAreTheyAnyway extends RSThing
{
    /**
     *   This class/program can take in a birthdate or age (plus an optional, but recommended birthday) and output their age or birthdate (range) respectively,
     *
     *   <p> Created March 14th 2020 </p>
     */
    public HowOldAreTheyAnyway(String name, String displayName)
    {
        super(name, displayName);
        this.mainPanel = Panels.CONSOLE_PANEL;
    }

    @Override
    public void main()
    {
        String pick;
        System.out.println("How Old Are They Anyway? by Schoperation");

        // Instead of a scanner, we can use dialog boxes with preset options or a simple textbox!! Woooaahh!
        // Frame, Question, Dialog Title, Message Type, Custom icon, object array of choices, first selected choice
        final Object[] options = {"Age", "Date of Birth"};
        pick = (String) JOptionPane.showInputDialog(Main.jframe, "Would you like to figure out someone's age or date of birth?", "Question", JOptionPane.PLAIN_MESSAGE, null, options, "Age");

        // Figure out pick
        if (pick.equals(options[0]))
            determineAge();
        else
            determineDOB();
    }

    /**
     * Asks for a date of birth, returns their age in years, weeks, etc...
     */
    private void determineAge()
    {
        // Different formats
        DateTimeFormatter inputDTF = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter outputDTF = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
        String inputDate;
        LocalDate dob = null;
        boolean validDate = false;

        // Grab the DOB
        while (!validDate)
        {
            inputDate = (String) JOptionPane.showInputDialog(Main.jframe, "Enter in the date of birth... MM/dd/yyyy", "Question", JOptionPane.PLAIN_MESSAGE, null, null, null);

            try
            {
                dob = LocalDate.parse(inputDate, inputDTF);
            }
            catch (DateTimeParseException exception)
            {
                System.out.println("Invalid date, please use the format MM/dd/yyyy (2 digit month/2 digit day/4 digit year)");
                continue;
            }

            validDate = true;
        }

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

    /**
     *  Asks for an age, then asks for an optional birthday,
     *  then returns either a range of DOBs (if no birthday provided) or the exact birthdate.
     */
    private void determineDOB()
    {
        // Get the info, starting with age
        int age = 0;

        while (age <= 0)
        {
            try
            {
                age = Integer.parseInt((String) JOptionPane.showInputDialog(Main.jframe, "Enter in the age", "Question", JOptionPane.PLAIN_MESSAGE, null, null, null));
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input dumbass. Enter an actual number.");
            }
        }

        // Grab birthday
        StringBuilder birthday = new StringBuilder();
        boolean validBirthday = false;

        while (!validBirthday)
        {
            birthday.append((String) JOptionPane.showInputDialog(Main.jframe, "Enter in birthday... MM/dd (type zero if unavailable)", "Question", JOptionPane.PLAIN_MESSAGE, null, null, null));

            // Check if zero or valid birthday
            if (birthday.toString().equals("0"))
                ;
            else
            {
                try
                {
                    // Append a fake year because DateTimeFormatter can't handle MM/dd for some odd reason
                    LocalDate.parse(birthday.append("/00").toString(), DateTimeFormatter.ofPattern("MM/dd/yy"));
                }
                catch (DateTimeParseException e)
                {
                    System.out.println("Invalid birthday. Use format MM/dd (2 digit month/2 digit day). Or type 0 to skip this crap.");
                    birthday.delete(0, birthday.length());
                    continue;
                }

                // Delete the hanging dummy year
                birthday.delete(5, 8);
            }

            validBirthday = true;
        }

        DateTimeFormatter outputDTF = DateTimeFormatter.ofPattern("E, MMM dd yyyy");

        // Check if we'll use the birthday or not...
        if (birthday.toString().equals("0"))
        {
            // We have to output a range of birthdays
            // They could've turned that age today, or perhaps tomorrow is their birthday. Thus, the range is either [today, age years ago] or [tomorrow, age+1 years ago]
            LocalDate today = LocalDate.now();
            System.out.println("Being currently " + age + " years old, their birthdate ranges from " + today.minusYears(age + 1).plusDays(1).format(outputDTF) + " to " + today.minusYears(age).format(outputDTF) + ".");
        }
        else
        {
            // Add the current year to the string
            birthday.append("/");
            birthday.append(LocalDate.now().getYear());
            DateTimeFormatter inputDTF = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate birthdayThisYear = LocalDate.parse(birthday.toString(), inputDTF);

            /*
                Two possible answers
                    - If we haven't passed their birthday yet, they will turn age+1 this year. So subtract age+1 from the current year.
                    - If we HAVE passed their birthday, just subtract age from the current year.
             */
            LocalDate birthDate = birthdayThisYear.minusYears(age);

            if (birthdayThisYear.compareTo(LocalDate.now()) > 0)
                birthDate = birthDate.minusYears(1);

            System.out.println("They were born on " + birthDate.format(outputDTF) + ".");
        }
    }
}
