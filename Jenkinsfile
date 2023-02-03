pipeline{
    agent any
    tools{
        maven 'maven'
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/JoseSc09/pf-devops.git']])
                bat "mvn clean install"
            }
        }
        stage('Build docker image'){
            steps{
                script{
					try{
						bat "docker rmi josesc09/suma-rest"
					} catch (Exception e){
						bat "echo 'Exception ocurred: '+ e.toString()"
					}
                    bat "docker build -t josesc09/suma-rest ."
                }
            }
        }
        stage('Push image to hub'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                        bat "docker login -u josesc09 -p ${dockerhubpwd}"
                    }
                    bat "docker push josesc09/suma-rest"
                }
            }
        }
    }
}