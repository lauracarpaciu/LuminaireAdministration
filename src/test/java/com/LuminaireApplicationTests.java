package com;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;



import com.laura.carpaciu.LuminaireApplication;

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

}
