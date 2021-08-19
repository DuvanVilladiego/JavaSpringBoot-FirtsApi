package com.example.demo;

import com.example.demo.bean.MyBean;
import com.example.demo.bean.MyBeanWithDependency;
import com.example.demo.bean.MyBeanWithProperties;
import com.example.demo.component.ComponentDependency;
import com.example.demo.entity.User;
import com.example.demo.pojo.UserPojo;
import com.example.demo.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	Log LOGGER = LogFactory.getLog(DemoApplication.class);;
	
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository  userRepository;

	public DemoApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {
//		ejemplosAnteriores();
		saveUsersInDataBase();
	}

	private void getInformationJpqlFromUser(){
		LOGGER.info("Usuario con el metodo findByUserEmail" +
				userRepository.findByUserEmail("john@domain.com")
						.orElseThrow(() -> new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("User", Sort.by("id").ascending())
				.forEach(user -> LOGGER.info("Usuario con metodo Sort " + user));

		userRepository.findByName("john")
				.forEach(user -> LOGGER.info("Usuario con query method " + user));
	}

	private void saveUsersInDataBase(){
		User user1 = new User("John", "john@domain.com", LocalDate.of(2021,8,17));
		User user2 = new User("Duvan", "duvan@domain.com", LocalDate.of(2021,5,17));
		User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021,8,27));
		User user4 = new User("user4", "user4@domain.com", LocalDate.of(2021,4,1));
		User user5 = new User("user5", "user5@domain.com", LocalDate.of(2021,5,18));
		User user6 = new User("user6", "user6@domain.com", LocalDate.of(2021,7,10));
		User user7 = new User("user7", "user7@domain.com", LocalDate.of(2021,8,1));
		User user8 = new User("user8", "user8@domain.com", LocalDate.of(2021,10,3));
		User user9 = new User("user9", "user9@domain.com", LocalDate.of(2021,2,14));
		User user10 = new User("user10", "user10@domain.com", LocalDate.of(2021,1,21));
		User user11 = new User("user11", "user11@domain.com", LocalDate.of(2021,6,9));
		User user12 = new User("user12", "user12@domain.com", LocalDate.of(2021,9,2));
		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10,user10,user11,user12);
		list.forEach(userRepository::save);
	}
	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + " - " + userPojo.getPassword());
		try {
			int value = 10/0;
			LOGGER.info("mi valor : " + value);
		} catch (Exception e) {
			LOGGER.error("Esto es un error al dividir por cero " + e);
		}
	}
}
