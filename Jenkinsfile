pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    tools{
         jdk 'jdk11'
    }
    stages {
        stage('configFile Plugin') {
            steps {
                configFileProvider([configFile(fileId: '003c15d9-32f5-4f03-b320-1ea95c142320', variable: 'APPLICATION_PROPERTIES')]) {
                    echo " =========== ^^^^^^^^^^^^ Reading config from pipeline script "
                    sh "touch application.properties"
                    sh "cat ${env.APPLICATION_PROPERTIES} > application.properties"
                    echo " =========== ~~~~~~~~~~~~ ============ "
                }
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
    }
}