# ใช้ Java 17 เป็น base image
FROM openjdk:17-jdk-slim

# สร้าง directory ชื่อ app
WORKDIR /app

# copy jar ไปไว้ใน container
COPY target/nail-booking-app.jar app.jar

# เปิด port 8080 สำหรับ web server
EXPOSE 8080

# คำสั่งรัน spring boot
ENTRYPOINT ["java", "-jar", "app.jar"]
