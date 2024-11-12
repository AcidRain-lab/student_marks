plugins {
	java
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(22) // Устанавливаем JDK 21
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring Data JPA for database connectivity with Jakarta Persistence API
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.mariadb.jdbc:mariadb-java-client")

	// Jakarta dependencies for persistence and validation
	implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
	implementation("jakarta.validation:jakarta.validation-api:3.0.2")

	// Spring Web and Thymeleaf for web applications and templating
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

	// Lombok for reducing boilerplate code
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// Testing dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

