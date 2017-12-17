import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class AppController {
	private int Food_Size = 0;// 실제 데이터 베이스에 있는 음식의 수를 나타내는 변수
	private int Choiced_Food_Size = 0; // 선택되어진 음식의 수를 나타내는 변수
	private List<Food> Food_Array = new ArrayList<Food>(); // 실제 All_Food에서 가지고 있는건
	private List<Food> ALL_Ate_Food = new ArrayList<Food>();
	private int ALL_Ate_Food_Size = 0;
	private List<Food> Ate_Food = new ArrayList<Food>(); // 여태까지 선택되었던 푸드들을 저장하고 있는 클래스들
	private List<Food> Choice_Food_Array = new ArrayList<Food>();
	private int Ate_Food_Size = 0;
	
	public class Food {// 내부 클래스 Food 구현(실제로 정보를 가지고 있는 클래스)
		private String Store;
		private String Food_Name;
		private String Kind;
		private String Nation;
		private double Time;
		private String Comment;
		public Food() {	}// 빈 생성자
		public Food(String Store, String Food_Name, String Kind, String Nation, double Time, String Comment) {
			this.Store = Store;
			this.Food_Name = Food_Name;
			this.Kind = Kind;
			this.Nation = Nation;
			this.Time = Time;
			this.Comment = Comment;
		}// Food 생성자
		public String get_Store() {return Store;}
		public String get_Food_Name() {return Food_Name;}
		public String get_Kind() {return Kind;}
		public String get_Nation() {return Nation;}
		public double get_Time() {return Time;}
		public String get_Comment() {return Comment;}
	}// 일단 필요할 경우 Food의 이름, 종류, 나라, 시간 등에 접근 할수있는 getter와 설정할 수 있는 setter를 만들어 주어야할듯
		// 예를 들면 관리자가 걸리는 시간을 바꾸고 싶은 경우에 해당된다.
	public int get_Ate_Food_Size() {return Ate_Food_Size;}
	public List<Food> get_Food() {return Food_Array;}
	public List<Food> get_Ate_Food() {return Ate_Food;}
	public List<Food> get_ALL_Ate_Food(){return ALL_Ate_Food;}
	public int get_Food_Size() {return Food_Size;}
	public void set_Food_Size(int size) {Food_Size = size;}
	public void add_List_Food(String Store, String Food_Name, String Kind, String Nation, double Time, String Comment) {
		Food add_List_Food = new Food(Store, Food_Name, Kind, Nation, Time, Comment); // Food 객체 생성후 더해주기 위한 준비과정
		Food_Array.add(add_List_Food); // 실제로 array_List안에 Food 객체를 넣어주는 것
		Food_Size++; // Food가 더해졌으므로 사이즈 +1 해줘야 함.
	}// 새로운 음식을 더해주는 함수
	
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
	}// 원래 있던 음식 DB에서 관리자가 음식을 삭제해줄때 쓰는 함수
	public void Random_Choice_Food() {
		Random random = new Random();
		int x = random.nextInt(Food_Size);
		System.out.print("<'" + Food_Array.get(x).Store + "'> " + Food_Array.get(x).Food_Name + " 먹는것을 추천!");
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
	   
	   System.out.println("코멘트를 추가하시겠습니까? (yes:1)");
		int select1;
		Scanner sc = new Scanner(System.in);
		select1=sc.nextInt();
		if(select1==1) {
		Scanner Scan = new Scanner(System.in);
		System.out.print("추가할 가게를 입력 해주세요!\n");
		Input_Store = Scan.nextLine();
		System.out.print(Input_Store+"을 입력하셨습니다. 추가할 가게의 메뉴를 입력 하여 주세요!");
		Input_Menu = Scan.nextLine();
		System.out.print(Input_Menu+"을 입력하셨습니다. 추가할 코멘트를 입력하여 주세요!");
		Input_Comment = Scan.nextLine();
		for(int i=0; i<Food_Size;i++) {
			if(Input_Store.equals(Food_Array.get(i).Store)&&Input_Menu.equals(Food_Array.get(i).Food_Name)) {
				System.out.println(Input_Store+"의 "+Input_Menu+"에 작성하신 '" +Input_Comment+"' 를 추가 하였습니다.");
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
		else { //이 경우 리뷰를 쓰지 않는것으로 먹지 않았다는 것을 표시한다.
			System.out.println("코멘트를 달지 않았으므로 먹은 것이 없는 것으로 처리 합니다. ");
			int choi = Ate_Food.size();
			for(int i=(choi-1);0<=i; i--) {
				Ate_Food.remove(i);
				--Ate_Food_Size;}
		}
	}
    public void Delete_Comment() {
	      Scanner Scan = new Scanner(System.in);
	      System.out.println("지우고 싶은 코멘트가 있는 가게의 이름을 입력 하여 주세요.");
	      String Delete_Store = Scan.nextLine();
	      System.out.println("지우고 싶은 코멘트가 있는 가게의 메뉴를 입력 하여 주세요.");
	      String Delete_Menu = Scan.nextLine();
	      
	      for(int i=0; i<Food_Size;i++) {
	         if(Delete_Store.equals(Food_Array.get(i).Store)&&Delete_Menu.equals(Food_Array.get(i).Food_Name)) {
	               if(!Food_Array.get(i).Comment.equals(" "))
	               System.out.println(Delete_Store+"의"+Delete_Menu+"에는 아래와 같은 코멘트가 있습니다. 몇번을 삭제 하시겠습니까? 한 숫자로 입력해주세요. 코멘트에 없는 x번째 숫자를 입력시 작업이 그냥 끝납니다. ");
	               if(Food_Array.get(i).Comment.equals(" ")) {
	                  System.out.println("삭제할 수 있는 코멘트가 없습니다.");
	                  break;
	               }
	               int bar_number=0; //이걸로 얼마나 많은 코멘트 들이 있는지 알 수 있다.
	               for(int check_bar_number=0; check_bar_number<Food_Array.get(i).Comment.length(); check_bar_number++) {
	                  if(Food_Array.get(i).Comment.charAt(check_bar_number)=='&')
	                     bar_number++;
	               }//이렇게 얼마나 많은 코멘트들이 있는지 bar를 통해서 구해 준것이다. 만약 바가 1이상이라면 적어도 2개이상의 코멘트가 있었던 것   
	               
	               String Compare = Food_Array.get(i).Comment;
	               String[] split_Compare = Compare.split("&");
	               for(int comment =0; comment<split_Compare.length; comment++) {
	                  System.out.println(comment+1+". "+split_Compare[comment]);//여기에 모든 코멘트를 출력
	               }
	               String Delete_Number = Scan.nextLine();
	               if(Integer.parseInt(Delete_Number)>split_Compare.length) {//만약 존재하는 코멘트의 수보다 지우려는 코멘트의 번호가 클 경우. 
	                  System.out.println("해당하는 번호는 존재하지 않습니다. 작업을 종료합니다.");
	                  break;
	               }
	               if(Delete_Number.equals("1")&&split_Compare.length==1) {
	                  Food_Array.get(i).Comment = " ";}//만약 0번째 인덱스에 있는 것을 삭제하고 그 삭제하고 싶은 코멘트가 유일한 코멘트일 경우 (즉, 코멘트가 1개밖에 없을 경우)
	               else {
	                  String twin_String = ""; //마지막에 결국 반환하여야 하는 코멘트 스트링 값
	                  for(int plus=0; plus<split_Compare.length; plus++) {
	                     if(plus!=(Integer.parseInt(Delete_Number)-1)) { //삭제 시켜야 하는 인덱스의 값은 빼주고, 마지막 인덱스의 값은 양식을 맞춰 주기 위해 따로 선언해준다.
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
	      System.out.println("코멘트를 보고 싶은 가게의 이름을 입력하여 주세요.");
	      String Print_Comment_Store = scan.nextLine();
	      System.out.println("코멘트를 보고 싶은 가게의 메뉴를 입력하여 주세요.");
	      String Print_Comment_Menu = scan.nextLine();
	      for(int i=0; i<Food_Size;i++) {
	         if(Print_Comment_Store.equals(Food_Array.get(i).Store)&&Print_Comment_Menu.equals(Food_Array.get(i).Food_Name)) {
	               int bar_number=0; //이걸로 얼마나 많은 코멘트 들이 있는지 알 수 있다.
	               for(int check_bar_number=0; check_bar_number<Food_Array.get(i).Comment.length(); check_bar_number++) {
	                  if(Food_Array.get(i).Comment.charAt(check_bar_number)=='&')
	                     bar_number++;
	               }//이렇게 얼마나 많은 코멘트들이 있는지 bar를 통해서 구해 준것이다. 만약 바가 1이상이라면 적어도 2개이상의 코멘트가 있었던 것   
	               
	               String Compare = Food_Array.get(i).Comment;
	               String[] split_Compare = Compare.split("&");
	               
	               if(Food_Array.get(i).Comment.equals(" "))
	            	   System.out.println(Print_Comment_Store+"의 "+Print_Comment_Menu + "의 출력할 코멘트가 없습니다.");
	               else {
	               System.out.println(Print_Comment_Store+"의 "+Print_Comment_Menu+"의 코멘트는 아래와 같습니다.");
	               for(int print=0; print<split_Compare.length;print++) {
	                  System.out.println(print+1+". "+split_Compare[print]);
	               }
	               }
	            }
	      }
   }
    public void Print_All_Comment() {
	      for(int i=0; i<Food_Size;i++) {
	    	  int bar_number=0; //이걸로 얼마나 많은 코멘트 들이 있는지 알 수 있다.
	          for(int check_bar_number=0; check_bar_number<Food_Array.get(i).Comment.length(); check_bar_number++) {
	        	  if(Food_Array.get(i).Comment.charAt(check_bar_number)=='&')
	        		  bar_number++;
	        	  }//이렇게 얼마나 많은 코멘트들이 있는지 bar를 통해서 구해 준것이다. 만약 바가 1이상이라면 적어도 2개이상의 코멘트가 있었던 것   
	               String Compare = Food_Array.get(i).Comment;
	               String[] split_Compare = Compare.split("&");
	               if(Food_Array.get(i).Comment.equals(" "))
	            	   System.out.println(Food_Array.get(i).Store+"의 "+Food_Array.get(i).Food_Name+"의 코멘트는 존재하지 않습니다.");
	               else {
	               System.out.println(Food_Array.get(i).Store+"의 "+Food_Array.get(i).Food_Name+"의 코멘트는 아래와 같습니다.");
	               for(int print=0; print<split_Compare.length;print++) {
	                  System.out.println(print+1+". "+split_Compare[print]); }
	               }
	       }
	}
    public List<Food> Choice_Food_Add(List<Food> Choice_Food) {
	      Scanner Scan = new Scanner(System.in);
	      System.out.println("\n원하시는 음식의 종류를 골라 주세요. 1. 한식, 2. 일식, 3. 중식, 4. 양식, 5. 분식");
	      List<Food> Choice_Food_Array = Choice_Food; //나중에 추가 된 음식들만 따로 담아주기 위한 객체 생성
	      String Choice = Scan.next(); //한식 일지, 일식 일지, 중식,양식,분식 일지 고르게 하는 int 형 변수

	      for (int k = 0; k < Choice.length(); k++) {
	         switch (Choice.charAt(k)) {
	         case '1':
	            System.out.println("원하시는 한식의 세부 종류를 골라 주세요. 1. 밥, 2. 면, 3. 고기");
	            Scanner ScanKind = new Scanner(System.in);
	            String Korean = ScanKind.nextLine();
	            /**********************************한식************************************/
	            switch(Korean.charAt(0)) {
	            case '1':
	               for (int i = 0; i < Food_Size; i++) {
	                  if (Food_Array.get(i).Nation.equals("한식")&&Food_Array.get(i).Kind.equals("밥")) { 
	                     Choice_Food_Array.add(Food_Array.get(i));
	                     Choiced_Food_Size++;
	                  }
	               }
	               break;
	            case '2':
	               for (int i = 0; i < Food_Size; i++) {
	                  if (Food_Array.get(i).Nation.equals("한식")&&Food_Array.get(i).Kind.equals("면")) { 
	                     Choice_Food_Array.add(Food_Array.get(i));
	                     Choiced_Food_Size++;
	                  }
	               }
	               break;
	            case '3':
	               for (int i = 0; i < Food_Size; i++) {
	                  if (Food_Array.get(i).Nation.equals("한식")&&Food_Array.get(i).Kind.equals("고기")) { 
	                     Choice_Food_Array.add(Food_Array.get(i));
	                     Choiced_Food_Size++;
	                  }
	               }
	               break;
	            
	            }
	            /****************************************************************************/
	            break;
	         case '2':
		            System.out.println("원하시는 일식의 세부 종류를 골라 주세요. 1. 밥, 2. 면, 3. 고기");
		            Scanner ScanKind2 = new Scanner(System.in);
		            String Japanese = ScanKind2.nextLine();
		            /*************************************일식***********************************/
		            switch(Japanese.charAt(0)) {
		            case '1':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("일식")&&Food_Array.get(i).Kind.equals("밥")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            case '2':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("일식")&&Food_Array.get(i).Kind.equals("면")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            case '3':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("일식")&&Food_Array.get(i).Kind.equals("고기")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            }
		            /********************************************************************************/
		            break;
	         case '3':
		            System.out.println("원하시는 중식의 세부 종류를 골라 주세요. 1. 밥, 2. 면, 3. 고기");
		            Scanner ScanKind3 = new Scanner(System.in);
		            String Chinese = ScanKind3.nextLine();
		            /***********************************중식*****************************************/
		            switch(Chinese.charAt(0)) {
		            case '1':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("중식")&&Food_Array.get(i).Kind.equals("밥")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            case '2':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("중식")&&Food_Array.get(i).Kind.equals("면")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            case '3':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("중식")&&Food_Array.get(i).Kind.equals("고기")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            
		            }
		            /**************************************************************************/
		            break;
	         case '4':
		            System.out.println("원하시는 양식의 세부 종류를 골라 주세요. 1. 밥, 2. 면, 3. 고기, 4. 햄치피 ");
		            Scanner ScanKind4 = new Scanner(System.in);
		            String West = ScanKind4.nextLine();
		            /********************************양식*****************************************/
		            switch(West.charAt(0)) {
		            case '1':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("양식")&&Food_Array.get(i).Kind.equals("밥")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            case '2':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("양식")&&Food_Array.get(i).Kind.equals("면")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            case '3':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("양식")&&Food_Array.get(i).Kind.equals("고기")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            case '4':
			               for (int i = 0; i < Food_Size; i++) {
			                  if (Food_Array.get(i).Nation.equals("양식")&&Food_Array.get(i).Kind.equals("햄치피")) { 
			                     Choice_Food_Array.add(Food_Array.get(i));
			                     Choiced_Food_Size++;
			                  }
			               }
			               break;
		            }
		            /**************************************************************************************/
		            break;
	         case '5':
		            System.out.println("원하시는 분식의 세부 종류를 골라 주세요. 1. 분식, 2. 면 ");
		            Scanner ScanKind5 = new Scanner(System.in);
		            String School = ScanKind5.nextLine();
		            /*********************************분식******************************************/
		            switch(School.charAt(0)) {
		            case '1':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("분식")&&Food_Array.get(i).Kind.equals("분식")) { 
		                     Choice_Food_Array.add(Food_Array.get(i));
		                     Choiced_Food_Size++;
		                  }
		               }
		               break;
		            case '2':
		               for (int i = 0; i < Food_Size; i++) {
		                  if (Food_Array.get(i).Nation.equals("분식")&&Food_Array.get(i).Kind.equals("면")) { 
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
	      }//중복 선택된 음식을 제거 해주는 경우! 
	      if((Choiced_Food_Size-2)>=0)
	      if(Choice_Food_Array.get(Choiced_Food_Size-1)==Choice_Food_Array.get(Choiced_Food_Size-2)) {
	         Choice_Food_Array.remove(Choiced_Food_Size-2);
	         Choiced_Food_Size--;
	      }
	      
	      System.out.println("현재까지 골라진 음식은 다음과 같습니다.");
	      for (int i = 0; i < Choiced_Food_Size; i++) {
	         System.out.print("<'" + Choice_Food_Array.get(i).Store + "' " + Choice_Food_Array.get(i).Food_Name + "> ");
	      } // 테스트 케이스

	      return Choice_Food_Array;// 새로 추가된 것들 까지 더해져서 리턴 해주는 것이다.
	   }
    public List<Food> Choice_Food_Delete(List<Food> Choice_Food) {  
       Scanner Scan = new Scanner(System.in);
       System.out.println("\n빼고싶은 음식의 종류를 골라 주세요. 1. 한식, 2. 일식, 3. 중식, 4. 양식, 5. 분식");
       List<Food> Choice_Food_Array = Choice_Food;
       String Choice = Scan.next(); // 한식일지 일식일지 중식,양식,분식일지 고르게 하는 int형 변수
      for (int k = 0; k < Choice.length(); k++) {
          switch (Choice.charAt(k)) {
          case '1':
             System.out.println("제거를 원하시는 한식의 세부 종류를 골라 주세요. 1. 밥, 2. 면, 3. 고기");
             Scanner ScanKind = new Scanner(System.in);
             String Korean = ScanKind.nextLine(); //여기서 for문을 붙이면 역시나 여러개를 제거 할 수 있다.
             //***************************한식*********************************//
             switch(Korean.charAt(0)) {
             case '1':
                for (int i = 0; i < Choiced_Food_Size;) {
                   if (Choice_Food_Array.get(i).Nation.equals("한식")&&Choice_Food_Array.get(i).Kind.equals("밥")) { // 만약 푸드 리스트에 '한식'이 있다면
                      Choice_Food_Array.remove(i);// 뺄것을 바로 제거해준다.
                      --Choiced_Food_Size; // 제거하고 나서 고른 음식들의 사이즈를 -1 해준다.
                      continue;}
                   i++;}
                break;
             case '2':
                for (int i = 0; i < Choiced_Food_Size;) {
                   if (Choice_Food_Array.get(i).Nation.equals("한식")&&Choice_Food_Array.get(i).Kind.equals("면")) { // 만약 푸드 리스트에 '한식'이 있다면
                      Choice_Food_Array.remove(i);// 뺄것을 바로 제거해준다.
                      --Choiced_Food_Size; // 제거하고 나서 고른 음식들의 사이즈를 -1 해준다.
                      continue; }
                   i++;}
                break;
             case '3':
                for (int i = 0; i < Choiced_Food_Size;) {
                if (Choice_Food_Array.get(i).Nation.equals("한식")&&Choice_Food_Array.get(i).Kind.equals("고기")) { // 만약 푸드 리스트에 '한식'이 있다면
                   Choice_Food_Array.remove(i);// 뺄것을 바로 제거해준다.
                   --Choiced_Food_Size; // 제거하고 나서 고른 음식들의 사이즈를 -1 해준다.
                   continue; }
                i++;}
                break;
             	}
             //******************************************************************************************************//
             break;
          case '2':
                System.out.println("제거를 원하시는 일식의 세부 종류를 골라 주세요. 1. 밥, 2. 면, 3. 고기");
                Scanner ScanKind2 = new Scanner(System.in);
                String Japanese = ScanKind2.nextLine(); //여기서 for문을 붙이면 역시나 여러개를 제거 할 수 있다.
            /***************************************일식***************************************************/    
                switch(Japanese.charAt(0)) {
                case '1':
                   for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("일식")&&Choice_Food_Array.get(i).Kind.equals("밥")) { // 만약 푸드 리스트에 '한식'이 있다면
                         Choice_Food_Array.remove(i);// 뺄것을 바로 제거해준다.
                         --Choiced_Food_Size; // 제거하고 나서 고른 음식들의 사이즈를 -1 해준다.
                         continue; }
                      i++;}
                   break;
                case '2':
                   for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("일식")&&Choice_Food_Array.get(i).Kind.equals("면")) { // 만약 푸드 리스트에 '한식'이 있다면
                         Choice_Food_Array.remove(i);// 뺄것을 바로 제거해준다.
                         --Choiced_Food_Size; // 제거하고 나서 고른 음식들의 사이즈를 -1 해준다.
                         continue;  }
                      i++; }
                   break;
                case '3':
                   for (int i = 0; i < Choiced_Food_Size;) {
                   if (Choice_Food_Array.get(i).Nation.equals("일식")&&Choice_Food_Array.get(i).Kind.equals("고기")) { // 만약 푸드 리스트에 '한식'이 있다면
                      Choice_Food_Array.remove(i);// 뺄것을 바로 제거해준다.
                      --Choiced_Food_Size; // 제거하고 나서 고른 음식들의 사이즈를 -1 해준다.
                      continue; }
                   i++;  }
                   break;
                }
                /*******************************************************************************************************/
                break;
          case '3':
                System.out.println("제거를 원하시는 중식의 세부 종류를 골라 주세요. 1. 밥, 2. 면, 3. 고기");
                Scanner ScanKind3 = new Scanner(System.in);
                String Chinese = ScanKind3.nextLine(); //여기서 for문을 붙이면 역시나 여러개를 제거 할 수 있다.
                /*************************************중식*************************************************/
                switch(Chinese.charAt(0)) {
                case '1':
                   for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("중식")&&Choice_Food_Array.get(i).Kind.equals("밥")) { // 만약 푸드 리스트에 '한식'이 있다면
                         Choice_Food_Array.remove(i);// 뺄것을 바로 제거해준다.
                         --Choiced_Food_Size; // 제거하고 나서 고른 음식들의 사이즈를 -1 해준다.
                         continue;  }
                      i++; }
                   break;
                case '2':
                   for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("중식")&&Choice_Food_Array.get(i).Kind.equals("면")) { // 만약 푸드 리스트에 '한식'이 있다면
                         Choice_Food_Array.remove(i);// 뺄것을 바로 제거해준다.
                         --Choiced_Food_Size; // 제거하고 나서 고른 음식들의 사이즈를 -1 해준다.
                         continue;  }
                      i++; }
                   break;
                case '3':
                   for (int i = 0; i < Choiced_Food_Size;) {
                   if (Choice_Food_Array.get(i).Nation.equals("중식")&&Choice_Food_Array.get(i).Kind.equals("고기")) { // 만약 푸드 리스트에 '한식'이 있다면
                      Choice_Food_Array.remove(i);// 뺄것을 바로 제거해준다.
                      --Choiced_Food_Size; // 제거하고 나서 고른 음식들의 사이즈를 -1 해준다.
                      continue;   }
                   i++; }
                   break;
                }
                /***********************************************************************************************/
                break;
          case '4':
                System.out.println("제거를 원하시는 양식의 세부 종류를 골라 주세요. 1. 밥, 2. 면, 3. 고기, 4. 햄치피");
                Scanner ScanKind4 = new Scanner(System.in);
                String West = ScanKind4.nextLine(); //여기서 for문을 붙이면 역시나 여러개를 제거 할 수 있다.
                /*************************************양식**************************************/
                switch(West.charAt(0)) {
                case '1':
                   for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("양식")&&Choice_Food_Array.get(i).Kind.equals("밥")) { // 만약 푸드 리스트에 '한식'이 있다면
                         Choice_Food_Array.remove(i);// 뺄것을 바로 제거해준다.
                         --Choiced_Food_Size; // 제거하고 나서 고른 음식들의 사이즈를 -1 해준다.
                         continue;   }
                      i++; }
                   break;
                case '2':
                   for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("양식")&&Choice_Food_Array.get(i).Kind.equals("면")) { // 만약 푸드 리스트에 '한식'이 있다면
                         Choice_Food_Array.remove(i);// 뺄것을 바로 제거해준다.
                         --Choiced_Food_Size; // 제거하고 나서 고른 음식들의 사이즈를 -1 해준다.
                         continue;}
                      i++;}
                   break;
                case '3':
                   for (int i = 0; i < Choiced_Food_Size;) {
                   if (Choice_Food_Array.get(i).Nation.equals("양식")&&Choice_Food_Array.get(i).Kind.equals("고기")) { // 만약 푸드 리스트에 '한식'이 있다면
                      Choice_Food_Array.remove(i);// 뺄것을 바로 제거해준다.
                      --Choiced_Food_Size; // 제거하고 나서 고른 음식들의 사이즈를 -1 해준다.
                      continue; }
                   i++; }
                   break;
                case '4':
                      for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("양식")&&Choice_Food_Array.get(i).Kind.equals("햄치피")) { // 만약 푸드 리스트에 '한식'이 있다면
                         Choice_Food_Array.remove(i);// 뺄것을 바로 제거해준다.
                         --Choiced_Food_Size; // 제거하고 나서 고른 음식들의 사이즈를 -1 해준다.
                         continue; }
                    i++;}
                    break;
                }
                /*****************************************************************************/
                break;
          case '5':
                System.out.println("제거를 원하시는 분식의 세부 종류를 골라 주세요. 1. 분식, 2. 면");
                Scanner ScanKind5 = new Scanner(System.in);
                String School = ScanKind5.nextLine(); //여기서 for문을 붙이면 역시나 여러개를 제거 할 수 있다.
                /*****************************************분식***********************************/
                switch(School.charAt(0)) {
                case '1':
                   for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("분식")&&Choice_Food_Array.get(i).Kind.equals("분식")) { // 만약 푸드 리스트에 '한식'이 있다면
                         Choice_Food_Array.remove(i);// 뺄것을 바로 제거해준다.
                         --Choiced_Food_Size; // 제거하고 나서 고른 음식들의 사이즈를 -1 해준다.
                         continue;}
                      i++;  }
                   break;
                case '2':
                   for (int i = 0; i < Choiced_Food_Size;) {
                      if (Choice_Food_Array.get(i).Nation.equals("분식")&&Choice_Food_Array.get(i).Kind.equals("면")) { // 만약 푸드 리스트에 '한식'이 있다면
                         Choice_Food_Array.remove(i);// 뺄것을 바로 제거해준다.
                         --Choiced_Food_Size; // 제거하고 나서 고른 음식들의 사이즈를 -1 해준다.
                         continue; }
                      i++;}
                   break;
                }
                /***********************************************************************************************/
                break;
          default:
             System.out.println("고를 수 없는 종류를 고르시면 안돼요!");
             break;
          }
      }
       System.out.println("현재까지 골라진 음식은 다음과 같습니다.");
       for (int i = 0; i < Choiced_Food_Size; i++) {
          System.out.print("<'" + Choice_Food_Array.get(i).Store + "' " + Choice_Food_Array.get(i).Food_Name + "> ");
       }
       return Choice_Food_Array;// 새로 추가된 것들 까지 더해져서 리턴 해주는 것이다.
    }
  	public List<Food> Choice_Food_A(List<Food> Choice_Food) {
		List<Food> Choice_Food_Array = Choice_Food; // 나중에 추가 된 음식들만 따로 담아주기 위한 객체 생성
		String Choice = "12345"; // 한식 일지 일식 일지 중식,양식,분식 일지 고르게 하는 int 형 변수
		int duplication = 0; // 만약 이미 추가가 되어있는데 또 추가하려고 한다면 그것은 추가가 되지 못하게 해야한다. (중복 방지 변수)
		for (int k = 0; k < Choice.length(); k++) {
			switch (Choice.charAt(k)) {
			case '1':
				for (int i = 0; i < Food_Size; i++) {
						if (Food_Array.get(i).Nation.equals("한식")) {
							Choice_Food_Array.add(Food_Array.get(i));
							Choiced_Food_Size++;} 
				}
				break;
			case '2':
				for (int i = 0; i < Food_Size; i++) {
					if (Food_Array.get(i).Nation.equals("일식")) {
						Choice_Food_Array.add(Food_Array.get(i));
						Choiced_Food_Size++;} 
				}
				break;
			case '3':
				for (int i = 0; i < Food_Size; i++) {
					if (Food_Array.get(i).Nation.equals("중식")) {
						Choice_Food_Array.add(Food_Array.get(i));
						Choiced_Food_Size++;} 
			}
			break;
			case '4':
				for (int i = 0; i < Food_Size; i++) {
					if (Food_Array.get(i).Nation.equals("양식")) {
						Choice_Food_Array.add(Food_Array.get(i));
						Choiced_Food_Size++;} 
				}
				break;
			case '5':
				for (int i = 0; i < Food_Size; i++) {
					if (Food_Array.get(i).Nation.equals("분식")) {
						Choice_Food_Array.add(Food_Array.get(i));
						Choiced_Food_Size++;// 중복이 될 수 있다는 말이므로 중복 변수를 증가시켜 준다.
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
		return Choice_Food_Array;// 새로 추가된 것들 까지 더해져서 리턴 해주는 것이다.
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
			System.out.println("\n 계속해서 음식 종류를 고르시려면 1을, 그만 고르시려면 다른수를 입력 해주세요. ");
			Choice = scan.nextInt();
		} while (Choice == 1);
		}
		else if(select==2) {
			Choice_Food_Array = Choice_Food_A(Choice_Food_Array);
		do {
			Choice_Food_Array = Choice_Food_Delete(Choice_Food_Array);
			System.out.println("\n 계속해서 음식 종류를 빼고 싶으시면 1을, 그만 빼고 싶으시면 다른수를 입력 해주세요. ");
			Choice = scan.nextInt();
		} while (Choice == 1);
		}
		else{
			System.out.println("잘못선택하셨습니다.");
			break;
		}}while(select!=1&&select!=2);
		System.out.println("해당하는 음식점의 메뉴의 코멘트입니다. ");
		Print_Comment(Choice_Food_Array);
		Ate_Food_Size = Choice_Food_Array.size();
		String add_comment;
		System.out.println("");
	}
	public void Print_Comment(List<Food> Comment_Food) {
		for (int i = 0; i < Choiced_Food_Size; i++) {
			System.out.println((i+1)+"번째 음식점 <'" + Comment_Food.get(i).Store + "' " + Comment_Food.get(i).Food_Name + ">");

			String Compare = Comment_Food.get(i).Comment;
            String[] split_Compare = Compare.split("&");
            for(int comment =0; comment<split_Compare.length; comment++) {
               System.out.print(" "+(comment+1)+":"+split_Compare[comment]+"");//여기에 모든 코멘트를 출력
               if(comment!=split_Compare.length-1) {
   				System.out.print(",");
   			}
            
            }
            System.out.println();
		}
	}//코멘트를 출력해주는 메소드

	public void Print_Ate_Food(List<Food> Ate_Food) {
		System.out.println("------------이번에 골랐던 음식들은 다음과 같습니다------------");
		for (int i = 0; i < Ate_Food_Size; i++) {
			System.out.println("<'" + Ate_Food.get(i).Store + "' " + Ate_Food.get(i).Food_Name + "> ");
			
		}
	}
	public void Print_All_Food() {
		System.out.println("------해당 하는 음식은 다음과 같습니다.------");
		for (int i = 0; i < Food_Size; i++) {
			System.out.print("<'" + Food_Array.get(i).Store + "' " + Food_Array.get(i).Food_Name + "> ");
		}
		System.out.println();
	}
	public void What_Percent() {

		double what_percent = 0;
		if(ALL_Ate_Food.size()==0) {
			System.out.println("점령한 음식점이 없습니다.");
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
		System.out.println("지금까지 점령한 음식점은 "+(int)((what_percent/Food_Array.size())*100)+ "% 입니다." );
		}
	}
	public void What_ate() {
		   if(ALL_Ate_Food.size()==0) {
		      System.out.println("드신것이 없으시므로 보여드릴것이 없습니다.");}
		   else {
			   System.out.println("최근에 먹은 음식은 다음과 같습니다.");
			   for(int i=0; i<6; i++) {
				   if(ALL_Ate_Food.size()>i) {
				   System.out.println((i+1)+". "+ALL_Ate_Food.get(ALL_Ate_Food.size()-(i+1)).Store+ "의 "+ALL_Ate_Food.get(ALL_Ate_Food.size()-(i+1)).Food_Name);
				   }
			   }
		   }
	}
}