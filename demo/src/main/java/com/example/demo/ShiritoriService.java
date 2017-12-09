package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class ShiritoriService {

	private static final String API_KEY = "fcQWt03bJZ6BNq8RqZQXP25I20d5n8r4I8WlW7Pe";

	// 外部サービスの URL
	private final String url = "https://api.repl-ai.jp/v1/dialogue";

	public String shiritori () {
		//ここにしりとり処理を書いていく
		String word = "あいうえお";
		// プロキシの設定
		// プロキシを使用しない場合はコメントにしてください
		//ProxyInfo.initializeProxy("proxyhost.co.jp", 80);

		// 雑談対話パラメータクラスを生成して、質問を設定する
		DialogueRequestParam param = new DialogueRequestParam();

		// APIKEY の設定
		AuthApiKey.initializeAuth("APIKEY");
		return "test";
	}
}
