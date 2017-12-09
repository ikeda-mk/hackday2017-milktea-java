package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.Random;

@Service
public class TweetService {


	@Autowired
	private TalkingTextHolder textHolder;

	Random random = new Random();

	private String[] templateArray =  {
			"「%s」って言われたわん"
			, "%s"
			, "「%s」わたしもそう思うわん" };

	private static final Twitter twitter = TwitterFactory.getSingleton();
	//ここにツイッター処理を書いていく
		public void tweet() {
			if(textHolder.getLatestText() == null){
				return;
			}
			try{
				twitter.updateStatus(String.format(templateArray[random.nextInt(3)],textHolder.getLatestText()));
			}catch(TwitterException e){
				e.printStackTrace();
			}

		}
}
