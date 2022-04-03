package com.laura.carpaciu.services;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.luminaire.Piece;

public interface PieceService {
	void createPart(Piece part);

	Optional<Piece> findPartByName(String partName);

	Optional<Piece> findPartByPartNumber(String partNumber);

	int updatePieceCount(int count, String partNumber);

	int updatePieceCountAndPrice(int increment, double price, String partNumber);

	int decreasePieceCount(int decrement, String partNumber);

}
