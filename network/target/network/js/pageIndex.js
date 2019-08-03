(function(w,d,u){
    var plist = util.get('plist');
    if(!plist){
        return;
    }
    var  token=document.getElementsByTagName('meta')[1].content;
    var header=document.getElementsByTagName('meta')[2].content;
   /* var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");*/
    
    var layer = new Layer();
    var loading = new Loading();
    var page = {
        init:function(){
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