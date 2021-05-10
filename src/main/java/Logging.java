import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logging {

    private File logFile;

    private String getFormattedDate () {
        return LocalDate.now ().format (DateTimeFormatter.ofPattern ("dd-MM-yyyy"));
    }

    private String getFormattedDateAndTime() {
        return getFormattedDate () + LocalDateTime.now ().format (DateTimeFormatter.ofPattern (" hh:mm:ss"));
    }

    public void printLog (String logString) {

        try {
            boolean newFile = !logFile.exists ();
            PrintWriter writer = new PrintWriter (new FileOutputStream (logFile,true));

            if (newFile) {
                writer.printf ("%-19s %-20s %s%n", "Date", "Gebruikersnaam", "Logging");
            }

            String pre = String.format ("%-19s ", getFormattedDateAndTime());
            // TODO: getUserName bestaat nog niet in User en kan dus nog niet worden aangeroepen.
            // pre += String.format ("%-20s ", Login.getInstance ().getUserName ());
            writer.append (String.format ("%s%s%n", pre, logString));
            writer.close ();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace ();
        }
    }
}