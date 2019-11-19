package edu.dipa.lunatech.ImdbApplication;

import edu.dipa.lunatech.ImdbApplication.entity.NameBasic;
import edu.dipa.lunatech.ImdbApplication.repository.NameBasicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class ImdbApplication {
	/*@Autowired
	NameBasicRepository nameRepo;
*/
	public static void main(String[] args) {
		SpringApplication.run(ImdbApplication.class, args);
	}

	/*public void run(String name){
		Optional<NameBasic> nameBasic= nameRepo.findByPrimaryName(name);
		String knownForTitles= nameBasic.get().getKnownForTitles();
		System.out.println(knownForTitles);
	}*/

}

