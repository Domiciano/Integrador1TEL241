Debe tener en cuenta las capas de:

## 1. Controller
Esta capa está encargada de recibir las solicitudes del frontend. Aquí programará los GET, POST, PUT o DELETE.<br>
Tenga en cuenta:<br>
<ol>
    <li>GET. Úselo cuando quiere consultar información a la base de datos</li>
    <li>POST. Úselo cuando quiere agregar un nuevo registro a la base de datos</li>
    <li>PUT. Úselo cuando quiere agregar/reemplazar/modificar un registro en la base de datos</li>
    <li>DELETE. Úselo cuando quiere eliminar información de la base de datos</li>
</ol>
Un controller se ve así:

```
@RestController
public class EchoController {

    @Autowired
    MyRepository myRepository;

    @GetMapping("echo")
    public String echo(){
        return "echo";
    }
    
    @PostMapping("another")
    public ResponseEntity<?> another(){
        return ResponseEntity.status(200).body("Another");
    }

}
```

## 2. Repository
En un repository, va el CRUD (Create, Read, Update, Delete) de cada una de las entidades. Por ejemplo si tenemos una entidad User, un repository puede verse así

```
public interface UserRepositories extends CrudRepository<User, Integer> {

}
``` 

Donde <User, Integer> el el tipo de dato de la entidad y el tipo de dato de la llave primaria de esa entidad. La interfaz por defecto tendrá los métodos save(User user), deleteById(Integer id), deleteAll(), findAll(), findById(), entre otros. 

La clase repository es llamada por la clase controller donde se necesite usar.


## 3. Entidad
Finalmente tenemos la entidad. Esta representa una tabla dentro de la base de datos y se creará automáticamente en cuanto almacene documentos mediante save().

Una entidad se ve así

```
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String email;

    private String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
```
Donde @GeneratedValue(strategy = GenerationType.AUTO) se usa para indicar que la variable será INT y AUTO_INCREMENT

## 4. Relación 1 a Muchos
Finalmente las relaciones de tablas que necesitará usar son: 1 a muchos y muchos a muchos. Supongo que tiene una relación entre las entidades Curso y Profesor. Para configurar la relación usando JPA, las clases se verán así:

```
@Entity
@Table(name = "profesores")
public class Profesores {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "profesores") //Nombre de la propiedad en la otra clase
    @JsonIgnore
    private Set<Cursos> cursos;
    
    //No olvidar los Getters y Setters   
}

```

```
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
    Set<Estudiantes> estudiantes;

    //No olvidar los Getters y Setters
}
```



## 4. Relación Muchos a Muchos
Suponga que tiene las entidades Estudiantes y Cursos. Para configurar la relación Muchos a Muchos entre estas dos tablas, necesita hacer lo siguiente:

```
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

    //No olvidar Getters y Setters
    
}
```
Note que usted define la tabla pivote a través de @JoinTable

```
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

    //No olvidar Getters y Setters
    
}

```


