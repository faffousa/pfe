pipeline {
    agent any

    environment {
        MAVEN_HOME = "/opt/apache-maven-3.8.8"
     
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



        stage('Run app With DockerCompose') {
            steps {
                sh 'docker-compose -f docker-compose.yml up -d'
            }
        }

     stage('NEXUS') {
            steps {
                sh "$MAVEN_HOME/bin/mvn deploy -DskipTests"
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
