create database EmployeeWorkManagement;
USE EmployeeWorkManagement;

CREATE TABLE IF NOT EXISTS roles (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) unique NOT NULL,
    password VARCHAR(100) NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    avatar VARCHAR(100),
    role_id INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS status (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS jobs (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tasks (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    user_id INT NOT NULL,
    job_id INT NOT NULL,
    status_id INT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (user_id) REFERENCES users (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (job_id) REFERENCES jobs (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (status_id) REFERENCES status (id)  ON DELETE CASCADE;

INSERT INTO roles ( name, description ) VALUES ("ROLE_ADMIN", "Quản lý trang");
INSERT INTO roles ( name, description ) VALUES ("ROLE_MANAGER", "Quản lý");
INSERT INTO roles ( name, description ) VALUES ("ROLE_USER", "Nhân viên");
INSERT INTO roles ( name, description ) VALUES ("ROLE_HR", "Human Resource");
INSERT INTO roles ( name, description ) VALUES ("ROLE_MARKETING", "Marketing");
INSERT INTO roles ( name, description ) VALUES ("ROLE_HR", "Human Resource");
INSERT INTO roles ( name, description ) VALUES ("ROLE_TEST7", "Quan Ly Test7");

INSERT INTO status ( name ) VALUES ("Chưa thực hiện");
INSERT INTO status ( name ) VALUES ("Đang thực hiện");
INSERT INTO status ( name ) VALUES ("Đã hoàn thành");
INSERT INTO status ( name ) VALUES ("Status test");

INSERT INTO users (email, password, fullname, avatar, role_id) 
VALUES ("nguyenvana@gmail.com","123456","Nguyễn Văn A","",1);
INSERT INTO users (email, password, fullname, avatar, role_id) 
VALUES ("nguyenvanb@gmail.com","123","Nguyễn Văn B","",3);
INSERT INTO users (email, password, fullname, avatar, role_id) 
VALUES ("nguyenvanc@gmail.com","123","Nguyễn Văn C","",2);
INSERT INTO users (email, password, fullname, avatar, role_id) 
VALUES ("daohoanh.work@gmail.com","daohoanh","Đào Hồ Anh","",3);
INSERT INTO users (email, password, fullname, avatar, role_id) 
VALUES ("buiducvinh@gmail.com","buiducvinh","Bùi Đức Vinh","",2);
INSERT INTO users (email, password, fullname, avatar, role_id) 
VALUES ("phamlochao@gmail.com","phamlochao","Phạm Lộc Hào","",3);
INSERT INTO users (email, password, fullname, avatar, role_id) 
VALUES ("buixuanhuan@gmail.com","buixuanhuan","Bùi Xuân Huấn","",3);
INSERT INTO users (email, password, fullname, avatar, role_id) 
VALUES ("jack97@gmail.com","trinhtranphuongtuan","Trịnh Trần Phương Tuấn","",2);
INSERT INTO users (email, password, fullname, avatar, role_id) 
VALUES ("nguyenthanhtung@gmail.com","nguyenthanhtung","Sơn Tùng","",2);
INSERT INTO users (email, password, fullname, avatar, role_id) 
VALUES ("nguyenvand@gmail.com","123","Nguyễn Văn D","",3);
INSERT INTO users (email, password, fullname, avatar, role_id) 
VALUES ("nguyenvane@gmail.com","123456","Nguyễn Văn E","",3);
INSERT INTO users (email, password, fullname, avatar, role_id) 
VALUES ("nguyenvanF@gmail.com","123123","Nguyễn Văn F","",3);
INSERT INTO users (email, password, fullname, avatar, role_id) 
VALUES ("nguyenvang@gmail.com","123","Nguyễn Văn G","",2);
INSERT INTO users (email, password, fullname, avatar, role_id) 
VALUES ("nguyenvanh@gmail.com","123456","Nguyễn Văn H","",2);
INSERT INTO users (email, password, fullname, avatar, role_id) 
VALUES ("nguyenvani@gmail.com","123","Nguyễn Văn I","",3);
INSERT INTO users (email, password, fullname, avatar, role_id) 
VALUES ("huynhphungha@gmail.com","huynhphungha","Huỳnh Phùng Há","",3);

INSERT INTO jobs (name, start_date, end_date)
VALUES ("Web quần áo", "2023-04-14", "2023-06-14");
INSERT INTO jobs (name, start_date, end_date)
VALUES ("App giao đồ ăn nhanh", "2023-04-20", "2023-06-25");
INSERT INTO jobs (name, start_date, end_date)
VALUES ("Game đào vàng", "2023-05-02", "2023-05-20");
INSERT INTO jobs (name, start_date, end_date)
VALUES ("Web mạng xã hội", "2023-07-01", "2023-10-30");
INSERT INTO jobs (name, start_date, end_date)
VALUES ("Phần mềm quản lý khách sạn", "2023-08-05", "2023-09-17");
INSERT INTO jobs (name, start_date, end_date)
VALUES ("Dự án test", "2023-11-11", "2023-12-11");

INSERT INTO tasks (name, start_date, end_date, user_id, job_id, status_id)
VALUES ("Thiết kế database", "2023-04-15", "2023-04-18", 4, 1, 1);
INSERT INTO tasks (name, start_date, end_date, user_id, job_id, status_id)
VALUES ("Tạo trang login", "2023-04-19", "2023-04-21", 1, 1, 2);
INSERT INTO tasks (name, start_date, end_date, user_id, job_id, status_id)
VALUES ("Viết Api login", "2023-04-19", "2023-04-22", 2, 1, 3);
INSERT INTO tasks (name, start_date, end_date, user_id, job_id, status_id)
VALUES ("Thiết kế giỏ hàng", "2023-04-30", "2023-05-03", 3, 2, 2);
INSERT INTO tasks (name, start_date, end_date, user_id, job_id, status_id)
VALUES ("Làm chức năng thêm vào giỏ hàng", "2023-04-30", "2023-05-03", 3, 2, 2);
INSERT INTO tasks (name, start_date, end_date, user_id, job_id, status_id)
VALUES ("Làm chức năng quên mật khẩu", "2023-05-03", "2023-05-05", 4, 4, 3);
INSERT INTO tasks (name, start_date, end_date, user_id, job_id, status_id)
VALUES ("Thiết kế chuyển động nhân vật", "2023-05-02", "2023-05-05", 5, 3, 2);
INSERT INTO tasks (name, start_date, end_date, user_id, job_id, status_id)
VALUES ("Tính điểm khi nhặt được vật phẩm", "2023-05-05", "2023-05-08", 6, 3, 3);
INSERT INTO tasks (name, start_date, end_date, user_id, job_id, status_id)
VALUES ("Mã hóa mật khẩu", "2023-05-06", "2023-05-08", 4, 4, 2);

-- select query
select * from users;
select * from jobs;
select * from roles;
select * from status;
select * from tasks;