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
			} //���� �̺κп��� �Ծ��� ���ĸ���Ʈ�� �о� �� �� �ֵ��� ��������� �Ѵ�.
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
			System.out.println("�մ��̽ʴϱ�?(yes:1)");
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
					System.out.println("1. ��� ������� 2. log out");
					god = remote.nextInt();
				if(god == 1) 
					{
					System.out.println("��� ���� ���");
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
					System.out.println("�ٽ� �Է��ϼ���.");
					god=1;
					}
				}while(god==1);
			}
				k=false;
			}
			else {
			System.out.println("�߸��Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
			
			}
		}while(k=true);
		
		int select;
		Scanner sc = new Scanner(System.in);
		
		
		
		
		System.out.println("******LUNCH SELECT SOLUTION******");
		do{
			System.out.println("� ������� �޴��� ���ðڽ��ϱ�?");
			System.out.println("�������� ������ ���÷���                  0");
			System.out.println("��������  ª�� �ð� ���� ���÷���      1");
			System.out.println("������ ���� ���÷���                        2");
			System.out.println("���� �޴��� ����� ��������ø�           3");
			System.out.println("mission:�õ��� �����϶�                4");
			System.out.println("�ֱ� ���� ���� ��������ø�                 5");
			System.out.println("logout(���α׷� ����)     other");
			System.out.print("���� �Է� : ");
			select = sc.nextInt();			
			if(select==0) {
				System.out.println("0");
				System.out.println("\n------�������� ������ ���!------");
				control.Random_Choice_Food();
			}
			else if(select==1) {
				System.out.println("1");
				System.out.println("\n------�ð��� ���������� ������ ���!------\n �ð��� ������ ");
				control.Notime_Choice();
				System.out.println("�� ��ô� ���� ��õ!");
			}
			else if(select==2) {
			System.out.println("2"); 
			System.out.println("1.���ϴ¹�� 2.���¹��");
			
			control.Choice_Food();
			
			control.Add_Comment();
			}
			else if(select==3) {
			System.out.println("3");
			System.out.println("comments�� �����ϼ̽��ϴ�.");
			control.Print_All_Comment();
			}
			else if(select==4) {
			System.out.println("*********�õ��� �����϶�!*********");
			control.What_Percent();
			System.out.println("*****************************");
			}
			else if(select==5) {
	         System.out.println("5");
	         System.out.println("�ֱٿ� ���� �͵��� ���� �޴��� �����ϼ̽��ϴ�.");
	         control.What_ate();
			}

		else {
			System.out.println("���α׷��� �����մϴ�.");
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

		         // ���⼭ ���ʹ� ���� ���ĵ��� ���ִ� ����
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
		         } // ���� ���ĵ��� ������ ��

		      } catch (FileNotFoundException fnfe) {
		         System.out.println("������ ã�� �� ����");
		      } catch (IOException ioe) {
		         System.out.println("���� ����� ����");
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
		
		
	    
		}while(select>= 0 && select<=5); // ���� ���� �Ծ��� ���ĸ���Ʈ�� ������ �� �ְ� ���� ���־�� �Ѵ�.
	}
}
