open module com.example.database {
    requires com.google.common;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    exports com.example.database.DAL;
    exports com.example.database.BLL;
}