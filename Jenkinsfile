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
                bat 'docker build . -t ms-project/demo-ibm-cloud:0.0.1'
            }
        }
        // Deploy
        stage('Deploy') {
            steps {
                bat 'kubectl set image -n dev deployment/ms-person docker.io/ms-project/demo-ibm-cloud:0.0.1'
            }
        }
        //end
    }
  }
