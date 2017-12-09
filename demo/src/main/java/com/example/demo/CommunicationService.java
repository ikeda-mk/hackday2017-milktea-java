package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CommunicationService {
	public String communication (String word) {
		switch (word) {
			case "ただいま":
			return "おかえり～";
			case "暇だな":
				return "さびしいの？ぼくがいあるからだいじょうぶっしょ～";
			case "寒っ":
				return "あたためてあげようか？";
			case "あつ":
				return "ふく、ぬぎなよ";
			case "いただきます":
				return "はい、ゆっくり食べなよ";
			case "ごちそうさま":
				return "おう。";
			case "いってきます":
				return "きをつけていってらっしゃ～い";
			case "なにかはなして":
				return "おう、というかいぬだけどね、おれ。";
		}
		return "あ？";
	}
	protected enum enumWords{
		ただいま, 暇だな, 寒っ, あつ, いただきます, ごちそう様, 行ってきます, 何か話して
	};
}
