(function(w,d,u){
    var vNum=document.getElementsByClassName('v-num');
    var value=document.getElementsByClassName('value');
    var allPrice=document.getElementById('allPrice');
    var price=0;
    if(vNum){

        for(var i=0;i<vNum.length;i++){
            price+=parseFloat(vNum[i].innerHTML)*parseFloat(value[i].innerHTML)
        }
        allPrice.innerHTML=price;

    }

})(window,document);