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

        stage('Deploy to Tomcat') {
            steps {
                script {
                    sh 'cp target/*.war /path/to/tomcat/webapps/'
                    sh '/path/to/tomcat/bin/shutdown.sh'
                    sh '/path/to/tomcat/bin/startup.sh'
                }
            }
        }
    }
}
