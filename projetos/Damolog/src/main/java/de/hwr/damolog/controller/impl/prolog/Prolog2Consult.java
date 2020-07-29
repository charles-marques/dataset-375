package de.hwr.damolog.controller.impl.prolog;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jpl.Atom;
import jpl.Integer;
import jpl.Query;
import jpl.Term;
import jpl.Variable;

public class Prolog2Consult implements IPrologConsult {

	private String FILE = "prolog/tree2.pl";// Prolog2Consult.class.getResource("/prolog/tree2.pl").getFile();

	public Prolog2Consult() {
		File f = new File(FILE);
		FILE = f.getAbsolutePath();
		Query q1 = new Query("consult", new Term[] { new Atom(FILE) });
		q1.oneSolution();
	}

	@Override
	public List<Map<String, String>> query(String termName, int[] params, String[] variables) {
		if (variables == null) {
			variables = new String[0];
		}
		Term[] terms = new Term[variables.length + params.length];
		for (int i = 0; i < params.length; i++) {
			terms[i] = new Integer(params[i]);
		}
		for (int i = 0; i < variables.length; i++) {
			terms[i + params.length] = new Variable(variables[i]);
		}
		Query query = new Query(termName, terms);

		return internalExecute(variables, query);
	}

	@Override
	public List<Map<String, String>> query(String termName, String[] variables) {
		Term[] params = new Term[variables.length];
		for (int i = 0; i < variables.length; i++) {
			params[i] = new Variable(variables[i]);
		}
		Query query = new Query(termName, params);

		return internalExecute(variables, query);
	}

	@Override
	public boolean execute(String t) {
		System.out.println(t);
		boolean hasSolution = new Query(t).hasSolution();
		return hasSolution;
	}

	private List<Map<String, String>> internalExecute(String[] variables, Query q) {
		System.out.println(q);
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		while (q.hasMoreElements()) {
			Map<String, String> map = new LinkedHashMap<String, String>(variables.length);
			@SuppressWarnings ("unchecked")
			Hashtable<String, Term> binding = q.nextSolution();
			for (String string : variables) {
				map.put(string, binding.get(string).toString());
			}
			result.add(map);
		}
		return result;
	}

	public static void printMap(List<Map<String, String>> query) {
		for (Map<String, String> map : query) {
			for (Entry<String, String> e : map.entrySet()) {
				System.out.print(e.getKey() + " - " + e.getValue() + ";");
			}
			System.out.println();
		}

	}
}
