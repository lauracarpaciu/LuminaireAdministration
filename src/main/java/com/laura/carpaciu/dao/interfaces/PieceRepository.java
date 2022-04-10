package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.luminaire.Piece;

public interface PieceRepository extends CrudRepository<Piece, Long> {

	Optional<Piece> findByPartNumber(String partNumber);

	int updatePieceCountAndPrice(Integer count, Double price, String partNumber);

	int decreasePieceCount(int decrement, String partNumber);

	int updatePieceCount(int count, String partNumber);

	void create(Piece part);

}
