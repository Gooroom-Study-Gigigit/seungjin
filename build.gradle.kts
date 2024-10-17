plugins {
	java
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.project"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.springframework.boot:spring-boot-starter-actuator")
	implementation ("io.micrometer:micrometer-registry-prometheus")

	implementation("commons-io:commons-io:2.11.0") // 현재 버전 확인하여 적절히 수정

	implementation ("com.google.cloud:google-cloud-texttospeech:2.42.0") // 구글 TTS 라이브러리



	//jwt
	implementation("io.jsonwebtoken:jjwt-api:0.11.1")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.1")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.1")

	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2") // webmvc-ui는 json 형식을 보여주기 위함.

	implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE") // amazon cloud 사용
	implementation ("org.springframework.boot:spring-boot-starter-webflux") // webflux, 모바일 사용


    implementation("io.netty:netty-resolver-dns-native-macos:4.1.96.Final:osx-aarch_64")
//	implementation('io.netty:netty-resolver-dns-native-macos')

	// 스프링 부트 3.0 이상 query dls
	implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
	annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")


	annotationProcessor("jakarta.persistence:jakarta.persistence-api")
	annotationProcessor("jakarta.annotation:jakarta.annotation-api")


	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
	runtimeOnly("com.h2database:h2")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation ("com.h2database:h2")

	//testImplementation(
     //       group = "io.netty",
      //      name = "netty-resolver-dns-native-macos",
       //     classifier = "osx-aarch_64"
        //)
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// Querydsl 빌드 옵션 (옵셔널)
/**
 * QueryDSL Build Options
 */
val querydslDir = "src/main/generated"

sourceSets {
	getByName("main").java.srcDirs(querydslDir)
}

tasks.withType<JavaCompile> {
	options.generatedSourceOutputDirectory = file(querydslDir)

	// 위의 설정이 안되면 아래 설정 사용
	// options.generatedSourceOutputDirectory.set(file(querydslDir))
}
tasks.named("clean") {
	doLast {
		file(querydslDir).deleteRecursively()
	}
}
