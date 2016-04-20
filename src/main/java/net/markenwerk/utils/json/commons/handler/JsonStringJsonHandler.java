/*
 * Copyright (c) 2016 Torsten Krause, Markenwerk GmbH
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.markenwerk.utils.json.commons.handler;

import java.io.IOException;
import java.io.Writer;

import net.markenwerk.utils.json.commons.exceptions.JsonHandlingException;
import net.markenwerk.utils.text.indentation.Indentation;

/**
 * A {@link JsonStringJsonHandler} is a {@link JsonHandler} that writes the
 * handled JSON document as a JSON string into a {@link Writer}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class JsonStringJsonHandler implements JsonHandler<Void> {

	private final Appendable appendable;

	private final Indentation indentation;

	private int depth;

	private boolean indented = true;

	private boolean empty;

	public JsonStringJsonHandler(Appendable appendable) {
		this(appendable, Indentation.DEFAULT);
	}

	public JsonStringJsonHandler(Appendable appendable, Indentation indentation) {
		if (null == appendable) {
			throw new IllegalArgumentException("writer is null");
		}
		if (null == indentation) {
			throw new IllegalArgumentException("indentation is null");
		}
		this.appendable = appendable;
		this.indentation = this.indentation;
	}

	@Override
	public void onDocumentBegin() {
	}

	@Override
	public void onDocumentEnd() {
	}

	@Override
	public void onArrayBegin() throws JsonHandlingException {
		writeIndentation();
		writeUnescaped("[");
		depth++;
		empty = true;
	}

	@Override
	public void onArrayEnd() throws JsonHandlingException {
		depth--;
		if (!empty) {
			writeIndentation();
		}
		empty = false;
		writeUnescaped("]");
	}

	@Override
	public void onObjectBegin() throws JsonHandlingException {
		writeIndentation();
		writeUnescaped("{");
		depth++;
		empty = true;
	}

	@Override
	public void onObjectEnd() throws JsonHandlingException {
		depth--;
		if (!empty) {
			writeIndentation();
		}
		empty = false;
		writeUnescaped("}");
	}

	@Override
	public void onName(String name) throws JsonHandlingException {
		writeUnescaped("\n");
		writeUnescaped(indentation.getIndentationString(depth));
		indented = true;
		writeUnescaped("\"");
		writeEscaped(name);
		writeUnescaped("\":");
		if (indentation.isVisible()) {
			writeUnescaped(" ");
		}
	}

	@Override
	public void onNext() throws JsonHandlingException {
		writeUnescaped(",");
	}

	@Override
	public void onNull() throws JsonHandlingException {
		writeIndentation();
		writeUnescaped("null");
	}

	@Override
	public void onBoolean(boolean value) throws JsonHandlingException {
		writeIndentation();
		writeUnescaped(value ? "true" : "false");
	}

	@Override
	public void onLong(long value) throws JsonHandlingException {
		writeIndentation();
		writeUnescaped(Long.toString(value));
	}

	@Override
	public void onDouble(double value) throws JsonHandlingException {
		writeIndentation();
		writeUnescaped(Double.toString(value));
	}

	@Override
	public void onString(String value) throws JsonHandlingException {
		writeIndentation();
		writeUnescaped("\"");
		writeEscaped(value);
		writeUnescaped("\"");
	}

	private void writeIndentation() throws JsonHandlingException {
		if (!indented) {
			writeUnescaped("\n");
			writeUnescaped(indentation.getIndentationString(depth));
		}
		indented = false;
		empty = false;
	}

	private void writeUnescaped(String string) throws JsonHandlingException {
		try {
			appendable.append(string);
		} catch (IOException e) {
			throw new JsonHandlingException(e);
		}
	}

	private void writeEscaped(String string) throws JsonHandlingException {
		try {
			for (int i = 0, n = string.length(); i < n; i++) {
				char character = string.charAt(i);
				switch (character) {
				case '"':
					appendable.append("\\\"");
					break;
				case '\\':
					appendable.append("\\\\");
					break;
				case '/':
					appendable.append("\\/");
					break;
				case '\r':
					appendable.append("\\r");
					break;
				case '\b':
					appendable.append("\\b");
					break;
				case '\n':
					appendable.append("\\n");
					break;
				case '\t':
					appendable.append("\\t");
					break;
				case '\f':
					appendable.append("\\f");
					break;
				default:
					if (character < ' ') {
						appendable.append("\\u");
						String hexString = Integer.toString(character, 16);
						for (int j = 0, m = hexString.length(); j < m; j++) {
							appendable.append("0");

						}
						appendable.append(hexString);
						appendable.append(hexString);
					} else {
						appendable.append(Character.toString(character));
					}
				}
			}
		} catch (IOException e) {
			throw new JsonHandlingException(e);
		}
	}

	@Override
	public Void getResult() {
		return null;
	}

}
