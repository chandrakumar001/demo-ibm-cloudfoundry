def newVersion
pipeline {
  agent any
     // auto triggers
     //${newVersion}
     // %newVersion%
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
        stage('Info') {
          steps {
            script{
                  def pom = readMavenPom file: 'pom.xml'
                  newVersion=pom.version
                  printf("Test Version: %s", pom.version)
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
                bat '''
                'docker build . -t localhost:50000/ms-project/demo-ibm-cloud:'+newVersion
                'docker build . -t localhost:50000/ms-project/demo-ibm-cloud:'+newVersion
                'echo the image to docker'
                'docker push localhost:50000/ms-project/demo-ibm-cloud:'+newVersion

                'echo the latest image to docker'
                'docker tag localhost:50000/ms-project/demo-ibm-cloud:'+newVersion+' localhost:50000/ms-project/demo-ibm-cloud:latest'
                'docker push localhost:50000/ms-project/demo-ibm-cloud:latest'

                'echo Delete the image from jenkins'
                'docker rmi -f localhost:50000/ms-project/demo-ibm-cloud:'+newVersion+' localhost:50000/ms-project/demo-ibm-cloud:latest'
               '''

            }
        }
        // Deploy
        stage('Deploy') {
            steps {
                bat 'echo Deploy'
                bat 'kubectl set image deployment/demo-ibm-cloud demo-ibm-cloud=localhost:50000/ms-project/demo-ibm-cloud:'+newVersion
            }
        }
        //end
    }
  }