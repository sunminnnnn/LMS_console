package Global;

public class Locale {
	//public final static String
	//public final static class
	
	public final static class LECTURE {
		public final static String SELECT_LECTURE = "강의 코드를 입력해주세요.";
		public final static String LECTURE_MANAGE = "<강의관리> (1)조회 (2)수정 (3)삭제";
		public final static String SEARCH_LECTURE = "조회할 강의 아이디를 입력해주세요:";
		public final static String NOT_EXIST = "해당 강의가 없습니다.";
		public final static String DELETE_LECTURE ="삭제할 강의 아이디를 입력해주세요:";
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
		public final static String MENU2 = "(1)강좌 선택 (2)장바구니 (3)신청 목록 (4)나가기";
		public final static String MENU3 = "(1)미리담기 (2)수강 신청";
		public final static String EXIT = "수강신청 프로그램을 종료합니다.";
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
		public final static String DDATA = "./data/";
	}
	
	public final static class SUGANGSINCHEONG {
		public final static String CAMPUS = "캠퍼스 코드를 ";
		public final static String COLLEGE = "대학 코드를 ";
		public final static String DEPARTMENT = "학과 코드를 ";
		public final static String ALREADY_EXIST = "이미 신청한 강좌입니다.";
		public final static String SUCCESS = "정상적으로 신청했습니다.";
	}
	
	public final static class MIRIDAMGI {
		public final static String SINCHEONG = "수강 신청할 강의 아이디를 입력해주세요: ";
		public final static String ALREADY_EXIST = "이미 담은 강좌입니다.";
		public final static String SUCCESS = "정상적으로 담았습니다.";
		public final static String ASK_SINCHEONG= "강좌를 수강 신청하시겠습니까? (1)Y (2)N";
	}
	
	
	
}
