package com.simpletech.webanalytics.model.entity;

import com.simpletech.webanalytics.annotations.Must;
import com.simpletech.webanalytics.model.ShareUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * JS探针发送的数据格式+服务器获取数据
 */
public class JsUser extends ShareUser{

    /**
     * 数据收集相关
     */
    @Must("当前页面的完整URL")
    private String url;     //(必需) 当前页面的完整URL

    public void bind(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        setCity(URLDecoder.decode(getCity(), "utf-8"));
        setUrl(URLDecoder.decode(getUrl(), "utf-8"));
        setCountry(URLDecoder.decode(getCountry(), "utf-8"));
        setNickname(URLDecoder.decode(getNickname(), "utf-8"));
        setProvince(URLDecoder.decode(getProvince(), "utf-8"));
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setSex(String sex) {
        if (sex != null && sex.length() > 9) {
            sex = sex.substring(0, 9);
        }
        super.setSex(sex);
    }

    @Override
    public void setNickname(String nickname) {
        if (nickname != null && nickname.length() > 31) {
            nickname = nickname.substring(0, 31);
        }
        super.setNickname(nickname);
    }

    @Override
    public void setCity(String city) {
        if (city != null && city.length() > 31) {
            city = city.substring(0, 31);
        }
        super.setCity(city);
    }

    @Override
    public void setProvince(String province) {
        if (province != null && province.length() > 31) {
            province = province.substring(0, 31);
        }
        super.setProvince(province);
    }

    @Override
    public void setCountry(String country) {
        if (country != null && country.length() > 31) {
            country = country.substring(0, 31);
        }
        super.setCountry(country);
    }

    @Override
    public void setPrivilege(String privilege) {
        if (privilege != null && privilege.length() > 31) {
            privilege = privilege.substring(0, 31);
        }
        super.setPrivilege(privilege);
    }

    @Override
    public void setHeadimgurl(String headimgurl) {
        if (headimgurl != null && headimgurl.length() > 255) {
            headimgurl = headimgurl.substring(0, 255);
        }
        super.setHeadimgurl(headimgurl);
    }


}