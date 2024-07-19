# OpenJDK 17 기반의 Alpine Linux 이미지를 베이스로 사용합니다.
FROM openjdk:17-alpine

# 환경 변수 설정 (주어진 환경 변수는 사용자가 지정한 환경 변수값을 사용합니다)
ENV HOST=$HOST
ENV USERNAME=$USERNAME
ENV PASSWORD=$PASSWORD

# JAR_FILE이라는 ARG(Argument)를 사용하여 JAR 파일 경로를 설정합니다.
ARG JAR_FILE=build/libs/*.jar

# JAR_FILE로부터 해당 JAR 파일을 컨테이너 내의 app.jar로 복사합니다.
COPY ${JAR_FILE} app.jar

# 컨테이너 실행 시 자동으로 실행되는 명령어를 정의합니다.
ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul", "-jar", "/app.jar"]
