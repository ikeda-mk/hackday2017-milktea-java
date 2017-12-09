package com.example.demo;

import org.springframework.stereotype.Service;

import jp.ne.docomo.smt.dev.common.http.AuthApiKey;
import jp.ne.docomo.smt.dev.dialogue.param.DialogueRequestParam;

@Service
public class ShiritoriService {

	private static final String API_KEY = "73556748702f69754655714146455a4b715542442f793369347a563964455a57756a4c6b777146354b5337";

	// 外部サービスの URL
	private final String url = "https://api.repl-ai.jp/v1/dialogue";

	public String shiritori (String word) {
		//ここにしりとり処理を書いていく
		word = "あいうえお";
		// プロキシの設定
		// プロキシを使用しない場合はコメントにしてください
		//ProxyInfo.initializeProxy("proxyhost.co.jp", 80);

		// 雑談対話パラメータクラスを生成して、質問を設定する
		DialogueRequestParam param = new DialogueRequestParam();
		// ユーザの発話
		param.setUtt(word);

		// APIKEY の設定
		AuthApiKey.initializeAuth(API_KEY);
		return "test";
	}
}
