pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    tools{
         jdk 'openjdk-11'
    }

    stages {

        stage('Preparation') {
            steps {
                git branch: 'main', url: 'https://github.com/pdisec2122/project-movie-fetcher.git'
                sh "git rev-parse --short HEAD > .git/commit-id"
                commit_id = readFile('.git/commit-id').trim()
            }
        }

        stage('Build') {
            environment {
                HOME="."
            }
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('docker build/push') {
            steps {
               docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                    def app = docker.build("pdisec2122/project-movie-fetcher")
                    app.push("${commit_id}")
                    app.push("latest")
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
