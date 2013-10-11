function getCookieVal(offset) {
  var endstr = document.cookie.indexOf(";", offset);
  if (endstr == -1) { endstr = document.cookie.length; }
  return unescape(document.cookie.substring(offset, endstr));
}

function getCookie (name) {
  var arg = name + "=";
  var alen = arg.length;
  var clen = document.cookie.length;
  var i = 0;
  while (i < clen) {
    var j = i + alen;
    if (document.cookie.substring(i,j) == arg) {
      return getCookieVal(j);
    }
    i = document.cookie.indexOf(" ", i) + 1;
    if (i == 0) break;
  }
  return null;
}

function deleteCookie (name, path, domain) {
  if(getCookie(name)){
    var cookie = name + "=";
    if(path)
      cookie+=("; path="+path);
    if(domain)
      cookie+=("; domain=" + domain);
    cookie+="; expires=Thu, 01-Jan-70 00:00:01 GMT";
    document.cookie=cookie;
  }
}

function setCookie (name, value, expires, path, domain, secure) {
  var cookie=name+"="+escape(value);
  if(expires)
    cookie+="; expires="+expires.toGMTString();
  if(path)
    cookie+="; path="+path;
  if(domain)
    cookie+="; domain="+domain;
  if(secure)
    cookie+="; secure";
  document.cookie=cookie;
}
