package com.nengxin.example.exampleproject.model.util;

import com.nengxin.example.exampleproject.model.application.ApplicationData;

public class RemindUtil {
    private static RemindUtil remindUtil;

    private RemindUtil() {

    }

    public static RemindUtil getInstance() {
        if (remindUtil == null) {
            remindUtil = new RemindUtil();
        }
        return remindUtil;
    }

    /**
     * 根据错误的情况返回不同的提示语
     */

    public String returnErrorCode(String errorcode) {

        String msgx = null;
        try {

            switch (Integer.parseInt(errorcode)) {
                case 1:
                    msgx = "网络信号弱或服务器故障,请稍后再试";
                    break;
                case 2:
                    msgx = "未登录或登录已过期，请重新登录";
                    ApplicationData.isLogin = false;
                    break;
                case 3:
                    msgx = "登录失败，用户名或密码错误";
                    break;
                case 4:
                    msgx = "注册失败";
                    break;
                case 5:
                    msgx = "短信验证码错误或过期";
                    break;
                case 6:
                    msgx = "信息验证失败或已过期的请求";
                    break;
                case 7:
                    msgx = "上传头像失败";
                    break;
                case 8:
                    msgx = "充值失败";
                    break;
                case 9:
                    msgx = "收藏失败";
                    break;
                case 10:
                    msgx = "删除失败";
                    break;
                case 11:
                    msgx = "评价失败";
                    break;
                case 12:
                    msgx = "上传失败";
                    break;
                case 13:
                    msgx = "提交失败";
                    break;
                case 14:
                    msgx = "暂时没有数据";
                    break;
                case 15:
                    msgx = "已提交请勿重复提交";
                    break;
                case 16:
                    msgx = "没有记录数据";
                    break;
                case 100:
                    msgx = "Key校验失败 ";
                    break;
                default:
                    msgx = "系统错误 - 442751";
                    break;
            }

        } catch (Exception e) {
            msgx = "未知错误";
            e.printStackTrace();
        }
        return msgx;
    }
}
