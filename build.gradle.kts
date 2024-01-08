plugins {
	java
	// 빌드 작업 자동 구성
	id("org.springframework.boot") version "3.2.1"
	//spring 기반으로 개발을 할때 의존성 버전들을 관리하기 위한 플러그인(의존성 추가할대 버전 생략 가능)
	id("io.spring.dependency-management") version "1.1.4"
}

group = "kr.foundcake"
version = "0.0.1-SNAPSHOT"

// JDK 21 버전을 기반
java {
	sourceCompatibility = JavaVersion.VERSION_21
}

// 어노테이션 프로세서를 컴파일에 적용 시킴
configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral() // maven central 라이브러리 서버
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa") //데이터 베이스 상호 작용
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf") //Thymeleaf 템플릿 엔진
	implementation("org.springframework.boot:spring-boot-starter-web") // 웹 에플리케이션 개발
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client") // 런타임에만 필요
	implementation("org.springframework.boot:spring-boot-starter-security") // Spring Security
	// compile
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	//test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

// test는 지금 하기 귀찮으니 ㅌㅌ))) 그 웹 관련 테스트는 selenium도 많이 사용됨
tasks.withType<Test> {
	useJUnitPlatform()
}

//jar 파일 이름 설정
tasks.bootJar{
	archiveFileName.set("TodoList.jar")
}