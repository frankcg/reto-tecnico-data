pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'frankcg' // Reemplaza con tu usuario de Docker Hub
        IMAGE_NAME = 'migraciones-poc'
        IMAGE_TAG = "${BUILD_NUMBER}"
        //KUBE_CONFIG = credentials('kube-config') // Credenciales de Kubernetes
        //env.KUBECONFIG = "${WORKSPACE}/kubeconfig"
    }

    stages {
        stage('Build Java Project') {
            steps {
                sh 'docker run --rm -v "$WORKSPACE":/app -w /app maven:3.9.6-eclipse-temurin-17 mvn clean package'
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
                    docker.withRegistry('', 'dockerhub-credentials') {
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
                        sh "kubectl apply -f k8s.yaml --kubeconfig=$KUBECONFIG"
                        sh "kubectl set image deployment/migraciones-poc-deployment migraciones-poc-container=${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG} --kubeconfig=$KUBECONFIG -n default"
                    }
                }
            }
        }
    }
}