1. Cai CSDL mysql
Chay file database.sql

2. Setup backend
Vào game-doan-so\src\main\resources\application.properties
Cập nhật cấu hình username, password databse mysql theo cấu hình máy

``` bash
cd game-doan-so
mvnw clean install
mvnw spring-boot:run
```bash

2.2.test 1 số API trên postman
-Đăng ký tài khoản
**POST** `/api/auth/register`
Request Body:
```json
{
  "username": "quangphu27",
  "password": "matkhau123",
  "email": "phu@gmail.com"
}
```
Response:
```json
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJxdWFuZ3BodTI3MiIsImlhdCI6MTc2ODI4NzAyMiwiZXhwIjoxNzY4MzczNDIyfQ.5xeljBBLvhv9MZjr0__Thv8JDw2ElbInFkf_NpyJNzw1dFXmFEqI_aBd3IzF72Q5BhWLaX1enGl3YbfqM-GYMg",
    "message": "Registration successful"
}
```
### 2. Đăng nhập
**POST** `/api/auth/login`
Request Body:
```json
{
  "username": "quangphu27",
  "password": "matkhau123"
}
```
Response:
```json
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJxdWFuZ3BodTI3IiwiaWF0IjoxNzY4Mjg3MDU4LCJleHAiOjE3NjgzNzM0NTh9.KFhinjsfJvUCP6rOp7aQT6qSrhZduo_Sa5JD5Z5kuxqfcQS1nEHsLja67q_bjnrUY0ysm0RS94KCm3kQ0e-eog",
    "message": "Login successful"
}
```
### 3. Đoán số
**POST** `/api/game/guess`
Headers:
```
Authorization: Bearer {token}
```
Request Body:
```json
{
  "number": 3
}
```
response
{
    "correct": false,
    "serverNumber": 2,
    "userGuess": 3,
    "score": 4,
    "remainingTurns": 13,
    "message": "Sorry, try again!"
}

3.Chay Frontend (có thể truy cập trực tiếp https://game-doan-so-six.vercel.app/)
### Chay local
```bash
cd frontend
npm install
npm run dev
```