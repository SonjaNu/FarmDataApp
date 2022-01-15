package farmDataExercise.FarmDataApp;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import farmDataExercise.FarmDataApp.domain.Farm;
import farmDataExercise.FarmDataApp.domain.FarmRepository;

@ExtendWith(SpringExtension.class) // JUnit5
@DataJpaTest
public class FarmDataRepositoryTests {

	@Autowired
	private FarmRepository farmRepository;
	
	@Test
	public void findByNameShouldReturnFarm() {
		Farm farm = farmRepository.findByKeyword("noora").get(1);
		assertThat(farm).isNotNull();
		assertThat(farm.getId()).isNotNull();
	}

	@Test
	public void findByMinMaxTypeShouldReturnFarm() {
		Farm farm = farmRepository.findBetween("pH", 5.88, 7.17).get(1);
		assertThat(farm).isNotNull();
		assertThat(farm.getId()).isNotNull();
	}
}
