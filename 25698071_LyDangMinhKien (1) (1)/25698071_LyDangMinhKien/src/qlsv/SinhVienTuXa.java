package qlsv;
import java.time.LocalDate;

public class SinhVienTuXa extends SinhVien{
    // ----- THUỘC TÍNH RIÊNG -----
    private String nenTang; // nền tảng học online (Zoom, Teams...)

    // ----- CONSTRUCTOR -----
    public SinhVienTuXa(String maSV, String hoTen, LocalDate ngaySinh,
                        String lop, int soTinChi, MonHoc monHoc,
                        String nenTang) {
        super(maSV, hoTen, ngaySinh, lop, soTinChi, monHoc);
        this.nenTang = nenTang;
    }

    public String getNenTang() { return nenTang; }
    public void setNenTang(String nenTang) {
        this.nenTang = nenTang;
    }

    // ===== OVERRIDE - loại =====
    @Override
    public String getLoai() { return "Từ xa"; }

    // ===== OVERRIDE - thông tin riêng =====
    @Override
    public String getThongTinRieng() {
        return "Học online qua: " + nenTang;
    }

    // ===== QUAN TRỌNG: OVERRIDE thanhTien() - giảm 10% =====
// super.thanhTien() = số tín * đơn giá (của lớp cha)
// * 0.9 = giảm 10%
    @Override
    public double thanhTien() {
        return super.thanhTien() * 0.9;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getThongTinRieng();
    }
}
