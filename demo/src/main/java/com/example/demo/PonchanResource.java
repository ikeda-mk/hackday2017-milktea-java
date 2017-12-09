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

	@RequestMapping("/communication")
	public String communication(@RequestParam String word) {
		//ユーザーが発した言葉へのレスポンス処理
		List<String> shiritoris = Arrays.asList("しりとり");
		boolean responseTrue = Arrays.stream(enumWords.values()).anyMatch(enumWord ->enumWord.toString().contains(word));
		List<String> shiritoriTrue = shiritoris.stream().filter(shiritori ->word.contains(shiritori)).collect(Collectors.toList());

		if (shiritoriTrue.size() != 0) {
			return shiritoriService.shiritori(word);
		} else if (responseTrue) {
			return communicationService.communication(word);
		} else {
			return communicationService.communicationApi(word);
		}
	}

	@RequestMapping("/talk")
	public String talk() {
		//独り言をつぶやく処理
		return monologueService.monologue();
	}

	@RequestMapping("/tweet")
	public void twitter(@RequestParam String word) {
		//ツイートを行う処理
		tweetService.test(word);
		}
}
