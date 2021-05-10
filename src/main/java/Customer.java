public class Customer {

    private String name;
    private String address;
    private String zipcode;
    private String city;

    public Customer (String name, String address, String zipcode, String city) {

        Logging logging = Logging.getInstance ();

        if (Login.getInstance ().isAuthenticated ()) {
            this.name = name;
            this.address = address;
            this.zipcode = zipcode;
            this.city = city;
            logging.printLog (String.format ("Er is een klant met de naam '%s' aangemaakt.", name));
        }
        else {
            logging.printLog ("Het is niet gelukt om de klant aan te maken, omdat er geen gebruiker is ingelogd.");
        }
    }

    @Override
    public String toString() {

        Logging.getInstance ().printLog ("Gegevens klant opgevraagd.");

        if (Login.getInstance ().isAuthenticated ()) {
            return String.format ("%s%n%s%n%s  %s%n", name, address, zipcode, city);
        }

        return "";
    }
}