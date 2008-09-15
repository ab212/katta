/**
 * Copyright 2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.katta.index.indexer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

public class DefaultXPathService implements IXPathService {

  private final XPath _xPath;

  public DefaultXPathService() {
    final XPathFactory factory = XPathFactory.newInstance();
    _xPath = factory.newXPath();
  }

  public String parse(final String xpath, final String xml) throws XPathExpressionException {
    final InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
    final InputSource inputSource = new InputSource(inputStream);
    return _xPath.evaluate(xpath, inputSource);
  }

}
