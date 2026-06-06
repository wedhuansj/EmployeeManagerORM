package service;

import model.Position;
import repository.GenericRepositoryImpl;

public class PositionService {
    public static PositionService instance;
    private final GenericRepositoryImpl repo = GenericRepositoryImpl.getInstance();
    private PositionService() {}
    public static synchronized PositionService getInstance() {
        if (instance == null)
            instance = new PositionService();
        return instance;
    }
    public void createPos(String id, String name, double alw) {
        repo.add(new Position(alw, name, id));
    }
}
