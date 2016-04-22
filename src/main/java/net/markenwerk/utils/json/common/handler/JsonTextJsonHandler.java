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
package net.markenwerk.utils.json.common.handler;

import net.markenwerk.utils.json.common.exceptions.InvalidJsonValueException;
import net.markenwerk.utils.json.common.exceptions.JsonHandlingException;
import net.markenwerk.utils.text.indentation.Indentation;

/**
 * A {@link JsonTextJsonHandler} is a {@link JsonHandler} that calculates a
 * pretty JSON text for the handled JSON document as a result.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class JsonTextJsonHandler extends IdleJsonHandler<String> {

	private final StringBuilder builder;

	private final AppandingJsonTextJsonHandler handler;

	/**
	 * Creates a new {@link JsonTextJsonHandler} using the
	 * {@link Indentation#DEFAULT default} Indentation.
	 * 
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Appendable} is {@literal null}.
	 */
	public JsonTextJsonHandler() throws IllegalArgumentException {
		this(Indentation.DEFAULT);
	}

	/**
	 * Creates a new {@link JsonTextJsonHandler}.
	 * 
	 * @param indentation
	 *            The {@link Indentation} to be used.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Appendable} is {@literal null} or if the
	 *             given {@link Indentation} is {@literal null}.
	 */
	public JsonTextJsonHandler(Indentation indentation) {
		builder = new StringBuilder();
		handler = new AppandingJsonTextJsonHandler(builder, indentation);

	}

	@Override
	public void onDocumentBegin() throws JsonHandlingException {
		handler.onDocumentBegin();
	}

	@Override
	public void onDocumentEnd() throws JsonHandlingException {
		handler.onDocumentEnd();
	}

	@Override
	public void onArrayBegin() throws JsonHandlingException {
		handler.onArrayBegin();
	}

	@Override
	public void onArrayEnd() throws JsonHandlingException {
		handler.onArrayEnd();
	}

	@Override
	public void onObjectBegin() throws JsonHandlingException {
		handler.onObjectBegin();
	}

	@Override
	public void onObjectEnd() throws JsonHandlingException {
		handler.onObjectEnd();
	}

	@Override
	public void onName(String name) throws JsonHandlingException {
		handler.onName(name);
	}

	@Override
	public void onNext() throws JsonHandlingException {
		handler.onNext();
	}

	@Override
	public void onNull() throws JsonHandlingException {
		handler.onNull();
	}

	@Override
	public void onBoolean(boolean value) throws JsonHandlingException {
		handler.onBoolean(value);
	}

	@Override
	public void onLong(long value) throws JsonHandlingException {
		handler.onLong(value);
	}

	@Override
	public void onDouble(double value) throws InvalidJsonValueException, JsonHandlingException {
		handler.onDouble(value);
	}

	@Override
	public void onString(String value) throws JsonHandlingException {
		handler.onString(value);
	}

	@Override
	public String getResult() {
		return builder.toString();
	}

}