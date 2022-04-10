package com.laura.carpaciu.util;

import org.springframework.security.core.Authentication;

import com.laura.carpaciu.entity.user.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logs {

	private Logs() {
	}

	public static void loginLogoutUser(Authentication auth, String action) {
		String userName = auth.getName();
		writeLog(userName, action);

	}

	public static void createUser(User user, String action) {
		String username = user.getUsername();
		writeLog(username, action);
	}

	public static void sendEmail(User user, String action) {
		String username = user.getUsername();
		writeLog(username, action);

	}

	private static void writeLog(String userName, String action) {
		String path = "./web/src/main/resources/log/user/" + userName + ".txt";
		String writeLog = "\n" + userName + " " + action + ": " + LocalDateTime.now().format(TimeFormat.formatter());

		writeInFile(writeLog, path);

	}

	public static void writeEmailException(String excMessage) {
		String path = "./web/src/main/resources/log/exceptions/mailException.txt";
		String writeLog = "\n" + excMessage + " " + LocalDateTime.now().format(TimeFormat.formatter());

		writeInFile(writeLog, path);

	}

	public static void writeExceptions(String excMessage) {

		String path = "./web/src/main/resources/log/exceptions/exceptions.txt";
		String writeLog = "\n" + excMessage + " " + LocalDateTime.now().format(TimeFormat.formatter());

		writeInFile(writeLog, path);
	}

	private static void writeInFile(String writeLog, String path) {

		try (FileWriter x = new FileWriter(path, true); BufferedWriter br = new BufferedWriter(x)) {

			br.write(writeLog);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}