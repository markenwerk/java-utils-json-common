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
package net.markenwerk.utils.json.common;

/**
 * A {@link InvalidJsonIndexException} is a {@link JsonException} indicates that
 * a given index value is not valid for a given JSON structure.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class InvalidJsonIndexException extends JsonException {

	private static final long serialVersionUID = -224463970528325073L;

	/**
	 * Creates a new {@link InvalidJsonIndexException} with the given message
	 * and cause.
	 *
	 * @param message
	 *            The message.
	 * @param cause
	 *            The cause of this {@link InvalidJsonIndexException}.
	 */
	public InvalidJsonIndexException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Creates a new {@link InvalidJsonIndexException} with the given message.
	 *
	 * @param message
	 *            The message.
	 */
	public InvalidJsonIndexException(String message) {
		super(message);
	}

	/**
	 * Creates a new {@link InvalidJsonIndexException} with the given cause.
	 *
	 * @param cause
	 *            The cause of this {@link InvalidJsonIndexException}.
	 */
	public InvalidJsonIndexException(Throwable cause) {
		super(null == cause ? null : cause.getMessage(), cause);
	}

}
