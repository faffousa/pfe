# Utilisation d'une image de base Maven pour construire l'application
FROM maven:3.6.3-jdk-11 AS builder

# Copiez le fichier pom.xml et le fichier src dans le conteneur
COPY pom.xml /usr/src/app/
COPY src /usr/src/app/src/

# Définissez le répertoire de travail dans lequel Maven construira l'application
WORKDIR /usr/src/app

# Exécutez la phase de packaging Maven pour générer le fichier JAR
RUN mvn package -DskipTests

# Utilisation d'une image JRE 11 légère pour exécuter l'application
FROM adoptopenjdk:11-jre-hotspot

# Copiez le fichier JAR de l'étape précédente dans l'image
COPY --from=builder /usr/src/app/target/pfe-1.0.jar /app/app.jar

# Exposez le port sur lequel l'application écoutera
EXPOSE 8082

# Définissez le répertoire de travail
WORKDIR /app

# Définissez la commande d'exécution de l'application
CMD ["java", "-jar", "app.jar"]
