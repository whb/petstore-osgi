package com.dwrdemo.reverseajax.chat;

import org.directwebremoting.Security;

public class Message {
	public Message(String newtext) {
		text = newtext;
		if (text.length() > 256) {
			text = text.substring(0, 256);
		}
		text = Security.replaceXmlCharacters(text);
	}

	public long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	private long id = System.currentTimeMillis();

	private String text;
}
