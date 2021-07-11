package za.co.bmw.kanban;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("za.co.bmw.kanban.model")
@EnableJpaRepositories("za.co.bmw.kanban.repository")
public class KanbanApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanbanApplication.class, args);
	}

}
