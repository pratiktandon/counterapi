package com.counter.demo.rest.api.service;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.commons.codec.binary.Base64;

public class AuthenticationService {
	public boolean authenticate(String authCredentials) {

		if (null == authCredentials)
			return false;
		// Hardcoding the basic for removing this.
		// "Basic b3B0dXM6Y2FuZGlkYXRlcw===="
		final String encodedUserPassword = authCredentials.replaceFirst("Basic"
				+ " ", "");
		String usernameAndPassword = null;
		try {
			byte[] plainCredsBytes = encodedUserPassword.getBytes();
			byte[] decodedBytes = Base64.decodeBase64(plainCredsBytes);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(
				usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		// Hard coding userID and Password
		boolean authenticationStatus = "optus".equals(username)
				&& "candidates".equals(password);
		return authenticationStatus;
	}
}