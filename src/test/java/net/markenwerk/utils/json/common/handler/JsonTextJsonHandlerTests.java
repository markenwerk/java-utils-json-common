package net.markenwerk.utils.json.common.handler;

import org.junit.Assert;
import org.junit.Test;

import net.markenwerk.utils.json.common.exceptions.InvalidJsonValueException;
import net.markenwerk.utils.text.indentation.Whitespace;
import net.markenwerk.utils.text.indentation.WhitespaceIndentation;

@SuppressWarnings("javadoc")
public class JsonTextJsonHandlerTests {

	private static final WhitespaceIndentation INDENTATION = new WhitespaceIndentation(Whitespace.SPACE, 0);

	@Test(expected = IllegalArgumentException.class)
	public void create_nullIndentation() {

		new JsonTextJsonHandler(null);

	}

	@Test
	public void onNull() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onNull();
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals("null", result);

	}

	@Test
	public void onBoolean_true() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onBoolean(true);
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals("true", result);

	}

	@Test
	public void onBoolean_false() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onBoolean(false);
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals("false", result);

	}

	@Test
	public void onLong_zero() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onLong(0);
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals(Long.toString(0), result);

	}

	@Test
	public void onLong_positive() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onLong(Long.MAX_VALUE);
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals(Long.toString(Long.MAX_VALUE), result);

	}

	@Test
	public void onLong_negative() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onLong(Long.MIN_VALUE);
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals(Long.toString(Long.MIN_VALUE), result);

	}

	@Test(expected = InvalidJsonValueException.class)
	public void onDouble_infinite() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onDouble(Double.POSITIVE_INFINITY);
		handler.onDocumentEnd();

	}

	@Test(expected = InvalidJsonValueException.class)
	public void onDouble_notANumber() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onDouble(Double.NaN);
		handler.onDocumentEnd();

	}

	@Test
	public void onDouble_zero() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onDouble(0);
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals(Double.toString(0), result);

	}

	@Test
	public void onDouble_positive() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onDouble(Double.MAX_VALUE);
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals(Double.toString(Double.MAX_VALUE), result);

	}

	@Test
	public void onDouble_negative() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onDouble(Double.MIN_VALUE);
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals(Double.toString(Double.MIN_VALUE), result);

	}

	@Test(expected = InvalidJsonValueException.class)
	public void onString_null() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onString(null);
		handler.onDocumentEnd();

	}

	@Test
	public void onString_empty() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onString("");
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals("\"\"", result);

	}

	@Test
	public void onString_nonEmpty() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onString("foobar");
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals("\"foobar\"", result);

	}

	@Test
	public void onString_escapeSequances() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onString("__\"_\\_/_\b_\f_\n_\r_\t__");
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals("\"__\\\"_\\\\_\\/_\\b_\\f_\\n_\\r_\\t__\"", result);

	}

	@Test
	public void onString_unicideEscapeSequances() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onString("𝄞");
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals("\"\uD834\uDD1E\"", result);

	}

	@Test
	public void onArray_empty() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onArrayBegin();
		handler.onArrayEnd();
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals("[]", result);

	}

	@Test
	public void onArray_nonEmpty() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onArrayBegin();
		handler.onNull();
		handler.onArrayEnd();
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals("[null]", result);

	}

	@Test
	public void onObject_empty() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onObjectBegin();
		handler.onObjectEnd();
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals("{}", result);

	}

	@Test
	public void onObject_nonEmpty() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onObjectBegin();
		handler.onName("n");
		handler.onNull();
		handler.onObjectEnd();
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals("{\"n\":null}", result);

	}

	@Test
	public void onDocument_complex() {

		JsonHandler<String> handler = new JsonTextJsonHandler(INDENTATION);

		handler.onDocumentBegin();
		handler.onObjectBegin();
		handler.onName("n");
		handler.onNull();
		handler.onNext();
		handler.onName("b");
		handler.onBoolean(true);
		handler.onNext();
		handler.onName("l");
		handler.onLong(-42);
		handler.onNext();
		handler.onName("d");
		handler.onDouble(-23.42);
		handler.onNext();
		handler.onName("a");
		handler.onArrayBegin();
		handler.onString("foo");
		handler.onNext();
		handler.onString("bar");
		handler.onArrayEnd();
		handler.onObjectEnd();
		handler.onDocumentEnd();

		String result = handler.getResult();

		Assert.assertEquals("{\"n\":null,\"b\":true,\"l\":-42,\"d\":-23.42,\"a\":[\"foo\",\"bar\"]}", result);

	}

}