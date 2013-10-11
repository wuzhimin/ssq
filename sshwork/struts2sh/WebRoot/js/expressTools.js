//contextPath is declared and initialized to the context path (/ecexpress) in main.jsp

var searchType = 1;
/*
function xToolsSwitch() {
    switchImage('img_express_tool',url_expand_img,url_collapse_img);
    MM_showHideLayers('xToolsContent','','','xToolsBg','','');
    tmd_SwitchDiv('content',10,200);
}
*/

function xToolsSwitch() {
    switchImage('img_express_tool',url_expand_img,url_collapse_img);
    MM_showHideLayers('xToolsContent','','');
    tmd_SwitchDiv('content',10,200);
}
function xToolsClick() {
    xToolsSwitch();
}

function productSearchSwitch() {
  expendCollapseLayers('productSearch','','');
  switchImage('img_productSearch',url_expand_img,url_collapse_img);
}

function productSearchClick() {
  productSearchSwitch();
  setCookie("c_productSearch", isLayerExpended('productSearch'), null, contextPath);
}

function newProductSearchSwitch(){
  expendCollapseLayers('newProductSearch','','');
  expendCollapseLayers('new_part_search_filter','','');
  switchImage('img_newProductSearch',url_expand_img,url_collapse_img);
}
function newProductSearchClick() {
   newProductSearchSwitch();
   setCookie("c_newProductSearch", isLayerExpended('newProductSearch'), null, contextPath);
}
function productPnASwitch() {
  expendCollapseLayers('productPnA','','');
  switchImage('img_productPnA',url_expand_img,url_collapse_img);
}

function productPnAClick() {
  productPnASwitch();
  setCookie("c_productPnA", isLayerExpended('productPnA'), null, '');
}

function orderSearchSwitch() {
  expendCollapseLayers('orderSearch','','');
  switchImage('img_orderSearch',url_expand_img,url_collapse_img);
}

function orderSearchClick() {
  orderSearchSwitch();
  setCookie("c_orderSearch", isLayerExpended('orderSearch'), null, contextPath);
}

function shopCartSwitch() {
  expendCollapseLayers('shopCart','','');
  switchImage('img_shopCart',url_expand_img,url_collapse_img);
}

function shopCartClick() {
  shopCartSwitch();
  setCookie("c_shopCart", isLayerExpended('shopCart'), null, contextPath);
}

function favoriteProductsSwitch() {
  expendCollapseLayers('favoriteProducts','','');
  switchImage('img_favoriteProducts',url_expand_img,url_collapse_img);
}

function favoriteProductsClick() {
  favoriteProductsSwitch();
  setCookie("c_favoriteProducts", isLayerExpended('favoriteProducts'), null, contextPath);
}

function favoriteSwitch(id){
  var imgName = 'img_favorite_'+id;
  var divName = 'favorite_'+id;
  expendCollapseLayers(divName,'','');
  switchImage(imgName,url_expand_img,url_collapse_img);
}

function favoriteClick(id) {
  var imgName = 'img_favorite_'+id;
  var divName = 'favorite_'+id;
  favoriteSwitch(id);
}

function modelsKeywordSwitch() {
  expendCollapseLayers('modelsKeyword','','');
  switchImage('img_modelsKeyword',url_expand_img,url_collapse_img);
}

function modelsKeywordClick() {
  modelsKeywordSwitch();
  setCookie("c_modelsKeyword", isLayerExpended('modelsKeyword'), null, contextPath);
}

function deleteXToolsCookies(){
  deleteCookie('c_xTools',contextPath);
  deleteCookie('c_productSearch',contextPath);
  deleteCookie('c_productPnA',contextPath);
  deleteCookie('c_orderSearch',contextPath);
  deleteCookie('c_shopCart',contextPath);
  deleteCookie('c_favoriteProducts',contextPath);
  deleteCookie('c_expendFavorite',contextPath);
  deleteCookie('c_modelsKeyword',contextPath);
}

