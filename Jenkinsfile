pipeline {
    agent any
    options { disableConcurrentBuilds(abortPrevious: true) }
    stages {
        stage ('GetProject') {
            steps {
                git 'https://github.com/Pysched/DarrensPetitions.git'
            }
        }
        stage ('build') {
            steps {
                sh 'mvn clean:clean'
                sh 'mvn dependency:copy-dependencies'
                sh 'mvn compiler:compile'
            }
        }
        stage ('Exec') {
            steps {
                sh 'mvn spring-boot:run'
            }
        }
    }
}
