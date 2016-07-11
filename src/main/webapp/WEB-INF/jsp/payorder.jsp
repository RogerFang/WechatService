<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/7/10
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <title>订单</title>
</head>
<body>
${orderParam['appId']}<br>
${orderParam['timeStamp']}<br>
${orderParam['nonceStr']}<br>
${orderParam['package']}<br>
${orderParam['packageValue']}<br>
${orderParam['paySign']}<br>
${orderParam['agent']}<br>

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

        wx.ready(function() {
            wx.chooseWXPay({
                timestamp: 0, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
                nonceStr: '', // 支付签名随机串，不长于 32 位
                package: '', // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
                signType: '', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
                paySign: '', // 支付签名
                success: function (res) {
                    // 支付成功后的回调函数
                }
            });
        });
    });
</script>

</body>
</html>
