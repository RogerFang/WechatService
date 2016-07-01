/**
 * Created by Administrator on 2016/6/06.
 */
$(document).ready(function(){
    //时间选择设置
    $("#begintime").datetimePicker();
    $("#endtime").datetimePicker();

    //单选框设置
    //$("#type").select({
    //    title: "请选择活动类型",
    //    items: ["女学馆", "美人计", "粉妈帮", "粉红club"]
    //});

    $("#location").select({
        title:"请选择活动地点",
        items:["洪山区","江夏区","汉阳区","武昌区","蔡甸区","江汉区","江岸区","新洲区","硚口区","东西湖区","青山区","黄陂区","汉南区"]
    })


    //上传图片预览效果
    $(function () {
        // 允许上传的图片类型
        var allowTypes = ['image/jpg', 'image/jpeg', 'image/png', 'image/gif'];
        // 1024KB，也就是 1MB
        var maxSize = 1024 * 1024;
        // 图片最大宽度
        var maxWidth = 300;
        // 最大上传图片数量
        var maxCount = 5;
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

                //控制单次图片的数目
                if (files.length > maxCount) {
                    $.alert('最多只能上传' + maxCount + '张图片');
                    return;
                }

                //控制总共上传图片的数目
                if (files.length+$('.weui_uploader_file').length > maxCount) {
                    $.alert('最多只能上传' + maxCount + '张图片');
                    return;
                }

                if ($('.weui_uploader_file').length > maxCount) {
                    $.alert('最多只能上传' + maxCount + '张图片');
                    return;
                }

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

    //图片数量统计效果
    $(document).on("click",".weui_uploader_file span",function(){
        $(this).parent(".weui_uploader_file").remove();
        var num = $('.weui_uploader_file').length;
        $("#photoNum span").text(num);
    })

    $("form").submit(function(){
        var pattern= /^[0-9]*$/;
        if($("input[name='eventTitle']").val().length==0){
            $.alert("请输入活动主题");
            return false;
        }else if($("input[name='eventBegintime']").val().length==0){
            $.alert("请选择活动开始时间");
            return false;
        }else if($("input[name='eventEndtime']").val().length==0){
            $.alert("请输入活动结束时间");
            return false;
        }else if(compareTime($("input[name='eventBegintime']").val(),$("input[name='eventEndtime']").val())==true){
            $.alert("活动开始时间不能大于结束时间");
            return false;
        }else if($("input[name='limitNum']").val().length==0){
            $.alert("请填写活动人数");
            return false;
        }else if($("input[name='location']").val().length==0){
            $.alert("请选择活动地点");
            return false;
        }else if($("input[name='eventPlace']").val().length==0){
            $.alert("请填写活动具体地点");
            return false;
        }else if($("textarea[name='eventContent']").val().length==0){
            $.alert("请简要描述活动详情");
            return false;
        }else if($('.weui_uploader_file').length==0){
            $.alert("请上传至少一张活动图片");
            return false;
        }else{
            $.alert("活动信息提交成功,请等待审核");
            return true;
        }
    });

    //比较两个日期的大小
    function compareTime(startDate,endDate){
        var startDateTemp = startDate.split(" ");
        var endDateTemp = endDate.split(" ");
        var arrStartDate = startDateTemp[0].split("-");
        var arrEndDate = endDateTemp[0].split("-");
        var arrStartTime = startDateTemp[1].split(":");
        var arrEndTime = endDateTemp[1].split(":");
        var allStartDate = new Date(arrStartDate[0],arrStartDate[1],arrStartDate[2],arrStartTime[0],arrStartTime[1]);
        var allEndDate = new Date(arrEndDate[0],arrEndDate[1],arrEndDate[2],arrEndTime[0],arrEndTime[1]);
        if(allStartDate.getTime()>=allEndDate.getTime()){
            return true;
        }else{
            return false;
        }
    }

})
