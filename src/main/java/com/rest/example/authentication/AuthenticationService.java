package com.rest.example.authentication;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class AuthenticationService implements IAuthentication {

	private static final Logger LOGGER = Logger.getLogger(AuthenticationService.class);
	private static final String USERNAME = "admin";
	
	@Override
	public boolean authenticate(String authCredentials) {

		if (null == authCredentials) {
			return false;
		}
		
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = getUsernameAndPasswordAsDecoded (encodedUserPassword);
		
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();
		
		LOGGER.debug("username = " + username);
		LOGGER.debug("password = " + password);

		// call some UserService/LDAP here
		return USERNAME.equals(username) && USERNAME.equals(password);
	}
	
	public static void main(String args[]) {
		AuthenticationService at = new AuthenticationService();
		
		String authString = at.getUsernameAndPasswordAsEncoded ("admin", "hell0123!");
		
		at.authenticate("Basic " + authString);
	}
	
	private String getUsernameAndPasswordAsEncoded(String username, String password) {
		String authString = username + ":" + password;
		return Base64.encodeBase64String(authString.getBytes());
	}
	
	private String getUsernameAndPasswordAsDecoded(String authString) {
		try {
			byte[] decodedBytes = Base64.decodeBase64(authString);
			return new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return null;
	}
}
