package cn.com.yuhaijun;

public abstract class AbsPasswordDict {

	// 62
	private char[] cPwdSet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9' };

	private int iBeginLength = 6;
	private int iEndLength = 12;
	
	private boolean isStopDict = false;

	public void initPwdDict(int _iBeginLength, int _iEndLength) {
		iBeginLength = _iBeginLength;
		iEndLength = _iEndLength;
	}

	public void initPwdDict(char[] _cPwdSet, int _iBeginLength, int _iEndLength) {
		cPwdSet = _cPwdSet;
		iBeginLength = _iBeginLength;
		iEndLength = _iEndLength;
	}

	public void runPwdDict() throws Exception {

		if (iEndLength > 18) {
			throw new Exception("Max Length is grant than 18...");
		} else if (iEndLength < iBeginLength) {
			throw new Exception("Mix Length is grant than Max Length...");
		}

		int[] column = new int[iEndLength];
		int icPwdSetLength = cPwdSet.length;
		int iNowLength = iBeginLength;
		int iNowChangeNum = 0;
		
		StringBuffer sbPwd = null;
		while (iNowLength <= iEndLength) {
			for (int i = 0; i < icPwdSetLength; i++) {
				if (this.isStopDict) {iNowLength = iEndLength + 1; break;}
				this.getPwd(sbPwd, column, iNowLength);
				column[iNowChangeNum]++;
				for (int j = 0; j < iEndLength; j++) {
					if (column[j] == icPwdSetLength) {
						if (column[iNowLength - 1] == icPwdSetLength) {
							iNowLength++;
						}
						if (iNowLength > iEndLength) {
							break;
						}
						column[j + 1]++;
						column[j] = 0;
					} else {
						break;
					}
				}
				if (i == icPwdSetLength) {i = 0;}
			}
		}
	}

	private void getPwd(StringBuffer sbPwd, int[] column, int iNowLength) {
		sbPwd = new StringBuffer();
		for (int i = iNowLength - 1; i >= 0; i--) {
			sbPwd = sbPwd.append(this.cPwdSet[column[i]]);
		}
		System.out.println(sbPwd.toString());
		if (this.checkPwd(sbPwd.toString())) {
			this.isStopDict = true;
		}
	}

	public abstract boolean checkPwd(String strPwd);

}
