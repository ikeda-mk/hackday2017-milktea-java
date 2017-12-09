package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PonChanResource {

	@Autowired
	private SpeakService speakService;
	@Autowired
	private ShiritoriService srtrService;
	@Autowired
	private TwitterService twitterService;

	@RequestMapping("/raspberryPi")
	public String speak(@RequestParam String word) {
		List<String> responses = Arrays.asList("ただいま", "暇だな");
		List<String> shiritoris = Arrays.asList("しりとり");
		List<String> responseTrue = responses.stream().filter(response ->word.contains(response)).collect(Collectors.toList());
		List<String> shiritoriTrue = shiritoris.stream().filter(shiritori ->word.contains(shiritori)).collect(Collectors.toList());

		if (responseTrue.size() == 0) {
			return speakService.speak(word);
		} else if (shiritoriTrue.size() == 0) {
			return srtrService.srtr(word);
		}
		return "hello World";
	}

	@RequestMapping("/twitter")
	public void twitter(@RequestParam String word) {
		twitterService.test(word);
		}
}
