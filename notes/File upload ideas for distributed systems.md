---
title: File upload ideas for distributed systems
date: 2021-01-01 02:38:29
tags: 
  - Springboot
  - Java Web
category: Springboot project
swiper: true
swiperImg: '/images/about.jpg'
top: True
---

# File upload ideas for distributed systems

In distributed system, services are deployed on different servers, an OSS(Object Stroage Service)is neccessary to avoid files in didferent servers that could not be found.

![](/images/oss.jpg)


Here we use the cloud OSS from Alibaba.

## 1. File Uploading

### 1.1. Access Alibaba Cloud Oss - [Aliyun](https://cn.aliyun.com/)

![](/images/oss1.png)

Simply following the instruction.

### 1.2. Testing

pom.xml

```java
<dependency>
	<groupId>com.alibaba.cloud</groupId>
	<artifactId>spring-cloud-starter-alicloud-oss</artifactId>
</dependency>
```

```java

@Autowired
OSSClient ossClient;
 
@Test
public void testUpload() throws FileNotFoundException {

	InputStream inputStream = new FileInputStream("D:\\aaa.jpg");
 
	ossClient.putObject("gulimall-xerxes", "aaa.jpg", inputStream);
 
	ossClient.shutdown();
 
	System.out.println("upload finished");
}
```

### 1.3. Create a third-party service project and return the signature

