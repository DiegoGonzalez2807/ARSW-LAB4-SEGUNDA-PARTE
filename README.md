## Escuela Colombiana de Ingeniería

## Diego Alejandro González - Cristian Andrés Castellanos

## Arquitecturas de Software 

# Componentes y conectores - Parte I.

El ejercicio se debe traer terminado para el siguiente laboratorio (Parte II).

#### Middleware- gestión de planos.


## Antes de hacer este ejercicio, realice [el ejercicio introductorio al manejo de Spring y la configuración basada en anotaciones](https://github.com/ARSW-ECI/Spring_LightweightCont_Annotation-DI_Example).

En este ejercicio se va a construír un modelo de clases para la capa lógica de una aplicación que permita gestionar planos arquitectónicos de una prestigiosa compañia de diseño. 

![](img/ClassDiagram1.png)

1. Configure la aplicación para que funcione bajo un esquema de inyección de dependencias, tal como se muestra en el diagrama anterior.


	Lo anterior requiere:

	* Agregar las dependencias de Spring.
	* Agregar la configuración de Spring.
	* Configurar la aplicación -mediante anotaciones- para que el esquema de persistencia sea inyectado al momento de ser creado el bean 'BlueprintServices'.
##### Configuración de anotaciones
###### Configuración BluePrintsServices
```java
@Service
public class BlueprintsServices {

    @Autowired
    @Qualifier("Memory")
    BlueprintsPersistence bpp;
```
###### Configuración InMemoryBluePrintsServices
```java
@Component
@Qualifier("Memory")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{
```
##### Si se revisa el diagrama de clases del ejercicio, nos dice que inMemory es un hijo de la clase padre BlueprintsPersistence y este padre va a ser inyectado a través de una variable que se encuentra en BlueprintsServices. Usamos inMemory gracias a la etiqueta @Qualifier, donde le damos la instrucción a la interfaz cual es el hijo escogido para usar sus funciones.


2. Complete los operaciones getBluePrint() y getBlueprintsByAuthor(). Implemente todo lo requerido de las capas inferiores (por ahora, el esquema de persistencia disponible 'InMemoryBlueprintPersistence') agregando las pruebas correspondientes en 'InMemoryPersistenceTest'.
##### Implementación getBluePrint() en los servicios
```java
public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException {
        return bpp.getBlueprint(author, name);
    }
```
#### Implementación getBlueprintsByAuthor() en los inMemory
##### Lo que se hace es inicializar un hashSet, este ayuda a revisar si un elemento está repetido en la lista. Se revisa el arreglo de Blueprints que nos dan por memoria y revisamos el primer elemento de la tupla<String,String> donde en caso que sea igual que el autor buscado, se manda todo el blueprint al hashset.
```java
@Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        //El hashset ayuda a determinar si un objeto ya esta en la lista o no mediante la matriz. Misma funcion que Set
        Set<Blueprint> prints = new HashSet<>();
        for(Tuple<String,String> tuple: this.blueprints.keySet()){
            if(tuple.o1.equals(author)){
                prints.add(blueprints.get(tuple));
            }
        }
        return prints;
    }
```

#### Prueba de getBlueprintsByAuthor()
##### Lo que se hizo es crear 3 BluePrints con un mismo autor e insertarlos en la memoria de Blueprints. Si la longitud de la lista de Blueprints creados por el autor es igual a un número específico (3 en este caso), eso quiere decir que la función si está revisando y enviando bien los blueprints del autor.

