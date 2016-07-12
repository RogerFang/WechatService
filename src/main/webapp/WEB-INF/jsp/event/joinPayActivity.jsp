<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/6/16
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <%--<title>活动报名</title>--%>
    <link rel="stylesheet" href="${ctx}/css/joinPayActivity.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <%--<form>--%>
        <div id="header">
            <p class="title"><span>活动报名</span><a id="nextstep">下一步</a></p>
        </div>
        <div id="joinActivity">
            <p>为了方便活动时能及时与您联系请填写以下信息</p>
            <label for="phone">手机号码</label>
            <input type="number" id="phone" name="phone" placeholder="请输入手机号码">
        </div>
        <div id="payforActivity" style="display: none">
            <div>
                <p>联系人：<span>${username}</span></p>
                <p id="phoneNum">手机号码：<span></span></p>
                <p>报名费用：<span>${fee}</span>元/人</p>
                <p>应付金额：<span>${fee}元</span>，请选择支付方式</p>
            </div>
            <div>
                <p>微信支付</p>
                <p>推荐微信用户使用</p>
            </div>
            <div id="footer">
                <button class="weui_btn weui_btn_warn" id="payBtn">支付</button>
            </div>
        </div>
    <%--</form>--%>
</div>
<script type="text/javascript" src="${ctx}/js/joinPayActivity.js"></script>
<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
    $(function(){
        $.ajax({
            type: "post",
            url: '${ctx}/getWeChatConfig',
            dataType: "json",
            data:{'url':window.location.href},
            success:function(data){
                wx.config({
                    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                    appId: data.appId, // 必填，公众号的唯一标识
                    timestamp: data.timestamp, // 必填，生成签名的时间戳
                    nonceStr: data.nonceStr, // 必填，生成签名的随机串
                    signature: data.signature,// 必填，签名，见附录1
                    jsApiList: [
                        'chooseWXPay'
                    ]
                });
            }
        });


        // 支付的时候, 发起下订单的请求(注意: 页面内一定不要有form标签, 否则会重新刷新一下页面)
        $("#payBtn").click(function(){
            $.ajax({
                type:"POST",
                //<span style="font-family:微软雅黑;">ajax调用微信统一接口获取prepayId</span>
                url:"${ctx}/wcpay/orderForActivity",
                data:{"eventId": "${eventId}"}
            }).done(function(data){
                var obj = eval(data);
                if(parseInt(obj.agent)<5){
                    alert("您的微信版本低于5.0无法使用微信支付");
                    return;
                }
                wx.chooseWXPay({
                    timestamp: obj.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
                    nonceStr: obj.nonceStr, // 支付签名随机串，不长于 32 位
                    package: obj.packageValue, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
                    signType: obj.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
                    paySign: obj.paySign, // 支付签名
                    success: function (res) {
                        // 支付成功后的回调函数
                        if(res.errMsg == "chooseWXPay:ok" ) {
                            submitRecord();
                            $.alert("恭喜您,活动报名成功!");
//                            window.location.href=obj.sendUrl;
                        }else{
                            $.alert("对不起,您的支付失败!");
                            //window.location.href="http://roger.tunnel.qydev.com/WechatService/testpay/payindex";
                            //<span style="font-family:微软雅黑;">当失败后，继续跳转该支付页面让用户可以继续付款，贴别注意不能直接调转jsp，</span><span style="font-size:10.5pt">不然会报</span><span style="font-size:12.0pt"> system:access_denied。</span>
                        }
                    }
                });
            });
        });
    });

    function submitRecord() {
        var mobile = $("#phone").val();
        $.ajax({
            url:"${ctx}/eventinfo/joinPay/${eventId}",
            type:"POST",
            async:false,
            data:{"mobile": mobile}
        });
    }
</script>
</body>
</html>
