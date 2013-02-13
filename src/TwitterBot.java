import java.util.Scanner;
//import java.util.List;
//import java.util.ArrayList;


import java.util.EventObject;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.Status;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterBot {
    Twitter twitter;
    private AccessToken accessToken;

    public static void main(String[] args) throws Exception{
    TwitterBot twitBot = new TwitterBot();

    accessTwitter(twitBot);
    tweetKeyword(twitBot);

    }

 /*   private static void menu(){
        Scanner choice = new Scanner(System.in);

        switch() {
            case 1:
                accessTwitter();
                break;
            case 2;
                tweetKeyword();
                break;
            case 3;


    }
    */


    private static void tweetKeyword(TwitterBot twitBot) throws TwitterException {
        long tweetID;
        long previousTweetID = 0;
        Status activeTweet;
        String QUERY_STRING = "#gardening";

        //Search the string
        Query query = new Query(QUERY_STRING);
        QueryResult qr =  twitBot.twitter.search(query);
        //Take the first tweet from the search
         for (int i=3; i < 10; i++){

             activeTweet = qr.getTweets().get(i);
             long activeTweetID = activeTweet.getId();
             if(previousTweetID == activeTweetID){

             }

             else{
                 twitBot.twitter.retweetStatus(activeTweet.getId());
                 previousTweetID = activeTweet.getId();
             }
         }
    }


    private static void accessTwitter(TwitterBot twitBot) throws TwitterException{
        Scanner inputPin = new Scanner(System.in);
        String accessPin;

        twitBot.twitter = new TwitterFactory().getInstance();
        //
        twitBot.twitter.setOAuthConsumer("[CONSUMER KEY]" , "[ACCESS TOKEN]");
        RequestToken requestToken = twitBot.twitter.getOAuthRequestToken();
        twitBot.accessToken = null;

        System.out.println("The following URL will grant access to your account: ");
        System.out.println(requestToken.getAuthorizationURL());

        while (null == twitBot.accessToken) {

            /*System.out.println("The following URL will grant access to your account: ");
            System.out.println(requestToken.getAuthorizationURL());*/

            System.out.println("Enter your PIN: ");
            accessPin = inputPin.nextLine();

            if (accessPin.length() < 0){
                System.out.print("You have to type in the PIN numbers. ");
            }

            if (accessPin.length() > 0){
            twitBot.accessToken = twitBot.twitter.getOAuthAccessToken(requestToken, accessPin);
            }


        }

    }
}
