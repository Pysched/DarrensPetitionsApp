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
                   def userInput = input(
                       message: 'Do you want to deploy to Tomcat?',
                       parameters: [choice(choices: 'Yes|No', description: 'Choose Yes to deploy', name: 'Deploy')]
                   )
                   if (userInput == 'Yes') {
                       // Build Docker image and copy WAR file to Tomcat webapps directory
                       echo "Building Docker image..."
                       sh "docker build -f Dockerfile -t darrenspetitions-tomcat ."
                       echo "Docker image built successfully."

                       echo "Removing existing container..."
                       sh "docker rm -f tomcat-container || true"
                       echo "Container removed successfully."

                       // Run Docker container, mount the WAR file into the webapps directory
                       echo "Starting Docker container..."
                       sh "docker run --name tomcat-container -p 9090:8080 -v $(pwd)/target/DarrensPetitions-1.0-SNAPSHOT.war:/usr/local/tomcat/webapps/DarrensPetitions.war --detach darrenspetitions-tomcat"
                       echo "Container started successfully."
                   } else {
                       echo "Deployment to Tomcat skipped."
                   }
               }
           }
       }
    }
}
