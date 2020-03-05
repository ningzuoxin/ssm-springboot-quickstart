package com.ning.core.util;

import cn.hutool.json.JSONUtil;
import com.ning.core.model.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 响应工具类
 */
public class RenderUtil {

    public static void renderJson(HttpServletResponse response, Result result) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSONUtil.toJsonStr(result));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println("RenderUtil renderJson exception => " + e.getMessage());
        }
    }
}
