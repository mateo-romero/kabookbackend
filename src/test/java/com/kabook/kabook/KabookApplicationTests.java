package com.kabook.kabook;

import com.kabook.kabook.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KabookApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
    IUserRepository iUsuarioRepository;

//	@Test
//	void asdasd() {
////		iUsuarioRepository.buscarUsuarioPorNombre("josu");
//		iUsuarioRepository.findByName("josu");
//	}


}
