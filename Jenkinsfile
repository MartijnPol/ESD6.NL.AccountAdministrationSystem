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
            agent {
                docker {
                    image 'maven'
                    reuseNode true
                }
            }
            steps {
                sh 'mvn compile'
                archiveArtifacts artifacts: 'target/', fingerprint: true
            }
        }
        stage('Build image'){
            steps{
				sh 'docker build -t accountadministrationsystem .'
				sh 'docker tag accountadministrationsystem:latest localhost:5000/aas'
				sh 'docker push localhost:5000/aas'
                sh 'mvn clean package -B'
                archiveArtifacts artifacts: 'target/AccountAdministrationSystem.war', fingerprint: true
            }

        }
        stage('Test sonarqube'){
            steps{
                sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.25.121:9000 -Dsonar.login=33d2bf0931e4ba870789a1cf8e6276a20de55fe1'

            }
        }
        stage('Deploy development'){
            agent {
                docker {
                    image 'docker:17.12-dind'
                    args '-v /var/run/docker.sock:/var/run/docker.sock'
                    reuseNode true
                }
            }
            when{
                branch 'development'
            }
            steps{
                sh 'docker stack deploy -c dev.yml accountadministrationsystem'
            }
        }
        stage('Deploy master'){
            when{
                branch 'master'
            }
            steps{
                sh 'mvn clean package -B'
            }
        }
        stage('Deploy release'){
            when{
                branch 'release'
            }
            steps{
                sh 'mvn clean package -B'
            }
        }
    }
}