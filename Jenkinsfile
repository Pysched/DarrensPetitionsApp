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

                       stage ('Deploy') {
                           steps {
                            script {
                                   def userInput = input(
                                       message: 'Do you want to deploy to Tomcat?',
                                       parameters: [choice(choices: 'Yes|No', description: 'Choose Yes to deploy', name: 'Deploy')]
                                   )
                                   if (userInput == 'Yes') {
                                       // Copy the WAR file into the Tomcat container's webapps directory
                                       sh 'docker build -f Dockerfile -t DarrensPetitions . '
                                       sh 'docker rm -f "tomcat-container" || true'
                                       sh 'docker run --name "tomcat-container" -p 9090:8080 --detach DarrensPetitions:latest'
                                   }
                               }

                           }
                       }
            }
        }
