pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn -B -Dtest=AllTestRunner test'
                    } else {
                        bat 'mvn -B -Dtest=AllTestRunner test'
                    }
                }
            }
        }
        stage('Publish Reports') {
            steps {
                junit 'target/surefire-reports/*.xml'
                cucumber buildStatus: 'UNSTABLE', fileIncludePattern: 'target/cucumber-report/cucumber.json'
                archiveArtifacts artifacts: 'target/surefire-reports/**', fingerprint: true
            }
        }
    }
}
