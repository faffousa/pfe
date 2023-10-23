pipeline {
    agent any

    environment {
        MAVEN_HOME = "/opt/apache-maven-3.8.8"
        NEXUS_REPO_ID = 'nexus-pfe'
        NEXUS_REPO_URL = 'http://192.168.1.108:8081/repository/pfe/'
        NEXUS_REPO_USERNAME = 'admin'
        NEXUS_REPO_PASSWORD = 'fares123'
    }

    stages {
        stage('Checkout Git') {
            steps {
                echo 'Pulling...'
                git branch: 'master',
                url: 'https://github.com/faffousa/pfe.git'
            }
        }

        stage('Testing Maven') {
            steps {
                sh "$MAVEN_HOME/bin/mvn -version"
            }
        }

        stage('Maven Clean') {
            steps {
                sh "$MAVEN_HOME/bin/mvn clean"
            }
        }

        stage('Maven Compile') {
            steps {
                sh "$MAVEN_HOME/bin/mvn compile"
            }
        }

        stage('JUnit and Mockito Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh "$MAVEN_HOME/bin/mvn --batch-mode test"
                    } else {
                        bat "$MAVEN_HOME\\bin\\mvn --batch-mode test"
                    }
                }
            }
        }

        stage('SonarQube analysis') {
            steps {
                sh "$MAVEN_HOME/bin/mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=fares123"
            }
        }

        stage('Docker build') {
            steps {
                sh 'sudo docker build -t faffousa/pfe .'
            }
        }

        stage('Docker login') {
            steps {
                sh 'echo $dockerhub_PSW | docker login -u faffousa -p dckr_pat_9f0g2XMz_iBfcGOGIsOL0EqpP_g'
            }
        }

        stage('Push Docker Image') {
            steps {
                sh 'docker push faffousa/pfe'
            }
        }

        stage('Deploy to Nexus Repository') {
            steps {
                sh "$MAVEN_HOME/bin/mvn deploy:deploy-file " +
                   "-Durl=$NEXUS_REPO_URL " +
                   "-DrepositoryId=$NEXUS_REPO_ID " +
                   "-Dfile=path/to/your/artifact.jar " + // Remplacez ceci par le chemin de votre artefact
                   "-DgroupId=com.example " + // Remplacez par le groupId de votre artefact
                   "-DartifactId=your-artifact " + // Remplacez par l'artifactId de votre artefact
                   "-Dversion=1.0" // Remplacez par la version de votre artefact
            }
        }

        stage('Run app With DockerCompose') {
            steps {
                sh 'docker-compose -f docker-compose.yml up -d'
            }
        }

        stage('Cleaning up') {
            steps {
                sh 'docker rmi -f faffousa/pfe'
            }
        }

        stage('Send Email Notification') {
            steps {
                emailext(
                    to: 'fares.aissa@esprit.tn',
                    subject: 'Pipeline executed successfully',
                    body: 'Your Jenkins pipeline has been executed successfully.',
                )
            }
        }
    }
}
