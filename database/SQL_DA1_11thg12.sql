CREATE DATABASE SQL_DA1_11thg12
GO
USE SQL_DA1_11thg12
--1. Loai
CREATE TABLE Loai
	(
		IdLoai UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdDanhMuc UNIQUEIDENTIFIER NOT NULL,
		MaLoai  VARCHAR(20) UNIQUE NOT NULL,
		TenLoai NVARCHAR(150) NOT NULL,
		TrangThai INT DEFAULT NULL
	)
--2.TABLE KHÁCH HÀNG
CREATE TABLE Khach_Hang
	(
		IdKH UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		MaKH VARCHAR(20) UNIQUE NOT NULL,
		Ho NVARCHAR(20) DEFAULT NULL,
		TenDem NVARCHAR(100) DEFAULT NULL,
		Ten NVARCHAR(20) NOT NULL,
		GioiTinh NVARCHAR(20) DEFAULT NULL,
		NgaySinh DATE DEFAULT NULL,
		Sdt VARCHAR(15) NOT NULL UNIQUE,
		DiaChi NVARCHAR(150) DEFAULT NULL,
		ThanhPho NVARCHAR(100) DEFAULT NULL,
		QuocGia NVARCHAR(100) DEFAULT NULL,
		Loai INT DEFAULT 0,
		IdRank UNIQUEIDENTIFIER NOT NULL 
	)
--3.TABLE BÀN
CREATE TABLE Ban
	(
		IdBan UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdKhuVuc UNIQUEIDENTIFIER NOT NULL,
		MaBan INT UNIQUE NOT NULL,
		SoLuongChoNgoi INT DEFAULT 0,
		TrangThai INT DEFAULT 0
	)
--4.Thêm b?ng Khu v?c (3)
CREATE TABLE Khu_Vuc
	(
		IdKhuVuc UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		MaKhuVuc VARCHAR(20) UNIQUE NOT NULL,
		TenKhuVuc NVARCHAR(100) NOT NULL,
		TrangThai INT DEFAULT 0
	)
--5.Hoá Ðon
--thêm c?t GiamGia (gi?m cho t?gn HD)
CREATE TABLE Hoa_Don
	(
		IdHD UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdNV UNIQUEIDENTIFIER NOT NULL,
		IdKH UNIQUEIDENTIFIER DEFAULT NULL,
		MaHD VARCHAR(20) UNIQUE NOT NULL,
		NgayTao DATETIME NOT NULL,
		NgayThanhToan DATETIME DEFAULT NULL,
		NgayHuy DATETIME ,
		TongTien DECIMAL(20,0) DEFAULT 0,
		GiamGia DECIMAL(20,0) DEFAULT 0,
		ThueVAT INT DEFAULT 0,
		PhanTramGiamTheoRank INT DEFAULT 0,
		GhiChu NVARCHAR(200) DEFAULT NULL,
		SoLuongKhach INT NOT NULL,
		TrangThai INT DEFAULT 0
	)
	--6.Giao d?ch
CREATE TABLE Giao_Dich
	(
		Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdHD UNIQUEIDENTIFIER NOT NULL,
		HinhThucThanhToan NVARCHAR(200) NOT NULL,
		SoTienThanhToan DECIMAL(20,0) DEFAULT 0
	)
--7.Combo 
CREATE TABLE Combo
	(
		IdCombo UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdNV UNIQUEIDENTIFIER NOT NULL,
		MaCB VARCHAR(20) UNIQUE NOT NULL,
		TenCB NVARCHAR(150) NOT NULL,
		HinhAnh VARCHAR(255) DEFAULT NULL,
		DonGia DECIMAL(20,0) NOT NULL,
		TrangThai INT DEFAULT 0
	)
--8.Chi_Tiet_Combo
CREATE TABLE  Chi_Tiet_Combo
	(
		Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdCombo UNIQUEIDENTIFIER NOT NULL,
		IdMonAn UNIQUEIDENTIFIER NOT NULL,
		SoLuongMonAn INT NOT NULL,
	)
