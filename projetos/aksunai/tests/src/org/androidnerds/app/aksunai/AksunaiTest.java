/*
 * Copyright (C) 2009  AndroidNerds.org
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.androidnerds.app.aksunai;

import org.androidnerds.app.aksunai.Aksunai;

import android.test.ActivityInstrumentationTestCase2;

public class AksunaiTest extends ActivityInstrumentationTestCase2<Aksunai> {

	public AksunaiTest() {
		super("org.androidnerds.app.aksunai", Aksunai.class);
	}
	
	public void testOpenMenu() {
		Aksunai a = getActivity();
		a.openOptionsMenu();
		a.closeOptionsMenu();
	}
	
	public void testOpenServer() {
		Aksunai a = getActivity();
		a.connectToServer(1, "irc.freenode.net");
	}
}
