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
            agent {
                docker {
                    image 'maven:3.8.1-adoptopenjdk-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }

            environment {
                HOME="."
            }
            
            steps {
                sh 'mvn install -DskipTests'
            }

            post {
                always {
                    archiveArtifacts artifacts: 'target/moviefetcher*.war', fingerprint: true
                }
            }
        }

        stage('Docker build image') {

            steps {
                step (
                    [$class: 'CopyArtifact',
                    projectName: '${JOB_NAME}',
                    filter: "moviefetcher*.war",
                    target: 'moviefetcher.war']
                );

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
