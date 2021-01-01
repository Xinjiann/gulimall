package com.xinjian.gulimall.product;

import com.xinjian.gulimall.product.entity.BrandEntity;
import com.xinjian.gulimall.product.service.BrandService;
import org.testng.annotations.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class GulimallProductApplicationTests {

	@Autowired
	BrandService brandService;

	@Test
	void contextLoads() {

		BrandEntity brandEntity = new BrandEntity();
		brandEntity.setBrandId(1L);
		brandEntity.setDescript("566247");
		brandService.updateById(brandEntity);
		System.out.println("成功");
	}

}
