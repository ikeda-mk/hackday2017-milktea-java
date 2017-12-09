package com.example.demo;

public class ShiritoriResponseDto {

	// APIからの返却テキスト
	private String resText = "";

	// 会話ID
	private String talkId = "";

	private ShiritoriResponseDto() {

	}

	public void getInstance (String tesText, String talkId) {
		this.resText = tesText;
		this.talkId = talkId;
	}

}
