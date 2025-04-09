# 🛍️ Product Catalog App

A modern **Product Catalog App** built using **Kotlin**, **Jetpack Compose**, **MVVM**, and **Hilt**. This app fetches products from a public API and displays them in a clean, user-friendly interface.

---

## 🚀 Features

### 🧾 Product List Screen
- Scrollable list of products on launch
- Displays:
  - 🖼️ Product image
  - 🏷️ Title
  - 🧾 Description (short)
  
### 📄 Product Details Screen
- Opens when a product is selected
- Displays:
  - Image gallery (LazyRow)
  - Title
  - Category
  - Price
  - Rating
  - Description

---

## 🔗 API Used

- [DummyJSON Products API](https://dummyjson.com/docs/products)
  - `GET /products` – Fetch all products
  - `GET /products/{id}` – Fetch product details

---

## 🧱 Architecture

- 🧩 **MVVM** (Model-View-ViewModel)
- 🛠️ **Retrofit** – for API networking
- 🔁 **StateFlow** – for reactive UI state
- 💉 **Hilt** – for dependency injection
- 💡 **Jetpack Compose** – for UI
- 🔄 **Navigation-Compose** – for screen navigation

---

---

## 🛠️ Setup & Installation

1. **Clone the repository**
https://github.com/ninjamaster831/Zynetic.git

## 📁 Folder Structure

com.example.productcatalog/
├── MyApplication.kt
├── MainActivity.kt
├── di/
│   └── NetworkModule.kt
├── model/
│   └── Product.kt
├── navigation/
│   └── AppNavigation.kt
├── network/
│   └── ApiService.kt
├── repository/
│   └── ProductRepository.kt
├── screen/
│     ├── ProductListScreen.kt
│     └── ProductDetailScreen.kt
├── viewmodel/
│   ├── ProductListViewModel.kt
│   └── ProductDetailViewModel.kt
