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

import net.markenwerk.utils.json.commons.exceptions.JsonHandlingException;

/**
 * A {@link JsonHandler} provides a collection of callback methods that can be
 * called to describe a JSON document and calculates a result for the described
 * JSON document.
 * 
 * @param <Result>
 *            The result type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public interface JsonHandler<Result> {

	/**
	 * Called when the parsing of a JSON document started.
	 * 
	 * <p>
	 * Implementers should catch any exception and wrap them in a
	 * {@link JsonHandlingException}.
	 * 
	 * @throws JsonHandlingException
	 *             If the handling failed.
	 */
	public void onDocumentBegin() throws JsonHandlingException;

	/**
	 * Called when the parsing of a JSON document ended.
	 * 
	 * <p>
	 * Implementers should catch any exception and wrap them in a
	 * {@link JsonHandlingException}.
	 * 
	 * @throws JsonHandlingException
	 *             If the handling failed.
	 */
	public void onDocumentEnd() throws JsonHandlingException;

	/**
	 * Called when the parsing of a JSON array started. A corresponding call for
	 * the {@link JsonHandler#onArrayEnd() end} must eventually be called.
	 * 
	 * <p>
	 * Implementers should catch any exception and wrap them in a
	 * {@link JsonHandlingException}.
	 * 
	 * @throws JsonHandlingException
	 *             If the handling failed.
	 */
	public void onArrayBegin() throws JsonHandlingException;

	/**
	 * Called when the parsing of a JSON array ended.
	 * 
	 * <p>
	 * Implementers should catch any exception and wrap them in a
	 * {@link JsonHandlingException}.
	 * 
	 * @throws JsonHandlingException
	 *             If the handling failed.
	 */
	public void onArrayEnd() throws JsonHandlingException;

	/**
	 * Called when the parsing of a JSON object started. A corresponding call
	 * for the {@link JsonHandler#onObjectEnd() end} must eventually be called.
	 * 
	 * <p>
	 * Implementers should catch any exception and wrap them in a
	 * {@link JsonHandlingException}.
	 * 
	 * @throws JsonHandlingException
	 *             If the handling failed.
	 */
	public void onObjectBegin() throws JsonHandlingException;

	/**
	 * Called when the parsing of a JSON object ended.
	 * 
	 * <p>
	 * Implementers should catch any exception and wrap them in a
	 * {@link JsonHandlingException}.
	 * 
	 * @throws JsonHandlingException
	 *             If the handling failed.
	 */
	public void onObjectEnd() throws JsonHandlingException;

	/**
	 * Called when the parsing of a JSON object encountered the name for the
	 * next JSON value.
	 * 
	 * @param name
	 *            The name for the next JSON value.
	 * 
	 *            <p>
	 *            Implementers should catch any exception and wrap them in a
	 *            {@link JsonHandlingException}.
	 * 
	 * @throws JsonHandlingException
	 *             If the handling failed.
	 */
	public void onName(String name) throws JsonHandlingException;

	/**
	 * Called when the parsing of a JSON array or JSON object encountered
	 * another element.
	 * 
	 * <p>
	 * Implementers should catch any exception and wrap them in a
	 * {@link JsonHandlingException}.
	 * 
	 * @throws JsonHandlingException
	 *             If the handling failed.
	 */
	public void onNext() throws JsonHandlingException;

	/**
	 * Called when the parsing of a JSON object encountered a JSON null.
	 * 
	 * <p>
	 * Implementers should catch any exception and wrap them in a
	 * {@link JsonHandlingException}.
	 * 
	 * @throws JsonHandlingException
	 *             If the handling failed.
	 */
	public void onNull() throws JsonHandlingException;

	/**
	 * Called when the parsing of a JSON object encountered a JSON boolean.
	 * 
	 * @param value
	 *            The value of the JSON boolean.
	 * 
	 *            <p>
	 *            Implementers should catch any exception and wrap them in a
	 *            {@link JsonHandlingException}.
	 * 
	 * @throws JsonHandlingException
	 *             If the handling failed.
	 */
	public void onBoolean(boolean value) throws JsonHandlingException;

	/**
	 * Called when the parsing of a JSON object encountered a JSON number, that
	 * represents an integer value.
	 * 
	 * @param value
	 *            The value of the JSON number.
	 * 
	 *            <p>
	 *            Implementers should catch any exception and wrap them in a
	 *            {@link JsonHandlingException}.
	 * 
	 * @throws JsonHandlingException
	 *             If the handling failed.
	 */
	public void onLong(long value) throws JsonHandlingException;

	/**
	 * Called when the parsing of a JSON object encountered a JSON number, that
	 * represents a decimal value.
	 * 
	 * @param value
	 *            The value of the JSON number.
	 * 
	 *            <p>
	 *            Implementers should catch any exception and wrap them in a
	 *            {@link JsonHandlingException}.
	 * 
	 * @throws JsonHandlingException
	 *             If the handling failed.
	 */
	public void onDouble(double value) throws JsonHandlingException;

	/**
	 * Called when the parsing of a JSON object encountered a JSON string.
	 * 
	 * @param value
	 *            The value of the JSON string.
	 * 
	 *            <p>
	 *            Implementers should catch any exception and wrap them in a
	 *            {@link JsonHandlingException}.
	 * 
	 * @throws JsonHandlingException
	 *             If the handling failed.
	 */
	public void onString(String value) throws JsonHandlingException;

	/**
	 * Returns the calculated result.
	 * 
	 * @return The calculated result.
	 * 
	 *         <p>
	 *         Implementers should catch any exception and wrap them in a
	 *         {@link JsonHandlingException}.
	 * 
	 * @throws JsonHandlingException
	 *             If the handling failed.
	 */
	public Result getResult() throws JsonHandlingException;

}
