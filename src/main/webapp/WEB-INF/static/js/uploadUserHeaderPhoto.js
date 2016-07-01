/**
 * Created by Administrator on 2016/6/1.
 */
$("#file").change(function(){
    $("#clipArea").show();
});

$("#clipArea").photoClip({
    width: 150,
    height: 150,
    file: "#file",
    view: "#view",
    ok: "#clipBtn",
    loadStart: function() {
        console.log("照片读取中");
    },
    loadComplete: function() {
        console.log("照片读取完成");
    },
    clipFinish: function(dataURL) {
        $("#clipArea").hide();
        var pic=document.getElementById("view").style.backgroundImage;

        form = $("<form method='post' action='teacher/query'></form>");


        input = $("<input type='text'>").val(pic).attr('name','data');

        form.append(input);

        //....继续添加字段

        form.submit();
        /* alert("ok"); */

    }
});