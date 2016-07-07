/*!
* Ajax Bootstrap Select
*
* Extends existing [Bootstrap Select] implementations by adding the ability to search via AJAX requests as you type. Originally for CROSCON.
*
* @version 1.3.7
* @author Adam Heim - https://github.com/truckingsim
* @link https://github.com/truckingsim/Ajax-Bootstrap-Select
* @copyright 2016 Adam Heim
* @license Released under the MIT license.
*
* Contributors:
*   Mark Carver - https://github.com/markcarver
*
* Last build: 2016-06-24 1:36:43 PM EDT
*/
!function(a,b){var c=function(c,d){var e,f,g=this;d=d||{},this.$element=a(c),this.options=a.extend(!0,{},a.fn.ajaxSelectPicker.defaults,d),this.LOG_ERROR=1,this.LOG_WARNING=2,this.LOG_INFO=3,this.LOG_DEBUG=4,this.lastRequest=!1,this.previousQuery="",this.query="",this.request=!1;var h=[{from:"ajaxResultsPreHook",to:"preprocessData"},{from:"ajaxSearchUrl",to:{ajax:{url:"{{{value}}}"}}},{from:"ajaxOptions",to:"ajax"},{from:"debug",to:function(b){var c={};c.log=Boolean(g.options[b.from])?g.LOG_DEBUG:0,g.options=a.extend(!0,{},g.options,c),delete g.options[b.from],g.log(g.LOG_WARNING,'Deprecated option "'+b.from+'". Update code to use:',c)}},{from:"mixWithCurrents",to:"preserveSelected"},{from:"placeHolderOption",to:{locale:{emptyTitle:"{{{value}}}"}}}];h.length&&a.map(h,function(b){if(g.options[b.from])if(a.isPlainObject(b.to))g.replaceValue(b.to,"{{{value}}}",g.options[b.from]),g.options=a.extend(!0,{},g.options,b.to),g.log(g.LOG_WARNING,'Deprecated option "'+b.from+'". Update code to use:',b.to),delete g.options[b.from];else if(a.isFunction(b.to))b.to.apply(g,[b]);else{var c={};c[b.to]=g.options[b.from],g.options=a.extend(!0,{},g.options,c),g.log(g.LOG_WARNING,'Deprecated option "'+b.from+'". Update code to use:',c),delete g.options[b.from]}});var i=this.$element.data();i.searchUrl&&(g.log(g.LOG_WARNING,'Deprecated attribute name: "data-search-url". Update markup to use: \' data-abs-ajax-url="'+i.searchUrl+"\" '"),this.options.ajax.url=i.searchUrl);var j=function(a,b){return b.toLowerCase()},k=function(a,b,c){var d=[].concat(a),e=d.length,f=c||{};if(e){var g=d.shift();f[g]=k(d,b,f[g])}return e?f:b},l=Object.keys(i).filter(/./.test.bind(new RegExp("^abs[A-Z]")));if(l.length){var m={},n=["locale"];for(e=0,f=l.length;f>e;e++){var o=l[e].replace(/^abs([A-Z])/,j).replace(/([A-Z])/g,"-$1").toLowerCase(),p=o.split("-");if(p[0]&&p.length>1&&-1!==n.indexOf(p[0])){for(var q=[p.shift()],r="",s=0;s<p.length;s++)r+=0===s?p[s]:p[s].charAt(0).toUpperCase()+p[s].slice(1);q.push(r),p=q}this.log(this.LOG_DEBUG,'Processing data attribute "data-abs-'+o+'":',i[l[e]]),k(p,i[l[e]],m)}this.options=a.extend(!0,{},this.options,m),this.log(this.LOG_DEBUG,"Merged in the data attribute options: ",m,this.options)}if(this.selectpicker=i.selectpicker,!this.selectpicker)return this.log(this.LOG_ERROR,"Cannot instantiate an AjaxBootstrapSelect instance without selectpicker first being initialized!"),null;if(!this.options.ajax.url)return this.log(this.LOG_ERROR,'Option "ajax.url" must be set! Options:',this.options),null;if(this.locale=a.extend(!0,{},a.fn.ajaxSelectPicker.locale),this.options.langCode=this.options.langCode||b.navigator.userLanguage||b.navigator.language||"en",!this.locale[this.options.langCode]){var t=this.options.langCode;this.options.langCode="en";var u=t.split("-");for(e=0,f=u.length;f>e;e++){var v=u.join("-");if(v.length&&this.locale[v]){this.options.langCode=v;break}u.pop()}this.log(this.LOG_WARNING,'Unknown langCode option: "'+t+'". Using the following langCode instead: "'+this.options.langCode+'".')}this.locale[this.options.langCode]=a.extend(!0,{},this.locale[this.options.langCode],this.options.locale),this.list=new b.AjaxBootstrapSelectList(this),this.list.refresh(),setTimeout(function(){g.init()},500)};c.prototype.init=function(){var c,d=this;this.options.preserveSelected&&this.selectpicker.$menu.off("click",".actions-btn").on("click",".actions-btn",function(b){d.selectpicker.options.liveSearch?d.selectpicker.$searchbox.focus():d.selectpicker.$button.focus(),b.preventDefault(),b.stopPropagation(),a(this).is(".bs-select-all")?(null===d.selectpicker.$lis&&(d.selectpicker.$lis=d.selectpicker.$menu.find("li")),d.$element.find("option:enabled").prop("selected",!0),a(d.selectpicker.$lis).not(".disabled").addClass("selected"),d.selectpicker.render()):(null===d.selectpicker.$lis&&(d.selectpicker.$lis=d.selectpicker.$menu.find("li")),d.$element.find("option:enabled").prop("selected",!1),a(d.selectpicker.$lis).not(".disabled").removeClass("selected"),d.selectpicker.render()),d.selectpicker.$element.change()}),this.selectpicker.$searchbox.attr("placeholder",this.t("searchPlaceholder")).off("input propertychange"),this.selectpicker.$searchbox.on(this.options.bindEvent,function(e){var f=d.selectpicker.$searchbox.val();if(d.log(d.LOG_DEBUG,'Bind event fired: "'+d.options.bindEvent+'", keyCode:',e.keyCode,e),d.options.cache||(d.options.ignoredKeys[13]="enter"),d.options.ignoredKeys[e.keyCode])return void d.log(d.LOG_DEBUG,"Key ignored.");if(clearTimeout(c),f.length||(d.options.clearOnEmpty&&d.list.destroy(),d.options.emptyRequest)){if(d.previousQuery=d.query,d.query=f,d.options.cache&&13!==e.keyCode){var g=d.list.cacheGet(d.query);if(g)return d.list.setStatus(g.length?"":d.t("statusNoResults")),d.list.replaceOptions(g),void d.log(d.LOG_INFO,"Rebuilt options from cached data.")}c=setTimeout(function(){d.lastRequest&&d.lastRequest.jqXHR&&a.isFunction(d.lastRequest.jqXHR.abort)&&d.lastRequest.jqXHR.abort(),d.request=new b.AjaxBootstrapSelectRequest(d),d.request.jqXHR.always(function(){d.lastRequest=d.request,d.request=!1})},d.options.requestDelay||300)}})},c.prototype.log=function(a,c){if(b.console&&this.options.log){if("number"!=typeof this.options.log)switch("string"==typeof this.options.log&&(this.options.log=this.options.log.toLowerCase()),this.options.log){case!0:case"debug":this.options.log=this.LOG_DEBUG;break;case"info":this.options.log=this.LOG_INFO;break;case"warn":case"warning":this.options.log=this.LOG_WARNING;break;default:case!1:case"error":this.options.log=this.LOG_ERROR}if(a<=this.options.log){var d=[].slice.apply(arguments,[2]);switch(a){case this.LOG_DEBUG:a="debug";break;case this.LOG_INFO:a="info";break;case this.LOG_WARNING:a="warn";break;default:case this.LOG_ERROR:a="error"}var e="["+a.toUpperCase()+"] AjaxBootstrapSelect:";"string"==typeof c?d.unshift(e+" "+c):(d.unshift(c),d.unshift(e)),b.console[a].apply(b.console,d)}}},c.prototype.replaceValue=function(b,c,d,e){var f=this;e=a.extend({recursive:!0,depth:!1,limit:!1},e),a.each(b,function(g,h){return e.limit!==!1&&"number"==typeof e.limit&&e.limit<=0?!1:void(a.isArray(b[g])||a.isPlainObject(b[g])?(e.recursive&&e.depth===!1||e.recursive&&"number"==typeof e.depth&&e.depth>0)&&f.replaceValue(b[g],c,d,e):h===c&&(e.limit!==!1&&"number"==typeof e.limit&&e.limit--,b[g]=d))})},c.prototype.t=function(a,b){return b=b||this.options.langCode,this.locale[b]&&this.locale[b].hasOwnProperty(a)?this.locale[b][a]:(this.log(this.LOG_WARNING,"Unknown translation key:",a),a)},b.AjaxBootstrapSelect=b.AjaxBootstrapSelect||c;var d=function(b){var c=this;this.$status=a(b.options.templates.status).hide().appendTo(b.selectpicker.$menu);var d=b.t("statusInitialized");d&&d.length&&this.setStatus(d),this.cache={},this.plugin=b,this.selected=[],this.title=null,this.selectedTextFormat=b.selectpicker.options.selectedTextFormat;var e=[];b.$element.find("option").each(function(){var c=a(this),d=c.attr("value");e.push({value:d,text:c.text(),"class":c.attr("class")||"",data:c.data()||{},preserved:b.options.preserveSelected,selected:!!c.attr("selected")})}),this.cacheSet("",e),b.options.preserveSelected&&(c.selected=e,b.$element.on("change.abs.preserveSelected",function(d){var e=b.$element.find(":selected");c.selected=[],b.selectpicker.multiple||(e=e.last()),e.each(function(){var b=a(this),d=b.attr("value");c.selected.push({value:d,text:b.text(),"class":b.attr("class")||"",data:b.data()||{},preserved:!0,selected:!0})}),c.replaceOptions(c.cacheGet(c.plugin.query))}))};d.prototype.build=function(b){var c,d,e=b.length,f=a("<select/>"),g=a("<optgroup/>").attr("label",this.plugin.t("currentlySelected"));for(this.plugin.log(this.plugin.LOG_DEBUG,"Building the select list options from data:",b),d=0;e>d;d++){var h=b[d],i=a("<option/>").appendTo(h.preserved?g:f);if(h.hasOwnProperty("divider"))i.attr("data-divider","true");else{i.val(h.value).text(h.text),h["class"].length&&i.attr("class",h["class"]),h.disabled&&i.attr("disabled",!0),h.selected&&!this.plugin.selectpicker.multiple&&f.find(":selected").prop("selected",!1),h.selected&&i.attr("selected",!0);for(c in h.data)h.data.hasOwnProperty(c)&&i.attr("data-"+c,h.data[c])}}g.find("option").length&&g["before"===this.plugin.options.preserveSelectedPosition?"prependTo":"appendTo"](f);var j=f.html();return this.plugin.log(this.plugin.LOG_DEBUG,j),j},d.prototype.cacheGet=function(a,b){var c=this.cache[a]||b;return this.plugin.log(this.LOG_DEBUG,"Retrieving cache:",a,c),c},d.prototype.cacheSet=function(a,b){this.cache[a]=b,this.plugin.log(this.LOG_DEBUG,"Saving to cache:",a,b)},d.prototype.destroy=function(){this.replaceOptions(),this.plugin.list.setStatus(),this.plugin.log(this.plugin.LOG_DEBUG,"Destroyed select list.")},d.prototype.refresh=function(a){this.plugin.selectpicker.$menu.css("minHeight",0),this.plugin.selectpicker.$menu.find("> .inner").css("minHeight",0);var b=this.plugin.t("emptyTitle");!this.plugin.$element.find("option").length&&b&&b.length?this.setTitle(b):this.title&&this.restoreTitle(),this.plugin.selectpicker.refresh(),this.plugin.selectpicker.findLis(),a&&(this.plugin.log(this.plugin.LOG_DEBUG,"Triggering Change"),this.plugin.$element.trigger("change.$")),this.plugin.log(this.plugin.LOG_DEBUG,"Refreshed select list.")},d.prototype.replaceOptions=function(a){var b,c,d,e="",f=[],g=[],h=[];if(a=a||[],this.selected&&this.selected.length){for(this.plugin.log(this.plugin.LOG_INFO,"Processing preserved selections:",this.selected),g=[].concat(this.selected,a),c=g.length,b=0;c>b;b++)d=g[b],d.hasOwnProperty("value")&&-1===h.indexOf(d.value+"")?(h.push(d.value+""),f.push(d)):this.plugin.log(this.plugin.LOG_DEBUG,"Duplicate item found, ignoring.");a=f}a.length&&(e=this.plugin.list.build(a)),this.plugin.$element.html(e),this.refresh(),this.plugin.log(this.plugin.LOG_DEBUG,"Replaced options with data:",a)},d.prototype.restore=function(){var a=this.plugin.list.cacheGet(this.plugin.previousQuery);return a&&this.plugin.list.replaceOptions(a)&&this.plugin.log(this.plugin.LOG_DEBUG,"Restored select list to the previous query: ",this.plugin.previousQuery),this.plugin.log(this.plugin.LOG_DEBUG,"Unable to restore select list to the previous query:",this.plugin.previousQuery),!1},d.prototype.restoreTitle=function(){this.plugin.request||(this.plugin.selectpicker.options.selectedTextFormat=this.selectedTextFormat,this.title?this.plugin.$element.attr("title",this.title):this.plugin.$element.removeAttr("title"),this.title=null)},d.prototype.setTitle=function(a){this.plugin.request||(this.title=this.plugin.$element.attr("title"),this.plugin.selectpicker.options.selectedTextFormat="static",this.plugin.$element.attr("title",a))},d.prototype.setStatus=function(a){a=a||"",a.length?this.$status.html(a).show():this.$status.html("").hide()},b.AjaxBootstrapSelectList=b.AjaxBootstrapSelectList||d;var e=function(b){var c,d=this,e=function(a){return function(){d.plugin.log(d.plugin.LOG_INFO,"Invoking AjaxBootstrapSelectRequest."+a+" callback:",arguments),d[a].apply(d,arguments),d.callbacks[a]&&(d.plugin.log(d.plugin.LOG_INFO,"Invoking ajax."+a+" callback:",arguments),d.callbacks[a].apply(d,arguments))}},f=["beforeSend","success","error","complete"],g=f.length;for(this.plugin=b,this.options=a.extend(!0,{},b.options.ajax),this.callbacks={},c=0;g>c;c++){var h=f[c];this.options[h]&&a.isFunction(this.options[h])&&(this.callbacks[h]=this.options[h]),this.options[h]=e(h)}this.options.data&&a.isFunction(this.options.data)&&(this.options.data=this.options.data.apply(this)||{q:"{{{q}}}"}),this.plugin.replaceValue(this.options.data,"{{{q}}}",this.plugin.query),this.jqXHR=a.ajax(this.options)};e.prototype.beforeSend=function(a){this.plugin.list.destroy(),this.plugin.list.setStatus(this.plugin.t("statusSearching"))},e.prototype.complete=function(a,b){if("abort"!==b){var c=this.plugin.list.cacheGet(this.plugin.query);if(c){if(!c.length)return this.plugin.list.destroy(),this.plugin.list.setStatus(this.plugin.t("statusNoResults")),void this.plugin.log(this.plugin.LOG_INFO,"No results were returned.");this.plugin.list.setStatus()}this.plugin.list.refresh(!0)}},e.prototype.error=function(a,b,c){"abort"!==b&&(this.plugin.list.cacheSet(this.plugin.query),this.plugin.options.clearOnError&&this.plugin.list.destroy(),this.plugin.list.setStatus(this.plugin.t("errorText")),this.plugin.options.restoreOnError&&(this.plugin.list.restore(),this.plugin.list.setStatus()))},e.prototype.process=function(b){var c,d,e,f,g,h,i=[],j=[];if(this.plugin.log(this.plugin.LOG_INFO,"Processing raw data for:",this.plugin.query,b),g=b,a.isFunction(this.plugin.options.preprocessData)&&(this.plugin.log(this.plugin.LOG_DEBUG,"Invoking preprocessData callback:",this.plugin.options.processData),e=this.plugin.options.preprocessData.apply(this,[g]),"undefined"!=typeof e&&null!==e&&e!==!1&&(g=e)),!a.isArray(g))return this.plugin.log(this.plugin.LOG_ERROR,'The data returned is not an Array. Use the "preprocessData" callback option to parse the results and construct a proper array for this plugin.',g),!1;for(d=g.length,c=0;d>c;c++)f=g[c],this.plugin.log(this.plugin.LOG_DEBUG,"Processing item:",f),a.isPlainObject(f)&&(f.hasOwnProperty("divider")||f.hasOwnProperty("data")&&a.isPlainObject(f.data)&&f.data.divider?(this.plugin.log(this.plugin.LOG_DEBUG,"Item is a divider, ignoring provided data."),i.push({divider:!0})):f.hasOwnProperty("value")?-1===j.indexOf(f.value+"")?(j.push(f.value+""),f=a.extend({text:f.value,"class":"",data:{},disabled:!1,selected:!1},f),i.push(f)):this.plugin.log(this.plugin.LOG_DEBUG,"Duplicate item found, ignoring."):this.plugin.log(this.plugin.LOG_DEBUG,'Data item must have a "value" property, skipping.'));if(h=[].concat(i),a.isFunction(this.plugin.options.processData)&&(this.plugin.log(this.plugin.LOG_DEBUG,"Invoking processData callback:",this.plugin.options.processData),e=this.plugin.options.processData.apply(this,[h]),"undefined"!=typeof e&&null!==e&&e!==!1)){if(!a.isArray(e))return this.plugin.log(this.plugin.LOG_ERROR,"The processData callback did not return an array.",e),!1;h=e}return this.plugin.list.cacheSet(this.plugin.query,h),this.plugin.log(this.plugin.LOG_INFO,"Processed data:",h),h},e.prototype.success=function(b,c,d){if(!a.isArray(b)&&!a.isPlainObject(b))return this.plugin.log(this.plugin.LOG_ERROR,"Request did not return a JSON Array or Object.",b),void this.plugin.list.destroy();var e=this.process(b);this.plugin.list.replaceOptions(e)},b.AjaxBootstrapSelectRequest=b.AjaxBootstrapSelectRequest||e,a.fn.ajaxSelectPicker=function(c){return this.each(function(){a(this).data("AjaxBootstrapSelect")||a(this).data("AjaxBootstrapSelect",new b.AjaxBootstrapSelect(this,c))})},a.fn.ajaxSelectPicker.locale={},a.fn.ajaxSelectPicker.defaults={ajax:{url:null,type:"POST",dataType:"json",data:{q:"{{{q}}}"}},bindEvent:"keyup",cache:!0,clearOnEmpty:!0,clearOnError:!0,emptyRequest:!1,ignoredKeys:{9:"tab",16:"shift",17:"ctrl",18:"alt",27:"esc",37:"left",39:"right",38:"up",40:"down",91:"meta"},langCode:null,locale:null,log:"error",preprocessData:function(){},preserveSelected:!0,preserveSelectedPosition:"after",processData:function(){},requestDelay:300,restoreOnError:!1,templates:{status:'<div class="status"></div>'}},/*!
* English translation for the "en-US" and "en" language codes.
* Mark Carver <mark.carver@me.com>
*/
a.fn.ajaxSelectPicker.locale["en-US"]={currentlySelected:"Currently Selected",emptyTitle:"Select and begin typing",errorText:"Unable to retrieve results",searchPlaceholder:"Search...",statusInitialized:"Start typing a search query",statusNoResults:"No Results",statusSearching:"Searching..."},a.fn.ajaxSelectPicker.locale.en=a.fn.ajaxSelectPicker.locale["en-US"]}(jQuery,window);  
