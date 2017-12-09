package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		List<String> responses = Arrays.asList("ただいま", "暇だな");
		List<String> shiritoris = Arrays.asList("しりとり");
		List<String> responseTrue = responses.stream().filter(response ->word.contains(response)).collect(Collectors.toList());
		List<String> shiritoriTrue = shiritoris.stream().filter(shiritori ->word.contains(shiritori)).collect(Collectors.toList());

		if (responseTrue.size() == 0) {
			return communicationService.communication(word);
		} else if (shiritoriTrue.size() == 0) {
			return shiritoriService.shiritori(word);
		}
		return "hello World";
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
