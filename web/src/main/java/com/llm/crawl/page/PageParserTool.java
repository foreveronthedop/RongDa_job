package com.llm.crawl.page;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PageParserTool {


    /* ͨ��ѡ������ѡȡҳ��� */
    public static Elements select(Page page , String cssSelector) {
        return page.getDoc().select(cssSelector);
    }

    /*
     *  ͨ��cssѡ�������õ�ָ��Ԫ��;
     *
     *  */
    public static Element select(Page page , String cssSelector, int index) {
        Elements eles = select(page , cssSelector);
        int realIndex = index;
        if (index < 0) {
            realIndex = eles.size() + index;
        }
        return eles.get(realIndex);
    }


    /**
     * ��ȡ����ѡ������Ԫ���е����� ѡ����cssSelector���붨λ������ĳ�����
     * �����������ȡidΪcontent��div�е����г����ӣ�����
     * ��Ҫ��cssSelector����Ϊdiv[id=content] a
     *  ����set �� ��ֹ�ظ���
     * @param cssSelector
     * @return
     */
    public static  Set<String> getLinks(Page page ,String cssSelector) {
        Set<String> links  = new HashSet<String>() ;
        Elements es = select(page , cssSelector);
        Iterator iterator  = es.iterator();
        while(iterator.hasNext()) {
            Element element = (Element) iterator.next();
            if ( element.hasAttr("href") ) {
                links.add(element.attr("abs:href"));
            }else if( element.hasAttr("src") ){
                links.add(element.attr("abs:src"));
            }
        }
        return links;
    }



    /**
     * ��ȡ��ҳ������ָ��cssѡ����������Ԫ�ص�ָ�����Եļ���
     * ����ͨ��getAttrs("img[src]","abs:src")�ɻ�ȡ��ҳ������ͼƬ������
     * @param cssSelector
     * @param attrName
     * @return
     */
    public static ArrayList<String> getAttrs(Page page , String cssSelector, String attrName) {
        ArrayList<String> result = new ArrayList<String>();
        Elements eles = select(page ,cssSelector);
        for (Element ele : eles) {
            if (ele.hasAttr(attrName)) {
                result.add(ele.attr(attrName));
            }
        }
        return result;
    }
}
