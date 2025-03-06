pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'frank.cg9@gmail.com' // Reemplaza con tu usuario de Docker Hub
        IMAGE_NAME = 'migraciones-poc'
        IMAGE_TAG = "${BUILD_NUMBER}"
        //KUBE_CONFIG = credentials('kube-config') // Credenciales de Kubernetes
        //env.KUBECONFIG = "${WORKSPACE}/kubeconfig"
    }

    stages {
        stage('Build Java Project') {
            steps {
                tool name: 'Maven 3.9.9'
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}", '.')
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry("https://index.docker.io/v1/", 'dockerhub-credentials') {
                        docker.image("${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}").push()
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    //withKubeConfig([credentialsId: 'kube-config']) {
                    withCredentials([string(credentialsId: 'kubeconfig-secret', variable: 'KUBECONFIG')]) {
                        sh "kubectl apply -f k8s.yaml -n default"
                        sh "kubectl set image deployment/migraciones-poc-deployment migraciones-poc-container=${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG} -n default"
                    }
                }
            }
        }
    }
}