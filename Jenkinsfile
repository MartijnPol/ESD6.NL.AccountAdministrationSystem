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

                archiveArtifacts artifacts: 'target/AccountAdministrationSystem.war', fingerprint: true
            }

        }
		stage('Push image'){
			steps{
				withCredentials([usernamePassword(credentialsId: 'docker', passwordVariable: 'dockerPassword', usernameVariable: 'dockerUser')]) {
					sh "docker login -u ${env.dockerUser} -p ${env.dockerPassword}"
					sh 'docker push esd6nl/aas'
			}
			}
		}
        stage('Test sonarqube'){
            steps{
                sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.25.126:9000 -Dsonar.login=74881713522900d0ec5dc5a0ad9e303480b307a8'

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