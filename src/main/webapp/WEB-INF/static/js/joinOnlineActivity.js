/**
 * Created by Administrator on 2016/6/9.
 */
//上传图片并预览效果
$(document).on("change","#file",function(){
    $("#viewImg").remove();
    setImagePreview();
    if($("#photoForDisplay").hasClass("uploaded")){
        $("#photoForDisplay").removeClass("uploaded");
    }
})

function setImagePreview() {
    var docObj = document.getElementById("file");
    var viewer=document.createElement("img");
    viewer.setAttribute("id","viewImg");
    var photo=document.getElementById("photoForDisplay");
    photo.appendChild(viewer);
    var obj = document.getElementById("file");
//            var viewer = document.getElementById("viewImg");
    viewer.onload=function(){
        $height1=$(this).height();
        $height2=$(this).parent().height();
        $(this).css({"margin-top":($height2-$height1)/2+"px","margin-bottom":($height2-$height1)/2+"px"});
    }

    //var imgObjPreview = document.getElementById("preview");
    if (docObj.files && docObj.files[0]) {
        viewer.src = window.URL.createObjectURL(docObj.files[0]);
    } else {
        docObj.select();
        var imgSrc = document.selection.createRange().text;
        var localImagId = document.getElementById("photoForDisplay");
        localImagId.style.Width = "100%";
        localImagId.style.Height = "300px";
        try {
            localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)"; localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
        } catch (e) {
            alert("您上传的图片格式不正确，请重新选择!!");
            return false;
        }
        document.selection.empty();
    }
    return true;
}

$(document).ready(function(){
    $("form").submit(function(){
        var pattern= /^[0-9]*$/;
        if($("input[name='mobile']").val().length==0){
            $.alert("请填写手机号码");
            return false;
        }else if($("input[name='mobile']").val().length!==11||!pattern.test($("input[name='mobile']").val())){
            $.alert("请填写正确的手机号码");
            return false;
        }else if($("#photoForDisplay").find("img").length==0)
        {
            $.alert("请上传图片");
            return false;
        }else{
            return true;
        }
    })
})
