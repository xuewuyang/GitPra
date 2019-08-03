(function(w,d,u){
    var form = util.get('form');
    if(!form){
        return;
    }
    var title = form['productName'];
    var summary = form['productSummary'];
    var image = form['pictureAddress'];
    var detail = form['productContext'];
    var price = form['price'];
    var uploadInput = form['file'];
    //var isSubmiting = false;
    var imgpre = util.get('imgpre');
    var loading = new Loading();
    var imageUrl;
    var imageMode = "urlUpload";
    var  token=document.getElementsByTagName('meta')[1].content;
    var header=document.getElementsByTagName('meta')[2].content;
    
    var id=parseInt(util.getQueryString('id'));

    var page = {
        init:function(){
            var $ = function(id){
                return document.getElementById(id);
            };

            $('uploadType').onclick = function(e){
                e = window.event || e;
                o = e.srcElement || e.target;
                if(o.nodeName==="INPUT"){
                    var s,h;
                    o.value==='url'?(s='urlUpload',h='fileUpload'):(s='fileUpload',h='urlUpload');
                    imageMode = o.value==='url'?"urlUpload":"fileUpload";
                    image.classList.remove("z-err");
                    uploadInput.classList.remove("z-err");
                    $(s).style.display='block';
                    $(h).style.display='none';
                }
            };

            $('upload').addEventListener('click', function (){

                uploadInput.addEventListener('change', function() {
                    console.log(uploadInput.files) // File listing!
                });
                for (var i = 0, fileCount = uploadInput.files.length; i < fileCount; i++) {
                    console.log(uploadInput.files[i]);
                }
                var maxAllowedSize = 1000000;
                var file = uploadInput.files[0];

                if(uploadInput.files[0].size > maxAllowedSize) {
                    alert("超过文件上传大小限制");
                }else{
                    var form = new FormData();
                    form.append('file', file, file.name);
                    form.enctype = "multipart/form-data";

                    var xhr = new XMLHttpRequest();
                    xhr.open("post", "/network/upload", true);
                    //xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    //xhr.setRequestHeader("Content-Type", "multipart/form-data");
                    xhr.setRequestHeader(header, token);
                    xhr.onload = function () {
                        if (xhr.status === 200) {
                            alert("文件上传成功");
                            var o = xhr.responseText;
                            imageUrl = o ;
                            image.value = imageUrl;
                            imgpre.src = imageUrl;
                           
                        } else {
                            alert('An error occurred!');
                        }
                    };
                    xhr.send(form);
                }
            });
            form.addEventListener('submit',function(e){            	
                if(this.check()){
                    price.value = Number(price.value);
                    formDetail={
                    		'productName':title.value,
                    		'productSummary':summary.value,
                    		'pictureAddress':image.value,
                    		'productContext':detail.value,
                    		'price':parseFloat(price.value)                    		
                    } 
                    if(id!=null){
                    	formDetail['id']=id;
                    }
                    ajax({
                        data:JSON.stringify(formDetail),              
                        contentType:"application/json",
                        requestHeader:header,
                        requestCsrf:token,
                        url:'/network/pub',
                        success:function(result){ 
                        	location.href = '/network/publicSubmit';
                        },
                        error:function(message){
                        	  loading.result('发布失败');
                        }
                    });
                }
            }.bind(this),false);
            [title,summary,image,detail,price].forEach(function(item){
                item.addEventListener('input',function(e){
                    item.classList.remove('z-err');
                }.bind(this),false);
            }.bind(this));
            image.addEventListener('input',function(e){
                var value = image.value.trim();
                if(value != ''){
                    imgpre.src = value;
                }
            }.bind(this),false);
        },
        check:function(){
            var result = true;
            [
                [title,function(value){return value.length<2 || value.length>80}],
                [summary,function(value){return value.length<2 || value.length>140}],
                [image,function(value){return imageMode == "urlUpload" && value == ''}],
                [detail,function(value){return value.length<2 || value.length>1000}],
                [price,function(value){return value == '' || !Number(value)}]
            ].forEach(function(item){
                var value = item[0].value.trim();
                if(item[1](value)){
                    item[0].classList.add('z-err');
                    result = false;
                }
                item[0].value = value;
            });
            if(imageMode == "fileUpload" && !imageUrl){
                uploadInput.classList.add('z-err');
                result = false;
            }
            return result;
        }
    };
    page.init();
})(window,document);