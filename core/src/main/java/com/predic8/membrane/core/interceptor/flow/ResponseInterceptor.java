package com.predic8.membrane.core.interceptor.flow;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.predic8.membrane.annot.MCElement;
import com.predic8.membrane.core.exchange.Exchange;
import com.predic8.membrane.core.interceptor.Interceptor;
import com.predic8.membrane.core.interceptor.Outcome;

@MCElement(name="response")
public class ResponseInterceptor extends AbstractFlowInterceptor {

	/**
	 * (Yes, this needs to be handled in handleREQUEST.)
	 */
	@Override
	public Outcome handleRequest(Exchange exc) throws Exception {
		for (Interceptor i : getInterceptors()) {
			Flow f = i.getFlow();
			if (f == Flow.RESPONSE) {
				exc.pushInterceptorToStack(i);
				continue;
			}

			if (f == Flow.REQUEST_RESPONSE)
				exc.pushInterceptorToStack(i);
		}
		return Outcome.CONTINUE;
	}

	@Override
	protected void writeInterceptor(XMLStreamWriter out) throws XMLStreamException {
		out.writeStartElement("response");
		for (Interceptor i : getInterceptors())
			i.write(out);
		out.writeEndElement();
	}

}