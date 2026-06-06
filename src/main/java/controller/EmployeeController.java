package controller;

import exception.CustomException;
import model.Employee;
import service.AttendanceService;
import service.DepartmentService;
import service.EmployeeService;
import service.PositionService;
import view.EmployeeView;

import java.util.List;
import java.util.Map;

public class EmployeeController {
    private static EmployeeController instance;
    private final EmployeeView view = EmployeeView.getInstance();
    private final EmployeeService empService = EmployeeService.getInstance();
    private final DepartmentService depService = DepartmentService.getInstance();
    private final PositionService posService = PositionService.getInstance();
    private final AttendanceService attService = AttendanceService.getInstance();
    private EmployeeController() {}
    public static synchronized EmployeeController getInstance() {
        if (instance == null)
            instance = new EmployeeController();
        return instance;
    }
    public void start() {
        while (true) {
            int choice = view.showMainMenu();
            if (choice == 0) break;
            switch (choice) {
                case 1:
                    handleEmployee();
                    break;
                case 2:
                    handleDep();
                    break;
                case 3:
                    handlePos();
                    break;
                case 4:
                    handleAtt();
                    break;
                case 5:
                    handleSalary();
                    break;
                case 6:
                    handleStats();
                    break;
            }
        }
    }
    private void handleEmployee() {
        while (true) {
            int c = view.showEmployeeMenu();
            if (c == 0) break;
            try {
                switch (c) {
                    case 1:
                        Map<String, Object> data = view.getEmployeeInput();
                        empService.registerEmployee(
                                (String)data.get("id"),
                                (String)data.get("name"),
                                (Integer)data.get("age"),
                                (String)data.get("gen"),
                                (String)data.get("addr"),
                                (String)data.get("phone"),
                                (String)data.get("email"),
                                (Double)data.get("sal"),
                                (Integer)data.get("type")
                        );
                        view.msg("Thêm nhân viên thành công!");
                        break;
                    case 2:
                        empService.removeEmployee(view.getIdInput());
                        view.msg("Xóa nhân viên thành công!");
                        break;
                    case 3:
                        empService.updateEmployeeName(view.getIdInput(), view.getNameInput());
                        view.msg("Cập nhật thành công!");
                        break;
                    case 4:
                        Employee e = empService.searchById(view.getIdInput());
                        if (e != null)
                            e.displayInfo();
                        else
                            view.msg("Không tìm thấy!");
                        break;
                    case 5:
                        view.displayEmployees(empService.getAllEmployees());
                        break;
                    case 6:
                        view.displayEmployees(empService.getEmployeesSorted());
                }
            } catch(CustomException ex) {
                view.msg("Lỗi: "+ex.getMessage());
            }
        }
    }
    private void handleDep() {
        int c = view.showDepMenu();
        try {
            if (c == 1) {
                Map<String, String> data = view.getDepartmentInput();
                depService.createDep(data.get("id"), data.get("name"), data.get("mgr"));
                view.msg("Thêm phòng ban thành công!");
            }
            else if (c== 2) {
                Map<String, String> data = view.getAssignInput();
                empService.assignDep(data.get("empId"), data.get("targetId"));
                view.msg("gán phòng ban thành công!");
            }
        } catch (CustomException ex) {
            view.msg("Lỗi: " + ex.getMessage());
        }
    }
    private void handlePos() {
        int c = view.showPositionMenu();
        try {
            if (c == 1) {
                Map<String, Object> data = view.getPositionInp();
                posService.createPos((String)data.get("id"), (String)data.get("name"), (Double)data.get("alw"));
                view.msg("Thêm chức vụ thành công!");
            }
            else if (c == 2) {
                Map<String, String> data = view.getAssignInput();
                empService.assignPos(data.get("empId"), data.get("targetId"));
                view.msg("Gán chức vụ thành công");
            }
        } catch (CustomException ex) {
            view.msg("Lỗi: " + ex.getMessage());
        }
    }
    private void handleAtt() {
        Map<String, Object> data = view.getAttendanceInput();
        try {
            attService.checkIn(
                    (String)data.get("empId"),
                (Integer)data.get("day"),
                    (Integer)data.get("hour"),
                    (Integer)data.get("ot")
            );
            view.msg("Chấm công thành công!");
        } catch (CustomException ex) {
            view.msg("Lỗi: " + ex.getMessage());
        }
    }
    private void handleSalary() {
        List<Employee> list = empService.getAllEmployees();
        for (Employee e : list)
            view.msg("Nhân viên: " + e.getName() + " | Lương thực nhận: " + e.calculateSalary());
    }
    private void handleStats() {
        List<Employee> list = empService.getAllEmployees();
        double total = 0;
        Employee maxSalaryEmp = null;
        for (Employee e : list) {
            double sal = e.calculateSalary();
            total += sal;
            if (maxSalaryEmp == null || sal > maxSalaryEmp.calculateSalary())
                maxSalaryEmp = e;
        }
        view.msg("Tổng quỹ lương công ty: " + total);
        if (maxSalaryEmp != null) {
            view.msg("Nhân viên lương cao nhất: ");
            maxSalaryEmp.displayInfo();
        }
    }
}
