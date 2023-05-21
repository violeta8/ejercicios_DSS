# EJERCICIO DE INYECCION DE DEPENDENCIAS
## Apartado a)
Rediseñamos el ejemplo de KnightOfTheRoundTable, para que no se implemente con java.lang.Object si no que a traves de la generalidad de la interfaz de Quest, para evitar posiblesClassCastException tal que:
`Quest.java`
```java
public interface Quest<T> {
    T embark() throws QuestFailedException;
}
```
`HolyGrailQuest.java`
```java
public class HolyGrailQuest implements Quest<HolyGrail> {
    @Override
    public HolyGrail embark() throws QuestFailedException {
        return new HolyGrail();
    }
}
```
`KnightOfTheRoundTable.java`
```java
public class KnightOfTheRoundTable implements Knight {
    private Quest<HolyGrail> quest;

    public KnightOfTheRoundTable(Quest<HolyGrail> quest) {
        this.quest = quest;
    }

    @Override
    public HolyGrail embarkOnQuest() throws QuestFailedException {
        return quest.embark();
    }
}
```
`HolyGrail.java`
```java
public class HolyGrail {}
```
`Knight.java`
```java
public interface Knight {
    HolyGrail embarkOnQuest() throws QuestFailedException;
}
```
## Apartado b)
Para esta parte se ha configurado el archivo `ApplicationConfig.java`
```java
@Configuration
public class ApplicationConfig {
    @Bean
    public Knight knight() {
        return new KnightOfTheRoundTable(quest());
    }

    @Bean
    public Quest<HolyGrail> quest() {
        return new HolyGrailQuest();
    }

    @Bean
    public HolyGrail holyGrail() {
        return new HolyGrail();
    }

}
```
En lugar de crear directamente una instancia de la búsqueda `HolyGrailQuest` dentro de la clase `KnightOfTheRoundTable`, el objeto `KnightOfTheRoundTable` recibe esta instancia a través de la inyección de dependencia. Esto se logra utilizando el contenedor de Spring y las anotaciones `@Configuration` y `@Bean`.

Para hacer uso del contenedor de Spring, se deben agregar las anotaciones `@Configuration` y `@Bean` a las clases y métodos correspondientes. Además, se necesita configurar el contexto de Spring en la aplicación y cargar el archivo de configuración `AppConfig`. Esto se puede realizar mediante una clase `Main`, en nuestro caso será el archivo `Application.java`:
```java
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		Knight knight = context.getBean(Knight.class);
		knight.embarkOnQuest();
		((AnnotationConfigApplicationContext) context).close();
	}
}
```