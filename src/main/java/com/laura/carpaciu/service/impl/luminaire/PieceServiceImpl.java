package com.laura.carpaciu.service.impl.luminaire;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.laura.carpaciu.dao.interfaces.PieceRepository;
import com.laura.carpaciu.entity.luminaire.Piece;
import com.laura.carpaciu.errors.user.PersonNotFoundException;
import com.laura.carpaciu.services.PieceService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PieceServiceImpl implements PieceService {

	private final PieceRepository pieceRepository;

	public PieceServiceImpl(PieceRepository pieceRepository) {
		super();
		this.pieceRepository = pieceRepository;
	}

	@Override
	@Transactional
	public void addPiece(Piece part) {
		Optional<Piece> optionalPart = pieceRepository.findByPartNumber(part.getPartNumber());

		if (optionalPart.isPresent()) {
			pieceRepository.create(part);
			return;
		}

		if (optionalPart.get().equals(part)) {

			pieceRepository.updatePieceCountAndPrice(part.getCount(), part.getPrice(), part.getPartNumber());
		}

	}

	@Override
	public Piece findPieceByPartNumber(String partNumber) {
		return pieceRepository.findByPartNumber(partNumber).orElseThrow(() -> new PersonNotFoundException("Person not found!"));
	}

	@Override
	public int decreasePieceCount(int decrement, String partNumber) {
		return pieceRepository.decreasePieceCount(decrement, partNumber);
	}

}
