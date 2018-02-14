node ("maven") {
  stage("Checkout") {
    checkout scm
  }
  
  stage ("Setup") {
    sh "./gradlew extractIdeaSdk"
  }
  
  stage ("Build") {
    sh "./gradlew dist"
    archiveArtifacts artifacts: 'build/distributions/*', onlyIfSuccessful: true
  }
}

