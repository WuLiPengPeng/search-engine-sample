package com.wlp.knowledgepower.controller;

import com.wlp.knowledgepower.model.User;
import com.wlp.knowledgepower.service.UserService;
import com.wlp.knowledgepower.util.SpringContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: wlp
 * @create: 2020-02-27 13:55
 * @description: 测试
 **/
@Controller
public class TestController {

    @RequestMapping("/test")
    public String test(HttpServletRequest request , HttpServletResponse response){
        String l = request.getParameter("l");
        System.out.println(l);
        try {
//            GetResponse getResponse = ESUtil.getInstance().getDoc("mytest", "1");
//            if (getResponse.isExists()) {
//                long version = getResponse.getVersion();
//                System.out.println("version: "+version);
//                String sourceAsString = getResponse.getSourceAsString();
//                System.out.println("sourceAsString: "+sourceAsString);
//                Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
//                System.out.println("sourceAsMap: "+sourceAsMap.toString());
//            }

//            boolean mytest = ESUtil.getInstance().docExists("mytest", "1");
//            System.out.println("mytest 1 是否存在: "+mytest);

//            SearchResponse searchResponse = ESUtil.getInstance().searchDoc("mytest", "");
//            RestStatus status = searchResponse.status();
//            System.out.println("searchResponse status : "+status);
//            if(status == RestStatus.OK){
//                SearchHits hits = searchResponse.getHits();
//                SearchHit[] searchHits = hits.getHits();
//                for(SearchHit hit : searchHits){
//                    String index = hit.getIndex();
//                    System.out.println("index :"+index);
//                    String id = hit.getId();
//                    System.out.println("id :"+id);
//                    float score = hit.getScore();
//                    System.out.println("score :"+score);
//                    String sourceAsString = hit.getSourceAsString();
//                    System.out.println("sourceAsString :"+sourceAsString);
//                }
//
//            }
            UserService userService = SpringContextUtil.getBean("userService", UserService.class);
            User user = userService.selectUserByUsername("wlp");

            System.out.println("getId :"+user.getId());
            System.out.println("getUsername :"+user.getUsername());
            System.out.println("getRealName :"+user.getRealName());
            System.out.println("getPassword :"+user.getPassword());
            System.out.println("getCreateTime :"+user.getCreateTime());
            System.out.println("getLastLoginTime :"+user.getLastLoginTime());
            System.out.println("isEnabled :"+user.isEnabled());
            System.out.println("isAccountNonExpired :"+user.isAccountNonExpired());
            System.out.println("isAccountNonLocked :"+user.isAccountNonLocked());
            System.out.println("isCredentialsNonExpired :"+user.isCredentialsNonExpired());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
}