--9.Hoa_Don_Chi_Tiet 
CREATE TABLE Hoa_Don_Chi_Tiet
	(
		IdHDCT UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdMonAn UNIQUEIDENTIFIER DEFAULT NULL, 
		IdHD UNIQUEIDENTIFIER NOT NULL,
		IdCombo UNIQUEIDENTIFIER DEFAULT NULL,
		SoLuongMonAn INT NOT NULL,
		DonGiaMonAn DECIMAL(20,0) NOT NULL,
		SoLuongCombo INT NOT NULL,
		DonGiaCombo DECIMAL(20,0) NOT NULL,
		GhiChu NVARCHAR(200)
	)
--10.Nhan_Vien
CREATE TABLE Nhan_Vien
	(
		IdNV UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdCV UNIQUEIDENTIFIER NOT NULL,
		MaNV VARCHAR(20) UNIQUE NOT NULL,
		Ho NVARCHAR(20) NOT NULL,
		TenDem NVARCHAR(100) NOT NULL,
		Ten NVARCHAR(20) NOT NULL,
		GioiTinh NVARCHAR(20) DEFAULT NULL,
		Sdt VARCHAR(15) NOT NULL,
		Email VARCHAR(100) DEFAULT NULL,
		NgaySinh DATE NOT NULL,
		DiaChi NVARCHAR(200) NOT NULL,
		MatKhau VARCHAR(70) NOT NULL,
		TrangThai INT DEFAULT 0
	)

--CREATE TABLE Giao_Ca
--( 
--	IdGiaoCa UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
--	MaGiaoCa VARCHAR(20) UNIQUE NOT NULL,
--	ThoiGianNhanCa DATETIME NOT NULL,
--	ThoiGianGiaoCa DATETIME NOT NULL,
--	IdNVCaTiepTheo UNIQUEIDENTIFIER NOT NULL,
--	TongTienMat DECIMAL(20,0) NOT NULL,
--	TienPhatSinh DECIMAL(20,0) NOT NULL,
--	GhiChuPhatSinh NVARCHAR(50) NOT NULL,

--)
--11.Mon_An-b? idKm ? b?ng món an
--KM - MA : N-N thêm b?ngKhuyenMAiChiTiet
CREATE TABLE Mon_An 
	(
		IdMonAn UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdLoai UNIQUEIDENTIFIER NOT NULL,
		MaMonAn VARCHAR(20) UNIQUE NOT NULL,
		TenMonAn NVARCHAR(150) NOT NULL,
		HinhAnh VARCHAR(255) DEFAULT NULL,
		DonGia DECIMAL(20,0) NOT NULL,
		DonViTinh NVARCHAR(50) NOT NULL,
		TrangThai INT DEFAULT 0
	)
--12.KhuyenMai 
CREATE TABLE Khuyen_Mai
	(
		IdKM UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdNV UNIQUEIDENTIFIER NOT NULL,
		MaKM VARCHAR(20) UNIQUE NOT NULL,
		TenKM NVARCHAR(50) NOT NULL,
		ThoiGianBD DATETIME NOT NULL,
		ThoiGianKT DATETIME NOT NULL,
		LoaiKM NVARCHAR(50) NOT NULL,
		GtriKM DECIMAL(20,0) DEFAULT 0,
		GhiChu NVARCHAR(200) DEFAULT NULL,
		TrangThai INT DEFAULT 0 
	)
--thêm b?ng KMCT
--13. KhuyenMaiChiTiet
CREATE TABLE Khuyen_Mai_Chi_Tiet
	(
		Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdKM UNIQUEIDENTIFIER NOT NULL,
		IdMonAn UNIQUEIDENTIFIER NOT NULL,
		DonGiaSauKM DECIMAL(20,0) DEFAULT 0,
		TrangThai int DEFAULT 0
	)
