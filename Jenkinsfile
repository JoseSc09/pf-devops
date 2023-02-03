pipeline{
    agent any
    parameters{
        string(name: 'image_name', defaultValue: 'josesc09/suma-rest', description: 'Nombre de la imagen docker.')
        string(name: 'container_name', defaultValue: 'suma-rest', description: 'Nombre del contenedor docker.')
        string(name: 'container_port', defaultValue: '8085', description: 'Puerto que usa el contenedor.')
    }
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
                        bat "docker stop ${container_name}"
                        bat "docker rm ${container_name }"
                        bat "docker rmi ${image_name}"
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
        stage('Deploy'){
            steps{
                script{
                    bat "docker run -d -p ${container_port}:8085 --name ${container_name} ${image_name}"
                }
            }
        }
    }
}