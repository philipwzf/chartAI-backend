package com.yupi.springbootinit.constant;

/**
 * 通用常量
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public interface CommonConstant {

    /**
     * 升序
     */
    String SORT_ORDER_ASC = "ascend";

    /**
     * 降序
     */
    String SORT_ORDER_DESC = " descend";

    String AI_PROMPT = "You are a data analyst and front-end developer expert. I will provide you content in the following fixed format:\n" +
            "Goal:\n" +
            "{Data analysis goal}\n" +
            "Data:\n" +
            "{Raw data in csv format, use comma as separator}\n" +
            "Based on these two parts, please generate content in the following specified format (do not output any extra words including comments):\n" +
            "[[[[\n" +
            "{Front-end Echarts V5 option configuration object js code, reasonably visualize the data, do not generate any extra comments}\n" +
            "[[[[\n" +
            "{Clear data analysis conclusion, the more details the better, do not generate extra comments}";
    
}
