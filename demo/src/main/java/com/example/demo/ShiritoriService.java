package com.example.demo;

import org.springframework.stereotype.Service;

import jp.ne.docomo.smt.dev.common.http.AuthApiKey;
import jp.ne.docomo.smt.dev.dialogue.Dialogue;
import jp.ne.docomo.smt.dev.dialogue.data.DialogueResultData;
import jp.ne.docomo.smt.dev.dialogue.param.DialogueRequestParam;

@Service
public class ShiritoriService {

	// APIキー
	private static final String API_KEY = "73556748702f69754655714146455a4b715542442f793369347a563964455a57756a4c6b777146354b5337";

	public String shiritori (String word) {
		//ここにしりとり処理を書いていく
		// APIKEY の設定
		AuthApiKey.initializeAuth(API_KEY);

		System.out.println(word);

		// プロキシの設定
		// プロキシを使用しない場合はコメントにしてください
		//ProxyInfo.initializeProxy("proxyhost.co.jp", 80);

		// 雑談対話パラメータクラスを生成して、質問を設定する
		DialogueRequestParam param = new DialogueRequestParam();
		// ユーザの発話
		param.setUtt(word);

		// 雑談対話クラスの生成して、リクエストを実行する
		Dialogue dialogue = new Dialogue();
		DialogueResultData resultData = new DialogueResultData();
		try {
	 		resultData = dialogue.request(param, "ACCESS_TOKEN");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultData.getUtt();
	}
}
