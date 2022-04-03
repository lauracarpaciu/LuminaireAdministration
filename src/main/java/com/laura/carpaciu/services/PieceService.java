package com.laura.carpaciu.services;


import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.entity.luminaire.Piece;

public interface PieceService {
	@Transactional
	void addPart(Piece part);

	@Transactional
	Piece findPartByPartNumber(String partNumber);

	@Transactional
	int decreasePartCount(int decrement, String partNumber);
}
