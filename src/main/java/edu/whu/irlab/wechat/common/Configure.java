package edu.whu.irlab.wechat.common;

public class Configure {
    //��������Լ�Ҫ���ܺõ�˽��Key�ˣ��м�ֻ�ܷ����Լ��ĺ�̨��������ܷ����κο��ܱ�����Դ����Ŀͻ��˳����У�
	// ÿ���Լ�Post���ݸ�API��ʱ��Ҫ�����key���������ֶν���ǩ�������ɵ�ǩ�������Sign����ֶΣ�API�յ�Post���ݵ�ʱ��Ҳ����ͬ����ǩ���㷨��Post���������ݽ���ǩ������֤
	// �յ�API�ķ��ص�ʱ��ҲҪ�����key���Է��ص���������ǩ������API��Sign���ݽ��бȽϣ����ֵ��һ�£��п������ݱ����������۸�

	private static String key = "123456";

	//΢�ŷ���Ĺ��ں�ID����ͨ���ں�֮����Ի�ȡ����
	private static String appID = "wxe77e6cada6b7344f";

    private static String appSecret = "fe1b97dd7e20110a7070e055fb8a3f8e";

	//΢��֧��������̻���ID����ͨ���ںŵ�΢��֧������֮����Ի�ȡ����
	private static String mchID = "123456";

	//����ģʽ�¸����̻���������̻���
	private static String subMchID = "";

	//HTTPS֤��ı���·��
	private static String certLocalPath = "";

	//HTTPS֤�����룬Ĭ����������̻���MCHID
	private static String certPassword = "";

	//�Ƿ�ʹ���첽�̵߳ķ�ʽ���ϱ�API���٣�Ĭ��Ϊ�첽ģʽ
	private static boolean useThreadToDoReport = true;

	//����IP
	private static String ip = "127.0.0.1";

	//�����Ǽ���API��·����
    public static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	public static boolean isUseThreadToDoReport() {
		return useThreadToDoReport;
	}

	public static void setUseThreadToDoReport(boolean useThreadToDoReport) {
		Configure.useThreadToDoReport = useThreadToDoReport;
	}

	public static String HttpsRequestClassName = "com.tencent.common.HttpsRequest";

	public static void setKey(String key) {
		Configure.key = key;
	}

	public static void setAppID(String appID) {
		Configure.appID = appID;
	}

    public static void setAppSecret(String appSecret) {
        Configure.appSecret = appSecret;
    }

    public static void setMchID(String mchID) {
		Configure.mchID = mchID;
	}

	public static void setSubMchID(String subMchID) {
		Configure.subMchID = subMchID;
	}

	public static void setCertLocalPath(String certLocalPath) {
		Configure.certLocalPath = certLocalPath;
	}

	public static void setCertPassword(String certPassword) {
		Configure.certPassword = certPassword;
	}

	public static void setIp(String ip) {
		Configure.ip = ip;
	}

	public static String getKey(){
		return key;
	}
	
	public static String getAppid(){
		return appID;
	}

    public static String getAppSecret() {
        return appSecret;
    }

    public static String getMchid(){
		return mchID;
	}

	public static String getSubMchid(){
		return subMchID;
	}
	
	public static String getCertLocalPath(){
		return certLocalPath;
	}
	
	public static String getCertPassword(){
		return certPassword;
	}

	public static String getIP(){
		return ip;
	}

	public static void setHttpsRequestClassName(String name){
		HttpsRequestClassName = name;
	}

}
