import java.lang.reflect.Method;

public class Router {
    public static String dispatch(Container c, String path) {
        for (Object bean : c.getBeans().values()) {
            for (Method m : bean.getClass().getDeclaredMethods()) {
                Route r = m.getAnnotation(Route.class);
                if (r != null && pathOf(r).equals(path)) {
                    try {
                        m.setAccessible(true);
                        return (String) m.invoke(bean);
                    } catch (Exception e) {
                        return "500 Error: " + e.getMessage();
                    }
                }
            }
        }
        return "404 Not Found: " + path;
    }

    private static String pathOf(Route r) {
        return r.path().isEmpty() ? r.value() : r.path();
    }
}
