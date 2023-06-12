FROM openjdk:8-jdk-alpine

MAINTAINER liunewshine@qq.com

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone && mkdir -p /sp-server

WORKDIR /sp-server

EXPOSE 8099

ADD ./target/sp-server.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar sp-server.jar
