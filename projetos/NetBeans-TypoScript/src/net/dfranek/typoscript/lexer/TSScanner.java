/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2011 Daniel Franek.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 * 
 */
package net.dfranek.typoscript.lexer;

import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilderFactory;
import org.netbeans.spi.lexer.LexerInput;
import org.netbeans.spi.lexer.LexerRestartInfo;

public class TSScanner {

	private LexerInput input;
	private int readLength = 0;
	private TSLexerState state;
	private int position = 0;

	/**
	 * Contructor. Sets input and current state.
	 *
	 * @param info information about current document
	 */
	public TSScanner(LexerRestartInfo<TSTokenId> info) {
		this.input = info.input();
		if (info.state() != null) {
			this.state = (TSLexerState) info.state();
		} else {
			this.state = TSLexerState.DEFAULT;
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
	}

	/**
	 * Resumes scanning until the next regular expression is matched, the end of
	 * input is encountered or an I/O-Error occurs.
	 *
	 * @return the next token
	 * @exception java.io.IOException if any I/O-Error occurs
	 */
	public TSTokenId nextToken() throws java.io.IOException {
		TSTokenId token = TSTokenId.UNKNOWN_TOKEN;

		//input.backup(1);
		char ch = (char) input.read();

		if (state == TSLexerState.IN_PARANTHESE && ch != ')') {
			token = readWhileInParanthese();
		} else if (ch == '>') {
			token = TSTokenId.TS_OPERATOR;
		} else if (ch == '\n') {
			token = TSTokenId.TS_NL;
			state = TSLexerState.DEFAULT;
		} else if (state != TSLexerState.IN_VALUE && state == TSLexerState.IN_COMMENT) {
			token = readMultilineComment(ch);
		} else if (isWhiteSpace(ch)) {
			nextWhileWhiteSpace();
			token = TSTokenId.WHITESPACE;
		} else if (state != TSLexerState.IN_VALUE && (ch == '"' || ch == '\'')) {
			nextUntilUnescaped(ch);
			token = TSTokenId.TS_STRING;
		} else if ((((ch == '=' && (char) input.read() != '<')) && (char) input.read() != '\n') && state != TSLexerState.IN_VALUE) { // there must be some value behind the operator!
			state = TSLexerState.IN_VALUE;
			token = TSTokenId.TS_OPERATOR;
			input.backup(1);
			if (ch == '=') {
				input.backup(1);
			}
		} else if (state != TSLexerState.IN_VALUE && ch == '[') {
			nextUntilUnescaped(']');
			token = TSTokenId.TS_CONDITION;
			// with punctuation, the type of the token is the symbol itself
		} else if (state != TSLexerState.IN_VALUE && ch == ')') {
			char next = (char) input.read();
			if (next == '\n') {
				token = TSTokenId.TS_PARANTHESE;
				state = TSLexerState.DEFAULT;
			} else {
				token = TSTokenId.TS_VALUE;
			}
			input.backup(1);
		} else if (state != TSLexerState.IN_VALUE && ch == '(') {
			token = TSTokenId.TS_PARANTHESE;
			state = TSLexerState.IN_PARANTHESE;
		} else if (state != TSLexerState.IN_VALUE && Pattern.matches("[\\[\\]\\(\\),;\\:\\.\\<\\>\\=]", new Character(ch).toString())) {
			token = TSTokenId.TS_OPERATOR;
		} else if (state != TSLexerState.IN_VALUE && (ch == '{' || ch == '}')) {
			token = ch == '{' ? TSTokenId.TS_CURLY_OPEN : TSTokenId.TS_CURLY_CLOSE;
		} else if (state != TSLexerState.IN_VALUE && ch == '0' && ((readAndBackup() == 'x') || (readAndBackup() == 'X'))) {
			token = readHexNumber();
		} else if (state != TSLexerState.IN_VALUE && isDigit(new Character(ch).toString())) {
			token = readNumber();
		} else if (state != TSLexerState.IN_VALUE && ch == '/') {
			char next = (char) input.read();

			if (next == '*') {
				token = readMultilineComment(ch);

			} else if (next == '/') {
				nextUntilUnescaped('\n');
				token = TSTokenId.TS_COMMENT;

			} else if (state == TSLexerState.IN_REGEXP) {
				token = readRegexp();

			} else {
				nextWhileOperatorChar();
				token = TSTokenId.TS_OPERATOR;
			}

		} else if (state != TSLexerState.IN_VALUE && ch == '#') {
			nextUntilUnescaped('\n');
			token = TSTokenId.TS_COMMENT;
		} else if (state != TSLexerState.IN_VALUE && isOperatorChar(new Character(ch).toString())) {
			nextWhileOperatorChar();
			token = TSTokenId.TS_OPERATOR;
		} else {
			String word = nextWhileWordChar(ch);
			token = TSTokenId.TS_PROPERTY;

			// try to find word in xml
			String propertyType = "";
			if (isWord(word, false)) {
				propertyType = TSLexerUtils.getWordFromXML(word);
			}
			
			if (!propertyType.isEmpty()) {
				if (propertyType.equals("object")) {
					token = TSTokenId.TS_OBJECT;
				} else if (propertyType.equals("keyword")) {
					token = TSTokenId.TS_KEYWORD;
				} else if (propertyType.equals("reserved")) {
					token = TSTokenId.TS_RESERVED;
				} else if (propertyType.equals("function")) {
					token = TSTokenId.TS_FUNCTION;
				} else if (propertyType.equals("condition")) {
					token = TSTokenId.TS_CONDITION;
				} else if (propertyType.equals("property")) {
					token = TSTokenId.TS_PROPERTY;
				}
			} else {
				if (isDigit(word)) {
					token = TSTokenId.TS_NUMBER;
				} else if (word.startsWith("{$") && word.endsWith("}")) {
					token = TSTokenId.TS_CONSTANT;
				} else if (word.startsWith("tt_") || word.startsWith("tx_")) {
					token = TSTokenId.TS_EXTENSION;
				} else if (Pattern.matches("[\\[\\]\\(\\),;\\:\\.\\<\\>\\=\\+\\-]", word)) {
					token = TSTokenId.TS_OPERATOR;
				} else if (state == TSLexerState.IN_VALUE) {
					token = TSTokenId.TS_VALUE;
				}
			}



		}

		token = token == null ? TSTokenId.TS_VALUE : token;

		token.setStart(position);
		this.readLength = input.readLength();
		this.position += this.readLength;
		token.setEnd(position);

		return token;
	}

	protected TSTokenId readRegexp() {
		nextUntilUnescaped('/');
		nextWhileMatchesRegExp("[gi]");
		return TSTokenId.TS_REGEXP;
	}

	protected String nextWhileWordChar(char ch) {
		boolean inConstant = false;
		if(ch == '{') inConstant = true;

		// Backing up 1 position to fix bug
		if (ch == '>') {
			input.backup(1);
		}
		if (ch == '=') {
			input.backup(2);
		}

		if (!isWordChar(new Character(ch).toString(),inConstant)) {
			return input.readText().toString();
		}

		StringBuilder sb = new StringBuilder();
		char next;
		while (((next = (char) input.read()) != LexerInput.EOF) && next != '\n' && isWordChar(new Character(next).toString(), inConstant)) {
			sb.append(next);
		}
		
		if(ch != '}' && !inConstant)
			input.backup(1);

		return input.readText().toString();
	}

	protected String nextWhileMatchesRegExp(String pattern) {
		char next;
		while (((next = (char) input.read()) != LexerInput.EOF) && Pattern.matches(pattern, new Character(next).toString())) {
		}
		input.backup(1);
		return input.readText().toString();
	}

	protected TSTokenId readNumber() {
		nextWhileDigit();
		return TSTokenId.TS_NUMBER;
	}

	protected TSTokenId readHexNumber() {
		input.read();
		// skip the 'x'
		nextWhileHexDigit();

		return TSTokenId.TS_NUMBER;
	}

	protected String nextWhileOperatorChar() {
		char next;
		while (((next = (char) input.read()) != LexerInput.EOF) && isOperatorChar(new Character(next).toString())) {
		}
		input.backup(1);
		return input.readText().toString();
	}

	protected String nextWhileDigit() {
		char next;
		while (((next = (char) input.read()) != LexerInput.EOF) && isDigit(new Character(next).toString())) {
		}
		input.backup(1);
		return input.readText().toString();
	}

	protected String nextWhileHexDigit() {
		char next;
		while (((next = (char) input.read()) != LexerInput.EOF) && isHexDigit(new Character(next).toString())) {
		}
		return input.readText().toString();
	}

	protected String nextWhileWhiteSpace() {
		char next;
		while (((next = (char) input.read()) != LexerInput.EOF) && isWhiteSpace(next)) {
		}
		input.backup(1);
		return input.readText().toString();
	}

	protected String nextUntilUnescaped(char end) {
		boolean escaped = false;
		char next = (char) input.read();
		while (next != '\n' && ((next = (char) input.read()) != LexerInput.EOF) && next != '\uffff') {
			if (next == end && !escaped) {
				break;
			}
			escaped = next == '\\';
		}
		if (next == '\n') {
			input.backup(1);
		}
		return input.readText().toString();
	}

	protected TSTokenId readWhileInParanthese() {
		char next;
		while (((next = (char) input.read()) != LexerInput.EOF) && next != ')' && next != '\uffff') {
		}
		input.backup(1);

		return TSTokenId.TS_VALUE;
	}

	protected TSTokenId readMultilineComment(char start) {
		state = TSLexerState.IN_COMMENT;
		boolean maybeEnd = (start == '*');
		while (true) {
			char next = (char) input.read();
			if (next == '\n') {
				break;
			}

			if (next == '/' && maybeEnd) {
				state = TSLexerState.DEFAULT;
				break;
			}
			maybeEnd = (next == '*');
		}

		return TSTokenId.TS_MULTILINE_COMMENT;
	}

	protected boolean isOperatorChar(String input) {
		return Pattern.matches("[\\+\\-\\*\\&\\%\\/=<>!\\?]", input);
	}

	protected boolean isDigit(String input) {
		return Pattern.matches("[0-9]+", input);
	}

	protected boolean isHexDigit(String input) {
		return Pattern.matches("[0-9A-Fa-f]", input);
	}

	protected boolean isWordChar(String input, boolean constant) {
		if(constant)
			return Pattern.matches("[\\w\\$_{\\.\\:]", input);
		else
			return Pattern.matches("[\\w\\$_]", input);
	}

	protected boolean isWord(String input, boolean constant) {
		if(constant)
			return Pattern.matches("[\\w\\$_{\\.\\:]+", input);
		else
			return Pattern.matches("[\\w\\$_]+", input);
	}

	protected boolean isWhiteSpace(char ch) {
		return ch != '\n' && (ch == ' ' || Pattern.matches("\\s", new Character(ch).toString()));
	}

	/**
	 * Returns the length that has been read by nextToken()
	 *
	 * @return the readLength
	 */
	public int getReadLength() {
		return readLength;
	}

	/**
	 * Returns the current state of the scanner
	 *
	 * @return the current state
	 */
	public TSLexerState getState() {
		return state;
	}
	
	public int readAndBackup() {
		int c = input.read();
		input.backup(1);
		return c;
	}
}
