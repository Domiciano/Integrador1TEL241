## Controller
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

## 4. Repository
En un repository, va el CRUD (Create, Read, Update, Delete) de cada una de las entidades. Por ejemplo si tenemos una entidad User, un repository puede verse así

```
public interface UserRepositories extends CrudRepository<User, Integer> {

}
``` 

Donde <User, Integer> el el tipo de dato de la entidad y el tipo de dato de la llave primaria de esa entidad. La interfaz por defecto tendrá los métodos save(User user), deleteById(Integer id), deleteAll(), findAll(), findById(), entre otros. 

La clase repository es llamada por la clase controller donde se necesite usar.
