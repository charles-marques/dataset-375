/*
 * Copyright 2006 - 2011 
 *     Stefan Balev 	<stefan.balev@graphstream-project.org>
 *     Julien Baudry	<julien.baudry@graphstream-project.org>
 *     Antoine Dutot	<antoine.dutot@graphstream-project.org>
 *     Yoann Pigné		<yoann.pigne@graphstream-project.org>
 *     Guilhelm Savin	<guilhelm.savin@graphstream-project.org>
 * 
 * This file is part of GraphStream <http://graphstream-project.org>.
 * 
 * GraphStream is a library whose purpose is to handle static or dynamic
 * graph, create them from scratch, file or any source and display them.
 * 
 * This program is free software distributed under the terms of two licenses, the
 * CeCILL-C license that fits European law, and the GNU Lesser General Public
 * License. You can  use, modify and/ or redistribute the software under the terms
 * of the CeCILL-C license as circulated by CEA, CNRS and INRIA at the following
 * URL <http://www.cecill.info> or under the terms of the GNU LGPL as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL-C and LGPL licenses and that you accept their terms.
 */
package org.graphstream.tool;

import java.util.LinkedList;

public class ToolRunner {
	protected LinkedList<ToolRunnerListener> listeners;
	protected Tool tool;
	protected String[] args;
	private Thread t;

	public ToolRunner(Tool tool, String... args) {
		this.tool = tool;
		this.args = args;
		this.listeners = new LinkedList<ToolRunnerListener>();
	}

	public ToolRunner start() {
		if (t != null) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// Ignore
			}

			t = null;
		}

		t = new Thread(new ToolRunnable(), tool.getName() + "-runner");
		t.start();
		
		return this;
	}

	public void waitEndOfExecution() throws InterruptedException {
		if (t != null)
			t.join();
	}

	public void addListener(ToolRunnerListener l) {
		listeners.add(l);
	}
	
	public void removeListener(ToolRunnerListener l) {
		listeners.remove(l);
	}
	
	private void executionStart() {
		for(int i=0; i<listeners.size(); i++)
			listeners.get(i).executionStart(tool);
	}
	
	private void initializationFailed(ToolInitializationException e) {
		for(int i=0; i<listeners.size(); i++)
			listeners.get(i).initializationFailed(tool, e);
	}

	private void executionFailed(ToolExecutionException e) {
		for(int i=0; i<listeners.size(); i++)
			listeners.get(i).executionFailed(tool, e);
	}

	private void executionSuccess() {
		for(int i=0; i<listeners.size(); i++)
			listeners.get(i).executionSuccess(tool);
	}

	private class ToolRunnable implements Runnable {
		public void run() {
			executionStart();
			
			try {
				tool.init(args);
			} catch (ToolInitializationException e) {
				initializationFailed(e);
				return;
			}

			try {
				tool.run();
			} catch (ToolExecutionException e) {
				executionFailed(e);
				return;
			}

			executionSuccess();
		}
	}
}
