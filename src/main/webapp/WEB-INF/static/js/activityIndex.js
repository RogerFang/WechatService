/**
 * Created by Administrator on 2016/6/3.
 */
$(document).ready(function(){
    // $("img").css({"max-height":$(".activityInfo").height()});

    if($("#search_input").val().length!==0){
        $("#search_bar").addClass(" weui_search_focusing");
    }


    //ѡ�������ʾЧ��
    $("#category>div").click(function(){
        var $index=$(this).index();
        if($(this).siblings().find("span").hasClass("selected")){
            $(this).siblings().find("span").removeClass("selected");
            $("#activitySelect>div").css({"display":"none"});
            $("#activitySelect").css({"display":"none"});
        }
        if($("#activitySelect").is(":hidden")){
            $(this).find("span").addClass("selected");
            $("#activitySelect>div").eq($index).show();
            $("#activitySelect").slideDown();
        }else{
            $(this).find("span").removeClass("selected");
            $("#activitySelect>div").eq($index).hide();
            $("#activitySelect").css({"display":"none"});
        };
    });

    //ѡ���ı���ʾЧ��
    $("#activitySelect label").click(function(){
        $(this).find("p").addClass("selected").parents("label").siblings().find("p").removeClass("selected");
        var $text=$(this).find("p").text();
        var $val=$(this).find("input").val();
        var $index2=$(this).parents(".weui_cells_radio").index();
        $(".weui-col-25").eq($index2).find(".type").text($text);
        //����ѡ����д���
        switch($index2){
            case 0:
                $("input[name='eventCls']").val($val);
                break;
            case 1:
                $("input[name='eventArea']").val($val);
                break;
            case 2:
                $("input[name='eventSort']").val($val);
                break;
            case 3:
                $("input[name='eventPay']").val($val);
                break;
        }
        
        $("#conditionForm button").trigger("click");
    });

    //��������ʾЧ��
    $("#search").click(function(){
        if($(".bd").is(":hidden")){
            $(".bd").show();
            $("#category").css({"top":"86px"});
            $("#activityList").css({"margin-top":"124px"});
        }else{
            $(".bd").hide();
            $("#category").css({"top":"42px"});
            $("#activityList").css({"margin-top":"80px"});
        }
    });

    // //��ֱ������ʾ
    // $height=$(window).width()*0.2;
    // $("#content #activityList li").height($height);
    // $marginTop=($("#content #activityList li").height()-$("#content #activityList li a").height())/2;
    // $("#content #activityList li a").css({
    //     "margin-top":$marginTop
    // })

    var loading=false;
    $(window).scroll(function(){
        if ($(this).scrollTop() + this.innerHeight >= $(document).height() && $(this).scrollTop() > 0){
            if(loading) return;
            loading = true;
            setTimeout(function() {
                var $page = $("input[name='page']");
                var $pageNumber = parseInt($page.val())+1;
                var $eventCls = $("input[name='eventCls']").val();
                var $eventArea = $("input[name='eventArea']").val();
                var $eventSort = $("input[name='eventSort']").val();
                var $eventPay = $("input[name='eventPay']").val();
                $.ajax({
                    type: "POST",
                    url: "loadList",
                    data: {"page":$pageNumber, "eventCls":$eventCls, "eventArea":$eventArea, "eventSort":$eventSort, "eventPay":$eventPay},
                    dataType: "json",
                    success: function (data) {
                        if (data.length > 0){
                            $.each(data, function (i, ei) {
                                var basepath = GetUrlRelativePath();
                                var startEnd = "";
                                var isPay = "";

                                if (ei.beginIntervalDays > 0){
                                    startEnd = "����<span>"+ei.beginIntervalDays+"</span>�쿪ʼ";
                                }
                                if (ei.endIntervalDays > 0){
                                    startEnd = "����<span>"+ei.endIntervalDays+"</span>�����";
                                }
                                if (ei.text2 != 0){
                                    isPay = "�շ�"
                                }else{
                                    isPay = "���";
                                }

                                var eventinfo =
                                    '<li>' +
                                    '<a href="detail/'+ei.id+'" style="margin-top: -4.5px;">' +
                                    '<div class="activityTime">' +
                                    '<p>' + startEnd + '</p>' +
                                    '<p>'+ei.beginMonth+'��,'+ei.beginDayOfWeek+'</p>' +
                                    '<p>'+ei.beginDayOfMonth+'</p>' +
                                    '</div>' +
                                    '<div class="activityImage">' +
                                    '<img src="http://herpink.cn:8080/pinknetpic/'+ei.descPic+'" style="display: none" onload="this.style.display=\'\'">' +
                                    '<img src="'+basepath+'/'+ei.descPic+'" style="display: none" onload="this.style.display=\'\'">' +
                                    '</div>' +
                                    '<div class="activityInfo">' +
                                    '<p>'+ei.eventTitle+'</p>' +
                                    '<p>����ʱ�䣺<span>'+ei.formatEndTime+'</span></p>' +
                                    '<p>�����ˣ�<span>'+ei.eventCreator.userName+'</span></p>' +
                                    '<p>' +
                                    '<span>'+ei.eventMemberCount+'</span>���Ѳμ�' +
                                    '<span>' +
                                    isPay +
                                    '</span>' +
                                    '</p>' +
                                    '</div>' +
                                    '</a>' +
                                    '</li>';
                                $("#activityList").append(eventinfo);
                            });

                            $page.val($pageNumber);
                        }
                    }
                });
                loading = false;
            }, 100);   //ģ���ӳ�
        }
    })
})
