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

#### Prueba de getBlueprintsByAuthor()
##### Lo que se hizo es crear 3 BluePrints con un mismo autor e insertarlos en la memoria de Blueprints. Si la longitud de la lista de Blueprints creados por el autor es igual a un número específico (3 en este caso), eso quiere decir que la función si está revisando y enviando bien los blueprints del autor.

![img1](https://github.com/DiegoGonzalez2807/ARSW-LAB4-SEGUNDA-PARTE/blob/master/img/IMAGEN1.png)

3. Haga un programa en el que cree (mediante Spring) una instancia de BlueprintServices, y rectifique la funcionalidad del mismo: registrar planos, consultar planos, registrar planos específicos, etc.

4. Se quiere que las operaciones de consulta de planos realicen un proceso de filtrado, antes de retornar los planos consultados. Dichos filtros lo que buscan es reducir el tamaño de los planos, removiendo datos redundantes o simplemente submuestrando, antes de retornarlos. Ajuste la aplicación (agregando las abstracciones e implementaciones que considere) para que a la clase BlueprintServices se le inyecte uno de dos posibles 'filtros' (o eventuales futuros filtros). No se contempla el uso de más de uno a la vez:
	* (A) Filtrado de redundancias: suprime del plano los puntos consecutivos que sean repetidos.
	* (B) Filtrado de submuestreo: suprime 1 de cada 2 puntos del plano, de manera intercalada.

5. Agrege las pruebas correspondientes a cada uno de estos filtros, y pruebe su funcionamiento en el programa de prueba, comprobando que sólo cambiando la posición de las anotaciones -sin cambiar nada más-, el programa retorne los planos filtrados de la manera (A) o de la manera (B). 