[](https://github.com/alibaba/spring-cloud-alibaba/blob/master/spring-cloud-alibaba-examples/oss-example/readme-zh.md)

```java
@RestController
public class OssController {
 
    @Autowired
    OSS ossClient;
 
    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endpoint;
    @Value("${spring.cloud.alicloud.oss.bucket}")
    private String bucket;
 
    @Value("${spring.cloud.alicloud.access-key}")
    private String accessId;
 
 
    @RequestMapping("/oss/policy")
    public R policy() {
        String host = "https://" + bucket + "." + endpoint; 
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dir = format + "/";
 
        Map<String, String> respMap = null;
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
 
            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);
 
            respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            // respMap.put("expire", formatISO8601Date(expiration));
        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
        }
        return R.ok().put("data", respMap);
    }
}
```

### 1.4. Upload files using direct upload after signing on the server

### 1.5. Create Alibaba Cloud sub-users to access Alibaba Cloud OSS objects

![](/images/oss2.png)


![](/images/0ss3.png)

### 1.6. Add authority 

![](/images/oss4.png)

### 1.7. Enable cross-domain access

![](/images/oss5.png)

## 2. Front end verification

### 2.1. Binding verification rules

```python
:rules="dataRule"
```
### 2.2 Write verification rules
```java
dataRule: {
	name: [{ required: true, message: "品牌名不能为空", trigger: "blur" }],
	logo: [
	  { required: true, message: "品牌logo地址不能为空", trigger: "blur" }
	],
	descript: [
	  { required: true, message: "介绍不能为空", trigger: "blur" }
	],
	showStatus: [
	  {
		required: true,
		message: "显示状态[0-不显示；1-显示]不能为空",
		trigger: "blur"
	  }
	],
	firstLetter: [
	  {
		validator: (rule, value, callback) => {
		  if (value == "") {
			callback(new Error("首字母必须填写"));
		  } else if (!/^[a-zA-Z]$/.test(value)) {
			callback(new Error("首字母必须a-z或者A-Z之间"));
		  } else {
			callback();
		  }
		},
		trigger: "blur"
	  }
	],
	sort: [
	  {
		validator: (rule, value, callback) => {
		  if (value == "") {
			callback(new Error("排序字段必须填写"));
		  } else if (!Number.isInteger(value) || value<0) {
			callback(new Error("排序必须是一个大于等于0的整数"));
		  } else {
			callback();
		  }
		},
		trigger: "blur"
	  }
	]
  }
};
```

brand-add-or-update.vue

```java
<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible"
  >
    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      @keyup.enter.native="dataFormSubmit()"
      label-width="140px"
    >
      <el-form-item label="品牌名" prop="name">
        <el-input v-model="dataForm.name" placeholder="品牌名"></el-input>
      </el-form-item>
      <el-form-item label="品牌logo地址" prop="logo">
        <!-- <el-input v-model="dataForm.logo" placeholder="品牌logo地址"></el-input> -->
        <single-upload v-model="dataForm.logo"></single-upload>
      </el-form-item>
      <el-form-item label="介绍" prop="descript">
        <el-input v-model="dataForm.descript" placeholder="介绍"></el-input>
      </el-form-item>
      <el-form-item label="显示状态" prop="showStatus">
        <el-switch
          v-model="dataForm.showStatus"
          active-color="#13ce66"
          inactive-color="#ff4949"
          :active-value="1"
          :inactive-value="0"
        ></el-switch>
      </el-form-item>
      <el-form-item label="检索首字母" prop="firstLetter">
        <el-input v-model="dataForm.firstLetter" placeholder="检索首字母"></el-input>
      </el-form-item>
      <el-form-item label="排序" prop="sort">
        <el-input v-model.number="dataForm.sort" placeholder="排序"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>
 
<script>
import SingleUpload from "@/components/upload/singleUpload";
export default {
  components: { SingleUpload },
  data() {
    return {
      visible: false,
      dataForm: {
        brandId: 0,
        name: "",
        logo: "",
        descript: "",
        showStatus: 1,
        firstLetter: "",
        sort: 0
      },
      dataRule: {
        name: [{ required: true, message: "品牌名不能为空", trigger: "blur" }],
        logo: [
          { required: true, message: "品牌logo地址不能为空", trigger: "blur" }
        ],
        descript: [
          { required: true, message: "介绍不能为空", trigger: "blur" }
        ],
        showStatus: [
          {
            required: true,
            message: "显示状态[0-不显示；1-显示]不能为空",
            trigger: "blur"
          }
        ],
        firstLetter: [
          {
            validator: (rule, value, callback) => {
              if (value == "") {
                callback(new Error("首字母必须填写"));
              } else if (!/^[a-zA-Z]$/.test(value)) {
                callback(new Error("首字母必须a-z或者A-Z之间"));
              } else {
                callback();
              }
            },
            trigger: "blur"
          }
        ],
        sort: [
          {
            validator: (rule, value, callback) => {
              if (value == "") {
                callback(new Error("排序字段必须填写"));
              } else if (!Number.isInteger(value) || value<0) {
                callback(new Error("排序必须是一个大于等于0的整数"));
              } else {
                callback();
              }
            },
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    init(id) {
      this.dataForm.brandId = id || 0;
      this.visible = true;
      this.$nextTick(() => {
        this.$refs["dataForm"].resetFields();
        if (this.dataForm.brandId) {
          this.$http({
            url: this.$http.adornUrl(
              `/product/brand/info/${this.dataForm.brandId}`
            ),
            method: "get",
            params: this.$http.adornParams()
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.dataForm.name = data.brand.name;
              this.dataForm.logo = data.brand.logo;
              this.dataForm.descript = data.brand.descript;
              this.dataForm.showStatus = data.brand.showStatus;
              this.dataForm.firstLetter = data.brand.firstLetter;
              this.dataForm.sort = data.brand.sort;
            }
          });
        }
      });
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/product/brand/${!this.dataForm.brandId ? "save" : "update"}`
            ),
            method: "post",
            data: this.$http.adornData({
              brandId: this.dataForm.brandId || undefined,
              name: this.dataForm.name,
              logo: this.dataForm.logo,
              descript: this.dataForm.descript,
              showStatus: this.dataForm.showStatus,
              firstLetter: this.dataForm.firstLetter,
              sort: this.dataForm.sort
            })
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.visible = false;
                  this.$emit("refreshDataList");
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    }
  }
};
</script>
```

## 3. JSR303 Validation

### 3.1 Group check

```java

    @RequestMapping("/update")
    public R update(@Validated(value = {UpdateGroup.class}) @RequestBody BrandEntity brand) {
        brandService.updateById(brand);
 
        return R.ok();
    }
 
    @RequestMapping("/update/status")
    public R updateStatus(@Validated(value = {UpdateStatusGroup.class}) @RequestBody BrandEntity brand) {
        brandService.updateById(brand);
        return R.ok();
    }
```

BrandEntity.java

```java

@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "修改必须指定品牌id", groups = {UpdateGroup.class})
    @Null(message = "新增不能指定id", groups = {AddGroup.class})
    @TableId
    private Long brandId;

    @NotBlank(message = "品牌名必须提交", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    @NotBlank(groups = {AddGroup.class})
    @URL(message = "logo必须是一个合法的url地址", groups = {AddGroup.class, UpdateGroup.class})
    private String logo;

    private String descript;


    @NotNull(groups = {AddGroup.class, UpdateStatusGroup.class})
    @ListValue(vals = {0, 1}, groups = {AddGroup.class, UpdateStatusGroup.class})
    private Integer showStatus;

    @NotEmpty(groups = {AddGroup.class})
    @Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是一个字母", groups = {AddGroup.class, UpdateGroup.class})
    private String firstLetter;

    @NotNull(groups = {AddGroup.class})
    @Min(value = 0, message = "排序必须大于等于0", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;
 
}
```



