# Common components for JSON processing libraries for Java

[![Build Status](https://travis-ci.org/markenwerk/java-utils-json-common.svg?branch=master)](https://travis-ci.org/markenwerk/java-utils-json-common)
[![Coverage Status](https://coveralls.io/repos/github/markenwerk/java-utils-json-common/badge.svg?branch=master)](https://coveralls.io/github/markenwerk/java-utils-json-common?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/57100ec5fcd19a0039f16fa4/badge.svg)](https://www.versioneye.com/user/projects/57100ec5fcd19a0039f16fa4)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/utils-json-common/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/utils-json-common)
[![Issues](https://img.shields.io/github/issues/markenwerk/java-utils-json-common.svg)](https://github.com/markenwerk/java-utils-json-common/issues)
[![MIT License](https://img.shields.io/badge/license-MIT-brightgreen.svg)](https://github.com/markenwerk/java-utils-json-common/blob/master/LICENSE)

## Overview

This library provides common components for other JSON processing libraries, such as:

 - [A fast event based JSON parser for Java](https://github.com/markenwerk/java-utils-json-common)
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

This library provides the following implementations of [`JsonHandler`][JsonHandler]:

 - [`AppendingJsonTextJsonHandler`][AppendingJsonTextJsonHandler]: Recreates the handled JSON document as a pretty printed JSON text that is appended to an [`Appendable`][Appendable].
 - [`JsonTextJsonHandler`][JsonTextJsonHandler]: Recreates the handled JSON document as a pretty printed JSON text that is returned as a [`String`][String].
 - [`XmlJsonHandler`][XmlJsonHandler]: Creates a XML [`Document`][Document] that represents the same data as the handled JSON document.

[AppendingJsonTextJsonHandler]:  http://markenwerk.github.io/java-utils-json-common/index.html?net/markenwerk/utils/json/common/handler/AppendingJsonTextJsonHandler.html
[JsonHandler]:  http://markenwerk.github.io/java-utils-json-common/index.html?net/markenwerk/utils/json/common/handler/JsonHandler.html
[JsonTextJsonHandler]:  http://markenwerk.github.io/java-utils-json-common/index.html?net/markenwerk/utils/json/common/handler/JsonTextJsonHandler.html
[XmlJsonHandler]:  http://markenwerk.github.io/java-utils-json-common/index.html?net/markenwerk/utils/json/common/handler/XmlJsonHandler.html

[Appendable]: http://docs.oracle.com/javase/8/docs/api/index.html?java/lang/Appendable.html
[Document]: https://docs.oracle.com/javase/8/docs/api/index.html?org/w3c/dom/Document.html
[String]: http://docs.oracle.com/javase/8/docs/api/index.html?java/lang/String.html