----12. Ca_Lam_Viec
--CREATE TABLE Ca_Lam_Viec
--	(
--		IdCaLamViec UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
--		IdNV UNIQUEIDENTIFIER NOT NULL,
--		TgianBD DATETIME NOT NULL,
--		TgianKT DATETIME DEFAULT NULL,
--		TienDauCa DECIMAL(20,0) DEFAULT 0,
--		TienMatTaiQuan DECIMAL(20,0) NOT NULL,
--		TienNhoThuHo DECIMAL(20,0) DEFAULT 0,
--		GhiChu NVARCHAR(200)
--	)

--14.Chuc_Vu
CREATE TABLE Chuc_Vu
	(
		IdCV UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		MaCV VARCHAR(20) UNIQUE NOT NULL,
		TenCV NVARCHAR(100) NOT NULL,
		TrangThai INT DEFAULT 0
	)
--15.Danh m?c
CREATE TABLE Danh_Muc
	(
		IdDanhMuc UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		MaDanhMuc VARCHAR(20) UNIQUE NOT NULL,
		TenDanhMuc NVARCHAR(200) NOT NULL,
		TrangThai INT DEFAULT 0
	)
-- 16 chi_tiet_ban_hoaDon
create table Chi_Tiet_Ban_HoaDon
	(
		Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		IdBan UNIQUEIDENTIFIER NULL,
		IdHoaDon UNIQUEIDENTIFIER NOT NULL
	)
--17 Rank
CREATE TABLE Rank_Khach_Hang
	(
		IdRank UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
		maRank VARCHAR(10),
		TenRank NVARCHAR(30) NOT NULL,
		soTienBatDau decimal,
		soTienKetThuc decimal,
		KhuyenMaiRank INT DEFAULT 0,
		TrangThai INT DEFAULT 0
	)
		SELECT * FROM dbo.Rank_Khach_Hang

SELECT * FROM dbo.Khach_Hang
ALTER TABLE dbo.Khach_Hang ADD CONSTRAINT FK_KhachHang FOREIGN KEY (IdRank) REFERENCES dbo.Rank_Khach_Hang(IdRank)
---Quan h? gi?ua các b?ng:
ALTER TABLE Chi_Tiet_Ban_HoaDon ADD CONSTRAINT FK_Chi_Tiet_Ban_HoaDon_Ban FOREIGN KEY (IdBan) REFERENCES Ban(IdBan)
ALTER TABLE Chi_Tiet_Ban_HoaDon ADD CONSTRAINT FK_Chi_Tiet_Ban_HoaDon_HoaDon FOREIGN KEY (IdHoaDon) REFERENCES Hoa_Don(IdHD)

ALTER TABLE Loai ADD CONSTRAINT FK_DanhMuc FOREIGN KEY (IdDanhMuc) REFERENCES Danh_Muc(IdDanhMuc)

ALTER TABLE Khuyen_Mai ADD CONSTRAINT FK_NVTaoKM FOREIGN KEY (IdNV) REFERENCES Nhan_Vien(IdNV)

