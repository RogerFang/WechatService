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
    <script>
        /*function onBridgeReady(){
            WeixinJSBridge.invoke(
                    'getBrandWCPayRequest', {
                        "appId": "wx2421b1c4370ec43b",     //公众号名称，由商户传入
                    "timeStamp":" 1395712654",         //时间戳，自1970年以来的秒数
                    "nonceStr": "e61463f8efa94090b1f366cccfbbb444", //随机串
                    "package": "prepay_id=u802345jgfjsdfgsdg888",
                    "signType": "MD5",         //微信签名方式：
                    "paySign": "70EA570631E4BB79628FBCA90534C63FF7FADD89" //微信签名
        },
        function(res){
            if(res.err_msg == "get_brand_wcpay_request：ok" ) {}     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
        }
        );
        }
        if (typeof WeixinJSBridge == "undefined"){
            if( document.addEventListener ){
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            }else if (document.attachEvent){
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }
        }else {
            onBridgeReady();
        }*/
    </script>
</head>
<body>
<input type="button" value="确认支付" name="ajaxLoadId" id="test"/>

<script>
    $(function(){
        $("#test").click(function(){
            alert("click");
            $.ajax({
                type:"GET",
                //<span style="font-family:微软雅黑;">ajax调用微信统一接口获取prepayId</span>
                url:"${ctx}/testpay/unifiedorder"
            })
               /*     .done(function(data){
                var obj = eval(data);
                if(parseInt(obj.agent)<5){
                    alert("您的微信版本低于5.0无法使用微信支付");
                    return;
                }
                WeixinJSBridge.invoke('getBrandWCPayRequest',{
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
                        window.location.href="http://183.45.18.197:8016/wxweb/config/oauth!execute.action";
                        //<span style="font-family:微软雅黑;">当失败后，继续跳转该支付页面让用户可以继续付款，贴别注意不能直接调转jsp，</span><span style="font-size:10.5pt">不然会报</span><span style="font-size:12.0pt"> system:access_denied。</span>
                    }
                });

            });*/
        });
    });
</script>
</body>
</html>
