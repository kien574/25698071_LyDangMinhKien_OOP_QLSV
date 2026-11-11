package qlsv;
import java.util.List;

public interface IFSinhVien {
    // ================== NHÓM 1: THÊM / XÓA / SỬA (CRUD) ==================
    // Các phương thức trả về boolean: thành công (true) / thất bại (false)

    boolean them(SinhVien sv);                  // Thêm một sinh viên mới
    boolean sua(String maSV, SinhVien svMoi);    // Cập nhật thông tin sinh viên theo mã số
    boolean xoa(String maSV);                   // Xóa sinh viên theo mã số
    boolean xoaTatCa();                         // Xóa toàn bộ danh sách sinh viên

    // ================== NHÓM 2: TÌM KIẾM ==================
    // Các phương thức trả về một danh sách (List) hoặc một đối tượng đơn

    List<SinhVien> layTatCa();                  // Lấy ra toàn bộ danh sách sinh viên
    SinhVien timTheoMa(String maSV);            // Tìm đích danh 1 sinh viên theo mã số
    List<SinhVien> timTheoTen(String tuKhoa);   // Tìm kiếm sinh viên theo từ khóa tên
    List<SinhVien> timTheoLop(String lop);      // Lọc danh sách sinh viên theo lớp học
    List<SinhVien> timTheoLoai(String loai);    // Lọc sinh viên theo phân loại (học lực/hạnh kiểm...)
    List<SinhVien> timTheoMon(MonHoc monHoc);

    // ======================= Nhóm 3: THỐNG KÊ =======================
    int tongSoLuong();          // đếm số lượng
    double tongThanhTien();    // tổng thành tiền

    // ======================= Nhóm 4: SẮP XẾP =======================
    List<SinhVien> sapXepTheoTen();
    List<SinhVien> sapXepTheoThanhTienGiamDan();


}
