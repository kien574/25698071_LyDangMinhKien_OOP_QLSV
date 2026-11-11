package qlsv;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
public class ListSinhVien {
    // ===== THUỘC TÍNH: ArrayList chứa sinh viên =====
    private ArrayList<SinhVien> danhSach;

    // ===== CONSTRUCTOR =====
    public ListSinhVien() {
        this.danhSach = new ArrayList<>();
    }
    public ListSinhVien(ArrayList<SinhVien> ds){
        this.danhSach = ds;
    }

    // ---------------------------- CRUD ----------------------------
    // THÊM: kiểm tra null + kiểm tra trùng mã
    public boolean them(SinhVien sv) {
        if (sv == null) return false;                   // không thêm null
        if (timTheoMa(sv.getMaSV()) != null) return false; // trùng mã
        return danhSach.add(sv);
    }

    // SỬA: duyệt tìm theo mã rồi set()
    public boolean sua(String maSV, SinhVien svMoi) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaSV().equals(maSV)) {
                danhSach.set(i, svMoi); // thay thế tại vị trí i
                return true;
            }
        }
        return false;
    }

    // XÓA: removeIf() xóa hết phần tử thỏa điều kiện
    public boolean xoa(String maSV) {
        return danhSach.removeIf(sv -> sv.getMaSV().equals(maSV));
    }

    // XÓA TẤT CẢ: kiểm tra rỗng trước
    public boolean xoaTatCa() {
        if (danhSach.isEmpty()) return false; // danh sách rỗng thì không xóa
        danhSach.clear();
        return true;
    }

    // ----------------------- TÌM KIẾM -----------------------

    // LẤY TẤT CẢ: trả về bản sao (không phải tham chiếu gốc)
    public List<SinhVien> layTatCa() {
        return new ArrayList<>(danhSach); // tạo list mới để tránh sửa đổi
    }

    // TÌM THEO MÃ: chính xác, không phân biệt hoa thường
    public SinhVien timTheoMa(String maSV) {
        for (SinhVien sv : danhSach) {
            if (sv.getMaSV().equalsIgnoreCase(maSV)) return sv;
        }
        return null;
    }

    // HÀM TÌM CHUNG: nhận Predicate - có thể dùng cho mọi điều kiện
    public List<SinhVien> tim(Predicate<SinhVien> dieuKien) {
        List<SinhVien> kq = new ArrayList<>();
        for (SinhVien sv : danhSach) {
            if (dieuKien.test(sv)) kq.add(sv);
        }
        return kq;
    }

    // TÌM THEO TÊN: chứa từ khóa, không phân biệt hoa thường
    public List<SinhVien> timTheoTen(String tuKhoa) {
        List<SinhVien> kq = new ArrayList<>();
        for (SinhVien sv : danhSach) {
            if (sv.getHoTen().toLowerCase().contains(tuKhoa.toLowerCase())) {
                kq.add(sv);
            }
        }
        return kq;
    }
    // TÌM THEO LỚP: so sánh equalsIgnoreCase
    public List<SinhVien> timTheoLop(String lop) {
        List<SinhVien> kq = new ArrayList<>();
        for (SinhVien sv : danhSach) {
            if (sv.getLop().equalsIgnoreCase(lop)) {
                kq.add(sv);
            }
        }
        return kq;
    }

    // TÌM THEO LOẠI: SV Chính quy / Từ xa / Sinh viên
    public List<SinhVien> timTheoLoai(String loai) {
        List<SinhVien> kq = new ArrayList<>();
        for (SinhVien sv : danhSach) {
            if (sv.getLoai().equalsIgnoreCase(loai)) {
                kq.add(sv);
            }
        }
        return kq;
    }

    // TÌM THEO MÔN: so sánh enum bằng ==
    public List<SinhVien> timTheoMon(MonHoc monHoc) {
        List<SinhVien> kq = new ArrayList<>();
        for (SinhVien sv : danhSach) {
            if (sv.getMonHoc() == monHoc) { // enum so sánh ==
                kq.add(sv);
            }
        }
        return kq;
    }
    // ------------------- THỐNG KÊ -------------------

    // Tính tổng số lượng sinh viên trong danh sách
    public int tongSoLuong() {
        return danhSach.size();
    }

    // Tính tổng thành tiền của tất cả sinh viên
    public double tongThanhTien() {
        double tong = 0;
        for (SinhVien sv : danhSach) {
            tong += sv.thanhTien();
        }
        return tong;
    }

