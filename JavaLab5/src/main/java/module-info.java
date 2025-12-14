module org.example.javalab4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    // Зависимости от Hibernate и Jakarta Persistence
    requires java.naming;
    requires org.hibernate.orm.core;

    // Jakarta Persistence API
    requires jakarta.persistence;

    // SLF4J
    requires org.slf4j;

    opens org.example.javalab4 to javafx.fxml, org.hibernate.orm.core;

    exports org.example.javalab4;
}


