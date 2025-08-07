package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantValue;

/**
 * 社員情報管理システム開始クラス 社員情報管理システムはこのクラスから始まる。<br/>
 * メニュー画面を表示する。
 *
 * @author System Shared
 *
 */
public class MainSystem {
	/**
	 * 社員管理システムを起動
	 *
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int menuNo = 0;

		do {
			// メニューの表示
			System.out.println(ConstantMsg.SYSTEM_TITLE);
			System.out.println(ConstantMsg.CHOICES_FIND_ALL);
			System.out.println(ConstantMsg.CHOICES_FIND_EMP_NAME);
			System.out.println(ConstantMsg.CHOICES_FIND_DEPT_ID);
			System.out.println(ConstantMsg.CHOICES_INSERT_EMPLOYEE);
			System.out.println(ConstantMsg.CHOICES_UPDATE_EMPLOYEE);
			System.out.println(ConstantMsg.CHOICES_DELETE_EMPLOYEE);
			System.out.println(ConstantMsg.CHOICES_SYSTEM_END);
			System.out.print(ConstantMsg.SELECT_MENU_MESSAGE + ConstantMsg.INPUT_PROMPT);

			// メニュー番号の入力
			String menuNoStr = br.readLine();
			menuNo = Integer.parseInt(menuNoStr);

			// 機能の呼出
			switch (menuNo) {
			case ConstantValue.CHOICES_FIND_ALL:
				// 全件表示機能の呼出
				DBController.findAll();
				break;

			case ConstantValue.CHOICES_FIND_EMP_NAME:
				// 社員名検索
				System.out.print(ConstantMsg.EMP_NAME + ConstantMsg.INPUT_PROMPT);

				// 検索機能の呼出
				DBController.findByEmpName();
				break;

			case ConstantValue.CHOICES_FIND_DEPT_ID:
				// 検索する部署IDを入力
				System.out.print(ConstantMsg.DEPT_ID + ConstantMsg.INPUT_PROMPT);
				String deptIdA = br.readLine();

				// 検索機能の呼出
				DBController.findByDeptId(deptIdA);
				break;

			case ConstantValue.CHOICES_INSERT_EMPLOYEE:
				// 登録する値を入力
				System.out.print(ConstantMsg.EMP_NAME + ConstantMsg.INPUT_PROMPT);
				String emp_name = br.readLine();
				System.out.print(ConstantMsg.GENDER + ConstantMsg.INPUT_PROMPT);
				String Seibetsu = br.readLine();
				System.out.print(ConstantMsg.BIRTHDAY + ConstantMsg.INPUT_PROMPT);
				String birthday = br.readLine();
				System.out.print(ConstantMsg.DEPT_ID + ConstantMsg.INPUT_PROMPT);
				String deptIdB = br.readLine();

				// 登録機能の呼出
				DBController.insert(emp_name, Seibetsu, birthday, deptIdB);
				break;

			case ConstantValue.CHOICES_UPDATE_EMPLOYEE:
				// 更新する社員IDを入力
				System.out.print(ConstantMsg.SELECT_UPDATE_EMPLOYEES_EMP_ID_MSG + ConstantMsg.INPUT_PROMPT);

				// 更新する値を入力する
				String empId_1 = br.readLine();
				Integer.parseInt(empId_1);

				// 更新機能の呼出
				DBController.update(empId_1);
				System.out.println(ConstantMsg.UPDATED_EMPLOYEE);

				break;

			case ConstantValue.CHOICES_DELETE_EMPLOYEE:
				// 削除する社員IDを入力
				System.out.print(ConstantMsg.SELECT_DELET_EMPLOYEES_EMP_ID_MSG + ConstantMsg.INPUT_PROMPT);

				// 削除機能の呼出
				DBController.delete();
				break;

			}
		} while (menuNo != ConstantValue.CHOICES_SYSTEM_END);
		System.out.println(ConstantMsg.SYSTEM_END_MSG);
	}
}
