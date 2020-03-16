package com.qf.controller;

import com.qf.constant.CookieConstant;
import com.qf.dto.ResultBean;
import com.qf.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("shopcar")
public class ShopCarController {

    @Autowired
    private ShopCarService cartService;

    @RequestMapping("add/{productId}/{count}")
    @ResponseBody
    public ResultBean addProduct(@CookieValue(name = CookieConstant.USER_CART, required = false) String uuid,
                                 @PathVariable Long productId,
                                 @PathVariable int count,
                                 HttpServletResponse response){
        //把该商品添加到购物车，这个购物车是在redis中。

        if(uuid==null||"".equals(uuid)){
            //uuid为空的话再生成一个uuid放到cookie里给客户端
            uuid = UUID.randomUUID().toString();
            //返回该uuid给cookie
            Cookie cookie = new Cookie(CookieConstant.USER_CART,uuid);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        ResultBean resultBean = cartService.addProduct(uuid,productId,count);

        return resultBean;
    }


    /**
     * 清空购物车
     * @param uuid
     * @param response
     * @return
     */
    @RequestMapping("clean")
    @ResponseBody
    public ResultBean cleanCart(@CookieValue(name=CookieConstant.USER_CART,required = false)String uuid,
                                HttpServletResponse response){

        if(uuid!=null&&!"".equals(uuid)){
            //删除Cookie
            Cookie cookie = new Cookie(CookieConstant.USER_CART,"");
            cookie.setMaxAge(0);
            cookie.setPath("/");//admin.qf.com  sso.qf.com  ****.qf.com
            // cookie.setDomain("qf.com");//如果我们使用域名来访问，那么cookie不会被携带，只有这边设置了这个一级域名，qf.com,那么在该域名下的所有二级cookie就都可以携带该cookie
            response.addCookie(cookie);

            //删除redis中的购物车
            return cartService.clean(uuid);
        }
        return ResultBean.error("当前用户没有购物车");

    }

    /**
     * 更新购物车中的商品
     * @param productId
     * @param count
     * @param uuid
     * @return
     */
    @RequestMapping("update/{productId}/{count}")
    @ResponseBody
    public ResultBean updateCart(
            @PathVariable Long productId,
            @PathVariable int count,
            @CookieValue(name=CookieConstant.USER_CART,required = false)String uuid
    ){

        return cartService.update(uuid,productId,count);

    }



    @RequestMapping("show")
    @ResponseBody
    public ResultBean showCart(@CookieValue(name=CookieConstant.USER_CART,required = false)String uuid){

        //
        return cartService.showCart(uuid);

    }
}
