# Usa uma imagem com Maven e Java 21 para compilar o projeto
FROM maven:3.9-eclipse-temurin-21 AS build

# Define a pasta de trabalho dentro do container
WORKDIR /app

# Copia o pom.xml primeiro (aproveita o cache do Docker nas dependências)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o restante do código e compila
COPY src ./src
RUN mvn clean package -DskipTests

# Usa uma imagem menor, só com o Java, para rodar o JAR gerado
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copia apenas o JAR da etapa de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta onde o Spring Boot vai rodar
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]