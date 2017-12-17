import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class AppController {
	private int Food_Size = 0;// ���� ������ ���̽��� �ִ� ������ ���� ��Ÿ���� ����
	private int Choiced_Food_Size = 0; // ���õǾ��� ������ ���� ��Ÿ���� ����
	private List<Food> Food_Array = new ArrayList<Food>(); // ���� All_Food���� ������ �ִ°�
	private List<Food> ALL_Ate_Food = new ArrayList<Food>();
	private int ALL_Ate_Food_Size = 0;
	private List<Food> Ate_Food = new ArrayList<Food>(); // ���±��� ���õǾ��� Ǫ����� �����ϰ� �ִ� Ŭ������
	private List<Food> Choice_Food_Array = new ArrayList<Food>();
	private int Ate_Food_Size = 0;
	
	public class Food {// ���� Ŭ���� Food ����(������ ������ ������ �ִ� Ŭ����)
		private String Store;
		private String Food_Name;
		private String Kind;
		private String Nation;
		private double Time;
		private String Comment;
		public Food() {	}// �� ������
		public Food(String Store, String Food_Name, String Kind, String Nation, double Time, String Comment) {
			this.Store = Store;
			this.Food_Name = Food_Name;
			this.Kind = Kind;
			this.Nation = Nation;
			this.Time = Time;
			this.Comment = Comment;
		}// Food ������
		public String get_Store() {return Store;}
		public String get_Food_Name() {return Food_Name;}
		public String get_Kind() {return Kind;}
		public String get_Nation() {return Nation;}
		public double get_Time() {return Time;}
		public String get_Comment() {return Comment;}
	}// �ϴ� �ʿ��� ��� Food�� �̸�, ����, ����, �ð� � ���� �Ҽ��ִ� getter�� ������ �� �ִ� setter�� ����� �־���ҵ�
		// ���� ��� �����ڰ� �ɸ��� �ð��� �ٲٰ� ���� ��쿡 �ش�ȴ�.
	public int get_Ate_Food_Size() {return Ate_Food_Size;}
	public List<Food> get_Food() {return Food_Array;}
	public List<Food> get_Ate_Food() {return Ate_Food;}
	public List<Food> get_ALL_Ate_Food(){return ALL_Ate_Food;}
	public int get_Food_Size() {return Food_Size;}
	public void set_Food_Size(int size) {Food_Size = size;}
	public void add_List_Food(String Store, String Food_Name, String Kind, String Nation, double Time, String Comment) {
		Food add_List_Food = new Food(Store, Food_Name, Kind, Nation, Time, Comment); // Food ��ü ������ �����ֱ� ���� �غ����
		Food_Array.add(add_List_Food); // ������ array_List�ȿ� Food ��ü�� �־��ִ� ��
		Food_Size++; // Food�� ���������Ƿ� ������ +1 ����� ��.
	}// ���ο� ������ �����ִ� �Լ�
	
	public void add_ALL_Ate_Food(String Store, String Food_Name, String Kind, String Nation, double Time, String Comment) {
		Food add_ALL_Ate_Food = new Food(Store,Food_Name, Kind, Nation, Time, Comment);
		ALL_Ate_Food.add(add_ALL_Ate_Food);
		ALL_Ate_Food_Size++;
	}
	public void delete_List_Food(String Food_Name) {
		for (int i = 0; i < Food_Size; i++) {
			if (Food_Array.get(i).Food_Name == Food_Name) {
				Food_Array.remove(i);
				break;
			}
		}
		Food_Size--;
	}// ���� �ִ� ���� DB���� �����ڰ� ������ �������ٶ� ���� �Լ�
	public void Random_Choice_Food() {
		Random random = new Random();
		int x = random.nextInt(Food_Size);
		System.out.print("<'" + Food_Array.get(x).Store + "'> " + Food_Array.get(x).Food_Name + " �Դ°��� ��õ!");
	}
	public void Notime_Choice() {
		Random ran = new Random();
		Food get = null;
		int i=0;
		
		while(true) {
			int xx= ran.nextInt(Food_Size);
			if (Food_Array.get(xx).Time <= 0.4) {
			 get = Food_Array.get(xx);	
			 break;
			}
		}
		System.out.print(" <'" + get.Store + "'> " + get.Food_Name);
	}
    public void Add_Comment() {
	   String Input_Store;
	   String Input_Menu;
	   String Input_Comment;
	   
	   System.out.println("�ڸ�Ʈ�� �߰��Ͻðڽ��ϱ�? (yes:1)");
		int select1;
		Scanner sc = new Scanner(System.in);
		select1=sc.nextInt();
		if(select1==1) {
		Scanner Scan = new Scanner(System.in);
		System.out.print("�߰��� ���Ը� �Է� ���ּ���!\n");
		Input_Store = Scan.nextLine();
		System.out.print(Input_Store+"�� �Է��ϼ̽��ϴ�. �߰��� ������ �޴��� �Է� �Ͽ� �ּ���!");
		Input_Menu = Scan.nextLine();
		System.out.print(Input_Menu+"�� �Է��ϼ̽��ϴ�. �߰��� �ڸ�Ʈ�� �Է��Ͽ� �ּ���!");
		Input_Comment = Scan.nextLine();
		for(int i=0; i<Food_Size;i++) {
			if(Input_Store.equals(Food_Array.get(i).Store)&&Input_Menu.equals(Food_Array.get(i).Food_Name)) {
				System.out.println(Input_Store+"�� "+Input_Menu+"�� �ۼ��Ͻ� '" +Input_Comment+"' �� �߰� �Ͽ����ϴ�.");
			   	Food_Array.get(i).Comment = Food_Array.get(i).Comment +"&" +Input_Comment; 
			   	}
		}
		List<Food> Real_Ate_Food = new ArrayList<Food>();
			for (int i = 0; i < Food_Array.size(); i++) {
				if(Food_Array.get(i).Store.equals(Input_Store)&&Food_Array.get(i).Food_Name.equals(Input_Menu)) {
					Real_Ate_Food.add(Food_Array.get(i));
					ALL_Ate_Food.add(Food_Array.get(i));
					}
			}
			Ate_Food = Real_Ate_Food;
		}
		else { //�� ��� ���並 ���� �ʴ°����� ���� �ʾҴٴ� ���� ǥ���Ѵ�.
			System.out.println("�ڸ�Ʈ�� ���� �ʾ����Ƿ� ���� ���� ���� ������ ó�� �մϴ�. ");
			int choi = Ate_Food.size();
			for(int i=(choi-1);0<=i; i--) {
				Ate_Food.remove(i);
				--Ate_Food_Size;}
		}
	}
    public void Delete_Comment() {
	      Scanner Scan = new Scanner(System.in);
	      System.out.println("����� ���� �ڸ�Ʈ�� �ִ� ������ �̸��� �Է� �Ͽ� �ּ���.");
	      String Delete_Store = Scan.nextLine();
	      System.out.println("����� ���� �ڸ�Ʈ�� �ִ� ������ �޴��� �Է� �Ͽ� �ּ���.");
	      String Delete_Menu = Scan.nextLine();
	      
	      for(int i=0; i<Food_Size;i++) {
	         if(Delete_Store.equals(Food_Array.get(i).Store)&&Delete_Menu.equals(Food_Array.get(i).Food_Name)) {
	               if(!Food_Array.get(i).Comment.equals(" "))
	               System.out.println(Delete_Store+"��"+Delete_Menu+"���� �Ʒ��� ���� �ڸ�Ʈ�� �ֽ��ϴ�. ����� ���� �Ͻðڽ��ϱ�? �� ���ڷ� �Է����ּ���. �ڸ�Ʈ�� ���� x��° ���ڸ� �Է½� �۾��� �׳� �����ϴ�. ");
	               if(Food_Array.get(i).Comment.equals(" ")) {
	                  System.out.println("������ �� �ִ� �ڸ�Ʈ�� �����ϴ�.");
	                  break;
	               }
	               int bar_number=0; //�̰ɷ� �󸶳� ���� �ڸ�Ʈ ���� �ִ��� �� �� �ִ�.
	               for(int check_bar_number=0; check_bar_number<Food_Array.get(i).Comment.length(); check_bar_number++) {
	                  if(Food_Array.get(i).Comment.charAt(check_bar_number)=='&')
	                     bar_number++;
	               }//�̷��� �󸶳� ���� �ڸ�Ʈ���� �ִ��� bar�� ���ؼ� ���� �ذ��̴�. ���� �ٰ� 1�̻��̶�� ��� 2���̻��� �ڸ�Ʈ�� �־��� ��   
	               
	               String Compare = Food_Array.get(i).Comment;
	               String[] split_Compare = Compare.split("&");
	               for(int comment =0; comment<split_Compare.length; comment++) {
	                  System.out.println(comment+1+". "+split_Compare[comment]);//���⿡ ��� �ڸ�Ʈ�� ���
	               }
	               String Delete_Number = Scan.nextLine();
	               if(Integer.parseInt(Delete_Number)>split_Compare.length) {//���� �����ϴ� �ڸ�Ʈ�� ������ ������� �ڸ�Ʈ�� ��ȣ�� Ŭ ���. 
	                  System.out.println("�ش��ϴ� ��ȣ�� �������� �ʽ��ϴ�. �۾��� �����մϴ�.");
	                  break;
	               }
	               if(Delete_Number.equals("1")&&split_Compare.length==1) {
	                  Food_Array.get(i).Comment = " ";}//���� 0��° �ε����� �ִ� ���� �����ϰ� �� �����ϰ� ���� �ڸ�Ʈ�� ������ �ڸ�Ʈ�� ��� (��, �ڸ�Ʈ�� 1���ۿ� ���� ���)
	               else {
	                  String twin_String = ""; //�������� �ᱹ ��ȯ�Ͽ��� �ϴ� �ڸ�Ʈ ��Ʈ�� ��
	                  for(int plus=0; plus<split_Compare.length; plus++) {
	                     if(plus!=(Integer.parseInt(Delete_Number)-1)) { //���� ���Ѿ� �ϴ� �ε����� ���� ���ְ�, ������ �ε����� ���� ����� ���� �ֱ� ���� ���� �������ش�.
	                        twin_String = twin_String +split_Compare[plus];
	                        if(plus!=split_Compare.length-1) {
	                           twin_String = twin_String + "&";}
	                     }
	                  }
	                  Food_Array.get(i).Comment = twin_String;
	                  
	                  for(int ATE_SIZE = 0; ATE_SIZE < ALL_Ate_Food.size(); ATE_SIZE++) {
	                	  if(Food_Array.get(i).Store.equals(ALL_Ate_Food.get(ATE_SIZE).Store))
	                		  if(Food_Array.get(i).Food_Name.equals(ALL_Ate_Food.get(ATE_SIZE).Food_Name))
	                			 ALL_Ate_Food.get(ATE_SIZE).Comment = twin_String; 
	                  }
	               }
	            break;}
	      }
	}
    public void Print_Comment() {
	      Scanner scan = new Scanner(System.in);
	      System.out.println("�ڸ�Ʈ�� ���� ���� ������ �̸��� �Է��Ͽ� �ּ���.");
	      String Print_Comment_Store = scan.nextLine();
	      System.out.println("�ڸ�Ʈ�� ���� ���� ������ �޴��� �Է��Ͽ� �ּ���.");
	      String Print_Comment_Menu = scan.nextLine();
	      for(int i=0; i<Food_Size;i++) {
	         if(Print_Comment_Store.equals(Food_Array.get(i).Store)&&Print_Comment_Menu.equals(Food_Array.get(i).Food_Name)) {
	               int bar_number=0; //�̰ɷ� �󸶳� ���� �ڸ�Ʈ ���� �ִ��� �� �� �ִ�.
	               for(int check_bar_number=0; check_bar_number<Food_Array.get(i).Comment.length(); check_bar_number++) {
	                  if(Food_Array.get(i).Comment.charAt(check_bar_number)=='&')
	                     bar_number++;
	               }//�̷��� �󸶳� ���� �ڸ�Ʈ���� �ִ��� bar�� ���ؼ� ���� �ذ��̴�. ���� �ٰ� 1�̻��̶�� ��� 2���̻��� �ڸ�Ʈ�� �־��� ��   
	               
	               String Compare = Food_Array.get(i).Comment;
	               String[] split_Compare = Compare.split("&");
	               
	               if(Food_Array.get(i).Comment.equals(" "))
	            	   System.out.println(Print_Comment_Store+"�� "+Print_Comment_Menu + "�� ����� �ڸ�Ʈ�� �����ϴ�.");
	               else {
	               System.out.println(Print_Comment_Store+"�� "+Print_Comment_Menu+"�� �ڸ�Ʈ�� �Ʒ��� �����ϴ�.");
	               for(int print=0; print<split_Compare.length;print++) {
	                  System.out.println(print+1+". "+split_Compare[print]);
	               }
	               }
	            }
	      }
   }
    public void Print_All_Comment() {
	      for(int i=0; i<Food_Size;i++) {
	    	  int bar_number=0; //�̰ɷ� �󸶳� ���� �ڸ�Ʈ ���� �ִ��� �� �� �ִ�.
	          for(int check_bar_number=0; check_bar_number<Food_Array.get(i).Comment.length(); check_bar_number++) {
	        	  if(Food_Array.get(i).Comment.charAt(check_bar_number)=='&')
	        		  bar_number++;
	        	  }//�̷��� �󸶳� ���� �ڸ�Ʈ���� �ִ��� bar�� ���ؼ� ���� �ذ��̴�. ���� �ٰ� 1�̻��̶�� ��� 2���̻��� �ڸ�Ʈ�� �־��� ��   
	               String Compare = Food_Array.get(i).Comment;
	               String[] split_Compare = Compare.split("&");
	               if(Food_Array.get(i).Comment.equals(" "))
	            	   System.out.println(Food_Array.get(i).Store+"�� "+Food_Array.get(i).Food_Name+"�� �ڸ�Ʈ�� �������� �ʽ��ϴ�.");
	               else {
	               System.out.println(Food_Array.get(i).Store+"�� "+Food_Array.get(i).Food_Name+"�� �ڸ�Ʈ�� �Ʒ��� �����ϴ�.");
	               for(int print=0; print<split_Compare.length;print++) {
	                  System.out.println(print+1+". "+split_Compare[print]); }
	               }
	       }
	}
    public List<Food> Choice_Food_Add(List<Food> Choice_Food) {
	      Scanner Scan = new Scanner(System.in);
	      System.out.println("\n���Ͻô� ������ ������ ��� �ּ���. 1. �ѽ�, 2. �Ͻ�, 3. �߽�, 4. ���, 5. �н�");
	      List<Food> Choice_Food_Array = Choice_Food; //���߿� �߰� �� ���ĵ鸸 ���� ����ֱ� ���� ��ü ����
	      String Choice = Scan.next(); //�ѽ� ����, �Ͻ� ����, �߽�,���,�н� ���� ���� �ϴ� int �� ����

	      for (int k = 0; k < Choice.length(); k++) {
	         switch (Choice.charAt(k)) {
	         case '1':
	            System.out.println("���Ͻô� �ѽ��� ���� ������ ��� �ּ���. 1. ��, 2. ��, 3. ���");
	            Scanner ScanKind = new Scanner(System.in);
	            String Korean = ScanKind.nextLine();
	            /**********************************�ѽ�************************************/
	            switch(Korean.charAt(0)) {
	            case '1':
	               for (int i = 0; i < Food_Size; i++) {
	                  if (Food_Array.get(i).Nation.equals("�ѽ�")&&Food_Array.get(i).Kind.equals("��")) { 
	                     Choice_Food_Array.add(Food_Array.get(i));
	                     Choiced_Food_Size++;
	                  }
	               }
	               break;
	            case '2':
	               for (int i = 0; i < Food_Size; i++) {
	                  if (Food_Array.get(i).Nation.equals("�ѽ�")&&Food_Array.get(i).Kind.equals("��")) { 
	                     Choice_Food_Array.add(Food_Array.get(i));
	                     Choiced_Food_Size++;
	                  }
	               }
	               break;
	            case '3':
	               for (int i = 0; i < Food_Size; i++) {
	                  if (Food_Array.get(i).Nation.equals("�ѽ�")&&Food_Array.get(i).Kind.equals("���")) { 
	                     Choice_Food_Array.add(Food_Array.get(i));
	                     Choiced_Food_Size++;
	                  }
	               }
	               break;
	            
	            }
	            /****************************************************************************/
	            break;
	         case '2':
		            System.out.println("���Ͻô� �Ͻ��� ���� ������ ��� �ּ���. 1. ��, 2. ��, 3. ���");
		            Scanner ScanKind2 = new Scanner(System.in);
		            String Japanese = ScanKind2.nextLine();
		            /*************************************�Ͻ�***********************************/
		            switch(Japanese.charAt(0)) {
		            case '1':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("�Ͻ�")&&Food_Array.get(i).Kind.equals("��")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            case '2':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("�Ͻ�")&&Food_Array.get(i).Kind.equals("��")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            case '3':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("�Ͻ�")&&Food_Array.get(i).Kind.equals("���")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            }
		            /********************************************************************************/
		            break;
	         case '3':
		            System.out.println("���Ͻô� �߽��� ���� ������ ��� �ּ���. 1. ��, 2. ��, 3. ���");
		            Scanner ScanKind3 = new Scanner(System.in);
		            String Chinese = ScanKind3.nextLine();
		            /***********************************�߽�*****************************************/
		            switch(Chinese.charAt(0)) {
		            case '1':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("�߽�")&&Food_Array.get(i).Kind.equals("��")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            case '2':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("�߽�")&&Food_Array.get(i).Kind.equals("��")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            case '3':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("�߽�")&&Food_Array.get(i).Kind.equals("���")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            
		            }
		            /**************************************************************************/
		            break;
	         case '4':
		            System.out.println("���Ͻô� ����� ���� ������ ��� �ּ���. 1. ��, 2. ��, 3. ���, 4. ��ġ�� ");
		            Scanner ScanKind4 = new Scanner(System.in);
		            String West = ScanKind4.nextLine();
		            /********************************���*****************************************/
		            switch(West.charAt(0)) {
		            case '1':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("���")&&Food_Array.get(i).Kind.equals("��")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            case '2':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("���")&&Food_Array.get(i).Kind.equals("��")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            case '3':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("���")&&Food_Array.get(i).Kind.equals("���")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            case '4':
			               for (int i = 0; i < Food_Size; i++) {
			                  if (Food_Array.get(i).Nation.equals("���")&&Food_Array.get(i).Kind.equals("��ġ��")) { 
			                     Choice_Food_Array.add(Food_Array.get(i));
			                     Choiced_Food_Size++;
			                  }
			               }
			               break;
		            }
		            /**************************************************************************************/
		            break;
	         case '5':
		            System.out.println("���Ͻô� �н��� ���� ������ ��� �ּ���. 1. �н�, 2. �� ");
		            Scanner ScanKind5 = new Scanner(System.in);
		            String School = ScanKind5.nextLine();
		            /*********************************�н�******************************************/
		            switch(School.charAt(0)) {
		            case '1':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("�н�")&&Food_Array.get(i).Kind.equals("�н�")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            case '2':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("�н�")&&Food_Array.get(i).Kind.equals("��")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		               /**********************************************************************************/
		            }
		            break;
	         default:
	         }
	      }
	      for(int i=0; i<Choiced_Food_Size; i++) {
	         for(int j=i+1; j<Choiced_Food_Size; j++) {
	            if(Choice_Food_Array.get(i).Food_Name.equals(Choice_Food_Array.get(j).Food_Name)) {
	               Choice_Food_Array.remove(j);
	               Choiced_Food_Size--;
	            }
	         }
	      }//�ߺ� ���õ� ������ ���� ���ִ� ���! 
	      if((Choiced_Food_Size-2)>=0)
	      if(Choice_Food_Array.get(Choiced_Food_Size-1)==Choice_Food_Array.get(Choiced_Food_Size-2)) {
	         Choice_Food_Array.remove(Choiced_Food_Size-2);
	         Choiced_Food_Size--;
	      }
	      
	      System.out.println("������� ����� ������ ������ �����ϴ�.");
	      for (int i = 0; i < Choiced_Food_Size; i++) {
	         System.out.print("<'" + Choice_Food_Array.get(i).Store + "' " + Choice_Food_Array.get(i).Food_Name + "> ");
	      } // �׽�Ʈ ���̽�

	      return Choice_Food_Array;// ���� �߰��� �͵� ���� �������� ���� ���ִ� ���̴�.
	   }
    public List<Food> Choice_Food_Delete(List<Food> Choice_Food) {  
       Scanner Scan = new Scanner(System.in);
       System.out.println("\n������� ������ ������ ��� �ּ���. 1. �ѽ�, 2. �Ͻ�, 3. �߽�, 4. ���, 5. �н�");
       List<Food> Choice_Food_Array = Choice_Food;
       String Choice = Scan.next(); // �ѽ����� �Ͻ����� �߽�,���,�н����� ���� �ϴ� int�� ����
      for (int k = 0; k < Choice.length(); k++) {
          switch (Choice.charAt(k)) {
          case '1':
             System.out.println("���Ÿ� ���Ͻô� �ѽ��� ���� ������ ��� �ּ���. 1. ��, 2. ��, 3. ���");
             Scanner ScanKind = new Scanner(System.in);
             String Korean = ScanKind.nextLine(); //���⼭ for���� ���̸� ���ó� �������� ���� �� �� �ִ�.
             //***************************�ѽ�*********************************//
             switch(Korean.charAt(0)) {
             case '1':
                for (int i = 0; i < Choiced_Food_Size;) {
                   if (Choice_Food_Array.get(i).Nation.equals("�ѽ�")&&Choice_Food_Array.get(i).Kind.equals("��")) { // ���� Ǫ�� ����Ʈ�� '�ѽ�'�� �ִٸ�
                      Choice_Food_Array.remove(i);// ������ �ٷ� �������ش�.
                      --Choiced_Food_Size; // �����ϰ� ���� �� ���ĵ��� ����� -1 ���ش�.
                      continue;}
                   i++;}
                break;
             case '2':
                for (int i = 0; i < Choiced_Food_Size;) {
                   if (Choice_Food_Array.get(i).Nation.equals("�ѽ�")&&Choice_Food_Array.get(i).Kind.equals("��")) { // ���� Ǫ�� ����Ʈ�� '�ѽ�'�� �ִٸ�
                      Choice_Food_Array.remove(i);// ������ �ٷ� �������ش�.
                      --Choiced_Food_Size; // �����ϰ� ���� �� ���ĵ��� ����� -1 ���ش�.
                      continue; }
                   i++;}
                break;
             case '3':
                for (int i = 0; i < Choiced_Food_Size;) {
                if (Choice_Food_Array.get(i).Nation.equals("�ѽ�")&&Choice_Food_Array.get(i).Kind.equals("���")) { // ���� Ǫ�� ����Ʈ�� '�ѽ�'�� �ִٸ�
                   Choice_Food_Array.remove(i);// ������ �ٷ� �������ش�.
                   --Choiced_Food_Size; // �����ϰ� ���� �� ���ĵ��� ����� -1 ���ش�.
                   continue; }
                i++;}
                break;
             	}
             //******************************************************************************************************//
             break;
          case '2':
                System.out.println("���Ÿ� ���Ͻô� �Ͻ��� ���� ������ ��� �ּ���. 1. ��, 2. ��, 3. ���");
                Scanner ScanKind2 = new Scanner(System.in);
                String Japanese = ScanKind2.nextLine(); //���⼭ for���� ���̸� ���ó� �������� ���� �� �� �ִ�.
            /***************************************�Ͻ�***************************************************/    
                switch(Japanese.charAt(0)) {
                case '1':
                   for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("�Ͻ�")&&Choice_Food_Array.get(i).Kind.equals("��")) { // ���� Ǫ�� ����Ʈ�� '�ѽ�'�� �ִٸ�
                         Choice_Food_Array.remove(i);// ������ �ٷ� �������ش�.
                         --Choiced_Food_Size; // �����ϰ� ���� �� ���ĵ��� ����� -1 ���ش�.
                         continue; }
                      i++;}
                   break;
                case '2':
                   for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("�Ͻ�")&&Choice_Food_Array.get(i).Kind.equals("��")) { // ���� Ǫ�� ����Ʈ�� '�ѽ�'�� �ִٸ�
                         Choice_Food_Array.remove(i);// ������ �ٷ� �������ش�.
                         --Choiced_Food_Size; // �����ϰ� ���� �� ���ĵ��� ����� -1 ���ش�.
                         continue;  }
                      i++; }
                   break;
                case '3':
                   for (int i = 0; i < Choiced_Food_Size;) {
                   if (Choice_Food_Array.get(i).Nation.equals("�Ͻ�")&&Choice_Food_Array.get(i).Kind.equals("���")) { // ���� Ǫ�� ����Ʈ�� '�ѽ�'�� �ִٸ�
                      Choice_Food_Array.remove(i);// ������ �ٷ� �������ش�.
                      --Choiced_Food_Size; // �����ϰ� ���� �� ���ĵ��� ����� -1 ���ش�.
                      continue; }
                   i++;  }
                   break;
                }
                /*******************************************************************************************************/
                break;
          case '3':
                System.out.println("���Ÿ� ���Ͻô� �߽��� ���� ������ ��� �ּ���. 1. ��, 2. ��, 3. ���");
                Scanner ScanKind3 = new Scanner(System.in);
                String Chinese = ScanKind3.nextLine(); //���⼭ for���� ���̸� ���ó� �������� ���� �� �� �ִ�.
                /*************************************�߽�*************************************************/
                switch(Chinese.charAt(0)) {
                case '1':
                   for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("�߽�")&&Choice_Food_Array.get(i).Kind.equals("��")) { // ���� Ǫ�� ����Ʈ�� '�ѽ�'�� �ִٸ�
                         Choice_Food_Array.remove(i);// ������ �ٷ� �������ش�.
                         --Choiced_Food_Size; // �����ϰ� ���� �� ���ĵ��� ����� -1 ���ش�.
                         continue;  }
                      i++; }
                   break;
                case '2':
                   for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("�߽�")&&Choice_Food_Array.get(i).Kind.equals("��")) { // ���� Ǫ�� ����Ʈ�� '�ѽ�'�� �ִٸ�
                         Choice_Food_Array.remove(i);// ������ �ٷ� �������ش�.
                         --Choiced_Food_Size; // �����ϰ� ���� �� ���ĵ��� ����� -1 ���ش�.
                         continue;  }
                      i++; }
                   break;
                case '3':
                   for (int i = 0; i < Choiced_Food_Size;) {
                   if (Choice_Food_Array.get(i).Nation.equals("�߽�")&&Choice_Food_Array.get(i).Kind.equals("���")) { // ���� Ǫ�� ����Ʈ�� '�ѽ�'�� �ִٸ�
                      Choice_Food_Array.remove(i);// ������ �ٷ� �������ش�.
                      --Choiced_Food_Size; // �����ϰ� ���� �� ���ĵ��� ����� -1 ���ش�.
                      continue;   }
                   i++; }
                   break;
                }
                /***********************************************************************************************/
                break;
          case '4':
                System.out.println("���Ÿ� ���Ͻô� ����� ���� ������ ��� �ּ���. 1. ��, 2. ��, 3. ���, 4. ��ġ��");
                Scanner ScanKind4 = new Scanner(System.in);
                String West = ScanKind4.nextLine(); //���⼭ for���� ���̸� ���ó� �������� ���� �� �� �ִ�.
                /*************************************���**************************************/
                switch(West.charAt(0)) {
                case '1':
                   for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("���")&&Choice_Food_Array.get(i).Kind.equals("��")) { // ���� Ǫ�� ����Ʈ�� '�ѽ�'�� �ִٸ�
                         Choice_Food_Array.remove(i);// ������ �ٷ� �������ش�.
                         --Choiced_Food_Size; // �����ϰ� ���� �� ���ĵ��� ����� -1 ���ش�.
                         continue;   }
                      i++; }
                   break;
                case '2':
                   for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("���")&&Choice_Food_Array.get(i).Kind.equals("��")) { // ���� Ǫ�� ����Ʈ�� '�ѽ�'�� �ִٸ�
                         Choice_Food_Array.remove(i);// ������ �ٷ� �������ش�.
                         --Choiced_Food_Size; // �����ϰ� ���� �� ���ĵ��� ����� -1 ���ش�.
                         continue;}
                      i++;}
                   break;
                case '3':
                   for (int i = 0; i < Choiced_Food_Size;) {
                   if (Choice_Food_Array.get(i).Nation.equals("���")&&Choice_Food_Array.get(i).Kind.equals("���")) { // ���� Ǫ�� ����Ʈ�� '�ѽ�'�� �ִٸ�
                      Choice_Food_Array.remove(i);// ������ �ٷ� �������ش�.
                      --Choiced_Food_Size; // �����ϰ� ���� �� ���ĵ��� ����� -1 ���ش�.
                      continue; }
                   i++; }
                   break;
                case '4':
                      for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("���")&&Choice_Food_Array.get(i).Kind.equals("��ġ��")) { // ���� Ǫ�� ����Ʈ�� '�ѽ�'�� �ִٸ�
                         Choice_Food_Array.remove(i);// ������ �ٷ� �������ش�.
                         --Choiced_Food_Size; // �����ϰ� ���� �� ���ĵ��� ����� -1 ���ش�.
                         continue; }
                    i++;}
                    break;
                }
                /*****************************************************************************/
                break;
          case '5':
                System.out.println("���Ÿ� ���Ͻô� �н��� ���� ������ ��� �ּ���. 1. �н�, 2. ��");
                Scanner ScanKind5 = new Scanner(System.in);
                String School = ScanKind5.nextLine(); //���⼭ for���� ���̸� ���ó� �������� ���� �� �� �ִ�.
                /*****************************************�н�***********************************/
                switch(School.charAt(0)) {
                case '1':
                   for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("�н�")&&Choice_Food_Array.get(i).Kind.equals("�н�")) { // ���� Ǫ�� ����Ʈ�� '�ѽ�'�� �ִٸ�
                         Choice_Food_Array.remove(i);// ������ �ٷ� �������ش�.
                         --Choiced_Food_Size; // �����ϰ� ���� �� ���ĵ��� ����� -1 ���ش�.
                         continue;}
                      i++;  }
                   break;
                case '2':
                   for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("�н�")&&Choice_Food_Array.get(i).Kind.equals("��")) { // ���� Ǫ�� ����Ʈ�� '�ѽ�'�� �ִٸ�
                         Choice_Food_Array.remove(i);// ������ �ٷ� �������ش�.
                         --Choiced_Food_Size; // �����ϰ� ���� �� ���ĵ��� ����� -1 ���ش�.
                         continue; }
                      i++;}
                   break;
                }
                /***********************************************************************************************/
                break;
          default:
             System.out.println("�� �� ���� ������ ���ø� �ȵſ�!");
             break;
          }
      }
       System.out.println("������� ����� ������ ������ �����ϴ�.");
       for (int i = 0; i < Choiced_Food_Size; i++) {
          System.out.print("<'" + Choice_Food_Array.get(i).Store + "' " + Choice_Food_Array.get(i).Food_Name + "> ");
       }
       return Choice_Food_Array;// ���� �߰��� �͵� ���� �������� ���� ���ִ� ���̴�.
    }
  	public List<Food> Choice_Food_A(List<Food> Choice_Food) {
		List<Food> Choice_Food_Array = Choice_Food; // ���߿� �߰� �� ���ĵ鸸 ���� ����ֱ� ���� ��ü ����
		String Choice = "12345"; // �ѽ� ���� �Ͻ� ���� �߽�,���,�н� ���� ���� �ϴ� int �� ����
		int duplication = 0; // ���� �̹� �߰��� �Ǿ��ִµ� �� �߰��Ϸ��� �Ѵٸ� �װ��� �߰��� ���� ���ϰ� �ؾ��Ѵ�. (�ߺ� ���� ����)
		for (int k = 0; k < Choice.length(); k++) {
			switch (Choice.charAt(k)) {
			case '1':
				for (int i = 0; i < Food_Size; i++) {
						if (Food_Array.get(i).Nation.equals("�ѽ�")) {
							Choice_Food_Array.add(Food_Array.get(i));
							Choiced_Food_Size++;} 
				}
				break;
			case '2':
				for (int i = 0; i < Food_Size; i++) {
					if (Food_Array.get(i).Nation.equals("�Ͻ�")) {
						Choice_Food_Array.add(Food_Array.get(i));
						Choiced_Food_Size++;} 
				}
				break;
			case '3':
				for (int i = 0; i < Food_Size; i++) {
					if (Food_Array.get(i).Nation.equals("�߽�")) {
						Choice_Food_Array.add(Food_Array.get(i));
						Choiced_Food_Size++;} 
			}
			break;
			case '4':
				for (int i = 0; i < Food_Size; i++) {
					if (Food_Array.get(i).Nation.equals("���")) {
						Choice_Food_Array.add(Food_Array.get(i));
						Choiced_Food_Size++;} 
				}
				break;
			case '5':
				for (int i = 0; i < Food_Size; i++) {
					if (Food_Array.get(i).Nation.equals("�н�")) {
						Choice_Food_Array.add(Food_Array.get(i));
						Choiced_Food_Size++;// �ߺ��� �� �� �ִٴ� ���̹Ƿ� �ߺ� ������ �������� �ش�.
				} 
				}
				break;
			default:
			}
		}
		for (int i = 0; i < Choiced_Food_Size; i++) {
			for (int j =i+1; j < Choiced_Food_Size; j++){
				if ((Choice_Food_Array.get(i).Food_Name.equals(Choice_Food_Array.get(j).Food_Name))&&(Choice_Food_Array.get(i).Store.equals(Choice_Food_Array.get(j).Store))) {
					 Choice_Food_Array.remove(j);
					 Choiced_Food_Size--;}}
		}
		return Choice_Food_Array;// ���� �߰��� �͵� ���� �������� ���� ���ִ� ���̴�.
	}
	public void Choice_Food() {
		Scanner scan = new Scanner(System.in);
		int select;
		int Choice = 1;
		select = scan.nextInt();
		do {
		if(select==1) {
		do {
			Choice_Food_Array = Choice_Food_Add(Choice_Food_Array);
			System.out.println("\n ����ؼ� ���� ������ ���÷��� 1��, �׸� ���÷��� �ٸ����� �Է� ���ּ���. ");
			Choice = scan.nextInt();
		} while (Choice == 1);
		}
		else if(select==2) {
			Choice_Food_Array = Choice_Food_A(Choice_Food_Array);
		do {
			Choice_Food_Array = Choice_Food_Delete(Choice_Food_Array);
			System.out.println("\n ����ؼ� ���� ������ ���� �����ø� 1��, �׸� ���� �����ø� �ٸ����� �Է� ���ּ���. ");
			Choice = scan.nextInt();
		} while (Choice == 1);
		}
		else{
			System.out.println("�߸������ϼ̽��ϴ�.");
			break;
		}}while(select!=1&&select!=2);
		System.out.println("�ش��ϴ� �������� �޴��� �ڸ�Ʈ�Դϴ�. ");
		Print_Comment(Choice_Food_Array);
		Ate_Food_Size = Choice_Food_Array.size();
		String add_comment;
		System.out.println("");
	}
	public void Print_Comment(List<Food> Comment_Food) {
		for (int i = 0; i < Choiced_Food_Size; i++) {
			System.out.println((i+1)+"��° ������ <'" + Comment_Food.get(i).Store + "' " + Comment_Food.get(i).Food_Name + ">");

			String Compare = Comment_Food.get(i).Comment;
            String[] split_Compare = Compare.split("&");
            for(int comment =0; comment<split_Compare.length; comment++) {
               System.out.print(" "+(comment+1)+":"+split_Compare[comment]+"");//���⿡ ��� �ڸ�Ʈ�� ���
               if(comment!=split_Compare.length-1) {
   				System.out.print(",");
   			}
            
            }
            System.out.println();
		}
	}//�ڸ�Ʈ�� ������ִ� �޼ҵ�

	public void Print_Ate_Food(List<Food> Ate_Food) {
		System.out.println("------------�̹��� ����� ���ĵ��� ������ �����ϴ�------------");
		for (int i = 0; i < Ate_Food_Size; i++) {
			System.out.println("<'" + Ate_Food.get(i).Store + "' " + Ate_Food.get(i).Food_Name + "> ");
			
		}
	}
	public void Print_All_Food() {
		System.out.println("------�ش� �ϴ� ������ ������ �����ϴ�.------");
		for (int i = 0; i < Food_Size; i++) {
			System.out.print("<'" + Food_Array.get(i).Store + "' " + Food_Array.get(i).Food_Name + "> ");
		}
		System.out.println();
	}
	public void What_Percent() {

		double what_percent = 0;
		if(ALL_Ate_Food.size()==0) {
			System.out.println("������ �������� �����ϴ�.");
		}
		else {
		for(int i=0; i<Food_Array.size(); i++) {
			boolean is_ate = false;
			for(int j=0; j<ALL_Ate_Food.size(); j++) {
				if((is_ate==false)&&(ALL_Ate_Food.get(j).Store.equals(Food_Array.get(i).Store))&&(ALL_Ate_Food.get(j).Food_Name.equals(Food_Array.get(i).Food_Name))) {
					what_percent++;
					is_ate = true;
				}
			}
			is_ate = false;
		}
		System.out.println("���ݱ��� ������ �������� "+(int)((what_percent/Food_Array.size())*100)+ "% �Դϴ�." );
		}
	}
	public void What_ate() {
		   if(ALL_Ate_Food.size()==0) {
		      System.out.println("��Ű��� �����ùǷ� �����帱���� �����ϴ�.");}
		   else {
			   System.out.println("�ֱٿ� ���� ������ ������ �����ϴ�.");
			   for(int i=0; i<6; i++) {
				   if(ALL_Ate_Food.size()>i) {
				   System.out.println((i+1)+". "+ALL_Ate_Food.get(ALL_Ate_Food.size()-(i+1)).Store+ "�� "+ALL_Ate_Food.get(ALL_Ate_Food.size()-(i+1)).Food_Name);
				   }
			   }
		   }
	}
}