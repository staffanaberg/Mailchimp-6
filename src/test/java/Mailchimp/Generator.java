package Mailchimp;

public class Generator {

    public long unixTime() {
        long unixTime = System.currentTimeMillis() / 1000L;
        return unixTime;
    }

    public String letter() {
        char randomLetter = (char) ('a' + Math.random() * ('z' - 'a' + 1));
        return Character.toString(randomLetter); // Convert char to String
    }
}
