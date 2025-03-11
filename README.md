# Frank Laura Borja

**Herramientas Aplicadas :**
```sh
>Spring Data JPA
>H2 Database
>Spring Web
>Lombok
>Hibernate
>Java 17
>Maven
```
**Consideraciones:**
>Se realizo el mantenimiento de tipos de cambio
>En la rama feat/devops-poc se crearon los siguiente archivos
```sh
>Dockerfile
>Jenkisfile
>k8s.yaml
>kubeconfig -> estos certificados se obtienen de la ruta C:\Users\Frank Laura\.kube\config
>docker-compose.yml -> esto para levantar el jenkins (deberia de ir en un repositorio aparte)

```

**Resultado:**
>Se construyo un pipeline para desplegar el backend en kubernetes. El servicio se encuentra corriendo.
>Se puede comprobar el servicio funcionando con el siguiente curl

curl --location 'http://localhost:80/api/v1/exchange' \
--header 'Cookie: JSESSIONID.2756d9d9=node018yb4kpencw257tlpvpzi36or66.node0; JSESSIONID.781285af=node0ixu4296n2ac41eu8feelrajkj7.node0'


