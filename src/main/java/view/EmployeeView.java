package view;

import model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EmployeeView {
    private static EmployeeView instance;
    private final Scanner sc = new Scanner(System.in);
    private EmployeeView() {}
    public static synchronized EmployeeView getInstance() {
        if (instance == null)
            instance = new EmployeeView();
        return instance;
    }
    public int showMainMenu() {
        System.out.println("\n===== EMPLOYEE MANAGER =====");
        System.out.println("1. Quản lý nhân viên");
        System.out.println("2. Quản lý phòng ban");
        System.out.println("3. Quản lý chức vụ");
        System.out.println("4. Quản lý chấm công");
        System.out.println("5. Quản lý lương");
        System.out.println("6. Thống kê");
        System.out.println("0. Thoát");
        System.out.print("Chọn chức năng: ");
        return sc.nextInt();
    }
    public int showEmployeeMenu() {
        System.out.println("\n--- QUẢN LÝ NHÂN VIÊN ---");
        System.out.println("1. Thêm nhân viên");
        System.out.println("2. Xóa nhân viên");
        System.out.println("3. Cập nhật nhân viên");
        System.out.println("4. Tìm kiếm nhân viên");
        System.out.println("5. Hiển thị danh sách nhân viên");
        System.out.println("6. Sắp xếp theo lương");
        System.out.println("0. Quay lại");
        System.out.print("Chọn: ");
        int c = sc.nextInt();
        sc.nextLine();
        return c;
    }
    public Map<String, Object> getEmployeeInput() {
        Map<String, Object> data = new HashMap<>();
        System.out.print("Nhập ID: ");
        data.put("id", sc.nextLine());
        System.out.print("Nhập tên: ");
        data.put("name", sc.nextLine());
        System.out.print("Nhập tuổi: ");
        data.put("age", sc.nextInt());
        sc.nextLine();
        System.out.print("Giới tính: ");
        data.put("gen", sc.nextLine());
        System.out.print("Địa chỉ: ");
        data.put("addr", sc.nextLine());
        System.out.print("SĐT: ");
        data.put("phone", sc.nextLine());
        System.out.print("Email: ");
        data.put("email", sc.nextLine());
        System.out.print("Lương: ");
        data.put("sal", sc.nextDouble());
        sc.nextLine();
        System.out.print("Loại nhân viên (1: Full time, 2: Part time): ");
        data.put("type", sc.nextInt());
        return data;
    }
    public String getIdInput() {
        System.out.print("Nhập mã định danh: ");
        return sc.nextLine();
    }
    public String getNameInput() {
        System.out.print("Nhập họ tên mới: ");
        return sc.nextLine();
    }
    public int showDepMenu() {
        System.out.println("\n--- QUẢN LÝ PHÒNG BAN ---");
        System.out.println("1. Thêm phòng ban");
        System.out.println("2. Gán nhân viên vào phòng");
        System.out.print("Chọn: ");
        int c = sc.nextInt();
        sc.nextLine();
        return c;
    }
    public Map<String, String> getDepartmentInput() {
        Map<String, String> data = new HashMap<>();
        System.out.print("Mã phòng: ");
        data.put("id", sc.nextLine());
        System.out.print("Tên phòng: ");
        data.put("name", sc.nextLine());
        System.out.print("Trưởng phòng: ");
        data.put("mgr", sc.nextLine());
        return data;
    }
    public Map<String, String> getAssignInput() {
        Map<String, String> data = new HashMap<>();
        System.out.print("Mã nhân viên");
        data.put("empId", sc.nextLine());
        System.out.print("Mã liên kết: ");
        data.put("targetId", sc.nextLine());
        return data;
    }
    public int showPositionMenu() {
        System.out.println("\n--- QUẢN LÝ CHỨC VỤ ---");
        System.out.println("1. Thêm chức vụ");
        System.out.println("2. Gán chức vụ cho nhân viên");
        System.out.print("Chọn: ");
        int c = sc.nextInt();
        sc.nextLine();
        return c;
    }
    public Map<String, Object> getPositionInp() {
        Map<String, Object> data = new HashMap<>();
        System.out.print("Mã chức vụ: ");
        data.put("id", sc.nextLine());
        System.out.print("Tên chức vụ: ");
        data.put("name", sc.nextLine());
        System.out.print("Phụ cấp: ");
        data.put("alw", sc.nextDouble());
        return data;
    }
    public Map<String, Object> getAttendanceInput() {
        Map<String, Object> data = new HashMap<>();
        System.out.print("Mã nhân viên: ");
        data.put("empId", sc.nextLine());
        System.out.print("Ngày: ");
        data.put("day", sc.nextInt());
        System.out.print("Số giờ làm: ");
        data.put("hour", sc.nextInt());
        System.out.print("Giờ tăng ca: ");
        data.put("ot", sc.nextInt());
        return data;
    }
    public void msg(String msg) {
        System.out.println(msg);
    }
    public void displayEmployees(List<Employee> list) {
        for (Employee e : list)
            e.displayInfo();
    }
}
