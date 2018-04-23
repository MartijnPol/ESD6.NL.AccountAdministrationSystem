pipeline{
    agent any
    tools {
         maven 'maven'
         jdk 'jdk8'
     }
    stages{
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }
        stage('Build project'){
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true compile'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
        }
        }
        stage('Build image'){


        }
        stage('Test sonarqube'){
            steps{
                sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.25.121:9001 -Dsonar.login=33d2bf0931e4ba870789a1cf8e6276a20de55fe1'

            }
        }
        stage('Deploy development'){
            when{
                branch 'development'
            }
            steps{
                sh 'docker stack deploy -c compose-dev.yml AccountAdministrationSystem'
            }
        }
        stage('Deploy master'){
            when{
                branch 'master'
            }
            steps{

            }
        }
        stage('Deploy release'){
            when{
                branch 'release'
            }
            steps{

            }
        }
    }
}