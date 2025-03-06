FROM jenkins/jenkins:lts

# Instalar Docker y Maven
USER root
RUN apt-get update && apt-get install -y \
    docker.io \
    maven \
    && apt-get clean

# No cambiamos de usuario, Jenkins correrá como root
