/*
 * Copyright 2005 Joe Walker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dwrdemo.reverseajax.chat;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

/**
 * A {@link ScriptSessionFilter} that only allows {@link ScriptSession}s that
 * match the given session id.
 * 
 * @author Joe Walker [joe at getahead dot ltd dot uk]
 */
public class RoomScriptSessionFilter implements ScriptSessionFilter {
	public static final String ROOM_NAME = "room";

	public RoomScriptSessionFilter(String room) {
		this.room = room;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.directwebremoting.ScriptSessionFilter#match(org.directwebremoting
	 * .ScriptSession)
	 */
	public boolean match(ScriptSession session) {
		boolean result = room.equals(session.getAttribute(ROOM_NAME));
		return result;
	}

	private final String room;
}
