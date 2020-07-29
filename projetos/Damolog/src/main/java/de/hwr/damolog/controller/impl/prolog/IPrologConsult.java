package de.hwr.damolog.controller.impl.prolog;

import java.util.List;
import java.util.Map;

public interface IPrologConsult {

	public List<Map<String, String>> query(String termName, int[] params, String[] variables);

	public List<Map<String, String>> query(String termName, String[] variables);

	public boolean execute(String t);

}
