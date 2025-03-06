FROM jenkins/jenkins:lts

# Usar usuario root para instalar paquetes
USER root

# Instalar Docker, Maven y Curl
RUN apt-get update && apt-get install -y \
    docker.io \
    maven \
    curl \
    && apt-get clean

# Descargar e instalar kubectl
RUN curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" \
    && chmod +x kubectl \
    && mv kubectl /usr/local/bin/

# Verificar la instalación
RUN kubectl version --client