ALTER TABLE Nhan_Vien ADD CONSTRAINT FK_CV FOREIGN KEY (IdCV) REFERENCES Chuc_Vu(IdCV)
ALTER TABLE Hoa_Don_Chi_Tiet ADD CONSTRAINT FK_MonAn_HDCT FOREIGN KEY(IdMonAn) REFERENCES Mon_An(IdMonAn)
ALTER TABLE Hoa_Don_Chi_Tiet ADD CONSTRAINT FK_HD_HDCT FOREIGN KEY(IdHD) REFERENCES Hoa_Don(IdHD)
ALTER TABLE Hoa_Don_Chi_Tiet ADD CONSTRAINT FK_Combo_HDCT FOREIGN KEY(IdCombo) REFERENCES Combo(IdCombo)
ALTER TABLE Chi_Tiet_Combo ADD CONSTRAINT FK_ComboCT FOREIGN KEY (IdCombo) REFERENCES Combo(IdCombo)
ALTER TABLE Chi_Tiet_Combo ADD CONSTRAINT FK_MonAnCombo FOREIGN KEY (IdMonAn) REFERENCES Mon_An(IdMonAn)
ALTER TABLE Hoa_Don ADD CONSTRAINT FK_NV_HD FOREIGN KEY (IdNV) REFERENCES Nhan_Vien(IdNV)
ALTER TABLE Hoa_Don ADD CONSTRAINT FK_KH_HD FOREIGN KEY (IdKH) REFERENCES Khach_Hang(IdKH)
ALTER TABLE Combo ADD CONSTRAINT FK_NV_Combo FOREIGN KEY (IdNV) REFERENCES Nhan_Vien(IdNV)
ALTER TABLE Ban ADD CONSTRAINT FK_KhuVuc FOREIGN KEY(IdKhuVuc) REFERENCES Khu_Vuc(IdKhuVuc)
ALTER TABLE Mon_An ADD CONSTRAINT FK_Loai_monAN FOREIGN KEY(IdLoai) REFERENCES Loai(IdLoai)
--b? FK_KM
--ALTER TABLE Mon_An ADD CONSTRAINT FK_KM FOREIGN KEY (IdKM) REFERENCES Khuyen_Mai(IdKM)
--thêm FK cho MOnd an - kmct -km
ALTER TABLE Khuyen_Mai_Chi_Tiet ADD CONSTRAINT FK_MA FOREIGN KEY (IdMonAn) REFERENCES Mon_An (IdMonAn)
ALTER TABLE Khuyen_Mai_Chi_Tiet ADD CONSTRAINT FK_KM FOREIGN KEY (IdKM) REFERENCES Khuyen_Mai (IdKM)

ALTER TABLE Giao_Dich ADD CONSTRAINT FK_HD FOREIGN KEY(IdHD) REFERENCES Hoa_Don(IdHD)

--CỦA SƠN NHÉ
GO
ALTER PROCEDURE [dbo].[ThongKe](@NgayBatDau Date, @NgayKetThuc Date )
AS
BEGIN
DECLARE @SanPham TABLE (
		MaMonAn VARCHAR(MAX),
		TenMonAn NVARCHAR(MAX),
		SoLuongMonAn INT)
		DECLARE @IdHD table(
		idHoaDon VARCHAR(MAX)
		)
		INSERT @IdHD SELECT Hoa_Don.IdHD FROM Hoa_Don WHERE Hoa_Don.NgayThanhToan BETWEEN @NgayBatDau AND @NgayKetThuc

		INSERT @SanPham SELECT Mon_An.MaMonAn, Mon_An.TenMonAn, 0 FROM Mon_An 

		INSERT @SanPham SELECT Mon_An.MaMonAn, Mon_An.TenMonAn,sum(Hoa_Don_Chi_Tiet.SoLuongMonAn)
		FROM Mon_An INNER JOIN Hoa_Don_Chi_Tiet ON Mon_An.IdMonAn = Hoa_Don_Chi_Tiet.IdMonAn 
		WHERE Hoa_Don_Chi_Tiet.IdHD in (select idHoaDon from @IdHD)
		GROUP BY Mon_An.MaMonAn, Mon_An.TenMonAn,Hoa_Don_Chi_Tiet.SoLuongMonAn
		--
		INSERT @SanPham select Mon_An.MaMonAn, Mon_An.TenMonAn,(Chi_Tiet_Combo.SoLuongMonAn * Hoa_Don_Chi_Tiet.SoLuongCombo)
		from Hoa_Don_Chi_Tiet inner join Combo on Hoa_Don_Chi_Tiet.IdCombo = Combo.IdCombo
		inner join Chi_Tiet_Combo on Chi_Tiet_Combo.IdCombo = Combo.IdCombo
		inner join Mon_An on Mon_An.IdMonAn = Chi_Tiet_Combo.IdMonAn
		WHERE Hoa_Don_Chi_Tiet.IdHD in (select idHoaDon from @IdHD)
		group by Mon_An.MaMonAn, Mon_An.TenMonAn,Chi_Tiet_Combo.SoLuongMonAn, Hoa_Don_Chi_Tiet.SoLuongCombo

		SELECT * FROM @SanPham 
END;
			--CỦA SƠN TIẾP
