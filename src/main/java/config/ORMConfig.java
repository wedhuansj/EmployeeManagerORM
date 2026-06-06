package config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ORMConfig {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("employeeMgmtPU");
}