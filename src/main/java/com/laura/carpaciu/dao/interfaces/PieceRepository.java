package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.laura.carpaciu.entity.luminaire.Piece;
@Repository
public interface PieceRepository  {

	void createPiece(Piece part);


    Optional<Piece> findPieceByName(String partName);

    Optional<Piece> findPieceByPartNumber(String partNumber);


    int updatePieceCount(int count, String partNumber);

    int updatePieceCountAndPrice(int increment, double price, String partNumber);

    int decreasePieceCount(int decrement, String partNumber);
}
