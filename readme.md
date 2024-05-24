<img width="256" src="https://www.icesi.edu.co/launiversidad/images/La_universidad/logo_icesi.png">

https://4592-200-3-193-228.ngrok-free.app

# Proyecto Integrador 1
Este es el repositorio del curso. Ejemplos de código, acceso a videos, etc. serán accesibles desde aquí.



# Informe final

Realice un informe en el que adjunte como capítulos los elementos de diseño que ha presentado a lo largo del semestre: Introducción, Referencias, Problema, Justificación, Requerimientos, el diagrama de diseño de la base de datos, diseño de bloques SysML y el product backlog. Los anteriores puntos puede dejarlos inalterados. <b>EVITE INCLUIR EN ESTE ESCRITO LINKS, TODO DEBERÍA ESTAR CONTENIDO EN EL TRABAJO</b>

Adicionalmente adjunte un capitulo llamado "Marco conceptual y técnico" donde liste todos los conceptos técnicos y tecnológicos que necesitaron para realizar el proyecto integrador. Cada item de esta lista debe tener una descripción donde se argumente por qué es necesario para el proyecto. Categorice los elementos de esta lista de acuerdo a si es parte de front, back, base de datos, hardware o protocolos de comunicación.




# Planeador

<a href="https://docs.google.com/spreadsheets/d/1a1aBU54QwW8XBjM-VW-ZW_pWia9nEnEH/edit?usp=sharing&ouid=117897710133227559254&rtpof=true&sd=true"><small>Planeador de Proyecto Integrador 1</small></a>

# Grupo de Whatsapp
<a href="https://chat.whatsapp.com/L7K2iFExtxyE93MxEbq552"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/WhatsApp.svg/479px-WhatsApp.svg.png" width="128"></a><br>

<a href="https://chat.whatsapp.com/L7K2iFExtxyE93MxEbq552">Grupo de Whatsapp</a>


# Elementos de referencia

<a href="https://miro.com/app/board/o9J_l23kC64=/?share_link_id=159122822699"><img width="128" src="https://store-images.s-microsoft.com/image/apps.59334.13959754522315136.c4ea2415-8e3c-42bf-8f77-e885eb7c11a1.be6eacf3-e0b4-4478-9abc-47192806c1b5?mode=scale&q=90&h=300&w=300"></a><br>


# Comando básicos de git

## Comandos clave:
</br>

```
git init
```
Para que una carpeta normal del sistema se vuelva un repositorio (tiene una carpeta oculta llamada .git)
</br></br>
```
git status
```
Para mirar los cambios actualues 
</br></br>
```
git add .
```
Para agregar todos los cambios al stage area
</br></br>
```
git commit -m "Mensaje"
```
Para crear un commit con los cambios del stage area
</br></br>
```
git log --oneline
```
Para ver el historial de commits
</br></br>

```
git log --oneline --graph
```
Para ver el historial de commits, pero más gráficamente
</br></br>


### Remotos
```
git remote -v 
```
Permite listar los remotos de nuestro repositorio local
</br></br>


```
git remote add <nombreRemoto> <URLremoto> 
```
Permite agregar un remoto con un nombre específico
</br></br>


```
git remote rm <nombreRemoto> 
```
Permite quitar un remoto
</br></br>


### Operaciones con remotos

```
git push <nombreRemoto> <nombreBranch> 
```
Permite actualizar un remoto con el trabajo hecho en el repositorio local
</br></br>

```
git pull <nombreRemoto> <nombreBranch> 
```
Permite actualizar una rama local usando una rama remota como fuente de actualización. Tener en cuenta que debemos estar parados en una rama local homónima a la que estamos invocando con el comando, porque si no, estamos mezclando las ramas
</br></br>

```
git fetch -p 
```
Permite actualizar las ramas respecto al repositorio remoto
</br></br>

```
git push --delete origin <nombreDeRama> 
```
Permite eliminar la rama nombreDeRama del remoto
</br></br>


### Branches

```
git branch <nombreBranch>
```
Permite crear una nueva rama
</br></br>

```
git checkout -b <nombreBranch>
```
Permite crear una nueva rama y pararse en ella en un sólo comando
</br></br>

```
git branch -a
```
Listar todas las ramas
</br></br>

```
git merge <nombreRama>
```
Permite mezclar la rama nombreRama en la rama en la que estemos parados. Hay 3 posibilidades: fast-fordward, recursive strategy o conflictos. En los dos últimos casos tendremos un commit específico que representa la mezcla.
</br></br>

```
git branch -d
```
Permite eliminar una rama local
</br></br>

```
git branch -D
```
Permite eliminar una rama local a la fuerza
</br></br>
