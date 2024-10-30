plugins {
	java
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.asciidoctor.jvm.convert") version "3.3.2"
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
	create("asciidoctorExt")
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

	// Spring REST Docs
	add("asciidoctorExt", "org.springframework.restdocs:spring-restdocs-asciidoctor") // asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")

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
    options.compilerArgs.add("-parameters")

	// 위의 설정이 안되면 아래 설정 사용
	// options.generatedSourceOutputDirectory.set(file(querydslDir))
}
tasks.named("clean") {
	doLast {
		file(querydslDir).deleteRecursively()
	}
}

// RestDocs 설정
val snippetsDir by extra { file("build/generated-snippets") }

tasks.test {
    useJUnitPlatform()
    outputs.dir(snippetsDir) // 테스트 결과를 Snippets 디렉터리에 저장
}

tasks.named<org.asciidoctor.gradle.jvm.AsciidoctorTask>("asciidoctor") {
    inputs.dir(snippetsDir) // 테스트 결과 스니펫을 입력으로 사용
    configurations("asciidoctorExt") // 추가적인 의존성 설정

    sources {
        include("**/index.adoc") // 특정 AsciiDoc 파일만 포함
    }
    baseDirFollowsSourceFile() // 상대 경로가 제대로 처리되도록 설정
    dependsOn(tasks.test) // Asciidoctor 작업이 테스트 이후 실행되도록 설정
}

// BootJar 작업 설정 - Asciidoctor의 출력을 포함합니다.
tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    dependsOn(tasks.named("asciidoctor")) // BootJar는 Asciidoctor 작업 이후 실행됨

    from(tasks.named<org.asciidoctor.gradle.jvm.AsciidoctorTask>("asciidoctor").get().outputDir) {
        into("static/docs") // 생성된 문서를 static/docs로 복사
    }
}

