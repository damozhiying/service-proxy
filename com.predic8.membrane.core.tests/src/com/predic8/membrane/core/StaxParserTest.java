/* Copyright 2009 predic8 GmbH, www.predic8.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. */
package com.predic8.membrane.core;

import java.io.StringReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.junit.Assert;
import org.junit.Test;

public class StaxParserTest {

	@Test
	public void test() throws Exception {
		String xml = "<e xmlns:ns='http://tempuri.org' a1='a1' ns:a2='a2' />";

		XMLStreamReader reader = new FixedStreamReader(XMLInputFactory
				.newInstance().createXMLStreamReader(new StringReader(xml)));

		reader.next();

		Assert.assertNull(reader.getAttributeValue("", "a2"));
		Assert.assertEquals("a2", reader.getAttributeValue(null, "a2"));
		Assert.assertEquals("a2",
				reader.getAttributeValue("http://tempuri.org", "a2"));

		Assert.assertNull(reader.getAttributeValue("http://tempuri.org", "a1"));
		Assert.assertEquals("a1", reader.getAttributeValue(null, "a1"));
		Assert.assertEquals("a1", reader.getAttributeValue("", "a1"));
	}

}