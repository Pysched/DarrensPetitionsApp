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

        stage('Package & Archive') {
            steps {
                sh 'cp target/DarrensPetitions-1.0-SNAPSHOT.war DarrensPetitions.war'
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
                        sh 'cp DarrensPetitions.war /opt/tomcat/webapps/'
                        sh '/opt/tomcat/bin/shutdown.sh' // Stop Tomcat if it's running
                        sh '/opt/tomcat/bin/startup.sh'  // Start Tomcat
                    }
                }
            }
        }
    }
}
