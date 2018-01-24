def goIdeaGradle = containerTemplate(
  name: 'jnlp', 
  image: 'docker.io/openshift/jenkins-slave-maven-centos7:latest',
  args: '${computer.jnlpmac} ${computer.name}',
  ttyEnabled: false) 

podTemplate(label: 'idea-gradle', cloud: "openshift", containers: [goIdeaGradle]) {
  node ("idea-gradle") {
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
}

