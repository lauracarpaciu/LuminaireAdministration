package com.laura.carpaciu.controllers.luminaire;

import com.laura.carpaciu.entity.luminaire.Luminaire;
import com.laura.carpaciu.services.LuminaireService;

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
public class LuminaireController {

	private final LuminaireService luminaireService;

	@Autowired
	public LuminaireController(LuminaireService luminaireService) {
		super();
		this.luminaireService = luminaireService;
	}

	@RequestMapping(value = "/luminaires", method = RequestMethod.POST)
	public ResponseEntity<?> createLuminaire(@RequestBody Luminaire luminaire) throws Exception {
		return Optional.ofNullable(luminaireService.createLuminaire(luminaire))
				.map(a -> new ResponseEntity<Employee>(a, HttpStatus.OK)).orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/luminaires/{serialNumber}", method = RequestMethod.GET)
	public ResponseEntity<?> findLuminaireBySerialNumber(@PathVariable String serialNumber) throws Exception {
		return Optional.ofNullable(luminaireService.findLuminaireBySerialNumber(serialNumber))
				.map(a -> new ResponseEntity<List<Luminaire>>(HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

}
