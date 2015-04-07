package abmWebApp.encoder;

import org.owasp.esapi.ESAPI;



public class Encoder {
	
	private String toEncode;
	private String encoded;

	public Encoder(String toEncode) {
		this.toEncode = toEncode;
	}
	
	public String encode() {
		
		encoded = ESAPI.encoder().canonicalize(toEncode);
		encoded = ESAPI.encoder().encodeForHTML(encoded);
		return encoded;
	}

}
