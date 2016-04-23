# Common components for JSON libraries

[![Build Status](https://travis-ci.org/markenwerk/java-utils-json-common.svg?branch=master)](https://travis-ci.org/markenwerk/java-utils-json-common)
[![Coverage Status](https://coveralls.io/repos/github/markenwerk/java-utils-json-common/badge.svg?branch=master)](https://coveralls.io/github/markenwerk/java-utils-json-common?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/57190b36fcd19a00518561ba/badge.svg)](https://www.versioneye.com/user/projects/57190b36fcd19a00518561ba)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/utils-json-common/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/utils-json-common)
[![Issues](https://img.shields.io/github/issues/markenwerk/java-utils-json-common.svg)](https://github.com/markenwerk/java-utils-json-common/issues)
[![MIT License](https://img.shields.io/badge/license-MIT-brightgreen.svg)](https://github.com/markenwerk/java-utils-json-common/blob/master/LICENSE)

## Overview

This library provides common components for other JSON processing libraries, such as:

 - [A fast event based JSON parser for Java](https://github.com/markenwerk/java-utils-json-parser)
 - [A convenient JSON domain model for Java](https://github.com/markenwerk/java-utils-json-model)

Consult the [documentation](http://markenwerk.github.io/java-utils-json-common/javadoc/index.html) and the [usage description](#usage) for further information.

## Maven

This library is hosted in the [Maven Central Repository](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/utils-json-common). You can use it with the following coordinates:

```xml
<dependency>
	<groupId>net.markenwerk</groupId>
	<artifactId>utils-json-common</artifactId>
	<version>1.0.0</version>
</dependency>
```
 
## Usage

### Handling JSON documents

This library provides the [`JsonHandler`][JsonHandler] interface which provides a collection of callback methods that can be called (e.g. by a JSON parser or by domain model for JSON documents) to describe a JSON document and calculates a result for the described JSON document.

The following sequence of callback methods describes the [`example.json`](https://gist.github.com/toKrause/208992e7625f0b8a9d04e7283f09fc26).

```java
// a generic JsonHandler
JsonHandler<?> handler = ...

// begin a document
handler.onDocumentBegin();

	// begin an object
	handler.onObjectBegin();
	
		// inside the object: set a name followed by a value to describe an entry
		handler.onName("null");
		handler.onNull();
	
		// inside the object: call next between two entries
		handler.onNext();
		
		handler.onName("boolean");
		handler.onBoolean(true);
		
		handler.onNext();
		
		handler.onName("long");
		handler.onLong(-42);
		
		handler.onNext();
		
		handler.onName("double");
		handler.onDouble(-23.42);
		
		handler.onNext();
		
		// begin an array
		handler.onName("array");
		handler.onArrayBegin();
		
			// inside the object: just a value to describe an entry
			handler.onString("foo");
		
			// inside the object: call next between two entries
			handler.onNext();
		
			handler.onString("bar");
		
		// end the array
		handler.onArrayEnd();
	
	// end the object
	handler.onObjectEnd();

// end the document
handler.onDocumentEnd();
```

A [`JsonDocument`][JsonDocument] from the convenient JSON domain model [library](https://github.com/markenwerk/java-utils-json-model) takes a [`JsonHandler`][JsonHandler] and calls the appropriate callback methods to describe itself.

```java
// Json describing the example
// a generic JsonHandler
JsonHandler<?> handler = ...

// a JsonDocument
JsonDocument document = ...

// handle document with handler
document.handle(handler);
```

### JSON text handler

This library provides the [`AppendingJsonTextJsonHandler`][AppendingJsonTextJsonHandler] which recreates the handled JSON document as a pretty printed JSON text that is appended to an [`Appendable`][Appendable]:

```java
// a JsonDocument
JsonDocument document = ...

// writes a pretty printed json document into test.json  
Writer writer = new FileWriter(new File("test.json"));
document.handle(new AppendingJsonTextJsonHandler(writer));
writer.close();
```

This library provides the [`JsonTextJsonHandler`][JsonTextJsonHandler] which recreates the handled JSON document as a pretty printed JSON text and returns it as a [`String`][String]:

```java
// a JsonDocument
JsonDocument document = ...

// returns a pretty printed json document  
String json = document.handle(new JsonTextJsonHandler(writer));
```

### XML document handler

This library provides the [`XmlDocumentJsonHandler`][XmlDocumentJsonHandler] which creates a XML [`Document`][Document] which resembles the handled JSON document returns the created [`Document`][Document]:

```java
// a JsonDocument
JsonDocument document = ...

// returns a xml document  
Document xmlDocument = document.handle(new XmlDocumentJsonHandler());
```

The returned XML [`Document`][Document] for the [`example.json`](https://gist.github.com/toKrause/208992e7625f0b8a9d04e7283f09fc26)has the following content:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<object>
   <entry name="null">
      <null />
   </entry>
   <entry name="boolean">
      <boolean value="true" />
   </entry>
   <entry name="longValue">
      <number value="-42" />
   </entry>
   <entry name="double">
      <number value="-23.42" />
   </entry>
   <entry name="array">
      <array>
         <string value="foo" />
         <string value="bar" />
      </array>
   </entry>
</object>
```


[AppendingJsonTextJsonHandler]: https://markenwerk.github.io/java-utils-json-common/index.html?net/markenwerk/utils/json/common/handler/AppendingJsonTextJsonHandler.html
[JsonHandler]: http://markenwerk.github.io/java-utils-json-common/index.html?net/markenwerk/utils/json/common/handler/JsonHandler.html
[JsonTextJsonHandler]: http://markenwerk.github.io/java-utils-json-common/index.html?net/markenwerk/utils/json/common/handler/JsonTextJsonHandler.html
[XmlDocumentJsonHandler]: http://markenwerk.github.io/java-utils-json-common/index.html?net/markenwerk/utils/json/common/handler/XmlDocumentJsonHandler.html

[JsonDocument]: https://markenwerk.github.io/java-utils-json-model/index.html?net/markenwerk/utils/json/JsonDocument.html

[Appendable]: http://docs.oracle.com/javase/8/docs/api/index.html?java/lang/Appendable.html
[Document]: https://docs.oracle.com/javase/8/docs/api/index.html?org/w3c/dom/Document.html
[String]: http://docs.oracle.com/javase/8/docs/api/index.html?java/lang/String.html

