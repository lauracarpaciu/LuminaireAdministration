package com.laura.carpaciu.controllers.work;

import com.laura.carpaciu.entity.work.Work;
import com.laura.carpaciu.services.WorkService;

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
public class WorkController {

	private final WorkService workService;

	@Autowired
	public WorkController(WorkService workService) {
		super();
		this.workService = workService;
	}

	@RequestMapping(value = "/workServices", method = RequestMethod.POST)
	public ResponseEntity<?> createWork(@RequestBody Work work) throws Exception {
		return Optional.ofNullable(workService.createWork(work)).map(a -> new ResponseEntity<>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/workServices", method = RequestMethod.GET)
	public ResponseEntity<?> findAllWorks() throws Exception {
		return Optional.ofNullable(workService.findAllWorks())
				.map(a -> new ResponseEntity<List<Work>>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/workServices/{workDescription}", method = RequestMethod.GET)
	public ResponseEntity<?> findWorkByName(@PathVariable String workDescription) throws Exception {
		return Optional.ofNullable(workService.findWorkByName(workDescription))
				.map(a -> new ResponseEntity<List<Work>>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/workServices/{workId}", method = RequestMethod.GET)
	public ResponseEntity<?> findWorkById(@PathVariable int workId) throws Exception {
		return Optional.ofNullable(workService.findWorkById(workId))
				.map(a -> new ResponseEntity<Work>(a, HttpStatus.OK)).orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/workServices/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateWorkTimeAndDescription(@RequestBody double timedWork, @RequestBody String workDescription,@PathVariable Long id)
			throws Exception {
		return Optional.ofNullable(workService.updateWorkTimeAndDescription(timedWork, workDescription, id))
				.map(a -> new ResponseEntity<>(a, HttpStatus.OK)).orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/workServices/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteWork(@PathVariable int id) throws Exception {
		return Optional.ofNullable(workService.deleteWork(id)).map(a -> new ResponseEntity<>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

}
