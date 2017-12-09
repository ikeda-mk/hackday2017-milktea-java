package com.example.twitterstream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
public class TwitterStreamApplication implements CommandLineRunner {

	@Value("${tweets.output.file}")
	private String outputFile;

	private String[] filterWord = new String[]{"#ももクロ"};

	public static void main(String[] args) {
		SpringApplication.run(TwitterStreamApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		File file = new File(this.outputFile);
		FileWriter filewriter = new FileWriter(file, false);

		StatusListener listener = new StatusListener() {
			@Override
			public void onStatus(Status status) {
				System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());

				try {
					ObjectMapper mapper = new ObjectMapper();

					String json = mapper.writeValueAsString(status);
					System.out.println(json);
					filewriter.write(json);
					filewriter.write("\n");
					filewriter.flush();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
			}

			@Override
			public void onStallWarning(StallWarning warning) {
				System.out.println("Got stall warning:" + warning);
			}

			@Override
			public void onException(Exception ex) {
				ex.printStackTrace();
			}
		};
		twitterStream.addListener(listener);
		twitterStream.filter(new FilterQuery(0, new long[0], filterWord));
//		twitterStream.sample();
	}
}
