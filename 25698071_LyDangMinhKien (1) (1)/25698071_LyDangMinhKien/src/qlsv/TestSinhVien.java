package qlsv;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
public class TestSinhVien {


        public static void main(String[] args) {

            // ------ 1. IN DANH SÁCH MÔN HỌC TỪ ENUM ------
            System.out.println("------ DANH SÁCH MÔN HỌC (ENUM) ------");
            for (MonHoc mh : MonHoc.values()) {
                System.out.println("  " + mh);
            }
            System.out.println();

            // ------ KHỞI TẠO DANH SÁCH SINH VIÊN ------
            // Danh sách có thể chứa các loại: SinhVien, ChinhQuy, TuXa
            ListSinhVien ql = new ListSinhVien();
            // --- Sinh viên chính quy ---
            ql.them(new SinhVienChinhQui("SV01", "Nguyễn Văn An", LocalDate.of(2003, 5, 10),
                    "21DTH01", 3, MonHoc.LAP_TRINH_JAVA, "Cơ sở 1"));
            ql.them(new SinhVienChinhQui("SV02", "Trần Thị Bình", LocalDate.of(2003, 8, 20),
                    "21DTH01", 4, MonHoc.CO_SO_DU_LIEU, "Cơ sở 2"));
            ql.them(new SinhVienChinhQui("SV03", "Lê Hoàng Cường", LocalDate.of(2003, 3, 15),
                    "21DTH02", 3, MonHoc.LAP_TRINH_JAVA, "Cơ sở 3"));

// --- Sinh viên từ xa ---
            ql.them(new SinhVienTuXa("SV04", "Phạm Thu Duyên", LocalDate.of(2004, 1, 15),
                    "21DTH02", 2, MonHoc.TOAN_ROI_RAC, "Zoom"));
            ql.them(new SinhVienTuXa("SV05", "Hoàng Văn Ân", LocalDate.of(2003, 8, 20),
                    "21DTH01", 3, MonHoc.LAP_TRINH_JAVA, "Microsoft Teams"));
            ql.them(new SinhVienTuXa("SV06", "Lý Minh Quân", LocalDate.of(2003, 3, 5),
                    "21DTH02", 3, MonHoc.MANG_MAY_TINH, "Google Meet"));

// --- Sinh viên thường ---
            ql.them(new SinhVien("SV07", "Đỗ Thu Hà", LocalDate.of(2004, 7, 12),
                    "21DTH01", 3, MonHoc.TRI_TUE_NHAN_TAO));


// ------- 1. IN DANH SÁCH SINH VIÊN -------
            System.out.println("====== DANH SÁCH SINH VIÊN (RA MÀN HÌNH) ======");
            ql.layTatCa().forEach(System.out::println);


// ------- 2. DEMO ĐA HÌNH -------
            System.out.println("====== DEMO ĐA HÌNH (POLYMORPHISM) ======");
            ql.layTatCa().forEach(sv ->
                    System.out.printf("%-8s | Loại: %-18s | Thành tiền: %,.0f VNĐ | %s\n",
                            sv.getMaSV(), sv.getLoai(), sv.thanhTien(), sv.getThongTinRieng()));


// ------- 3. GOM NHÓM THEO LỚP -------
            System.out.println("\n ====> 1. GOM NHÓM THEO LỚP <====");
            Map<String, List<SinhVien>> theoLop = ql.nhomTheo(SinhVien::getLop);
            theoLop.forEach((lop, ds) -> {
                System.out.println("Lớp: " + lop + " (" + ds.size() + " SV)");
                ds.forEach(sv -> System.out.println("   " + sv));
            });

// ------ 4. GOM NHÓM THEO LOẠI ------
            System.out.println("\n ====> 2. GOM NHÓM THEO LOẠI <====");
            Map<String, List<SinhVien>> theoLoai = ql.nhomTheo(SinhVien::getLoai);
            theoLoai.forEach((loai, ds) -> {
                System.out.println("Loại: " + loai + " (" + ds.size() + " SV)");
                ds.forEach(sv -> System.out.println("   " + sv.getHoTen()));
            });

// ------ 5. TỔNG THÀNH TIỀN THEO LỚP ------
            System.out.println("\n ====> 3. TỔNG THÀNH TIỀN THEO LỚP <====");
            Map<String, Double> sumLop = ql.tongTheoNhom(SinhVien::getLop, SinhVien::thanhTien);
            sumLop.forEach((lop, sum) -> {
                System.out.printf("Lớp %-10s Tổng = %,.0f VND\n", lop, sum);
            });

// ------ 6. TỔNG THÀNH TIỀN THEO MÔN ------
            System.out.println("\n ====> 4. TỔNG THÀNH TIỀN THEO MÔN <====");
            Map<MonHoc, Double> sumMon = ql.tongTheoNhom(SinhVien::getMonHoc, SinhVien::thanhTien);
            sumMon.forEach((mon, sum) -> {
                System.out.printf("%-25s : %,.0f VND\n", mon.getTenMon(), sum);
            });

// ------ 7. TỔNG THÀNH TIỀN THEO LOẠI ------
//            System.out.println("\n ====> 5. TỔNG THÀNH TIỀN THEO LOẠI <====");
//            Map<String, Double> sumLoai = ql.tongTheoNhom(SinhVien::getLoai, SinhVien::thanhTien);
//            sumLoai.forEach((loai, sum) ->
//                    System.out.printf("Loai %-12s : %,.0f VND%n", loai, sum);
//
//
//            // 6. ĐẾM SỐ LƯỢT THEO MÔN
//            System.out.println("\n-------- 6. ĐẾM SỐ LƯỢT THEO MÔN --------");
//            Map<MonHoc, Integer> demMon = ql.demTheoNhom(SinhVien::getMonHoc);
//            demMon.forEach((mon, count) ->
//                    System.out.printf("%-25s - %d sinh viên%n", mon.TenMon(), count);
// ------ 7. TỔNG THÀNH TIỀN THEO LOẠI ------
            System.out.println("\n ====> 5. TỔNG THÀNH TIỀN THEO LOẠI <====");
            Map<String, Double> sumLoai = ql.tongTheoNhom(SinhVien::getLoai, SinhVien::thanhTien);
            sumLoai.forEach((loai, sum) ->
                    System.out.printf("Loai %-12s : %,.0f VND%n", loai, sum));

// 6. ĐẾM SỐ LƯỢT THEO MÔN
            System.out.println("\n-------- 6. ĐẾM SỐ LƯỢT THEO MÔN --------");
            Map<MonHoc, Integer> demMon = ql.demTheoNhom(SinhVien::getMonHoc);
            demMon.forEach((mon, count) ->
                    System.out.printf("%-25s - %d sinh viên%n", mon.getTenMon(), count));

            // 7. LỌC SINH VIÊN CHÍNH QUY
            System.out.println("\n-------- 7. LỌC SINH VIÊN CHÍNH QUY --------");
            List<SinhVien> dsChinhQuy = ql.tim(sv -> sv instanceof SinhVienChinhQui);
            dsChinhQuy.forEach(sv -> System.out.println("⭐ " + sv));

            // 8. LỌC SINH VIÊN TỪ XA
            System.out.println("\n-------- 8. LỌC SINH VIÊN TỪ XA --------");
            List<SinhVien> dsTuXa = ql.tim(sv -> sv instanceof SinhVienTuXa);
            dsTuXa.forEach(sv -> System.out.println("🌐 " + sv));

            // 9. SẮP XẾP THÀNH TIỀN GIẢM DẦN
            System.out.println("\n-------- 9. SẮP XẾP THÀNH TIỀN GIẢM DẦN --------");
            ql.sapXepGiamDan(Comparator.comparingDouble(SinhVien::thanhTien));
            ql.layTatCa().forEach(sv -> System.out.println("💰 " + sv));
//
//            // 10. TÌM MÔN HỌC THEO TÊN
//            System.out.println("\n-------- 10. TÌM MÔN HỌC THEO TÊN --------");
//            MonHoc monTimThay = ql.timTheoMon("Mạng máy tính");
//            if (monTimThay != null) {
//                System.out.println("Đã tìm thấy: " + monTimThay);
//            } else {
//                System.out.println("Không tìm thấy môn học!");
//            }
//            // ----- 11. GROUP BY ... HAVING -----
//            System.out.println("\n====== 11. HAVING: TỔNG THEO LỚP >= 1,500,000 ======");
//            Map<String, Double> havingLop = ql.tongTheoNhom_Having(
//                    SinhVien::getLop, SinhVien::getThanhTien, 1_500_000);
//            if (havingLop.isEmpty()) System.out.println(" (Không có nhóm nào thỏa)!");
//            else havingLop.forEach((lop, sum) ->
//                    System.out.printf(" Lớp %-10s : %,.0f VNĐ\n", lop, sum);
//
//
//            System.out.println("\n====== 12. HAVING: MÔN CÓ >= 2 SV ======");
//            Map<MonHoc, Integer> havingDemMon = ql.demTheoNhom_Having(SinhVien::getMonHoc, 2);
//            if (havingDemMon.isEmpty()) System.out.println(" (Không có nhóm nào thỏa)!");
//            else havingDemMon.forEach((mon, dem) ->
//                    System.out.printf(" %-25s : %d SV\n", mon.getTenMon(), dem);
//            Use code with caution.
// 10. TÌM MÔN HỌC THEO TÊN
            System.out.println("\n-------- 10. TÌM MÔN HỌC THEO TÊN --------");
            MonHoc monTimThay = MonHoc.fromTenMon("Mạng máy tính");
            if (monTimThay != null) {
                System.out.println("Đã tìm thấy: " + monTimThay);
            } else {
                System.out.println("Không tìm thấy môn học!");
            }

// ----- 11. GROUP BY ... HAVING -----
            System.out.println("\n====== 11. HAVING: TỔNG THEO LỚP >= 1,500,000 ======");
            Map<String, Double> havingLop = ql.tongTheoNhom_Having(
                    SinhVien::getLop, SinhVien::thanhTien, 1_500_000);
            if (havingLop.isEmpty()) System.out.println(" (Không có nhóm nào thỏa)!");
            else havingLop.forEach((lop, sum) ->
                    System.out.printf(" Lớp %-10s : %,.0f VNĐ%n", lop, sum));

            System.out.println("\n====== 12. HAVING: MÔN CÓ >= 2 SV ======");
            Map<MonHoc, Integer> havingDemMon = ql.demTheoNhom_Having(SinhVien::getMonHoc, 2);
            if (havingDemMon.isEmpty()) System.out.println(" (Không có nhóm nào thỏa)!");
            else havingDemMon.forEach((mon, dem) ->
                    System.out.printf(" %-25s : %d SV%n", mon.getTenMon(), dem));
        }
}

