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
 * A {@link FailedJsonOperationException} is a {@link JsonException} that indicates
 * that a JSON related operation couldn't be performed because the JSON related
 * object is not in the correct state for that operation.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public class FailedJsonOperationException extends JsonException {

	private static final long serialVersionUID = -224463970528325073L;

	/**
	 * Creates a new {@link FailedJsonOperationException} with the given message
	 * and cause.
	 *
	 * @param message
	 *            The message.
	 * @param cause
	 *            The cause of this {@link FailedJsonOperationException}.
	 */
	public FailedJsonOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Creates a new {@link FailedJsonOperationException} with the given message.
	 *
	 * @param message
	 *            The message.
	 */
	public FailedJsonOperationException(String message) {
		super(message);
	}

	/**
	 * Creates a new {@link FailedJsonOperationException} with the given cause.
	 *
	 * @param cause
	 *            The cause of this {@link FailedJsonOperationException}.
	 */
	public FailedJsonOperationException(Throwable cause) {
		super(null == cause ? null : cause.getMessage(), cause);
	}

}
