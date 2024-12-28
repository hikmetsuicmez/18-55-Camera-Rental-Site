# 18-55 Kamera Kiralama Mobil Uygulaması

Bu proje, kullanıcıların kamera ve ekipman kiralayabileceği, kurslar ve etkinlikler hakkında bilgi alabileceği bir mobil uygulamadır. Projede, modern mobil uygulama tasarım prensiplerine uygun olarak **Jetpack Compose** ile geliştirilmiş ekranlar bulunmaktadır.

---

## 📋 Özellikler

- **Giriş ve Kayıt Ekranları**: Kullanıcıların uygulamaya giriş yapabileceği ve yeni hesap oluşturabileceği ekranlar.
- **Ana Sayfa**: Kullanıcıların hızlı erişim sağlayabileceği Profil ve Ekipmanlar gibi temel butonlar.
- **Bottom Navigation Bar**: Her sayfanın altında gezinmeyi kolaylaştıran bir alt menü çubuğu.
- **Pop-up Menü**: "Menü" butonuna tıklandığında açılan, Kiralama, Kurslar, Etkinlikler ve Ayarlar gibi seçenekler sunan dinamik bir pop-up.

---

## 📱 Ekran Görüntüleri

### 1. **Welcome (Hoş Geldiniz) Ekranı**
![image](https://github.com/user-attachments/assets/14db639c-2021-43cc-a1b8-01bf79240c93)


### 2. **Login (Giriş) ve Register (Kayıt) Ekranları**
![image](https://github.com/user-attachments/assets/8aa47052-c0fa-4747-9e0a-c5ad6e74b43a)
![image](https://github.com/user-attachments/assets/25b92c27-be9b-4dbe-8a28-38fe621d4dcf)


### 3. **Ana Sayfa**
Ana sayfa, temel butonlarla kullanıcıların Profil ve Ekipmanlar gibi özelliklere erişmesini sağlar.
![image](https://github.com/user-attachments/assets/344fa0ba-34f0-47fe-8380-937a7ff3e6f3)


### 4. **Bottom Navigation Bar**
Alt gezinme çubuğu; Menü, Galeri ve Profil seçeneklerini içerir.
![image](https://github.com/user-attachments/assets/505decba-a819-4c73-83ed-ca7d87a9f196)


### 5. **Pop-up Menü**
Dinamik bir menü, aşağıdaki seçenekleri içerir:
- Kiralama
- Kurslar
- Etkinlikler
- Ayarlar
- 
![image](https://github.com/user-attachments/assets/8b862ded-4e58-45cd-ba43-3737798fded4)

### 6. **Ürün ekleme Sayfası**
Ürününüzü ekleyebileceğiniz, Fotoğraf ekleyebileceğiniz ve ürün detaylarını verebileceğiniz sayfa.
![image](https://github.com/user-attachments/assets/31d6f073-5767-4965-9500-f59af06b1f1b)

### 7. **Ürün Sayfası**
Ürünlerin kategorilerine göre listelendiği sayfa.
![image](https://github.com/user-attachments/assets/19777511-a44f-43ba-bea5-0e87c16346bc)

---

## 🛠️ Kullanılan Teknolojiler

- **Kotlin**: Modern ve okunabilir bir programlama dili.
- **Jetpack Compose**: Android kullanıcı arayüzlerini oluşturmak için kullanılan modern bir araç.
- **Navigation Component**: Sayfalar arası geçiş yönetimi.
- **Material Design**: Şık ve kullanıcı dostu arayüzler.



## 📂 Proje Yapısı

```plaintext
app/
├── main/
│   ├── model/
│   ├── storage/
│   ├── ui/
│   │   ├── activity/     # Etkinlik ekranı
│   │   ├── auth/         # Giriş yap ve Kayıt ol Ekranları
│   │   ├── components/   # Ortak UI bileşenleri (BottomNavigationBar, DropdownMenu)
│   │   ├── course/       # Kurs ekranı
│   │   ├── equipment/    # Ekipmanlar ekranı
│   │   ├── gallery/      # Galeri ekranı
│   │   ├── home/         # Ana Sayfa ekranı
│   │   ├── profile/      # Profil ekranı
│   │   ├── rental/       # Kiralama ekranı
│   │   ├── sell/       # Ürün ekleme ekranı
│   │   ├── welcome/      # Hoş geldiniz ekranı
│   ├── MainActivity.kt   # Navigation ile başlangıç noktası
