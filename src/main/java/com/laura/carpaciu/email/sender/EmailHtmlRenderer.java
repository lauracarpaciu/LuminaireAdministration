package com.laura.carpaciu.email.sender;

public interface EmailHtmlRenderer {
	String constructHtmlMailPage(String link, String username);
}
