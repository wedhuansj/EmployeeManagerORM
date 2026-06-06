package service;

import exception.CustomException;
import model.Attendance;
import model.Employee;
import repository.GenericRepositoryImpl;

public class AttendanceService {
    public static AttendanceService instance;
    private final GenericRepositoryImpl repo = GenericRepositoryImpl.getInstance();
    private AttendanceService() {}
    public static synchronized AttendanceService getInstance() {
        if (instance == null)
            instance = new AttendanceService();
        return instance;
    }
    public void checkIn(String empId, int day, int hour, int ot) throws CustomException {
        Employee e = (Employee) repo.findById(empId, Employee.class);
        if (e == null) throw new CustomException("Nhân viên không tồn tại");
        repo.add(new Attendance(empId, day, hour, ot));
        e.setOt(e.getOt()+ot);
        repo.update(e);
    }
}
