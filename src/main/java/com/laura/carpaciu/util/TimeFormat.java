package com.laura.carpaciu.util;

import java.time.format.DateTimeFormatter;

public class TimeFormat {

	private TimeFormat() {
	}

	public static DateTimeFormatter formatter() {

		return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	}

}