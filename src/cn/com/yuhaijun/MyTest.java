package cn.com.yuhaijun;

public class MyTest extends AbsPasswordDict {

	public static void main(String[] args) throws Exception {
		
		MyTest myTest = new MyTest();
		myTest.runPwdDict();
	}

	@Override
	public boolean checkPwd(String strPwd) {

		boolean isRet = false;
		if ("aaaab9".equals(strPwd)) {
			isRet = true;
		}

		return isRet;
	}

}
