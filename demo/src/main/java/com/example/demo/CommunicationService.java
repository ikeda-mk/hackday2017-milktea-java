package com.example.demo;

import jp.ne.docomo.smt.dev.common.http.AuthApiKey;
import jp.ne.docomo.smt.dev.dialogue.Dialogue;
import jp.ne.docomo.smt.dev.dialogue.data.DialogueResultData;
import jp.ne.docomo.smt.dev.dialogue.param.DialogueRequestParam;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CommunicationService {
	// APIキー
	private static final String API_KEY = "73556748702f69754655714146455a4b715542442f793369347a563964455a57756a4c6b777146354b5337";
	public String communication (int languageNo) {
			List<String> responseLanguage = Arrays.asList("おかえり～", "さびしいの？ぼくがいるからだじょうぶっしょ～", "あたためてあげようか？",
					"はい、ゆっくり食べなよ", "ごちそうさま", "いってらっしゃい", "おう、というかいぬだけどね、おれ");
			return responseLanguage.get(languageNo);
		}

	public String communicationApi (String word) {
		// APIKEYの設定
		AuthApiKey.initializeAuth(API_KEY);

		// 雑談対話パラメータクラスを生成して、質問を設定する
		DialogueRequestParam param = new DialogueRequestParam();
		// ユーザの発話
		param.setUtt(word);

		// 雑談対話クラスの生成して、リクエストを実行する
		Dialogue dialogue = new Dialogue();
		DialogueResultData resultData = new DialogueResultData();
		try {
			resultData = dialogue.request(param);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultData.getUtt();
	}
	protected enum enumWords{
		ただいま, 暇, 寒, あつ, いただきます, ごちそうさま, 行ってきます, 話して
	};
}
