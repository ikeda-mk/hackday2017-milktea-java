package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class ShiritoriResponseDto {

	// APIからの返却テキスト
	private String resText = "";

	// 会話ID
	private String talkId = "";

	public String getResText() {
		return resText;
	}

	public void setResText(String resText) {
		this.resText = resText;
	}

	public String getTalkId() {
		return talkId;
	}

	public void setTalkId(String talkId) {
		this.talkId = talkId;
	}

}
