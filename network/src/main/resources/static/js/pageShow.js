
var $ = function(id){
    return document.getElementById(id);
}

$('plusNum').onclick = function(e){
    e = window.event || e;
    o = e.srcElement || e.target;
    var num = $('allNum').textContent;
    if(num > 0){
        num --;
        $('allNum').innerHTML = num;
    }else{
        alert("您没有购买任何商品");
    }
};

$('addNum').onclick = function(e){
    e = window.event || e;
    o = e.srcElement || e.target;
    var num = $('allNum').textContent;
    num ++;
    $('allNum').innerHTML = num;
};

var loading = new Loading();
var layer = new Layer();

var  token=document.getElementsByTagName('meta')[1].content;
var header=document.getElementsByTagName('meta')[2].content;

$('add').onclick = function(e){
    var ele = e.target;
    var id = parseInt(ele && ele.dataset.id);
    var productName = ele && ele.dataset.title;
    var price = parseFloat(ele && ele.dataset.price);
    var num = parseInt($('allNum').innerHTML);
    var productDetail = {'productId':id,'price':price,'productName':productName,'count':num};
    e == window.event || e;
    layer.reset({
        content:'确认加入购物车吗？',
        onconfirm:function(){
            layer.hide();
            loading.show();
      //向后台发送Ajax
            ajax({
                data:JSON.stringify(productDetail),              
                contentType:"application/json",
                requestHeader:header,
                requestCsrf:token,
                url:'/network/addShoppingCart',
                success:function(result){
                    loading.hide();
                    loading.result('添加购物车成功');
                    var num=parseInt($("cartNum").innerHTML);
                    $("cartNum").innerHTML=num+1;                    
                },
                error:function(message){
                    loading.result(message||'登录失败');
                    isSubmiting = false;
                }
            });
          
        }.bind(this)
    }).show();
    return;
};




