### TASK-1: Completed

### TASK-2:
~~~
package com.example;

import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String URL = "jdbc:sqlite:primes.db";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n (to find and store the nth prime): ");
        int n = sc.nextInt();
        if (n < 1) {
            System.out.println("n must be >= 1");
            return;
        }

        long prime = nthPrime(n);
        System.out.println("The " + n + "th prime is: " + prime);

        try (Connection conn = DriverManager.getConnection(URL)) {
            System.out.println("Connected to database: " + URL);
            createTable(conn);
            insertPrime(conn, n, prime);
            showAll(conn);
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Finds the nth prime using trial division by previously found primes
    static long nthPrime(int n) {
        long[] primes = new long[n];
        int count = 0;
        long candidate = 2;
        while (count < n) {
            boolean isPrime = true;
            for (int i = 0; i < count && primes[i] * primes[i] <= candidate; i++) {
                if (candidate % primes[i] == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) primes[count++] = candidate;
            candidate++;
        }
        return primes[n - 1];
    }

    static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS primes ("
                + "position INTEGER PRIMARY KEY, "
                + "prime_value INTEGER NOT NULL)";
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        }
    }

    static void insertPrime(Connection conn, int position, long prime) throws SQLException {
        String sql = "INSERT OR REPLACE INTO primes (position, prime_value) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, position);
            ps.setLong(2, prime);
            int rows = ps.executeUpdate();
            System.out.println("Inserted " + rows + " row(s).");
        }
    }

    static void showAll(Connection conn) throws SQLException {
        System.out.println("\nContents of table 'primes':");
        System.out.printf("%-10s %-12s%n", "Position", "Prime");
        System.out.println("----------------------");
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT position, prime_value FROM primes ORDER BY position")) {
            while (rs.next()) {
                System.out.printf("%-10d %-12d%n", rs.getInt("position"), rs.getLong("prime_value"));
            }
        }
    }
}
~~~
Output: <img width="1380" height="522" alt="Screenshot 2026-11-01 114619" src="https://github.com/user-attachments/assets/a622c1c3-e801-4fea-828b-f9757de9d799" />



### TASK-3:   <img width="1866" height="1008" alt="Screenshot 2026-11-01 113414" src="https://github.com/user-attachments/assets/c5cb5e6e-027d-4698-a8f4-b42f17dacc16" />

~~~
package com.example.demo;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
~~~

### TASK:4:
~~~
package com.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {

    public static void main(String[] args) throws LifecycleException {
        int port = 8080;

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(new File("tomcat-temp").getAbsolutePath());
        tomcat.setPort(port);
        tomcat.getConnector(); // initialise the default HTTP connector

        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());
        Tomcat.addServlet(ctx, "primeServlet", new PrimeServlet());
        ctx.addServletMappingDecoded("/", "primeServlet");

        tomcat.start();
        System.out.println("Server started. Open in your browser: http://localhost:" + port + "/");
        tomcat.getServer().await();
    }
}
~~~
<img width="655" height="438" alt="Screenshot 2026-11-01 115037" src="https://github.com/user-attachments/assets/9c586845-c705-4dda-bb50-d9d17b2075c6" />

