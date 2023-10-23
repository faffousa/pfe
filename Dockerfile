# Utilisez une image Docker avec Java 11
FROM adoptopenjdk:11-jre-hotspot

# Définissez le répertoire de travail
WORKDIR /app

# Copiez le fichier JAR généré par Maven (assurez-vous que le nom du JAR correspond à votre projet)
COPY target/app-0.0.1-SNAPSHOT.jar app.jar

# Exposez le port sur lequel votre application écoute (8080 par défaut)
EXPOSE 8082

# Commande pour exécuter l'application lorsque le conteneur démarre
CMD ["java", "-jar", "app.jar"]
