package dev;

import gnu.prolog.database.PrologTextLoaderError;
import gnu.prolog.term.AtomTerm;
import gnu.prolog.term.CompoundTerm;
import gnu.prolog.term.CompoundTermTag;
import gnu.prolog.term.IntegerTerm;
import gnu.prolog.term.Term;
import gnu.prolog.term.VariableTerm;
import gnu.prolog.vm.Environment;
import gnu.prolog.vm.Interpreter;
import gnu.prolog.vm.Interpreter.Goal;
import gnu.prolog.vm.PrologCode;
import gnu.prolog.vm.PrologException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.hwr.damolog.controller.impl.prolog.IPrologConsult;

public class PrologConsult implements IPrologConsult {

	// private final String FILE =
	// PrologConsult.class.getClassLoader().getResource("tree.pl").getFile();
	private final String FILE = "C:/Users/Jakob/Dropbox/Programmierung/Damolog/src/main/prolog/tree2.pl";
	private final String FILE_E = "C:/Users/Jakob/Dropbox/Programmierung/Damolog/src/main/prolog/extra.pl";
	private Environment environment;
	private Interpreter interpreter;

	public PrologConsult() {
		environment = new Environment();
		environment.ensureLoaded(AtomTerm.get(FILE_E));
		environment.ensureLoaded(AtomTerm.get(FILE));
		interpreter = environment.createInterpreter();
		environment.runInitialization(interpreter);
	}

	private void debug(Environment env) {
		List<PrologTextLoaderError> errors = env.getLoadingErrors();
		for (PrologTextLoaderError error : errors) {
			error.printStackTrace();
		}
	}

	public static void main(String[] args) {
		IPrologConsult consult = new PrologConsult();
		consult.execute("makeMoves");
		List<Map<String, String>> query = consult.query("nextMoves", new String[] { "X", "Y", "X2", "Y2", "J" });
		query = consult.query("setFigure2", new int[] { 3, 5, 2, 4, 0 }, new String[] {});
		consult.execute("nextTurn");
		// consult.executeCompound(new String[0], new Term[0], 0, new CompoundTerm(AtomTerm.get("nextTurn"), 0));
		query = consult.query("nextMoves", new String[] { "X", "Y", "X2", "Y2", "J" });
		// query = consult.query("nextMoves", new int[] { 0, 2, 1, 3 }, new String[] { "J" });
		query = consult.query("setFigure2", new int[] { 0, 2, 1, 3, 0 }, new String[] {});
		query = consult.query("tempBoard", new String[] { "X" });
		consult.execute("nextTurn");
		query = consult.query("possibleMoves", new String[] { "X", "Y", "X2", "Y2" });
		// query = consult.query("setFigure", new int[] { 0, 2, 1, 3 }, new String[] { "J" });
		// query = consult.query("setFigure", new int[] { 2, 4, 0, 2 }, new String[] { "J" });
		// printMap(query);
	}

	private static void printMap(List<Map<String, String>> query) {
		for (Map<String, String> map : query) {
			for (Entry<String, String> e : map.entrySet()) {
				System.out.print(e.getKey() + " - " + e.getValue() + ";");
			}
			System.out.println();
		}

	}

	/* (non-Javadoc)
	 * @see de.hwr.damolog.controller.impl.prolog.IPrologConsult#query(java.lang.String, int[], java.lang.String[])
	 */
	@Override
	public List<Map<String, String>> query(String termName, int[] params, String[] variables) {
		if (variables == null) {
			variables = new String[0];
		}
		Term[] terms = new Term[variables.length + params.length];
		for (int i = 0; i < params.length; i++) {
			terms[i] = new IntegerTerm(params[i]);
		}
		for (int i = 0; i < variables.length; i++) {
			terms[i + params.length] = new VariableTerm(variables[i]);
		}
		CompoundTerm goalTerm = new CompoundTerm(AtomTerm.get(termName), terms);

		try {
			return executeCompound(variables, terms, params.length, goalTerm);
		} catch (PrologException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see de.hwr.damolog.controller.impl.prolog.IPrologConsult#query(java.lang.String, java.lang.String[])
	 */
	@Override
	public List<Map<String, String>> query(String termName, String[] variables) {
		Term[] params = new Term[variables.length];
		for (int i = 0; i < variables.length; i++) {
			params[i] = new VariableTerm(variables[i]);
		}
		CompoundTerm goalTerm = new CompoundTerm(AtomTerm.get(termName), params);

		try {
			return executeCompound(variables, params, 0, goalTerm);
		} catch (PrologException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	private List<Map<String, String>> executeCompound(String[] variables, Term[] params, int paramCount,
			CompoundTerm goalTerm) throws PrologException {
		debug(environment);
		Goal goal = interpreter.prepareGoal(goalTerm);
		System.out.println(goalTerm.tag);
		int rc;
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		do {
			rc = interpreter.execute(goal);
			// System.out.println(rc);
			if (rc != PrologCode.FAIL) {
				Map<String, String> map = new LinkedHashMap<String, String>(params.length);
				for (int i = paramCount; i < params.length; i++) {
					map.put(variables[i - paramCount], params[i].dereference().toString());
					// System.out.println(params[i].dereference());
				}
				result.add(map);
			}
		} while (rc == PrologCode.SUCCESS && rc != PrologCode.SUCCESS_LAST);
		printMap(result);
		return result;
	}

	/* (non-Javadoc)
	 * @see de.hwr.damolog.controller.impl.prolog.IPrologConsult#execute(java.lang.String)
	 */
	@Override
	public boolean execute(String t) {
		CompoundTerm term = new CompoundTerm(CompoundTermTag.get(t, 0));
		try {
			Goal goal = interpreter.prepareGoal(term);
			System.out.println(t);
			int i = interpreter.execute(goal);
			System.out.println(i);
			return i == 1;
		} catch (PrologException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
