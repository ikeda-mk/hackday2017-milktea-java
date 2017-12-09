package com.example.demo;

import org.springframework.stereotype.Service;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Service
public class TweetService {

	private static final Twitter twitter = TwitterFactory.getSingleton();
	//ここにツイッター処理を書いていく
		public void tweet(String word) {
			try{
				twitter.updateStatus(word);
			}catch(TwitterException e){
				e.printStackTrace();
			}

		}
}
