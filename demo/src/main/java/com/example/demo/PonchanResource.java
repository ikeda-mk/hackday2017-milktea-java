package com.example.demo;

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
	@Autowired
	private ShiritoriResponseDto shiritoriResponseDto;
	@Autowired
    private TalkingTextHolder textHolder;

	@RequestMapping("/communication")
	public String communication(@RequestParam String word) {



		//ユーザーが発した言葉へのレスポンス処理
		int languageNo = 0;
		for(enumWords enumWord : enumWords.values()) {
			if (word.contains(enumWord.toString())) {
				break;
			} else {
				languageNo++;
			}
		}
		boolean shiritoriTrue = word.contains("しりとり");

		// しりとりを続けるかを判定するフラグ
//		boolean continueJudge = false;
		if (shiritoriTrue || !"".equals(shiritoriResponseDto.getTalkId())) {
			String srtrRes = shiritoriService.shiritori(word);
			String[] resArray = srtrRes.split(",");
			// 会話IDが入っている場合はしりとり続行
			if (srtrRes.contains("talkId")) {
				shiritoriResponseDto.setResText(resArray[0]);
				shiritoriResponseDto.setTalkId(resArray[1].replace("talkId", ""));
			} else {
				shiritoriResponseDto.setResText(resArray[0]);
				shiritoriResponseDto.setTalkId("");
			}
			return shiritoriResponseDto.getResText();
		} else if (languageNo < enumWords.values().length) {
			//会話の履歴を保存
			textHolder.addText(word);
			return communicationService.communication(languageNo);
		} else {
			//会話の履歴を保存
			textHolder.addText(word);
			return communicationService.communicationApi(word);
		}
	}

	@RequestMapping("/talk")
	public void talk() {
		//独り言をつぶやく処理
		monologueService.monologue();
	}

	@RequestMapping("/tweet")
	public void twitter() {
		//ツイートを行う処理
		tweetService.tweet();
		}
}
