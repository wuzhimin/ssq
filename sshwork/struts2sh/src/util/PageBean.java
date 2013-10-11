/*
 * @(#)PageControl.java 1.00 2004-9-22
 *
 * Copyright 2004 2004 . All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package util;

/**
 * PageControl, 分页控制, 可以判断总页数和是否有上下页.
 *
 * 2008-07-22 加入输出上下分页HTML代码功能
 *
 * @author beansoft
 * @version 1.1 2008-9-22
 */
public class PageBean {
    /** 每页显示记录数 */
    private int pageCount;
    /** 是否有上一页 */
    private boolean hasPrevPage;
    /** 记录总数 */
    private int recordCount;
    /** 是否有下一页 */
    private boolean hasNextPage;
    /**总页面数 */
    private int totalPage;
    /** 当前页码数 */
    private int currentPage;

    /**
     * 分页前的页面地址
     */
    private String pageUrl;

    /**
     * 输出分页 HTML 页面跳转代码, 分链接和静态文字两种.
     * 2008-07-22
     * @return HTML 代码
     */
    public String getPageJumpLinkHtml() {
	    if(StringUtil.isEmpty(pageUrl)) {
	    	return "";
		}
	    
	    // 检查是否有参数符号, 没有就加上一个?
	    if(pageUrl.indexOf('?') == -1) {
	    	pageUrl = pageUrl + '?';
	    }
	    
	    StringBuffer buff = new StringBuffer("<span id='pageText'>");
	    
	    // 上一页翻页标记
	    if(currentPage > 1) {
	    	buff.append("[ <a href='" + pageUrl + "&page=" + (currentPage - 1) + "' title='转到第 "
					+ (currentPage - 1) + " 页'>上一页</a> ] ");
	    } else {
	    	buff.append("[ 上一页 ] ");
	    }
	    
	    // 下一页翻页标记
	    if(currentPage < getTotalPage()) {
	    	buff.append("[ <a href='" + pageUrl + "&page=" + (currentPage + 1)+ "' title='转到第 "
					+ (currentPage + 1) + " 页'>下一页</a> ] ");
	    } else {
	    	buff.append("[ 下一页 ] ");
	    }
	    
	    buff.append("</span>");
	    
	    return buff.toString();
    }
    
    /**
     * 输出页码信息: 第${currentPage}页/共${totalPage}页
     * @return
     */
    public String getPageCountHtml() {
    	return "第" + currentPage + "页/共" + getTotalPage() + "页";
    }
    
    /**
     *  输出 JavaScript 跳转函数代码
     * @return
     */
    public String getJavaScriptJumpCode() {
	    if(StringUtil.isEmpty(pageUrl)) {
	    	return "";
		}
	    
	    // 检查是否有参数符号, 没有就加上一个?
	    if(pageUrl.indexOf("?") == -1) {
	    	pageUrl = pageUrl + '?';
	    }
	    
    	return "<script>" + 
    	"// 页面跳转函数\n" +
    	"// 参数: 包含页码的表单元素，例如输入框，下拉框等\n" +
    "function jumpPage(input) {\n" +
    "	// 页码相同就不做跳转\n" +
   " 	if(input.value == " + currentPage + ") {" +
    "		return;\n" +
    "	}" +
    "	var newUrl = '" + pageUrl + "&page=' + input.value;\n" +
    "	document.location = newUrl;\n" +
   " }\n" +
   " </script>";
    
    }
    
