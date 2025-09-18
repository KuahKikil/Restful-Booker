@"
# Restful-Booker API Automation (Rest Assured + TestNG)

## Prerequisites
- JDK 21 (atau minimal JDK 11)
- Maven 3.9+
- IntelliJ IDEA (opsional, untuk run via IDE)
- Git

## Struktur penting
- \`src/test/java/com/naniek/restassured/BelajarAutomation/API/ResfulBooker.java\`
- \`testng.xml\` (di root project)

## Cara jalanin test

### A. Via Maven (terminal)
1. Masuk ke folder project:
   \`\`\`bash
   cd ProjectMaven
   \`\`\`

2. Jalankan semua test **berdasarkan file suite** \`testng.xml\`:
   \`\`\`bash
   mvn -q -Dsurefire.suiteXmlFiles=testng.xml test
   \`\`\`
   > Kalau properti di atas belum didukung di POM, pakai opsi kelas:
   \`\`\`bash
   mvn -q -Dtest=ResfulBooker test
   \`\`\`

3. (Opsional) Bersihin build + jalanin lagi:
   \`\`\`bash
   mvn clean test
   \`\`\`

### B. Via IntelliJ IDEA
- **Klik kanan** \`testng.xml\` → **Run 'testng.xml'**  
- atau **klik kanan** di kelas \`ResfulBooker\` → **Run 'ResfulBooker'**  
- bisa juga run per-method (klik tanda ▶ di samping nama test).

### Notes
- Token auth dibuat otomatis di test (tidak perlu set manual).
- Base URL: \`https://restful-booker.herokuapp.com\`.

## Perintah Git yang sering dipakai
\`\`\`bash
git status
git add <file>
git commit -m "pesan commit"
git push
\`\`\`
"@ | Set-Content -Encoding UTF8 README.md
