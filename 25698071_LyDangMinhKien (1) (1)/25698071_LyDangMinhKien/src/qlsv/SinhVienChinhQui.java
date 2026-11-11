package qlsv;

import java.time.LocalDate;
public class SinhVienChinhQui extends SinhVien{
    // Thuoc tinh rieng
    private String hocTruocTiep; // cơ sở học trực tiếp

    // ------ CONSTRUCTOR - gọi super() ------
    public SinhVienChinhQui(String maSV, String hoTen, LocalDate ngaySinh,
                            String lop, int soTinChi, MonHoc monHoc,
                            String hocTruocTiep) {
        super(maSV, hoTen, ngaySinh, lop, soTinChi, monHoc); // gọi lớp cha
        this.hocTruocTiep = hocTruocTiep;
    }

    // ===== GETTER + SETTER =====

    public String getHocTruocTiep() {return hocTruocTiep;}

    public void setHocTruocTiep(String hocTruocTiep) {
        this.hocTruocTiep = hocTruocTiep;
    }

    // ===== OVERRIDE - trả về loại =====
    @Override
    public String getLoai() { return "Chinh quy"; }
    @Override
    public String getThongTinRieng() {
        return "Học trực tiếp tại: " + hocTruocTiep;
    }

    // ===== OVERRIDE toString - gọi super.toString() rồi nối thêm =====
    @Override
    public String toString() {
        return super.toString() + " | 🏫 " + getThongTinRieng();
    }
}
