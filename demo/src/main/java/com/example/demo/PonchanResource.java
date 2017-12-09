package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.CommunicationService.enumWords;

@RestController
public class PonchanResource {

	@Autowired
	private CommunicationService communicationService;
	@Autowired
	private ShiritoriService shiritoriService;
	@Autowired
	private MonologueService monologueService;
	@Autowired
	private TweetService tweetService;

	// しりとり続行判断フラグ
//	private serialized

	@RequestMapping("/communication")
	public String communication(@RequestParam String word) {
		//ユーザーが発した言葉へのレスポンス処理
		boolean responseTrue = Arrays.stream(enumWords.values()).anyMatch(enumWord ->enumWord.toString().contains(word));
		boolean shiritoriTrue = word.contains("しりとり");

		// しりとりを続けるかを判定するフラグ
		boolean continueJudge = false;
		if (shiritoriTrue || continueJudge) {
			String srtrRes = shiritoriService.shiritori(word);
			// 会話IDが入っている場合はしりとり続行
			continueJudge = srtrRes.contains("talkId");
			if (continueJudge) {
				srtrRes.replace("talkId", "");
			}
			return srtrRes;
		} else if (responseTrue) {
			return communicationService.communication(word);
		} else {
			return communicationService.communicationApi(word);
		}
	}

	@RequestMapping("/talk")
	public void talk() {
		//独り言をつぶやく処理
		monologueService.monologue();
	}

	@RequestMapping("/tweet")
	public void twitter(@RequestParam String word) {
		//ツイートを行う処理
		tweetService.test(word);
		}
}