GO
CREATE FUNCTION checkTongTienTrongNgay()
RETURNS DECIMAL
AS
BEGIN

	declare @StartDate datetime2

	declare @EndDate datetime2 

	set @StartDate = cast (GETDATE() as DATE) 

	set @EndDate = DATEADD(ns, -100, DATEADD(s, 86400, @StartDate))

	RETURN  (select ((sum(TongTien) + (sum(TongTien) * ThueVAT)) - (sum(TongTien) * PhanTramGiamTheoRank) - GiamGia ) from Hoa_Don where TrangThai = 1 and NgayThanhToan between @StartDate and @EndDate 
	GROUP BY TongTien, ThueVAT, PhanTramGiamTheoRank, GiamGia)
	
END
print dbo.checkTongTienTrongNgay()



--INSERT D? Li?u
SELECT * FROM Khu_Vuc
SELECT * FROM Ban
SELECT * FROM Chuc_Vu
SELECT * FROM Nhan_Vien
SELECT * FROM Danh_Muc
SELECT * FROM Loai
SELECT * FROM Mon_An
SELECT * FROM Combo
SELECT * FROM Chi_Tiet_Combo
SELECT * FROM Khach_Hang
SELECT * FROM Khuyen_Mai
--khu v?c
INSERT INTO Khu_Vuc(MaKhuVuc, TenKhuVuc, TrangThai) VALUES
	('KV1', N'Trên cây', 0),
	('KV2', N'Ngoài sân', 0)
	SELECT * FROM Khu_Vuc
--bàn
INSERT INTO Ban (IdKhuVuc, MaBan, SoLuongChoNgoi,TrangThai) VALUES
	('A24DEDFA-209A-4256-AE37-C09BA68E684C', 1, 10, 0)
INSERT INTO Ban (IdKhuVuc, MaBan, SoLuongChoNgoi,TrangThai) VALUES
	('8B7B3463-CAD3-4FB6-8F5A-71F55ED86524', 2, 5, 0),
	('8B7B3463-CAD3-4FB6-8F5A-71F55ED86524', 3, 5, 0)
--ch?c v?
INSERT INTO Chuc_Vu (MaCV,TenCV,TrangThai) VALUES
	('CV1', N'Nhân Viên', 0),
	('CV2', N'Quản lý', 0)

	SELECT * FROM Chuc_Vu
	delete from Nhan_Vien
--nhân viên
INSERT INTO Nhan_Vien(IdCV, MaNV, Ho, TenDem,Ten,GioiTinh, Sdt,Email, NgaySinh, DiaChi, MatKhau, TrangThai) VALUES
	('BB66AD23-1871-4158-B49A-87D9704F410D','NV2', N'Bùi', N'Công', N'Tuấn', N'Nam', '0339927992',
	'tuanbcph25878@fpt.edu.vn', '2003-04-22', N'Phú Thọ', '123', 0)
	INSERT INTO Nhan_Vien(IdCV, MaNV, Ho, TenDem,Ten,GioiTinh, Sdt,Email, NgaySinh, DiaChi, MatKhau, TrangThai) VALUES
	('7CF81657-8FDA-40C6-AABA-31494BA729E0','NV1', N'Nguyễn', N'Thuỳ', N'Dương', N'Nữ', '0384910040',
	'duongnttph25958@fpt.edu.vn', '2003-11-29', N'Tuyên Quang', '12123', 0)
--danh m?c:
INSERT INTO Danh_Muc (MaDanhMuc,TenDanhMuc,TrangThai) VALUES
	('DM1',N'Ð? an', 0)
INSERT INTO Danh_Muc (MaDanhMuc,TenDanhMuc,TrangThai) VALUES
	('DM2',N'Ð? u?ng', 0)
SELECT * FROM Danh_Muc
--lo?i:
delete from Loai
INSERT INTO Loai (IdDanhMuc, MaLoai, TenLoai, TrangThai) VALUES
	('669964DF-0FB8-4A0F-8437-6C9B065BCEB0', 'L1', N'Nu?ng',0)
