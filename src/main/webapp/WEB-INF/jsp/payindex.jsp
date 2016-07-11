<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/7/9
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <title></title>
</head>
<body>
<%--<input type="button" value="确认支付" name="ajaxLoadId" id="test"/>--%>
<br>
<br>
<button id="test">确认支付</button>
<br>
<br>
<br>
<a href="${ctx}/testpay/testorder">下订单</a>
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
                    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
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


        $("#test").click(function(){
            $.ajax({
                type:"GET",
                //<span style="font-family:微软雅黑;">ajax调用微信统一接口获取prepayId</span>
                url:"${ctx}/testpay/unifiedorder"
            }).done(function(data){
                var obj = eval(data);
                if(parseInt(obj.agent)<5){
                    alert("您的微信版本低于5.0无法使用微信支付");
                    return;
                }

//                wx.ready(function() {
                    wx.chooseWXPay({
                        timestamp: obj.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
                        nonceStr: obj.nonceStr, // 支付签名随机串，不长于 32 位
                        package: obj.packageValue, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
                        signType: obj.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
                        paySign: obj.paySign, // 支付签名
                        success: function (res) {
                            // 支付成功后的回调函数
                            if(res.errMsg == "chooseWXPay:ok" ) {
                                window.location.href=obj.sendUrl;
                            }else{
                                alert("fail");
                                window.location.href="http://roger.tunnel.qydev.com/WechatService/testpay/payindex";
                                //<span style="font-family:微软雅黑;">当失败后，继续跳转该支付页面让用户可以继续付款，贴别注意不能直接调转jsp，</span><span style="font-size:10.5pt">不然会报</span><span style="font-size:12.0pt"> system:access_denied。</span>
                            }
                        }
                    });
//                });

                /*WeixinJSBridge.invoke('getBrandWCPayRequest',{
                    "appId" : obj.appId,                  //公众号名称，由商户传入
                    "timeStamp":obj.timeStamp,          //时间戳，自 1970 年以来的秒数
                    "nonceStr" : obj.nonceStr,         //随机串
                    "package" : obj.packageValue,      //<span style="font-family:微软雅黑;">商品包信息</span>
                    "signType" : obj.signType,        //微信签名方式:
                    "paySign" : obj.paySign           //微信签名
                },function(res){
                    alert(res.err_msg);
                    if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                        window.location.href=obj.sendUrl;
                    }else{
                        alert("fail");
                        window.location.href="http://roger.tunnel.qydev.com/WechatService/testpay";
                        //<span style="font-family:微软雅黑;">当失败后，继续跳转该支付页面让用户可以继续付款，贴别注意不能直接调转jsp，</span><span style="font-size:10.5pt">不然会报</span><span style="font-size:12.0pt"> system:access_denied。</span>
                    }
                });*/

            });
        });
    });
</script>
</body>
</html>
