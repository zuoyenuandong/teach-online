package com.kuang.service.edu;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {
    @Test
    public void genCode() {

        String prefix = "teach_online";
        String moduleName = "edu";

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("Kuang");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        gc.setServiceName("%sService");    //去掉Service接口的首字母I
        gc.setIdType(IdType.ASSIGN_ID); //主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(true);//开启Swagger2模式
        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://180.76.100.158/" + prefix + "_" + moduleName + "?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("Kuang001102");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName); //模块名
        pc.setParent("com.kuang.service");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(moduleName + "_");//设置表前缀不生成

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setLogicDeleteFieldName("is_deleted");//逻辑删除字段名
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);//去掉布尔值的is_前缀

        //自动填充
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符
        mpg.setStrategy(strategy);

        //设置BaseEntity
        strategy.setSuperEntityClass("com.kuang.service.base.model.BaseEntity");
        // 填写BaseEntity中的公共字段
        strategy.setSuperEntityColumns("id", "gmt_create", "gmt_modified");

        // 6、执行
        mpg.execute();
    }
    @Test
    public void mytest(){
        String str = "*.txt";
        String substring1 = str.substring(1, str.length());
        String substring = str.substring(0, 1);
        char v = substring.toCharArray()[0];
        char c = str.charAt(str.length() - 1);
        System.out.println(substring1);
    }
    @Test
    public void countNums(){
        int lIndex = 0;
        int time = 0;
        int typeLIndex = 0;
        int rIndex = 0;
        int typeRIndex = 0;
        boolean flag = false;
        boolean nextFlag = false;
        Map<String,Integer> map = new HashMap();
        String name = "Fe2(SO4)3";
        char[] names = name.toCharArray();
        for (int i = 0; i < names.length; i++) {
            if (names[i]=='('){
                lIndex = i;
                flag = true;
            }
            if (names[i]==')'){
                time = Integer.parseInt(String.valueOf(names[i+1]));
                rIndex = i;
                flag = false;
                char[] nameChild = name.substring(lIndex+1, rIndex).toCharArray();
                for (int j= 0;j<nameChild.length;j++){
                    if (Character.isUpperCase(nameChild[j])){
                        typeLIndex = j;
                        continue;
                    }
                    if (Character.isLowerCase(nameChild[j])){
                        typeRIndex = j;
                        String type = new String(nameChild, typeLIndex, typeRIndex + 1);
                        if (j==(nameChild.length-1)){
                            if (map.get(type)==null){
                                map.put(type,time);
                            }else {
                                map.put(type,map.get(type)+time);
                                time =1;
                            }
                        }else {
                            if (Character.isDigit(nameChild[j+1])){
                                if (map.get(type)==null){
                                    map.put(type,time*Integer.parseInt(String.valueOf(nameChild[j+1])));
                                }else {
                                    map.put(type,map.get(type)+time*Integer.parseInt(String.valueOf(nameChild[j+1])));
                                    time =1 ;
                                }
                            }
                        }
                    }
                }
            }
            if (flag=true){
                if (Character.isUpperCase(names[i])){
                    typeLIndex = i;
                    if (i==(names.length-1)){
                        if (map.get(String.valueOf(names[i+1]))==null){
                            map.put(String.valueOf(names[i+1]),1);
                        }else {
                            map.put(String.valueOf(names[i+1]),map.get(String.valueOf(names[i+1]))+1);
                        }
                    }else if (Character.isDigit(names[i+1])){
                        time = Integer.parseInt(String.valueOf(names[i+1]));
                        if (map.get(String.valueOf(names[i+1]))==null){
                            map.put(String.valueOf(names[i+1]),time);
                        }else {
                            map.put(String.valueOf(names[i+1]),map.get(String.valueOf(names[i+1]))+time);
                        }
                    }
                }
                if (Character.isLowerCase(names[i])){
                    typeRIndex = i;
                    String type = new String(names, typeLIndex, typeRIndex + 1);

                    if (i==(names.length-1)){
                        if (map.get(type)==null){
                            map.put(type,1);
                        }else {
                            map.put(type,map.get(type)+1);
                        }
                    }else {
                        if (Character.isDigit(names[i+1])){
                            if (map.get(type)==null){
                                map.put(type,time*Integer.parseInt(String.valueOf(names[i+1])));
                            }else {
                                map.put(type,map.get(type)+Integer.parseInt(String.valueOf(names[i+1])));
                                time =1 ;
                            }
                        }
                    }

                }
            }

        }
        System.out.println(map);

    }
    @Test
    public void countNums01(){
        int typeLIndex = 0;
        int rIndex = 0;
        int typeRIndex = 0;
        Map map = new HashMap();
        char[] nameChild = {'F','e','C'};
//        for (int j= 0;j<nameChild.length;j++){
//            if (Character.isUpperCase(nameChild[j])){
//                typeLIndex = j;
//                continue;
//            }
//            if (Character.isLowerCase(nameChild[j])){
//                typeRIndex = j;
//                System.out.println(new String(nameChild,typeLIndex,typeRIndex+1));
//
//            }
//        }
        System.out.println(map.get("hello"));
    }

}
