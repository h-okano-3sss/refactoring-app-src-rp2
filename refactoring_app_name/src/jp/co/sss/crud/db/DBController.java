package jp.co.sss.crud.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantSQL;
import jp.co.sss.crud.util.ConstantValue;

/**
 * DB操作処理用のクラス
 *
 * @author System Shared
 */
public class DBController {

	/** インスタンス化を禁止 */
	private DBController() {
	}

	/**
	 * 全ての社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 */
	public static void findAll() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_ALL_SELECT);

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();

			//resultSetの結果Setがない場合はfalse
			if (!resultSet.isBeforeFirst()) {
				System.out.println(ConstantMsg.NOT_FOUND_EMPLOYEE);
				return;
			}

			// レコードを出力
			System.out.println(ConstantMsg.EMPLOYEE_TABLE_HEADER);
			while (resultSet.next()) {
				System.out.print(resultSet.getString(ConstantSQL.EMP_ID) + ConstantMsg.TAB);
				System.out.print(resultSet.getString(ConstantSQL.EMP_NAME) + ConstantMsg.TAB);

				int gender = Integer.parseInt(resultSet.getString(ConstantSQL.GENDER));
				if (gender == ConstantValue.GENDER_NOANSWER) {
					System.out.print(ConstantMsg.NO_ANSWER + ConstantMsg.TAB);
				} else if (gender == ConstantValue.GENDER_MALE) {
					System.out.print(ConstantMsg.MALE + ConstantMsg.TAB);

				} else if (gender == ConstantValue.GENDER_FEMALE) {
					System.out.print(ConstantMsg.FEMALE + ConstantMsg.TAB);

				} else if (gender == ConstantValue.GENDER_OTHERS) {
					System.out.print(ConstantMsg.OTHERS + ConstantMsg.TAB);

				}

				System.out.print(resultSet.getString(ConstantSQL.BIRTHDAY) + ConstantMsg.TAB);
				System.out.println(resultSet.getString(ConstantSQL.DEPT_NAME));
			}

			System.out.println(ConstantMsg.BLANK);
		} finally {
			// ResultSetをクローズ
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員名に該当する社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void findByEmpName() throws ClassNotFoundException, SQLException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 検索ワード
		String searchWord = br.readLine();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// SQL文を準備
			StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			sql.append(ConstantSQL.SQL_SELECT_BY_EMP_NAME);

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(sql.toString());

			// 検索条件となる値をバインド
			preparedStatement.setString(1, ConstantSQL.PLACEHOLDER + searchWord + ConstantSQL.PLACEHOLDER);

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.isBeforeFirst()) {
				System.out.println(ConstantMsg.NOT_FOUND_EMPLOYEE);
				return;
			}

			System.out.println(ConstantMsg.EMPLOYEE_TABLE_HEADER);
			while (resultSet.next()) {
				System.out.print(resultSet.getString(ConstantSQL.EMP_ID) + ConstantMsg.TAB);
				System.out.print(resultSet.getString(ConstantSQL.EMP_NAME) + ConstantMsg.TAB);

				int gender = Integer.parseInt(resultSet.getString(ConstantSQL.GENDER));
				if (gender == ConstantValue.GENDER_NOANSWER) {
					System.out.print(ConstantMsg.NO_ANSWER + ConstantMsg.TAB);
				} else if (gender == ConstantValue.GENDER_MALE) {
					System.out.print(ConstantMsg.MALE + ConstantMsg.TAB);

				} else if (gender == ConstantValue.GENDER_FEMALE) {
					System.out.print(ConstantMsg.FEMALE + ConstantMsg.TAB);

				} else if (gender == ConstantValue.GENDER_OTHERS) {
					System.out.print(ConstantMsg.OTHERS + ConstantMsg.TAB);

				}

				System.out.print(resultSet.getString(ConstantSQL.BIRTHDAY) + ConstantMsg.TAB);
				System.out.println(resultSet.getString(ConstantSQL.DEPT_NAME));
			}

			System.out.println(ConstantMsg.BLANK);

		} finally {
			// クローズ処理
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 部署IDに該当する社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void findByDeptId(String deptId) throws ClassNotFoundException, SQLException, IOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// SQL文を準備
			StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			sql.append(ConstantSQL.SQL_SELECT_BY_DEPT_ID);

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(sql.toString());

			// 検索条件となる値をバインド
			preparedStatement.setInt(1, Integer.parseInt(deptId));

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.isBeforeFirst()) {
				System.out.println(ConstantMsg.NOT_FOUND_EMPLOYEE);
				return;
			}

			System.out.println(ConstantMsg.EMPLOYEE_TABLE_HEADER);
			while (resultSet.next()) {
				System.out.print(resultSet.getString(ConstantSQL.EMP_ID) + ConstantMsg.TAB);
				System.out.print(resultSet.getString(ConstantSQL.EMP_NAME) + ConstantMsg.TAB);

				int gender = Integer.parseInt(resultSet.getString(ConstantSQL.GENDER));
				if (gender == ConstantValue.GENDER_NOANSWER) {
					System.out.print(ConstantMsg.NO_ANSWER + ConstantMsg.TAB);
				} else if (gender == ConstantValue.GENDER_MALE) {
					System.out.print(ConstantMsg.MALE + ConstantMsg.TAB);

				} else if (gender == ConstantValue.GENDER_FEMALE) {
					System.out.print(ConstantMsg.FEMALE + ConstantMsg.TAB);

				} else if (gender == ConstantValue.GENDER_OTHERS) {
					System.out.print(ConstantMsg.OTHERS + ConstantMsg.TAB);

				}

				System.out.print(resultSet.getString(ConstantSQL.BIRTHDAY) + ConstantMsg.TAB);
				System.out.println(resultSet.getString(ConstantSQL.DEPT_NAME));
			}

			System.out.println(ConstantMsg.BLANK);

		} finally {
			// クローズ処理
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件登録
	 * 
	 * @param empName 社員名
	 * @param gender 性別
	 * @param birthday 生年月日
	 * @param deptId 部署ID
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException            DB処理でエラーが発生した場合に送出
	 * @throws IOException             入力処理でエラーが発生した場合に送出
	 */
	public static void insert(String empName, String gender, String birthday, String deptId)
			throws ClassNotFoundException, SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// DBに接続
			connection = DBManager.getConnection();

			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_INSERT);

			// 入力値をバインド
			preparedStatement.setString(1, empName);
			preparedStatement.setString(2, gender);
			preparedStatement.setString(3, birthday);
			preparedStatement.setString(4, deptId);

			// SQL文を実行
			preparedStatement.executeUpdate();

			// 登録完了メッセージを出力
			System.out.println(ConstantMsg.INSERTED_EMPLOYEE);
		} finally {
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件更新
	 * 
	 * @param empId 社員ID
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException            DB処理でエラーが発生した場合に送出
	 * @throws IOException             入力処理でエラーが発生した場合に送出
	 */
	public static void update(String empId)
			throws ClassNotFoundException, SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			// データベースに接続
			connection = DBManager.getConnection();

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_UPDATE);

			System.out.print(ConstantMsg.EMP_NAME + ConstantMsg.INPUT_PROMPT);
			String empName = br.readLine();
			// 性別を入力
			System.out.print(ConstantMsg.GENDER + ConstantMsg.INPUT_PROMPT);
			String gender = br.readLine();
			// 誕生日を入力
			System.out.print(ConstantMsg.BIRTHDAY + ConstantMsg.INPUT_PROMPT);
			String birthday = br.readLine();

			// 部署IDを入力
			System.out.print(ConstantMsg.DEPT_ID + ConstantMsg.INPUT_PROMPT);
			String deptId = br.readLine();

			// 入力値をバインド
			preparedStatement.setString(1, empName);
			preparedStatement.setString(2, gender);
			preparedStatement.setString(3, birthday);
			preparedStatement.setString(4, deptId);
			preparedStatement.setString(5, empId);

			// SQL文の実行(失敗時は戻り値0)
			preparedStatement.executeUpdate();

		} finally {
			// クローズ処理
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件削除
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void delete() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			// データベースに接続
			connection = DBManager.getConnection();
			String empId = br.readLine();

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_DELETE);

			// 社員名をバインド
			preparedStatement.setString(1, empId);

			// SQL文の実行(失敗時は戻り値0)
			preparedStatement.executeUpdate();

			System.out.println(ConstantMsg.DELETED_EMPLOYEE);

		} catch (Exception e) {
			e.printStackTrace();

		}

		finally {
			// Statementをクローズ
			try {
				DBManager.close(preparedStatement);
				DBManager.close(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// DBとの接続を切断
		}
	}
}
