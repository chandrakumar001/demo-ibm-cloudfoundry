pipeline {
  agent any
    stages {
      stage ('Build') {
        steps{
          // Run the maven build
          sh "mvn clean verify"
        }
      }
    }
}