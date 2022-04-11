package com.laura.carpaciu.email.sender.impl;

import org.springframework.stereotype.Component;

import com.laura.carpaciu.email.sender.EmailHtmlRenderer;

@Component
public class EmailHtmlRendererImpl implements EmailHtmlRenderer {

	@Override
	public String constructHtmlMailPage(String link, String username) {

		return "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Email registration</title>\n" + "    <style>\n" + "\n" + "*{\n" + "    margin: 0;\n"
				+ "    padding: 0;\n" + "}\n" + "\n" + "\n" + "body{\n" + "    width: 100%;\n" + "    height: 100vh;\n"
				+ "    background-color:tomato;\n" + "    font-family: Arial, Helvetica, sans-serif;\n"
				+ "    font-weight: 600;\n" + "    min-width: 270px;\n" + "    min-height: 370px;\n" + "    \n"
				+ "    \n" + "}\n" + "\n" + "\n" + "\n" + "header{\n" + "    width: 100%;\n"
				+ "    text-align: center;\n" + "    height: 5%;\n" + "    padding-top: 20px;\n"
				+ "    background-color: white;\n" + "\n" + "}\n" + "\n" + "\n" + ".text{\n"
				+ "    text-align: center;\n" + "    padding-top: 30px;\n" + "    width:  100%;\n" + "}\n" + "\n"
				+ ".link{\n" + "    text-align: center;\n" + "    width: 100%;\n" + "}\n" + "\n" + "a{\n"
				+ "    width: 100%;\n" + "}\n" + "\n" + ".btn{\n" + "    background-color: blue;\n"
				+ "    color: whitesmoke;\n" + "    padding:10px 80px;\n" + "    margin-top: 15px;\n"
				+ "    font-weight: 600;\n" + "    border-radius: 10px;\n" + "}\n" + "\n" + ".btn:hover{\n"
				+ "    background-color: black;\n" + "    cursor: pointer;\n" + "}\n" + "\n" + "footer{\n"
				+ "    background-color: whitesmoke;\n" + "    width: 100%;\n" + "    height: 5%;\n"
				+ "    position: absolute;\n" + "    text-align: center;\n" + "    bottom: 0;\n" + "}\n" + "\n" + "p{\n"
				+ "    padding-top: 20px;\n" + "}\n" + "\n" + "    </style>\n" + "</head>\n" + "<body>\n" + "    \n"
				+ "    <header>\n" + "        <h2>Thank you for registering an account</h2>\n" + "    </header>\n"
				+ "\n" + "    <div class=\"text\">\n" + " " + username + "\n" + "    </div>\n"
				+ "    <div class=\"text\">\n" + "        Click the link below to activate account\n" + "    </div>\n"
				+ "\n" + "    <div class=\"link\">\n" + "        <a href=\"" + link
				+ "\"><button type=\"button\" class=\"btn\">Activate account</button></a>\n" + "    </div>\n" + "\n"
				+ "    <footer>\n" + "        <p>Ginitoru<span>&trade;</span></p>\n" + "    </footer>\n" + "    \n"
				+ "\n" + "</body>\n" + "</html>";
	}
}
