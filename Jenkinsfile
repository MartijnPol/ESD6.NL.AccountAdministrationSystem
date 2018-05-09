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
                sh 'mvn compile'
                archiveArtifacts artifacts: 'target/', fingerprint: true
            }
        }
        stage('Build image'){
            steps{
                sh 'mvn clean package -B'
				sh 'docker build -t accountadministrationsystem .'
				sh 'docker tag accountadministrationsystem:latest esd6nl/aas'
				withDockerRegistry([ credentialsId: "dacebdd2-3f17-4ea6-93dd-2892e8ada2ef", url: "" ]) {
					sh 'docker push esd6nl/aas'
				}
                archiveArtifacts artifacts: 'target/AccountAdministrationSystem.war', fingerprint: true
            }

        }
        stage('Test sonarqube'){
            steps{
                sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.25.126:9000 -Dsonar.login=74881713522900d0ec5dc5a0ad9e303480b307a8'

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
            }
                branch 'development'
            steps{
                sh 'echo Deploying woop woop'
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