pipeline {
    agent any

    stages {
        stage('Get Code') {
            steps {
                git 'https://github.com/Pysched/DarrensPetitions'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Package ') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts allowEmptyArchive: true,
                artifacts: 'target/DarrensPetitions.war'
            }
        }

        stage('Deploy') {
            steps {
                stage ('Deploy') {
                           steps {
                               sh 'docker build -f Dockerfile -t tomcat-container . '
                               sh 'docker rm -f "tomcat-container" || true'
                               sh 'docker run --name "tomcat-container" -p 8082:8080 --detach tomcat-container:latest'
                           }
                       }
            }
        }
    }
}
