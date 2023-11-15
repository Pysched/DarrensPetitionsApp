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
                           script {
                               sh 'mvn clean package'
                               def warFile = sh(script: 'find . -name "*.war"', returnStdout: true).trim()
                               echo "War file location: ${warFile}"
                           }
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
                      artifacts: '**/DarrensPetitions.war'

            }
        }

                       stage('Deploy') {
                           steps {
                               script {
                                   echo 'Starting deployment process...'
                                   def userInput = input(
                                       message: 'Do you want to deploy to Tomcat?',
                                       parameters: [choice(choices: 'Yes|No', description: 'Choose Yes to deploy', name: 'Deploy')]
                                   )
                                   if (userInput == 'Yes') {
                                       echo 'Building Docker image...'
                                       sh 'docker build -f Dockerfile -t DarrensPetitions . '

                                       echo 'Removing existing tomcat container (if any)...'
                                       sh 'docker rm -f "tomcat-container" || true'

                                       echo 'Running Tomcat container...'
                                       sh 'docker run --name "tomcat-container" -p 9090:8080 -v $(pwd)/target/DarrensPetitions-1.0-SNAPSHOT.war:/usr/local/tomcat/webapps/DarrensPetitions.war --detach DarrensPetitions:latest'
                                   } else {
                                       echo 'Deployment aborted.'
                                   }
                               }
                           }
                       }

            }
        }
