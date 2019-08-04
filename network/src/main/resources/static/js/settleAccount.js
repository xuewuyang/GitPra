(function(w,d,u){
    var settleAccount = util.get('settleAccount');
    if(!settleAccount){
        return;
    }
    var $ = function(id){
        return document.getElementById(id);
    }
    var  token=document.getElementsByTagName('meta')[1].content;
    var header=document.getElementsByTagName('meta')[2].content;

/*    var str = "<tr>" +
        "<th>" + '内容名称'  + "</th>"+
        "<th>" + '数量' + "</th>" +
        "<th>" + '价格' + "</th>" +
        "</tr>";

    for(var i = 0; i < products.length; i++){
        str = str +
            "<tr>" +
            "<td>" + products[i].title  + "</td>"+
            "<td>" +
            "<span class=\"lessNum\">"+ "-" + "</span>" +
            "<span class=\"totalNum\" id=\"allNum\">" + products[i].num + "</span>" +
            "<span id=\"thisId\">" + products[i].id + "</span>" +
            "<span class=\"moreNum\">"+ "+" + "</span>" + "</td>" +
            "<td>" + products[i].price + "</td>" +
            "</tr>";
    }

    $("newTable").innerHTML = str;*/

    window.onload = function(){
        $('newTable').onclick = function(e){
            var e = arguments[0] || window.event;
            target = e.srcElement ? e.srcElement : e.target;
            if(target.nodeName == "SPAN" && target.className == "moreNum"){
                var num = target.parentElement.children[1].textContent;
                var id = target.parentElement.children[2].textContent;
                num ++;
                target.parentElement.children[1].textContent = num;
                util.modifyOne(products,id,num);
            }else if(target.nodeName == "SPAN" && target.className == "lessNum"){
                var num = target.parentElement.children[1].textContent;
                var id = target.parentElement.children[2].textContent;
                num --;
                if(num < 0){
                    alert("该商品数量为0");
                }else{
                    target.parentElement.children[1].textContent = num;
                    util.modifyOne(products,id,num);
                }
            }
            return false;
        };
    };

    var loading = new Loading();
    var layer = new Layer();
    $('Account').onclick = function(e){  	
    	var thisId=document.getElementsByClassName('thisId');   	
    	var countNodeList=document.getElementsByClassName('totalNum'); 
    	var newProducts=[];
    	for(var i=0;i<thisId.length;i++){
    		var count=parseInt(countNodeList[i].innerHTML);
        	var id=parseInt(thisId[i].innerHTML);
        	newProducts.push({'id':id,'count':count});
    	}
        
        console.log(newProducts);
        var ele = e.target;
        layer.reset({
            content:'确认购买吗？',
            onconfirm:function(){
                layer.hide();
                loading.show();
                
                var xhr = new XMLHttpRequest();
                var data = JSON.stringify(newProducts);
                xhr.onreadystatechange = function(){
                    if(xhr.readyState == 4){
                        var status = xhr.status;
                        if(status >= 200 && status < 300 || status == 304){
                            var json = JSON.parse(xhr.responseText);
                            if(json){
                                loading.result('购买成功',function(){
                                	location.href = '/network/account';});
                            }else{
                                alert(json.message);
                            }
                        }else{
                            loading.result(message||'购买失败');
                        }
                    }
                };
                xhr.open('post','/network/buy');
                xhr.setRequestHeader('Content-Type','application/json');
                xhr.setRequestHeader(header, token);
                xhr.send(data);
            }.bind(this)
        }).show();
        return;
    };
    $('back').onclick = function(){
      //  location.href = window.history.go(-1);//返回功能
    	location.href = '/network/';
    }
})(window,document);