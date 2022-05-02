package com;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.laura.carpaciu.LuminaireApplication;
import com.laura.carpaciu.controllers.luminaire.PieceController;
import com.laura.carpaciu.services.PieceService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = LuminaireApplication.class)
public class LuminaireApplicationTests {

	private Logger log = LoggerFactory.getLogger(LuminaireApplicationTests.class);

	@Test
	public void contextLoads() {
	}

	@Before
	public void setUp() throws Exception {
	}

	@MockBean
	public PieceService pieceService;

	@Autowired
	public PieceController pieceController;

	@Test
	@DisplayName("check been injection")
	public void checkBean() {
		Assert.assertNotNull(pieceController);
	}

	@Test
	@DisplayName("verify if the PartService bean is injected from Spring Context")
	public void checkPartServiceInjection() {
		Assert.assertNotNull(pieceService);
	}
}
