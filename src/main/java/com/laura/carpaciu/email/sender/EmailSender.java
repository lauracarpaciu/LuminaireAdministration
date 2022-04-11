package com.laura.carpaciu.email.sender;

import com.laura.carpaciu.entity.user.User;

public interface EmailSender {

	void sendEmail(User user);
}
