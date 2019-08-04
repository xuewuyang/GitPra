(function(w,d,u){
    var plist = util.get('plist');
    var type=util.getQueryString('type');
    if(type){
        var sel=document.getElementsByClassName("tab");
        var selnode=sel[0].getElementsByTagName("li");
       for(var i=0;i<selnode.length;i++){
           var className=selnode[i].getAttribute("class");
           if(className===''){
               selnode[i].setAttribute("class",'z-sel');
           }else{
               selnode[i].setAttribute("class",'');
           }
        }


    }
    var $ = function(id){
        return document.getElementById(id);
    };
    if(!plist){
        return;
    }
    var  token=document.getElementsByTagName('meta')[1].content;
    var header=document.getElementsByTagName('meta')[2].content;
    var layer = new Layer();
    var loading = new Loading();
    var clickFunction=function(){

        if($('cartEnter')){
          $('cartEnter').addEventListener('click', function (){
            location.href='/network/shoppingcart';
           }.bind(this),false);
        }
    };
    clickFunction();
    var page = {
        init:function(){
            console.log('mnid');
            plist.addEventListener('click',function(e){
                var ele = e.target;
                var delId = ele.dataset && ele.dataset.del;
                delId=parseInt(delId);
                if(delId){
                    this.ondel(delId);
                    return;
                }
            }.bind(this),false);
        },
        ondel:function(id){
            layer.reset({
                content:'确定要删除该内容吗？',
                onconfirm:function(){
                    layer.hide();
                    loading.show();
                    ajax({
                        url:'/network/delete',
                        data:JSON.stringify({"id":id}),
                        contentType:"application/json",
                        requestHeader:header,
                        requestCsrf:token,
                        success:function(json){
                            this.delItemNode(id);
                            loading.result('删除成功');
                            //删除div
                            
                        }.bind(this)
                    });
                }.bind(this)
            }).show();
        },
        delItemNode:function(id){
            var item = util.get('p-'+id);
            if(item && item.parentNode){
                item.parentNode.removeChild(item);
            }
        }

    };
    page.init();

   
})(window,document);