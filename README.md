# IT-24006 CampusBoot

A tiny Spring-Boot-style framework built with **custom annotations** and **Java Reflection**, with no external libraries. It shows that `@Component`, `@Value`, `@GetMapping` and friends are just annotations plus reflection.

- **Language:** Java 11+ (tested with JDK 21 and JDK 25)
- **Dependencies:** none

## Project structure

| File | Part | Description |
|------|------|-------------|
| `Component.java` | A | Marks a class to be created and managed by the container (`value()` = bean name, default `""`) |
| `Value.java` | A | Marks a field to receive a configuration value (`value()` = config key) |
| `Range.java` | A | Validates an `int` field (`min()`, `max()`, `message()` default `"Value out of range"`) |
| `Route.java` | A | Maps a URL path to a method (`path()`; `value()` also accepted for the `@Route("/x")` shorthand) |
| `Container.java` | B | IoC container: `scan(...)`, `injectValues(...)`, `getBean(...)` |
| `Validator.java` | C | `static List<String> validate(Object)` reads `@Range` fields |
| `Router.java` | D | `dispatch(Container, path)` invokes the matching `@Route` method or returns a 404 message |
| `GradeController.java` | starter | Sample `@Component` with `@Value` fields and `@Route` methods |
| `ReportPrinter.java` | starter | Has no annotation, so it is skipped by the container |
| `Student.java` | starter | Sample class with `@Range` fields |
| `Main.java` | starter | Demo that wires everything together |
| `answers.txt` | E | Reflection questions (short answers) |

All annotations use `@Retention(RetentionPolicy.RUNTIME)` so they can be read by reflection at runtime.

## How it works

1. **Scan:** `Container.scan` checks each class for `@Component`. Annotated classes are instantiated through the no-arg constructor and stored in a `Map<String,Object>`. The bean name is `value()`, or the simple class name with a lowercase first letter if `value()` is empty. Other classes are skipped.
2. **Inject:** `Container.injectValues` finds fields marked `@Value`, looks up the key in the config map, converts to `int` when needed, and sets the field with `setAccessible(true)`.
3. **Validate:** `Validator.validate` checks every `@Range` `int` field and reports violations as `fieldName - message (actual: value)`.
4. **Route:** `Router.dispatch` searches all beans for a method whose `@Route` path equals the request path, invokes it, and returns the result. If nothing matches it returns `404 Not Found: <path>`.



##  output

```
<img width="972" height="605" alt="Output" src="https://github.com/user-attachments/assets/2dc84d7c-5560-4ecf-b8b5-0c520011a5a3" />

```

## Notes

- The starter code uses `@Route("/hello")`, which needs an element named `value`. To keep the starter code unmodified, `Route` declares both `path()` and `value()`, and `Router` accepts either.
- `Injected` lines follow field declaration order, which is what `getDeclaredFields()` normally returns.
- Reflection answers are in `answers.txt`.


