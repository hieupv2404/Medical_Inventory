##Inventory medical
I. **Tools & công nghệ sử dụng**

    * Spring boot 2.2.6.RELEASE
    * hibernate-core
    * JDK 1.8 or later
    * Maven 4.0.0
    * IDE InteliiJ any version
    * Mysql 8+
    * Postman
    
II. **Bước triển khai**

    1. Tạo Spring-boot project application
    2. Import thư viện cần dùng trong pom.xml
    3. Tạo packages cần thiết trong /src/main/java
    4. Tạo class trong gói tương ứng
    5. Cấu hình trong application.property
    5. Tạo class test để insert dữ liệu
    6. Run project trên Intellij
    
III. **Chi tiết**

> **Ý tưởng:** Tạo ra một trang web quản lý kho thuốc gồm một số chức năng: thêm sửa xóa sản phẩm, thể loại, xem lại lịch sử nhâp xuất kho,...
>
> 1.Tạo Spring-boot project application
>
>* New project > Spring Initializr (tùy chọn project SDK) > Next.
>* Điền *Group*, ví dụ: demo.springboot
>* Điền *Artifact*, ví dụ: demo.project > Next
>* Chọn Spring boot version > chọn Depedencies(web, thymeleaf) > Next
>* Đặt tên Project name > Chọn nơi lưu Project location > Finish.
>
> 2.Import thư viện cần dùng trong pom.xml
>
>* Thêm Hibernate-core
>* Thêm Mysql-conector
>
> 3.Tạo packages cần thiết trong /src/main/java
>
>* Controller: xử lý điều hướng trả về cho Client API dạng JSON
>* Data: Lưu trữ model và entity