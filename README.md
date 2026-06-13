# 🚀 Next-IDE

<p align="center">
  <img src="assets/nextide_logo.png" width="96" alt="Next-IDE Logo" />
</p>

<h3 align="center">A lightweight Android IDE alternative built directly for Android.</h3>

<p align="center">
  <img src="https://img.shields.io/badge/platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android" />
  <img src="https://img.shields.io/badge/language-Java-orange?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/AIDE-Compatible-blue?style=for-the-badge" alt="AIDE Compatible" />
  <img src="https://img.shields.io/badge/status-In%20Development-red?style=for-the-badge" alt="Status" />
</p>

---

## ✨ About

**Next-IDE** is a project written as an Android IDE alternative.

It is designed for developers who want to open, edit, manage, build, and debug Android projects directly on an Android device without needing a desktop setup.

The goal is to create an AIDE-style mobile development environment with a clean interface, project navigation, code editing, build tools, APK output, and useful debugging logs.

---

## 📱 Preview

<p align="center">
  <img src="screenshots/no-open-files.png" width="320" alt="Next-IDE No Open Files Screen" />
</p>

---

## 🔥 Features

### 🗂 Project Handling

- Open Android projects from local storage.
- Set the selected folder as the active project base directory.
- Show and hide project-opening options depending on whether a project is currently loaded.
- Close the current project and return to the empty IDE state.

### 📄 File Editing

- AIDE-style editor layout.
- Empty state screen when no files are open.
- File picker bottom sheet for browsing project files.
- Open files into editor tabs.
- Multiple open file support.
- File tab switching.
- Long-press file tab behaviour support.

### 🎨 Code Editor Styling

- Syntax highlighting support.
- Java keyword highlighting.
- Type/import highlighting.
- Comment, string, number, and annotation colouring.
- Line number display.
- Optimised scrolling performance.
- Android-friendly editor behaviour.

### 🧰 Android Build System

Next-IDE is being built to support an on-device Android build pipeline using tools such as:

- `javac`
- `ecj.jar`
- `aapt` / `aapt2`
- `d8.jar`
- `apksigner.jar`
- `zipalign`
- Android platform `android.jar`
- Debug and release keystores

Build output is intended to produce installable APK files directly from the selected project.

### 📝 Build Logs

- Generate build logs as text files.
- Save build logs at the base of the open project.
- Use logs for debugging compile errors and failed APK builds.

Example target output:

```text
/project-root/NextIDE-build-log.txt
```

### 🧩 UI Behaviour

- Top toolbar with run/build button.
- Edit button.
- Overflow menu.
- Bottom sheet actions.
- Bottom sheet protection so it does not disappear when opening overflow actions.
- Floating open-project button.
- Clean empty state matching a mobile IDE style.

---

## 🛠 Tech Stack

| Area | Technology |
|---|---|
| Platform | Android |
| Language | Java |
| UI | Native Android Views |
| Build Compatibility | AIDE-compatible Java |
| Minimum Style Target | Mobile-first IDE layout |
| Build Tools | ECJ, Javac, AAPT/AAPT2, D8, APK Signer, Zipalign |

---

## 📂 Suggested Project Structure

```text
Next-IDE/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       ├── res/
│   │       └── AndroidManifest.xml
│   └── build.gradle
├── assets/
│   └── nextide_logo.png
├── screenshots/
│   └── no-open-files.png
├── tools/
│   ├── ecj.jar
│   ├── javac
│   ├── d8.jar
│   ├── apksigner.jar
│   └── zipalign
├── README.md
└── LICENSE
```

---

## 🚀 Vision

Next-IDE aims to become a complete Android development environment that runs fully on Android.

The long-term goal is to support:

- Opening complete Android projects.
- Editing project source files.
- Managing multiple files in tabs.
- Running project builds from inside the app.
- Viewing build output and errors clearly.
- Producing signed APK files.
- Installing and testing APKs directly from the device.
- Supporting modern Android SDK versions.
- Keeping compatibility with AIDE-style Java projects.

---

## 📦 Planned Build Flow

```text
Open Project
     ↓
Scan AndroidManifest.xml
     ↓
Find Java / XML / Resource Files
     ↓
Compile Java Sources
     ↓
Package Resources
     ↓
Dex Classes
     ↓
Build APK
     ↓
Sign APK
     ↓
Align APK
     ↓
Install / Export
```

---

## ✅ Compatibility Goals

Next-IDE is designed with Android-only development in mind.

Important compatibility goals:

- No desktop required.
- No lambda-only code requirement.
- Friendly to AIDE-style Java projects.
- Works with Android project folders stored on-device.
- Designed for older and newer Android SDK targets.
- Build tools stored locally inside the app or user storage.

---

## 🧪 Current Status

Next-IDE is currently in active development.

Implemented and experimental areas include:

- Main IDE interface.
- Empty editor state.
- File picker UI.
- Project opening behaviour.
- Toolbar actions.
- Bottom sheet behaviour.
- Code editor improvements.
- Syntax highlighting improvements.
- Build pipeline experiments.
- Build log generation.

---

## 🗺 Roadmap

- [ ] Improve project opening flow.
- [ ] Add full project tree navigation.
- [ ] Add stable multi-tab editor support.
- [ ] Add save and save-all actions.
- [ ] Add APK build button support.
- [ ] Add complete build log viewer.
- [ ] Add install APK action.
- [ ] Add SDK/platform selector.
- [ ] Add keystore manager.
- [ ] Add release/debug build variants.
- [ ] Add XML preview tools.
- [ ] Add icon/vector tools.
- [ ] Improve AIDE-style UI matching.
- [ ] Add plugin/tool support.

---

## 🤝 Contributing

Contributions, ideas, testing, screenshots, bug reports, and feature suggestions are welcome.

When contributing code, please keep the project Android-friendly and avoid features that break compatibility with on-device Java builders.

Recommended contribution style:

- Keep Java code simple and readable.
- Avoid unnecessary dependencies.
- Keep compatibility with older Android APIs where possible.
- Test UI changes on real Android devices.
- Include screenshots for visual changes.
- Include build logs when reporting build issues.

---

## 🐛 Reporting Issues

When opening an issue, please include:

```text
Device:
Android version:
Next-IDE version/build:
Project type:
What you expected:
What happened:
Build log, if available:
Screenshots, if useful:
```

---

## 📸 Screenshots

Add screenshots inside the `screenshots/` folder and reference them here.

```md
![No open files](screenshots/no-open-files.png)
![Editor view](screenshots/editor.png)
![File picker](screenshots/file-picker.png)
![Build output](screenshots/build-output.png)
```

---

## 📜 License

This project is currently under development.

Add your preferred license here, for example:

- MIT License
- Apache License 2.0
- GPLv3
- Custom license

---

## ⭐ Support

If you like the project, consider starring the repository and following development.

<p align="center">
  <strong>Next-IDE — Android development, built on Android.</strong>
</p>