INSERT INTO Loai (IdDanhMuc, MaLoai, TenLoai, TrangThai) VALUES
	('669964DF-0FB8-4A0F-8437-6C9B065BCEB0', 'L2', N'H?p',0)
INSERT INTO Loai (IdDanhMuc, MaLoai, TenLoai, TrangThai) VALUES
	('57089E43-342B-4637-AE9B-69319F1A70F0', 'L3', N'Ð? u?ng có ga',0)
INSERT INTO Loai (IdDanhMuc, MaLoai, TenLoai, TrangThai) VALUES
	('57089E43-342B-4637-AE9B-69319F1A70F0', 'L4', N'Ð? u?ng ko ga',0)
select * from Loai
--món an
INSERT INTO Mon_An(IdLoai, MaMonAn, TenMonAn, DonGia, DonViTinh, TrangThai) VALUES
	('7F9715EE-8606-491C-8B46-4B1680CE6CDB', 'MA1', N'Hàu nu?ng',200, N'Ðia', 0)
INSERT INTO Mon_An(IdLoai, MaMonAn, TenMonAn, DonGia, DonViTinh, TrangThai) VALUES
	('F3308C7F-7C53-487F-AC7F-E4185AFC6154', 'MA2', N'Hàu h?p',250, N'Ðia', 0)
INSERT INTO Mon_An(IdLoai, MaMonAn, TenMonAn, DonGia, DonViTinh, TrangThai) VALUES
	('99838FDE-2206-40B1-A516-79DE2A29F6FB', 'MA3', N'pepsi',10, N'chai', 0)
INSERT INTO Mon_An(IdLoai, MaMonAn, TenMonAn, DonGia, DonViTinh, TrangThai) VALUES
	('50336A98-B818-426E-8F39-ED8B618794E9', 'MA4', N'lavie',5, N'chai', 0)
	select * from Mon_An
	SELECT * FROM Nhan_Vien
--combo
INSERT INTO Combo(IdNV, MaCB, TenCB, DonGia, TrangThai) VALUES
	('397E7E22-8E4A-4025-8515-029AD9321524','CB1', N'Combo 255k', 255, 0 )
INSERT INTO Combo(IdNV, MaCB, TenCB, DonGia, TrangThai) VALUES
	('227DB553-87EC-4B4F-B746-3CD123FF0B98','CB2', N'Combo 215k', 215, 0 )
	select * from Mon_An
	SELECT * FROM Combo
	SELECT * FROM dbo.Hoa_Don
--chi ti?t combo
INSERT INTO Chi_Tiet_Combo (IdCombo,IdMonAn, SoLuongMonAn) VALUES
	('23CB925E-EE51-4289-A09A-78693163A25A','C2B2C528-BC37-408D-B9FC-0D6C84BCEC81', 1)
INSERT INTO Chi_Tiet_Combo (IdCombo,IdMonAn, SoLuongMonAn) VALUES
	('23CB925E-EE51-4289-A09A-78693163A25A','EC576E07-AED7-4ACD-A44A-F1BB4AC7612D', 2)
INSERT INTO Chi_Tiet_Combo (IdCombo,IdMonAn, SoLuongMonAn) VALUES
	('167362B0-1D1D-412F-A41B-B0296AA48EA0','9A0B21E2-2707-4A52-AFB3-A285ADFED1B5', 1)
INSERT INTO Chi_Tiet_Combo (IdCombo,IdMonAn, SoLuongMonAn) VALUES
	('167362B0-1D1D-412F-A41B-B0296AA48EA0','322FCCEE-089D-490B-91E4-6E628946C9EF', 2)
--Khach hàng

