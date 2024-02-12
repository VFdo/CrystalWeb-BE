FROM openjdk:17-buster as builder
FROM maven:3.8.6-eclipse-temurin-11 as mavenBuild

WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar

RUN java -Djarmode=layertools -jar application.jar extract


