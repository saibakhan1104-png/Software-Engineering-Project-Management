
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

## How to run

### IntelliJ IDEA
1. Create a new Java project and copy all `.java` files into `src` (default package, no `package` lines).
2. Open `Main.java` and click the green run button next to `main`.

### Command line
```
javac *.java
java Main
```
Run these inside the folder containing the `.java` files. `javac` must be on your PATH.

## Expected output

```
[Container] Registered: gradeController (GradeController)
[Container] Skipped: ReportPrinter (no @Component)
[Container] Injected: app.name -> GradeController.appName
[Container] Injected: app.passMark -> GradeController.passMark
--- Validation ---
VIOLATION: score - Score must be between 0 and 100 (actual: 120)
OK
VIOLATION: semester - Value out of range (actual: 15)
--- Routing ---
[Router] /hello -> Welcome to CampusBoot
[Router] /status -> Pass mark is 40
[Router] /unknown -> 404 Not Found: /unknown
```

## Output
<img width="972" height="605" alt="image" src="https://github.com/user-attachments/assets/677cdef9-bfb0-4df5-bc26-748974c97f85" />


## Notes

- The starter code uses `@Route("/hello")`, which needs an element named `value`. To keep the starter code unmodified, `Route` declares both `path()` and `value()`, and `Router` accepts either.
- `Injected` lines follow field declaration order, which is what `getDeclaredFields()` normally returns.
- Reflection answers are in `answers.txt`.
