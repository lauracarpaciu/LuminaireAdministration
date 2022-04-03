package com.laura.carpaciu.service.impl.luminaire;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.laura.carpaciu.dao.interfaces.PieceRepository;
import com.laura.carpaciu.entity.luminaire.Piece;
import com.laura.carpaciu.services.PieceService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PieceServiceImpl implements PieceService {
	
	private final PieceRepository pieceRepository ;

	public PieceServiceImpl(PieceRepository pieceRepository) {
		super();
		this.pieceRepository = pieceRepository;
	}

	@Override
	public void createPart(Piece part) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Piece> findPartByName(String partName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Piece> findPartByPartNumber(String partNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePieceCount(int count, String partNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePieceCountAndPrice(int increment, double price, String partNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int decreasePieceCount(int decrement, String partNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

}
