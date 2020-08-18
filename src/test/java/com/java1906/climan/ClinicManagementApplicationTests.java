package com.java1906.climan;

import com.java1906.climan.data.model.*;
import com.java1906.climan.data.repo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClinicManagementApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;


    @Test
    void setupUsers() {
        userRepository.deleteAll();
        userInfoRepository.deleteAll();

        User user01 = new User();
        user01.setUsername("hieupv");
        user01.setPassword("123456");
        user01.setRole(RoleType.ADMIN);
        user01 = userRepository.save(user01);

        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("hieupv@email.com");
        userInfo.setName("Hieu");
        userInfo.setPhone("123123123");
        userInfo.setId(user01.getId());
        userInfoRepository.save(userInfo);
    }

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryValueRepository categoryDetailRepository;

    @Autowired
    private ProductInfoRepository productRepository;

    @Test
    void createCategorysAndProducts() {
        Category category = new Category();
        category.setName("Category A");
        category.setDescription("Category A Description");
        category = categoryRepository.save(category);

        CategoryValue categoryDetail01 = new CategoryValue();
        categoryDetail01.setCategory(category);
        categoryDetail01.setName("Category Value 01");
        categoryDetailRepository.save(categoryDetail01);

        CategoryValue categoryDetail02 = new CategoryValue();
        categoryDetail02.setCategory(category);
        categoryDetail02.setName("Category Value 02");
        categoryDetailRepository.save(categoryDetail02);

        ProductInfo product = new ProductInfo();

        product.setId(category.getId());
        product.setName("thuoc dong y");
        product.setDescription("chua benh");
        product.setImg_url("huy.jpg");
        product.setActiveFlag(1);
        productRepository.save(product);
    }

}
