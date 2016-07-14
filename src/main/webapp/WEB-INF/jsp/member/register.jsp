<%--
  Created by IntelliJ IDEA.
  User: Roger
  Date: 2016/5/15
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <%--<title>会员注册</title>--%>
    <link rel="stylesheet" href="${ctx}/css/register.css" type="text/css">
</head>
<body>
<div id="wrapper">
    <form action="${ctx}/member/register" method="post">
        <div id="ageSelect">
            <div id="introduce">
                <p>欢迎注册herpink</p>
                <p style="margin-bottom: 10px">可信赖的纯女性社交平台</p>
            </div>
            <div id="ageSelectItem" style="display: none">
                <label>请先选择您的年龄:</label>
                <div class="radioclass">
                        <span class="on">
                            <input type="radio" name="userAgeDesc" id="age1" value="青葱年华 18~23" checked="checked"/>
                        </span>
                    <label for="age1">青葱年华 18~23</label>
                </div>
                <div class="radioclass">
                        <span>
                            <input type="radio" name="userAgeDesc" id="age2" value="花样年华 24~29"/>
                        </span>
                    <label for="age2">花样年华 24~29</label>
                </div>
                <div class="radioclass">
                        <span>
                            <input type="radio" name="userAgeDesc" id="age3" value="桃李年华 30~35"/>
                        </span>
                    <label for="age3">桃李年华 30~35</label>
                </div>
                <div class="radioclass">
                        <span>
                            <input type="radio" name="userAgeDesc" id="age4" value="蕙质兰心 36~40"/>
                        </span>
                    <label for="age4">蕙质兰心 36~40</label>
                </div>
                <div class="radioclass">
                        <span>
                            <input type="radio" name="userAgeDesc" id="age5" value="风华正茂 41~45"/>
                        </span>
                    <label for="age5">风华正茂 41~45</label>
                </div>
                <div class="radioclass">
                        <span>
                            <input type="radio" name="userAgeDesc" id="age6" value="风韵犹存 45以上"/>
                        </span>
                    <label for="age6">风韵犹存 45以上</label>
                </div>
                <a href="#">下一步</a>
            </div>
        </div>

        <div id="register" style="display:none;">
            <div id="registerHeader">
                <img src="${ctx}/img/indexbg.jpg">
            </div>
            <div id="registerContent">
                <form id="registerForm" action="${ctx}/member/register" method="post">
                    <div class="msg"><input type="text" placeholder=" 手机号码" name="mobile"></div>
                    <div id="msgValidation" class="weui-row">
                        <div  class="weui-col-65"><input type="text" placeholder=" 验证码" name="captcha"></div>
                        <a href="javascript:;" class="weui_btn weui_btn_plain_warn weui-col-30">点击获取验证码</a>
                    </div>
                    <div class="msg"><input type="password" placeholder=" 密码" name="password"></div>
                    <div class="msg"><input type="password" placeholder=" 确认密码" name="confirmpassword"></div>
                    <%--<div class="msg"><input type="text" placeholder=" 邀请码（选填）" name="inviteCode"></div>--%>
                    <label><input type="checkbox" checked="checked">&nbsp;同意<a href="javascript:;" class="open-popup" data-target="#userAgreement">《Herpink用户使用协议》</a></label>
                    <button href="javascript:;" class="weui_btn weui_btn_warn" type="submit">提交注册</button>
                    <span>${msg}</span>
                    <a href="${ctx}/member/login" class="info">已有帐号,点击登录</a>
                </form>
                <%--<div id="info">--%>
                <%--<a href="#" style="color: #F24166;" type="submit">已有账号，点击登录</a>--%>
                <%--</div>--%>
                <div class="weui-popup-container" id="userAgreement">
                    <div class="weui-popup-modal">
                        <h3>Herpink用户使用协议</h3>
                        <p>1、一切移动客户端用户在下载并浏览APP手机APP软件时均被视为已经仔细阅读本条款并完全同意。凡以任何方式登陆本APP，或直接、间接使用本APP资料者，均被视为自愿接受本网站相关声明和用户服务协议的约束。
                        </p>
                        <p>2、APP手机APP转载的内容并不代表APP手机APP之意见及观点，也不意味着本网赞同其观点或证实其内容的真实性。
                        </p>
                        <p>3、APP手机APP转载的文字、图片、音视频等资料均由本APP用户提供，其真实性、准确性和合法性由信息发布人负责。APP手机APP不提供任何保证，并不承担任何法律责任。
                        </p>
                        <p>4、APP手机APP所转载的文字、图片、音视频等资料，如果侵犯了第三方的知识产权或其他权利，责任由作者或转载者本人承担，本APP对此不承担责任。</p>
                        <p>5、APP手机APP不保证为向用户提供便利而设置的外部链接的准确性和完整性，同时，对于该外部链接指向的不由APP手机APP实际控制的任何网页上的内容，APP手机APP不承担任何责任。</p>
                        <p>6、用户明确并同意其使用APP手机APP网络服务所存在的风险将完全由其本人承担；因其使用APP手机APP网络服务而产生的一切后果也由其本人承担，APP手机APP对此不承担任何责任。</p>
                        <p>7、除APP手机APP注明之服务条款外，其它因不当使用本APP而导致的任何意外、疏忽、合约毁坏、诽谤、版权或其他知识产权侵犯及其所造成的任何损失，APP手机APP概不负责，亦不承担任何法律责任。</p>
                        <p>8、对于因不可抗力或因黑客攻击、通讯线路中断等APP手机APP不能控制的原因造成的网络服务中断或其他缺陷，导致用户不能正常使用APP手机APP，APP手机APP不承担任何责任，但将尽力减少因此给用户造成的损失或影响。 </p>
                        <p>9、本声明未涉及的问题请参见国家有关法律法规，当本声明与国家有关法律法规冲突时，以国家法律法规为准。 </p>
                        <p>10、本网站相关声明版权及其修改权、更新权和最终解释权均属APP手机APP所有。</p>
                        <a href="javascript:;" class="weui_btn weui_btn_plain_warn close-popup">我已阅读并同意该协议</a>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${ctx}/js/register.js"></script>
</body>
</html>