// ------------------- SẮP XẾP -------------------

    // Sắp xếp tại chỗ (thay đổi trực tiếp danh sách gốc) theo tiêu chí truyền vào
    public void sapXep(Comparator<SinhVien> cmp) {
        danhSach.sort(cmp);
    }

    // Sắp xếp giảm dần tại chỗ bằng cách đảo ngược Comparator
    public void sapXepGiamDan(Comparator<SinhVien> cmp) {
        danhSach.sort(cmp.reversed()); // đảo ngược comparator
    }

    // Sắp xếp theo tên tăng dần và trả về một danh sách (List) mới
    public List<SinhVien> sapXepTheoTen() {
        List<SinhVien> kq = new ArrayList<>(danhSach);
        kq.sort(Comparator.comparing(SinhVien::getHoTen));
        return kq;
    }
    // ************************* GROUP BY *************************

    // NHÓM: trả về Map<Key, List<SinhVien>>
// VD: nhomTheo(SinhVien::getLop) -> { "21DTH01": [sv1, sv2], "21DTH02": [...] }
    public <K> Map<K, List<SinhVien>> nhomTheo(Function<SinhVien, K> layKey) {
        Map<K, List<SinhVien>> kq = new LinkedHashMap<>();
        for (SinhVien sv : danhSach) {
            K key = layKey.apply(sv);
            // computeIfAbsent: nếu key chưa có thì tạo List mới
            kq.computeIfAbsent(key, k -> new ArrayList<>()).add(sv);
        }
        return kq;
    }
    // // ĐẾM THEO NHÓM: trả về Map<Key, Integer>
// VD: demTheoNhom (SinhVien::getLop) -> {"21DTH01": 4, "21DTH02": 3}
    public <K> Map<K, Integer> demTheoNhom (Function<SinhVien, K> layKey) {
        Map<K, Integer> kq = new LinkedHashMap<>();
        for (SinhVien sv : danhSach) {
            // merge: nếu key có rồi thì +1, chưa có thì gán = 1
            kq.merge (layKey.apply(sv), 1, Integer::sum);
        }
        return kq;
    }

    // // TỔNG THEO NHÓM: trả về Map<Key, Double>
// VD: tongTheoNhom (SinhVien::getLop, SinhVien::getHocPhi)
//     -> {"21DTH01": 4.500.000, "21DTH02": 1.200.000 }
    public <K> Map<K, Double> tongTheoNhom (Function<SinhVien, K> layKey,
                                            ToDoubleFunction<SinhVien> layGiaTri) {
        Map<K, Double> kq = new LinkedHashMap<>();
        for (SinhVien sv : danhSach) {
            K key = layKey.apply(sv);
            double val = layGiaTri.applyAsDouble(sv);
            kq.merge (key, val, Double::sum); // cộng dồn giá trị
        }
        return kq;
    }

// ---------------------- GROUP BY ... HAVING ----------------------

    // // HAVING: chỉ giữ nhóm có TỔNG >= minTong
    public <K> Map<K, Double> tongTheoNhom_Having (Function<SinhVien, K> layKey,
                                                   ToDoubleFunction<SinhVien> layGiaTri,
                                                   double minTong) {
        Map<K, Double> kq = new LinkedHashMap<>();

        for (Map.Entry<K, Double> e : tongTheoNhom(layKey, layGiaTri).entrySet()) {
            if (e.getValue() >= minTong) {
                kq.put(e.getKey(), e.getValue());
            }
        }
        return kq;
    }
// HAVING: chỉ giữ nhóm có SỐ LƯỢNG >= minDem
        public <K> Map<K, Integer> demTheoNhom_Having(Function<SinhVien, K> layKey, int minDem) {
            Map<K, Integer> kq = new LinkedHashMap<>();
            for (Map.Entry<K, Integer> e : demTheoNhom(layKey).entrySet()) {
                if (e.getValue() >= minDem) {
                    kq.put(e.getKey(), e.getValue());
                }
            }
            return kq;
        }



}