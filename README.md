
# 📝 Note Taking App (Android - Internal Storage)

A simple and lightweight **Note Taking Android App** built using **Java** and **XML** in **Android Studio**. It allows users to **create**, **view**, **edit**, and **delete** notes. All notes are stored securely using the device’s **internal storage**.

---

## 📱 Features

- ✍️ Create and save notes with a title and content
- 📄 View a list of all saved notes
- 🔍 Open and view the full content of a note
- ✏️ Edit and update existing notes
- 🗑️ Delete notes with confirmation dialog
- 📂 Uses internal storage to store `.txt` files (one per note)

---

## 🧰 Technologies Used

- **Java** (Android SDK)
- **Android Studio**
- **XML Layouts**
- **RecyclerView**
- **Internal Storage (File I/O)**
- **Intents and Activities**
- **AlertDialog**

---

## 🧠 Key Components

### Activities

| Activity              | Purpose |
|-----------------------|---------|
| `MainActivity`        | Add new notes and navigate to the list |
| `NotesListActivity`   | Display all saved notes in a RecyclerView |
| `NoteDetailActivity`  | Edit or update a selected note |

### Storage

- Notes are saved as `.txt` files in the app's **internal storage** using `FileOutputStream`.
- Only accessible within the app (private mode).

### UI

- Custom layouts with `EditText`, `TextView`, `Button`, and `ImageView`
- List display using `RecyclerView` with a custom `Adapter`

---



## 🚀 How to Run

1. Open the project in **Android Studio**.
2. Let Gradle build finish and sync.
3. Connect your emulator or Android device.
4. Click **Run ▶️** to install and launch the app.
5. Start taking notes!

---

## 🔐 Permissions

No external permissions are required as the app uses **internal storage** only.

---
