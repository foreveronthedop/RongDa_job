package com.llm.crawl.main;

import com.alibaba.dubbo.config.annotation.Reference;
import com.llm.crawl.link.Links;
import com.llm.crawl.page.Page;
import com.llm.crawl.page.PageParserTool;
import com.llm.crawl.page.RequestAndResponseTool;

import com.llm.demo.SecuritiesService;
import com.llm.demo.entity.Announcement;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 抓取页面
 */
public class MyCrawler{

    @Reference
    private SecuritiesService securitiesService;


    /**
     * 使用种子初始化 URL 队列
     *
     * @param seeds 种子 URL
     * @return
     */
    private void initCrawlerWithSeeds(String[] seeds) {
        for (int i = 0; i < seeds.length; i++){
            Links.addUnvisitedUrlQueue(seeds[i]);
        }
    }


    /**
     * 抓取过程
     *
     * @param seeds
     * @return
     */
    public void crawling(String[] seeds) {

        //初始化 URL 队列
        initCrawlerWithSeeds(seeds);

//        //定义过滤器，提取以 http://www.baidu.com 开头的链接
//        LinkFilter filter = new LinkFilter() {
//            public boolean accept(String url) {
//                if (url.startsWith("http://www.baidu.com"))
//                    return true;
//                else
//                    return false;
//            }
//        };

        //循环条件：待抓取的链接不空且抓取的网页不多于 1000
        while (!Links.unVisitedUrlQueueIsEmpty()  && Links.getVisitedUrlNum() <= 1000) {

            //先从待访问的序列中取出第一个；
            String visitUrl = (String) Links.removeHeadOfUnVisitedUrlQueue();
            if (visitUrl == null){
                continue;
            }

            //根据URL得到page;
            Page page = RequestAndResponseTool.sendRequstAndGetResponse(visitUrl);

            //对page进行处理： 访问DOM的某个标签
            Elements es = PageParserTool.select(page,"b");
            if(!es.isEmpty()){
                System.out.println("下面将打印所有b标签： ");
                System.out.println(es);
            }
//            es = PageParserTool.select(page,"span");
//            if(!es.isEmpty()){
//                System.out.println("下面将打印所有span标签： ");
//                System.out.println(es);
//            }
            Document doc = page.getDoc();
            es = doc.getElementsByAttributeValue("id", "lTitle");
            if(!es.isEmpty()){
                System.err.println(es.text());
                System.out.println("下面将打印 id=\"lTitle\" 的标签： ");
                System.out.println(es);
            }
            es = doc.getElementsByAttributeValue("id", "lSubcat");
            if(!es.isEmpty()){
                System.err.println(es.text());
                System.out.println("下面将打印 id=\"lSubcat\" 的标签： ");
                System.out.println(es);
            }
            Elements b = doc.select("table tbody tr td tbody tr td");
            Announcement announcement = new Announcement();
            int i = 1;
            for(Element e:b) {
                if(i==1){announcement.setIndexno(e.text()); }
                else if(i==2){announcement.setOrganization(e.text());}
                else if(i==3){announcement.setRoles(e.text());}
                else if(i==4){announcement.setCreattime(e.text());}
                else if(i==5){announcement.setName(e.text());}
                else if(i==6){announcement.setContent(e.text());}
            }
            System.out.println(announcement);
        }
           /* es = doc.getElementsMatchingText("、");
            if(!es.isEmpty()){
                System.out.println("下面将打印  的标签： ");
                System.out.println(es);
            }

            es = doc.getElementsMatchingOwnText("审核的发行人");
          f(!es.isEmpty()){
                System.out.println("下面将打印  的标签： ");
                System.out.println(es);
            }

            //将保存文件
//            FileTool.saveToLocal(page);

            //将已经访问过的链接放入已访问的链接中；
            Links.addVisitedUrlSet(visitUrl);

            //得到超链接
//            Set<String> links = PageParserTool.getLinks(page,"img");
//            for (String link : links) {
//                Links.addUnvisitedUrlQueue(link);
//                System.out.println("新增爬取路径: " + link);
//            }*/
    }
    //main 方法入口
    public static void main(String[] args) {
        MyCrawler crawler = new MyCrawler();
        crawler.crawling(new String[]{"http://www.csrc.gov.cn/pub/zjhpublic/G00306202/201712/t20171229_330048.htm"});
    }

}
