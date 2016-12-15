package com.nengxin.example.exampleproject.model.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @class StringToUTF8
 * @描述 本类主要功能为：
 * 将从网上下载的文字数据转成标准的UTF-8数据
 * 内含多个方法和模块以及若干功能，
 * 详情注释请看每个功能对应的代码注释!
 */
public class StringToUTF8 {
    /**
     * Get XML String of utf-8
     *
     * @return XML-Formed string
     */
    public static String getUTF8XMLString(String xml) {
        StringBuffer sb = new StringBuffer();
        sb.append(xml);
        String xmString = "";
        String xmlUTF8 = "";
        try {
            xmlUTF8 = URLDecoder.decode(xml, "UTF-8");
//			xmString = new String(sb.toString().getBytes("UTF-8"));
//			xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
//			System.out.println("utf-8" + xmlUTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return xmlUTF8;
    }

}
