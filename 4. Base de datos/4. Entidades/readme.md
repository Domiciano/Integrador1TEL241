# Relaciones de entidades en Springboot


```
import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String email;

    private String password;


    //ToDo: Hacer Getters y Setters
}
```
Donde @GeneratedValue(strategy = GenerationType.AUTO) se usa para indicar que la variable será INT y AUTO_INCREMENT

# Relación 1 a Muchos
Finalmente las relaciones de tablas que necesitará usar son: 1 a muchos y muchos a muchos. Supongo que tiene una relación entre las entidades Curso y Profesor. Para configurar la relación usando JPA, las clases se verán así:

```
import jakarta.persistence.*;

@Entity
@Table(name = "profesores")
public class Profesores {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "profesor") //Nombre de la propiedad en la otra clase
    private List<Cursos> cursos;
    
    //ToDo: Hacer Getters y Setters
}

```

```
import jakarta.persistence.*;

@Entity
@Table(name = "cursos")
public class Cursos {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;

    private String program;

    @ManyToOne
    @JoinColumn(name = "profeID")
    Profesores profesor;

    //ToDo: Hacer Getters y Setters
}
```



# Relación Muchos a Muchos
Suponga que tiene las entidades Estudiantes y Cursos. Para configurar la relación Muchos a Muchos entre estas dos tablas, necesita hacer lo siguiente:

```
import jakarta.persistence.*;

@Entity
@Table(name = "estudiantes")
public class Estudiantes {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    private String code;

    @ManyToMany
    @JoinTable(
            name = "estudiante_curso",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private List<Cursos> cursos;

    //ToDo: Hacer Getters y Setters
    
}
```
Note que usted define la tabla pivote a través de @JoinTable

```
import jakarta.persistence.*;

@Entity
@Table(name = "cursos")
public class Cursos {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;

    private String program;

    @ManyToOne
    @JoinColumn(name = "profeID")
    Profesores profesores;

    @ManyToMany
    List<Estudiantes> estudiantes;

    //ToDo: Hacer Getters y Setters
    
}

```


