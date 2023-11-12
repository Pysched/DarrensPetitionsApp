pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                script {
                    git 'https://github.com/Pysched/DarrensPetitions.git'
                }
            }
        }

        stage('Build and Package') {
            steps {
                script {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build 'my-tomcat-image'
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    docker.image('my-tomcat-image').inside('-p 9090:8080') {
                        sh 'cp target/*.war /usr/local/tomcat/webapps/'
                    }
                }
            }
        }
    }
}
