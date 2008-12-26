package com.dwrdemo.reverseajax.chat;

import java.util.LinkedList;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.WebContextFactory;

public class JavaChat {
	public void setRoom(String room) {
		ScriptSession session = WebContextFactory.get().getScriptSession();
		session.setAttribute(RoomScriptSessionFilter.ROOM_NAME, room);
	}

	public void addMessage(String text) {
		if (text != null && text.trim().length() > 0) {
			messages.addFirst(new Message(text));
			while (messages.size() > 10) {
				messages.removeLast();
			}
		}
		// Reverse Ajax code to be added here shortly
		ScriptSession session = WebContextFactory.get().getScriptSession();
		String room = (String) session
				.getAttribute(RoomScriptSessionFilter.ROOM_NAME);
		if (room != null && room.trim().length() > 0) {
			ScriptSessionFilter filter = new RoomScriptSessionFilter(room);
			Browser.withCurrentPageFiltered(filter, updatePage());
		} else {
			Browser.withCurrentPage(updatePage());
		}
	}

	private Runnable updatePage() {
		return new Runnable() {
			public void run() {
				MyUtil.setValue("text", "");
				MyUtil.removeAllOptions("chatlog");
				MyUtil.addOptions("chatlog", messages, "text");
			}
		};
	}

	private static LinkedList<Message> messages = new LinkedList<Message>();
}
