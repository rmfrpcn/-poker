package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerModel {
	/** ゲーム回数 */
	int games;
	/** 現在のチップ数 初期値は500 */
	static int chips;
	/** 獲得ポイント */
	int point = 0;
	/** 数字毎に手札を集計した配列(countNumber()内で初期化する) */
	int count[];
	/** ペアのカウのカウント */
	int two, three, four, straight, flash;
	/** 山札 */
	List<Integer> deckcards;
	/** 手札 */
	List<Integer> handcards;
	/** 送信ボタンに表示する文字列("交換","次のゲーム"のどちらか */
	String buttonLabel;
	/** プレイヤーへのメッセージ */
	String message;

	/** コンストラクタ */
	public PokerModel() {
		deckcards = new ArrayList<>();
		handcards = new ArrayList<>();
	}

	/** 一連のゲームを開始する */
	public void reset() {
		games = 0;
		chips = 500;

	}

	/** 次のゲームのためにカードを配りなおす */
	public void nextgame() {
		// 52枚の山札を作る
		deckcards.clear();
		for (int i = 0; i <= 51; i++) {
			deckcards.add(i);
		}
		Collections.shuffle(deckcards);
		// 山札の先頭から5枚抜いて手札にする
		handcards.clear();
		for (int i = 0; i < 5; i++) {
			int n = deckcards.remove(0);
			handcards.add(n);
		}
		// 残りの場札の枚数とカード番号をコンソールに表示する
		System.out.printf("%d: ", deckcards.size());
		for (int n : deckcards) {
			System.out.printf("%d ", n);
		}
		System.out.println();
		message = "交換するカードをチェックしてください";
		buttonLabel = "交換";
		games++;
	}

	/** indexで指定された位置のカードを、山札から補充したカードを置き換える */
	public void change(List<String> index) {
		System.out.println("index=" + index);
		for (String i : index) {
			int c = deckcards.remove(0); // 山札の先頭を取り除き、
			handcards.set(Integer.parseInt(i), c); // 手札の指定場所に入れる
		}
		evaluate();
		buttonLabel = "次のゲーム";
	}

	/** 役の判別を行い、チップを増減させる */
	public void evaluate() {
		countNumber();
		int red = countRed();
		int seven = countSeven();
		point = 0;
		if( (flash==5) 
				&& (count[9]==1) 
				&& (count[10]==1) 
				&& (count[11]==1) 
				&& (count[12]==1) 
				&& (count[0]==1) ) { //ロイヤルストレートフラッシュ
			message = "ロイヤルストレートフラッシュ";
			point = 500;
		}
		else if( (straight==5) && (flash==5) ) { //ストレートフラッシュ
			message = "ストレートフラッシュ";
			point = 300;
		}
		else if (four == 1) { //フォーカード
			message = "フォーカード";
			point = 200;
		} 
		else if ((two == 1) && (three == 1)) { //フルハウス
			message = "フルハウス";
			point = 150;
		} 
		else if(flash == 5) { //フラッシュ
			message = "フラッシュ";
			point = 100;
		}
		else if(straight == 5) { //ストレート
			message = "ストレート";
			point = 90;
		} 
		else if (three == 1) { //スリーカード
			message = "スリーカード";
			point = 80;
		}
		else if (two == 2) { //ツーペア
			message = "ツーペア";
			point = 70;
		}
		else if (two == 1) { //ワンペア
			message = "ワンペア";
			point = 60;
		}
		else if (red == 5) { //レッド
			message = "レッド";
			point = 50;
		} 
		else if (seven > 0) { //セブン
			message = "セブン";
			point = seven * 10;
		} 
		else { //ハイカード
			message = "ハイカード";
			point = -500;
		}
		chips += point;
		message += ": " + chips + "(" + point + ")";
	}

	/** 7 の枚数を返す */
	int countSeven() {
		int sevenCount = 0;
		for (int i : handcards) {
			if (i % 13 == 6) {
				sevenCount++;
			}
		}
		//System.out.println("7: " + sevenCount);
		return sevenCount;
	}

	/** 赤の枚数を返す */
	int countRed() {
		int redCount = 0;
		for (int i : handcards) {
			if (i > 12 && i < 39) {
				redCount++;
			}
		}
		//System.out.println("red: " + redCount);
		return redCount;
	}

	/** 数字毎に手札の枚数を数える */
	void countNumber() {
		count = new int[13];
		two = three = four = straight = flash = 0;
		for (int i : handcards) {
			count[i % 13]++;
		}
		for (int n : count) { //ペア数判定
			if (n == 2) {
				two++;
			} else if (n == 3) {
				three++;
			} else if (n == 4) {
				four++;
			}
			System.out.printf("%d", n);
		}
		System.out.println();
		for (int i = 0; i < 10; i++) { //ストレート判定
			straight = 0;
			if (count[i] == 1) {
				for (int j = i; j < i + 5; j++) {
					if (count[j%13] > 0) {
						straight++;
					}
				}
			}
			if (straight == 5) {
				break;
			}
		}
		for(int i : handcards) { //フラッシュ判定
			if(handcards.get(0)/13 == i/13) {
				flash++;
			}
		}
	}

	/** JSPから呼び出されるメソッド */
	public int getGames() {
		return games;
	}

	/** 現在のチップ数を返す */
	public static int getChips() {
		return chips;
	}

	/** 5枚の手札のうち，i番目のカード番号を返す (i=0～4) */
	public int getHandcardAt(int i) {
		return handcards.get(i);
	}

	/** 送信ボタンのラベルを返す */
	public String getButtonLabel() {
		return buttonLabel;
	}

	/** プレイヤーへのメッセージを返す */
	public String getMessage() {
		return message;
	}

	/** 手札をセットする（テスト用） */
	public void setHandcards(int a, int b, int c, int d, int e) {
		handcards.set(0, a);
		handcards.set(1, b);
		handcards.set(2, c);
		handcards.set(3, d);
		handcards.set(4, e);
	}

}
