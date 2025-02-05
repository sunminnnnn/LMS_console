package global;

public class Locale {
	// public final static String
	// public final static class

	public final static String OK_LABEL = "확인";
	public final static String CANCEL_LABEL = "취소";
	public final static String NOTICE = "알림";

	public final static class AccountPanel {
		public final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
		public final static String INSA_POSTFIX = " 님, 환영합니다. ";
		public final static String LOGIN_TIME_PREFIX = "로그인 시간은 ";
		public final static String IPNIDA = "입니다.";
	}

	public final static class LLoginJoinPanel {
		public final static String LOGINTITLE = "로그인";
		public final static String JOINTITLE = "회원가입";

		public final static String ID_LABEL = "아이디 ";
		public final static String PASSWORD_LABEL = "비밀번호 ";
		public final static String ADDRESS_LABEL = "이메일 ";
		public final static String DEPARTMENT_LABEL = "학과 ";
		public static final String NAME_LABEL = "이름 ";
		public static final String FIND_PW = "비밀번호 찾기";

		public final static String FINISH_DIALOG = "등록되었습니다.";
		public final static String FINISH_DIALOG_TITLE = "회원가입 완료";

		public final static String NULL_ID_PW = "아이디 또는 비밀번호를 입력하세요.";
		public final static String RE_ID_PW = "아이디 또는 비밀번호를 확인하세요. ";
		public final static String RE_ID_PW2 = "회 틀렸습니다. 5회 이상 틀릴 시 임시 비밀번호가 발급됩니다.";
		public final static String FIND_PW_PLZ = "비밀번호 찾기로 비밀번호를 찾으십시오.";
		public final static String LOGIN_DIALOG = "로그인 확인창";
	}

	public final static class USERINFO {
		public final static String ENTER_EMAIL = "이메일을 입력하세요: ";
		public final static String ERROR_EMAIL = "이메일 형식이 올바르지 않습니다. 다시 입력해주세요.";
		public final static String SEND_EMAIL_ID = "해당 이메일로 아이디가 발송되었습니다.";
		public final static String NOT_EXIST_EMAIL = "해당 이메일로 가입된 계정이 없습니다.";
		public final static String ENTER_ID = "아이디를 입력하세요: ";
		public final static String SEND_EMAIL_PASSWORD = "해당 이메일로 임시 비밀번호가 발송되었습니다.";
		public final static String ENTER_NOWPASSWORD = "현재 비밀번호를 입력하세요: ";
		public final static String ENTER_TEMPPASSWORD = "변경할 비밀번호를 입력하세요: ";
		public final static String FIN_PASSWORD_CHANGE = "변경이 완료되었습니다.";
	}

	public final static class LECTURE {
		public final static String SELECT_LECTURE = "강의 코드를 입력해주세요.";
		public final static String LECTURE_MANAGE = "<강의관리> (1)조회 (2)수정 (3)삭제";
		public final static String SEARCH_LECTURE = "조회할 강의 아이디를 입력해주세요:";
		public final static String NOT_EXIST = "해당 강의가 없습니다.";
		public final static String DELETE_LECTURE = "삭제할 강의 아이디를 입력해주세요:";
		public final static String FIN_DELETE = "삭제가 완료되었습니다.";
		public final static String UPDATE_LECTURE = "수정할 강의 아이디를 입력해주세요:";
		public final static String UPDATE_NAME = "수정할 강의 이름을 입력해주세요:";
		public final static String UPDATE_PROFESSOR = "수정할 교수명을 입력해주세요:";
		public final static String UPDATE_GRADE = "수정할 학년을 입력해주세요:";
		public final static String UPDATE_TIME = "수정할 시간대를 입력해주세요:";

	}

	public final static class INITIAL {
		public final static String MENU_INPUT = "메뉴 번호를 입력해주세요.\n";
		public final static String MENU1 = "(1)로그인 (2)회원가입 (3)강좌 관리 (4)회원 목록";
		public final static String MENU2 = "(1)강좌 선택 (2)장바구니 (3)신청 목록 (4)비밀번호 변경 (5)나가기";
		public final static String MENU3 = "(1)미리담기 (2)수강 신청";
		public final static String MENU4 = "(1)아이디 찾기 (2)비밀번호 변경";
		public final static String EXIT = "수강신청 프로그램을 종료합니다.";
	}

	public final static class JOIN {
		public final static String START_JOIN = "회원등록을 시작합니다.";
		public final static String ID = "아이디: ";
		public final static String PASSWORD = "비밀번호: ";
		public final static String NAME = "이름: ";
		public final static String ADDRESS = "주소: ";
		public final static String DEPARTMENT = "학과: ";
		public final static String EMAIL = "이메일: ";
		public final static String FIN_JOIN = "등록이 완료되었습니다.";
	}

	public final static class LOGIN {
		public final static String ID = "사용자 아이디를 입력해주세요: ";
		public final static String PASSWORD = "비밀번호를 입력해주세요: ";
		public final static String ERROR = "사용자 아이디나 비밀번호를 잘못 입력하였습니다.";
		public final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
		public final static String WELCOME = " 님, 환영합니다. 로그인 시각은 ";
		public final static String FIN = "입니다.";
	}

	public final static class FILE {
		public final static String TXT = ".txt";
		public final static String DATA = "data/";
		public final static String SINCHEONG = "account/Sincheong";
		public final static String MIRIDAMGI = "account/Miridamgi";
		public final static String ACCOUNT = "account/account";
		public final static String TEMP_ACCOUNT = "account/temp";
		public final static String DDATA = "./data/";
	}

	public final static class SUGANGSINCHEONG {
		public final static String CAMPUS = "캠퍼스 코드를 ";
		public final static String COLLEGE = "대학 코드를 ";
		public final static String DEPARTMENT = "학과 코드를 ";
		public final static String ALREADY_EXIST = "이미 신청한 강좌입니다.";
		public final static String SUCCESS = "정상적으로 신청했습니다.";
		public final static String ASK_CANCEL = "강좌를 수강 취소하시겠습니까? (1)Y (2)N";
		public final static String CANCEL = "수강 취소할 강의 아이디를 입력해주세요: ";
	}

	public final static class MIRIDAMGI {
		public final static String SINCHEONG = "수강 신청할 강의 아이디를 입력해주세요: ";
		public final static String ALREADY_EXIST = "이미 담은 강좌입니다.";
		public final static String SUCCESS = "정상적으로 담았습니다.";
		public final static String ASK_SINCHEONG = "(1)수강 신청 (2)미리담기 취소";
		public final static String CANCEL = "미리담기 취소할 강의 아이디를 입력해주세요: ";
	}

	public enum lectureHeader {
		code("과목코드"), name("교과목명"), professor("교수명"), grade("학점"), number("강의시간");

		private String text;

		private lectureHeader(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}

		public int getInt() {
			return Integer.parseInt(text);
		}
	}
}
