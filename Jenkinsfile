pipeline {
    
    agent any

    environment {
        imagename = "pdisec2122/project-movie-fetcher"
        registryCredential = 'dockerhub'
        dockerImage = ''
    }


    stages {

        stage('Cloning Git') {
            steps {
                git([url: 'https://github.com/pdisec2122/project-movie-fetcher.git', 
                    branch: 'main'])

            }
        }

        stage('Build Java Application') {
            environment {
                HOME="."
            }
            agent {
                docker {
                    image 'maven:3.8.1-adoptopenjdk-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Docker build image') {
            steps {
                script {
                    dockerImage = docker.build imagename
                }
            }
        }

        stage('Deploy Image') {
            steps{
                script {
                    docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
                        dockerImage.push("$BUILD_NUMBER")
                        dockerImage.push('latest')
                    }
                }
            }
        }
    
    }

    post {
        always {
            echo "Finished"
        }
    }

}
