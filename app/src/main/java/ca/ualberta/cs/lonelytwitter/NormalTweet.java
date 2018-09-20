package ca.ualberta.cs.lonelytwitter;

public class NormalTweet extends Tweet {

    public NormalTweet(String message) {
        super(message);
    }

    public Boolean isImportant() {
        return false;
    }
}
