import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class Container {
    private final Map<String, Object> beans = new LinkedHashMap<>();

    public void scan(Class<?>... classes) {
        for (Class<?> c : classes) {
            Component comp = c.getAnnotation(Component.class);
            if (comp == null) {
                System.out.println("[Container] Skipped: " + c.getSimpleName() + " (no @Component)");
                continue;
            }
            try {
                Object instance = c.getDeclaredConstructor().newInstance();
                String name = comp.value();
                if (name.isEmpty()) {
                    String s = c.getSimpleName();
                    name = Character.toLowerCase(s.charAt(0)) + s.substring(1);
                }
                beans.put(name, instance);
                System.out.println("[Container] Registered: " + name + " (" + c.getSimpleName() + ")");
            } catch (Exception e) {
                System.out.println("[Container] Failed: " + c.getSimpleName() + " (" + e.getMessage() + ")");
            }
        }
    }

    public void injectValues(Map<String, String> config) {
        for (Object bean : beans.values()) {
            for (Field f : bean.getClass().getDeclaredFields()) {
                Value v = f.getAnnotation(Value.class);
                if (v == null) continue;
                String raw = config.get(v.value());
                if (raw == null) {
                    System.out.println("[Container] Skipped: " + v.value() + " (no config value)");
                    continue;
                }
                try {
                    f.setAccessible(true);
                    Class<?> t = f.getType();
                    if (t == int.class || t == Integer.class) {
                        f.set(bean, Integer.parseInt(raw.trim()));
                    } else if (t == String.class) {
                        f.set(bean, raw);
                    } else {
                        System.out.println("[Container] Skipped: " + f.getName() + " (unsupported type)");
                        continue;
                    }
                    System.out.println("[Container] Injected: " + v.value() + " -> "
                            + bean.getClass().getSimpleName() + "." + f.getName());
                } catch (Exception e) {
                    System.out.println("[Container] Failed to inject " + f.getName() + ": " + e.getMessage());
                }
            }
        }
    }

    public Object getBean(String name) {
        return beans.get(name);
    }

    Map<String, Object> getBeans() {
        return beans;
    }
}
