package com.laura.carpaciu.service.impl.luminaire;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laura.carpaciu.dao.interfaces.PieceRepository;
import com.laura.carpaciu.entity.luminaire.Piece;
import com.laura.carpaciu.errors.user.PersonNotFoundException;
import com.laura.carpaciu.services.PieceService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PieceServiceImpl implements PieceService {
	@Autowired
	private final PieceRepository pieceRepository;

	public PieceServiceImpl(PieceRepository pieceRepository) {
		super();
		this.pieceRepository = pieceRepository;
	}

	@Override
	@Transactional
	public void addPiece(Piece part) {
		Optional<Piece> optionalPart = pieceRepository.findPieceByPartNumber(part.getPartNumber());

		if (optionalPart.isPresent()) {
			pieceRepository.createPiece(part);
			return;
		}

		if (optionalPart.get().equals(part)) {

			pieceRepository.updatePieceCountAndPrice(part.getCount(), part.getPrice(), part.getPartNumber());
		}

	}

	@Override
	public Piece findPieceByPartNumber(String partNumber) {
		return pieceRepository.findPieceByPartNumber(partNumber).orElseThrow(() -> new PersonNotFoundException("Person not found!"));
	}

	@Override
	public int decreasePieceCount(int decrement, String partNumber) {
		return pieceRepository.decreasePieceCount(decrement, partNumber);
	}

}
