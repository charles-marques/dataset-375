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
package net.dfranek.typoscript.parser;

import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.text.Document;
import net.dfranek.typoscript.lexer.TSLexerUtils;
import net.dfranek.typoscript.lexer.TSTokenId;
import net.dfranek.typoscript.parser.ast.TSASTNode;
import net.dfranek.typoscript.parser.ast.TSASTNodeType;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenId;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.modules.csl.api.*;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.Source;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Daniel Franek
 */
public class TSStructureScanner implements StructureScanner {

	private static final String FOLD_BLOCKS = "codeblocks";
	private static final String FOLD_COMMENTS = "comments"; //NOI18N
	private static final String FONT_GRAY_COLOR = "<font color=\"#999999\">"; //NOI18N
	private static final String CLOSE_FONT = "</font>";                   //NOI18N

	public List<? extends StructureItem> scan(ParserResult pr) {
		List<? extends StructureItem> items;
		TSASTNode root = ((TSParserResult) pr).getTree();
		items = buildHierarchy(root);
		return items;
	}

	protected List<? extends StructureItem> buildHierarchy(TSASTNode tree) {
		final List<StructureItem> items = new ArrayList<StructureItem>();
		for (Iterator<TSASTNode> it = tree.getChildren().iterator(); it.hasNext();) {
			TSASTNode node = it.next();
			List<? extends StructureItem> itemsSub = null;
			if (node.hasChildren()) {
				itemsSub = buildHierarchy(node);
			}

			TSStructureItem sItem;
			if (node.getType() != TSASTNodeType.VALUE && node.getType() != TSASTNodeType.UNKNOWN && node.getType() != TSASTNodeType.COPIED_PROPERTY && node.getType() != TSASTNodeType.CLEARED_PROPERY) {
				sItem = new TSTypedStructureItem(node, itemsSub, "");
			} else if (node.getType() == TSASTNodeType.CLEARED_PROPERY) {
				sItem = new TSClearedStructureItem(node, itemsSub, "");
			} else if (node.getType() == TSASTNodeType.COPIED_PROPERTY) {
				sItem = new TSCopiedStructureItem(node, itemsSub, "");
			} else {
				sItem = new TSValueStructureItem(node, itemsSub, "");
			}
			items.add(sItem);
		}

		return items;
	}

	public Map<String, List<OffsetRange>> folds(ParserResult pr) {
		final Map<String, List<OffsetRange>> folds = new HashMap<String, List<OffsetRange>>();
		TSParserResult tpr = (TSParserResult) pr;
		TokenSequence<? extends TSTokenId> ts = tpr.getSequence();
		ts.moveStart();
		if (ts != null) {
			List<OffsetRange> blocks = tpr.getCodeBlocks();
			for (Iterator<OffsetRange> it = blocks.iterator(); it.hasNext();) {
				OffsetRange offsetRange = it.next();
				ts.move(offsetRange.getStart());
				if (ts.moveNext()) {
					Token<? extends TSTokenId> token = ts.token();
					TokenId id = token.id();

					OffsetRange r = null;
					String kind = "";
					int offset = ts.offset();
					if (TSLexerUtils.textEquals(token.text(), '(')) {
						r = TSLexerUtils.findFwd(ts, TSTokenId.TS_PARANTHESE, '(', TSTokenId.TS_PARANTHESE, ')');
						r = new OffsetRange(offset, r.getEnd());
						kind = FOLD_BLOCKS;
					} else if (TSLexerUtils.textEquals(token.text(), ')')) {
						r = TSLexerUtils.findBwd(ts, TSTokenId.TS_PARANTHESE, '(', TSTokenId.TS_PARANTHESE, ')');
						r = new OffsetRange(offset, r.getEnd());
						kind = FOLD_BLOCKS;
					} else if (id == TSTokenId.TS_CURLY_OPEN) {
						r = TSLexerUtils.findFwd(ts, TSTokenId.TS_CURLY_OPEN, '{', TSTokenId.TS_CURLY_CLOSE, '}');
						r = new OffsetRange(offset, r.getEnd());
						kind = FOLD_BLOCKS;
					} else if (id == TSTokenId.TS_CURLY_CLOSE) {
						r = TSLexerUtils.findBwd(ts, TSTokenId.TS_CURLY_OPEN, '{', TSTokenId.TS_CURLY_CLOSE, '}');
						r = new OffsetRange(offset, r.getEnd());
						kind = FOLD_BLOCKS;
					} else if (id == TSTokenId.TS_CONDITION) {
						r = TSLexerUtils.findNextStartsWith(ts, TSTokenId.TS_CONDITION, "[global]");
						if (r == OffsetRange.NONE) {
							r = null;
						} else {
							r = new OffsetRange(offset, r.getEnd());
						}
						kind = FOLD_BLOCKS;
					} else if (id == TSTokenId.TS_MULTILINE_COMMENT) {
						r = TSLexerUtils.findNextEndsWith(ts, TSTokenId.TS_MULTILINE_COMMENT, "*/");
						if (r == OffsetRange.NONE) {
							r = null;
						} else {
							r = new OffsetRange(offset, r.getEnd());
						}
						kind = FOLD_COMMENTS;
					}/*
					 * else if(id == TSTokenId.TS_COMMENT &&
					 * token.text().charAt(0) == '#') { offset = ts.offset(); r
					 * = TSLexerUtils.findFwd(ts, TSTokenId.TS_COMMENT, '#',
					 * TSTokenId.TS_COMMENT, '#'); r = new OffsetRange(offset,
					 * r.getEnd()); kind = FOLD_COMMENTS; }
					 */
					if (r != null) {
						getRanges(folds, kind).add(r);
					}
				}
			}

			Source source = pr.getSnapshot().getSource();
			assert source != null : "source was null";
			Document doc = source.getDocument(false);

			if (doc != null) {
				doc.putProperty(FOLD_BLOCKS, folds);
			}

		}

		return folds;
	}

