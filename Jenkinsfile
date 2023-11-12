pipeline {
    agent any
    options { disableConcurrentBuilds(abortPrevious: true) }
    stages {
           stage('Get Project') {
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
                       docker.image('tomcat:latest').inside('-p 9090:8080') {
                           sh 'cp target/*.war /usr/local/tomcat/webapps/'
                       }
                   }
               }
           }
       }
   }
