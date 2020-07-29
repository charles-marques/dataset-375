// Modified or written by Luca Marrocco for inclusion with hoptoad.
// Copyright (c) 2009 Luca Marrocco.
// Licensed under the Apache License, Version 2.0 (the "License")

package hoptoad;

public class ValidBacktraces {

	public static boolean isValidBacktrace(String string) {
		return string.matches("[^:]*:\\d+.*");
	}

	public static boolean notValidBacktrace(String string) {
		return !isValidBacktrace(string);
	}

}
