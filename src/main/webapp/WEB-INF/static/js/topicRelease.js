/**
 * Created by Administrator on 2016/5/25.
 */
$(document).ready(function(){
    //话题选择框下拉效果
    //不能对禁用的输入框绑定事件
    $("#topicSelect div").click(function(){
        if($("#topicTypeSelect").is(":hidden")){
            $("#topicTypeSelect").slideDown();
        }else{
            $("#topicTypeSelect").slideUp();
        }
    });

    //话题选择效果
    $("#topicTypeSelect label").click(function(){
        var $selectText=$(this).find("div").find("p").text();
        $("#topicSelect div").text($selectText)
            .css({"color":"#333333"});
        $("#topicTypeSelect").slideUp();
        $("#topicClsId").val($(this).find("div").find("p").attr("title"));
    })

    //文本框字数统计效果
    //change事件是失去焦点后出发，这里不适用
    //textarea的值获取用val不用text
    $("#commentArea textarea").bind("keyup", function(){
        var $maxNum=5000;
        var $commentNum=$(this).val().length;
        $("#commentArea .weui_textarea_counter span").text($commentNum);
        if($commentNum>$maxNum){
            $("#commentArea .weui_textarea_counter span").css({"color":"#F43530"})
        }else{
            $("#commentArea .weui_textarea_counter span").css({"color":"#B2B2B2"})
        }
    })

    //话题发布事件
    $(document).on("click", "#post", function() {
        if($("#topicSelect div").text()=="选择话题类型"){
            $(".info.error p").text("请选择话题");
            $(".info.error").fadeIn("fast");
            setTimeout(function(){
                $(".info.error").fadeOut();
            },2000);
            return false;
        }else if($("#commentArea textarea").val().length==0){
            $(".info.error p").text("内容不能为空");
            $(".info.error").fadeIn("fast");
            setTimeout(function(){
                $(".info.error").fadeOut();
            },2000);
            return false;
        }else if($("#commentArea textarea").val().length>5000){
            $(".info.error p").text("字数超过限制");
            $(".info.error").fadeIn("fast");
            setTimeout(function(){
                $(".info.error").fadeOut();
            },2000);
            return false;
        }else{
            $(".info.success").fadeIn("fast");
            setTimeout(function(){
                $(".info.success").fadeOut();
                // $("#releaseForm").submit();
            },2000);
            return true;
        }
    });

    $(function () {
        // 允许上传的图片类型
        var allowTypes = ['image/jpg', 'image/jpeg', 'image/png', 'image/gif'];
        // 1024KB，也就是 1MB
        var maxSize = 1024 * 1024;
        // 图片最大宽度
        var maxWidth = 300;
        // 最大上传图片数量
        var maxCount = 9;
        $('.weui_uploader_input').on('change', function (event) {
            var files = event.target.files;

            // 如果没有选中文件，直接返回
            if (files.length === 0) {
                return;
            }

            for (var i = 0, len = files.length; i < len; i++) {
                var file = files[i];
                var reader = new FileReader();

                // 如果类型不在允许的类型范围内
                if (allowTypes.indexOf(file.type) === -1) {
                    $.weui.alert({text: '该类型不允许上传'});
                    continue;
                }

                if (file.size > maxSize) {
                    $.weui.alert({text: '图片太大，不允许上传'});
                    continue;
                }

//                if ($('.weui_uploader_file').length >= maxCount) {
//                    $.weui.alert({text: '最多只能上传' + maxCount + '张图片'});
//                    return;
//                }

                reader.onload = function (e) {
                    var img = new Image();
                    img.onload = function () {
                        // 不要超出最大宽度
                        var w = Math.min(maxWidth, img.width);
                        // 高度按比例计算
                        var h = img.height * (w / img.width);
                        var canvas = document.createElement('canvas');
                        var ctx = canvas.getContext('2d');
                        // 设置 canvas 的宽度和高度
                        canvas.width = w;
                        canvas.height = h;
                        ctx.drawImage(img, 0, 0, w, h);
                        var base64 = canvas.toDataURL('image/png');

                        // 插入到预览区
                        var $preview = $('<li class="weui_uploader_file weui_uploader_status" style="background-image:url(' + base64 + ')"><span></span><div class="weui_uploader_status_content">0%</div></li>');
                        //$('.weui_uploader_files').empty();
                        $('.weui_uploader_files').append($preview);
                        var num = $('.weui_uploader_file').length;
//                        $('.js_counter').text(num + '/' + maxCount);
                        $("#photoNum span").text(num);

                        // 然后假装在上传，可以post base64格式，也可以构造blob对象上传，也可以用微信JSSDK上传

                        var progress = 0;
                        function uploading() {
                            $preview.find('.weui_uploader_status_content').text(++progress + '%');
                            if (progress < 100) {
                                setTimeout(uploading, 30);
                            }
                            else {
                                // 如果是失败，塞一个失败图标
                                //$preview.find('.weui_uploader_status_content').html('<i class="weui_icon_warn"></i>');
                                $preview.removeClass('weui_uploader_status').find('.weui_uploader_status_content').remove();
                            }
                        }
                        setTimeout(uploading, 30);
                    };

                    img.src = e.target.result;
                };
                reader.readAsDataURL(file);
            }
        });
    });

    $(document).on("click",".weui_uploader_file span",function(){
        $(this).parent(".weui_uploader_file").remove();
        var num = $('.weui_uploader_file').length;
        $("#photoNum span").text(num);
    })
})