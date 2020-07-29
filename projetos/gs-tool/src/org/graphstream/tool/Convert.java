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

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.graphstream.stream.file.FileSink;
import org.graphstream.stream.file.FileSource;

/**
 * A tool to convert from various formats to various formats...
 * 
 * @author Guilhelm Savin
 */
public class Convert extends Tool {

	public Convert() {
		super("convert", "", true, true);
	}

	/*
	 * (non-Javadoc)
	 * @see org.graphstream.tool.Tool#getDomain()
	 */
	public String getDomain() {
		return "org.graphstream.tool.i18n.convert";
	}
	
	public void run() throws ToolExecutionException {
		FileSource source = getSource(SourceFormat.DGS);
		FileSink sink = getSink(SinkFormat.DGS);
		Reader input = getInput();
		Writer output = getOutput();

		source.addSink(sink);

		try {
			sink.begin(output);
			source.begin(input);
			while (source.nextStep())
				;
			source.end();
			sink.end();
		} catch (IOException e) {
			throw new ToolExecutionException(e, "%s", i18n("exception:io"));
		}

		source.removeSink(sink);
	}

	public static void main(String... args) {
		Convert conv = new Convert();
		
		ToolRunner runner = new ToolRunner(conv, args);
		runner.addListener(conv);
		
		try {
			runner.start().waitEndOfExecution();
		} catch (InterruptedException e) {
			// Ignore
		}
	}

}
