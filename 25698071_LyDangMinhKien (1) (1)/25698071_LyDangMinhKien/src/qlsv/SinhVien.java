package qlsv;
import java.time.LocalDate;
public class SinhVien {
    // ===== 6 THUỘC TÍNH CHUNG (protected để lớp con truy cập) =====
    protected String maSV;       // mã sinh viên
    protected String hoTen;      // họ tên
    protected LocalDate ngaySinh;// ngày sinh
    protected String lop;        // lớp
    protected int soTinChi;      // số tín chỉ
    protected MonHoc monHoc;     // môn học (enum)

    // ===== CONSTRUCTOR =====
    public SinhVien(String maSV, String hoTen, LocalDate ngaySinh,
                    String lop, int soTinChi, MonHoc monHoc) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.lop = lop;
        this.soTinChi = soTinChi;
        this.monHoc = monHoc;
    }

    // ====== PHƯƠNG THỨC TÍNH THÀNH TIỀN ======
// Có thể bị override ở lớp con (VD: SinhVienTuXa giảm 10%)
    public double thanhTien() {
        return soTinChi * monHoc.getDonGia();
    }

    // ====== PHƯƠNG THỨC LẤY LOẠI – override ở lớp con ======
    public String getLoai() { return "Sinh viên"; }

    // ====== THÔNG TIN RIÊNG – override ở lớp con ======
    public String getThongTinRieng() { return ""; }

    // ====== GETTER ======
    public String getMaSV()          { return maSV; }
    public String getHoTen()         { return hoTen; }
    public LocalDate getNgaySinh()   { return ngaySinh; }
    public String getLop()           { return lop; }
    public int getSoTinChi()         { return soTinChi; }
    public MonHoc getMonHoc()        { return monHoc; }
    public float getDonGia()         { return monHoc.getDonGia(); }
    public String getTenMonHoc()     { return monHoc.getTenMon(); }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s | Lớp: %s | NS: %s | Môn: %s (%d tín - %,.0f) | TT: %,.0f VNĐ",
                maSV, hoTen, lop, ngaySinh,
                monHoc.getTenMon(), soTinChi, monHoc.getDonGia(),
                thanhTien()
        );
    }
}
