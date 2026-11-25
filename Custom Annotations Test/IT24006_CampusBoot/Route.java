import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Route {
    String path() default "";
    String value() default "";   // lets the starter's @Route("/hello") shorthand compile
}
