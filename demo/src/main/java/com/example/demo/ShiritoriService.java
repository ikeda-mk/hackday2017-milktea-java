package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class ShiritoriService {

	private static final String API_KEY = "fcQWt03bJZ6BNq8RqZQXP25I20d5n8r4I8WlW7Pe";
	// RestTemplate はスレッドセーフ
	private final RestTemplate rt = new RestTemplate();

	// 外部サービスの URL
	private final String url = "https://api.repl-ai.jp/v1/dialogue";

	// 外部サービスの JSON を、そのまま（JSON のまま）返却。
	@RequestMapping(value="/ex/exchange")
	public ResponseEntity<String> exchange() {
		return rt.exchange(url, HttpMethod.POST, null, String.class);
	}
	public String srtr (String word) {
		//ここにしりとり処理を書いていく

		// APIKEY の設定
		AuthApiKey.initializeAuth("APIKEY");
		return "test";
	}
}