	private List<OffsetRange> getRanges(Map<String, List<OffsetRange>> folds, String kind) {
		List<OffsetRange> ranges = folds.get(kind);
		if (ranges == null) {
			ranges = new ArrayList<OffsetRange>();
			folds.put(kind, ranges);
		}
		return ranges;
	}

	public Configuration getConfiguration() {
		return new Configuration(true, true);
	}

	abstract private class TSStructureItem implements StructureItem {

		final protected TSASTNode node;
		final protected List<? extends StructureItem> children;
		final protected String sortPrefix;     //NOI18N

		public TSStructureItem(TSASTNode node, List<? extends StructureItem> children, String sortPrefix) {
			this.sortPrefix = sortPrefix;
			this.node = node;
			if (children != null) {
				this.children = children;
			} else {
				this.children = Collections.emptyList();
			}
		}

		public String getName() {
			return node.getName();
		}

		public String getSortText() {
			return sortPrefix + this.getName();
		}

		public ElementHandle getElementHandle() {
			return null;
		}

		public ElementKind getKind() {
			return ElementKind.PROPERTY;
		}

		public Set<Modifier> getModifiers() {
			return null;
		}

		public boolean isLeaf() {
			return (children.size() == 0);
		}

		public List<? extends StructureItem> getNestedItems() {
			return children;
		}

		public long getPosition() {
			return node.getOffset();
		}

		public long getEndPosition() {
			return node.getOffset() + node.getLength();
		}

		public abstract ImageIcon getCustomIcon();

		@Override
		public String getHtml(HtmlFormatter hf) {
			hf.reset();
			hf.appendText(getName());
			return hf.getText();
		}
	}

	private class TSValueStructureItem extends TSStructureItem {

		public TSValueStructureItem(TSASTNode node, List<? extends StructureItem> children, String sortPrefix) {
			super(node, children, sortPrefix);
		}

		@Override
		public ImageIcon getCustomIcon() {
			return new ImageIcon(ImageUtilities.loadImage("net/dfranek/typoscript/resources/value.png"));
		}

		@Override
		public String getHtml(HtmlFormatter hf) {
			super.getHtml(hf);
			if (!node.getValue().equals("")) {
				hf.appendHtml(FONT_GRAY_COLOR + " = ");
				String nodeValue = node.getValue();
				if (nodeValue.length() > 45) {
					nodeValue = nodeValue.substring(0, 45) + "...";
				}
				hf.appendText(nodeValue);
				hf.appendHtml(CLOSE_FONT);
			}

			return hf.getText();
		}
	}

	private class TSTypedStructureItem extends TSStructureItem {

		public TSTypedStructureItem(TSASTNode node, List<? extends StructureItem> children, String sortPrefix) {
			super(node, children, sortPrefix);
		}

		@Override
		public ImageIcon getCustomIcon() {
			return new ImageIcon(ImageUtilities.loadImage("net/dfranek/typoscript/resources/type.png"));
		}

		@Override
		public String getHtml(HtmlFormatter hf) {
			super.getHtml(hf);
			hf.appendHtml(FONT_GRAY_COLOR + " : ");
			hf.appendText(node.getType().toString());
			hf.appendHtml(CLOSE_FONT);
			return hf.getText();
		}
	}

	private class TSCopiedStructureItem extends TSStructureItem {

		public TSCopiedStructureItem(TSASTNode node, List<? extends StructureItem> children, String sortPrefix) {
			super(node, children, sortPrefix);
		}

		@Override
		public ImageIcon getCustomIcon() {
			return new ImageIcon(ImageUtilities.loadImage("net/dfranek/typoscript/resources/value.png"));
		}

		@Override
		public String getHtml(HtmlFormatter hf) {
			super.getHtml(hf);
			hf.appendHtml(FONT_GRAY_COLOR + " &lt; ");
			hf.appendText(node.getValue());
			hf.appendHtml(CLOSE_FONT);
			return hf.getText();
		}
	}

	private class TSClearedStructureItem extends TSStructureItem {

		public TSClearedStructureItem(TSASTNode node, List<? extends StructureItem> children, String sortPrefix) {
			super(node, children, sortPrefix);
		}

		@Override
		public ImageIcon getCustomIcon() {
			return new ImageIcon(ImageUtilities.loadImage("net/dfranek/typoscript/resources/value.png"));
		}

		@Override
		public String getHtml(HtmlFormatter hf) {
			super.getHtml(hf);
			hf.appendHtml(FONT_GRAY_COLOR + " &gt;");
			hf.appendHtml(CLOSE_FONT);
			return hf.getText();
		}
	}
}
