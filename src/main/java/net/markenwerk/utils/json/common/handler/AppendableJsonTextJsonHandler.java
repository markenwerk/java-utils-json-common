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

import net.markenwerk.utils.text.indentation.Indentation;

/**
 * A {@link AbstractAppandingJsonTextJsonHandler} is a {@link JsonHandler} that
 * appends the handled JSON document as a pretty JSON text to a given
 * {@link Appendable} and calculates no result.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class AppendableJsonTextJsonHandler extends AbstractAppandingJsonTextJsonHandler<Appendable, Void> {

	/**
	 * Creates a new {@link AppendableJsonTextJsonHandler} using the
	 * {@link Indentation#DEFAULT default} Indentation.
	 * 
	 * @param appendable
	 *            The {@link Appendable} to be used.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Appendable} is {@literal null}.
	 */
	public AppendableJsonTextJsonHandler(Appendable appendable) throws IllegalArgumentException {
		this(appendable, Indentation.DEFAULT);
	}

	/**
	 * Creates a new {@link AppendableJsonTextJsonHandler}.
	 * 
	 * @param appendable
	 *            The {@link Appendable} to be used.
	 * @param indentation
	 *            The {@link Indentation} to be used.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Appendable} is {@literal null} or if the
	 *             given {@link Indentation} is {@literal null}.
	 */
	public AppendableJsonTextJsonHandler(Appendable appendable, Indentation indentation) {
		super(appendable, indentation);
	}

	@Override
	public Void getResult() {
		return null;
	}

}
