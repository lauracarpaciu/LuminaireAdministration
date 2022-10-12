package com.laura.carpaciu.controllers.luminaire;

import com.laura.carpaciu.entity.luminaire.Piece;
import com.laura.carpaciu.services.PieceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
public class PieceController {

	private final PieceService pieceService;

	@Autowired
	public PieceController(PieceService pieceService) {
		super();
		this.pieceService = pieceService;
	}

	@RequestMapping(value = "/pieces", method = RequestMethod.POST)
	public ResponseEntity<?> addPiece(@RequestBody Piece part) throws Exception {
		return Optional.ofNullable(pieceService.addPiece(part)).map(a -> new ResponseEntity<Piece>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/pieces/{partNumber}", method = RequestMethod.GET)
	public ResponseEntity<?> findPieceByPartNumber(@PathVariable String partNumber) throws Exception {
		return Optional.ofNullable(pieceService.findPieceByPartNumber(partNumber))
				.map(a -> new ResponseEntity<List<Piece>>(HttpStatus.OK)).orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/pieces", method = RequestMethod.GET)
	public ResponseEntity<?> decreasePieceCount(int decrement, String partNumber) throws Exception {
		return Optional.ofNullable(pieceService.decreasePieceCount(decrement, partNumber))
				.map(a -> new ResponseEntity<>(a, HttpStatus.OK)).orElseThrow(() -> new Exception("Not found"));
	}
}