select * from Rank_Khach_Hang
insert Rank_Khach_Hang values (NEWID(), 1, N'Đồng', 0,500000,5,0)
insert Rank_Khach_Hang values (NEWID(), 2, N'Bạc', 501000,2000000,10,0)
insert Rank_Khach_Hang values (NEWID(), 3, N'Vàng', 2000001,5000000,15,0)
DELETE FROM dbo.Rank_Khach_Hang
INSERT INTO Khach_Hang (MaKH, Ho, TenDem, Ten, GioiTinh, NgaySinh, Sdt, DiaChi, ThanhPho,QuocGia,IdRank) VALUES
	('KH2', N'Nguy?n', N'Anh', N'Tuấn', N'Nam', '2003-11-29', '0354178673', N'Tuyên Quang', N'Tuyên Quang',
		N'Vi?t Nam','F7C1D911-076C-4F59-91DC-98C849DDDCAA')
INSERT INTO Khach_Hang (MaKH, Ho, TenDem, Ten, GioiTinh, NgaySinh, Sdt, DiaChi, ThanhPho,QuocGia,IdRank) VALUES
	('KH1', N'Nguy?n', N'Anh', N'Dung', N'Nam', '2003-11-29', '0384910040', N'Tuyên Quang', N'Tuyên Quang',
		N'Vi?t Nam','F7C1D911-076C-4F59-91DC-98C849DDDCAA')
		SELECT * FROM dbo.Rank_Khach_Hang
		SELECT * FROM dbo.Khach_Hang
		


Go
		ALTER FUNCTION Rank(@id_KH UNIQUEIDENTIFIER )
		RETURNS UNIQUEIDENTIFIER 
		AS
		BEGIN 
		DECLARE @Rank UNIQUEIDENTIFIER
		DECLARE @TongTien DECIMAL
		SET @TongTien =( SELECT ((sum(TongTien) + (sum(TongTien) * ThueVAT)) - (sum(TongTien) * PhanTramGiamTheoRank) - GiamGia ) FROM dbo.Khach_Hang kh INNER JOIN dbo.Hoa_Don hd ON hd.IdKH = kh.IdKH WHERE kh.IdKH = @id_KH
		GROUP BY TongTien, ThueVAT, PhanTramGiamTheoRank, GiamGia)
	    SET @Rank = (SELECT IdRank FROM Rank_Khach_Hang WHERE @TongTien <= soTienKetThuc AND @TongTien > soTienBatDau)

		RETURN @Rank
END;
DELETE FROM Khach_Hang
DELETE FROM Rank_Khach_Hang WHERE maRank = 1
SELECT * FROM Danh_Muc
UPDATE Rank_Khach_Hang SET TenRank = N'Khách lẻ', maRank = 1 WHERE maRank = 5
SELECT * FROM Hoa_Don WHERE MaHD = 'HD5'
GO
ALTER FUNCTION checkTongTienTrongNgay()
RETURNS DECIMAL
AS
BEGIN

	declare @StartDate datetime2

	declare @EndDate datetime2 

	set @StartDate = cast (GETDATE() as DATE) 

	set @EndDate = DATEADD(ns, -100, DATEADD(s, 86400, @StartDate))

	RETURN  (select ((sum(TongTien) - (sum(TongTien) * PhanTramGiamTheoRank) - GiamGia ) + ((sum(TongTien) - (sum(TongTien) * PhanTramGiamTheoRank) - GiamGia ) * PhanTramGiamTheoRank)) from Hoa_Don where TrangThai = 1 and NgayThanhToan between @StartDate and @EndDate 
	GROUP BY TongTien, ThueVAT, PhanTramGiamTheoRank, GiamGia)
	
END

Go
		ALTER FUNCTION Rank(@id_KH UNIQUEIDENTIFIER )
		RETURNS UNIQUEIDENTIFIER 
		AS
		BEGIN 
		DECLARE @Rank UNIQUEIDENTIFIER
		DECLARE @TongTien DECIMAL
		SET @TongTien =( SELECT ((sum(TongTien) - (sum(TongTien) * PhanTramGiamTheoRank) - GiamGia ) + ((sum(TongTien) - (sum(TongTien) * PhanTramGiamTheoRank) - GiamGia ) * PhanTramGiamTheoRank)) FROM dbo.Khach_Hang kh INNER JOIN dbo.Hoa_Don hd ON hd.IdKH = kh.IdKH WHERE kh.IdKH = @id_KH
		GROUP BY TongTien, ThueVAT, PhanTramGiamTheoRank, GiamGia)
	    SET @Rank = (SELECT IdRank FROM Rank_Khach_Hang WHERE @TongTien < soTienKetThuc AND @TongTien > soTienBatDau)

		RETURN @Rank
