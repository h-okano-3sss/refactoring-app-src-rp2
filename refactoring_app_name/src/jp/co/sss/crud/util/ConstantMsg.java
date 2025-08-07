package jp.co.sss.crud.util;

public class ConstantMsg {
	public static final String TAB = "\t";
	public static final String BLANK = "";
	public static final String INPUT_PROMPT = ":";
	public static final String EMPLOYEE_TABLE_HEADER = "社員ID\t社員名\t性別\t生年月日\t部署名";
	public static final String INSERTED_EMPLOYEE = "社員情報を登録しました";
	public static final String DELETED_EMPLOYEE = "社員情報を削除しました";
	public static final String UPDATED_EMPLOYEE = "社員情報を更新しました";
	public static final String NOT_FOUND_EMPLOYEE = "該当者はいませんでした";
	public static final String NO_ANSWER = "回答なし";
	public static final String MALE = "男性";
	public static final String FEMALE = "女性";
	public static final String OTHERS = "その他";
	public static final String EMP_NAME = "社員名";
	public static final String GENDER = "性別(0:回答しない, 1:男性, 2:女性, 9:その他)";
	public static final String BIRTHDAY = "生年月日(西暦年/月/日)";
	public static final String DEPT_ID = "部署ID(1：営業部、2：経理部、3：総務部)";

	public static final String SYSTEM_TITLE = "=== 社員管理システム ===";
	public static final String CHOICES_FIND_ALL = "1.全件表示";
	public static final String CHOICES_FIND_EMP_NAME = "2.社員名検索";
	public static final String CHOICES_FIND_DEPT_ID = "3.部署ID検索";
	public static final String CHOICES_INSERT_EMPLOYEE = "4.新規登録";
	public static final String CHOICES_UPDATE_EMPLOYEE = "5.更新";
	public static final String CHOICES_DELETE_EMPLOYEE = "6.削除";
	public static final String CHOICES_SYSTEM_END = "7.終了";
	public static final String SELECT_MENU_MESSAGE = "メニュー番号を入力してください";
	public static final String SELECT_UPDATE_EMPLOYEES_EMP_ID_MSG = "更新する社員の社員IDを入力してください";
	public static final String SELECT_DELET_EMPLOYEES_EMP_ID_MSG = "削除する社員の社員IDを入力してください";
	public static final String SYSTEM_END_MSG = "システムを終了します。";
}
