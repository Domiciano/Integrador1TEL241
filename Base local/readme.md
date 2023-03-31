Guarde el siguiente archivo como docker-compose.yml
```
services:
  db:
    command: ["--max_connections=1000"]
    image: mysql:5.7
    restart: always
    environment:
      MYSQL_DATABASE: 'db'
      MYSQL_USER: 'user'
      MYSQL_PASSWORD: 'password'
      MYSQL_ROOT_PASSWORD: 'password'
    ports:
      - '3306:3306'
    expose:
      - '3306'
    volumes:
      - my-db:/var/lib/mysql
volumes:
  my-db:
```

Luego, use el comando
```
docker-compose up -d
```

Con esto tendrá una base de datos local en 127.0.0.1:3306. Su usuario es user, su password es password y la base de datos se llamará db