END;

--sơn: 11/12:
GO
ALTER FUNCTION checkTongTienTrongNgay()
RETURNS DECIMAL
AS
BEGIN

	declare @StartDate datetime2

	declare @EndDate datetime2 

	set @StartDate = cast (GETDATE() as DATE) 

	set @EndDate = DATEADD(ns, -100, DATEADD(s, 86400, @StartDate))

	RETURN  (select ((sum(TongTien) - (sum(TongTien) * PhanTramGiamTheoRank) - GiamGia ) + ((sum(TongTien) - (sum(TongTien) * PhanTramGiamTheoRank) - GiamGia ) * ThueVAT)) from Hoa_Don where TrangThai = 1 and NgayThanhToan between @StartDate and @EndDate 
	GROUP BY TongTien, ThueVAT, PhanTramGiamTheoRank, GiamGia)
	
END

Go
		ALTER FUNCTION Rank(@id_KH UNIQUEIDENTIFIER )
		RETURNS UNIQUEIDENTIFIER 
		AS
		BEGIN 
		DECLARE @Rank UNIQUEIDENTIFIER
		DECLARE @TongTien DECIMAL
		SET @TongTien =( SELECT ((sum(TongTien) - (sum(TongTien) * PhanTramGiamTheoRank) - GiamGia ) + ((sum(TongTien) - (sum(TongTien) * PhanTramGiamTheoRank) - GiamGia ) * ThueVAT)) FROM dbo.Khach_Hang kh INNER JOIN dbo.Hoa_Don hd ON hd.IdKH = kh.IdKH WHERE kh.IdKH = @id_KH
		GROUP BY TongTien, ThueVAT, PhanTramGiamTheoRank, GiamGia)
	    SET @Rank = (SELECT IdRank FROM Rank_Khach_Hang WHERE @TongTien <= soTienKetThuc AND @TongTien >= soTienBatDau)

		RETURN @Rank
END;
--SƠN  PHẠM
USE SQL_DA1_11thg12
GO
ALTER FUNCTION tongTien(@ngayBatDau date, @ngayKetThuc date)
RETURNS DECIMAL
AS
BEGIN

	declare @StartDate datetime2

	declare @EndDate datetime2 

	declare @checkEndDate datetime2

	set @StartDate = cast (@ngayBatDau as DATE) 

	set @checkEndDate = cast (@ngayKetThuc as DATE) 

	set @EndDate = DATEADD(ns, -100, DATEADD(s, 86400, @checkEndDate))

	
	RETURN  (select (((sum(TongTien) - (sum(TongTien) * PhanTramGiamTheoRank) - GiamGia ) + ((sum(TongTien) - (sum(TongTien) * PhanTramGiamTheoRank) - GiamGia ) * ThueVAT))) from Hoa_Don where TrangThai = 1 and NgayThanhToan between @StartDate and @EndDate 
	GROUP BY TongTien, ThueVAT, PhanTramGiamTheoRank, GiamGia)
	
END
SELECT dbo.tong

--Phạm sơn final 1
GO
ALTER FUNCTION tongTien(@ngayBatDau date, @ngayKetThuc date)
RETURNS DECIMAL
AS
BEGIN

	declare @StartDate datetime2

	declare @EndDate datetime2 

	declare @checkEndDate datetime2

	set @StartDate = cast (@ngayBatDau as DATE) 

	set @checkEndDate = cast (@ngayKetThuc as DATE) 

	set @EndDate = DATEADD(ns, -100, DATEADD(s, 86400, @checkEndDate))

	RETURN  (select sum(TongTien) from Hoa_Don where TrangThai = 1 and NgayThanhToan between @StartDate and @EndDate )
	
END
 

