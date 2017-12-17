import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Appview  {
	public static void output(String[] args) {

		AppController control = new AppController();
		try {
			BufferedReader br = new BufferedReader(new FileReader("Food.txt"));
			String line = br.readLine();
			String[] split_line = line.split(", ");
			int Try_Number = (Integer.parseInt(split_line[0]));
			for (int i = 0; i < Try_Number; i++) {
				String line_ = br.readLine();
				String[] split_line_ = line_.split(", ");
				control.add_List_Food((split_line_[0]), (split_line_[1]), (split_line_[2]), (split_line_[3]), Double.parseDouble((split_line_[4])), (split_line_[5]));
			}
			line = br.readLine();
			split_line = line.split(", ");
			Try_Number = (Integer.parseInt(split_line[0]));
			for (int i = 0; i < Try_Number; i++) {
				String line_ = br.readLine();
				String[] split_line_ = line_.split(", ");
				control.add_ALL_Ate_Food((split_line_[0]), (split_line_[1]), (split_line_[2]), (split_line_[3]), Double.parseDouble((split_line_[4])), (split_line_[5]));
			} //이제 이부분에서 먹었던 음식리스트를 읽어 올 수 있도록 구현해줘야 한다.
			br.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		int id;
		String passwrd;
		boolean k;
		Scanner sc0 = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		do{
			System.out.println("손님이십니까?(yes:1)");
			id = sc0.nextInt();
		if(id == 1) {
			k=true;
			break;
		}
		else if(id == 12345) {
			System.out.println("Password: ");
			passwrd = sc1.nextLine();
			int god;
			Scanner remote = new Scanner(System.in);
			if(passwrd.equals("******")) {
				do {
					System.out.println("1. 댓글 삭제기능 2. log out");
					god = remote.nextInt();
				if(god == 1) 
					{
					System.out.println("댓글 삭제 기능");
					control.Print_All_Comment();
					control.Delete_Comment();
					}
				else if(god == 2)
					{
					System.out.println("log out");
					
					break;
					}
				else
					{
					System.out.println("다시 입력하세요.");
					god=1;
					}
				}while(god==1);
			}
				k=false;
			}
			else {
			System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
			
			}
		}while(k=true);
		
		int select;
		Scanner sc = new Scanner(System.in);
		
		
		
		
		System.out.println("******LUNCH SELECT SOLUTION******");
		do{
			System.out.println("어떤 방법으로 메뉴를 고르시겠습니까?");
			System.out.println("랜덤으로 음식을 고르시려면                  0");
			System.out.println("랜덤으로  짧은 시간 내에 고르시려면      1");
			System.out.println("선택을 통해 고르시려면                        2");
			System.out.println("가게 메뉴의 댓글을 보고싶으시면           3");
			System.out.println("mission:궁동을 점령하라                4");
			System.out.println("최근 먹은 음식 보고싶으시면                 5");
			System.out.println("logout(프로그램 종료)     other");
			System.out.print("숫자 입력 : ");
			select = sc.nextInt();			
			if(select==0) {
				System.out.println("0");
				System.out.println("\n------랜덤으로 선택한 결과!------");
				control.Random_Choice_Food();
			}
			else if(select==1) {
				System.out.println("1");
				System.out.println("\n------시간이 빠른것으로 선택한 결과!------\n 시간이 없으니 ");
				control.Notime_Choice();
				System.out.println("를 드시는 것을 추천!");
			}
			else if(select==2) {
			System.out.println("2"); 
			System.out.println("1.더하는방식 2.빼는방식");
			
			control.Choice_Food();
			
			control.Add_Comment();
			}
			else if(select==3) {
			System.out.println("3");
			System.out.println("comments를 선택하셨습니다.");
			control.Print_All_Comment();
			}
			else if(select==4) {
			System.out.println("*********궁동을 점령하라!*********");
			control.What_Percent();
			System.out.println("*****************************");
			}
			else if(select==5) {
	         System.out.println("5");
	         System.out.println("최근에 먹은 것들을 보는 메뉴를 선택하셨습니다.");
	         control.What_ate();
			}

		else {
			System.out.println("프로그램을 종료합니다.");
			  BufferedWriter bw = null;
		      try {
		         bw = new BufferedWriter(new FileWriter("Food.txt"));

		         bw.write(String.valueOf(control.get_Food_Size()));
		         bw.newLine();

		         for (int i = 0; i < control.get_Food_Size(); i++) {
		            String line[] = { control.get_Food().get(i).get_Store(), control.get_Food().get(i).get_Food_Name(),
		                  control.get_Food().get(i).get_Kind(), control.get_Food().get(i).get_Nation(),
		                  String.valueOf(control.get_Food().get(i).get_Time()),
		                  control.get_Food().get(i).get_Comment() };

		            for (String str : line) {
		               bw.write(str);
		               bw.write(", ");
		            }
		            bw.newLine();
		         }

		         // 여기서 부터는 먹은 음식들을 써주는 것임
		         bw.write(String.valueOf(control.get_ALL_Ate_Food().size()));
		         bw.newLine();

		         for (int i = 0; i < control.get_ALL_Ate_Food().size(); i++) {
		            String line[] = { control.get_ALL_Ate_Food().get(i).get_Store(),
		                  control.get_ALL_Ate_Food().get(i).get_Food_Name(),
		                  control.get_ALL_Ate_Food().get(i).get_Kind(), control.get_ALL_Ate_Food().get(i).get_Nation(),
		                  String.valueOf(control.get_ALL_Ate_Food().get(i).get_Time()),
		                  control.get_ALL_Ate_Food().get(i).get_Comment() };

		            for (String str : line) {
		               bw.write(str);
		               bw.write(", ");
		            }
		            bw.newLine();
		         } // 먹은 음식들을 저장해 줌

		      } catch (FileNotFoundException fnfe) {
		         System.out.println("파일을 찾을 수 없음");
		      } catch (IOException ioe) {
		         System.out.println("파일 입출력 오류");
		      } finally {
		         try {
		            bw.close();
		         } catch (IOException ioe) {
		            ioe.printStackTrace();
		         }
		      }
			break;
		}
		
		System.out.println("\n\n");
		
		
	    
		}while(select>= 0 && select<=5); // 여기 또한 먹었던 음식리스트를 저장할 수 있게 구현 해주어야 한다.
	}
}
