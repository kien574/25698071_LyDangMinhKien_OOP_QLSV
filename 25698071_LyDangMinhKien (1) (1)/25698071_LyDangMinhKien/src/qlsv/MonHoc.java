package qlsv;

public enum MonHoc {
    // ===== 8 MÔN HỌC - mỗi môn có TÊN + ĐƠN GIÁ =====
    LAP_TRINH_JAVA("Lập trình Java", 450_000f),
    CO_SO_DU_LIEU("Cơ sở dữ liệu", 450_000f),
    TOAN_ROI_RAC("Toán rời rạc", 400_000f),
    MANG_MAY_TINH("Mạng máy tính", 500_000f),
    TRI_TUE_NHAN_TAO("Trí tuệ nhân tạo", 600_000f),
    KY_THUAT_PHAN_MEM("Kỹ thuật phần mềm", 480_000f),
    LAP_TRINH_WEB("Lập trình Web", 500_000f),
    HE_DIEU_HANH("Hệ điều hành", 420_000f);

    // ===== THUỘC TÍNH - private final (bắt buộc với enum) =====
    private final String tenMon;    // tên môn
    private final float donGia;     // đơn giá/tín chỉ

    // ===== CONSTRUCTOR - private ngầm định =====
    MonHoc(String tenMon, float donGia) {
        this.tenMon = tenMon;
        this.donGia = donGia;
    }

    public String getTenMon() {return tenMon;}
    public float getDonGia() {return donGia;}

    // ===== PHƯƠNG THỨC TĨNH: Tìm môn theo tên =====
    public static MonHoc fromTenMon(String tenMon) {
        for (MonHoc mh : values())       // values() trả về mảng tất cả enum
            if (mh.tenMon.equalsIgnoreCase(tenMon)) return mh;
        return null;                    // không tìm thấy
    }

    // ===== OVERRIDE toString() để in đẹp =====
    @Override
    public String toString() {
        return String.format("%s (%,.0f VNĐ/tín chỉ)", tenMon, donGia);
    }
}
