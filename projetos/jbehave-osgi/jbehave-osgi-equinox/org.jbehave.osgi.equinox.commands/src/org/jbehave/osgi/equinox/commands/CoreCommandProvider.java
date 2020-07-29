package org.jbehave.osgi.equinox.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.jbehave.osgi.core.service.EmbedderService;

public class CoreCommandProvider implements
		CommandProvider {

	private EmbedderService embedderService;

	@Override
	public String getHelp() {
		StringBuffer help = new StringBuffer();
		help.append("\r\n--- JBehave Equinox Commands ---\r\n");
		help.append("\tjbStatus - JBehave OSGi EmbedderService status.\r\n");
		help.append("\tjbRunStoriesWithAnnotatedEmbedder - Run Stories via Annotated Embedder.\r\n");
		help.append("\r\n");
		return help.toString();
	}

	public void _jbStatus(CommandInterpreter intp) throws Exception {
		EmbedderService embedderService = getEmbedderService();
		embedderService.showStatus();

	}

	public void _jbRunStoriesWithAnnotatedEmbedder(CommandInterpreter intp) throws Exception {

		List<String> includes = fromCSV(intp.nextArgument());

		EmbedderService embedderService = getEmbedderService();
		if (embedderService.isStarted()) {
			if (!includes.isEmpty()) {
				embedderService.runStoriesWithAnnotatedEmbedderRunner(includes);
			} else {
				intp.println("One or more Annotated Embedder class should be informed.");
			}
		} else {
			intp.println("OSGi Embedder Service isn't started.");
		}
	}

	public EmbedderService getEmbedderService() {
		return embedderService;
	}

	public void setEmbedderService(EmbedderService embedderService) {
		this.embedderService = embedderService;
	}

	public void unsetEmbedderService(EmbedderService embedderService) {
		this.embedderService = null;
	}

	private List<String> fromCSV(String csv) {
		List<String> list = new ArrayList<String>();
		if (csv != null) {
			for (String string : csv.split(",")) {
				if (!string.isEmpty()) {
					list.add(string);
				}
			}
		}
		return list;
	}
}
