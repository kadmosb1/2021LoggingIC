import java.util.ArrayList;
import java.util.Scanner;

public class Login {

    private static Login singleton;
    private ArrayList<User> users;
    private User loggedInUser;

    private Login () {
        users = new ArrayList<> ();
        users.add (new User ("x", "y"));
        users.add (new User ("a", "b"));
        users.add (new User ("k", "l"));
        loggedInUser = null;
    }

    public static Login getInstance () {

        if (singleton == null) {
            singleton = new Login ();
        }

        return singleton;
    }

    private User getUser (String name) {

        for (User user : users) {
            if (user.getName ().equals (name)) {
                return user;
            }
        }

        return null;
    }

    private boolean userIsAuthenticated () {
        return loggedInUser != null;
    }

    public boolean isAuthenticated () {

        if (userIsAuthenticated ()) {
            return true;
        }
        else {

            Scanner scanner = new Scanner(System.in);
            Logging logging = Logging.getInstance ();

            for (int i = 0; i < 3; i++) {

                System.out.println ("=================");
                System.out.print("Met welke gebruikersnaam wilt u inloggen? ");
                String userName = scanner.nextLine();
                System.out.print ("Graag het bijbehorende password: ");
                String password = scanner.nextLine();
                System.out.println ("=================");

                User user = getUser(userName);

                if (user != null && user.passwordIsCorrect(password)) {
                    this.loggedInUser = user;
                    logging.printLog ("Gebruiker is ingelogd");
                    System.out.println ();
                    return true;
                }

                System.out.println ("De combinatie van gebruikersnaam en password is niet OK.");
                logging.printLog (String.format ("Het is gebruiker met gebruikersnaam '%s' niet gelukt om in te loggen", userName));
            }

            System.out.println ("=================");
            System.out.println ();
            return false;
        }

    }

    public String getUserName () {

        if (loggedInUser == null) {
            return "";
        }

        return loggedInUser.getName ();
    }
}