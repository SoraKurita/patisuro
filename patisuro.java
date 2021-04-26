package 〇〇;
import java.util.Random;  //抽選用
import java.util.Scanner; //文字入力用
public class pati {
	public static void main(String[] args) throws Exception{
		
		Random rand = new Random(System.currentTimeMillis());
		Scanner sc = new Scanner(System.in);
		
		int coin = 100; //初期コイン所持数
		int chip; //賭け枚数
		int randresult; //抽選結果
		int looop = 0; //スロットを回した回数
		int win = 0; //当たりを引いた回数
		int extraWin = 0; //大当たりを引いた回数
		int score; //回した回数、当たり数、大当たり数から算出するスコア
		String name; //名前
		
		String[] result = new String[3]; //スロットの3枠の作成
		String[] yaku = new String[]{"♪","♡","◇","$","７"}; //役の作成
		
		//↓利用者に役ごとの倍率を表示
		System.out.println("♪♪♪:1"); 
		System.out.println("♡♡♡:10");
		System.out.println("◇◇◇:30");
		System.out.println("$$$:50");
		System.out.println("777:100");
		
		do {
			System.out.print("名前（2~8文字）：");
			name = sc.nextLine(); //名前の入力
			if(name.length() > 8 || name.length() < 2) { //名前の制約
				System.out.println("その名前は使用できません");
			}
		}while(name.length() > 8 || name.length() < 2);
			
		do {
			
				System.out.println(name+"さん"+" 回転数："+looop+"　枚数："+coin); //利用者の情報を表示
				System.out.print("賭け数(最大9枚)：");
				chip = sc.nextInt(); //賭け枚数の入力
				looop++; //回した回数の加算
				
				if(chip>9) {
					chip = 9; //利用者が9枚以上の枚数を賭けようとしたら自動的に9枚で決定
					System.out.println("※賭けられる上限を超えたので9枚に設定しました※");
				}
				if(chip > coin) {
					System.out.println("所持数より多い枚数は賭けられません"); //所持枚数が9枚以下で賭け枚数が所持枚数より多かった場合の文		
				}
			
			coin -= chip; //所持枚数から賭け枚数を引く
			
			for(int i = 0;i<result.length;i++) { //スロット枠の3回分処理
				randresult = rand.nextInt(15); //出力される数字を15個用意して、役の倍率に沿った確率となるよう分配
				if(randresult < 5) {
					result[i] = yaku[0]; //抽選で出力された数字が0～5ならyaku[0](♪)をresult[]に挿入…といった具合
				}
				if(randresult >= 5 && randresult < 9) {
					result[i] = yaku[1];
				}
				if(randresult >= 9 && randresult < 12) {
					result[i] = yaku[2];
				}
				if(randresult >= 12 && randresult < 14) {
					result[i] = yaku[3];
				}
				if(randresult == 14) {
					result[i] = yaku[4];
				}
			}
			Thread.sleep(1000); //スロットが回っている時間をイメージ
			System.out.print(result[0]+"  "); //出た役を画面に表示
			Thread.sleep(1000);
			System.out.print(result[1]);
			if(result[0]==result[1]) {
				System.out.print("（リーチ！）");
				Thread.sleep(5000); //リーチの場合通常より長い回転時間（というイメージ）
			}else {
				Thread.sleep(1000);
			}
			System.out.println("  "+result[2]);
			Thread.sleep(50);
			
			//↓役ごとの当たった場合の処理
			if(result[0]==result[1] && result[0]==result[2] && result[0] == yaku[0]) {
				System.out.println("当たり！（1枚✕"+chip+"倍）");
				coin += 1 * chip; //所持枚数に「当たった役の倍率×賭け枚数」を追加
				win++; //当たり回数を1追加
			}
			
			if(result[0]==result[1] && result[0]==result[2] && result[0] == yaku[1]) {
				System.out.println("当たり！（10枚✕"+chip+"倍）");
				coin += 10 * chip;
				win++;
			}
			
			if(result[0]==result[1] && result[0]==result[2] && result[0] == yaku[2]) {
				System.out.println("当たり！（30枚✕"+chip+"倍）");
				coin += 30 * chip;
				win++;
			}
			
			if(result[0]==result[1] && result[0]==result[2] && result[0] == yaku[3]) {
				System.out.println("当たり！（50枚✕"+chip+"倍）");
				coin += 50 * chip;
				win++;
			}
			
			if(result[0]==result[1] && result[0]==result[2] && result[0] == yaku[4]) {
				System.out.println("大当たり！（100枚✕"+chip+"倍）");
				coin += 100 * chip;
				extraWin++; //大当たり回数を1追加
			}
			
		}while(coin > 0); //コインがなくなるまで続ける
		
		score = looop * 1 + win * 10 + extraWin * 100; //スコアの算出
		
		//↓終了後、プレイ情報表示
		System.out.println("コインがありません。またお越しください");
		System.out.println("【結果】");
		System.out.println("総回転数："+looop+"回");
		System.out.println("当たり数："+win+"回");
		System.out.println("大当たり数："+extraWin+"回");
		System.out.println("スコア："+score);
	}
}
