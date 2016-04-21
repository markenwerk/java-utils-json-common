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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import net.markenwerk.utils.json.common.exceptions.JsonHandlingException;

public final class XmlJsonHandler implements JsonHandler<Document> {

	private final Document document;

	private Node node;

	public XmlJsonHandler() throws ParserConfigurationException {
		this(DocumentBuilderFactory.newInstance().newDocumentBuilder());
	}

	public XmlJsonHandler(DocumentBuilder documentBuilder) throws IllegalArgumentException {
		if (null == documentBuilder) {
			throw new IllegalArgumentException("documentBuilder is null");
		}
		document = documentBuilder.newDocument();
		node = document;
	}

	@Override
	public void onDocumentBegin() throws JsonHandlingException {
	}

	@Override
	public void onDocumentEnd() throws JsonHandlingException {
	}

	@Override
	public void onArrayBegin() throws JsonHandlingException {
		Element element = document.createElement("array");
		this.node.appendChild(element);
		this.node = element;
	}

	@Override
	public void onArrayEnd() throws JsonHandlingException {
		node = node.getParentNode();
		if (node.getNodeName().equals("entry")) {
			node = node.getParentNode();
		}
	}

	@Override
	public void onObjectBegin() throws JsonHandlingException {
		Element element = document.createElement("object");
		this.node.appendChild(element);
		this.node = element;
	}

	@Override
	public void onObjectEnd() throws JsonHandlingException {
		node = node.getParentNode();
		if (node.getNodeName().equals("entry")) {
			node = node.getParentNode();
		}
	}

	@Override
	public void onName(String name) throws JsonHandlingException {
		Element element = document.createElement("entry");
		element.setAttribute("name", name);
		this.node.appendChild(element);
		this.node = element;
	}

	@Override
	public void onNext() throws JsonHandlingException {
	}

	@Override
	public void onNull() throws JsonHandlingException {
		Element element = document.createElement("null");
		appendChild(element);
	}

	@Override
	public void onBoolean(boolean value) throws JsonHandlingException {
		Element element = document.createElement(value ? "true" : "false");
		appendChild(element);
	}

	@Override
	public void onLong(long value) throws JsonHandlingException {
		Element element = document.createElement("number");
		element.appendChild(document.createTextNode(Long.toString(value)));
		appendChild(element);
	}

	@Override
	public void onDouble(double value) throws JsonHandlingException {
		Element element = document.createElement("number");
		element.appendChild(document.createTextNode(Double.toString(value)));
		appendChild(element);
	}

	@Override
	public void onString(String value) throws JsonHandlingException {
		Element element = document.createElement("string");
		element.appendChild(document.createTextNode(value));
		appendChild(element);
	}

	private void appendChild(Element element) {
		node.appendChild(element);
		if (node.getNodeName().equals("entry")) {
			node = node.getParentNode();
		}
	}

	@Override
	public Document getResult() throws JsonHandlingException {
		return document;
	}

}
