pipeline {
  agent any
    environment {
        //def newVersion= "0.0.3";
        def newVersion= "0.0.3";

        // read info from pom (see: http://maven.apache.org/components/ref/3.3.9/maven-model/apidocs/org/apache/maven/model/Model.html)
        def  pom = readMavenPom file: 'pom.xml'
        def  verson=pom.version
        bat "Version: %verson%"
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
                //${newVersion}
               // %newVersion%
                bat 'docker build . -t localhost:50000/ms-project/demo-ibm-cloud:%newVersion%'
                bat 'echo the image to docker'
                bat 'docker push localhost:50000/ms-project/demo-ibm-cloud:%newVersion%'

                bat 'echo the latest image to docker'
                bat 'docker tag localhost:50000/ms-project/demo-ibm-cloud:%newVersion% localhost:50000/ms-project/demo-ibm-cloud:latest'
                bat 'docker push localhost:50000/ms-project/demo-ibm-cloud:latest'

                bat 'echo Delete the image from jenkins'
                bat 'docker rmi -f localhost:50000/ms-project/demo-ibm-cloud:%newVersion% localhost:50000/ms-project/demo-ibm-cloud:latest'
            }
        }
        // Deploy
        stage('Deploy') {
            steps {
                bat 'echo Deploy'
                bat 'kubectl set image deployment/demo-ibm-cloud demo-ibm-cloud=localhost:50000/ms-project/demo-ibm-cloud:%newVersion%'
            }
        }
        //end
    }
  }
