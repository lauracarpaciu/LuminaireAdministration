package com.laura.carpaciu.services;

import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.entity.luminaire.Piece;

public interface PieceService {
	@Transactional
	void addPiece(Piece part);

	@Transactional
	Piece findPieceByPartNumber(String partNumber);

	@Transactional
	int decreasePieceCount(int decrement, String partNumber);
}