function setSearchType(v) {
    searchType = v;
}

function isDigit(obj) {
    slen=obj.length;
    for (var position=0; position<slen; position++){
        cc = obj.charAt(position);
        if (cc <"0" || cc >"9")	{
            return false;
        }
    }
    return true;
}

function SubmitPartSearch(keyword)
{
//Follow lines added "docment." by Boris Wei(2003-10-26), for compatible with netscape brower!
	if(keyword)
		document.express_part_search.criteriaContent.value = keyword;
	var chars = document.express_part_search.criteriaContent.value;

    if (chars.length < 2 && chars.length > 0)
    {
        alert("Please ensure keyword size > 1");
        return false;
    }

    var keywordCount = getKeywordCount(chars);


    if (keywordCount > 48 )
    {
        alert("Too many criterias, please ensure it less than 48 ");
        return false;
    }

	if (keywordCount > 0)
    {
        return true;
    }
    else
    {
        alert("Please input search criteria");
        return false;
    }
}

function SubmitSwitchPartSearch(keyword)
{
	if(keyword)
		document.express_product_search.criteriaContent.value = keyword;
	var chars = document.express_product_search.criteriaContent.value;

    if (chars.length < 2 && chars.length > 0)
    {
        alert("Please ensure keyword size > 1");
        return false;
    }

    var keywordCount = getKeywordCount(chars);


    if (keywordCount > 48 )
    {
        alert("Too many criterias, please ensure it less than 48 ");
        return false;
    }

	if (keywordCount > 0)
    {
        return true;
    }
    else
    {
        alert("Please input search criteria");
        return false;
    }
}

function SubmitLicenseSearch()
{
//Follow lines added "docment." by Boris Wei(2003-10-26), for compatible with netscape brower!
    if (document.license_search.search_item.value != "")
    {
        if (document.license_search.search_item.value.length > 1)
            document.license_search.submit();
        else
        {
            alert("Please ensure keyword size > 1");
            return false;
        }
        return true;
    }
    else
    {
        alert("Please input license search criteria");
        return false;
    }
}

function SubmitPASearch(keyword)
{
//Follow lines added "docment." by Boris Wei(2003-10-26), for compatible with netscape brower!
	if(keyword)
		document.express_pa_search.criteriaContent.value = keyword;
	var chars = document.express_pa_search.criteriaContent.value;

    if (chars.length < 2 && chars.length > 0)
    {
        alert("Please ensure keyword size > 1");
        return false;
    }

    var keywordCount = getKeywordCount(chars);


    if (keywordCount > 48 )
    {
        alert("Too many criterias, please ensure it less than 48 ");
        return false;
    }

	if (keywordCount > 0)
    {
        document.express_pa_search.submit();
        return true;
    }
    else
    {
        alert("Please input search criteria");
        return false;
    }
}

function SubmitModelsKeywordSearch(keyword)
{
	if(keyword)
		document.express_modelsKeyword_search.supplyModelKeyword.value = keyword;
    var chars = document.express_modelsKeyword_search.supplyModelKeyword.value;
    var keywordCount = getKeywordCount(chars);

    if (keywordCount > 0)
    {
        document.express_modelsKeyword_search.submit();
        //modelsKeywordSearch(chars);
        return true;
    }
    else
    {
        alert("Please input search criteria");
        return false;
    }
}
function SubmitProductSearch(keyword)
{
	if(keyword)
		document.express_product_search.keyword.value = keyword;
	  var chars = document.express_product_search.keyword.value;

    if ( chars.length < 2 && chars.length > 0)
    {
        alert("Please ensure keyword size > 1");
        return false;
    }

    var keywordCount = getKeywordCount(chars);

    if (keywordCount > 48 )
    {
        alert("Too many criterias, please ensure it less than 48 ");
        return false;
    }

	if (keywordCount > 0)
    {
        return true;
    }
    else
    {
        alert("Please input search criteria");
        return false;
    }
}

