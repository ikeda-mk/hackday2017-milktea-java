package com.example.twitterstream;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class TweetService {
	
	private static final Twitter twitter = TwitterFactory.getSingleton();;
	
    public static void main(String[] args) throws TwitterException {
		TweetService u = new TweetService();
//        u.tweet(u.searchTweet(u.getTrendInJapan()));
		u.searchTweet(u.getTrendInJapan());
    }
    
    private void searchTweet(String searchStr) throws TwitterException {
		System.out.println("[トレンドワード]" + searchStr);
		Query query = new Query(searchStr + " -filter:links exclude:retweets");
		// 15件のツイートを取得
		QueryResult result = twitter.search(query);
		// リプライを除外
		String n = System.getProperty("line.separator");
		Pattern replyPattern = Pattern.compile("(@|#)");
//		List<String> filterdTweetList = 
			result.getTweets().stream()
			.filter(status -> !replyPattern.matcher(status.getText()).find())
			.map(status -> status.getText().replaceAll("([。…！!？?wｗ]+)", "わん$1").replaceAll("([^。…！!？?wｗ]$)", "$1わん"))
//			.collect(Collectors.toList());
			.forEach(text -> System.out.println(text + n));
//		return filterdTweetList.get(new Random().nextInt(filterdTweetList.size()));
    }
    
    private String getTrendInJapan() throws TwitterException {
		List<Trend> filterdTrendList = new ArrayList<>();
		// 日本のWOEIDで50件のトレンドを取得
		Trends japanTrends = twitter.getPlaceTrends(Integer.parseInt("23424856"));
		// ハッシュタグを除外
		Pattern hashTagPattern = Pattern.compile("^#");
		filterdTrendList = Arrays.stream(japanTrends.getTrends())
			.filter(trend -> !hashTagPattern.matcher(trend.getName()).find())
			.collect(Collectors.toList());
		
		return filterdTrendList.get(new Random().nextInt(filterdTrendList.size())).getName();
    }
    
    private void tweet(String tweetStr) throws TwitterException {
    		twitter.updateStatus(tweetStr);
    }
    
    private void logUserInfo(Twitter twitter) throws TwitterException {
    		User user = twitter.verifyCredentials();
        System.out.println(user.getName());
        System.out.println(user.getScreenName());
        System.out.println(user.getFriendsCount());
        System.out.println(user.getFollowersCount());
    }
}
