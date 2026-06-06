package service;

import model.Department;
import repository.GenericRepositoryImpl;

public class DepartmentService {
    public static DepartmentService instance;
    private final GenericRepositoryImpl repo = GenericRepositoryImpl.getInstance();
    private DepartmentService() {}
    public static synchronized DepartmentService getInstance() {
        if (instance == null)
            instance = new DepartmentService();
        return instance;
    }
    public void createDep(String id, String name, String mgr) {
        repo.add(new Department(id, name, mgr));
    }
}
