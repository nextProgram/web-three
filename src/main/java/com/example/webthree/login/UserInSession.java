package com.example.webthree.login;


import javafx.geometry.Side;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lhx
 * @date 2019/11/1
 */
@Data
public class UserInSession {
    private String userId;
    private String userName;
    private String certId;
    private List<String> consumerId;
    //private List<SidebarMenuVO> menulist;
    //所有验证的URL
    private Set<String> menuAuthUrls = new HashSet<String>();
    //存放用户能够访问的URL
    private Set<String> userUrls = new HashSet<String>();
    //存放用户锁具有的功能的key
    private Set<String> userFunction = new HashSet<String>();
}