![img1](https://github.com/DiegoGonzalez2807/ARSW-LAB4-SEGUNDA-PARTE/blob/master/img/IMAGEN1.png)

3. Haga un programa en el que cree (mediante Spring) una instancia de BlueprintServices, y rectifique la funcionalidad del mismo: registrar planos, consultar planos, registrar planos específicos, etc.
#### Implementación de Spring en Maven 

##### Lo primero que se tiene que hacer es colocar en el pom la siguiente dependecia así como la etiqueta parent en el archivo. Esto con el fin de que al iniciar maven, nos acepte comandos como "mvn spring-boot:run"
``` xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.1.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
```
``` xml
    	<dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.1.3.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.1.3.RELEASE</version>
        </dependency>
	<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
```

##### Con las dependencias puestas, podemos empezar a crear el main de BluePrints. Este tiene una nueva dependencia la cuál será @SpringBootApplication. Esta dependencia sirve para autoconfigurar nuestra clase principal en la aplicación. También tendremos una implementación llamada " CommandLineRunner". Esta implementación es una simple interfaz de Spring Boot con un método de ejecución. Spring Boot llamará automáticamente al método de ejecución de todos los beans que implementen esta interfaz después de cargar el contexto de la aplicación.
```java
@SpringBootApplication
public class main_Blueprints implements CommandLineRunner {

    @Autowired
    BlueprintsServices services;

    public static void main(String[] args) {
        SpringApplication.run(main_Blueprints.class,args);
    }
    @Override
    public void run(String... args) throws Exception{}
```
##### Con este código decimos que vamos a inyectar BlueprintServices en una variable a partir de la dependencia @Autowired. Como se puede observar, con CommandLineRunner podemos sobreescribir el método run de la clase principal. Esto se usará para que en run() se pueda definir los autores y cada que corra esta inserte los planos de cada autor, así como las pruebas donde se muestra el buen funcionamiento.

![img1](https://github.com/DiegoGonzalez2807/ARSW-LAB4-SEGUNDA-PARTE/blob/master/img/IMAGEN2.png)

4. Se quiere que las operaciones de consulta de planos realicen un proceso de filtrado, antes de retornar los planos consultados. Dichos filtros lo que buscan es reducir el tamaño de los planos, removiendo datos redundantes o simplemente submuestrando, antes de retornarlos. Ajuste la aplicación (agregando las abstracciones e implementaciones que considere) para que a la clase BlueprintServices se le inyecte uno de dos posibles 'filtros' (o eventuales futuros filtros). No se contempla el uso de más de uno a la vez:
	* (A) Filtrado de redundancias: suprime del plano los puntos consecutivos que sean repetidos.
	* (B) Filtrado de submuestreo: suprime 1 de cada 2 puntos del plano, de manera intercalada.
	*
##### El contexto par la creación de los filtros y su abstracción es a partir de la abstracción de blueprints.
![img1](https://github.com/DiegoGonzalez2807/ARSW-LAB4-SEGUNDA-PARTE/blob/master/img/IMAGEN3.png)
##### Como se puede mirar en la imagen, tenemos un FilterService, el cual es el encargado de dar las funciones de filtrado al usuario. Este tiene una variable la cuál se le pone la etiqueta @Autowired y @Service debido a que esa variable va a ser inyectada con el tipo de filtro (Recordar que solo puede estar en funcionamiento uno)
```java
@Service
public class FilterService {
    @Autowired
    @Qualifier("Redundancy")
    filterType filter;

    public void filterBlueprint(Blueprint bp) throws BlueprintNotFoundException {
        filter.filterBlueprint(bp);
    }
    public void filterBlueprints(Set<Blueprint> blueprints) throws BlueprintNotFoundException, BlueprintPersistenceException {
        filter.filterBlueprints(blueprints);
    }
```
##### Esta variable responde a una clase filterType. Esta es una interfaz la cuál tiene 3 métodos. Todos estos son de filtrado, tanto individual, como también por un arreglo de blueprints. También se implementa la filtración de blueprints de acuerdo al autor que quiera filtrar el usuario.
```java
public interface filterType {

    public void filterBlueprint(Blueprint bp) throws BlueprintNotFoundException;
    public void filterBlueprints(Set<Blueprint> blueprints) throws BlueprintPersistenceException, BlueprintNotFoundException;
    public void filterPrintsByAuthor(String author, Set<Blueprint> blueprints) throws BlueprintNotFoundException;
}
```
##### Tenemos dos hijos de la interfaz filterType: filterRedundancy y filterSub. El primero revisa el blueprint y revisa los puntos de este en busca de repetidos. En caso que los encuentre, los elimina y retorna una lista sin ningún punto repetido. El segundo filtro se encarga de eliminar los puntos del blueprint de manera intercalada.

#### Funcion principal de filtrado de redundancia (Se encuentra en la clase filterRedundancy.java)
```java
public void review(Blueprint bp, Point point){
        List<Point> points = new ArrayList<Point>(bp.getPoints());
        for(int i = 0; i<=points.size()-1;i++){
            if(point.equals(points.get(i))){
                points.remove(i);
            }
        }
        points.add(point);
        bp.setPoints(points);
    }
```
#### Funcion principal de filtrado de submuestreo (Se encuentra en la clase filterSub.java)
```java
public void filterBlueprint(Blueprint bp) throws BlueprintNotFoundException {
        List<Point> points = new ArrayList<Point>(bp.getPoints());
        List<Point> pointsFilter = new ArrayList<Point>();
        int size = points.size();
        for(int i = 0; i<points.size();i++){
            //System.out.println("Index :"+i);
            if(i%2 == 1){
                //System.out.println("Size: "+points.size()+"Indice: "+i);
                pointsFilter.add(points.get(i));
                //System.out.println(pointsFilter);
            }
        }
        bp.setPoints(pointsFilter);
    }
```

5. Agrege las pruebas correspondientes a cada uno de estos filtros, y pruebe su funcionamiento en el programa de prueba, comprobando que sólo cambiando la posición de las anotaciones -sin cambiar nada más-, el programa retorne los planos filtrados de la manera (A) o de la manera (B). 
