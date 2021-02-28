pipeline {
  agent any
    environment {
        def newVersion= "0.0.1";
    }
    // auto triggers
    triggers {
        pollSCM('H/5 * * * *')
    }
    stages {
        // Build
        stage('Build') {
            steps {
                // Clean before build
                cleanWs()
                // We need to explicitly checkout from SCM here
                checkout scm
                // sh mvn clean install
                bat 'mvn clean compile'
            }
            post {
                // Clean after build
                always {
                    cleanWs(cleanWhenNotBuilt: false,
                            deleteDirs: true,
                            disableDeferredWipeout: true,
                            notFailBuild: true,
                            patterns: [[pattern: '.gitignore', type: 'INCLUDE'],
                                       [pattern: '.propsfile', type: 'EXCLUDE']])
                }
            }
        }
        // Build
        stage('Test') {
            steps {
                bat 'mvn test'
            }
            post{
              always{
                junit "**/target/surefire-reports/TEST-*.xml"
              }
            }
        }
        // Build
        stage('Package') {
            steps {
                bat 'mvn package -Dmaven.test.skip=true'
            }
            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                bat 'docker build . -t ms-project/demo-ibm-cloud:0.0.2'
                bat 'echo the image to docker'
                bat 'docker push docker.io/ms-project/demo-ibm-cloud:0.0.2'

                bat 'echo the latest image to docker'
                bat 'docker tag docker.io/ms-project/demo-ibm-cloud:0.0.2 docker.io/ms-project/demo-ibm-cloud:latest'
                bat 'docker push docker.io/ms-project/demo-ibm-cloud:latest'

                bat 'Delete the image from jenkins'
                bat 'docker rmi -f ms-project/demo-ibm-cloud:0.0.2 ms-project/demo-ibm-cloud:latest'
            }
        }
        // Deploy
        stage('Deploy') {
            steps {
                bat 'kubectl set image -n dev deployment/demo-ibm-cloud demo-ibm-cloud=docker.io/ms-project/demo-ibm-cloud:0.0.2'
            }
        }
        //end
    }
  }
