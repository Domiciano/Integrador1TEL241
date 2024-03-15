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
public interface UserRepository extends CrudRepository<User, Long> {

}
``` 

Donde <User, Long> el el tipo de dato de la entidad y el tipo de dato de la llave primaria de esa entidad. La interfaz por defecto tendrá los métodos save(User user), deleteById(Integer id), deleteAll(), findAll(), findById(), entre otros. 

La clase repository es llamada por la clase controller donde se necesite usar.


## 3. Acciones
Todas las acciones y consultas se hacen por medio del repositorio.<br><br>

### Obtener todos los registros de la tabla 
```
repository.findAll()
```
### Insertar datos
```
repository.save(user)
```
Donde user es una instancia de la entidad User

### Obtener registros con filtro de búsqueda
En el repositorio debe escribir la consulta

```
@Query("SELECT u FROM User u WHERE u.name = :name")
List<User> findUsersByVehicleBrand(@Param("name") String name);
```

Una consulta más avanzada se ve así
```
@Query("SELECT u FROM User u JOIN u.vehicleList v WHERE v.brand = :brand")
List<User> findUsersByVehicleBrand(@Param("brand") String brand);
```