    /**
     * 输出页面跳转的选择框和输入框. 示例输出:
     * <pre>
转到
	  <!-- 输出 HTML SELECT 元素, 并选中当前页面编码 -->
      <select onchange='jumpPage(this);'>
      
      <c:forEach var="i" begin="1" end="${totalPage}">
        <option value="${i}"
		
		<c:if test="${currentPage == i}">
		selected
		</c:if>

	>第${i}页</option>
	  </c:forEach>
	  
      </select>
      输入页码：<input type="text" value="${currentPage}" id="jumpPageBox" size="3"> 
      <input type="button" value="跳转" onclick="jumpPage(document.getElementById('jumpPageBox'))">     
</pre> 
     * @return
     */
    public String getPageFormJumpHtml() {
    	String s = "转到\n" + 
    	"\t  <!-- 输出 HTML SELECT 元素, 并选中当前页面编码 -->\n" + 
    	"      <select onchange='jumpPage(this);'>\n" + 
    	"      \n";
    	
    	for(int i = 1; i <= getTotalPage(); i++ ) {
    		s +=  "<option value=" + i + "\n";
    		
    		if(currentPage == i) {
    			s+= " selected ";
    		}
    		
    		s += "\t>第" + i + "页</option>\n";
    	}

    	s+=
    	"      </select>\n" + 
    	"      输入页码：<input type=\"text\" value=\"" + currentPage + "\" id=\"jumpPageBox\" size=\"3\"> \n" + 
    	"      <input type=\"button\" value=\"跳转\" onclick=\"jumpPage(document.getElementById('jumpPageBox'))\">  ";
    	return s;
    }
    
    /**
     * 进行分页计算.
     */
    private void calculate() {
        if (getPageCount() == 0) {
            setPageCount(1);
        }

        totalPage = (int) Math.ceil(1.0 * getRecordCount() / getPageCount()); // 总页面数
        if (totalPage == 0)
            totalPage = 1;

        // Check current page range, 2006-08-03
        if(currentPage > totalPage) {
        	currentPage = totalPage;
        }
//        System.out.println("currentPage=" + currentPage);
//        System.out.println("maxPage=" + maxPage);
//        // Fixed logic error at 2004-09-25
        hasNextPage = currentPage < totalPage;
        hasPrevPage = currentPage > 1;
        return;
    }

    /**
     * @return Returns the 最大页面数.
     */
    public int getTotalPage() {
    	calculate();
        return totalPage;
    }

    /**
     * @param currentPage
     *            The 最大页面数 to set.
     */
    private void setTotalPage(int maxPage) {
        this.totalPage = maxPage;
    }

    /**
     * 是否有上一页数据
     */
    public boolean hasPrevPage() {
    	calculate();
        return hasPrevPage;
   }

    /**
     * 是否有下一页数据
     */
    public boolean hasNextPage() {
    	calculate();
        return hasNextPage;
    }

    // Test
    public static void main(String[] args) {
        PageBean pc = new PageBean();
        pc.setCurrentPage(2);
        pc.setPageCount(4);
        pc.setRecordCount(5);
        pc.setPageUrl("product/list.do");

        System.out.println("当前页 " + pc.getCurrentPage());
        System.out.println("有上一页 " + pc.hasPrevPage());
        System.out.println("有下一页 " + pc.hasNextPage());
        System.out.println("总页面数 " + pc.getTotalPage());
        
        System.out.println("分页 HTML 代码 " + pc.getPageJumpLinkHtml());
    }

    /**
     * @return Returns the 当前页码数.
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 设置当前页码, 从 1 开始.
	 * @param currentPage
     *            The 当前页码数 to set.
     */
    public void setCurrentPage(int currentPage) {
        if (currentPage <= 0) {
           currentPage = 1;
		}
        this.currentPage = currentPage;
    }

    /**
     * @return Returns the recordCount.
     */
    public int getRecordCount() {
        return recordCount;
    }

    /**
     * @param recordCount
     *            The recordCount to set.
     */
    public void setRecordCount(int property1) {
        this.recordCount = property1;
    }

    /**
     * @return Returns the 每页显示记录数.
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * @param pageCount
     *            The 每页显示记录数 to set.
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

	public String getPageUrl()
	{
		return pageUrl;
	}

	public void setPageUrl(String value)
	{
		pageUrl = value;
	}
}
