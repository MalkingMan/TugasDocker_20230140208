# Praktikum 6 - Spring Boot Login + Input Mahasiswa

Aplikasi web sederhana menggunakan Spring Boot + Thymeleaf dengan fitur:

- Login manual (`/login`) tanpa Spring Security
- Home (`/home`) menampilkan data mahasiswa dari memori
- Form (`/form`) untuk input data mahasiswa
- Penyimpanan sementara menggunakan `List<User>` (tanpa database)

## Akun Login

- Username: `admin`
- Password: `20210140019`

## Struktur Utama

- `src/main/java/com/tugas/deploy/controller/UserController.java`
- `src/main/java/com/tugas/deploy/model/User.java`
- `src/main/resources/templates/login.html`
- `src/main/resources/templates/home.html`
- `src/main/resources/templates/form.html`

## Menjalankan Aplikasi

```powershell
.\mvnw.cmd spring-boot:run
```

Akses di browser:

- `http://localhost:8080/login`

## Catatan

- Data akan hilang saat aplikasi dihentikan atau restart.
- Sesuaikan teks identitas di halaman home jika ingin personalisasi lebih lanjut.

